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
	//Get Request Agent Types
	getObjects();
	
	document.getElementById('erasable').innerHTML = "";
});

function createObj() {
	ShowLoading();
	
	//Create budget year
    data = {};
    data['option'] = $('#insertx').val();
    data['name'] = document.getElementById("obj-name").value;
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/RequestAgentTypesServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#insertedx').val()) !== -1) {
            	getObjects();
            	
            	ReturnToList();
            	
            	// Notification
                toastr["success"]("Request agent type created successfully!", "Created");
            }
            else
                toastr["error"]("Item cration failed!", "Failure");
            
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
	
	//Update request type
    data = {};
    data['option'] = $('#updatex').val();
    data['id'] = document.getElementById("obj-id").value;
    data['name'] = document.getElementById("obj-ename").value;
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/RequestAgentTypesServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#updatedx').val()) !== -1) {
            	CloseEdit();
            	
            	getObjects();
            	
            	// Notification
                toastr["success"]("Request agent type updated successfully!", "Updated");
            }
            else
                toastr["error"]("Item edit failed!", "Failure");
            
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
        url: $('#site-url').val() + "/RequestAgentTypesServlet",
        data: data,
        dataType: "json",
        success: function(response) {
        	//alert(response);
        	var table = "<table id='main-tablex' class='table table-clean'>" +
	    					"<thead>" +
	        					"<tr>" +
	                                "<th><b>Name</b></th>" +
	                                "<th><b>Date Created</b></th>" +
	                                "<th><b>Action</b></th>" +
	                            "</tr>" +
	                        "</thead>" +
    	            		"<tbody>";
        
        	if(response.length > 0) {
            	$.each(response, function(index, element) {
            		var buttons = '<button onclick="showEditObj(' + element[0] + ');" type="button" class="btn btn-sm btn-success btn-labeled">' +
            					  '<span class="btn-label btn-label-left"><i class="fa fa-edit"></i></span>Edit</button>&nbsp;&nbsp;';
            			 
            		table += "<tr><td>" + element[1] + 
            				 "</td><td>" + element[2] + 
            				 "</td><td>" + buttons + "</td></tr>";
                });
        	}
        	else
        		table += "<tr colspan='5'><td>No request agent type created yet.</td></tr>";
        	
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
        url: $('#site-url').val() + "/RequestAgentTypesServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		document.getElementById("obj-ename").value = element[1];
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