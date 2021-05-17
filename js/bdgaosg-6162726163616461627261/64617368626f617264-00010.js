/* global frameWorkMethods */

//check login status, access rights and get menus
checkLogin();

//Check workflow
checkWorkflowStatus();

//For totals calculations
var totalsX = [],
        totalsX2 = [],
        totalsXRec = [],
        totalsXCap = [];

//used for charts budget vs actuals
var currentLineId = '';
var currentTitle = '';
var currentForecastMethod = -1;

var currentWorkflowStatus = -1;
var canUpdate = false;
var controlPanelToggle = 0;
var contingencyValue = 0.0;

Number.prototype.format = function (n, x) {
    var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
    return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$&,');
};

//set years
var currentBaseYear=0;
$(function () {
    //set default values for slider
    var baseYear = 0;

    //Get current budget year
    data = {};
    data['option'] = $('#selectx').val();

    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetYearServlet",
        data: data,
        dataType: "json",
        cache: false,
        async: false,
        success: function (response) {
            $.each(response, function (index, element) {
                baseYear = parseInt(element[2]) - 1;
                currentBaseYear = baseYear;
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });

    
    //var baseYear = parseInt(new Date().getFullYear());
    var fromYear = baseYear - 3;
    var toYear = baseYear + 3;

    var minYear = baseYear - 7;
    var maxYear = baseYear + 8;
            
    $('#current-base-year').val(baseYear);
    $('#current-from-year').val(fromYear);
    $('#current-to-year').val(toYear);

    //initialise range slider
    $("#range").ionRangeSlider({
        type: "double",
        min: minYear,
        max: maxYear,
        from: fromYear,
        from_min: minYear,
        from_max: (baseYear - 1),
        from_shadow: true,
        to: toYear,
        to_min: (baseYear + 1),
        to_max: maxYear,
        to_shadow: true,
        grid: true,
        grid_snap: true,
        prettify_enabled: false,
        hide_min_max: true,
        onChange: function (data) {
            //Get new values
            var baseYear = parseInt($('#current-base-year').val());
            var fromYear = parseInt(data.from);
            var toYear = parseInt(data.to);
            var versionID = parseInt($('#chosen-budget-version-id').val());

            //update values
            $('#current-from-year').val(fromYear);
            $('#current-to-year').val(toYear);

            //Get new previous forward figures
            
            getBudgetVersionFigures(versionID, baseYear, fromYear, toYear);
        }
    });

    //format the pivot year
    var PivotYear = '' + baseYear;
    $('.irs-grid-text').removeClass('pivot_year');
    $('.irs-grid-text:contains(' + PivotYear + ')').addClass('pivot_year');
});

ClosePopover = function () {
    $('#framework-trigger').popover("hide");
};

SaveNewVersion = function () {
    $('#modal_new_version').iziModal('open', this);
};

SubmitVersion = function () {
    $('#modal_submit').iziModal('open', this);
};

ShowOwnPercentageValues = function () {
    $('#modal_own_values').iziModal('open', this);
};

OpenChartModal = function () {
    $('#modal_chart').iziModal('open', this);
};

OpenNarrationModal = function () {
    $('#modal_narration').iziModal('open', this);
};

GenerateReport = function () {
    gotoLink('/dashboard00069');
};

OpenUpdateFigModal = function () {
    $('#modal_update_fig').iziModal('open', this);
};

ViewReport = function () {
    currentWorkflowStatus = document.getElementById("wfs").value;

    if (currentWorkflowStatus === 6) {
        var requestDetailID = $('#request-det-id').val();

        data = {};
        data['option'] = $('#selectx').val();
        data['requestDetailID'] = requestDetailID;

        $.ajax({
            type: "GET",
            url: $('#site-url').val() + "/RequestDocumentsServlet",
            data: data,
            dataType: "json",
            cache: false,
            success: function (response) {
                if (response.length > 0) {
                    $.each(response, function (index, element) {
                        var printButton = '<button onclick="print();" style="position: fixed; right: 200px;" type="button" class="btn btn-info btn-labeled"><span class="btn-label btn-label-left"><i class="fa fa-print"></i></span>Print Report</button><br style="clear: both;" />';

                        document.getElementById('divReport').innerHTML = printButton + element[3];

                        $('#modal_view_report').iziModal('setSubtitle', "For Year - " + element[6]);
                    });

                    $('#modal_view_report').iziModal('open', this);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //console.log(textStatus + " " + errorThrown);
            }
        });
    } else
        gotoLink('/dashboard00069');
};

$(function () {
    $("#long_loading").iziModal();
    $("#modal11").iziModal();
    $("#modal_new_version").iziModal();
    $("#modal_submit").iziModal();
    $("#modal_own_values").iziModal();
    $("#modal_narration").iziModal();
    $("#modal_update_fig").iziModal();
    $("#modal_chart").iziModal({
        fullscreen: true,
        width: 1000
    });
    $("#modal_view_report").iziModal({
        fullscreen: true,
        width: 1000
    });

    // Notification
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

    //setup framework trigger pop-over
    $('#framework-trigger').popover({
        html: true,
        content: function () {
            return getFrameworkParameters(parseInt($('#current-from-year').val()), parseInt($('#current-to-year').val()));
        },
        placement: 'left',
        container: '#blanket_section'
    });

    $('input.line-style').each(function () {
        var self = $(this),
                label = self.next(),
                label_text = label.text();

        label.remove();
        self.iCheck({
            checkboxClass: 'icheckbox_line-red',
            radioClass: 'iradio_line-red',
            insert: '<div class="icheck_line-icon"></div>' + label_text
        });
    });

    $('.budgetItemTrigger').on('click', function () {
        $('#modal11').iziModal('open', this);
    });
});

$(document).ready(function () {
    //Get Budget Versions
    getAllBudgetVersions(true);

    //Set version drop-down change
    $('#mybf-version').change(function () {
        var versionID = $(this).val();

        getSpecificBudgetVersion(versionID);
    });

    //Get framework parameters
    $('#mybf-framework-params').html(getFrameworkParameters(parseInt($('#current-from-year').val()), parseInt($('#current-to-year').val())));

    //Close side-menu
    $('#nav-togglerx').click();

    //Format number inputs with commas
    $(document).on('keyup', '.pointers', function () {
        var x = $(this).val();
        $(this).val(x.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
    });

    //Erase JS from inspection
    document.getElementById('erasable').innerHTML = "";
    document.getElementById('erasable2').innerHTML = "";
});

/**
 * Open or Close control panel
 * 
 * @returns
 */
function toggleControlPanel() {
    $('#blanket_section').toggle("slow", function () {
        if (controlPanelToggle === 0) {
            $('#open-close-tag').html("<i class='fa fa-gear'> </i>Open Control Panel");
            controlPanelToggle += 1;
        } else {
            $('#open-close-tag').html("<i class='fa fa-gear'> </i>Close Control Panel");
            controlPanelToggle -= 1;
        }
    });
}

//Ensure input value contains number(s) only.
function numbvalidate(input) {
    var grep = input.value.replace(/,/g, "");

    if (isNaN(grep)) {
        alert(input.value + " is not a valid value");
        input.value = null;
    }
}

/**
 * Method for locking the current version for any entry
 * 
 * @returns
 */
function lockCurrentBudgetVersion() {
    //disable things
    //document.getElementById("save-mybf-narration").disabled = true;
    //document.getElementById("mybf-submit-final-version").disabled = true;
    //document.getElementById("save-mybf-version").disabled = true;
    //document.getElementById("submit-final").disabled = true;
    //document.getElementById("save-version").disabled = true;
    //document.getElementById("save-framework-params").disabled = true;
}

/**
 * Method for unlocking the current version for modification
 * 
 * @returns
 */
function unlockCurrentBudgetVersion() {
    //enable things
    document.getElementById("save-mybf-narration").disabled = false;
    document.getElementById("mybf-submit-final-version").disabled = false;
    document.getElementById("save-mybf-version").disabled = false;
    document.getElementById("submit-final").disabled = false;
    document.getElementById("save-version").disabled = false;
    document.getElementById("save-framework-params").disabled = false;
}

/**
 * Method for hiding approvals
 * 
 * @returns
 */
function hideApprovals() {
    //disable things
    $("#reject-version").hide();
    $("#approve-version").hide();
}

/**
 * Method for showing approvals
 * 
 * @returns
 */
function showApprovals() {
    //enable things
    $("#reject-version").show();
    $("#approve-version").show();
}

/**
 * Method for hiding submission and save buttons
 * 
 * @returns
 */
function hideSubmission() {
    //disable things
    $("#submit-final").hide();
    $("#save-version").hide();

    $("#mybf-version").attr('disabled', true);
}

/**
 * Approve version
 * 
 * @returns
 */
function ApproveVersion() {
    $('#approve-reject').val(1);

    document.getElementById("btn-approve-reject").innerHTML = 'Approve';

    $('#modal11').iziModal('open', this);
    $('#modal11').iziModal('setSubtitle', 'Approval justification');
}

/**
 * Reject version
 * 
 * @returns
 */
function RejectVersion() {
    $('#approve-reject').val(0);

    document.getElementById("btn-approve-reject").innerHTML = 'Reject';

    $('#modal11').iziModal('open', this);
    $('#modal11').iziModal('setSubtitle', 'Rejection justification');
}

/**
 * Sync Budget Actuals from SAP
 *  
 * @returns
 */
function SyncActuals() {
    ShowLoading();

    data = {};
    data['option'] = $('#select-all').val();
    data['versionID'] = $('#chosen-budget-version-id').val();
    if($('#chosen-budget-version-id').val()==="-1"){
        toastr["error"]("You must select a budget version to synchronize budget actuals", "No Budget Version Selected ");
        StopLoading();
        return true;
    }

    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/SAPActualsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            if (response.length > 0) {
                getSpecificBudgetVersion($('#chosen-budget-version-id').val());

                toastr["success"]("Budget actuals synchronized successfully!", "Budget Actuals Synchronization");
            } else
                toastr["error"]("Something went wrong with synchronizing budget actuals, please contact administrator.", "Budget Actuals Synchronization");

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
 * Sync Approved Budget from App
 *  
 * @returns
 */
function SyncApprovedBudgets() {
    ShowLoading();

    data = {};
    data['option'] = $('#sync-approved-budget').val();
    data['year_id'] = $('#current-base-year').val();
    data['version_id'] = $('#chosen-budget-version-id').val();
    data['user_id'] = $('#v5er-idx').val();
    if($('#chosen-budget-version-id').val()==="-1"){
        toastr["error"]("You must select a budget version to synchronize approved budget", "No Budget Version Selected ");
        StopLoading();
        return true;
    }
    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/MybfFiguresServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            //alert(response);
            //stop loading
            StopLoading();
            if (response.length > 0) {
                getSpecificBudgetVersion($('#chosen-budget-version-id').val());
                toastr["success"]("Approved Budget synchronized successfully!", "Approved Budget Synchronization");
            } else
                toastr["error"]("Something went wrong with synchronizing approved budget, please contact administrator.", "Approved Budget Synchronization");

        },
        error: function (jqXHR, textStatus, errorThrown) { 
            //alert(errorThrown);
            //console.log(textStatus + " " + errorThrown);
            //stop loading
            StopLoading();
        }
    });
}

/**
 * Method for getting the lock status of the current chosen budget version
 * 
 * @returns
 */
function getBudgetVersionLockStatus() {
    var versionID = parseInt($('#chosen-budget-version-id').val());

    data = {};
    data['option'] = $('#selectx').val();
    data['id'] = versionID;

    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetVersionStatusServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function (response) {
            if (response.length > 0) {
                var currentStatus = -1;

                $.each(response, function (index, element) {
                    currentStatus = element[2];
                });

                if (currentStatus === 1)
                    unlockCurrentBudgetVersion();
                else {
                    $('#llllll').val(1);
                    lockCurrentBudgetVersion();
                }
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });
}

function checkWorkflowStatus() {
    var roles = $('#roles-idx').val().split(',');
    if (roles[0] !== '1') {
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
            success: function (response) {
                //alert(response);
                if (response.length > 0) {
                    $.each(response, function (index, element) {
                        currentWorkflowStatus = element[0];

                        if (currentWorkflowStatus !== -1)
                            $('#request-det-id').val(element[1]);
                    });
                    //alert(currentWorkflowStatus);
                    //set 
                    currentWorkflowStatus = 2;
                    if (currentWorkflowStatus === -1) {
                        toastr["error"]("You are not authorized to view this page, please contact admin.", "Failure");

                        gotoLink('/codem00017');
                        /*}
                         else {
                         hideSubmission();
                         hideApprovals();
                         
                         $("#view-report").show();
                         $("#mybf-version").attr('disabled', false);
                         
                         canUpdate = true;
                         $("#save-version").show();
                         }*/
                    } else if (currentWorkflowStatus === 0 || currentWorkflowStatus === 3) {
                        hideSubmission();
                        hideApprovals();
                    } else if (currentWorkflowStatus === 1) {
                        hideSubmission();
                        showApprovals();
                    } else if (currentWorkflowStatus === 2) {
                        hideApprovals();

                        canUpdate = true;
                    } else if (currentWorkflowStatus === 4 ||
                            currentWorkflowStatus === 5 ||
                            currentWorkflowStatus === 6) {
                        document.getElementById("submit-final").disabled = true;
                        document.getElementById("save-version").disabled = true;

                        hideSubmission();
                        hideApprovals();
                        
                        showApprovals();
                        canUpdate = false;

                        //show generate report
                        if (currentWorkflowStatus === 4)
                            $("#generate-report").show();
                        else if (currentWorkflowStatus === 5 || currentWorkflowStatus === 6)
                            $("#view-report").show();
                    }
//alert(currentWorkflowStatus);
                        
                    document.getElementById("wfs").value = currentWorkflowStatus;
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                                //console.log(textStatus + " " + errorThrown);
            }
        });
    };
}

