$('#deleteModal').iziModal();
toastr.options = {
      "closeButton": true,
      "debug": false,
      "newestOnTop": false,
      "progressBar": false,
      "positionClass": "toast-top-right",
      "preventDuplicates": false,
      "onclick": null,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "5000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    };

//check login status, access rights and get menus
checkLogin();

$(document).ready(function () {
	//Get Budget Year
	getObjects();
	
	document.getElementById('erasable').innerHTML = "";
});

function createObj() {
	ShowLoading();
	
	//Create budget year
    data = {};
    data['option'] = $('#insertx').val();
    data['name'] = document.getElementById("obj-name").value;
    data['year'] = document.getElementById("obj-year").value;
    data['isCurrentBaseYear'] = ($('#obj-current:checked').val() == 'on' ? true : false);
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetYearServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#insertedx').val()) !== -1) {
            	getObjects();
            	
            	ReturnToList();
            	
            	// Notification
                toastr["success"]("Budget year created successfully!", "Created");
            }
            else
                toastr["error"]("Item creation failed, record already exists!", "Failure");
            
            StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	StopLoading();
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function editObj() {
	ShowLoading();
	
	//Update budget year
    data = {};
    data['option'] = $('#updatex').val();
    data['id'] = document.getElementById("obj-id").value;
    data['name'] = document.getElementById("obj-ename").value;
    data['isCurrentBaseYear'] = ($('#obj-ecurrent:checked').val() == 'on' ? true : false);
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetYearServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#updatedx').val()) !== -1) {
            	CloseEdit();
            	
            	getObjects();
            	
            	// Notification
                toastr["success"]("Budget year updated successfully!", "Updated");
            } else  if(response.indexOf("BLOCKED") !== -1) {
                toastr["error"]("Current Base Year set to false, Add 3 more years beyond the current year ("+document.getElementById("obj-ename").value+")", "Failure");
            } else {
                toastr["error"]("Item edit failed, record does not exist!", "Failure");
            }
            
            StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	StopLoading();
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function getObjects() {
    data = {};
    data['option'] = $('#select-all').val();
    
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetYearServlet",
        data: data,
        dataType: "json",
        success: function(response) {
        	//alert(response);
        	var table = "<table id='main-tablex' class='table table-clean'>" +
	    					"<thead>" +
	        					"<tr>" +
	                                "<th><b>S/No</b></th>" +
	                                "<th><b>Name</b></th>" +
	                                "<th><b>Budget Year</b></th>" +
	                                "<th><b>Current Base Year?</b></th>" +
	                                "<th><b>Date Created</b></th>" +
	                                "<th><b>Action</b></th>" +
	                            "</tr>" +
	                        "</thead>" +
    	            		"<tbody>";
        
        	if(response.length > 0) {
                    var count = 0;
            	$.each(response, function(index, element) {
            		var buttons = '<button onclick="showEditObj(' + element[0] + ');" type="button" class="btn btn-sm btn-success btn-labeled">' +
            					  '<span class="btn-label btn-label-left"><i class="fa fa-edit"></i></span>Edit</button>&nbsp;&nbsp;';
                            buttons += " <button type='button' class='btn btn-danger' onclick='DeleteItem(" + element[0] + ")'><i class='fa fa-remove'></i>Delete</button>";  	                    					 
            		table += "<tr><td>" + (++count) + 
            				 "</td><td>" + element[1] + 
            				 "</td><td>" + element[2] +
            				 "</td><td>" + (element[3] === true ? 'Yes' : 'No') +
            				 "</td><td>" + element[4] +
            				 "</td><td>" + buttons + "</td></tr>";
                });
        	}
        	else
        		table += "<tr colspan='5'><td>No budget year created yet.</td></tr>";
        	
        	table += "</tbody></table>";
        	
        	document.getElementById("main-table").innerHTML = table;
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showEditObj(requestTypeID) {
    $('#obj-id').val(requestTypeID);
    data = {};
    data['option'] = $('#selectx').val();
    data['id'] = requestTypeID;
	
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetYearServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		document.getElementById("obj-ename").value = element[1];
        		
        		if(element[3] === true)
        			$('#obj-ecurrent').prop('checked', true);
        		else
        			$('#obj-ecurrent').prop('checked', false);
            });
        	
        	EditItem();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}
 
function checkEnter(e) { //e is event object passed from function invocation
    var characterCode; //literal character code will be stored in this variable

    if (e && e.which) { //if which property of event object is supported (NN4)
        e = e;
        characterCode = e.which //character code is contained in NN4's which property
    } else {
        e = event;
        characterCode = e.keyCode; //character code is contained in IE's keyCode property
    }

    if (characterCode === 13) { //if generated character code is equal to ascii 13 (if enter key)
        //checkLogin();
        return false;
    } else {
        return true;
    }
}


function DeleteItem(arg) {
    $('#deleteModal').iziModal('open');
    window.deleteId = arg;
}

function cancelDelete() {
    $('#deleteModal').iziModal('close');
    window.deleteId = 0;
}

function FinishDelete() {
    var id = window.deleteId;
    $.ajax({
        type: "GET",
        url: "/BudgetPlanningPortals/BudgetYearServlet",
        data: {option: "delete", id: id},
        dataType: "text",
        cache: false,
        async: false,
        success: function (data) {
            $('#deleteModal').iziModal('close');
            if (data.indexOf("DELETED")!== -1) {
                toastr["success"]("Budget Year deleted Successfull!", "Delete Successfull");
                getObjects();
            }
        },
        error: function (a, b, c) {
            toastr["error"]("Budget Year delete Failed!", "No record deleted!");
        }
    });
}

