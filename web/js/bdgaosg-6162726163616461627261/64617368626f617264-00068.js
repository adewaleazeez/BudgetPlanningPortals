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

CKEDITOR.replace('obj-desc');
CKEDITOR.replace('obj-edesc');

//Initialise modal
$("#modal_view_project").iziModal();

$('#obj-budget-years').change(function() {
	var selectedOption = $(this).val();
	
	var roles = $('#roles-idx').val().split(',');
	
	var isCitizen = false;
	for(z = 0; z < roles.length; z++) {
		if(roles[z] === '10')
			isCitizen = true;
	}	
	
	getProjects(isCitizen, selectedOption);
});

var all_lgas = "";

$(document).ready(function () {
	//Get Current Year
	getBudgetYears();
	
	//LGAs
	getLGAs();
	
	//Get roles
	var roles = $('#roles-idx').val().split(',');
	
	var isCitizen = false;
	for(z = 0; z < roles.length; z++) {
		if(roles[z] === '10')
			isCitizen = true;
	}
	
	if(!isCitizen)
		$('#show-create').hide();
	
	getProjects(isCitizen, 0);
	
	document.getElementById('erasable').innerHTML = "";
});

function getBudgetYears() {
	data = {};
    data['option'] = $('#select-all').val();
    
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetYearServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
            if(response.length > 0) {
            	var select_options = "";
            	
            	$.each(response, function(index, element) {
            		if(element[3] === true) {
	            		$('#current-base-year').val(element[2]);
	            		$('#current-base-year-id').val(element[0]);
            		}
            		
            		select_options += "<option value='" + element[0] + "'>" + element[1] + "</option>";
                });
            	
            	document.getElementById("obj-budget-years").innerHTML = select_options;
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function getLGAs() {
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/LGAsServlet",
        data: {option: $('#select-all').val()},
        dataType: "json",
        async: false,
        cache: false,
        success: function(response) {
        	var select_options = "";
        	
        	$.each(response, function(index, element) {
        		select_options += "<option value='" + element[0] + "'>" + element[1] + "</option>";
            });
        	
        	all_lgas = select_options;
        	
        	document.getElementById("obj-lga").innerHTML = all_lgas;
        	document.getElementById("obj-elga").innerHTML = all_lgas;
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function createObj() {
	var instance = CKEDITOR.instances['obj-desc'];
	
	//Create budget year
    data = {};
    data['option'] = $('#insertx').val();
    data['title'] = $("#obj-name").val();
    data['description'] = instance.getData();
    data['lgaID'] = $('#obj-lga').val();
    data['year'] = $('#current-base-year').val();
    data['userID'] = $('#v5er-idx').val();
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/PublicProjectSuggestionsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#insertedx').val()) !== -1) {
            	var roles = $('#roles-idx').val().split(',');
            	
            	var isCitizen = false;
            	for(z = 0; z < roles.length; z++) {
            		if(roles[z] === '10')
            			isCitizen = true;
            	}
            	
            	getProjects(isCitizen, $('#current-base-year-id').val());
            	
            	ReturnToList();
            }
            else
            	alert('Project creation failed!');
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function getProjects(isCitizen, budgetYearID) {
    var yearID = budgetYearID;
    
    if(yearID == 0) {
	    data = {};
	    data['option'] = $('#selectx').val();
	    
		$.ajax({
	        type: "GET",
	        url: $('#site-url').val() + "/BudgetYearServlet",
	        data: data,
	        dataType: "json",
	        cache: false,
	        async: false,
	        success: function(response) {
	            if(response.length > 0) {
	            	$.each(response, function(index, element) {
	            		yearID = element[0];
	                });
	            }
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        	//console.log(textStatus + " " + errorThrown);
	        }
	    });
    }
    
	data = {};
    data['option'] = $('#select-all').val();
    data['budgetYearID'] = yearID;
    
    if(isCitizen)
    	data['userID'] = $('#v5er-idx').val();
    
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/PublicProjectSuggestionsServlet",
        data: data,
        dataType: "json",
        success: function(response) {
        	var table = "<table id='main-tablex' class='table table-clean display' style='table-layout: fixed; width: 100%;'>" +
	    					"<thead>" +
	        					"<tr>" +
	                                "<th><b>Title</b></th>" +
	                                "<th style='width: 30%;'><b>Description</b></th>" +
	                                "<th style='width: 20%;'><b>Citizen/NGO/Civil Soc.</b></th>" +
	                                "<th><b>LGA</b></th>" +
	                                "<th><b>Year</b></th>" +	                                
	                                "<th><b>Action</b></th>" +
	                            "</tr>" +
	                        "</thead>" +
    	            		"<tbody>";
        
        	if(response.length > 0) {
            	$.each(response, function(index, element) {
            		var buttons = '<button title="Edit" onclick="showFullSizeProject(' + element[0] + ');" type="button" class="btn btn-sm btn-success">' +
		 			   			  '<span class="btn-label"><i class="fa fa-eye"></i></span>View Project</button>';
            		
            		if(isCitizen) {
            			buttons += '<br/><button title="Edit" onclick="showEditProject(' + element[0] + ');" type="button" class="btn btn-sm btn-info">' +
					 			   '<span class="btn-label"><i class="fa fa-edit"></i></span>Edit</button>';
            		
	            		buttons += '<button onclick="deleteObj(' + element[0] + ');" type="button" class="btn btn-sm btn-danger btn-labeled">' +
					       		   '<span class="btn-label btn-label-left"><i class="fa fa-remove"></i></span>Delete</button>';
            		}
            					 
            		table += "<tr><td>" + element[1] +
            				 "</td><td><div class='cut'>" + element[2] + "</div>" +
            				 "</td><td>" + (isCitizen ? "-" : element[5] + " " + element[6]) +
            				 "</td><td>" + element[3] + 
            				 "</td><td>" + element[4] +            				 
            				 "</td><td>" + buttons + "</td></tr>";
                });
        	}
        	else
        		table += "<tr colspan='5'><td>There are no projects in the current or selected budget year.</td></tr>";
        	
        	table += "</tbody></table>";
        	
        	document.getElementById("main-table").innerHTML = table;
        	
        	$('#main-tablex').DataTable({
                dom: 'Bfrtip',
                buttons: [
                    'excelHtml5',
                    'csvHtml5',
                    'pdfHtml5'
                ]
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showFullSizeProject(id) {
	$('#obj-id').val(id);
	
	data = {};
    data['option'] = $('#selectx').val();
    data['id'] = id;
	
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/PublicProjectSuggestionsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	var roles = $('#roles-idx').val().split(',');
        	
        	var isCitizen = false;
        	for(z = 0; z < roles.length; z++) {
        		if(roles[z] === '10')
        			isCitizen = true;
        	}
        	
        	var project = "<table id='main-tablex' class='table table-clean display'>" +
							"<thead>" +
							"<tr>" +
				                "<th><b>Title</b></th>" +
				                "<th><b>Citizen/NGO/Civil Society</b></th>" +
				            "</tr>" +
				        "</thead>" +
						"<tbody>";
        	
        	$.each(response, function(index, element) {
        		project += "<tr><td>" + element[1] + "</td><td>" + (isCitizen ? "-" : element[6] + " " + element[7]) + 
        				   "</td></tr>";
        		
        		project += "<tr><td colspan='3'><br/>" +
        				   "<label>LGA</label>" + element[3] + "<br/><br/>" +
        				   "<label>Project Description</label>" + element[2] + "</td></tr>";
            });
        	
        	project += "</tbody></table>";
        	
        	document.getElementById("divProject").innerHTML = project;
        	
        	$('#modal_view_project').iziModal('open', this);
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showEditProject(id) {
	$('#obj-id').val(id);
	
	data = {};
    data['option'] = $('#selectx').val();
    data['id'] = id;
	
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/PublicProjectSuggestionsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		document.getElementById("obj-ename").value = element[1];
        		
        		var instance = CKEDITOR.instances['obj-edesc'];
            	instance.setData(element[2]);
        		
        		$('#obj-elga').val(String(element[5]));
            });
        	
        	EditItem();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showEditProject(id) {
	$('#obj-id').val(id);
	
	data = {};
    data['option'] = $('#selectx').val();
    data['id'] = id;
	
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/PublicProjectSuggestionsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		document.getElementById("obj-ename").value = element[1];
        		document.getElementById("obj-edesc").value = element[2];
        		
        		$('#obj-elga').val(String(element[5]));
            });
        	
        	EditItem();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function editObj() {
	ShowLoading();
	
	var instance = CKEDITOR.instances['obj-edesc'];
	
	//Update
    data = {};
    data['option'] = $('#updatex').val();
    data['id'] = $("#obj-id").val();
    data['title'] = $("#obj-ename").val();
    data['description'] = instance.getData();
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/PublicProjectSuggestionsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#updatedx').val()) !== -1) {
            	CloseEdit();
            	
            	var isCitizen = false;
            	var roles = $('#roles-idx').val().split(',');            	
            	for(z = 0; z < roles.length; z++) {
            		if(roles[z] === '10')
            			isCitizen = true;
            	}
            	
            	getProjects(isCitizen, 0);
            }
            else
            	alert('Project edit failed!');
            
            StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	StopLoading();
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function deleteObj(objID) {
	if(confirm("Are you sure you want to delete this?")) {
		ShowLoading();
		
		data = {};
		    data['option'] = $('#deletex').val();
		    data['id'] = objID;
         
        $.ajax({
             type: "POST",
             url: $('#site-url').val() + "/PublicProjectSuggestionsServlet",
             data: data,
             dataType: "text",
             cache: false,
             success: function(response) {                    	
		            if(response.indexOf($('#deletedx').val()) !== -1) {
		            	getObjects();
		            	
		            	// Notification
		                toastr["success"]("Project deleted successfully!", "Deleted");
		            }
		            else  {
		            	// Notification
		                toastr["error"]("Item deletion failed!", "Failure");
		            }
		            
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