/**
 * Print functionality for report in div
 */
function print() {
    $('#divReport').printThis();
}

/**
 * Method for approving or rejecting budget versions
 * 
 * @returns
 */
function approveOrReject() {
    $('#modal11').iziModal('startLoading');

    //ShowLoading();

    var currentRequestDetailID = $('#request-det-id').val();
    var requestTypeID = parseInt($('#req-type-id').val());

    var approvalStatus = -1;
    var approvalStatusText = '';
    if (parseInt($('#approve-reject').val()) === 0) {
        approvalStatus = parseInt($('#rejection-status').val());
        approvalStatusText = 'rejected';
    } else {
        approvalStatus = parseInt($('#approval-status').val());
        approvalStatusText = 'approved';
    }

    //Gather all data
    data = {};
    data['option'] = $('#insertx').val();
    data['userID'] = $('#v5er-idx').val();
    data['approvalStatus'] = approvalStatus;
    data['requestDetailID'] = currentRequestDetailID;
    data['requestTypeID'] = requestTypeID;
    data['comment'] = $('#workflow-comment').val();
    data['mdaID'] = $('#mda-idx').val();
    data['roles'] = $('#roles-idx').val();
    data['budgetVersionID'] = $('#chosen-budget-version-id').val();
    data['year'] = $('#current-base-year').val();

    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/RequestApprovalsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            if (response.indexOf($('#insertedx').val()) !== -1) {
                lockCurrentBudgetVersion();
                hideApprovals();

                toastr["success"]("Forecast estimates " + approvalStatusText + " successfully!", "Created");
            } else
                toastr["error"]("Forecast estimates " + approvalStatusText + " failed!", "Failure");

            //close loading
            $('#modal11').iziModal('stopLoading');
            $('#modal11').iziModal('close');
            //StopLoading();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);

            $('#modal11').iziModal('stopLoading');
            $('#modal11').iziModal('close');
        }
    });
}

/**
 * Method for submitting current version figures for approval work-flow
 * 
 * @returns
 */
function submitVersionFigures() {
    $('#modal_submit').iziModal('startLoading');

    //create budget version status
    data = {};
    data['option'] = $('#insertx').val();
    data['budgetVersionID'] = $('#chosen-budget-version-id').val();
    data['budgetStatusID'] = 2;
    data['year'] = $('#current-base-year').val();

    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetVersionStatusServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            if (response.indexOf($('#insertedx').val()) !== -1) {
                var requestTypeID = parseInt($('#req-type-id').val());

                //Create request transaction
                data = {};
                data['option'] = $('#insertx').val();
                data['itemUrl'] = '/dashboard00010';
                data['requiresApproval'] = true;
                data['versionID'] = $('#chosen-budget-version-id').val();
                data['requestTypeID'] = requestTypeID;
                data['roles'] = $('#roles-idx').val();
                data['mdaID'] = $('#mda-idx').val();
                data['year'] = $('#current-base-year').val();

                $.ajax({
                    type: "POST",
                    url: $('#site-url').val() + "/RequestDetailsServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    success: function (response) {
                        if (response.indexOf($('#insertedx').val()) !== -1) {
                            lockCurrentBudgetVersion();

                            toastr["success"]("Estimates submitted successfully!", "Created");
                        } else
                            toastr["error"]("Estimates submission failed. This Version already submited!", "Failure");
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        //console.log(textStatus + " " + errorThrown);
                    }
                });
            } else
                toastr["error"]("Estimates submission failed. This Version already submited!", "Failure");

            $('#modal_submit').iziModal('stopLoading');
            $('#modal_submit').iziModal('close');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });
}

/**
 * Method for new version for current budget year
 * @returns
 */
function CreateNewVersionYear() {
    //show loading
    $('#long_loading').iziModal('open', this);
}

/**
 * Method for starting the new budget year creation process
 * 
 * @returns
 */
function startNewBudgetYearProcess() {
    $('#long_loading').iziModal('startLoading');

    ShowLoading();

    //set variables
    var isSuccess = true;
    var notif_header = 'Success';
    var notif_msg = 'Budget version for current year created successfully!';

    //change base year to icy
    var prevBaseYear = parseInt($('#current-base-year').val());
    var baseYear = parseInt($('#icy-base-year').val());

    $('#current-base-year').val(baseYear);
    $('#current-from-year').val(baseYear - 3);
    $('#current-to-year').val(baseYear + 3);

    //Save version info
    data = {};
    data['option'] = $('#insertx').val();
    data['name'] = "Initial version - Legacy - " + baseYear;
    data['description'] = "The initial default version";
    data['mtefFromYear'] = $('#current-from-year').val();
    data['mtefToYear'] = $('#current-to-year').val();
    data['year'] = $('#current-base-year').val();

    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetVersionsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            var versionID = parseInt(response);
            if (versionID >= 1) {
                //Create base year values
                $(".mybf-fig.figx").each(function () {
                    var xId = $(this).attr('id').split('-');

                    var span = $(this).find('span');
                    var xValue = 0;
                    if (span.length)
                        xValue = parseFloat(span.html().replace(/,/g, ""));
                    else
                        xValue = parseFloat($(this).html().replace(/,/g, ""));

                    var xVal = xValue;

                    xId[4] = parseInt($('#current-base-year').val());

                    if (xId[4] === parseInt($('#current-base-year').val())) {
                        data = {};
                        data['option'] = $('#insertx').val();
                        data['mtefBoVersionID'] = -1;
                        data['budgetValue'] = 0.0;
                        data['chartOfAccount'] = '-';
                        data['budgetLine'] = 'baseYearValue' + xId[4];
                        data['userID'] = $('#v5er-idx').val();
                        data['year'] = xId[4];
                        data['budgetTypeComponentID'] = xId[1];
                        data['budgetVersionID'] = versionID;
                        data['budgetStatusID'] = 1;
                        data['frameworkMethodID'] = 1;
                        data['yearTo'] = $('#current-to-year').val();

                        $.ajax({
                            type: "POST",
                            url: $('#site-url').val() + "/MybfFiguresServlet",
                            data: data,
                            dataType: "text",
                            cache: false,
                            success: function (response2) {
                                var mybfFigureID = parseInt(response2);

                                if (mybfFigureID >= 1) {
                                    var selector = xId[0] + '-' + xId[1] + '-' + xId[2];

                                    //Create previous forward values
                                    $(".mybf-fig[id^='" + selector + "']").each(function () {
                                        var xId2 = $(this).attr('id').split('-');

                                        var span = $(this).find('span');
                                        var xValue = 0;
                                        if (span.length)
                                            xValue = parseFloat(span.html().replace(/,/g, ""));
                                        else
                                            xValue = parseFloat($(this).html().replace(/,/g, ""));

                                        var xVal2 = xValue;

                                        //Check for line for current MTEF figure ID
                                        if (parseInt(xId2[4]) !== parseInt($('#current-base-year').val())) {
                                            if (parseInt(xId2[4]) === prevBaseYear)
                                                xVal2 = xVal;

                                            data = {};
                                            data['option'] = $('#insertx').val();
                                            data['budgetYear'] = xId2[4];
                                            data['budgetValue'] = xVal2;
                                            data['mybfFigureID'] = mybfFigureID;

                                            $.ajax({
                                                type: "POST",
                                                url: $('#site-url').val() + "/MybfPreviousForwardServlet",
                                                data: data,
                                                dataType: "text",
                                                cache: false,
                                                error: function (jqXHR, textStatus, errorThrown) {
                                                    //console.log(textStatus + " " + errorThrown);
                                                }
                                            });
                                        }
                                    });

                                    //for immediate previous
                                    data = {};
                                    data['option'] = $('#insertx').val();
                                    data['budgetYear'] = $('#current-to-year').val();
                                    data['budgetValue'] = 0.0;
                                    data['mybfFigureID'] = mybfFigureID;

                                    $.ajax({
                                        type: "POST",
                                        url: $('#site-url').val() + "/MybfPreviousForwardServlet",
                                        data: data,
                                        dataType: "text",
                                        cache: false,
                                        error: function (jqXHR, textStatus, errorThrown) {
                                            //console.log(textStatus + " " + errorThrown);
                                        }
                                    });
                                }
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                //console.log(textStatus + " " + errorThrown);
                            }
                        });
                    }
                });

                toastr["success"](notif_msg, notif_header);
            } else {
                isSuccess = false;
                notif_header = 'Failure';
                notif_msg = 'Budget version for new year could not be saved!';

                toastr["error"](notif_msg, notif_header);
            }

            //close loading
            $('#long_loading').iziModal('close');
            StopLoading();

            //get all versions and select the latest
            getAllBudgetVersions(true);
        }
    });
}

