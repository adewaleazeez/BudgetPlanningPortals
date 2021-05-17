var all_roles = "";
var all_users = "";
var all_mdas = "";
var users_mdas = [];
var all_user_roles = "";
var initialMDA = -1;
var messageType = 1;

//for new
var i = 1;

//check login status, access rights and get menus
checkLogin();

//convert text box
CKEDITOR.replace('message-text');

$(document).ready(function () {	
	checkSMSBalance();
	
	//get all messages
	getObjects();
	
	//get MDAs
	getMDAs(i - 1);
	
	//get user roles
	getAllUserRoles();
	
	//Get attendees
	getPossibleAttendees(i - 1, 0);
	
	//ATTENDEES
    $("#add_row").click(function () {
    	$('#addr' + i).html("<td>" + (i + 1) + "</td><td><select class='form-control custom_select' id='mmda-id" + i + "'></select></td><td><div id='select_mattendee" + i + "'></div></td>");

        $('#tab_logic').append('<tr id="addr' + (i + 1) + '"></tr>');
        i++;
        
        //get data
        getMDAs(i - 1);
    });
    
    $("#delete_row").click(function () {
        if (i > 1) {
            $("#addr" + (i - 1)).html('');
            i--;
        }
    });
    
    $('#all-users').change(function() {
    	//un-check others
    	$('#specific-role').prop('checked', false);
    	$('#specific-users').prop('checked', false);
    	$('#specific-mda').prop('checked', false);
    	
    	$('#divAttendees').hide();
    	$('#divRolesOnly').hide();
    	$('#divMDAOnly').hide();
    });
    
    $('#specific-role').change(function() {
    	//un-check others
    	$('#all-users').prop('checked', false);
    	$('#specific-users').prop('checked', false);
    	$('#specific-mda').prop('checked', false);
    	
    	$('#divAttendees').hide();
    	$('#divRolesOnly').show();
    	$('#divMDAOnly').hide();
    });
    
    $('#specific-users').change(function() {
    	//un-check others
    	$('#all-users').prop('checked', false);
    	$('#specific-role').prop('checked', false);
    	$('#specific-mda').prop('checked', false);
    	
    	$('#divAttendees').show();
    	$('#divRolesOnly').hide();
    	$('#divMDAOnly').hide();
    });
    
    $('#specific-mda').change(function() {
    	//un-check others
    	$('#all-users').prop('checked', false);
    	$('#specific-users').prop('checked', false);
    	$('#specific-role').prop('checked', false);
    	
    	$('#divAttendees').hide();
    	$('#divRolesOnly').hide();
    	$('#divMDAOnly').show();
    });
    
    //Erase JS from inspection
	document.getElementById('erasable').innerHTML = "";
	unloadJS('erasable');
});

function unloadJS(scriptName) {
	var js = document.getElementById(scriptName);
	js.parentNode.removeChild(js);
}

function setCustomSelectOn() {
	try {
        $('.custom_select').select2();
    } catch (e) {}
}

