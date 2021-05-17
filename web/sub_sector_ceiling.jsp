<%@ page import="com.bpp.utility.Utility" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Budgeting Application - Ondo State</title>
        <!-- ========== COMMON STYLES ========== -->
        <link rel="stylesheet" href="css/bootstrap.min.css" media="screen">
        <link rel="stylesheet" href="css/font-awesome.min.css" media="screen">
        <link rel="stylesheet" href="css/animate-css/animate.min.css" media="screen">
        <link rel="stylesheet" href="css/lobipanel/lobipanel.min.css" media="screen">
        <!-- ========== PAGE STYLES ========== -->
        <link rel="stylesheet" href="css/toastr/toastr.min.css" media="screen">
        <link rel="stylesheet" href="css/icheck/skins/line/blue.css">
        <link rel="stylesheet" href="css/icheck/skins/line/red.css">
        <link rel="stylesheet" href="css/icheck/skins/line/green.css">
        <link rel="stylesheet" href="css/x-editable/css/bootstrap-editable.css">

        <link rel="stylesheet" href="css/ladda/ladda-themeless.min.css" media="screen">
        <link rel="stylesheet" href="css/iziModal/iziModal.min.css" media="screen">
        <link rel="stylesheet" href="css/sweet-alert/sweetalert.css" media="screen">
        <link rel="stylesheet" href="css/select2/select2.min.css">
        <link rel="stylesheet" href="css/switchery/switchery.min.css">
        <link href="css/bootstrap-switch/bootstrap-switch.min.css" rel="stylesheet" />
        <link href="css/cropper.css" rel="stylesheet" />
        <link href="css/jquery.dataTables.min.css" rel="stylesheet" />

        <!-- ========== THEME CSS ========== -->
        <link rel="stylesheet" href="css/main.css" media="screen">
        <style>.hover-control{display: inline !important;}</style>
        <!-- ========== MODERNIZR ========== -->
        <script src="js/modernizr/modernizr.min.js"></script>
        <link rel="stylesheet" href="css/jquery.loading.css" media="screen">

    </head>
    <body class="top-navbar-fixed">
        <div class="main-wrapper">
            <!-- ========== TOP NAVBAR ========== -->
            <nav class="navbar top-navbar bg-white box-shadow">
                <div class="container-fluid">
                    <div class="row">
                        <div class="navbar-header no-padding">
                            <a class="navbar-brand" onclick="gotoLink('/dashboard00012');">
                                <img src="images/logo-dark.png" alt="Options - Admin Template" class="logo" style="display: inline; margin-bottom: 10px">&nbsp;<div style="display: inline"><b>Budget</b></div>
                            </a>
                            <span id="nav-togglerx" class="small-nav-handle hidden-sm hidden-xs"><i class="fa fa-outdent"></i></span>
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1" aria-expanded="false">
                                <span class="sr-only">Toggle navigation</span>
                                <i class="fa fa-ellipsis-v"></i>
                            </button>
                            <button type="button" class="navbar-toggle mobile-nav-toggle">
                                <i class="fa fa-bars"></i>
                            </button>
                        </div>
                        <!-- /.navbar-header -->
                        <div class="pull-left p-r-10 p-t-10 fs-16 font-heading">
                            <br/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <b><%= session.getAttribute("username")%>&nbsp;[<%= session.getAttribute("userrole")%>]&nbsp;[<%= session.getAttribute("usermda")%>]</b>
                        </div>
                        <div class="collapse navbar-collapse" id="navbar-collapse-1">
                            <ul class="nav navbar-nav" data-dropdown-in="fadeIn" data-dropdown-out="fadeOut">
                                <!--li class="hidden-sm hidden-xs"><a href="#"><i class="fa fa-search"></i></a></li-->
                                <li class="hidden-xs hidden-xs"><!-- <a href="#">My Tasks</a> --></li>

                            </ul>
                            <!-- /.nav navbar-nav -->
                            <ul class="nav navbar-nav navbar-right" data-dropdown-in="fadeIn" data-dropdown-out="fadeOut">

                                <!-- /.dropdown -->
                                <!--li><a href="#" class=""><i class="fa fa-bell"></i><span class="badge badge-danger"></span></a></li>
                                <<li><a href="#" class=""><i class="fa fa-comments"></i><span class="badge badge-warning">2</span></a></li>
                                -->
                                <li class="dropdown">
                                    
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Menu&nbsp;<span class="caret"></span></a>
                                    
                                    <ul class="dropdown-menu profile-dropdown">
                                        <li class="profile-menu bg-gray">
                                            <div class="">
                                                <img src='<%= session.getAttribute("userdp") %>' alt="User" class="img-circle profile-img">
                                                <!--src="images/user-avatar.jpg"-->
                                                <div class="profile-name">
                                                    <!--<h6>User</h6>-->
                                                    <a onclick="gotoLink('/dashboard00034');">View Profile</a>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                        </li>
                                        <li role="separator" class="divider"></li>
                                        <li><a onclick="logout();" class="color-danger text-center"><i class="fa fa-sign-out"></i> Logout</a></li>
                                    </ul>
                                </li>
                                <!-- /.dropdown -->

                            </ul>
                            <!-- /.nav navbar-nav navbar-right -->
                        </div>
                        <!-- /.navbar-collapse -->
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
            </nav>
            <!-- ========== WRAPPER FOR BOTH SIDEBARS & MAIN CONTENT ========== -->
            <div class="content-wrapper">
                <div class="content-container">
                    <!-- ========== LEFT SIDEBAR ========== -->
                    <div class="left-sidebar fixed-sidebar bg-black-300 box-shadow">
                        <div class="sidebar-content">
                            <div class="user-info closed">
                                <img src="http://placehold.it/90/c2c2c2?text=User" alt="User" class="img-circle profile-img">
                                <h6 class="title">User</h6>
                                <small class="info">PHP Developer</small>
                            </div>
                            <!-- /.user-info -->
                            <div id="mymenus" class="sidebar-nav">
                                
                            </div>
                            <!-- /.sidebar-nav -->
                        </div>
                        <!-- /.sidebar-content -->
                    </div>
                    <!-- /.left-sidebar -->
                    <div class="main-page">
                        <div class="container-fluid">
                            <div class="row page-title-div">
                                <div class="col-sm-6">
                                    <h2 class="title">Sub Sector Envelope</h2>
                                </div>
                                <!-- /.col-sm-6 -->
                                <div class="col-sm-6 right-side">

                                </div>
                                <!-- /.col-sm-6 text-right -->
                            </div>
                            <!-- /.row -->
                            <div class="row breadcrumb-div">
                                <div class="col-sm-6">
                                    <ul class="breadcrumb">
                                        <li><a onclick="gotoLink('/dashboard00012');"><i class="fa fa-home"></i> Home</a></li>
                                        <li class="active">Sub Sector Envelope</li>
                                    </ul>
                                </div>
                                <!-- /.col-sm-6 -->
                                <!-- /.col-sm-6 -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.container-fluid -->
                        <section class="section preparation">
                            <div class="row" id="MainArea">
                                <div class="col-md-12">
                                    <div class="panel">
                                        <div class="panel-heading">
                                            <div class="panel-title">
                                                <h5></h5>
                                            </div>
                                        </div>
                                        <div class="panel-body p-20">
                                            <div class="col-md-2">
                                                <div class="input-group col-md-2">
                                                    <label for="budget_year">Budget&nbsp;Year</label>
                                                    <select class='js-states form-control custom_select' id='budget_year' disabled></select>
                                                </div>
                                                <!-- /input-group -->
                                            </div>
                                            <div class="col-md-6"></div>
                                            <div class="col-md-2">
                                                <a class="btn btn-info toggle-code-handle pull-right newpolicy" onclick="distributeEnvelopes();" role="button"><i class="fa fa-user-plus"> </i>Distribute By Weight</a>
                                            </div>
                                            <div class="col-md-2">
                                                <button type="button" id="clear-form" style="width: 120px;" class="btn btn-danger toggle-code-handle pull-left mr-5" role="button" onclick="clearForms();"
                                                        data-toggle="tooltip" data-placement="top" title="Clear Forms"><i class="fa fa-remove"> </i>Clear Forms</button>
                                            </div>
                                            <br style="clear: both;" />
                                            <!-- Nav tabs -->
                                            <ul id="year_menu" class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;"></ul>

                                            <!-- Tab panes -->
                                            <div id="datalist" class="tab-content bg-white p-55" style="overflow: auto">

                                            </div>