function getAllBudgetVersions(isNewPageLoad) {
    ShowLoading();                            
                          
    var current_year = 0;

    //Get current budget year
    data = {};
    data['option'] = $('#selectx').val();

    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetYearServlet",
        data: data,
        dataType: "json",
        cache: false,
        async: false,
        success: function (response) {
            $.each(response, function (index, element) {
                current_year = parseInt(element[2]);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });

    //Get All Saved Budget Versions
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetVersionsServlet",
        data: {option: $('#select-all').val()},
        dataType: "json",
        cache: false,
        success: function (response) {
            if (response.length > 0) {
                var select_options = "";
                var baseYear = 0, fromYear = 0, toYear = 0, versionID = -1;

                $.each(response, function (index, element) {
                    select_options += "<option value='" + element[0] + "'>" + element[1] + " " +
                            "<small>(Date Created: " + element[5] + ")</small></option>";
                    if (index === 0) {
                        baseYear = parseInt(element[2]);
                        fromYear = parseInt(element[3]);
                        toYear = parseInt(element[4]);
                        versionID = element[0];
                        contingencyValue = element[7];
                        //set budget version
                        $('#chosen-budget-version-id').val(versionID);
                        if (current_year > baseYear && response.length===0) {
                            $('#divNewYear').show();
                            $('#icy').val(0);
                            $('#icy-base-year').val(current_year);

                            //disable things
                            lockCurrentBudgetVersion();
                        } else {
                            //set values
                            $('#divNewYear').hide();
                            $('#icy').val(1);
                            $('#icy-base-year').val('');

                            $('#modal_submit').iziModal('setSubtitle', element[1]);

                            //Check lock status on the current version
                            getBudgetVersionLockStatus();
                        }
                    }
                });

                $("#range").data("ionRangeSlider").update({
                    from: fromYear,
                    to: toYear,
                    from_max: (baseYear - 1),
                    to_min: (baseYear + 1)
                });

                //format the pivot year
                var PivotYear = '' + baseYear + '';
                $('.irs-grid-text').removeClass('pivot_year');
                $('.irs-grid-text:contains(' + PivotYear + ')').addClass('pivot_year');

                //set years
                

                $('#current-base-year').val(baseYear);
                $('#current-from-year').val(parseInt(fromYear));
                $('#current-to-year').val(parseInt(toYear));

                //get figures
                if (isNewPageLoad === true)
                    getBudgetVersionFigures(versionID, baseYear, fromYear, toYear);
                else
                    StopLoading();

                document.getElementById("mybf-version").innerHTML = select_options;
            } else {
                var baseYear = parseInt($('#current-base-year').val());
                $('#icy-base-year').val(baseYear);
                //set budget version
                $('#chosen-budget-version-id').val(-1);

                //get figures
                //alert(baseYear);
                getBudgetVersionFigures(-1, baseYear, baseYear - 3, baseYear + 3);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
            //alert(textStatus + " " + errorThrown);
        }
    });
}

function getSpecificBudgetVersion(versionID) {
    $('#chosen-budget-version-id').val(versionID);

    data = {};
    data['option'] = $('#selectx').val();
    data['id'] = versionID;

    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetVersionsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success: function (response) {
            var baseYear, fromYear, toYear;

            $.each(response, function (index, element) {
                baseYear = parseInt(element[2]);
                fromYear = parseInt(element[3]);
                toYear = parseInt(element[4]);
                contingencyValue = element[7];
            });

            $("#range").data("ionRangeSlider").update({
                from: fromYear,
                to: toYear,
                from_max: (baseYear - 1),
                to_min: (baseYear + 1)
            });

            //format the pivot year
            var PivotYear = '' + baseYear + '';
            $('.irs-grid-text').removeClass('pivot_year');
            $('.irs-grid-text:contains(' + PivotYear + ')').addClass('pivot_year');

            //set years
            $('#current-base-year').val(baseYear);
            $('#current-from-year').val(fromYear);
            $('#current-to-year').val(toYear);

            //lock status
            getBudgetVersionLockStatus();

            //get figures
            ShowLoading();
            getBudgetVersionFigures(versionID, baseYear, fromYear, toYear);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });
}

/**
 * Method for getting the figures for any selected version
 * 
 * @param versionID
 * @param baseYear
 * @param fromYear
 * @param toYear
 * @returns
 */
