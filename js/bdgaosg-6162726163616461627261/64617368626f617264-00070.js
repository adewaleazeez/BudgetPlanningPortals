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

//Check workflow
checkWorkflowStatus();

CKEDITOR.replace('obj-desc');
CKEDITOR.replace('obj-edesc');

$(document).ready(function () {
	var wfs = parseInt(document.getElementById("wfs").value);
	
	//Close side-menu
	if(wfs == 4) {
		$('#nav-togglerx').click();
		
		var requestDetailID = document.getElementById("request-det-id").value;
		
		data = {};
	    data['option'] = $('#selectx').val();
	    data['requestDetailID'] = requestDetailID;
		
		$.ajax({
	        type: "GET",
	        url: $('#site-url').val() + "/RequestDocumentsServlet",
	        data: data,
	        dataType: "json",
	        cache: false,
	        
	        success: function(response) {
	        	if(response.length > 0) {
		        	$.each(response, function(index, element) {
		        		$('#obj-id').val(element[0]);
		        		
		        		var instance = CKEDITOR.instances['obj-edesc'];		        		
	                	instance.setData(element[3]);
	                	//instance.resize('100%', '800');
		            });
		        	
		        	EditItem();
	        	}
	        	else {
	        		//Load template
	        		data = {};
	        	    data['option'] = $('#selectx').val();
	        	    data['id'] = $('#req-type-id').val();
	        	    
	        		$.ajax({
	        	        type: "GET",
	        	        url: $('#site-url').val() + "/RequestTypesServlet",
	        	        data: data,
	        	        dataType: "json",
	        	        cache: false,
	        	        success: function(response) {
	        	        	if(response.length > 0) {
	        		        	$.each(response, function(index, element) {
	        		        		var instance = CKEDITOR.instances['obj-desc'];
	        	                	instance.setData(element[3]);
	        	                	instance.resize('100%', '800');
	        		            });
	        	        	}
	        	        },
	        	        error: function(jqXHR, textStatus, errorThrown) {
	        	        	//console.log(textStatus + " " + errorThrown);
	        	        }
	        	    });
	        		
	        		$('#list-area').hide();
		    	    $('#create-area').fadeIn();
		    	    $('#MainSection .panel-title').fadeIn();
	        	}
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        	//console.log(textStatus + " " + errorThrown);
	        }
	    });
	}	
	else
		getMTSSReports();
	
	document.getElementById('erasable').innerHTML = "";
});

function checkWorkflowStatus() {
	var requestTypeID = parseInt($('#req-type-id').val());
	
	data = {};
    data['option'] = $('#check-workflowx').val();
    data['requestTypeID'] = requestTypeID;
    data['roles'] = $('#roles-idx').val();
    data['mdaID'] = $('#mda-idx').val();
    data['userID'] = $('#v5er-idx').val();
    
	$.ajax({
        type: "POST",
        url: $('#site-url').val() + "/RequestApprovalsServlet",
        data: data,
        dataType: "json",
        cache: false,
        async: false,
        success: function(response) {
        	if(response.length > 0) {
	        	$.each(response, function(index, element) {
	        		currentWorkflowStatus = element[0];
	        		
	        		if(currentWorkflowStatus != -1)
	        			$('#request-det-id').val(element[1]);
	        	});
	        		        	
	        	document.getElementById("wfs").value = currentWorkflowStatus;
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function print(element) {
	var editor = CKEDITOR.instances[element];           
    editor.execCommand('print');
}

function createObj() {
	ShowLoading();
	
	var instance = CKEDITOR.instances['obj-desc'];
	
	//Create budget year
    data = {};
    data['option'] = $('#insertx').val();
    data['requestDetailID'] = $("#request-det-id").val();
    data['documentText'] = instance.getData();
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/RequestDocumentsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {
            if(response.indexOf($('#insertedx').val()) !== -1 || response.indexOf($('#updatedx').val()) !== -1) {
            	getMTSSReports();
            	
            	toastr["success"]("Report generated and saved successfully!", "Created");
            	
            	ReturnToList();
            }
            else
            	toastr["error"]("Report generation and saving failed!", "Failure");
            
            StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function getMTSSReports() {
	data = {};
    data['option'] = $('#select-all').val();
    data['requestTypeID'] = $('#req-type-id').val();
    
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/RequestDocumentsServlet",
        data: data,
        dataType: "json",
        success: function(response) {
        	var table = "<table id='main-tablex' class='table table-clean'>" +
	    					"<thead>" +
	        					"<tr>" +
	                                "<th><b>Year</b></th>" +
	                                "<th><b>Report Document</b></th>" +
	                                "<th><b>Action</b></th>" +
	                            "</tr>" +
	                        "</thead>" +
    	            		"<tbody>";
        
        	if(response.length > 0) {
            	$.each(response, function(index, element) {
            		var buttons = '&nbsp;&nbsp;<button title="Edit" onclick="showEditReport(' + element[0] + ');" type="button" class="btn btn-sm btn-info">' +
		  			  			   '<span class="btn-label"><i class="fa fa-edit"></i></span> Edit</button>';
            		
            		var doc_url = '-';
            		if(element[1] != null)
            			doc_url = "<a href='" + $('#site-url').val() + "/" + element[1] + "'>Open Uploaded Report</a>";
            					 
            		table += "<tr><td>MTSS Report: Year " + element[2] + 
            				 "</td><td>" + doc_url + 
            				 "<td>" + buttons + "</td></tr>";
                });
        	}
        	else
        		table += "<tr colspan='5'><td>No MTSS reports yet.</td></tr>";
        	
        	table += "</tbody></table>";
        	
        	document.getElementById("main-table").innerHTML = table;
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function showEditReport(id) {
	$('#obj-id').val(id);
	
	data = {};
    data['option'] = $('#selectx').val();
    data['id'] = id;
	
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/RequestDocumentsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function(response) {
        	$.each(response, function(index, element) {
        		var instance = CKEDITOR.instances['obj-edesc'];
            	instance.setData(element[3]);
            	instance.resize('100%', '800');
            });
        	
        	$('#nav-togglerx').click();
        	
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
    data['documentText'] = instance.getData();
    
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/RequestDocumentsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {                    	
            if(response.indexOf($('#updatedx').val()) !== -1) {
            	CloseEdit();
            	
            	toastr["success"]("Report edited and saved successfully!", "Created");
            	
            	$('#nav-togglerx').click();
            	
            	getMTSSReports();
            }
            else
            	toastr["error"]("Report could not be saved!", "Failure");
            
            StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	StopLoading();
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}