<!--                                            <div>
                                                <button type="button" id="clear-form" style="width: 120px;" class="btn btn-danger toggle-code-handle pull-left mr-5" role="button" onclick="clearForms()"
                                                        data-toggle="tooltip" data-placement="top" title="Clear Forms"><i class="fa fa-remove"> </i>Clear Forms</button>

                                                <button type="button" id="submit-final" style="width: 120px;" class="btn btn-success toggle-code-handle pull-right" role="button" onclick="SubmitVersion()"
                                                        data-toggle="tooltip" data-placement="top" title="Submit this version"><i class="fa fa-send"> </i>Submit</button>

                                                <button type="button" id="reject-version" style="width: 120px; display: none;" class="btn btn-danger toggle-code-handle pull-left mr-5" role="button" onclick="RejectVersion()"
                                                        data-toggle="tooltip" data-placement="top" title="Reject version"><i class="fa fa-remove"> </i>Reject</button>

                                                <button type="button" id="approve-version" style="width: 120px; display: none;" class="btn btn-success toggle-code-handle pull-right" role="button" onclick="ApproveVersion()"
                                                        data-toggle="tooltip" data-placement="top" title="Approve version"><i class="fa fa-check"> </i>Approve</button>
                                            </div>-->
                                        </div>
                                    </div>


                                </div>
                                <!-- /.col-md-12 -->
                                
                                <!-- /.Page-Loading -->
                                <div class="modal fade in" id="PageLoading">
                                    <div class="modal-dialog modal-center modal-md">
                                        <div class="modal-content">
                                            <div class="modal-header">
<!--                                                <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true" style="font-size:14px">x</span>
                                                </button>-->
                                                <h4 class="modal-title">Page Loading</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p><br><br><b>Please wait.........</b><br><brprocessing your records</p>
                                            </div>