function getBudgetVersionFigures(versionID, baseYear, fromYear, toYear) {
    var tableBudget = "";
    var tableActual = "";
    var budgetTypeID = 0;
    var icy = parseInt($('#icy').val());

    var roles = $('#roles-idx').val().split(',');

    var editable = '';
    if (icy === 1 || roles[0] === '1')
        editable = 'editable-control ';

    var budgetForActual = 0.00;
    //First of All, Get Budget Types
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/BudgetTypesServlet",
        data: {option: $('#select-all').val()},
        dataType: "json",
        cache: false,
        success: function (response) {
            //alert(response);
            if (response.length > 0) {
                $.each(response, function (index, element) {
                    budgetTypeID = element[0];

                    tableBudget = '<h3 class="mt-20">' + element[1] + '<small class="text-danger"><strong> Budget</strong></small> </h3>' +
                            '<table id="mybf-budget-' + budgetTypeID + '" class="table table-bordered table-striped" style="clear: both"><thead>' +
                            '<tr class="tit"><th width="20%">Budget Code</th>';

                    tableActual = '<h3 class="mt-20">' + element[1] + '<small class="text-danger"><strong> Actual</strong></small> </h3>' +
                            '<table id="mybf-actual-' + budgetTypeID + '" class="table table-bordered table-striped" style="clear: both"><thead>' +
                            '<tr class="tit"><th width="20%">Budget Code</th>';

                    //Get table headers
                    for (i = 2010; i <= toYear; i++) {
                        if (i === baseYear) {
                            tableBudget += '<th class="edit-column">' + i + ' (&#8358;)</th>';
                            tableActual += '<th class="edit-column">' + i + ' (&#8358;)</th>';
                        } else {
                            if (i < fromYear) {
                                tableBudget += '<th style="display:none"></th>';
                                tableActual += '<th style="display:none"></th>';
                            } else {
                                tableBudget += '<th>' + i + ' (&#8358;)</th>';
                                tableActual += '<th>' + i + ' (&#8358;)</th>';
                            }
                        }
                    }

                    if (icy === 1){
                        tableActual += '<th width="10%">';
                    } else {
                        tableActual += '<th width="10%" style="display:none;">';
                    }
                    tableActual += '</th><th width="5%"></th><th width="5%"></th></tr></thead><tbody>';
                    tableBudget += '<th width="10%" style="display:none;"></th><th width="5%"></th><th width="5%"></th></tr></thead><tbody>';

                    //Get Budget Components
                    data = {};
                    data['option'] = $('#select-all').val();
                    data['budgetTypeID'] = budgetTypeID;

                    $.ajax({
                        type: "GET",
                        url: $('#site-url').val() + "/BudgetTypeComponentsServlet",
                        data: data,
                        dataType: "json",
                        cache: false,
                        async: false,
                        success: function (response1) {
                            //console.log(response1);
                            var mybfFigureID = 1;
                            var baseYearValue = 0.00;
                            var frameworkMethodID = 0;

                            if (response1.length > 0) {
                                $.each(response1, function (index1, element1) {
                                    var isBudget = element1[3];
                                    var budgetLine = element1[1];
                                    var budgetTypeComponentID = element1[0];
                                    var budgetTypeComponentTypeID = element1[6];

                                    if (isBudget === true)
                                        tableBudget += '<tr><td>' + budgetLine + '</td>';
                                    else
                                        tableActual += '<tr><td>' + budgetLine + '</td>';

                                    //Get MTEF Figures per Component
                                    data = {};
                                    data['option'] = $('#selectx').val();
                                    data['budgetTypeComponentID'] = budgetTypeComponentID;
                                    data['budgetVersionID'] = versionID;
                                    data['year'] = baseYear;
                                    $.ajax({
                                        type: "GET",
                                        url: $('#site-url').val() + "/MybfFiguresServlet",
                                        data: data,
                                        dataType: "json",
                                        cache: false,
                                        async: false,
                                        success: function (response2) {
                                            //console.log(response2);
                                            if (response2.length > 0) {
                                                $.each(response2, function (index2, element2) {
                                                    mybfFigureID = element2[0];
                                                    baseYearValue = parseFloat(element2[6]);
                                                    frameworkMethodID = element2[10];

                                                    //Get Previous and forward figures for current MTEF figure
                                                    data = {};
                                                    data['option'] = $('#select-all').val();
                                                    data['mybfFigureID'] = mybfFigureID;
                                                    data['budgetTypeComponentID'] = budgetTypeComponentID;
                                                    data['yearFrom'] = 2010;//fromYear - 3
                                                    data['yearTo'] = toYear;
                                                    $.ajax({
                                                        type: "GET",
                                                        url: $('#site-url').val() + "/MybfPreviousForwardServlet",
                                                        data: data,
                                                        dataType: "json",
                                                        cache: false,
                                                        async: false,
                                                        success: function (response3) {
                                                            //console.log(response3);
                                                            
                                                            if (response3.length > 0) {
                                                                var prev_forwardYears = [];
                                                                var prev_forwardValues = [];
                                                                
                                                                        
                                                                $.each(response3, function (index3, element3) {
                                                                    prev_forwardYears.push(element3[1]);
                                                                    prev_forwardValues.push(parseFloat(element3[2]));
                                                                });

                                                                for (j = 2010; j <= toYear; j++) {
                                                                    var xValue = 0.00;

                                                                    for (x = 0; x < prev_forwardYears.length; x++) {
                                                                        if (prev_forwardYears[x] === j) {
                                                                            xValue = prev_forwardValues[x];
                                                                            break;
                                                                        }
                                                                    }

                                                                    if (j < baseYear) {
                                                                        if (isBudget === true) {
                                                                            if (j < fromYear)
                                                                                tableBudget += '<td style="display:none">';
                                                                            else
                                                                                tableBudget += '<td>';

                                                                            tableBudget += '<a class="rowDataSd mybf-fig" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + j + '-' + budgetTypeComponentTypeID + '">' +
                                                                                    xValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        } else {
                                                                            if (j < fromYear)
                                                                                tableActual += '<td style="display:none">';
                                                                            else
                                                                                tableActual += '<td>';

                                                                            tableActual += '<a class="rowDataSd mybf-fig" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + j + '-' + budgetTypeComponentTypeID + '">' +
                                                                                    xValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        }
                                                                    } else if (j === baseYear) {
                                                                        if (isBudget === true) { //' + editable + ' href="#" 
                                                                            budgetForActual = baseYearValue;
                                                                            tableBudget += '<td class="edit-column">' +
                                                                                    '<a class="rowDataSd mybf-fig figx" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '-' + budgetTypeComponentTypeID + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                                    baseYearValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        } else {//' + editable + ' href="#" 
                                                                            baseYearValue = budgetForActual;
                                                                            budgetForActual = 0.00;
                                                                            tableActual += '<td class="edit-column">' +
                                                                                    '<a class="rowDataSd mybf-fig figx" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '-' + budgetTypeComponentTypeID + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                                    baseYearValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        }
                                                                    } else if (j > baseYear) {
                                                                        if (isBudget === true) {
                                                                            tableBudget += '<td>' +
                                                                                    '<a class="rowDataSd ' + editable + 'mybf-fig" href="#" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '-' + budgetTypeComponentTypeID + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                                    xValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        } else {
                                                                            tableActual += '<td>' +
                                                                                    '<a class="rowDataSd ' + editable + 'mybf-fig" href="#" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '-' + budgetTypeComponentTypeID + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                                    xValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                for (j = 2010; j <= toYear; j++) {
                                                                    var xValue = 0.00;
                                                                    baseYearValue = 0.00;

                                                                    if (j < baseYear) {
                                                                        if (isBudget === true) {
                                                                            if (j < fromYear)
                                                                                tableBudget += '<td style="display:none">';
                                                                            else
                                                                                tableBudget += '<td>';

                                                                            tableBudget += '<a class="rowDataSd mybf-fig" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + j + '-' + budgetTypeComponentTypeID + '">' +
                                                                                    xValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        } else {
                                                                            if (j < fromYear)
                                                                                tableActual += '<td style="display:none">';
                                                                            else
                                                                                tableActual += '<td>';

                                                                            tableActual += '<a class="rowDataSd mybf-fig" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + j + '-' + budgetTypeComponentTypeID + '">' +
                                                                                    xValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        }
                                                                    } else if (j === baseYear) {
                                                                        if (isBudget === true) { //' + editable + ' href="#" 
                                                                            tableBudget += '<td class="edit-column">' +
                                                                                    '<a class="rowDataSd mybf-fig figx" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '-' + budgetTypeComponentTypeID + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                                    baseYearValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        } else { //' + editable + ' href="#" 
                                                                            tableActual += '<td class="edit-column">' +
                                                                                    '<a class="rowDataSd mybf-fig figx" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '-' + budgetTypeComponentTypeID + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                                    baseYearValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        }
                                                                    } else if (j > baseYear) {
                                                                        if (isBudget === true) {
                                                                            tableBudget += '<td>' +
                                                                                    '<a class="rowDataSd ' + editable + 'mybf-fig" href="#" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                                    xValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        } else {
                                                                            tableActual += '<td>' +
                                                                                    '<a class="rowDataSd ' + editable + 'mybf-fig" href="#" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                                    xValue.format(2) +
                                                                                    '</a>' +
                                                                                    '</td>';
                                                                        }
                                                                    }
                                                                }
                                                            }
//alert(frameWorkMethods);
                                                            if (isBudget === true) {
                                                                tableBudget += '<td style="display: none"><select data-method="' + frameworkMethodID + '" class="custom_selectx" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + budgetTypeComponentTypeID + '-' + baseYear + '-sel' + '">' + frameWorkMethods + '</select></td>' +
                                                                        '<td><a data-title="' + budgetLine + '" title="View Chart" onclick="showChartModal(this, \'' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + budgetTypeComponentTypeID + '\');" class="btn btn-sm">' +
                                                                        '<i class="fa fa-bar-chart"></i></span></a></td>' +
                                                                        '<td><a data-title="' + budgetLine + '" title="View Narration" onclick="showNarrationModal(this, \'' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + budgetTypeComponentTypeID + '\');" class="btn btn-sm">' +
                                                                        '<i class="fa fa-comment"></i></span></a></td></tr>';
                                                                //console.log("1 "+tableBudget);
                                                            } else {
                                                                if (icy === 1)
                                                                    tableActual += '<td>';
                                                                else
                                                                    tableActual += '<td>';

                                                                tableActual += '<select data-method="' + frameworkMethodID + '" class="custom_selectx" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + budgetTypeComponentTypeID + '-' + baseYear + '-sel' + '">' + frameWorkMethods + '</select></td>' +
                                                                        '<td><a data-title="' + budgetLine + '" title="View Chart" onclick="showChartModal(this, \'' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + budgetTypeComponentTypeID + '\');" class="btn btn-sm">' +
                                                                        '<i class="fa fa-bar-chart"></i></span></a></td>' +
                                                                        '<td><a data-title="' + budgetLine + '" title="View Narration" onclick="showNarrationModal(this, \'' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + budgetTypeComponentTypeID + '\');" class="btn btn-sm">' +
                                                                        '<i class="fa fa-comment"></i></span></a></td></tr>';
                                                                //console.log("2 "+tableBudget);
                                                            }
                                                        },
                                                        error: function (jqXHR, textStatus, errorThrown) {
                                                            //console.log(textStatus + " " + errorThrown);
                                                        }
                                                    });
                                                });
                                            } else {
                                                for (j = 2010; j <= toYear; j++) {
                                                    var xValue = 0.00;

                                                    if (j < baseYear) {
                                                        if (isBudget === true) {
                                                            if (j < fromYear)
                                                                tableBudget += '<td style="display:none">';
                                                            else
                                                                tableBudget += '<td>';

                                                            tableBudget += '<a class="rowDataSd mybf-fig" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + j + '-' + budgetTypeComponentTypeID + '">' +
                                                                    xValue.format(2) +
                                                                    '</a>' +
                                                                    '</td>';
                                                        } else {
                                                            if (j < fromYear)
                                                                tableActual += '<td style="display:none">';
                                                            else
                                                                tableActual += '<td>';

                                                            tableActual += '<a class="rowDataSd mybf-fig" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + j + '-' + budgetTypeComponentTypeID + '">' +
                                                                    xValue.format(2) +
                                                                    '</a>' +
                                                                    '</td>';
                                                        }
                                                    } else if (j === baseYear) {
                                                        if (isBudget === true) {
                                                            tableBudget += '<td class="edit-column">' +
                                                                    '<a class="rowDataSd ' + editable + 'mybf-fig figx" href="#" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '-' + budgetTypeComponentTypeID + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                    baseYearValue.format(2) +
                                                                    '</a>' +
                                                                    '</td>';
                                                        } else {
                                                            tableActual += '<td class="edit-column">' +
                                                                    '<a class="rowDataSd ' + editable + 'mybf-fig figx" href="#" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '-' + budgetTypeComponentTypeID + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                    baseYearValue.format(2) +
                                                                    '</a>' +
                                                                    '</td>';
                                                        }
                                                    } else if (j > baseYear) {
                                                        if (isBudget === true) {
                                                            tableBudget += '<td>' +
                                                                    '<a class="rowDataSd ' + editable + 'mybf-fig" href="#" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '-' + budgetTypeComponentTypeID + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                    xValue.format(2) +
                                                                    '</a>' +
                                                                    '</td>';
                                                        } else {
                                                            tableActual += '<td>' +
                                                                    '<a class="rowDataSd ' + editable + 'mybf-fig" href="#" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + j + '-' + budgetTypeComponentTypeID + '" ' +
                                                                    'data-name="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + j + '-' + budgetTypeComponentTypeID + '" data-type="text" data-pk="1" data-title="' + budgetLine + '">' +
                                                                    xValue.format(2) +
                                                                    '</a>' +
                                                                    '</td>';
                                                        }
                                                    }

                                                    mybfFigureID++;
                                                }

                                                if (isBudget === true) {
                                                    tableBudget += '<td style="display: none"><select data-method="' + frameworkMethodID + '" class="custom_selectx" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + budgetTypeComponentTypeID + '-' + baseYear + '-sel' + '">' + frameWorkMethods + '</select></td>' +
                                                            '<td><a data-title="' + budgetLine + '" title="View Chart" onclick="showChartModal(this, \'' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + budgetTypeComponentTypeID + '\');" class="btn btn-sm">' +
                                                            '<i class="fa fa-bar-chart"></i></span></a></td>' +
                                                            '<td><a data-title="' + budgetLine + '" title="View Narration" onclick="showNarrationModal(this, \'' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 1 + '-' + budgetTypeComponentTypeID + '\');" class="btn btn-sm">' +
                                                            '<i class="fa fa-comment"></i></span></a></td></tr>';
                                                    //console.log("3 "+frameWorkMethods);
                                                } else {
                                                    if (icy === 1)
                                                        tableActual += '<td>';
                                                    else
                                                        tableActual += '<td>';

                                                    tableActual += '<select data-method="' + frameworkMethodID + '" class="custom_selectx" id="' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + budgetTypeComponentTypeID + '-' + baseYear + '-sel' + '">' + frameWorkMethods + '</select></td>' +
                                                            '<td><a data-title="' + budgetLine + '" title="View Chart" onclick="showChartModal(this, \'' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + budgetTypeComponentTypeID + '\');" class="btn btn-sm">' +
                                                            '<i class="fa fa-bar-chart"></i></span></a></td>' +
                                                            '<td><a data-title="' + budgetLine + '" title="View Narration" onclick="showNarrationModal(this, \'' + budgetTypeID + '-' + budgetTypeComponentID + '-' + mybfFigureID + '-' + 0 + '-' + budgetTypeComponentTypeID + '\');" class="btn btn-sm">' +
                                                            '<i class="fa fa-comment"></i></span></a></td></tr>';
                                                    //console.log("4 "+frameWorkMethods);
                                                }
                                            }
                                        },
                                        error: function (jqXHR, textStatus, errorThrown) {
                                            //console.log(textStatus + " " + errorThrown);
                                        }
                                    });
                                });
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            //console.log(textStatus + " " + errorThrown);
                        }
                    });

                    //Setup Total Recurrent Line
                    tableBudget += '<tr class="totrec"><td><b>Total Recurrent</td>';
                    tableActual += '<tr class="totrec"><td><b>Total Recurrent</td>';
                    var i = 0;
                    for (j = 2010; j <= toYear; j++) {
                        if (j === baseYear) {
                            tableBudget += '<td class="totcolrec edit-column">0.00</td>';
                            tableActual += '<td class="totcolrec edit-column">0.00</td>';
                        } else {
                            if (j < fromYear) {
                                tableBudget += '<td style="display:none" class="totcolrec">0.00</td>';
                                tableActual += '<td style="display:none" class="totcolrec">0.00</td>';
                            } else {
                                tableBudget += '<td class="totcolrec">0.00</td>';
                                tableActual += '<td class="totcolrec">0.00</td>';
                            }
                        }

                        totalsX[i] = 0;
                        totalsX2[i] = 0;
                        totalsXRec[i] = 0;
                        totalsXCap[i] = 0;
                        i++;
                    }
                    tableBudget += '</tr>';
                    tableActual += '</tr>';

                    //Setup Total Capital Line
                    tableBudget += '<tr class="totcap"><td><b>Total Capital</td>';
                    tableActual += '<tr class="totcap"><td><b>Total Capital</td>';
                    var i = 0;
                    for (j = 2010; j <= toYear; j++) {
                        if (j === baseYear) {
                            tableBudget += '<td class="totcolcap edit-column">0.00</td>';
                            tableActual += '<td class="totcolcap edit-column">0.00</td>';
                        } else {
                            if (j < fromYear) {
                                tableBudget += '<td style="display:none" class="totcolcap">0.00</td>';
                                tableActual += '<td style="display:none" class="totcolcap">0.00</td>';
                            } else {
                                tableBudget += '<td class="totcolcap">0.00</td>';
                                tableActual += '<td class="totcolcap">0.00</td>';
                            }
                        }
                    }
                    tableBudget += '</tr>';
                    tableActual += '</tr>';

                    //Setup Grand Total Line
                    tableBudget += '<tr class="tot"><td><b>Total ' + (budgetTypeID === 1 ? 'Revenue <span style="font-size:12px; color:red;">(Budget)</span>' :
                            'Expenditure <span style="font-size:12px; color:red;">(Budget)</span>') + '</b></td>';
                    tableActual += '<tr class="tot"><td><b>Total ' + (budgetTypeID === 1 ? 'Revenue <span style="font-size:12px; color:red;">(Actual)</span>' :
                            'Expenditure <span style="font-size:12px; color:red;">(Actual)</span>') + '</b></td>';
                    for (j = 2010; j <= toYear; j++) {
                        if (j === baseYear) {
                            tableBudget += '<td class="totcol edit-column">0.00</td>';
                            tableActual += '<td class="totcol edit-column">0.00</td>';
                        } else {
                            if (j < fromYear) {
                                tableBudget += '<td style="display:none" class="totcol">0.00</td>';
                                tableActual += '<td style="display:none" class="totcol">0.00</td>';
                            } else {
                                tableBudget += '<td class="totcol">0.00</td>';
                                tableActual += '<td class="totcol">0.00</td>';
                            }
                        }
                    }
                    tableBudget += '</tr>';
                    tableActual += '</tr>';

                    //Contingency value
                    if (budgetTypeID === 1) {
                        var fromColSpan = (baseYear - fromYear) + 1;
                        var toColSpan = (toYear - baseYear) - 1;

                        currentWorkflowStatus = document.getElementById("wfs").value;

                        var roles = $('#roles-idx').val().split(',');

                        var readOnly = 'disabled ';
                        var button = '';
                        
                        if (currentWorkflowStatus === "2" || currentWorkflowStatus === "4" || currentWorkflowStatus === "5" || roles[0] === "1") {
                            readOnly = '';

                            button = '<button onclick="OpenUpdateFigModal()" type="button" class="btn btn-sm btn-danger btn-labeled">' +
                                    '<span class="btn-label btn-label-left"><i class="fa fa-edit"></i></span>Update Contingency</button>&nbsp;&nbsp;';
                        }
                        
                        tableActual += '<tr><td><b><span style="color:blue;">Budget Contingency</span></b></td>' +
                                '<td colspan="' + fromColSpan + '"></td>' +
                                '<td><input ' + readOnly + 'id="contingency-value" type="text" onfocusout="numbvalidate(this);" class="form-control pointers" style="color: blue;" value="' + contingencyValue.format(2) + '"></td>' +
                                '<td colspan="' + toColSpan + '">' + button + '</td></tr>';
                    }

                    //Close table-body and table
                    tableBudget += '</tbody></table><br style="clear: both;" />';
                    tableActual += '</tbody></table>';

                    //Attach tables to the document
                    if (budgetTypeID === 1)
                        document.getElementById("revenuex").innerHTML = tableBudget + tableActual;
                    else
                        document.getElementById("expenditurex").innerHTML = tableBudget + tableActual;
//console.log(budgetTypeID+" "+tableBudget);
                    //calculate totals
                    calculateTotals(budgetTypeID, true);
                    calculateTotals(budgetTypeID, false);

                    //re-initialise editable controls
                    initializeEditableControls();

                    //set up function calls for each framework method selection
                    initializeFrameworkMethodSelectionCalls();
                });
            }

            //stop loading...
            StopLoading();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });
}

/**
 * Method for initialising framework methods per line and selection calls for the current version
 * 
 * @returns
 */
function initializeFrameworkMethodSelectionCalls() {
    $('.custom_selectx').each(function () {
        var id = $(this).attr('id');
        var selectedMethod = $(this).attr('data-method');

        //set selected method
        $('#' + id).val(selectedMethod);

        //wire up change callback
        $('#' + id).change(function () {
            //process framework method
            var selectedOption = $(this).val();

            if (selectedOption === "1"){
                showOwnPercentagesModal(id, true);
            }else if (selectedOption === "2"){
                elasticityCalculator(id, true);
            }else if (selectedOption === "3"){
                maThreeYearCalculator(id, true);
            }else if (selectedOption === "4"){
                maFiveYearOutlierCalculator(id, true);
            } else if (selectedOption === "5"){
                maFourYearWeightedCalculator(id, true);
            }
        });
    });
}

/**
 * Method for initialising editable controls
 * 
 * @returns
 */
function initializeEditableControls() {
    $('.editable-control.mybf-fig').each(function () {
        var id = $(this).attr('id');
        var title = $(this).attr('data-title');
        var name = $(this).attr('data-name');
        var pk = $(this).attr('data-pk');
        var type = $(this).attr('data-type');

        if (type === 'text' || type === 'number') {
            $('#' + id).editable({
                url: '/post',
                type: 'number',
                pk: pk,
                name: name,
                title: title,
                display: function (value) {
                    if (!value) {
                        $(this).html('0.00');
                        return;
                    } else {
                        var grep = value.replace(/,/g, "");

                        if (isNaN(grep)) {
                            alert("The entered figure (" + value + ") is not a valid value");
                            value = null;

                            //document.getElementById("submit-final").disabled = true;
                            return;
                        }
                    }

                    var html = value.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");

                    if (html.indexOf('.') === -1)
                        html += '.00';

                    $(this).html(html);

                    //re-calculate totals for table
                    var xId = id.split('-');
                    calculateTotals(xId[0], (xId[3] === 1 ? true : false));
                }
            });
        }
    });
}

/**
 * Method for showing budget line chart modal
 * 
 * @param element
 * @param lineId
 * @returns
 */
function showChartModal(element, lineId) {
    var title = $(element).attr('data-title');
    var baseYear = $('#current-base-year').val();
    var forecast_method = $('#' + lineId + '-' + baseYear + '-sel').val();

    //persist line ID for later use
    currentLineId = lineId;
    currentTitle = title;
    currentForecastMethod = forecast_method;

    var xId = lineId.split('-');

    var selector = xId[0] + '-' + xId[1] + '-' + xId[2] + '-' + xId[3];

    var mainArray = [];
    $(".rowDataSd[id^='" + selector + "']").each(function (i) {
        var span = $(this).find('span');

        var xValue = 0;

        if (span.length)
            xValue = parseFloat(span.html().replace(/,/g, ""));
        else
            xValue = parseFloat($(this).html().replace(/,/g, ""));

        mainArray[i] = xValue;
    });

    //Get chart data
    var toYear = $('#current-base-year').val();
    var chartData = [];
    var i = 0;
    for (j = 2010; j < toYear; j++) {
        chartData.push({
            BudgetValue: mainArray[i],
            year: j
        });

        i++;
    }

    //Hide forecast options
    if (xId[3] === 1)
        $('#chart-all-methods').hide();
    else
        $('#chart-all-methods').show();

    makeBudgetLineChart(title, chartData, forecast_method);

    OpenChartModal();
}

/**
 * Method for showing or going back to original chart
 * 
 * @returns
 */
function showOriginalChart() {
    $('#chart-original').hide();

    var title = currentTitle;
    var baseYear = $('#current-base-year').val();
    var forecast_method = $('#' + currentLineId + '-' + baseYear + '-sel').val();

    var xId = currentLineId.split('-');

    var selector = xId[0] + '-' + xId[1] + '-' + xId[2] + '-' + xId[3];

    var mainArray = [];
    $(".rowDataSd[id^='" + selector + "']").each(function (i) {
        var span = $(this).find('span');

        var xValue = 0;

        if (span.length)
            xValue = parseFloat(span.html().replace(/,/g, ""));
        else
            xValue = parseFloat($(this).html().replace(/,/g, ""));

        mainArray[i] = xValue;
    });

    //Get chart data
    var toYear = $('#current-base-year').val();
    var chartData = [];
    var i = 0;
    for (j = 2010; j < toYear; j++) {
        chartData.push({
            BudgetValue: mainArray[i],
            year: j
        });

        i++;
    };

    //Hide forecast options
    if (xId[3] === 1)
        $('#chart-all-methods').hide();
    else
        $('#chart-all-methods').show();

    makeBudgetLineChart(title, chartData, forecast_method);
};

/**
 * Method for showing budget versus actual charts
 * 
 */
function showBudgetVsActual() {
    $('#chart-original').show();

    var xId = currentLineId.split('-');

    var start = 0, start2 = 0;

    if (parseInt(xId[3]) === 1) {
        start = xId[0] + '-' + xId[1];
        start2 = xId[0] + '-' + (parseInt(xId[1]) + 1);
    } else {
        start = xId[0] + '-' + (parseInt(xId[1]) - 1);
        start2 = xId[0] + '-' + xId[1];
    }

    var selector = 1 + '-' + 2010 + '-' + xId[4];
    var selector2 = 0 + '-' + 2010 + '-' + xId[4];

    var toYear = $('#current-base-year').val();

    var mainArray = [];
    var ii = 0;
    for (j = 2010; j < toYear; j++) {
        selector = 1 + '-' + j + '-' + xId[4];

        $(".rowDataSd[id^='" + start + "'][id$='" + selector + "']").each(function (i) {
            var span = $(this).find('span');

            var xValue = 0;

            if (span.length)
                xValue = parseFloat(span.html().replace(/,/g, ""));
            else
                xValue = parseFloat($(this).html().replace(/,/g, ""));

            mainArray[ii] = xValue;
        });

        ii++;
    }

    ii = 0;
    var mainArray2 = [];
    for (j = 2010; j < toYear; j++) {
        selector2 = 0 + '-' + j + '-' + xId[4];

        $(".rowDataSd[id^='" + start2 + "'][id$='" + selector2 + "']").each(function (i) {
            var span = $(this).find('span');

            var xValue = 0;

            if (span.length)
                xValue = parseFloat(span.html().replace(/,/g, ""));
            else
                xValue = parseFloat($(this).html().replace(/,/g, ""));

            mainArray2[ii] = xValue;
        });

        ii++;
    }

    var chartData = [];
    var i = 0;
    for (j = 2010; j <= toYear; j++) {
        chartData.push({
            BudgetValue: mainArray[i],
            ActualValue: mainArray2[i],
            year: j
        });

        i++;
    }

    makeBudgetLineChart2(currentTitle, chartData, currentForecastMethod);
}

/**
 * Method for kicking off showing charts
 */
function showForecastOptions() {
    $('#modal_chart').iziModal('close');
    showOwnPercentagesModal(currentLineId, false);
}

/**
 * Method for showing chart of different forecast options
 * @param ownPercentageArray
 */
function showForecastOptionsChart(ownPercentageArray) {
    $('#chart-original').show();

    var elasticityArray = elasticityCalculator(currentLineId, false);
    var ma3Array = maThreeYearCalculator(currentLineId, false);
    var ma5Array = maFiveYearOutlierCalculator(currentLineId, false);
    var ma4Array = maFourYearWeightedCalculator(currentLineId, false);

    var chartData = [];
    var i = 0;
    var toYear = $('#current-to-year').val();
    for (j = 2010; j <= toYear; j++) {
        chartData.push({
            "year": j,
            "OwnPercentageValue": ownPercentageArray[i],
            "ElasticityValue": elasticityArray[i],
            "MAThreeValue": ma3Array[i],
            "MAFiveValue": ma5Array[i],
            "MAFourValue": ma4Array[i]
        });

        i++;
    }

    makeBudgetLineChart3(currentTitle, chartData);

    OpenChartModal();
};

/**
 * Method for for narration modal per line
 * 
 * @param element
 * @param lineId
 * @returns
 */
function showNarrationModal(element, lineId) {
    var title = $(element).attr('data-title');

    var baseYear = $('#current-base-year').val();
    var forecast_method = $('#' + lineId + '-' + baseYear + '-sel').val();

    var xId = lineId.split('-');

    var mybfFigureID = xId[2];

    //clear old narration text
    $('#narrationx').val('');

    //Mtef Figure Narration
    data = {};
    data['option'] = $('#selectx').val();
    data['mybfFigureID'] = mybfFigureID;

    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MybfNarrationsServlet",
        data: data,
        dataType: "json",
        cache: false,
        success(response) {
            if (response.length > 0) {
                var firstName = '', lastName = '',
                        deptName = '', mdaName = '',
                        narration = '', dateModified = '';

                $.each(response, function (index, element) {
                    narration = element[1];
                    dateModified = element[2];
                    firstName = element[3];
                    lastName = element[4];
                    deptName = element[5];
                    mdaName = element[6];
                });

                //set modal subtitle
                $('#date-who').html("<small>Date modified: " + dateModified + "<br/>" +
                        "By: " + firstName + " " + lastName + " (" + deptName + ", " + mdaName + ")</small>");

                //set the narration
                $('#narrationx').val(narration);
            } else
                $('#date-who').html("");

            //chosen mybf figure ID
            $('#chosen-mybf-figure-id').val(mybfFigureID);

            //show narration modal
            OpenNarrationModal();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });
}

/**
 * Method for saving MTEF figures narration
 * 
 * @returns
 */
function saveMTEFFigureNarration() {
    $('#modal_narration').iziModal('startLoading');

    var mybfFigureID = $('#chosen-mybf-figure-id').val();

    data = {};
    data['option'] = $('#insertx').val();
    data['mybfFigureID'] = mybfFigureID;
    data['narration'] = $('#narrationx').val();
    data['userID'] = $('#v5er-idx').val();

    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/MybfNarrationsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            //close modal
            $('#modal_narration').iziModal('stopLoading');
            $('#modal_narration').iziModal('close');
        }
    });
}

