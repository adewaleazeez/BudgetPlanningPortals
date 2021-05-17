//check login status, access rights and get menus
checkLogin();

$(document).ready(function () {
	//get MDAs
	getMDAs();
	
	//get Years
	getYears();
	
	//Close side-menu
	$('#nav-togglerx').click();
		    
    //Erase JS from inspection
	document.getElementById('erasable').innerHTML = "";
	unloadJS('erasable');
});

function unloadJS(scriptName) {
	var js = document.getElementById(scriptName);
	js.parentNode.removeChild(js);
}

function getMDAs() {
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
            });
        	
        	document.getElementById("mda-id").innerHTML = select_options;
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function getYears() {
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/PersonnelCostPlanningServlet",
        data: {option: $('#select-all-years').val()},
        dataType: "json",
        cache: false,
        success: function(response) {
        	var select_options = "";
        	
        	$.each(response, function(index, element) {
        		select_options += "<option value='" + element[0] + "'>" + element[0] + "</option>";
            });
        	
        	document.getElementById("year").innerHTML = select_options;
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

function getReport() {
	var mdaID = $('#mda-id').val();
	var year = $('#year').val();
	
	if(year <= 0) {
		toastr["error"]("Please select a specific year for this report.", "Failure");
		return;
	}
	
	data = {};
    data['option'] = $('#select-all').val();
    data['year'] = year;
    
    if($('#obj-show-all:checked').val() != 'on' && mdaID > 0)
    	data['mdaID'] = mdaID;
    
    if(mdaID <= 0 && $('#obj-show-all:checked').val() != 'on') {
    	toastr["error"]("Please select a MDA or check all.", "Failure");
		return;
    }
    
    ShowLoading();
	$.ajax({
        type: "GET",
        url: $('#site-url').val() + "/PersonnelCostPlanningServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function(response) {
        	var printer = '<button onclick="print();" type="button" class="btn btn-info btn-labeled pull-right">' +
        		          '<span class="btn-label btn-label-left"><i class="fa fa-print"></i></span>Print</button>' +
        		          '<br style="clear: both;" /><br style="clear: both;" />';
        	
        	document.getElementById("divReportBody").innerHTML = printer + response;
        	
        	if($('#obj-show-all:checked').val() != 'on')
        		toastr["success"]("Showing personnel cost report for the chosen MDA.", "Success");
        	else
        		toastr["success"]("Showing personnel cost report for all categorized MDAs.", "Success");
        	
        	StopLoading();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	StopLoading();
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}

/**
 * Print functionality for report in div
 */
function print() {
	$('#divReport').printThis();
}