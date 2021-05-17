//check login status, access rights and get menus
checkLogin();

$(document).ready(function () {
	//get all messages
	getObjects();
		    
    //Erase JS from inspection
	document.getElementById('erasable').innerHTML = "";
	unloadJS('erasable');
});

function unloadJS(scriptName) {
	var js = document.getElementById(scriptName);
	js.parentNode.removeChild(js);
}

function getObjects() {
	data = {};
    data['option'] = $('#select-all').val();
    data['userID'] = $('#v5er-idx').val();
    
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
            				 "</td><td>" + (element[4] == 1 ? 'Email' : 'SMS') + 
            				 "</td><td>" + element[5] + "</td></tr>";
            		
            		i++;
                });
        	}
        	else
        		table += "<tr colspan='5'><td>You have no messages yet.</td></tr>";
        	
        	table += "</tbody></table>";
        	
        	document.getElementById("main-table").innerHTML = table;
        	
        	$('#main-tablex').DataTable();
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	//console.log(textStatus + " " + errorThrown);
        }
    });
}