/**
 * Method for showing the own percentage values modal
 * 
 * @param lineId
 * @param isForPrint
 * @returns
 */
function showOwnPercentagesModal(lineId, isForPrint) {
    $('#own-percentage-line-id').val(lineId);

    if (isForPrint){
        $('#isForPrint').val(1);
    }else{
        $('#isForPrint').val(0);
    }
    var xId = lineId.split('-');
    var baseYear = $('#current-base-year').val();
    var toYear = $('#current-to-year').val();

    //Get all own percentage values for mybf figures
    data = {};
    data['option'] = $('#select-all').val();
    data['mybfFigureID'] = xId[2];

    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/MybfFigureOwnPercentageValuesServlet",
        data: data,
        dataType: "json",
        success: function (response) {
            var ownPercentageInputs = '<table class="table table-bordered table-striped">' +
                    '<thead><tr><th>Year</th><th>Percentage Value</th></tr></thead>' +
                    '<tbody>';
            var newBaseYear = parseInt(baseYear) + 1;
            if (response.length > 0) {
                var storedOwnValues = [];
                var storedOwnValueYears = [];

                $.each(response, function (index, element) {
                    storedOwnValues.push(element[1]);
                    storedOwnValueYears.push(element[2]);
                });

                for (j = newBaseYear; j <= toYear; j++) {
                    var xVal = 0;

                    for (i = 0; i <= storedOwnValues.length; i++) {
                        if (storedOwnValueYears[i] === j) {
                            xVal = parseInt(storedOwnValues[i]);
                            break;
                        }
                    }

                    ownPercentageInputs += '<tr>' +
                            '<td><label>' + j + '</label></td>' +
                            '<td>' +
                            '<a class="rowOwnVal editable-control own-val" href="#" id="' + j + '" ' +
                            'data-name="' + j + '" data-type="text" data-pk="1" data-title="Year ' + j + '">' +
                            xVal + '</a>&nbsp;%' +
                            '</td>' +
                            '</tr>';
                }
            } else {
                for (j = newBaseYear; j <= toYear; j++) {
                    ownPercentageInputs += '<tr>' +
                            '<td><label>' + j + '</label></td>' +
                            '<td>' +
                            '<a class="rowOwnVal editable-control own-val" href="#" id="' + j + '" ' +
                            'data-name="' + j + '" data-type="text" data-pk="1" data-title="Year ' + j + '">' +
                            '0</a>&nbsp;%' +
                            '</td>' +
                            '</tr>';
                }
            }

            ownPercentageInputs += '</tbody></table>';

            //add percentage inputs to modal
            $('#divOwnPercentageValuesTable').html(ownPercentageInputs);

            //initialise editable control
            initializeEditableControlsForOwnPercentages();

            //show percentage values
            ShowOwnPercentageValues();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });
}