function getObjects() {
	data = {};
    data['option'] = $('#select-all').val();
    
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MessagesServlet",
        data: data,
        dataType: "json",
        success: function(response) {
        	var table = "<table id='main-tablex' class='table table-clean'>" +
	    					"<thead>" +
	        					"<tr>" +
	        					    "<th></th>" +
	                                "<th><b>Subject/Title</b></th>" +
	                                "<th><b>Message</b></th>" +
	                                "<th><b>Recipient</b></th>" +
	                                "<th><b>Type</b></th>" +
	                                "<th><b>Date Created</b></th>" +
	                            "</tr>" +
	                        "</thead>" +
    	            		"<tbody>";
        
        	if(response.length > 0) {
        		var i = 1;
        		
            	$.each(response, function(index, element) {
            		table += "<tr><td>" + i + 
            				 "</td><td>" + (element[1] == '' ? '-' : element[1]) + 
            				 "</td><td>" + element[2] + 
            				 "</td><td>" + element[3] + 
            				 "</td><td>" + (element[4] == 1 ? 'Email' : 'SMS') + 
            				 "</td><td>" + element[5] + "</td></tr>";
            		
            		i++;
                });
        	}
        	else
        		table += "<tr colspan='5'><td>No messages created yet.</td></tr>";
        	
        	table += "</tbody></table>";
        	
        	document.getElementById("main-table").innerHTML = table;
        	
        	$('#main-tablex').DataTable();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function getAllUserRoles() {
	if(all_user_roles == '') {
		data = {};
	    data['option'] = $('#select-all').val();
		
		$.ajax({
	        type: "GET",
	        url: $('#site-url').val() + "/RolesServlet",
	        data: data,
	        dataType: "json",
	        cache: false,
	        success: function(response) {
	        	var select_options = "";
	        	
	        	$.each(response, function(index, element) {
	        		select_options += "<option value='" + element[0] + "'>" + element[1] + "</option>";
	            });
	        	
	        	all_user_roles = select_options;
	        	
	        	document.getElementById("role-only").innerHTML = select_options;
	        	
	        	setCustomSelectOn();
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        	//console.log(textStatus + " " + errorThrown);
	        }
	    });
	}
	else
		document.getElementById("role-only").innerHTML = all_user_roles;
}

function getMDAs(id) {
	if(id == 0) {
		$.ajax({
            type: "GET",
            url: $('#site-url').val() + "/MdaServlet",
            data: {option: $('#select-all').val()},
            dataType: "json",
            cache: false,
            success: function(response) {
            	var select_options = "";
            	
            	$.each(response, function(index, element) {
            		select_options += "<option value='" + element[0] + "'>" + element[1] + "</option>";
            		
            		if(index == 0)
            			initialMDA = element[0];
                });
            	
            	all_mdas = select_options;
            	
            	document.getElementById("mmda-id0").innerHTML = select_options;
            	document.getElementById("mda-only").innerHTML = select_options;
            	
            	$('#mmda-id0').change(function() {
            		getUsersByMDA(0, $('#mmda-id0 option:selected').val());
            	});
            	
            	getUsersByMDA(0, initialMDA);
            	
            	setCustomSelectOn();
	        },
            error: function(jqXHR, textStatus, errorThrown) {
            	//console.log(textStatus + " " + errorThrown);
            }
        });
	}
	else {
		document.getElementById("mmda-id" + id).innerHTML = all_mdas;
		
		$('#mmda-id' + id).change(function() {
    		var xId = $(this).attr('id');
    		
    		getUsersByMDA(xId.substring(7), $('#' + xId + ' option:selected').val());
    	});
		
		getUsersByMDA(id, initialMDA);
		
		setCustomSelectOn();
	}
}

function getUsersByMDA(position, mdaID) {
	if(mdaID in users_mdas) {
		var select_options = users_mdas[mdaID];
		
		document.getElementById("select_mattendee" + position).innerHTML = select_options;
		
		setCustomSelectOn();
	}
	else {
		data = {};
	    data['option'] = $('#select-all-mdax').val();
	    data['mdaID'] = mdaID;
	    
		$.ajax({
	        type: "POST",
	        url: $('#site-url').val() + "/UserServlet",
	        data: data,
	        dataType: "json",
	        cache: false,
	        success: function(response) {
	        	var select_options = "";
	        	
	        	select_options = "<select class='form-control custom_select' id='meeting-attendant-id[]'>";
	        	
	        	$.each(response, function(index, element) {
	        		select_options += "<option value='" + element[0] + "'>" + element[1] + " " + element[2] + " (" + element[3] + ")</option>";
	            });
	        	
	        	select_options += "</select>";
	        	
	        	users_mdas[mdaID] = select_options;
	        	
	        	document.getElementById("select_mattendee" + position).innerHTML = select_options;
	        	
	        	setCustomSelectOn();
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        	//console.log(textStatus + " " + errorThrown);
	        }
	    });
	}
}

function getPossibleAttendees(id, isEdit) {
	if(id == 0) {
		$.ajax({
            type: "GET",
            url: $('#site-url').val() + "/UserServlet",
            data: {option: $('#select-all').val()},
            dataType: "json",
            cache: false,
            success: function(response) {
            	var select_options = "";
            	
            	if(isEdit == 0)
            		select_options = "<select class='form-control custom_select' id='meeting-attendant-id[]'>";
        		else
        			select_options = "<select class='form-control custom_select' id='emeeting-attendant-id[]'>";
            	
        		var userID = $('#v5er-idx').val();
            	$.each(response, function(index, element) {
            		select_options += "<option value='" + element[0] + "'>" + element[1] + " " + element[2] + " (" + element[3] + ")</option>";
                });
            	
            	select_options += "</select>";
            	
            	all_users = select_options;
            	
            	if(isEdit == 0)
            		document.getElementById("select_mattendee0").innerHTML = all_users;
            	else
            		document.getElementById("eselect_mattendee0").innerHTML = all_users;
            	
            	setCustomSelectOn();
	        },
            error: function(jqXHR, textStatus, errorThrown) {
            	//console.log(textStatus + " " + errorThrown);
            }
        });
	}
	else {
		if(isEdit == 0)
			document.getElementById("select_mattendee" + id).innerHTML = all_users;
		else
			document.getElementById("eselect_mattendee" + id).innerHTML = all_users;
	}
}

function setMessageType(type) {
	messageType = type;
}

function resetForm() {
	var instance = CKEDITOR.instances['message-text'];
	instance.setData('');
	
	document.getElementById("message-subject").value = '';
	
	document.getElementById("smessage-text").value = '';
	
	$('#specific-role').prop('checked', false);
	$('#specific-users').prop('checked', false);
	$('#specific-mda').prop('checked', false);
	$('#all-users').prop('checked', false);
	
	$('#divAttendees').hide();
	$('#divRolesOnly').hide();
	$('#divMDAOnly').hide();
}

function checkSMSBalance() {
	data = {};
    data['option'] = $('#getbalancex').val();
    
	$.ajax({
		type: "POST",
        url: $('#site-url').val() + "/MessagesServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {
        	$('#sms-balance').html(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function sendMessage() {
	var instance = CKEDITOR.instances['message-text'];
	var msg_text = instance.getData();
	
	if(messageType == 1) {
		if(document.getElementById("message-subject").value == '' || msg_text == '') {
			toastr["error"]("You must enter a subject and message before proceeding.", "Sending Failed");
			return;
		}
	}
	else {
		if(document.getElementById("smessage-text").value == '')
			toastr["error"]("You must enter a text message before proceeding.", "Sending Failed");
	}
	
	ShowLoading();
	
	var attendants = [];
	var roles = [];
	
	//Check options selected
	if($('#all-users:checked').val() != 'on' && $('#specific-role:checked').val() != 'on' && $('#specific-mda:checked').val() != 'on') {
		$('select[id^="meeting-attendant-id"]').each(function() { 
			if(this.value != '')
				attendants.push(this.value);
		});
		
		if(attendants.length <= 0) {
			toastr["error"]("You must select at least one recipient or option before proceeding.", "Messenger");
			StopLoading();
			return;
		}
	}
	
	var message = '';
	var subject = '';
	if(messageType == 1)
		message = msg_text;
	else
		message = document.getElementById("smessage-text").value;
	
	//Create message
	//handle other attendants
    if($('#all-users:checked').val() != 'on' && $('#specific-role:checked').val() != 'on' && $('#specific-mda:checked').val() != 'on') {
    	for(x = 0; x < attendants.length; x++) {
        	data = {};
		    data['option'] = $('#insertx').val();
		    data['message'] = message;
		    data['userID'] = attendants[x];
		    data['messageTypeID'] = messageType;
		    
		    if(messageType == 1)
		    	data['title'] = document.getElementById("message-subject").value;
            
            $.ajax({
                type: "POST",
                url: $('#site-url').val() + "/MessagesServlet",
                data: data,
                dataType: "text",
                cache: false,
                async: false,
                success: function(response) {
                	StopLoading();
                	
                	getObjects();
                	
                	ReturnToList();
                	
                	//clean up
                	resetForm();
                	
                	//show notification
                	toastr["success"]('Message sent successfully!', 'Messenger');
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		        	StopLoading();
		        	
		        	ReturnToList();
		        	
		        	//clean up
                	resetForm();
		        	
		        	toastr["error"]('Message could not be sent, please check and try again!', 'Messenger');
		        	
                	//console.log(textStatus + " " + errorThrown);
                }
            });
    	}
    }
    else {
    	data = {};
    	data['option'] = $('#insertx').val();
    	data['message'] = message;
    	data['userID'] = -1;
    	data['messageTypeID'] = messageType;
    	
    	if(messageType == 1)
    		data['title'] = document.getElementById("message-subject").value;
    	
    	if($('#specific-role:checked').val() == 'on')
    		data['specificUserRoleID'] = $('#role-only').val();                	
    	else if($('#specific-mda:checked').val() == 'on')
    		data['specificMDAID'] = $('#mda-only').val();
    	
    	$.ajax({
            type: "POST",
            url: $('#site-url').val() + "/MessagesServlet",
            data: data,
            dataType: "text",
            cache: false,
            async: false,
            success: function(response) {
            	StopLoading();
            	
            	getObjects();
            	
            	ReturnToList();
            	
            	//clean up
            	resetForm();
            	
            	//show notification
            	toastr["success"]('Message sent successfully!', 'Messenger');
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        	StopLoading();
	        	
	        	ReturnToList();
	        	
	        	//clean up
            	resetForm();
	        	
	        	toastr["error"]('Message cold be sent, please check and try again!', 'Messenger');
	        	
            	//console.log(textStatus + " " + errorThrown);
            }
        });
    }
}