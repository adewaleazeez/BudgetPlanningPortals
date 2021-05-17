//check login status, access rights and get menus
checkLogin();

$(document).ready(function () {
    //get related meetings
    getRelatedMeetings();

    //get all messages
    getRelatedMessages();
    var roles = $('#roles-idx').val().split(',');
    //if (roles[0] === '1') {
    if (roles.indexOf("1") !== -1) {
        //var indx = $('#roles-idx').val().toString().indexOf("1");
        //alert(indx);
        //if(indx >= 0) {
        //Budget Actuals
        $('#divDash').append('<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">' +
                "<a class='dashboard-stat bg-warning' onclick='syncActuals();'>" +
                '<span class="number counter">Sync</span>' +
                '<span class="name">Budget Actuals (System)</span>' +
                '<span class="bg-icon"><i class="fa fa-history"></i></span>' +
                '</a>' +
                '</div>');

        //Personnel Cost Plan
        $('#divDash').append('<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">' +
                "<a class='dashboard-stat bg-success' onclick='syncPCP();'>" +
                '<span class="number counter">Get</span>' +
                '<span class="name">Personnel Cost (System)</span>' +
                '<span class="bg-icon"><i class="fa fa-sitemap"></i></span>' +
                '</a>' +
                '</div>');
    }

    //Erase JS from inspection
    document.getElementById('erasable').innerHTML = "";
    unloadJS('erasable');
});

function unloadJS(scriptName) {
    var js = document.getElementById(scriptName);
    js.parentNode.removeChild(js);
}

/**
 * Sync Budget Actuals from SAP
 *  
 * @returns
 */
function syncActuals() {
    ShowLoading();

    data = {};
    data['option'] = $('#select-all').val();

    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/SAPActualsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            if (response.length > 0){
                toastr["success"]("Budget actuals synchronized successfully!", "Budget Actuals Synchronization");
            }else{
                toastr["error"]("Something went wrong with synchronizing budget actuals, please contact administrator.", "Budget Actuals Synchronization");
            }
            //stop loading
            StopLoading();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
            //stop loading
            StopLoading();
        }
    });
}

/**
 * Sync Personnel Cost Plan from SAP
 *  
 * @returns
 */
function syncPCP() {
    ShowLoading();

    data = {};
    data['option'] = $('#insertx').val();
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/ZHR_PCP_BudgetServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            if (response.length > 0)
                toastr["success"]("Personnel cost plan synchronized successfully!", "PCP Synchronization");
            else
                toastr["error"]("Something went wrong with synchronizing current personnel cos plan, please contact administrator.", "PCP Synchronization");

            //stop loading
            StopLoading();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //stop loading
            StopLoading();
        }
    });
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
        success: function (response) {
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

            var count = 0;
            if (response.length > 0) {
                $.each(response, function (index, element) {
                    var buttons = '<button onclick="showMeetingDetails(' + element[0] + ');" type="button" class="btn btn-sm btn-success btn-labeled">' +
                            '<span class="btn-label btn-label-left"><i class="fa fa-eye"></i></span>View Details</button>';

                    table += "<tr><td>" + (index + 1) +
                            "</td><td>" + element[1] +
                            "</td><td>" + element[2] +
                            "</td><td>" + element[3] +
                            "</td><td>" + element[4] +
                            "</td><td>" + buttons + "</td></tr>";

                    count++;
                });
            } else
                table += "<tr colspan='5'><td>No budget timetable created yet.</td></tr>";

            table += "</tbody></table>";

            document.getElementById("main-table").innerHTML = table;

            if (response.length > 0)
                $('#main-tablex').DataTable();

            $('#no-of-meetings').html(count);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });
}

function getRelatedMessages() {
    data = {};
    data['option'] = $('#select-all').val();
    data['userID'] = $('#v5er-idx').val();

    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MessagesServlet",
        data: data,
        dataType: "json",
        success: function (response) {
            var table = "<table id='main-tablex2' class='table table-clean'>" +
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

            var count = 0;
            if (response.length > 0) {
                $.each(response, function (index, element) {
                    table += "<tr><td>" + (index + 1) +
                            "</td><td>" + (element[1] == '' ? '-' : element[1]) +
                            "</td><td>" + element[2] +
                            "</td><td>" + (element[4] == 1 ? 'Email' : 'SMS') +
                            "</td><td>" + element[5] + "</td></tr>";

                    count++;
                });
            } else
                table += "<tr colspan='5'><td>You have no messages yet.</td></tr>";

            table += "</tbody></table>";

            document.getElementById("main-table2").innerHTML = table;

            if (response.length > 0)
                $('#main-tablex2').DataTable();

            $('#no-of-messages').html(count);
        },
        error: function (jqXHR, textStatus, errorThrown) {
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
        success: function (response) {
            alert(response);
            $.each(response, function (index, element) {
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
                success: function (response) {
                    $.each(response, function (index, element) {
                        $('#vaddr' + xx).html("<td>" + (xx + 1) + "</td><td><label>" + element[1] + " " + element[2] + " (" + element[3] + ", " + element[4] + ")</label></td><td><label>" + element[5] + "</label></td>");

                        $('#vtab_logic').append('<tr id="vaddr' + (xx + 1) + '"></tr>');
                        xx++;
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
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
                success: function (response) {
                    $.each(response, function (index, element) {
                        $('#vaddre' + yy).html("<td>" + (yy + 1) + "</td><td><label>" + element[2] + "</label></td><td><label>" + element[1] + "</label></td>");

                        $('#vtab_logice').append('<tr id="vaddre' + (yy + 1) + '"></tr>');
                        yy++;
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    //console.log(textStatus + " " + errorThrown);
                }
            });

            $('#divViewMeeting').modal('show');
        },
        error: function (jqXHR, textStatus, errorThrown) {
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
        success: function (response) {
            var table = "<table id='main-tablexx' class='table table-clean'>" +
                    "<thead>" +
                    "<tr>" +
                    "<th><b>Deliberation</b></th>" +
                    "<th><b>Person Responsible</b></th>" +
                    "<th><b>Timeline</b></th>" +
                    "</tr>" +
                    "</thead>" +
                    "<tbody>";

            if (response.length > 0) {
                $.each(response, function (index, element) {
                    table += "<tr><td>" + element[1] +
                            "</td><td>" + element[3] + " " + element[4] + " <small>(" + element[5] + ", " + element[6] + ")</small>" +
                            "</td><td>" + element[2] + "</td></tr>";
                });
            } else
                table += "<tr colspan='3'><td>No deliberaation has been created yet.</td></tr>";

            table += "</tbody></table>";

            document.getElementById("divAllDeliberations").innerHTML = table;

            $('#divViewDeliberations').modal('show');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });
}