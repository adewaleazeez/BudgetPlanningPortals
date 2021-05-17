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

//Notification
$(document).ready(function () {
	//for adding zero prefixes to numbers (date manipulation)
	Number.prototype.AddZero= function(b,c){
        var  l = (String(b|| 10).length - String(this).length) + 1;
        return l > 0 ? new Array(l).join(c || '0') + this : this;
    };
	
	//Get Years
	getBudgetYears();
	
	//Get timetables
	getTimetables();
	
	//Get Phases
	getBudgetPhases();
	
	document.getElementById('erasable').innerHTML = "";
});

function getBudgetYears() {
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetYearServlet",
        data: {option: $('#select-all').val()},
        dataType: "json",
        cache: false,
        success: function(response) {
            if(response.length <= 0)
            	$('#divAskCreateNewYear').modal('show');
            else {
            	var select_options = "<select class='js-states form-control custom_select' id='timetable-year'><option value='0'></option>";
            	
            	$.each(response, function(index, element) {
            		select_options += "<option value='" + element[2] + "'>" + element[1] + "</option>";
                });
            	
            	select_options += "</select>";
            	
            	document.getElementById("select_year1").innerHTML = select_options;
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}


function getBudgetPhases() {
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetPhasesServlet",
        data: {option: $('#select-all').val()},
        dataType: "json",
        cache: false,
        success: function(response) {
        	var select_options = "<select class='js-states form-control custom_select' id='timetable-act-phase'>";
        	var select_options2 = "<select class='js-states form-control custom_select' id='timetable-act-ephase'>";
        	
        	$.each(response, function(index, element) {
        		select_options += "<option value='" + element[0] + "'>" + element[1] + "</option>";
        		select_options2 += "<option value='" + element[0] + "'>" + element[1] + "</option>";
            });
        	
        	select_options += "</select>";
        	select_options2 += "</select>";
        	
        	document.getElementById("select_phase1").innerHTML = select_options;
        	document.getElementById("select_ephase1").innerHTML = select_options2;
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function openCreateNewYear() {
	$('#divAskCreateNewYear').modal('hide');
	$('#divCreateNewYear').modal('show');
}
    		
function createNewYear() {
	//Create budget year
    data = {};
    data['option'] = $('#insertx').val();
    data['name'] = document.getElementById("new-year-name").value;
    data['year'] = document.getElementById("new-year-year").value;
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetYearServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#insertedx').val()) !== -1) {
            	$('#divCreateNewYear').modal('hide');
            	
            	if(document.getElementById("main-table").innerHTML.indexOf("") !== -1) {
            		getBudgetYears();
            		
            		$('#show-create').click();
            	}
            	else
            		//alert('Budget year created successfully!');
                    toastr["error"]("Budget year created successfully!!!", "Record created successfully!");
            }
            else
            	//alert('Budget year creation failed!');
                toastr["error"]("Budget year creation failed!!!", "No Record created!");
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function createBudgetTimetable() {
	//Create budget year
    data = {};
    data['option'] = $('#insertx').val();
    data['name'] = document.getElementById("timetable-name").value;
    data['description'] = document.getElementById("timetable-desc").value;
    data['year'] = document.getElementById("timetable-year").value;
    
    var error = "";
    if(data['name']===""){ error +="<br>Name must not be blank<br>"; }
    if(data['description']===""){ error +="Description must not be blank<br>"; }
    if(data['year']==="0"){ error +="Budget Year must be selected<br>"; }
    if(error!==""){
        toastr["error"](error, "Please Correct The Following Error(s)!!!");
        return true;
    }       
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetTimetableServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {  
            if(response.indexOf($('#insertedx').val()) !== -1) {
            	getTimetables();
            	
            	ReturnToList();
            } else {
                toastr["error"]("Budget timetable creation failed!!", "No record created!");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function editBudgetTimetable() {
	//ShowLoading();
	
	//Update budget timetable
    data = {};
    data['option'] = $('#updatex').val();
    data['id'] = document.getElementById("chosen-budget-timetable-id").value;
    data['name'] = document.getElementById("timetable-ename").value;
    data['description'] = document.getElementById("timetable-edesc").value;
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetTimetableServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {    
            if(response.indexOf($('#updatedx').val()) !== -1) {
            	toastr["success"]("Budget timetable edit successful!!!", "Record updated!");
                CloseEdit();
            	getTimetables();
            }
            else
            	//alert('Budget timetable edit failed!');
                toastr["error"]("Budget timetable edit failed!!!", "No Record created!");
            
            StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//StopLoading();
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function getTimetables() {
    data = {};
    data['option'] = $('#select-all').val();
    
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetTimetableServlet",
        data: data,
        dataType: "json",
        success: function(response) {
        	//alert(response);
        	var table = "<table id='main-tablex' class='table table-clean'>" +
	    					"<thead>" +
	        					"<tr>" +
	                                "<th><b>S/No</b></th>" +
	                                "<th><b>Name</b></th>" +
	                                "<th><b>Description</b></th>" +
	                                "<th><b>Year</b></th>" +
	                                "<th><b>Action</b></th>" +
	                            "</tr>" +
	                        "</thead>" +
    	            		"<tbody>";
        
        	if(response.length > 0) {
                    var count = 0;
            	$.each(response, function(index, element) {
            		var buttons = '<button onclick="showActivities(' + element[0] + ');" type="button" class="btn btn-sm btn-success btn-labeled">' +
            					  '<span class="btn-label btn-label-left"><i class="fa fa-eye"></i></span>Activities</button>';
                            buttons += " <button type='button' class='btn btn-danger' onclick='DeleteItem(" + element[0] + ")'><i class='fa fa-remove'></i>Delete</button>";
            		buttons += '&nbsp;&nbsp;<button title="Edit" onclick="showEditTimetable(' + element[0] + ');" type="button" class="btn btn-sm btn-info">' +
					 			   '<span class="btn-label"><i class="fa fa-edit"></i></span>Edit</button>';
            					 
            		table += "<tr><td>" + (++count) + 
            				 "</td><td>" + element[1] + 
            				 "</td><td>" + element[2] + 
            				 "</td><td>" + element[3] +
            				 "</td><td>" + buttons + "</td></tr>";
                });
        	}
        	else
        		table += "<tr colspan='5'><td>No budget timetable created yet.</td></tr>";
        	
        	table += "</tbody></table>";
        	
        	document.getElementById("main-table").innerHTML = table;
        	
        	$('#main-tablex').DataTable({
                    "order": [[ 3, "desc" ]]
                });
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showEditTimetable(budgetTimetableID) {
	$('#chosen-budget-timetable-id').val(budgetTimetableID);
	
	data = {};
    data['option'] = $('#selectx').val();
    data['id'] = budgetTimetableID;
	
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetTimetableServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		document.getElementById("timetable-ename").value = element[1];
        		document.getElementById("timetable-edesc").value = element[2];
            });
        	
        	EditItem();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showActivities(budgetTimetableID) {
	$('#chosen-budget-timetable-id').val(budgetTimetableID);
	
	data = {};
    data['option'] = $('#select-all').val();
    data['budgetTimetableID'] = budgetTimetableID;
    
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetTimetableActivitiesServlet",
        data: data,
        dataType: "json",
        success: function(response) {
        	var table = "<table id='main-tablex' class='table table-clean'>" +
	    					"<thead>" +
	        					"<tr>" +
	                                "<th><b>Name</b></th>" +
	                                "<th><b>Description</b></th>" +
	                                "<th><b>From</b></th>" +
	                                "<th><b>To</b></th>" +
	                                "<th><b>Phase</b></th>" +
	                                "<th><b>Action</b></th>" +
	                            "</tr>" +
	                        "</thead>" +
    	            		"<tbody>";
        
        	if(response.length > 0) {
            	$.each(response, function(index, element) {
            		var buttons = '<button title="Edit" onclick="showEditBudgetTimetableActivity(' + element[0] + ');" type="button" class="btn btn-sm btn-info">' +
			 			  			  '<span class="btn-label"><i class="fa fa-edit"></i></span></button>';
			 			  			 
			 			buttons += '&nbsp;&nbsp;<button title="Delete" onclick="deleteBudgetTimetableActivity(' + element[0] + ');" type="button" class="btn btn-sm btn-danger">' +
    					 		   '<span class="btn-label"><i class="fa fa-remove"></i></span></button>';
            		
            		table += "<tr><td>" + element[1] + 
            				 "</td><td>" + element[2] + 
            				 "</td><td>" + element[3] + 
            				 "</td><td>" + element[4] +
            				 "</td><td>" + element[5] +
            				 "</td><td>" + buttons + "</td></tr>";
                });
        	}
        	else
        		table += "<tr colspan='5'><td>No budget timetable activity created yet.</td></tr>";
        	
        	table += "</tbody></table>";
        	
        	document.getElementById("divAllActivities").innerHTML = table;
        	
        	$('#divViewActivities').modal('show');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showEditBudgetTimetableActivity(budgetTimetableActivityID) {
    	$('#chosen-budget-timetable-activity-id').val(budgetTimetableActivityID);
	
	data = {};
    data['option'] = $('#selectx').val();
    data['id'] = budgetTimetableActivityID;
	
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetTimetableActivitiesServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		document.getElementById("timetable-act-ename").value = element[1];
        		document.getElementById("timetable-act-edesc").value = element[2];
        		
        		var formattedDate = new Date(element[4]);
//        		var newDateLocal = formattedDate.getFullYear() + "-" + (formattedDate.getMonth() + 1).AddZero() + "-" + 
//        						   formattedDate.getDate().AddZero() + "T" + formattedDate.getHours().AddZero() + ":" + 
//        						   formattedDate.getMinutes().AddZero() + ":" + formattedDate.getSeconds().AddZero() + "." + 
//        						   formattedDate.getMilliseconds();
                        var newDateLocal = formattedDate.getDate().AddZero() + "/" + (formattedDate.getMonth() + 1).AddZero() + "/" + 
                                formattedDate.getFullYear() + " " + formattedDate.getHours().AddZero() + ":" + 
                                formattedDate.getMinutes().AddZero() + ":" + formattedDate.getSeconds().AddZero()+" "+formattedDate.getMilliseconds();
        		document.getElementById("timetable-act-efrom").value = newDateLocal;
        		formattedDate = new Date(element[5]); //     06/06/2018 02:23:14 PM
//        		newDateLocal = formattedDate.getFullYear() + "-" + (formattedDate.getMonth() + 1).AddZero() + "-" + 
//        						   formattedDate.getDate().AddZero() + "T" + formattedDate.getHours().AddZero() + ":" + 
//        						   formattedDate.getMinutes().AddZero() + ":" + formattedDate.getSeconds().AddZero() + "." + 
//        						   formattedDate.getMilliseconds();
                                                   
                        newDateLocal = formattedDate.getDate().AddZero() + "/" + (formattedDate.getMonth() + 1).AddZero() + "/" + 
                                formattedDate.getFullYear() + " " + formattedDate.getHours().AddZero() + ":" + 
                                formattedDate.getMinutes().AddZero() + ":" + formattedDate.getSeconds().AddZero()+" "+formattedDate.getMilliseconds();
        		document.getElementById("timetable-act-eto").value = newDateLocal;
            });
        	
        	$('#divEditActivity').modal('show');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function editBudgetTimetableActivity() {
	ShowLoading();
	
  	//Update budget timetable activity
    data = {};
    data['option'] = $('#updatex').val();
    data['id'] = document.getElementById("chosen-budget-timetable-activity-id").value;
    data['name'] = document.getElementById("timetable-act-ename").value;
    data['description'] = document.getElementById("timetable-act-edesc").value;
    data['fromDate'] = document.getElementById("timetable-act-efrom").value;
    data['toDate'] = document.getElementById("timetable-act-eto").value;
    data['budgetPhaseID'] = document.getElementById("timetable-act-ephase").value;
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetTimetableActivitiesServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#updatedx').val()) !== -1) {
            	$('#divEditActivity').modal('hide');
            	
            	showActivities(document.getElementById("chosen-budget-timetable-id").value);
            }
            else
            	toastr["error"]('Budget timetable activity edit failed!', 'Timetable activity');
            
            StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	StopLoading();
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function deleteBudgetTimetableActivity(activityID) {
	if(confirm("Are you sure you want to delete this activity?")) {
		ShowLoading();
		
		data = {};
		    data['option'] = $('#deletex').val();
		    data['id'] = activityID;
         
        $.ajax({
             type: "POST",
             url: $('#site-url').val() + "/BudgetTimetableActivitiesServlet",
             data: data,
             dataType: "text",
             cache: false,
             success: function(response) {                    	
		            if(response.indexOf($('#deletedx').val()) !== -1)
		            	showActivities(document.getElementById("chosen-budget-timetable-id").value);
		            else
		            	toastr["error"]('Budget timetable activity deletion failed!', 'Timetable activity');
		            
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

function openCreateBudgetTimetableActivity() {
	$('#divAddActivity').modal('show');
}

function createBudgetTimetableActivity() {
	ShowLoading();
	
	//Create budget year
    data = {};
    data['option'] = $('#insertx').val();
    data['name'] = document.getElementById("timetable-act-name").value;
    data['description'] = document.getElementById("timetable-act-desc").value;
    data['fromDate'] = document.getElementById("timetable-act-from").value;
    data['toDate'] = document.getElementById("timetable-act-to").value;
    data['budgetTimetableID'] = document.getElementById("chosen-budget-timetable-id").value;
    data['budgetPhaseID'] = document.getElementById("timetable-act-phase").value;
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetTimetableActivitiesServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#insertedx').val()) !== -1) {
            	$('#divAddActivities').modal('hide');
            	
            	showActivities(document.getElementById("chosen-budget-timetable-id").value);
            }
            else
            	toastr["error"]('Budget timetable activity creation failed!', 'Timetable activity');
            
            StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	StopLoading();
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
        url: "/BudgetPlanningPortals/BudgetTimetableServlet",
        data: {option: "delete", id: id},
        dataType: "text",
        cache: false,
        async: false,
        success: function (data) {
            $('#deleteModal').iziModal('close');
            if (data.indexOf("DELETED")!== -1) {
                toastr["success"]("Budget Type deleted Successfull!", "Delete Successfull");
                getTimetables();
            }
        },
        error: function (a, b, c) {
            toastr["error"]("Budget Type delete Failed!", "No record deleted!");
        }
    });
}
