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

$('.show-create2').click(function () {
	//reset
	resetNotificationSetup();
	
    $('#list-area').hide();
    $('#create-area').fadeIn();
    $('#MainSection .panel-title').fadeIn();
});

var EditItem2 = function () {
	//reset
	resetNotificationSetup();
	
    $('#MainSection').hide();
    $('#EditSection').fadeIn();
    $('.edit-child').fadeIn();
}

var all_agent_types = "";
var all_roles = "";
var all_mdas = "";
var all_notif_types = "";

var roles_for_add = [];
var agent_type_for_add = [];

//notifications
var a_notif_subjects = [];
var a_notif_texts = [];
var a_notif_types = [];

var r_notif_subjects = [];
var r_notif_texts = [];
var r_notif_types = [];

//for new
var i = 2;

//for edit
var x = 1;
var y = 1;
var finalx = 0;
var finaly = 0;

//Initialise editors
CKEDITOR.replace('mnotif-text0');
CKEDITOR.replace('mnotif-text1');
CKEDITOR.replace('mnotif-text2');
CKEDITOR.replace('mnotif-text3');
CKEDITOR.replace('emnotif-text3');
CKEDITOR.replace('mrequest-report');
CKEDITOR.replace('emrequest-report');

$(document).ready(function () {
	//Get Request Types
	getObjects();
	
	//get data - submission
	getAgentTypes(i - 2, 0);        		
	getRoles(i - 2, 0);
	getMdas(i - 2, 0);
	
	//get data - first approval
	getAgentTypes(i - 1, 0);        		
	getRoles(i - 1, 0);
	getMdas(i - 1, 0);
	
	$('#magent-type1').val(2);
	
	/* Request Agents */
	$("#add_row").click(function () {
    	$('#addr' + i).html("<td>" + (i + 1) + "</td>" +
    						"<td><select class='form-control custom_select' id='magent-type" + i + "'></select></td>" +
    			            "<td><select class='form-control custom_select' id='mrole" + i + "'></select></td>" + 
    			            "<td><select class='form-control custom_select' id='mmda" + i + "'></select></td>" +
    			            "<td><button type='button' id='notif-setup" + i + "' class='btn btn-info' role='button' onclick='showNotificationSetup(" + i + ", 0)' " +
              				"data-toggle='tooltip' data-placement='top' title='Create notification messages'><i class='fa fa-comment'> </i>Add Notifications</button></td>");

        $('#tab_logic').append('<tr id="addr' + (i + 1) + '"></tr>');
        i++;
        
        //get data
        getAgentTypes(i - 1, 0);
        getRoles(i - 1, 0);
        getMdas(i - 1, 0);
        
        try {
            $('.custom_select').select2();
        } catch (e) {}
    });
    
    $("#delete_row").click(function () {
        if (i > 2) {
        	//remove values
        	//approval
            a_notif_types.pop();
            a_notif_subjects.pop();
            a_notif_texts.pop();
            
            //rejection
            r_notif_types.pop();
            r_notif_subjects.pop();
            r_notif_texts.pop();
            
            //remove row
            $("#addr" + (i - 1)).html('');
            i--;
        }
    });
    
    /* Edit Request Agents */
    $("#eadd_row").click(function () {
    	$('#eaddr' + x).html("<td><select class='form-control custom_select' id='emagent-type" + x + "'></select></td>" +
    						 "<td><select class='form-control custom_select' id='emrole" + x + "'></select></td>" +
    						 "<td><select class='form-control custom_select' id='emmda" + x + "'></select></td>" +
     			             "<td><button type='button' id='enotif-setup" + x + "' class='btn btn-info' role='button' onclick='showNotificationSetup(" + x + ", 1)' " +
               				 "data-toggle='tooltip' data-placement='top' title='Create notification messages'><i class='fa fa-comment'> </i>Add Notifications</button></td>" +
    						 "<td></td>");

        $('#etab_logic').append('<tr id="eaddr' + (x + 1) + '"></tr>');
        x++;
        
        //get data
        getAgentTypes(x - 1, 1);
        getRoles(x - 1, 1);
        getMdas(x - 1, 1);
        
        try {
            $('.custom_select').select2();
        } catch (e) {}
    });
    
    $("#edelete_row").click(function () {
        if (x > finalx) {
        	//approval
            a_notif_types.pop();
            a_notif_subjects.pop();
            a_notif_texts.pop();
            
            //rejection
            r_notif_types.pop();
            r_notif_subjects.pop();
            r_notif_texts.pop();
            
            $("#eaddr" + (x - 1)).html('');
            x--;
        }
    });
});