<!--                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-danger" data-dismiss="modal" data-izimodal-close="" onclick="finishClearForms();">Yes, Clear All Forms</button>
                                                <button type="button" class="btn btn-warning" data-dismiss="modal" data-izimodal-close="">No</button>
                                            </div>-->
                                        </div>

                                    </div>
                                </div> 
                                <!-- Modal -->
                                
                                <div class="modal fade in" id="clearFormModal">
                                    <div class="modal-dialog modal-center modal-md">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true" style="font-size:14px">x</span>
                                                </button>
                                                <h4 class="modal-title">Clear Forms For All Years</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>Are you sure you want to clear all forms</p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="finishClearForms();">Yes, Clear All Forms</button>
                                                <button type="button" class="btn btn-warning" data-dismiss="modal" data-izimodal-close="" onclick="CancelDelete();">No</button>
                                            </div>
                                        </div>

                                    </div>
                                </div> 
                            </div> 
                        </section>

                    </div>
                    <input type="hidden" id="valb4edit" value="NA" />
                    <!-- /.main-page -->
                    <!-- /.right-sidebar -->
                </div>
                <!-- /.content-container -->
            </div>
            <!-- /.content-wrapper -->
        </div>

        <!-- /.main-wrapper -->
        <!-- ========== COMMON JS FILES ========== -->
        <script src="js/jquery/jquery-2.2.4.min.js"></script>
        <script src="js/jquery-ui/jquery-ui.min.js"></script>
        <script src="js/bootstrap/bootstrap.min.js"></script>
        <script src="js/pace/pace.min.js"></script>
        <script src="js/lobipanel/lobipanel.min.js"></script>
        <script src="js/iscroll/iscroll.js"></script>
        <!-- ========== PAGE JS FILES ========== -->
        <script src="js/prism/prism.js"></script>
        <script src="js/waypoint/waypoints.min.js"></script>
        <script src="js/counterUp/jquery.counterup.min.js"></script>
        <script src="js/amcharts/amcharts.js"></script>
        <script src="js/amcharts/pie.js"></script>
        <script src="js/amcharts/serial.js"></script>
        <script src="js/amcharts/xy.js"></script>
        <script src="js/amcharts/plugins/export/export.min.js"></script>
        <link rel="stylesheet" href="js/amcharts/plugins/export/export.css" type="text/css" media="all" />
        <script src="js/amcharts/themes/light.js"></script>
        <script src="js/toastr/toastr.min.js"></script>
        <script src="js/icheck/icheck.min.js"></script>
        <script src="js/iziModal/iziModal.min.js"></script>
        <script src="js/sweet-alert/sweetalert.min.js"></script>
        <script src="js/cropper.min.js"></script>

        <script src="js/moment/moment.min.js"></script>
        <script src="js/mockjax/jquery.mockjax.js"></script> 
        <script src="js/mockjax/demo-mock.js"></script> 
        <script src="js/x-editable/bootstrap-editable.min.js"></script>
        <script src="js/x-editable/demo.js"></script>

        <!-- ========== THEME JS ========== -->
        <script src="js/main.js"></script>
        <script src="js/jquery.loading.js"></script>
        <script src="js/jquery.dataTables.min.js"></script>
        <script src="js/production-chart.js"></script>
        <script src="js/traffic-chart.js"></script>
        <script src="js/task-list.js"></script>
        <script src="js/select2/select2.min.js"></script>
        <script src="js/switchery/switchery.min.js"></script>
        <script src="js/bootstrap-switch/bootstrap-switch.min.js"></script>

        <!-- ========== ADD custom.js FILE BELOW WITH YOUR CHANGES ========== -->
        <script>
            checkLogin();
            $(document).ready(function () {
                //$('#main-table').DataTable();
                getSubSectors();

                //getRoles();
                //getUsers();
                //getCategorys();
                createCookie("formtype", "");
                //$('#clearFormModal').iziModal();
                
                //Close side-menu
                $('#nav-togglerx').click();

                /*$("#addrolemenu").iziModal();
                 $("#selRoles_a, #selRoles_b, #selUsers_a, #txtRole_a, #menucategory0").select2();
                 //$('#chkActive, #chkActiveB').bootstrapSwitch({onText: 'Active', offText: 'Blocked'});
                 //$('.switch-checkboxes').bootstrapSwitch({ onText: 'Active', offText: 'Blocked' });
                 if (readCookie("checkAllStatus") === "true") {
                 $('#checkAllActive').bootstrapSwitch('state', true);
                 } else {
                 $('#checkAllActive').bootstrapSwitch('state', false);
                 }
                 createCookie("checkAllActive");*/

            });
            var sectorlist = null;
            function getSubSectors() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SubSectorCeilingServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_NAME %>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        sectorlist = data;
                        getBudgetYear();
                    },
                    error: function () {
                        toastr["error"]("No record selected!", "Sub Sector Selection Failed!!!");
                    }
                });
            }
            function getBudgetYear() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SubSectorCeilingServlet",
                    data: {option: "<%= Utility.OPTION_SELECT%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: budgetYearReturnValues,
                    error: function () {
                        toastr["error"]("No record selected!", "Budget Year Selection Failed!!!");
                    }
                });
            }
            var currentyear = null;
            function budgetYearReturnValues(data) {
               if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "' selected>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                            if (attrName === "2") {
                                currentyear = attrValue;
                            }
                        }
                    }
                    $('#budget_year').html(resp);
                }
                getMenuYear();

            };

            var contingencies = 0.0;
            function getMenuYear() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SubSectorCeilingServlet",
                    data: {option: "<%= Utility.OPTION_RESET_MENU%>", budget_year_id: currentyear},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: menuYearReturnValues,
                    error: function () {
                        toastr["error"]("No record selected!", "Menu Year  Selection Failed!!!");
                    }
                });
            }
            function menuYearReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    var flag = 0;
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }
                            if (attrName === "0") {
                                if (flag === 0) {
                                    flag = 1;
                                    resp += "<li role='presentation' class='active'><a class='' href='#" + attrValue + "' aria-controls='" + attrValue + "' role='tab' data-toggle='tab' aria-expanded='true'>" + attrValue + "</a></li>";
                                } else {
                                    resp += "<li role='presentation' class=''><a class='' href='#" + attrValue + "' aria-controls='" + attrValue + "' role='tab' data-toggle='tab' aria-expanded='true'>" + attrValue + "</a></li>";
                                }
                            }
                            if (attrName === "1" && contingencies === 0.0) {
                                contingencies = attrValue;
                            }
                        }
                    }
                    $('#year_menu').html(resp);
                }
                getHeadList();

            };

            var headlist = null;
            function getHeadList() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SubSectorCeilingServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        headlist = data;
                        createDataList();
                    },
                    error: function () {
                        toastr["error"]("No record selected!", "Menu Year  Selection Failed!!!");
                    }
                });
            }

            function createDataList() {
                ShowLoading();
                //$('#PageLoading').modal('show');
                var budget_year = document.getElementById("budget_year").value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SubSectorCeilingServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL %>", budget_year_id: budget_year},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: dataListReturnValues,
                    error: function () {
                        toastr["error"]("No record selected!", "Menu Year  Selection Failed!!!");
                        StopLoading();
                        //$('#PageLoading').modal('hide');
                    }
                });
            }
            
            var component_type_list = "";
            function dataListReturnValues(data) {
//console.log(data);            
//console.log(data);                        
                if (data.length > 0) {
                    var resp = "";                        
                    var header = "";
                    var activeflag = true;
                    var baseYear = "";
                    var currYear = "";
                    var record_id = "";
                    var columnlist = "";
                    var header1 = 0;
                    for (var i = 0; i <= data.length; i++) {
                        var obj = data[i];
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }
                            if (attrName === "0") {
                                //alert(baseYear +">"+ header);
                                if (attrValue > baseYear && header > "") {
                                    resp += "</tr><tr bgcolor='#ffff66'><td><b>Sub Sectors</b></td>";
                                    for (var j = 0; j < headlist.length; j++) {
                                        var obj2 = headlist[j];
                                        for (var key2 in obj2) {
                                            var attrName2 = key2;
                                            var attrValue2 = obj2[key2];
                                            if (attrValue2 === null || attrValue2 === 'null') {
                                                attrValue2 = "";
                                            }
                                            if (attrName2 === "1") {
                                                resp += "<td style='text-align: right'><b>" + attrValue2 + " (&#8358;)</b></td>";
                                            }
                                        }
                                    }

                                    resp += "</tr><tr>";
                                    sectorid = "";
                                    //console.log(sectorlist);
                                    for (var k = 0; k < sectorlist.length; k++) {
                                        var obj3 = sectorlist[k];
                                        for (var key3 in obj3) {
                                            var attrName3 = key3;
                                            var attrValue3 = obj3[key3];
                                            if (attrValue3 === null || attrValue3 === 'null') {
                                                attrValue3 = "";
                                            }
                                            if (attrName3 === "0") {
                                                sectorid = parseInt(attrValue3).toString();
                                            }
                                            if (attrName3 === "1") {
                                                resp += "<td><b>" + attrValue3 + "</b></td>";
                                                reclist_arr = columnlist.split(",");
                                                for (var l = 0; l < reclist_arr.length; l++) {
                                                    var recid = baseYear + "_" + reclist_arr[l] + "_" + sectorid;
                                                    resp += "<td class='edit-column' align='right'><a onclick=$('#valb4edit').val(this.innerHTML);_checkEnvelopeAmount=true; class='rowDataSd editable-control envelope-fig figx' href='#' id='" + recid + "' data-name='" + recid + "' data-type='text' data-pk='1' data-title='Sub Sector Value'></a>&nbsp;</td>";
                                                }
                                            }
                                        }
                                        resp += "</tr><tr>";
                                    }

                                    resp += "<tr><td><b>Total Distributed</b></td>";
                                    //console.log(columnlist);
                                    reclist_arr = columnlist.split(",");
                                    for (var l = 0; l < reclist_arr.length; l++) {
                                        var recid = baseYear + "_" + reclist_arr[l] + "_total";
                                        if (l === 0) {
                                            resp += "<td align='right'><input style='text-align: right' value='0.00' id='" + recid + "' type='text' readonly disabled size='15' /></td>";
                                        } else {
                                            resp += "<td align='right'><input style='text-align: right' value='0.00' id='" + recid + "' type='text' readonly disabled size='14' /></td>";
                                        }
                                    }
                                    resp += "</tr></table></div>";
                                    component_type_list = columnlist;
                                    columnlist = "";
                                    header1 = 0;
                                }
                                baseYear = attrValue;
                            }
                            if (attrName === "1") {
                                if (columnlist === "") {
                                    columnlist += attrValue;
                                } else {
                                    columnlist += "," + attrValue;
                                }
                                record_id = baseYear + "_" + attrValue;
                            }
                            if (attrName === "2") {
                                attrValue = attrValue.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                if (baseYear > header) {
                                    header = baseYear;
                                    if (activeflag) {
                                        activeflag = false;
                                        currYear = baseYear;
                                        var heade_column = headlist.length - 3;
                                        resp += "<div role='tabpanel' class='tab-pane active' id='" + header + "'><table class='table table-clean table-striped' border='0'>";
                                        resp += "<tr><td></td><td><b>Contingency:</b></br><input style='text-align: right'  id='contingency0_" + header + "' type='text' readonly disabled size='15' /></td>";
                                        resp += "<td colspan='2'><b>Envelope Less Contingency:</b></br><input style='text-align: right' id='revenue_contingency0_" + header + "' type='text' value='" + attrValue + "' readonly disabled size='15' /></td>";
                                        resp += "<td colspan='"+heade_column+"'></td></tr>";
                                        resp += "<tr><td></td><td><input style='text-align: right'  id='contingency_" + header + "' type='text' readonly disabled size='15' /></td>";
                                        resp += "<td colspan='2'><input style='text-align: right' id='revenue_contingency_" + header + "' type='text' value='" + attrValue + "' readonly disabled size='15' /></td>";
                                        resp += "<td colspan='"+heade_column+"'></td></tr>";
                                        resp += "<tr><td><b>Total Envelope For State:&nbsp;</b></td>";
                                    } else {
                                        resp += "<div role='tabpanel' class='tab-pane' id='" + header + "'><table class='table table-clean table-striped' border='0'>";
                                        resp += "<tr><td></td><td colspan='3'><b>Envelope:</b><input id='contingency0_" + header + "' type='hidden' value='0.00' /></br><input id='revenue_contingency0_" + header + "' type='hidden' value='" + attrValue + "' /></td>";
                                        resp += "<td colspan='"+heade_column+"'></td></tr>";
                                        resp += "<tr><td></td><td colspan='3'><input id='contingency_" + header + "' type='hidden' value='0.00' /><input style='text-align: right' id='revenue_contingency_" + header + "' type='text' value='" + attrValue + "' readonly disabled size='15' /></td>";
                                        resp += "<td colspan='"+heade_column+"'></td></tr>";
                                        resp += "<tr><td><b>Total Envelope For State:&nbsp;</b></td>";
                                    }
                                }
                                
                                
                                
                                var hidden_header = record_id + "_hidden";
                                if (header1 < 1 && i < data.length - 1) {
                                    header1 = 1;
                                    resp += "<td style='text-align: right'><input id='" + hidden_header + "' type='hidden' value='" + attrValue + "' />";
                                    resp += "<input style='text-align: right' id='" + record_id + "_0' type='text' readonly disabled value='" + attrValue + "' size='15' /></td>";
                                    //resp += "<a onclick=$('#valb4edit').val(this.innerHTML); class='rowDataSd editable-control envelope-fig figx' href='#' id='" + record_id + "_0' data-namerecid='" + record_id + "' data-type='text' data-pk='1' data-title='State Value'>" + attrValue + "</a>&nbsp;</td>";

                                } else {
                                    resp += "<td style='text-align: right'><input id='" + hidden_header + "' type='hidden' value='" + attrValue + "' />";
                                    //resp += "<input style='text-align: right' id='" + record_id + "_0' type='text' readonly disabled value='" + attrValue + "' size='14' /></td>";
                                    resp += "<a onclick=$('#valb4edit').val(this.innerHTML); class='rowDataSd editable-control envelope-fig figx' href='#' id='" + record_id + "_0' data-name='" + record_id + "_0' data-type='text' data-pk='1' data-title='State Value'>" + attrValue + "</a>&nbsp;</td>";
                                }
                            }
                        }
                    }

                    resp += "</tr><tr bgcolor='#ffff66'><td><b>Sub Sectors</b></td>";
                    for (var j = 0; j < headlist.length; j++) {
                        var obj2 = headlist[j];
                        for (var key2 in obj2) {
                            var attrName2 = key2;
                            var attrValue2 = obj2[key2];
                            if (attrValue2 === null || attrValue2 === 'null') {
                                attrValue2 = "";
                            }
                            if (attrName2 === "1") {
                                resp += "<td style='text-align: right'><b>" + attrValue2 + " (&#8358;)</b></td>";
                            }
                        }
                    }

                    resp += "</tr><tr>";
                    sectorid = "";
                    for (var k = 0; k < sectorlist.length; k++) {
                        var obj3 = sectorlist[k];
                        for (var key3 in obj3) {
                            var attrName3 = key3;
                            var attrValue3 = obj3[key3];
                            if (attrValue3 === null || attrValue3 === 'null') {
                                attrValue3 = "";
                            }
                            if (attrName3 === "0") {
                                sectorid = parseInt(attrValue3).toString();
                            }
                            if (attrName3 === "1") {
                                resp += "<td><b>" + attrValue3 + "</b></td>";
                                reclist_arr = columnlist.split(",");
                                for (var l = 0; l < reclist_arr.length; l++) {
                                    var recid = baseYear + "_" + reclist_arr[l] + "_" + sectorid;
                                    resp += "<td class='edit-column' align='right'><a onclick=$('#valb4edit').val(this.innerHTML);_checkEnvelopeAmount=true; class='rowDataSd editable-control envelope-fig figx' href='#' id='" + recid + "' data-name='" + recid + "' data-type='text' data-pk='1' data-title='Sub Sector Value'></a>&nbsp;</td>";
                                }
                            }
                        }
                        resp += "</tr><tr>";
                    }

                    resp += "<tr><td><b>Total Distributed</b></td>";
                    reclist_arr = columnlist.split(",");
                    for (var l = 0; l < reclist_arr.length; l++) {
                        var recid = baseYear + "_" + reclist_arr[l] + "_total";
                        if (l === 0) {
                            resp += "<td><input style='text-align: right' id='" + recid + "' value='0.00' type='text' readonly disabled size='15' /></td>";
                        } else {
                            resp += "<td><input style='text-align: right' id='" + recid + "' value='0.00' type='text' readonly disabled size='14' /></td>";
                        }
                    }
                    resp += "</tr></table></div>";
                    $('#datalist').html(resp);
                    //
                    //console.log(resp);
                    //contingencies = parseFloat(contingencies);
                    var arr_component_type_list = component_type_list.split(",");
                    var revenue_less_contingency = parseFloat($('#'+currYear + '_' + arr_component_type_list[0] + "_0").val().replace(/,/g, "")) - contingencies;
                    $('#contingency0_'+currYear).val(contingencies.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                    $('#contingency_'+currYear).val(contingencies.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                    $('#revenue_contingency0_'+currYear).val(revenue_less_contingency.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                    $('#revenue_contingency_'+currYear).val(revenue_less_contingency.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                    
//console.log("revenue_contingency0_2019   "+$("#revenue_contingency0_2019").val());                
//console.log("revenue_contingency_2019   "+$("#revenue_contingency_2019").val());                
//console.log("contingency0_2019   "+$("#contingency0_2019").val());                
//console.log("contingency_2019   "+$("#contingency_2019").val());                
//console.log("");                
//                    
//console.log("revenue_contingency0_2020   "+$("#revenue_contingency0_2020").val());                
//console.log("revenue_contingency_2020   "+$("#revenue_contingency_2020").val());                
//console.log("contingency0_2020   "+$("#contingency0_2020").val());                
//console.log("contingency_2020   "+$("#contingency_2020").val());                
//console.log("");                
//                    
//console.log("revenue_contingency0_2021   "+$("#revenue_contingency0_2021").val());                
//console.log("revenue_contingency_2021   "+$("#revenue_contingency_2021").val());                
//console.log("contingency0_2021   "+$("#contingency0_2021").val());                
//console.log("contingency_2021   "+$("#contingency_2021").val());                
//console.log("");                
                    
                
                    //initializeEditableControls();
                    StopLoading();
                    getEnvelopes();
                }
                
                //$('#PageLoading').modal('hide');
            };

            /**
             * Method for initialising editable controls
             * 
             * @returns
             */
            function initializeEditableControls() {
                $('.editable-control').each(function () {
                    var id = $(this).attr('id');
                    var title = $(this).attr('data-title');
                    var name = $(this).attr('data-name');
                    var pk = $(this).attr('data-pk');
                    var type = $(this).attr('data-type');
                    //var classs = $(this).attr('class');
                    if (type === 'text' || type === 'number') {
                        $('#' + id).editable({
                            url: '/post',
                            type: 'number',
                            pk: pk,
                            name: name,
                            title: title,
                            display: function (value) {
                                if (!value) {
                                    if($('#valb4edit').val()==="NA"){
                                        $(this).html('0.00');
                                    }else{
                                        $(this).html($('#valb4edit').val());
                                    }
                                    return;
                                } else {
                                    var grep = value.replace(/,/g, "");
                                    if (isNaN(grep)) {
                                        alert("The entered figure (" + value + ") is not a valid value");
                                        value = null;
                                        //document.getElementById("submit-final").disabled = true;
                                        return;
                                    } else {
                                        //document.getElementById("submit-final").disabled = false;
                                    }
                                }
                                var html = value.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                if (html.indexOf('.') === -1) {
                                    html += '.00';
                                }
                                $(this).html(html);

                                var id_s = $(this).attr('id');
                                var id_arr = id_s.split("_");
                                if(id_arr[id_arr.length-1]==="0"){
                                    //console.log("H: "+id_s);
                                    calculateheader(id_s);
                                }else{
                                    //console.log("B: "+id_s);
                                    calculateBody(id_s);
                                }
                            }
                        });
                    }
                });
            }
            
            function calculateheader(id_s) {
                sum = 0.0;
                var id_arr = id_s.split("_");
                var arr_component_type_list = component_type_list.split(",");
                for (var k = 0; k < arr_component_type_list.length; k++) {
                    if(k>0){
                        var id = id_arr[0] + "_" + arr_component_type_list[k] + "_0";
                        var val = $("#" + id).html().replace(/,/g, "");
                        if(val === null || val.length===0){
                            $("#" + id).html("0.0");
                        }
                        var val = parseFloat($("#" + id).html().replace(/,/g, ""));
                        
                        var id_total = id_arr[0] + "_" + arr_component_type_list[k] + "_total";
                        var val_total = $("#" + id_total).val().replace(/,/g, "");
                        if(val_total === null || val_total.length===0){
                            $("#" + id_total).val("0.0");
                        }
                        var val_total = parseFloat($("#" + id_total).val().replace(/,/g, ""));
                        sum += val + val_total;
                    }
                }
                var contingency = parseFloat($("#contingency0_"+id_arr[0]).val().replace(/,/g, ""));
                if(isNaN(contingency)){
                    contingency = 0.0;
                }
                
                var revenue_contingency = parseFloat($("#revenue_contingency0_"+id_arr[0]).val().replace(/,/g, "").replace(/ /g, ""));

                if(isNaN(revenue_contingency)){
                    revenue_contingency = 0.0;
                }
//                if(isNaN(revenue_contingency)){
//                    revenue_contingency = parseFloat($('#'+id_arr[0] + '_1' + "_0").val().replace(/,/g, ""));
//                }
                
                if($('#valb4edit').val()==="NA"){
                    revenue_contingency -= sum;
                    if(revenue_contingency < 0){
                        if(id_arr[0] === currentyear.toString()){
                            contingency += revenue_contingency;
                        }
                        revenue_contingency = 0.0;
                        $("#revenue_contingency_"+id_arr[0]).val("0.00");
                        $("#contingency_"+id_arr[0]).val(contingency.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                    }else{
                        $("#revenue_contingency_"+id_arr[0]).val(revenue_contingency.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                        $("#contingency_"+id_arr[0]).val(contingency.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                    }
                    return true;
                }
                var hidden_id = id_arr[0] + "_" + id_arr[1] + "_hidden";
                var old_val = parseFloat(parseFloat($("#valb4edit").val().replace(/,/g, "")).toFixed(2));
                var new_val = parseFloat(parseFloat($("#" + id_s).html().replace(/,/g, "")).toFixed(2));
                var hid_val = parseFloat(parseFloat($("#" + hidden_id).val().replace(/,/g, "")).toFixed(2));

                if(new_val > old_val){
                    var revenue_contingency1 = parseFloat(parseFloat($("#revenue_contingency_"+id_arr[0]).val().replace(/,/g, "")).toFixed(2));;
                    var diff = parseFloat((new_val - old_val).toFixed(2));
                    new_val = hid_val + (new_val - old_val);
                    $("#" + hidden_id).val(new_val);
                    if((revenue_contingency1 - diff) < 0){
                        var contingency1 = parseFloat(parseFloat($("#contingency_"+id_arr[0]).val().replace(/,/g, "")).toFixed(2));
//                        if(id_arr[0] === currentyear.toString()){
//                            if(confirm("Insufficient Amount in Envelope\n\nUse Contingency?")){
//                                $("#revenue_contingency_"+id_arr[0]).val("0.00");
//                                contingency1 -= parseFloat((diff - revenue_contingency1).toFixed(2));
//                                if(contingency1<0){
//                                    alert("Insufficient Amount in Contingency!!!\n\nThe Amount you entered is more than the Contingency balance");
//                                    $("#" + id_s).html($("#valb4edit").val().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
//                                    return true;
//                                }else{
//                                    $("#contingency_"+id_arr[0]).val(contingency1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
//                                    if($('#valb4edit').val()!=="NA"){
//                                        saveEnvelope($("#" + id_s).attr('id'), new_val, "state");
//                                        $('#valb4edit').val('NA');
//                                    }
//                                }
//                            }
//                        }else{
                            if((revenue_contingency1 - diff) < 0){
                                alert("Insufficient Amount in Envelope!!!\n\nThe Amount you entered is more than the Envelope balance");
                                $("#" + id_s).html($("#valb4edit").val().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                                return true;
                            }else{
                                revenue_contingency1 -= diff;
                                $("#revenue_contingency_"+id_arr[0]).val(revenue_contingency1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                                if($('#valb4edit').val()!=="NA"){
                                    saveEnvelope($("#" + id_s).attr('id'), new_val, "state");
                                    $('#valb4edit').val('NA');
                                }
                            }
                        //}
                    }else{
                        revenue_contingency1 -= diff;
                        $("#revenue_contingency_"+id_arr[0]).val(revenue_contingency1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                        if($('#valb4edit').val()!=="NA"){
                            saveEnvelope($("#" + id_s).attr('id'), new_val, "state");
                            $('#valb4edit').val('NA');
                        }
                    }
                }else{
                    var diff = (old_val - new_val);
                    new_val = hid_val + (new_val - old_val);
                    $("#" + hidden_id).val(new_val);
                    var revenue_contingency1 = parseFloat(parseFloat($("#revenue_contingency_"+id_arr[0]).val().replace(/,/g, "")).toFixed(2));
                    if(id_arr[0] === currentyear.toString()){
                        var contingency1 = parseFloat(parseFloat($("#contingency_"+id_arr[0]).val().replace(/,/g, "")).toFixed(2));
                        if((diff + contingency1) <= contingency){
                            contingency1 += diff;
                        }else if((diff + contingency1) > contingency){
                            diff -= (contingency - contingency1);
                            contingency1 = contingency;
                            revenue_contingency1 += diff;
                        }else{
                            revenue_contingency1 += diff;
                        }
                        if($('#valb4edit').val()!=="NA"){
                            saveEnvelope($("#" + id_s).attr('id'), new_val, "state");
                            $('#valb4edit').val('NA');
                        }
                        $("#contingency_"+id_arr[0]).val(contingency1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                        $("#revenue_contingency_"+id_arr[0]).val(revenue_contingency1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                    }else{
                        revenue_contingency1 += diff;
                        if($('#valb4edit').val()!=="NA"){
                            saveEnvelope($("#" + id_s).attr('id'), new_val, "state");
                            $('#valb4edit').val('NA');
                        }
                        $("#revenue_contingency_"+id_arr[0]).val(revenue_contingency1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                    }
                }                
            }
            function calculateBody(id_s) {
                var sum = 0.0;
                //console.log("sum: "+sum);
                var id_arr = id_s.split("_");
                for (var k = 0; k < sectorlist.length; k++) {
                    var obj = sectorlist[k];
                    var sectorid = "";
                    for (var key in obj) {
                        var attrName = key;
                        var attrValue = obj[key];
                        if (attrValue === null || attrValue === 'null') {
                            attrValue = "";
                        }
                        if (attrName === "0") {
                            sectorid = parseInt(attrValue).toString();
                        }
                    }

                    var id = id_arr[0] + "_" + id_arr[1] + "_" + sectorid;
                    //alert($("#" + id).html());
                    var val = parseFloat(parseFloat($("#" + id).html().replace(/,/g, "")).toFixed(2));
                    //console.log("id: "+id+"            val: "+val);
                    if (!isNaN(val)) {
                        sum += val;
                    }else{
                        sum += 0;
                    }
                    //console.log("id: "+id+"            val: "+val+"            sum: "+sum);
                    
                }
//console.log("id: "+id+"            val: "+val+"            sum: "+sum);
                var header_id_hidden = id_arr[0] + "_" + id_arr[1] + "_hidden";
                var header_val = $("#" + header_id_hidden).val();
                var header_id = id_arr[0] + "_" + id_arr[1] + "_0";
                var sub_header_id = id_arr[0] + "_" + id_arr[1] + "_total";
                header_val = parseFloat(parseFloat(header_val.replace(/,/g, "")).toFixed(2));
                //if (Math.abs(header_val - sum) < 1) {
                //console.log((sum - header_val)+"    "+_checkEnvelopeAmount);
                if (sum > header_val && _checkEnvelopeAmount) { // && Math.abs(sum - header_val) >= 1
                    _checkEnvelopeAmount=false;
                    toastr["error"]("The Amount entered throws envelope into minus!", "Amount Greater Than Envelope!!!");
//console.log("id_s: "+id_s+"              header_id: "+header_id+"    header_val: "+header_val+"    sum: "+sum+"    Diff: "+Math.abs(sum - header_val));
                    //console.log("header_id: "+header_id+"    header_val: "+header_val+"    sum: "+sum)
                    html = $('#valb4edit').val();
                    if (html === null || html === "" || isNaN(html.replace(/,/g, ""))) {
                        html = '0.00';
                    }
                    $("#" + id_s).html(html);
                    return true;
                }
                
                //if ((header_val - sum) < 1) {
                //    sum = header_val;
                //}
                
                if (header_val > 0) {
                    header_val = parseFloat((header_val - sum).toFixed(2));
                } else {
                    header_val = 0.0;
                }
                //if (parseInt(header_val) < 0) {
                /*if (header_val < 0) {
                    toastr["error"]("The Amount entered throws envelope into minus!", "Amount Greater Than Envelope!!!");
                    html = $('#valb4edit').val();
                    if (html === null || html === "" || isNaN(html.replace(/,/g, ""))) {
                        html = '0.00';
                    }
                    $("#" + id_s).html(html);
                    return true;
                }*/
                var html = "";
                if (isNaN(header_val)) {
                    html = "0.00";
                } else {
                    html = header_val.toFixed(2).toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                }
                if (html.indexOf('.') === -1) {
                    html += '.00';
                }
                $("#" + header_id).html(html);
                
                var html = sum.toFixed(2).toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                if (html.indexOf('.') === -1) {
                    html += '.00';
                }
                $("#" + sub_header_id).val(html);
                
                if($('#valb4edit').val()!=="NA"){
                    var val = parseFloat($("#" + id_s).html().replace(/,/g, ""));
                    saveEnvelope(id_s, val);
                    $('#valb4edit').val('NA');
                }
            }
            
            var _checkEnvelopeAmount = false;
            function saveEnvelope(id, val, type) {
                var id_arr = id.split("_");
                var sub_sector_id = id_arr[2];
                var budget_type_component_id = id_arr[1];
                var total_amount = val;
                var budget_year_id = id_arr[0];
                if(type==="state"){
                    sub_sector_id = "0";
                }
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SubSectorCeilingServlet",
                    data: {option: "<%= Utility.OPTION_INSERT%>", sub_sector_id: sub_sector_id, sub_sector_weight: "0.0", budget_type_component_id: budget_type_component_id, total_amount: total_amount, budget_year_id: budget_year_id},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function () {
                        toastr["success"]("Record saved Successfully!", "Record saved Successfully!!!");
                        //getEnvelopes();
                    },
                    error: function () {
                        toastr["error"]("No record saved!", "No record saved!!!");
                    }
                });
            };

            function getEnvelopes() {
                ShowLoading();
                //$('#PageLoading').modal('show');
                var budget_year_id = document.getElementById("budget_year").value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SubSectorCeilingServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID%>", budget_year_id: budget_year_id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: getEnvelopeReturnValues,
                    error: function () {
                        toastr["error"]("No record fetched!", "No Record Fetched!!!");
                        StopLoading();
                        //$('#PageLoading').modal('hide');
                    }
                });
            }
            function getEnvelopeReturnValues(data) {
                if (data.length > 0) {
                    var id = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = 0;
                            }
                            if (attrName === "0") {
                                id = attrValue;
                            }
                            if (attrName === "1") {
                                $("#" + id).html(attrValue.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
                                var id_arr = id.split("_");
                                if(id_arr[id_arr.length-1] === "0"){
                                    calculateheader(id);
                                }else{
                                    calculateBody(id);
                                }
                            }
                        }
                    }
                }
                initializeEditableControls();
                StopLoading();
                //$('#PageLoading').modal('hide');
            }

            function distributeEnvelopes() {
                ShowLoading();
                //$('#PageLoading').modal('show');
                setTimeout(function(){ distributeEnvelopes2(); }, 3000);
            }
            function distributeEnvelopes2() {
                //$('#PageLoading').modal('show');
                var budget_year_id = document.getElementById("budget_year").value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SubSectorCeilingServlet",
                    data: {option: "<%= Utility.OPTION_DISTRIBUTE_ENVELOPES %>", budget_year_id: budget_year_id},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function () {
                        toastr["success"]("All Envelopes are successfully distributed!", "Envelopes Distributed!!!");
                        window.location.href = window.location;
                        //getEnvelopes();
                        StopLoading();
                        getEnvelopes();
                    },
                    error: function () {
                        toastr["error"]("Attempt to distribute Envelopes failed!", "Envelopes Distribution Failed!!!");
                        StopLoading();
                    }
                });
            }
            
            ShowLoading = function () {
                $("html").loading({
                    stoppable: true
                });
            };
            StopLoading = function () {
                $("html").loading('stop');
            };


            /**
             * Method for hiding approvals
             * 
             * @returns
             */
//            function hideApprovals() {
//                //disable things
//                $("#reject-version").hide();
//                $("#approve-version").hide();
//            }

            /**
             * Method for showing approvals
             * 
             * @returns
             */
//            function showApprovals() {
//                //enable things
//                $("#reject-version").show();
//                $("#approve-version").show();
//            }

            /**
             * Method for hiding submission and save buttons
             * 
             * @returns
             */
//            function hideSubmission() {
//                //disable things
//                $("#submit-final").hide();
//                $("#save-version").hide();
//
//                $("#mybf-version").attr('disabled', true);
//            }
//
//            function checkWorkflowStatus() {
//                var requestTypeID = parseInt($('#req-type-id').val());
//
//                data = {};
//                data['option'] = $('#check-workflowx').val();
//                data['requestTypeID'] = requestTypeID;
//                data['roles'] = $('#roles-idx').val();
//                data['mdaID'] = $('#mda-idx').val();
//                data['userID'] = $('#v5er-idx').val();
//
//                $.ajax({
//                    type: "POST",
//                    url: $('#site-url').val() + "/RequestApprovalsServlet",
//                    data: data,
//                    dataType: "json",
//                    cache: false,
//                    success: function (response) {
//                        if (response.length > 0) {
//                            $.each(response, function (index, element) {
//                                currentWorkflowStatus = element[0];
//
//                                if (currentWorkflowStatus != -1)
//                                    $('#request-det-id').val(element[1]);
//                            });
//
//                            if (currentWorkflowStatus == -1) {
//                                toastr["error"]("You are not authorized to view this page, please contact admin.", "Failure");
//
//                                gotoLink('/codem00017');
//                            } else if (currentWorkflowStatus == 0 ||
//                                    currentWorkflowStatus == 3) {
//                                hideSubmission();
//                                hideApprovals();
//                            } else if (currentWorkflowStatus == 1) {
//                                hideSubmission();
//                                showApprovals();
//                            } else if (currentWorkflowStatus == 2) {
//                                hideApprovals();
//
//                                canUpdate = true;
//                            } else if (currentWorkflowStatus == 4 ||
//                                    currentWorkflowStatus == 5 ||
//                                    currentWorkflowStatus == 6) {
//                                document.getElementById("submit-final").disabled = true;
//                                document.getElementById("save-version").disabled = true;
//
//                                hideSubmission();
//                                hideApprovals();
//
//                                canUpdate = false;
//
//                                //show generate report
//                                if (currentWorkflowStatus == 4)
//                                    $("#generate-report").show();
//                                else if (currentWorkflowStatus == 5 ||
//                                        currentWorkflowStatus == 6)
//                                    $("#view-report").show();
//                            }
//                        }
//                    },
//                    error: function () {
//                    }
//                });
//            }

            function clearForms(arg) {
                $('#clearFormModal').modal('show');
                //$('#clearFormModal').iziModal('open');
                //window.deleteId = arg;
            }

            function cancelClearForms() {
                $('#clearFormModal').modal('hide');
                //$('#clearFormModal').iziModal('close');
                //window.deleteId = 0;
            }

            function finishClearForms() {
                $('#clearFormModal').modal('hide');
                ShowLoading();
                //$('#PageLoading').modal('show');
                setTimeout(function(){ finishClearForms2(); }, 3000);
            }
            function finishClearForms2() {
                var budget_year_id = document.getElementById("budget_year").value;

                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SubSectorCeilingServlet",
                    data: {option: "<%= Utility.OPTION_DELETE_BY_ID %>", budget_year_id: budget_year_id},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.indexOf("<%= Utility.ActionResponse.DELETED.toString()%>") !== -1) {
                            $('#clearFormModal').modal('hide');
                            //$('#clearFormModal').iziModal('close');
                            toastr["success"]("All form cleared Successfull!", "Forms Clearing Successfull");
                            window.location.href=window.location;
                        }
                        StopLoading();
                        //$('#PageLoading').modal('hide');
                    },
                    error: function () {
                        $('#clearFormModal').modal('hide');
                        //$('#clearFormModal').iziModal('close');
                        toastr["error"]("Clearing of forms Failed!", "Forms Clearing Failed!");
                        StopLoading();
                        //$('#PageLoading').modal('hide');
                    }
                });
            }

        </script>
    </body>
</html>