/**
 * Method for initialising editable controls for own percentages
 * 
 * @returns
 */
function initializeEditableControlsForOwnPercentages() {
    $('.editable-control.own-val').each(function () {
        var id = $(this).attr('id');
        var title = $(this).attr('data-title');
        var name = $(this).attr('data-name');
        var pk = $(this).attr('data-pk');
        var type = $(this).attr('data-type');

        if (type === 'text' || type === 'number') {
            $('#' + id).editable({
                url: '/post',
                type: 'number',
                pk: pk,
                name: name,
                title: title,
                display: function (value) {
                    if (!value) {
                        $(this).html('0');
                        return;
                    } else if (isNaN(value)) {
                        alert("The entered figure (" + value + ") is not a valid number");
                        $(this).html('0');
                        return;
                    } else if (value < 0 || value > 100) {
                        alert("The entered figure (" + value + ") cannot be lower than zero or greater than 100");
                        $(this).html('0');
                        return;
                    }

                    var html = value.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");

                    $(this).html(html);
                }
            });
        }
    });
}

/**
 * Method for auto calculating OWN PERCENTAGES on a selected budget line
 * 
 * @returns
 */
function ownPercentageCalculator() {
    $('#modal_own_values').iziModal('startLoading');

    var xId = $('#own-percentage-line-id').val().split('-');
    var mybfFigureID = xId[2];

    //Get and Save values in DB
    var ownPercentageValuesArray = [];
    $(".rowOwnVal").each(function (i) {
        var ownPercent = parseInt($(this).html().replace(/,/g, ""));
        var year = $(this).attr('id');

        ownPercentageValuesArray[i] = ownPercent;

        if (canUpdate) {
            //store value proper
            data = {};
            data['option'] = $('#insertx').val();
            data['percentage'] = ownPercent;
            data['year'] = year;
            data['mybfFigureID'] = mybfFigureID;

            $.ajax({
                type: "POST",
                url: $('#site-url').val() + "/MybfFigureOwnPercentageValuesServlet",
                data: data,
                dataType: "text",
                cache: false
            });
        }
    });

    //close modal
    $('#modal_own_values').iziModal('stopLoading');
    $('#modal_own_values').iziModal('close');

    var selector = xId[0] + '-' + xId[1] + '-' + xId[2] + '-' + xId[3];

    var mainArray = [];
    var isForPrint = parseInt($('#isForPrint').val()) === 1;
    $(".rowDataSd[id^='" + selector + "']").each(function (i) {
        mainArray[i] = parseFloat($(this).html().replace(/,/g, ""));
    });

    //do main Own Percentage values calculation
    var baseYear = $('#current-base-year').val();
    var toYear = $('#current-to-year').val();

    var i = 0, k = 0;
    var xMainArray = [];
    for (j = 2010; j <= toYear; j++) {
        if (j > baseYear) {
            mainArray[i] = mainArray[i - 1] * (1 + (ownPercentageValuesArray[k] / 100));

            k++;
        }

        xMainArray[i] = mainArray[i];

        i++;
    }

    //Set newly calculated values for selected line
    var isForPrint = (parseInt($('#isForPrint').val()) === 1 ? true : false);
    if (isForPrint)
        printForcastedValuesAndCalculateTotals(xId[0], selector, baseYear, mainArray);
    else
        showForecastOptionsChart(xMainArray);
}

/**
 * Method for auto calculating Elasticity on a selected budget line
 * 
 * @param lineId
 * @param isForPrint
 * @returns
 */
function elasticityCalculator(lineId, isForPrint) {
    var xId = lineId.split('-');

    var selector = xId[0] + '-' + xId[1] + '-' + xId[2] + '-' + xId[3];

    var mainArray = [];
    $(".rowDataSd[id^='" + selector + "']").each(function (i) {
        mainArray[i] = parseFloat($(this).html().replace(/,/g, ""));
    });

    //Get GDP and inflation
    var inflationArray = [];
    var j = 0;
    $(".fmx").each(function (i) {
        var xId = $(this).attr('id').split('-');
        var xVal = $(this).html();

        var frameworkParameterTypeID = xId[0];

        if (frameworkParameterTypeID === 1) {
            inflationArray[j] = parseFloat(xVal) / 100;
            j++;
        }
    });

    var gdpArray = [];
    j = 0;
    $(".fmx").each(function (i) {
        var xId = $(this).attr('id').split('-');
        var xVal = $(this).html();

        var frameworkParameterTypeID = xId[0];

        if (frameworkParameterTypeID === 2) {
            gdpArray[j] = parseFloat(xVal) / 100;
            j++;
        }
    });

    //Get combined GDP and inflation
    var combinedGdpInflationArray = [];
    for (k = 0; k < inflationArray.length; k++) {
        combinedGdpInflationArray[k] = (((1 + inflationArray[k]) * (1 + gdpArray[k])) - 1) * 100;
    }

    //Get growth percentage
    var baseYear = $('#current-base-year').val();
    var toYear = $('#current-to-year').val();
    var growthPercentageArray = [];
    var k = 0;
    for (j = 2010; j <= toYear; j++) {
        if (j === 2010)
            growthPercentageArray[k] = 0;
        else if (j < baseYear)
            growthPercentageArray[k] = ((mainArray[k] - mainArray[k - 1]) / mainArray[k - 1]) * 100;
        else {
            var val1 = growthPercentageArray[k - 1] / combinedGdpInflationArray[k - 1];
            var val2 = growthPercentageArray[k - 2] / combinedGdpInflationArray[k - 2];
            var val3 = growthPercentageArray[k - 3] / combinedGdpInflationArray[k - 3];
            var val4 = growthPercentageArray[k - 4] / combinedGdpInflationArray[k - 4];
            var val5 = growthPercentageArray[k - 5] / combinedGdpInflationArray[k - 5];

            var maPercentage = (+(val1 + val2 + val3 + val4 + val5) -
                    (Math.min(val1, val2, val3, val4, val5)) -
                    (Math.max(val1, val2, val3, val4, val5))
                    ) / 3;

            growthPercentageArray[k] = combinedGdpInflationArray[k] * maPercentage;
        }

        k++;
    }

    //Main elasticity calculation
    var xMainArray = [];
    var i = 0;
    for (j = 2010; j <= toYear; j++) {
        if (j > baseYear) {
            mainArray[i] = mainArray[i - 1] * (1 + (growthPercentageArray[i] / 100));
        }

        xMainArray[i] = mainArray[i];

        i++;
    }

    //Set newly calculated values for selected line
    if (isForPrint)
        printForcastedValuesAndCalculateTotals(xId[0], selector, baseYear, mainArray);
    else
        return xMainArray;
}

/**
 * Method for auto calculating MA 3-Year on a selected budget line
 * 
 * @param lineId
 * @param isForPrint
 * @returns
 */
function maThreeYearCalculator(lineId, isForPrint) {
    var xId = lineId.split('-');

    var selector = xId[0] + '-' + xId[1] + '-' + xId[2] + '-' + xId[3];

    var mainArray = [];
    $(".rowDataSd[id^='" + selector + "']").each(function (i) {
        mainArray[i] = parseFloat($(this).html().replace(/,/g, ""));
    });

    //do main 3-Year moving average calculation
    var baseYear = $('#current-base-year').val();
    var toYear = $('#current-to-year').val();

    var xMainArray = [];
    var i = 0;
    for (j = 2010; j <= toYear; j++) {
        if (j > baseYear) {
            var maPercentage = (((mainArray[i - 1] - mainArray[i - 2]) / mainArray[i - 2]) +
                    ((mainArray[i - 2] - mainArray[i - 3]) / mainArray[i - 3]) +
                    ((mainArray[i - 3] - mainArray[i - 4]) / mainArray[i - 4])
                    ) / 3;

            mainArray[i] = mainArray[i - 1] * (1 + maPercentage);
        }

        xMainArray[i] = mainArray[i];

        i++;
    }

    //Set newly calculated values for selected line
    if (isForPrint)
        printForcastedValuesAndCalculateTotals(xId[0], selector, baseYear, mainArray);
    else
        return xMainArray;
}