function resetNotificationSetup() {
	$('#row-index').val('');
	
	for(j = 0; j < a_notif_types.length; j++) {
		//approval
		a_notif_types.pop();
        a_notif_subjects.pop();
        a_notif_texts.pop();
        
        //rejection
        r_notif_types.pop();
        r_notif_subjects.pop();
        r_notif_texts.pop();
        
        if(j > 2) {
	        $("#addr" + (j - 1)).html('');
	        j--;
        }
        
        if (x > finalx) {
        	$("#eaddr" + (x - 1)).html('');
            x--;
        }
	}
	
	//Sub
	$('#mnotif-subject0').val('');
	
	var instance = CKEDITOR.instances['mnotif-text0'];
	instance.setData('');
	
	//Approval
	$('#mnotif-subject1').val('');
	
	var instance = CKEDITOR.instances['mnotif-text1'];
	instance.setData('');
	
	//Rejection
	$('#mnotif-subject2').val('');
	
	instance = CKEDITOR.instances['mnotif-text2'];
	instance.setData('');
	
	//Full Approval
	$('#mnotif-subject3').val('');
	
	instance = CKEDITOR.instances['mnotif-text3'];
	instance.setData('');
	
	instance = CKEDITOR.instances['mrequest-report'];
	instance.setData('');
	
	instance = CKEDITOR.instances['emrequest-report'];
	instance.setData('');
}

function showNotificationSetup(index, isEdit) {
	$('#row-index').val(index);
	$('#is-edit').val(isEdit);
	
	if(index < a_notif_types.length) {
		//get current texts
		//Approval
		$('#mnotif-subject1').val(a_notif_subjects[index]);
		
		var instance = CKEDITOR.instances['mnotif-text1'];
		instance.setData(a_notif_texts[index]);
		
		//Rejection
		$('#mnotif-subject2').val(r_notif_subjects[index]);
		
		var instance = CKEDITOR.instances['mnotif-text2'];
		instance.setData(r_notif_texts[index]);
	}
	else {
		//clean up old stuff for new
		//Approval
		$('#mnotif-subject1').val('');
		
		var instance = CKEDITOR.instances['mnotif-text1'];
		instance.setData('');
		
		//Rejection
		$('#mnotif-subject2').val('');
		
		var instance = CKEDITOR.instances['mnotif-text2'];
		instance.setData('');
	}
	
	$('#divNotifModal').modal('show');
}

function storeNotifValues() {
	var pos = parseInt($('#row-index').val());
	var isEdit = parseInt($('#is-edit').val());
	
	if(isEdit == 0) {
		//Approval
		var instance = CKEDITOR.instances['mnotif-text1'];
	    a_notif_texts.splice(pos, 1, instance.getData());
		a_notif_types.splice(pos, 1, $('#mnotif-type1').val());
		a_notif_subjects.splice(pos, 1, $('#mnotif-subject1').val())
		
	    //Rejection
	    var instance = CKEDITOR.instances['mnotif-text2'];
	    r_notif_texts.splice(pos, 1, instance.getData());
		r_notif_types.splice(pos, 1, $('#mnotif-type2').val());
		r_notif_subjects.splice(pos, 1, $('#mnotif-subject2').val());
	}
	else {
		if(pos > finalx) {
			//Approval
			var instance = CKEDITOR.instances['mnotif-text1'];
		    a_notif_texts.splice(pos, 1, instance.getData());
			a_notif_types.splice(pos, 1, $('#mnotif-type1').val());
			a_notif_subjects.splice(pos, 1, $('#mnotif-subject1').val())
			
		    //Rejection
		    var instance = CKEDITOR.instances['mnotif-text2'];
		    r_notif_texts.splice(pos, 1, instance.getData());
			r_notif_types.splice(pos, 1, $('#mnotif-type2').val());
			r_notif_subjects.splice(pos, 1, $('#mnotif-subject2').val());
		}
		else {
			var requestTypeID = document.getElementById("obj-id").value;
			
			//Approval update
			var instance = CKEDITOR.instances['mnotif-text1'];
		    
			data = {};
		    data['option'] = $('#updatex').val();
		    data['notificationTypeID'] = $('#mnotif-type1').val();
		    data['requestTypeID'] = requestTypeID;
		    data['subject'] = $('#mnotif-subject1').val();
		    data['text'] = instance.getData();
		    data['roleID'] = $('#curr-role').val();
	        
	        $.ajax({
	            type: "POST",
	            url: $('#site-url').val() + "/NotificationsServlet",
	            data: data,
	            dataType: "text",
	            cache: false,
	            async: false,
		        error: function(jqXHR, textStatus, errorThrown) {
	            	//console.log(textStatus + " " + errorThrown);
	            }
	        });
	        
	        //Rejection
	        instance = CKEDITOR.instances['mnotif-text2'];
		    
			data = {};
		    data['option'] = $('#updatex').val();
		    data['notificationTypeID'] = $('#mnotif-type2').val();
		    data['requestTypeID'] = requestTypeID;
		    data['subject'] = $('#mnotif-subject2').val();
		    data['text'] = instance.getData();
		    data['roleID'] = $('#curr-role').val();
	        
	        $.ajax({
	            type: "POST",
	            url: $('#site-url').val() + "/NotificationsServlet",
	            data: data,
	            dataType: "text",
	            cache: false,
	            async: false,
		        error: function(jqXHR, textStatus, errorThrown) {
	            	//console.log(textStatus + " " + errorThrown);
	            }
	        });
		}
	}
	
	$('#divNotifModal').modal('hide');
}

