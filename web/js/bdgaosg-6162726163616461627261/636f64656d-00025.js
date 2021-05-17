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
	//Get Notification Types
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
        url: $('#site-url').val() + "/NotificationTypesServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#insertedx').val()) !== -1) {
            	getObjects();
            	
            	ReturnToList();
            	
            	// Notification
                toastr["success"]("Notification type created successfully!", "Created");
            }
            else
                toastr["error"]("Notification type creation failed!", "Failure");
            
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
        url: $('#site-url').val() + "/NotificationTypesServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#updatedx').val()) !== -1) {
            	CloseEdit();
            	
            	getObjects();
            	
            	// Notification
                toastr["success"]("Notification type updated successfully!", "Updated");
            }
            else
            	// Notification
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
        url: $('#site-url').val() + "/NotificationTypesServlet",
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
        		table += "<tr colspan='5'><td>No notification type created yet.</td></tr>";
        	
        	table += "</tbody></table>";
        	
        	document.getElementById("main-table").innerHTML = table;
        	
        	$('#main-tablex').DataTable();
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
        url: $('#site-url').val() + "/NotificationTypesServlet",
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