/**
 * Method for auto calculating MA 5-Year X-Outliers on a selected budget line
 * 
 * @param lineId
 * @param isForPrint
 * @returns
 */
function maFiveYearOutlierCalculator(lineId, isForPrint) {
    var xId = lineId.split('-');

    var selector = xId[0] + '-' + xId[1] + '-' + xId[2] + '-' + xId[3];

    //Get all values into an array
    var mainArray = [];
    $(".rowDataSd[id^='" + selector + "']").each(function (i) {
        mainArray[i] = parseFloat($(this).html().replace(/,/g, ""));
    });

    //do main 5-Year x-outliers moving average calculation
    var baseYear = $('#current-base-year').val();
    var toYear = $('#current-to-year').val();

    var i = 0;
    var xMainArray = [];
    for (j = 2010; j <= toYear; j++) {
        if (j > baseYear) {
            var val1 = ((mainArray[i - 1] - mainArray[i - 2]) / mainArray[i - 2]);
            var val2 = ((mainArray[i - 2] - mainArray[i - 3]) / mainArray[i - 3]);
            var val3 = ((mainArray[i - 3] - mainArray[i - 4]) / mainArray[i - 4]);
            var val4 = ((mainArray[i - 4] - mainArray[i - 5]) / mainArray[i - 5]);
            var val5 = ((mainArray[i - 5] - mainArray[i - 6]) / mainArray[i - 6]);

            var maPercentage = (+(val1 + val2 + val3 + val4 + val5) -
                    (Math.min(val1, val2, val3, val4, val5)) -
                    (Math.max(val1, val2, val3, val4, val5))
                    ) / 3;

            mainArray[i] = mainArray[i - 1] * (1 + maPercentage);
        }

        xMainArray[i] = mainArray[i];

        i++;
    }

    //Set newly calculated values for selected line
    if (isForPrint)
        printForcastedValuesAndCalculateTotals(xId[0], selector, baseYear, mainArray);
    else
        return xMainArray;
}

/**
 * Method for auto calculating MA 4-Year Weighted on a selected budget line
 * 
 * @param lineId
 * @param isForPrint
 * @returns
 */
function maFourYearWeightedCalculator(lineId, isForPrint) {
    var xId = lineId.split('-');

    var selector = xId[0] + '-' + xId[1] + '-' + xId[2] + '-' + xId[3];

    //Get all values into an array
    var mainArray = [];
    $(".rowDataSd[id^='" + selector + "']").each(function (i) {
        mainArray[i] = parseFloat($(this).html().replace(/,/g, ""));
    });

    //do main 4-Year weighted moving average calculation
    var baseYear = $('#current-base-year').val();
    var toYear = $('#current-to-year').val();

    var i = 0;
    var xMainArray = [];
    for (j = 2010; j <= toYear; j++) {
        if (j > baseYear) {
            var val1 = ((mainArray[i - 1] - mainArray[i - 2]) / mainArray[i - 2]);
            var val2 = ((mainArray[i - 2] - mainArray[i - 3]) / mainArray[i - 3]);
            var val3 = ((mainArray[i - 3] - mainArray[i - 4]) / mainArray[i - 4]);
            var val4 = ((mainArray[i - 4] - mainArray[i - 5]) / mainArray[i - 5]);

            var maPercentage = ((+val1 * 0.1) + (val2 * 0.2) + (val3 * 0.3) + (val4 * 0.4));

            mainArray[i] = mainArray[i - 1] * (1 + maPercentage);
        }

        xMainArray[i] = mainArray[i];

        i++;
    }

    //Set newly calculated values for selected line
    if (isForPrint)
        printForcastedValuesAndCalculateTotals(xId[0], selector, baseYear, mainArray);
    else
        return xMainArray;
}

/**
 * Method for printing forecasted values and calculating totals
 * 
 * @param xId
 * @param selector
 * @param baseYear
 * @param mainArray
* @returns
 */
function printForcastedValuesAndCalculateTotals(xId, selector, baseYear, mainArray) {
    //Set newly calculated values for selected line
    $(".rowDataSd[id^='" + selector + "']").each(function (i) {
        var xIdx = $(this).attr('id').split('-');
        if(isNaN(mainArray[i])){
            mainArray[i]=0;
        }
        if (xIdx[4] > baseYear)
            $(this).html("<span style='color:red;'>" + mainArray[i].format(2) + "</span>");
    });

    //re-calculate totals
    calculateTotals(xId, true);
    calculateTotals(xId, false);
}

/**
 * Method for calculating totals
 * 
 * @param budgetTypeID
 * @param isBudget
 * @returns
 */
function calculateTotals(budgetTypeID, isBudget) {
    totalsXRec = makeArrayOf(0, totalsXRec.length);
    totalsXCap = makeArrayOf(0, totalsXCap.length);

    if (isBudget === true) {
        totalsX = makeArrayOf(0, totalsX.length);

        //Calculate Budget Grand Totals
        var $dataRowsBudget = $("#mybf-budget-" + budgetTypeID + " tr:not('.tot, .totrec, .totcap, .tit')");
        $dataRowsBudget.each(function () {
            $(this).find('.rowDataSd').each(function (i) {
                var xId = $(this).attr('id').split('-');
                var span = $(this).find('span');

                var xValue = 0;

                if (span.length)
                    xValue = parseFloat(span.html().replace(/,/g, ""));
                else
                    xValue = parseFloat($(this).html().replace(/,/g, ""));

                if (xId[5] === 1)
                    totalsXRec[i] += xValue;
                else if (xId[5] === 2)
                    totalsXCap[i] += xValue;

                totalsX[i] += xValue;
            });
        });

        //Set calculated values
        $("#mybf-budget-" + budgetTypeID + " td.totcolrec").each(function (i) {
            $(this).html("<b>" + totalsXRec[i].format(2) + "</b>");
        });
        $("#mybf-budget-" + budgetTypeID + " td.totcolcap").each(function (i) {
            $(this).html("<b>" + totalsXCap[i].format(2) + "</b>");
        });
        $("#mybf-budget-" + budgetTypeID + " td.totcol").each(function (i) {
            $(this).html("<b>" + totalsX[i].format(2) + "</b>");
        });
    } else {
        totalsX2 = makeArrayOf(0, totalsX2.length);

        //Calculate Actual Grand Totals
        var $dataRowsActual = $("#mybf-actual-" + budgetTypeID + " tr:not('.tot, .totrec, .totcap, .tit')");
        $dataRowsActual.each(function () {
            $(this).find('.rowDataSd').each(function (i) {
                var xId = $(this).attr('id').split('-');
                var span = $(this).find('span');

                var xValue = 0;

                if (span.length)
                    xValue = parseFloat(span.html().replace(/,/g, ""));
                else
                    xValue = parseFloat($(this).html().replace(/,/g, ""));

                if (xId[5] === 1)
                    totalsXRec[i] += xValue;
                else if (xId[5] === 2)
                    totalsXCap[i] += xValue;

                totalsX2[i] += xValue;
            });
        });

        //Set calculated values
        $("#mybf-actual-" + budgetTypeID + " td.totcolrec").each(function (i) {
            $(this).html("<b>" + totalsXRec[i].format(2) + "</b>");
        });
        $("#mybf-actual-" + budgetTypeID + " td.totcolcap").each(function (i) {
            $(this).html("<b>" + totalsXCap[i].format(2) + "</b>");
        });
        $("#mybf-actual-" + budgetTypeID + " td.totcol").each(function (i) {
            $(this).html("<b>" + totalsX2[i].format(2) + "</b>");
        });
    }
}

/**
 * Method for re-filling an array with a specific value
 * 
 * @param value
 * @param length
 * @returns
 */
function makeArrayOf(value, length) {
    var arr = [],
            i = length;

    while (i--) {
        arr[i] = value;
    }

    return arr;
}

/**
 * Method for saving MYBF figures as-is on screen
 * 
 * @returns
 */
function saveMybfFigures() {
    $('#modal_new_version').iziModal('startLoading');

    ShowLoading();

    //set variables
    var isSuccess = true;
    var notif_header = 'Success';
    var notif_msg = 'Budget version saved successfully!';
    var baseYear = $('#current-base-year').val();

    var promise;

    //Save version info
    data = {};
    data['option'] = $('#insertx').val();
    data['name'] = $('#version-namex').val();
    data['description'] = $('#version-descx').val();
    data['mtefFromYear'] = $('#current-from-year').val();
    data['mtefToYear'] = $('#current-to-year').val();
    data['year'] = $('#current-base-year').val();
    data['mybfContigencyValue'] = parseFloat($('#contingency-value').val().replace(/,/g, ""));

    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetVersionsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            var versionID = parseInt(response);

            if (versionID >= 1) {
                var mybfFigures = [];
                var mybfPrevForward = [];

                //Create base year values
                $(".mybf-fig.figx").each(function () {
                    var xId = $(this).attr('id').split('-');

                    var span = $(this).find('span');
                    var xValue = 0;
                    if (span.length)
                        xValue = parseFloat(span.html().replace(/,/g, ""));
                    else
                        xValue = parseFloat($(this).html().replace(/,/g, ""));

                    var xVal = xValue;

                    if (xId[4] === $('#current-base-year').val()) {
                        var chosenFrameworkMethod = $('#' + xId[0] + '-' + xId[1] + '-' + xId[2] + '-' + xId[3] + '-' + xId[5] + '-' + baseYear + '-sel').val();

                        mybfFigures.push(String(xVal));
                        mybfFigures.push(String('baseYearValue' + xId[4]));
                        mybfFigures.push(String(xId[1]));
                        mybfFigures.push(String(chosenFrameworkMethod));

                        var selector = xId[0] + '-' + xId[1] + '-' + xId[2];

                        //Create previous forward values
                        $(".mybf-fig[id^='" + selector + "']").each(function () {
                            var xId2 = $(this).attr('id').split('-');

                            var span = $(this).find('span');
                            var xValue = 0;
                            if (span.length)
                                xValue = parseFloat(span.html().replace(/,/g, ""));
                            else
                                xValue = parseFloat($(this).html().replace(/,/g, ""));

                            var xVal2 = xValue;

                            //Check for line for current MTEF figure ID
                            if (xId2[4] !== $('#current-base-year').val()) {
                                mybfPrevForward.push(String(xId2[4]));
                                mybfPrevForward.push(String(xVal2));
                            }
                        });
                    }
                });
                //upload MYBF figures
                data = {};
                data['option'] = $('#insertx').val();
                data['budgetVersionID'] = versionID;
                data['mybfFiguresMap'] = mybfFigures;
                data['mybfPrevForwardMap'] = mybfPrevForward;
                data['userID'] = String($('#v5er-idx').val());
                data['yearTo'] = $('#current-to-year').val();
                data['year'] = $('#current-base-year').val();
                $.ajax({
                    type: "POST",
                    url: $('#site-url').val() + "/MybfFiguresServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    success: function () {
                        //close all here
                        //close modal
                        StopLoading();
                        $('#modal_new_version').iziModal('stopLoading');
                        $('#modal_new_version').iziModal('close');

                        toastr["success"](notif_msg, notif_header);
                        
                        //get all versions and select the latest
                        getAllBudgetVersions(false);
                        
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        StopLoading();

                        //console.log(textStatus + " " + errorThrown);
                    }
                });
            } else {
                isSuccess = false;
                notif_header = 'Failure';
                notif_msg = 'Budget version could not be saved!';

                toastr["error"](notif_msg, notif_header);

                //close modal
                $('#modal_new_version').iziModal('stopLoading');
                $('#modal_new_version').iziModal('close');

                StopLoading();
            }
        }
    });
}