function showSubNotificationSetup() {
	$('#divSubNotifModal').modal('show');
}

function hideSubNotificationSetup() {
	$('#divSubNotifModal').modal('hide');
}

function getAgentTypes(id, isEdit) {
	if(id == 0) {
		$.ajax({
            type: "GET",
            url: $('#site-url').val() + "/RequestAgentTypesServlet",
            data: {option: $('#select-all').val()},
            dataType: "json",
            async: false,
            cache: false,
            success: function(response) {
            	var select_options = "";
            	
            	$.each(response, function(index, element) {
            		select_options += "<option value='" + element[0] + "'>" + element[1] + "</option>";
                });
            	
            	all_agent_types = select_options;
            	
            	if(isEdit == 0)
            		document.getElementById("magent-type0").innerHTML = all_agent_types;
            	else
            		document.getElementById("emagent-type0").innerHTML = all_agent_types;
	        },
            error: function(jqXHR, textStatus, errorThrown) {
            	//console.log(textStatus + " " + errorThrown);
            }
        });
	}
	else {
		if(isEdit == 0)
			document.getElementById("magent-type" + id).innerHTML = all_agent_types;
		else
			document.getElementById("emagent-type" + id).innerHTML = all_agent_types;
	}
}

function getRoles(id, isEdit) {
	if(id == 0) {
		$.ajax({
            type: "GET",
            url: $('#site-url').val() + "/RolesServlet",
            data: {option: $('#select-all').val()},
            dataType: "json",
            async: false,
            cache: false,
            success: function(response) {
            	var select_options = "";
            	
            	$.each(response, function(index, element) {
            		select_options += "<option value='" + element[0] + "'>" + element[1] + "</option>";
                });
            	
            	all_roles = select_options;
            	
            	if(isEdit == 0)
            		document.getElementById("mrole0").innerHTML = all_roles;
            	else
            		document.getElementById("emrole0").innerHTML = all_roles;
	        },
            error: function(jqXHR, textStatus, errorThrown) {
            	//console.log(textStatus + " " + errorThrown);
            }
        });
	}
	else {
		if(isEdit == 0)
			document.getElementById("mrole" + id).innerHTML = all_roles;
		else
			document.getElementById("emrole" + id).innerHTML = all_roles;
	}
}

function getMdas(id, isEdit) {
	if(id == 0) {
		$.ajax({
            type: "GET",
            url: $('#site-url').val() + "/MdaServlet",
            data: {option: $('#select-all').val()},
            dataType: "json",
            async: false,
            cache: false,
            success: function(response) {
            	var select_options = "";
            	
            	$.each(response, function(index, element) {
            		select_options += "<option value='" + element[0] + "'>" + element[1] + "</option>";
                });
            	
            	all_mdas = select_options;
            	
            	if(isEdit == 0)
            		document.getElementById("mmda0").innerHTML = all_mdas;
            	else
            		document.getElementById("emmda0").innerHTML = all_mdas;
	        },
            error: function(jqXHR, textStatus, errorThrown) {
            	//console.log(textStatus + " " + errorThrown);
            }
        });
	}
	else {
		if(isEdit == 0)
			document.getElementById("mmda" + id).innerHTML = all_mdas;
		else
			document.getElementById("emmda" + id).innerHTML = all_mdas;
	}
}

