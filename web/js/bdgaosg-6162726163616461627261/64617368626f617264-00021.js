var all_roles = "";
var all_users = "";
var all_agents_types = "";
var all_mdas = "";
var users_mdas = [];
var all_user_roles = "";
var initialMDA = -1;

//for new
var i = 1;
var j = 1;

//for edit
var x = 1;
var y = 1;
var finalx = 0;
var finaly = 0;

//check login status, access rights and get menus
checkLogin();

$(document).ready(function () {
	//for adding zero prefixes to numbers (date manipulation)
	Number.prototype.AddZero = function(b,c){
        var  l = (String(b|| 10).length - String(this).length) + 1;
        return l > 0 ? new Array(l).join(c || '0') + this : this;
    }
	
	//get related meetings
	getRelatedMeetings();
	
	//get timetable activities
	getBudgetTimetableActivities();
	
	//get MDAs
	getMDAs(i - 1);
	
	//get user roles
	getAllUserRoles();
	
	//Get roles
	getMeetingRoles(i - 1, 0);
	
	//Get attendees
	getPossibleAttendees(i - 1, 0);
	
	//Get agenda types
	getAgendaTypes(i - 1, 0);
	
	//ATTENDEES
    $("#add_row").click(function () {
    	$('#addr' + i).html("<td>" + (i + 1) + "</td><td><select class='form-control custom_select' id='mmda-id" + i + "'></select></td><td><div id='select_mattendee" + i + "'></div></td><td><div id='select_mrole" + i + "'></div></td>");

        $('#tab_logic').append('<tr id="addr' + (i + 1) + '"></tr>');
        i++;
        
        //get data
        getMDAs(i - 1);
        getMeetingRoles(i - 1, 0);
    });
    
    $("#delete_row").click(function () {
        if (i > 1) {
            $("#addr" + (i - 1)).html('');
            i--;
        }
    });
    
    //AGENDA TYPES
    $("#add_rowe").click(function () {
    	$('#addre' + j).html("<td>" + (j + 1) + "</td><td><div id='select_magendatype" + j + "'></div></td><td><textarea rows='2' required class='form-control' id='magenda-details[]' placeholder='Enter Details'></textarea></td>");

        $('#tab_logice').append('<tr id="addre' + (j + 1) + '"></tr>');
        j++;
        
        //get data
        getAgendaTypes(j - 1, 0);
    });
    
    $("#delete_rowe").click(function () {
        if (j > 1) {
            $("#addre" + (j - 1)).html('');
            j--;
        }
    });
    
  	//ATTENDEES EDIT
    $("#eadd_row").click(function () {
    	$('#eaddr' + x).html("<td>" + (x + 1) + "</td><td><div id='eselect_mattendee" + x + "'></div></td><td><div id='eselect_mrole" + x + "'></div></td>");

        $('#etab_logic').append('<tr id="eaddr' + (x + 1) + '"></tr>');
        x++;
        
        //get data
        getMeetingRoles(x - 1, 1);
        getPossibleAttendees(x - 1, 1);
    });
    
    $("#edelete_row").click(function () {
        if (x > finalx) {
            $("#eaddr" + (x - 1)).html('');
            x--;
        }
    });
    
  	//AGENDA TYPES
    $("#eadd_rowe").click(function () {
    	$('#eaddre' + y).html("<td>" + (y + 1) + "</td><td><div id='eselect_magendatype" + y + "'></div></td><td><textarea rows='2' required class='form-control' id='emagenda-details[]' placeholder='Enter Details'></textarea></td>");

        $('#etab_logice').append('<tr id="eaddre' + (y + 1) + '"></tr>');
        y++;
        
        //get data
        getAgendaTypes(y - 1, 1);
    });
    
    $("#edelete_rowe").click(function () {
        if (y > finaly) {
            $("#eaddre" + (y - 1)).html('');
            y--;
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

function getRelatedMeetings() {
	data = {};
    data['option'] = $('#select-all').val();
    data['userID'] = $('#v5er-idx').val();
    
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MeetingDetailsServlet",
        data: data,
        dataType: "json",
        success: function(response) {
        	var table = "<table id='main-tablex' class='table table-clean'>" +
	    					"<thead>" +
	        					"<tr>" +
	        						"<th></th>" +
	                                "<th><b>Meeting Title</b></th>" +
	                                "<th><b>Venue</b></th>" +
	                                "<th><b>Budget Activity</b></th>" +
	                                "<th><b>Date</b></th>" +
	                                "<th><b>Action</b></th>" +
	                            "</tr>" +
	                        "</thead>" +
    	            		"<tbody>";
        
        	if(response.length > 0) {
            	$.each(response, function(index, element) {
            		var buttons = '<button onclick="showMeetingDetails(' + element[0] + ');" type="button" class="btn btn-sm btn-success btn-labeled">' +
            					  '<span class="btn-label btn-label-left"><i class="fa fa-eye"></i></span>View Details</button>';
            		
            		if(element[4] == 4) {
                		buttons += '&nbsp;&nbsp;<button title="Edit Meeting" onclick="showEditMeeting(' + element[0] + ');" type="button" class="btn btn-sm btn-info">' +
   					 			   '<span class="btn-label"><i class="fa fa-edit"></i></span></button>';
            		}
            					 
            		table += "<tr><td>" + (index + 1) + 
            				 "</td><td>" + element[1] +
            				 "</td><td>" + element[2] + 
            				 "</td><td>" + element[3] + 
            				 "</td><td>" + element[4] +
            				 "</td><td>" + buttons + "</td></tr>";
                });
        	}
        	else
        		table += "<tr colspan='5'><td>No budget timetable created yet.</td></tr>";
        	
        	table += "</tbody></table>";
        	
        	document.getElementById("main-table").innerHTML = table;
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showMeetingDetails(meetingID) {
	$('#chosen-meeting-id').val(meetingID);
	
	//for edit
	var xx = 0;
	var yy = 0;
	
	data = {};
    data['option'] = $('#selectx').val();
    data['id'] = meetingID;
    data['userID'] = $('#v5er-idx').val();
    
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MeetingDetailsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
            	document.getElementById("vmeeting-title").innerHTML = element[1];
        		document.getElementById("vmeeting-venue").innerHTML = element[2];
        		document.getElementById("vmeeting-timetable").innerHTML = element[3];
        		document.getElementById("vmeeting-date").innerHTML = element[4];
        		
        		//meeting role of current user
        		$('#chosen-meeting-is-creator').val(element[5]);
        	});
        	
        	//Get Meeting Attendees
        	data = {};
		    data['option'] = $('#select-all').val();
		    data['meetingID'] = meetingID;
		    
        	$.ajax({
                type: "GET",
                url: $('#site-url').val() + "/MeetingAttendantsServlet",
                data: data,
                dataType: "json",
                cache: false,
                success: function(response) {
	            	$.each(response, function(index, element) {
	            		$('#vaddr' + xx).html("<td>" + (xx + 1) + "</td><td><label>" + element[1] + " " + element[2] + " (" + element[3] + ", " + element[4] + ")</label></td><td><label>" + element[5] + "</label></td>");

	                    $('#vtab_logic').append('<tr id="vaddr' + (xx + 1) + '"></tr>');
	                    xx++;
	                });
		        },
                error: function(jqXHR, textStatus, errorThrown) {
                	//console.log(textStatus + " " + errorThrown);
                }
            });
        	
        	//Get Meeting Agenda
        	data = {};
		    data['option'] = $('#select-all').val();
		    data['meetingID'] = meetingID;
		    
        	$.ajax({
                type: "GET",
                url: $('#site-url').val() + "/MeetingAgendaDetailsServlet",
                data: data,
                dataType: "json",
                cache: false,
                success: function(response) {
	            	$.each(response, function(index, element) {
	            		$('#vaddre' + yy).html("<td>" + (yy + 1) + "</td><td><label>" + element[2] + "</label></td><td><label>" + element[1] + "</label></td>");

	                    $('#vtab_logice').append('<tr id="vaddre' + (yy + 1) + '"></tr>');
	                    yy++;
	                });
		        },
                error: function(jqXHR, textStatus, errorThrown) {
                	//console.log(textStatus + " " + errorThrown);
                }
            });
        	
        	$('#divViewMeeting').modal('show');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function getBudgetTimetableActivities() {
	data = {};
    data['option'] = $('#select-all').val();
    data['budgetTimetableID'] = $('#current-timetable-id').val();
	
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetTimetableActivitiesServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	var select_options = "<select class='form-control custom_select' id='meeting-budget-timetable-activity'>";
        	
        	$.each(response, function(index, element) {
        		select_options += "<option value='" + element[0] + "'>" + element[1] + "</option>";
            });
        	
        	select_options += "</select>";
        	
        	document.getElementById("select_activity1").innerHTML = select_options;
        	
        	setCustomSelectOn();
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

function getAgendaTypes(id, isEdit) {
	if(id == 0) {
		$.ajax({
            type: "GET",
            url: $('#site-url').val() + "/MeetingAgendaServlet",
            data: {option: $('#select-all').val()},
            dataType: "json",
            cache: false,
            success: function(response) {
            	var select_options = "";
            	
            	if(isEdit == 0)
            		select_options = "<select class='form-control custom_select' id='magenda-type[]'>";
            	else
            		select_options = "<select class='form-control custom_select' id='emagenda-type[]'>";
            	
            	$.each(response, function(index, element) {
            		select_options += "<option value='" + element[0] + "'>" + element[1] + "</option>";
                });
            	
            	select_options += "</select>";
            	
            	all_agents_types = select_options;
            	
            	if(isEdit == 0)
            		document.getElementById("select_magendatype0").innerHTML = select_options;
            	else
            		document.getElementById("eselect_magendatype0").innerHTML = select_options;
            	
            	setCustomSelectOn();
	        },
            error: function(jqXHR, textStatus, errorThrown) {
            	//console.log(textStatus + " " + errorThrown);
            }
        });
	}
	else {
		if(isEdit == 0)
			document.getElementById("select_magendatype" + id).innerHTML = all_agents_types;
		else
			document.getElementById("eselect_magendatype" + id).innerHTML = all_agents_types;
	}
}

function getMeetingRoles(id, isEdit) {
	if(id == 0) {
		$.ajax({
            type: "GET",
            url: $('#site-url').val() + "/MeetingRolesServlet",
            data: {option: $('#select-all').val()},
            dataType: "json",
            cache: false,
            success: function(response) {
            	var select_options = "";
            	
            	if(isEdit == 0)
                	select_options = "<select class='form-control custom_select' id='meeting-attendant-role[]'>";
	            else
					select_options = "<select class='form-control custom_select' id='emeeting-attendant-role[]'>";
	            		                    	
            	$.each(response, function(index, element) {
            		select_options += "<option value='" + element[0] + "'>" + element[1] + "</option>";
                });
            	
            	select_options += "</select>";
            	
            	all_roles = select_options;
            	
            	if(isEdit == 0)
            		document.getElementById("select_mrole0").innerHTML = all_roles;
            	else
            		document.getElementById("eselect_mrole0").innerHTML = all_roles;
            	
            	setCustomSelectOn();
	        },
            error: function(jqXHR, textStatus, errorThrown) {
            	//console.log(textStatus + " " + errorThrown);
            }
        });
	}
	else {
		if(isEdit == 0)
			document.getElementById("select_mrole" + id).innerHTML = all_roles;
		else
			document.getElementById("eselect_mrole" + id).innerHTML = all_roles;
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

function createMeeting() {
	if(document.getElementById("meeting-date").value == '') {
		alert('You must select a meeting date before proceeeding.');
		StopLoading();
		return;
	}
	
	if(document.getElementById("meeting-venue").value == '') {
		alert('You must enter a meeting venue before proceeeding.');
		StopLoading();
		return;
	}
	
	if(document.getElementById("meeting-name").value == '') {
		alert('You must enter a meeting title before proceeeding.');
		StopLoading();
		return;
	}
	
	if(document.getElementById("meeting-budget-timetable-activity").value == '') {
		alert('You must select budget timetable activity before proceeeding.');
		StopLoading();
		return;
	}
		
	ShowLoading();
	
	var attendants = [];
	var roles = [];
	var agenda_types = [];
	var agenda_details = [];
	
	$('select[id^="magenda-type"]').each(function(){ 
		agenda_types.push(this.value);
	});
	
	$('textarea[id^="magenda-details"]').each(function(){ 
		agenda_details.push(this.value);
	});
	
	//Check options selected
	if($('#all-users:checked').val() != 'on' && $('#specific-role:checked').val() != 'on' && $('#specific-mda:checked').val() != 'on') {
		$('select[id^="meeting-attendant-id"]').each(function() { 
			if(this.value != '')
				attendants.push(this.value);
		});
		
		$('select[id^="meeting-attendant-role"]').each(function() { 
			roles.push(this.value);
		});
		
		if(attendants.length <= 0 || roles.length <= 0) {
			alert('You must enter at least one attendant with their respective roles in the meeting.');
			StopLoading();
			return;
		}
		
		if(attendants.length != roles.length) {
			alert('You must select a Role and MDA for every attendant.');
			StopLoading();
			return;
		}
	}
	
	if(agenda_details.length <= 0 || agenda_types.length <= 0) {
		alert('You must enter at least one agenda for this meeting.');
		StopLoading();
		return;
	}
	
	if(agenda_details.length != agenda_types.length) {
		alert('You must select an agenda type for every agenda detail.');
		StopLoading();
		return;
	}
	
	//Create meeting
    data = {};
    data['option'] = $('#insertx').val();
    data['name'] = document.getElementById("meeting-name").value;
    data['venue'] = document.getElementById("meeting-venue").value;
    data['budgetTimetableActivityID'] = document.getElementById("meeting-budget-timetable-activity").value;
    data['meetingDate'] = document.getElementById("meeting-date").value;
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/MeetingDetailsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {
        	var meetingID = parseInt(response);
        	
            if(meetingID >= 1) {
            	//handle agenda
            	data = {};
    		    data['option'] = $('#insertx').val();
    		    data['agendaDetailNames'] = agenda_details;
    		    data['agendaIDs'] = agenda_types;
    		    data['meetingID'] = meetingID;
                
                $.ajax({
                    type: "POST",
                    url: $('#site-url').val() + "/MeetingAgendaDetailsServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(response) {
                    	
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                    	//console.log(textStatus + " " + errorThrown);
                    }
                });
            	
            	//handle project creator
            	data = {};
    		    data['option'] = $('#insertx').val();
    		    data['meetingRoleID'] = 4;
    		    data['userID'] = $('#v5er-idx').val();
    		    data['meetingID'] = meetingID;
                
                $.ajax({
                    type: "POST",
                    url: $('#site-url').val() + "/MeetingAttendantsServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(response) {
                    	
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                    	//console.log(textStatus + " " + errorThrown);
                    }
                });		            
                
            	//handle other attendants
                if($('#all-users:checked').val() != 'on' && $('#specific-role:checked').val() != 'on' && $('#specific-mda:checked').val() != 'on') {
                	data = {};
	    		    data['option'] = $('#insertx').val();
	    		    data['meetingRoles'] = roles;
	    		    data['userIDs'] = attendants;
	    		    data['meetingID'] = meetingID;
	                
	                $.ajax({
	                    type: "POST",
	                    url: $('#site-url').val() + "/MeetingAttendantsServlet",
	                    data: data,
	                    dataType: "text",
	                    cache: false,
	                    async: false,
	                    success: function(response) {
	                    	
				        },
				        error: function(jqXHR, textStatus, errorThrown) {
	                    	//console.log(textStatus + " " + errorThrown);
	                    }
	                });
                }
                else {
                	data = {};
                	data['option'] = $('#insertx').val();
                	data['meetingID'] = meetingID;
                	data['userID'] = -1;
                	
                	if($('#specific-role:checked').val() == 'on')
                		data['specificUserRoleID'] = $('#role-only').val();                	
                	else if($('#specific-mda:checked').val() == 'on')
                		data['specificMDAID'] = $('#mda-only').val();
                	
                	$.ajax({
	                    type: "POST",
	                    url: $('#site-url').val() + "/MeetingAttendantsServlet",
	                    data: data,
	                    dataType: "text",
	                    cache: false,
	                    async: false,
	                    success: function(response) {
	                    	
				        },
				        error: function(jqXHR, textStatus, errorThrown) {
	                    	//console.log(textStatus + " " + errorThrown);
	                    }
	                });
                }
            	
            	//show notification and return to the list
            	toastr["success"]('Meeting created successfully!', 'Meeting Creation');
        		
        		getRelatedMeetings();
        		
        		ReturnToList();
            }
            else
            	toastr["error"]('Meeting creation failed!', 'Meeting Creation');
            
            StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	StopLoading();
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showEditMeeting(meetingID) {
	$('#chosen-meeting-id').val(meetingID);
	
	//for edit
	x = 0;
	y = 0;
	
	//Get meeting details
	data = {};
    data['option'] = $('#selectx').val();
    data['id'] = meetingID;
    data['userID'] = $('#v5er-idx').val();
	
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MeetingDetailsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		document.getElementById("emeeting-name").value = element[1];
        		document.getElementById("emeeting-venue").value = element[2];
        		
        		var formattedDate = new Date(element[4]);
        		var newDateLocal = formattedDate.getFullYear() + "-" + (formattedDate.getMonth() + 1).AddZero() + "-" + 
        						   formattedDate.getDate().AddZero() + "T" + formattedDate.getHours().AddZero() + ":" + 
        						   formattedDate.getMinutes().AddZero() + ":" + formattedDate.getSeconds().AddZero() + "." + 
        						   formattedDate.getMilliseconds();
        		
        		document.getElementById("emeeting-date").value = newDateLocal;
            });
        	
        	EditItem();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
	
	//Get Meeting Attendees
	data = {};
    data['option'] = $('#select-all').val();
    data['meetingID'] = meetingID;
    
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MeetingAttendantsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		$('#eaddr' + x).html("<td>" + (x + 1) + "</td><td><label>" + element[1] + " " + element[2] + " (" + element[3] + ", " + element[4] + ")</label></td><td><label>" + element[5] + "</label></td>");

                $('#etab_logic').append('<tr id="eaddr' + (x + 1) + '"></tr>');
                x++;
            });
        	
        	finalx = x;
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
	
	//Get Meeting Agenda
	data = {};
    data['option'] = $('#select-all').val();
    data['meetingID'] = meetingID;
    
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MeetingAgendaDetailsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		$('#eaddre' + y).html("<td>" + (y + 1) + "</td><td><label>" + element[2] + "</label></td><td><label>" + element[1] + "</label></td>");

                $('#etab_logice').append('<tr id="eaddre' + (y + 1) + '"></tr>');
                y++;
            });
        	
        	finaly = y;
        	
        	EditItem();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function editMeeting() {
	ShowLoading();
	
	var meetingID = document.getElementById("chosen-meeting-id").value;
	
	//Update meeting
    data = {};
    data['option'] = $('#updatex').val();
    data['id'] = meetingID;
    data['name'] = document.getElementById("emeeting-name").value;
    data['venue'] = document.getElementById("emeeting-venue").value;
    data['meetingDate'] = document.getElementById("emeeting-date").value;
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/MeetingDetailsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#updatedx').val()) !== -1) {
            	var attendants = [];
            	var roles = [];
            	var agenda_types = [];
            	var agenda_details = [];
            	
            	$('select[id^="emeeting-attendant-id"]').each(function(){ 
            		attendants.push(this.value);
            	});
            	
            	$('select[id^="emeeting-attendant-role"]').each(function(){ 
            		roles.push(this.value);
            	});
            	
            	$('select[id^="emagenda-type"]').each(function(){ 
            		agenda_types.push(this.value);
            	});
            	
            	$('textarea[id^="emagenda-details"]').each(function(){ 
            		agenda_details.push(this.value);
            	});
            	
        		//handle attendants
            	data = {};
    		    data['option'] = $('#insertx').val();
    		    data['meetingRoles'] = roles;
    		    data['userIDs'] = attendants;
    		    data['meetingID'] = meetingID;
                
                $.ajax({
                    type: "POST",
                    url: $('#site-url').val() + "/MeetingAttendantsServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(response) {
                    	
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                    	//console.log(textStatus + " " + errorThrown);
                    }
                });
            	
            	//handle agenda
            	data = {};
    		    data['option'] = $('#insertx').val();
    		    data['agendaDetailNames'] = agenda_details;
    		    data['agendaIDs'] = agenda_types;
    		    data['meetingID'] = meetingID;
                
                $.ajax({
                    type: "POST",
                    url: $('#site-url').val() + "/MeetingAgendaDetailsServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    success: function(response) {
                    	
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                    	//console.log(textStatus + " " + errorThrown);
                    }
                });
            	
            	toastr["success"]('Meeting edited successfully!', 'Meeting Edit');
            	
            	//get related meetings
        		getRelatedMeetings();
            	
            	//close edit
            	CloseEdit();
            }
            else
            	toastr["error"]('Meeting edit failed!', 'Meeting Edit');
            
            StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	StopLoading();
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function openViewDeliberations() {
	data = {};
    data['option'] = $('#select-all').val();
    data['meetingID'] = document.getElementById("chosen-meeting-id").value;
    
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MeetingDeliberationsServlet",
        data: data,
        dataType: "json",
        success: function(response) {
        	var table = "<table id='main-tablex' class='table table-clean'>" +
	    					"<thead>" +
	        					"<tr>" +
	                                "<th><b>Deliberation</b></th>" +
	                                "<th><b>Person Responsible</b></th>" +
	                                "<th><b>Timeline</b></th>" +
	                            "</tr>" +
	                        "</thead>" +
    	            		"<tbody>";
        
        	if(response.length > 0) {
            	$.each(response, function(index, element) {		 
            		table += "<tr><td>" + element[1] + 
            				 "</td><td>" + element[3] + " " + element[4] + " <small>(" + element[5] + ", " + element[6] + ")</small>" + 
            				 "</td><td>" + element[2] + "</td></tr>";
                });
        	}
        	else
        		table += "<tr colspan='3'><td>No deliberaation has been created yet.</td></tr>";
        	
        	table += "</tbody></table>";
        	
        	document.getElementById("divAllDeliberations").innerHTML = table;
        	
        	if(document.getElementById("chosen-meeting-is-creator").value != 3 && document.getElementById("chosen-meeting-is-creator").value != 4)
        		$('#add-delib').hide();
        	else
        		$('#add-delib').show();
        	
        	$('#divViewDeliberations').modal('show');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function openAddDeliberation() {
	data = {};
    data['option'] = $('#select-all').val();
    data['meetingID'] = document.getElementById("chosen-meeting-id").value;
    
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MeetingAttendantsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		var select_options = "<select class='form-control custom_select' id='eperson-responsible'>";
	            		                    	
            	$.each(response, function(index, element) {
            		select_options += "<option value='" + element[6] + "'>" + element[1] + " " + element[2] + " (" + element[3] + ", " + element[4] + ")</option>";
                });
            	
            	select_options += "</select>";
            	
            	document.getElementById("select_person_responsible1").innerHTML = select_options;
            	
            	setCustomSelectOn();
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
            		
	$('#divAddDeliberation').modal('show');
}

function createDeliberation() {
	ShowLoading();
	
	//Create deliberation
    data = {};
    data['option'] = $('#insertx').val();
    data['text'] = document.getElementById("deliberation-text").value;
    data['personID'] = document.getElementById("eperson-responsible").value;
    data['timeline'] = document.getElementById("deliberation-timeline").value;
    data['meetingID'] = document.getElementById("chosen-meeting-id").value;
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/MeetingDeliberationsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#insertedx').val()) !== -1) {
            	$('#divAddDeliberation').modal('hide');
            	
            	openViewDeliberations();
            }
            else
            	toastr["error"]('Deliberation creation failed!', 'Deliberation');
            
            StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	StopLoading();
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}