/**
 * Method for updating MYBF figures as-is on screen
 * 
 * @returns
 */
function updateMybfFigures() {
    $('#modal_update_fig').iziModal('startLoading');

    ShowLoading();

    //set variables
    var isSuccess = true;
    var notif_header = 'Success';
    var notif_msg = 'Updated budget saved successfully!';

    var baseYear = $('#current-base-year').val();
    var versionID = $('#chosen-budget-version-id').val();

    var promise;

    //Save version info
    data = {};
    data['option'] = $('#updatex').val();
    data['id'] = versionID;
    data['mybfContigencyValue'] = parseFloat($('#contingency-value').val().replace(/,/g, ""));

    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/BudgetVersionsServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function (response) {
            if (versionID >= 1) {
                var mybfFigures = [];
                var mybfPrevForward = [];

                //Create base year values
                $(".mybf-fig.figx").each(function () {
                    var xId = $(this).attr('id').split('-');

                    var span = $(this).find('span');
                    var xValue = 0;
                    if (span.length)
                        xValue = parseFloat(span.html().replace(/,/g, ""));
                    else
                        xValue = parseFloat($(this).html().replace(/,/g, ""));

                    var xVal = xValue;

                    if (xId[4] === $('#current-base-year').val()) {
                        var mybfFigureID = xId[2];

                        mybfFigures.push(String(mybfFigureID));
                        mybfFigures.push(String(xVal));

                        if (mybfFigureID >= 1) {
                            var selector = xId[0] + '-' + xId[1] + '-' + xId[2];

                            //Create previous forward values
                            $(".mybf-fig[id^='" + selector + "']").each(function () {
                                var xId2 = $(this).attr('id').split('-');

                                var span = $(this).find('span');
                                var xValue = 0;
                                if (span.length)
                                    xValue = parseFloat(span.html().replace(/,/g, ""));
                                else
                                    xValue = parseFloat($(this).html().replace(/,/g, ""));

                                var xVal2 = xValue;

                                //Check for line for current MYBF figure ID
                                if (xId2[4] !== $('#current-base-year').val()) {
                                    mybfPrevForward.push(String(xId2[4]));
                                    mybfPrevForward.push(String(xVal2));
                                }
                            });
                        }
                    }
                });

                data['option'] = $('#updatex').val();
                data['mybfFiguresMap'] = mybfFigures;
                data['mybfPrevForwardMap'] = mybfPrevForward;
                data['yearTo'] = $('#current-to-year').val();

                $.ajax({
                    type: "POST",
                    url: $('#site-url').val() + "/MybfFiguresServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    success: function () {
                        $('#modal_update_fig').iziModal('stopLoading');
                        $('#modal_update_fig').iziModal('close');

                        toastr["success"](notif_msg, notif_header);

                        //get all versions and select the latest
                        getAllBudgetVersions(false);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        StopLoading();

                        //console.log(textStatus + " " + errorThrown);
                    }
                });
            } else {
                isSuccess = false;
                notif_header = 'Failure';
                notif_msg = 'Updated budget could not be saved!';

                toastr["error"](notif_msg, notif_header);

                $('#modal_update_fig').iziModal('stopLoading');
                $('#modal_update_fig').iziModal('close');

                StopLoading();
            }
        }
    });
}

/**
 * Method for getting framework parameters and displaying values in a table
 * 
 * @param fromYear
 * @param toYear
 * @returns
 */
function getFrameworkParameters(fromYear, toYear) {
    $.ajax({
        type: "GET",
        url: $('#site-url').val() + "/FrameworkParameterTypesServlet",
        data: {option: $('#select-all').val()},
        dataType: "json",
        cache: false,
        success: function (response) {
            var diffYear = toYear - fromYear;

            var table = '<table id="fmxtab" class="table table-striped">' +
                    '<thead><tr><th></th>';

            for (j = 2010; j <= toYear; j++) {
                if (j < fromYear)
                    table += '<th style="display:none;"><b>' + j + '</b></th>';
                else
                    table += '<th><b>' + j + '</b></th>';
            }

            table += '</tr></thead><tbody>';

            if (response.length > 0) {
                $.each(response, function (index, element) {
                    var frameworkParameterTypeID = element[0];
                    var frameworkName = element[1];
                    var isRate = element[3];

                    if (isRate === true)
                        table += '<tr><th scope="row">' + element[1] + ' (%)</th>';
                    else
                        table += '<tr><th scope="row">' + element[1] + '</th>';

                    data = {};
                    data['option'] = $('#select-all').val();
                    data['frameworkParameterTypeID'] = frameworkParameterTypeID;
                    data['yearFrom'] = 2010;//fromYear;
                    data['yearTo'] = toYear;

                    $.ajax({
                        type: "GET",
                        url: $('#site-url').val() + "/FrameworkParametersServlet",
                        data: data,
                        dataType: "json",
                        cache: false,
                        async: false,
                        success: function (response2) {
                            if (response2.length > 0) {
                                var paramValues = [];
                                var paramYears = [];

                                $.each(response2, function (index2, element2) {
                                    paramValues.push(element2[1]);
                                    paramYears.push(element2[2]);
                                });

                                for (j = 2010; j <= toYear; j++) {
                                    var xValue = 0.00;

                                    for (x = 0; x < paramYears.length; x++) {
                                        if (paramYears[x] === j) {
                                            xValue = paramValues[x];
                                            break;
                                        }
                                    }

                                    if (j < fromYear) {
                                        table += '<td style="display:none;">' +
                                                '<a class="editable-control fmx" href="#" id="' + frameworkParameterTypeID + '-' + j + '" ' +
                                                'data-name="' + frameworkParameterTypeID + '-' + j + '" data-type="text" data-pk="1" data-title="' + frameworkName + '">' + xValue.format(2) +
                                                '</a></td>';
                                    } else {
                                        if(j < currentBaseYear){
                                            table += '<td>' +
                                                    '<a data-name="' + frameworkParameterTypeID + '-' + j + '" data-type="text" data-pk="1" data-title="' + frameworkName + '">' + xValue.format(2) +
                                                    '</a></td>';
                                        }else{
                                            table += '<td>' +
                                                    '<a class="editable-control fmx" href="#" id="' + frameworkParameterTypeID + '-' + j + '" ' +
                                                    'data-name="' + frameworkParameterTypeID + '-' + j + '" data-type="text" data-pk="1" data-title="' + frameworkName + '">' + xValue.format(2) +
                                                    '</a></td>';
                                        }
                                    }
                                }

                                table += '</tr>';
                            } else {
                                for (j = fromYear; j <= toYear; j++) {
                                    var xValue = 0.00;

                                    table += '<td>' +
                                            '<a class="editable-control fmx" href="#" id="' + frameworkParameterTypeID + '-' + j + '" ' +
                                            'data-name="' + frameworkParameterTypeID + '-' + j + '" data-type="text" data-pk="1" data-title="' + frameworkName + '">' + xValue.format(2) +
                                            '</a>';

                                    table += '</td>';
                                }

                                table += '</tr>';
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            //console.log(textStatus + " " + errorThrown);
                        }
                    });
                });

                //add button
                var disable = '';
                if ($("#llllll").val() === 1)
                    disable = 'disabled ';

                table += '<tr><td colspan="' + (diffYear + 2) + '">' +
                        '<button ' + disable + 'id="save-framework-params" class="btn btn-danger btn-sm toggle-code-handle pull-right" role="button" onclick="saveFrameworkParameters()">' +
                        '<i class="fa fa-save"> </i>Save Parameters</button>' +
                        '</td></tr>';

                table += '</tbody></table>';

                $("#fmxx" + fromYear + '-' + toYear).html(table);

                //re-initialise editable controls
                $('.fmx').each(function () {
                    var id = $(this).attr('id');
                    var title = $(this).attr('data-title');
                    var name = $(this).attr('data-name');
                    var pk = $(this).attr('data-pk');
                    var type = $(this).attr('data-type');

                    if (type === 'text' || type === 'number') {
                        $('#' + id).editable({
                            url: '/post',
                            type: 'number',
                            pk: pk,
                            name: name,
                            title: title,
                            display: function (value) {
                                if (!value) {
                                    $(this).html('0.00');
                                    return;
                                } else {
                                    var grep = value.replace(/,/g, "");

                                    if (isNaN(grep)) {
                                        alert("The entered figure (" + value + ") is not a valid value.");
                                        value = null;
                                        return;
                                    }
                                }

                                var html = value.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");

                                if (html.indexOf('.') === -1)
                                    html += '.00';

                                $(this).html(html);
                            }
                        });
                    }
                });
            } else {
                table = "<label>Framework parameters have not been setup.</label><br/><br/>" +
                        "<a onclick='gotoFrameworkParametersSetup();' class='btn btn-info btn-sm toggle-code-handle pull-right' role='button'>" +
                        "<i class='fa fa-plus'></i>Setup Parameters</a>";
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //console.log(textStatus + " " + errorThrown);
        }
    });

    return '<div id="fmxx' + fromYear + '-' + toYear + '">Loading...</div>';
}

/**
 * Method for saving MTEF framework parameters
 * 
 * @returns
 */
function saveFrameworkParameters() {
    ShowLoading();

    var frameworkParamIDs = [];
    var frameworkValues = [];
    var years = [];

    $(".fmx").each(function () {
        var xId = $(this).attr('id').split('-');
        var xVal = $(this).html();

        var frameworkParameterTypeID = xId[0];
        var year = xId[1];

        frameworkParamIDs.push(String(frameworkParameterTypeID));
        frameworkValues.push(String(xVal.replace(/,/g, "")));
        years.push(String(year));
    });

    //Create framework parameters
    data = {};
    data['option'] = $('#insertx').val();
    data['frameworkParamIDs'] = frameworkParamIDs;
    data['frameworkValues'] = frameworkValues;
    data['years'] = years;

    $.ajax({
        type: "POST",
        url: $('#site-url').val() + "/FrameworkParametersServlet",
        data: data,
        dataType: "text",
        cache: false,
        success: function () {
            StopLoading();

            // Notification
            toastr["success"]("Framework parameter values saved successfully!", "Created");

            ClosePopover();

            //reset fmxx
            $('#mybf-framework-params').html(getFrameworkParameters(parseInt($('#current-from-year').val()), parseInt($('#current-to-year').val())));
        },
        error: function (jqXHR, textStatus, errorThrown) {
            StopLoading();

            // Notification
            toastr["error"]("Framework parameter could not be saved!", "Failure");

            //console.log(textStatus + " " + errorThrown);
        }
    });
}

/**
 * Method for going to framework parameters setup page
 * 
 * @returns
 */
function gotoFrameworkParametersSetup() {
    gotoLink('/codem00045');
}