function createObj() {
	ShowLoading();
	
	var instance = CKEDITOR.instances['mrequest-report'];
	
	//Create budget year
    data = {};
    data['option'] = $('#insertx').val();
    data['name'] = document.getElementById("obj-name").value;
    data['documentTemplate'] = instance.getData();
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/RequestTypesServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {
            var requestTypeID = parseInt(response);
            
        	if(requestTypeID >= 1) {
        		/* Request Agents */
            	var agent_types = [];
            	var roles = [];
            	var mdas = [];
            	
            	$('select[id^="magent-type"]').each(function(){ 
            		agent_types.push(this.value);
            	});
            	
            	$('select[id^="mrole"]').each(function(){ 
            		roles.push(this.value);
            	});
            	
            	$('select[id^="mmda"]').each(function(){ 
            		mdas.push(this.value);
            	});
            	
            	if(agent_types.length <= 0 || roles.length <= 0 || mdas.length <= 0) {
            		alert('You must enter at least one agent type with their respective roles and mdas for this request type.');
            		
            		data = {};
            		data['option'] = $('#deletex').val();
            		data['id'] = requestTypeID;
                     
                    $.ajax({
                         type: "POST",
                         url: $('#site-url').val() + "/RequestTypesServlet",
                         data: data,
                         dataType: "text",
                         cache: false,
            		     error: function(jqXHR, textStatus, errorThrown) {
                         	//console.log(textStatus + " " + errorThrown);
                         }
                    });
                    
            		StopLoading();
            		return;
            	}
            	
            	if(agent_types.length != roles.length && agent_types.length != mdas.length) {
            		alert('You must select a role and mda for every agent type chosen.');
            		
            		data = {};
            		data['option'] = $('#deletex').val();
            		data['id'] = requestTypeID;
                     
                    $.ajax({
                         type: "POST",
                         url: $('#site-url').val() + "/RequestTypesServlet",
                         data: data,
                         dataType: "text",
                         cache: false,
            		     error: function(jqXHR, textStatus, errorThrown) {
                         	//console.log(textStatus + " " + errorThrown);
                         }
                    });
                    
            		StopLoading();
            		return;
            	}
            	
            	for(z = 0; z < roles.length; z++) {
	            	data = {};
	    		    data['option'] = $('#insertx').val();
	    		    data['roleID'] = roles[z];
	    		    data['requestTypeID'] = requestTypeID;
	    		    data['requestAgentTypeID'] = agent_types[z];
	    		    data['mdaID'] = mdas[z];
	                
	                $.ajax({
	                    type: "POST",
	                    url: $('#site-url').val() + "/RequestAgentsServlet",
	                    data: data,
	                    dataType: "text",
	                    cache: false,
	                    async: false,
				        error: function(jqXHR, textStatus, errorThrown) {
	                    	//console.log(textStatus + " " + errorThrown);
	                    }
	                });
            	}
            	/* End Request Agents */            	
            	
            	/* Approval Notifications */
            	for(z = 0; z < a_notif_types.length; z++) {
	            	data = {};
	    		    data['option'] = $('#insertx').val();
	    		    data['notificationTypeID'] = a_notif_types[z];
	    		    data['requestTypeID'] = requestTypeID;
	    		    data['subject'] = a_notif_subjects[z];
	    		    data['text'] = a_notif_texts[z];
	    		    data['roleID'] = roles[z + 1];
	                
	                $.ajax({
	                    type: "POST",
	                    url: $('#site-url').val() + "/NotificationsServlet",
	                    data: data,
	                    dataType: "text",
	                    cache: false,
	                    async: false,
				        error: function(jqXHR, textStatus, errorThrown) {
	                    	//console.log(textStatus + " " + errorThrown);
	                    }
	                });
            	}
            	/* End Approval Notifications */
            	
            	/* Rejection Notifications */
            	for(z = 0; z < r_notif_types.length; z++) {
	            	data = {};
	    		    data['option'] = $('#insertx').val();
	    		    data['notificationTypeID'] = r_notif_types[z];
	    		    data['requestTypeID'] = requestTypeID;
	    		    data['subject'] = r_notif_subjects[z];
	    		    data['text'] = r_notif_texts[z];
	    		    data['roleID'] = roles[z + 1];
	                
	                $.ajax({
	                    type: "POST",
	                    url: $('#site-url').val() + "/NotificationsServlet",
	                    data: data,
	                    dataType: "text",
	                    cache: false,
	                    async: false,
				        error: function(jqXHR, textStatus, errorThrown) {
	                    	//console.log(textStatus + " " + errorThrown);
	                    }
	                });
            	}
            	/* End Rejection Notifications */
            	
            	/* Submission Notifications */
            	var instance = CKEDITOR.instances['mnotif-text0'];
                
            	data = {};
    		    data['option'] = $('#insertx').val();
    		    data['notificationTypeID'] = $('#mnotif-type0').val();
    		    data['requestTypeID'] = requestTypeID;
    		    data['subject'] = $('#mnotif-subject0').val();
    		    data['text'] = instance.getData();
    		    data['roleID'] = roles[0];
                
                $.ajax({
                    type: "POST",
                    url: $('#site-url').val() + "/NotificationsServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    async: false,
			        error: function(jqXHR, textStatus, errorThrown) {
                    	//console.log(textStatus + " " + errorThrown);
                    }
                });
                /* End Submission Notifications */
            	
            	/* Full Approval Notifications */
            	instance = CKEDITOR.instances['mnotif-text3'];
                
            	data = {};
    		    data['option'] = $('#insertx').val();
    		    data['notificationTypeID'] = $('#mnotif-type3').val();
    		    data['requestTypeID'] = requestTypeID;
    		    data['subject'] = $('#mnotif-subject3').val();
    		    data['text'] = instance.getData();
    		    data['roleID'] = roles[0];
                
                $.ajax({
                    type: "POST",
                    url: $('#site-url').val() + "/NotificationsServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    async: false,
			        error: function(jqXHR, textStatus, errorThrown) {
                    	//console.log(textStatus + " " + errorThrown);
                    }
                });
                /* End Full Approval Notifications */
            	
            	getObjects();
            	
            	ReturnToList();
            	
            	// Notification
                toastr["success"]("Request type created successfully!", "Created");
            }
            else
                toastr["error"]("Request type creation failed!", "Failure");
            
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
	
	var requestTypeID = document.getElementById("obj-id").value;
	
	var instance = CKEDITOR.instances['emrequest-report'];	
	
	//Update request type
    data = {};
    data['option'] = $('#updatex').val();
    data['id'] = requestTypeID;
    data['name'] = document.getElementById("obj-ename").value;
    data['documentTemplate'] = instance.getData();
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/RequestTypesServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#updatedx').val()) !== -1) {
            	/* Request Agents */
            	var agent_types = [];
            	var roles = [];
            	var mdas = [];
            	            	
            	$('select[id^="emagent-type"]').each(function(){ 
            		agent_types.push(this.value);
            	});
            	
            	$('select[id^="emrole"]').each(function(){ 
            		roles.push(this.value);
            	});
            	
            	$('select[id^="emmda"]').each(function(){ 
            		mdas.push(this.value);
            	});
            	            	            	
            	for(z = 0; z < roles.length; z++) {
	            	data = {};
	    		    data['option'] = $('#insertx').val();
	    		    data['roleID'] = roles[z];
	    		    data['requestTypeID'] = requestTypeID;
	    		    data['requestAgentTypeID'] = agent_types[z];
	    		    data['mdaID'] = mdas[z];
	                
	                $.ajax({
	                    type: "POST",
	                    url: $('#site-url').val() + "/RequestAgentsServlet",
	                    data: data,
	                    dataType: "text",
	                    cache: false,
	                    async: false,
				        error: function(jqXHR, textStatus, errorThrown) {
	                    	//console.log(textStatus + " " + errorThrown);
	                    }
	                });
            	}
            	/* End Request Agents */
            	
            	/* Approval Notifications */
            	for(z = 0; z < a_notif_types.length; z++) {
	            	data = {};
	    		    data['option'] = $('#insertx').val();
	    		    data['notificationTypeID'] = a_notif_types[z];
	    		    data['requestTypeID'] = requestTypeID;
	    		    data['subject'] = a_notif_subjects[z];
	    		    data['text'] = a_notif_texts[z];
	    		    data['roleID'] = roles[z];
	                
	                $.ajax({
	                    type: "POST",
	                    url: $('#site-url').val() + "/NotificationsServlet",
	                    data: data,
	                    dataType: "text",
	                    cache: false,
	                    async: false,
				        error: function(jqXHR, textStatus, errorThrown) {
	                    	//console.log(textStatus + " " + errorThrown);
	                    }
	                });
            	}
            	/* End Approval Notifications */
            	
            	/* Rejection Notifications */
            	for(z = 0; z < r_notif_types.length; z++) {
	            	data = {};
	    		    data['option'] = $('#insertx').val();
	    		    data['notificationTypeID'] = r_notif_types[z];
	    		    data['requestTypeID'] = requestTypeID;
	    		    data['subject'] = r_notif_subjects[z];
	    		    data['text'] = r_notif_texts[z];
	    		    data['roleID'] = roles[z];
	                
	                $.ajax({
	                    type: "POST",
	                    url: $('#site-url').val() + "/NotificationsServlet",
	                    data: data,
	                    dataType: "text",
	                    cache: false,
	                    async: false,
				        error: function(jqXHR, textStatus, errorThrown) {
	                    	//console.log(textStatus + " " + errorThrown);
	                    }
	                });
            	}
            	/* End Rejection Notifications */
            	
            	if($('#mnotif-subject0').val() !== '') {
	            	/* Submission Notifications */
	            	var instance = CKEDITOR.instances['mnotif-text0'];
	                
	            	data = {};
	    		    data['option'] = $('#updatex').val();
	    		    data['notificationTypeID'] = $('#mnotif-type0').val();
	    		    data['requestTypeID'] = requestTypeID;
	    		    data['subject'] = $('#mnotif-subject0').val();
	    		    data['text'] = instance.getData();
	    		    data['roleID'] = $('#sub-role').val();
	                
	                $.ajax({
	                    type: "POST",
	                    url: $('#site-url').val() + "/NotificationsServlet",
	                    data: data,
	                    dataType: "text",
	                    cache: false,
	                    async: false,
				        error: function(jqXHR, textStatus, errorThrown) {
	                    	//console.log(textStatus + " " + errorThrown);
	                    }
	                });
	                /* End Submission Notifications */
            	}
            	
            	/* Full Approval Notifications */
            	instance = CKEDITOR.instances['emnotif-text3'];
                
            	data = {};
    		    data['option'] = $('#updatex').val();
    		    data['notificationTypeID'] = $('#emnotif-type3').val();
    		    data['requestTypeID'] = requestTypeID;
    		    data['subject'] = $('#emnotif-subject3').val();
    		    data['text'] = instance.getData();
    		    data['roleID'] = $('#sub-role').val();
                
                $.ajax({
                    type: "POST",
                    url: $('#site-url').val() + "/NotificationsServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    async: false,
			        error: function(jqXHR, textStatus, errorThrown) {
                    	//console.log(textStatus + " " + errorThrown);
                    }
                });
                /* End Full Approval Notifications */
            	
            	CloseEdit();
            	
            	getObjects();
            	
            	// Notification
                toastr["success"]("Request type updated successfully!", "Updated");
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
        url: $('#site-url').val() + "/RequestTypesServlet",
        data: data,
        dataType: "json",
        success: function(response) {
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
            			buttons += '<button onclick="showAgents(' + element[0] + ');" type="button" class="btn btn-sm btn-info btn-labeled">' +
            					   '<span class="btn-label btn-label-left"><i class="fa fa-eye"></i></span>View Agents</button>&nbsp;&nbsp;';
            			buttons += '<button onclick="deleteObj(' + element[0] + ');" type="button" class="btn btn-sm btn-danger btn-labeled">' +
            					   '<span class="btn-label btn-label-left"><i class="fa fa-remove"></i></span>Delete</button>';
            			
            		table += "<tr><td>" + element[1] + 
            				 "</td><td>" + element[2] + 
            				 "</td><td>" + buttons + "</td></tr>";
                });
        	}
        	else
        		table += "<tr colspan='5'><td>No request type created yet.</td></tr>";
        	
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
	
	//for edit
	x = 0;
	
	data = {};
    data['option'] = $('#selectx').val();
    data['id'] = requestTypeID;
	
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/RequestTypesServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		document.getElementById("obj-ename").value = element[1];
        		
        		var instance = CKEDITOR.instances['emrequest-report'];
            	instance.setData(element[3]);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
	
	//Get Request Agents
	data = {};
    data['option'] = $('#select-all').val();
    data['requestTypeID'] = requestTypeID;
    
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/RequestAgentsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	var initialRole = '';
        	
        	$.each(response, function(index, element) {
        		if(element[5] == 1) {
	        		$('#eaddr' + x).html("<td><label>" + element[3] + "</label></td>" +
	        							 "<td><label>" + element[1] + "</label></td>" +
	        							 "<td><label>" + element[7] + "</label></td>" +
	        							 "<td><a onclick='viewSubNotification(" + element[4] + ", " + element[2] + ");' class='btn btn-sm btn-info'><i class='fa fa-comment'></i> Edit Notification</a></td>" +
	        							 "<td><a onclick='deleteAgent(" + element[0] + ", " + x + ");' class='btn btn-sm btn-danger'><i class='fa fa-remove'></i></a></td>");
	        		
	        		initialRole = element[4];
        		}
        		else {
        			$('#eaddr' + x).html("<td><label>" + element[3] + "</label></td>" +
										 "<td><label>" + element[1] + "</label></td>" +
										 "<td><label>" + element[7] + "</label></td>" +
										 "<td><a onclick='viewNotifications(" + x + ", " + element[4] + ", " + element[2] + ");' class='btn btn-sm btn-info'><i class='fa fa-comment'></i> Edit Notifications</a></td>" +
										 "<td><a onclick='deleteAgent(" + element[0] + ", " + x + ");' class='btn btn-sm btn-danger'><i class='fa fa-remove'></i></a></td>");
        		}
        		
                $('#etab_logic').append('<tr id="eaddr' + (x + 1) + '"></tr>');
                x++;
            });
        	
        	finalx = x;
        	
        	$('#sub-role').val(initialRole);
        	
        	//get full approval
        	data = {};
            data['option'] = $('#selectx').val();
            data['roleID'] = initialRole;
            data['requestTypeID'] = requestTypeID;
            data['notificationTypeID'] = $('#emnotif-type3').val();
            
        	$.ajax({
                type: "GET",
                url: $('#site-url').val() + "/NotificationsServlet",
                data: data,
                dataType: "json",
                cache: false,
                success: function(response) {
                	var subject = '', 
                		message = '';
                	
                	$.each(response, function(index, element) {
                		subject = element[1];
                		message = element[2];
                    });
                	
                	//subject/message
                	$('#emnotif-subject3').val(subject);        	
                	var instance = CKEDITOR.instances['emnotif-text3'];
                	instance.setData(message);
                },
                error: function(jqXHR, textStatus, errorThrown) {
                	//console.log(textStatus + " " + errorThrown);
                }
            });
        	
        	EditItem2();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function viewSubNotification(roleID, requestTypeID) {
	//Get Request Agents
	data = {};
    data['option'] = $('#selectx').val();
    data['roleID'] = roleID;
    data['requestTypeID'] = requestTypeID;
    data['notificationTypeID'] = $('#mnotif-type0').val();
    
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/NotificationsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	var subject = '', 
        		message = '';
        	
        	$.each(response, function(index, element) {
        		subject = element[1];
        		message = element[2];
            });
        	
        	//subject/message
        	$('#mnotif-subject0').val(subject);        	
        	var instance = CKEDITOR.instances['mnotif-text0'];
        	instance.setData(message);
        	       	
        	$('#divSubNotifModal').modal('show');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function viewNotifications(pos, roleID, requestTypeID) {
	$('#curr-role').val(roleID);
	$('#row-index').val(pos);
	$('#is-edit').val(1);
	
	//Get Request Agents
	data = {};
    data['option'] = $('#select-all').val();
    data['roleID'] = roleID;
    data['requestTypeID'] = requestTypeID;
    
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/NotificationsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	var a_subject = '', a_message = '',
        		r_subject = '', r_message = '';
        	
        	$.each(response, function(index, element) {
        		if(element[3] == 2) {
	        		a_subject = element[1];
	        		a_message = element[2];
        		}
        		else if(element[3] == 3) {
	        		r_subject = element[1];
	        		r_message = element[2];
        		}
            });
        	
        	//subject/message
        	$('#mnotif-subject1').val(a_subject);        	
        	var instance = CKEDITOR.instances['mnotif-text1'];
        	instance.setData(a_message);
        	
        	$('#mnotif-subject2').val(r_subject);        	
        	var instance = CKEDITOR.instances['mnotif-text2'];
        	instance.setData(r_message);
        	       	
        	$('#divNotifModal').modal('show');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showAgents(requestTypeID) {
	//Get Request Agents
	data = {};
    data['option'] = $('#select-all').val();
    data['requestTypeID'] = requestTypeID;
    
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/RequestAgentsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	var table = '<table class="table table-striped" id="vtab_logic">' +
                            '<thead>' +
		                        '<tr>' +
		                          '<th style="width: 5px;"></th>' +
		                          '<th>Agent Type</th>' +
		                          '<th>Role</th>' +
		                          '<th>MDA</th>' +
		                        '</tr>' +
		                    '</thead>' +
		                    '<tbody>';
		    
        	var y = 1;
        	$.each(response, function(index, element) {
        		table += '<tr><td>' + y + '</td>' +
        				 '<td><label>' + element[3] + '</label></td>' +
        				 '<td><label><a onclick="showUsers(' + element[4] + ', ' + element[6] + ')"><span style="color: blue;">' + element[1] + '</span></a></label></td>' +
        				 '<td><label>' + element[7] + '</label></td></tr>';
        		y++;
            });
        	
        	table += '</tbody></table>';
	
			$('#allAgents').html(table);
        	
        	$('#divViewAgents').modal('show');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showUsers(roleID, mdaID) {
	//Get users
	data = {};
    data['option'] = $('#select-all').val();
    data['roleID'] = roleID;
    data['mdaID'] = mdaID;
    
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/UserRoleServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	var table = '<table class="table table-striped" id="vvtab_logic">' +
                            '<thead>' +
		                        '<tr>' +
		                          '<th style="width: 5px;"></th>' +
		                          '<th>Name</th>' +
		                          '<th>Department</th>' +
		                        '</tr>' +
		                    '</thead>' +
		                    '<tbody>';
		    
        	var y = 1;
        	$.each(response, function(index, element) {
        		table += '<tr><td>' + y + '</td>' +
        				 '<td><label>' + element[1] + ' ' + element[2] + '</label></td>' +
        				 '<td><label>' + element[3] + '</label></td></tr>';
        		y++;
            });
        	
        	table += '</tbody></table>';
	
        	$('#allUsers').html(table);
        	
        	$('#divViewUsers').modal('show');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function deleteNotification(objID, rowY) {
	if(confirm("Are you sure you want to delete this?")) {
		ShowLoading();
		
		data = {};
		data['option'] = $('#deletex').val();
		data['id'] = objID;
         
        $.ajax({
             type: "POST",
             url: $('#site-url').val() + "/NotificationsServlet",
             data: data,
             dataType: "text",
             cache: false,
             success: function(response) {                    	
		            if(response.indexOf($('#deletedx').val()) !== -1) {
		            	$("#enaddr" + rowY).html('');
		            	finaly--;
		            	
		            	// Notification
		            	toastr["success"]("Notification deleted successfully!", "Deleted");
		            }
		            else
		            	toastr["error"]("Item deletion failed!", "Failure");
		            
		           StopLoading();
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		        	StopLoading();
             	//console.log(textStatus + " " + errorThrown);
             }
        });
    }
	else
		return false;
}

function deleteAgent(objID, rowX) {
	if(confirm("Are you sure you want to delete this?")) {
		ShowLoading();
		
		data = {};
		data['option'] = $('#deletex').val();
		data['id'] = objID;
         
        $.ajax({
             type: "POST",
             url: $('#site-url').val() + "/RequestAgentsServlet",
             data: data,
             dataType: "text",
             cache: false,
             success: function(response) {                    	
		            if(response.indexOf($('#deletedx').val()) !== -1) {
		            	$("#eaddr" + rowX).html('');
		            	finalx--;
		            	
		            	// Notification
		            	toastr["success"]("Agent deleted successfully!", "Deleted");
		            }
		            else
		            	toastr["error"]("Item deletion failed!", "Failure");
		            
		           StopLoading();
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		        	StopLoading();
             	//console.log(textStatus + " " + errorThrown);
             }
        });
    }
	else
		return false;
}

function deleteObj(objID) {
	if(confirm("Are you sure you want to delete this?")) {
		ShowLoading();
		
		data = {};
		data['option'] = $('#deletex').val();
		data['id'] = objID;
         
        $.ajax({
             type: "POST",
             url: $('#site-url').val() + "/RequestTypesServlet",
             data: data,
             dataType: "text",
             cache: false,
             success: function(response) {                    	
		            if(response.indexOf($('#deletedx').val()) !== -1) {
		            	getObjects();
		            	
		            	// Notification
		            	toastr["success"]("Request type deleted successfully!", "Deleted");
		            }
		            else
		            	toastr["error"]("Item deletion failed!", "Failure");
		            
		           StopLoading();
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		        	StopLoading();
             	//console.log(textStatus + " " + errorThrown);
             }
        });
    }
	else
		return false;
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