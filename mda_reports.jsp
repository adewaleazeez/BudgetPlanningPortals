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

        <!--         DATA TABLE CSS -->

        <link rel="stylesheet" href="js/DataTables/DataTables-1.10.13/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="js/DataTables/Buttons-1.2.4/css/buttons.dataTables.min.css">

        <!-- ========== THEME CSS ========== -->
        <link rel="stylesheet" href="css/main.css" media="screen">
        <!-- ========== MODERNIZR ========== -->
        <script src="js/modernizr/modernizr.min.js"></script>
        <!-- For Loading -->
        <link href="css/jquery.loading.css" rel="stylesheet" />
        <link rel="stylesheet" href="js/amcharts/plugins/export/export.css" type="text/css" media="all" />
        <!-- CK Editor -->
        <script src="js/ckeditor/ckeditor.js"></script>
        <style>
            div.dt-buttons {
                float: left;
            }
            @media print {
                tr.page-break  { display: block; page-break-before: always; }
            } 
        </style>
        <!--        ===========================-->
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
        <script src="js/select2/select2.min.js"></script>
        <script src="js/switchery/switchery.min.js"></script>
        <script src="js/bootstrap-switch/bootstrap-switch.min.js"></script>
        <!-- DATA TABLE STUFF -->
        <script src="js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="js/DataTables/Buttons-1.2.4/css/buttons.dataTables.min.css">
        <script src="js/DataTables/dataTables.buttons.min.js"></script>            
        <script src="js/DataTables/JSZip-2.5.0/jszip.min.js"></script>            
        <script src="js/DataTables/pdfmake-0.1.18/build/pdfmake.min.js"></script>            
        <script src="js/DataTables/pdfmake-0.1.18/build/vfs_fonts.js"></script>            
        <script src="js/DataTables/Buttons-1.2.4/js/buttons.html5.min.js"></script>
        <script src="js/DataTables/Buttons-1.2.4/js/buttons.print.js"></script>    
    </head>
    <body class="top-navbar-fixed">
        <div class="main-wrapper">
            <!-- ========== TOP NAVBAR ========== -->
            <nav class="navbar top-navbar bg-white box-shadow">
                <div class="container-fluid">
                    <div class="row">
                        <div class="navbar-header no-padding">
                            <a class="navbar-brand" href="index.html">
                                <img src="images/logo-dark.svg" alt="Options - Admin Template" class="logo">
                            </a>
                            <span class="small-nav-handle hidden-sm hidden-xs">
                                <i class="fa fa-outdent"></i>
                            </span>
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
                                    <h2 class="title">Budget Reports </h2>
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
                                        <li class="active">Budget Reports</li>
                                    </ul>
                                </div>
                                <!-- /.col-sm-6 -->
                                <!-- /.col-sm-6 -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.container-fluid -->
                        <section class="section preparation">
                            <!--***************************************************Page Area START*********************************************************************-->
                            <div class="container-fluid">

                                <div class="container">
                                    <!-- Nav tabs -->
                                    <ul class="nav nav-tabs border-primary" role="tablist" >
                                        <li role="presentation" class="active">
                                            <a class="" href="#BudgetBookTemplateTab" aria-controls="BudgetBookTemplateTab"
                                               role="tab" data-toggle="tab" aria-expanded="false">Budget Reports</a>
                                        </li>
                                    </ul>

                                    <!-- Tab panes -->
                                    <div class="tab-content bg-white p-15">
                                        <div role="tabpanel" class="tab-pane active" id="SegmentTab" style="background-color: #f0f0f1">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <label for="budget_year">Budget&nbsp;Year</label>
                                                    <select class='js-states form-control custom_select' id='budget_year' disabled></select>
                                                </div>
                                                <div class="col-md-4">
                                                    <label>Budget Version</label>
                                                    <select class="form-control" id="cmbBudgetVersion" onchange="ChangeBudgetVersion(this.value)"></select>
                                                </div>                                            
                                            </div>
                    
                                            <div class="row">
                                                <div class="col-md-12" style="display: block;float:left;">
                                                    <div class="row" style="margin-left: 0px;margin-right: 0px;">
                                                        <div class="span6 col-xs-5">
                                                            <div style="color: rgb(28, 37, 43);margin-left: 10px;margin-top: 10px;font-size: 18px;font-weight: 900">Revenue Reports :</div>
                                                            <div class="osg-box">
                                                                <div class="input-group">
                                                                    <select id="selRevenueReport" class="form-control ">
                                                                        <option value="0" disabled selected>Select Revenue Report</option>
                                                                                <option value="7">Details of Revenue by Economic Segment</option>
                                                                                <option value="8">Summary of Total Revenue Based on Sector by Ind. Rev.</option>
                                                                                <option value="9">Revenue Details - Revenue Estimates</option>
                                                                    </Select>
                                                                    <span class="input-group-btn">
                                                                        <button class="btn btn-warning" onclick="RevenueReport()" type="button">
                                                                            <i class="fa fa-info-circle"></i>
                                                                        </button>
                                                                    </span>
                                                                    <!--<p class="wv-text--body report-description">See the Revenue related Reports here.</p>-->
                                                                </div>
                                                                <!-- /input-group -->

                                                            </div>
                                                        </div>
                                                        <div class="span6 col-xs-5">
                                                            <div style="color: rgb(28, 37, 43);margin-left: 10px;margin-top: 10px;font-size: 18px;font-weight: 900">Budget Estimates :</div>
                                                            <div class="osg-box">
                                                                <div class="input-group">
                                                                    <select id="selRecCapEstReport" class="form-control">
                                                                        <option value="0" disabled selected>Select Budget Report</option>
                                                                        <option value="5">Summary of Approved Recurrent and Capital Est.</option>
                                                                        <option value="10">Summary of Recurrent Estimates</option>
                                                                        <option value="21">Approved Capital Estimates - Project Details</option>
                                                                        <option value="24">Capital Budget Allocation to MDAs</option>
                                                                    </Select>
                                                                    <span class="input-group-btn">
                                                                        <button class="btn btn-warning" onclick="getReport('RecCapEstReport', 1)" type="button">
                                                                            <i class="fa fa-info-circle"></i>
                                                                        </button>
                                                                    </span>
                                                                </div>
                                                                <!-- /input-group -->
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row" style="margin-left: 0px;margin-right: 0px;">
                                                        <div class="span6 col-xs-5">
                                                            <div style="color: rgb(28, 37, 43);margin-left: 10px;margin-top: 10px;font-size: 18px;font-weight: 900">Allocations :</div>                                                            
                                                            <div class="osg-box">
                                                                <div class="input-group">
                                                                    <select id="selAllocationReport" class="form-control">
                                                                        <option value="0" disabled selected>Select Budget Report</option>
                                                                        <option value="1">Allocations to MDAs</option>
                                                                        <option value="2">Allocations to MDAs (Capital Estimates)</option>
                                                                        <option value="11">Grants to Parastatals / Tertiary Institutions</option>
                                                                        <option value="17">Allocations of Funds to MDAs</option>
                                                                        <option value="x" disabled="">=== Personnel Reports ===</option>
                                                                        <option value="40">Summary of Approved Personnel Cost</option>
                                                                        <option value="41">Personnel Cost Details</option>
                                                                        <option value="42">Summary of Personnel Cost</option>
                                                                    </Select>
                                                                    <span class="input-group-btn">
                                                                        <button class="btn btn-warning" onclick="getReport('Allocations', 1)" type="button">
                                                                            <i class="fa fa-info-circle"></i>
                                                                        </button>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="span6 col-xs-5">
                                                            <div style="color: rgb(28, 37, 43);margin-left: 10px;margin-top: 10px;font-size: 18px;font-weight: 900">Expenditures :</div>
                                                            <div class="osg-box">
                                                                <div class="input-group">
                                                                    <select id="selExpenditureReport" class="form-control" onchange="">
                                                                        <option value="0" disabled selected>Select Allocation Report</option>
                                                                        <option value="4">Over Head Cost</option>
                                                                        <option value="12">Recurrent Expenditure - Special Programmes</option>
                                                                        <option value="15">Recurrent Expenditure - Overhead Cost</option>
                                                                    </Select>
                                                                    <span class="input-group-btn">
                                                                        <button class="btn btn-warning" onclick="getReport('Expenditures', 1)" type="button">
                                                                            <i class="fa fa-info-circle"></i>
                                                                        </button>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div id="txtReportPad" class="table-responsive"></div>
                            </div>
                            <!--***************************************************Page Area END*********************************************************************-->
                    </div>
                    </section>
                </div>
                <!-- /.main-page -->
                <!-- /.right-sidebar -->
            </div>
            <!-- /.content-container -->
        </div>
        <!-- /.content-wrapper -->
        <!-- /.content-wrapper -->
        <!--Modal Dialogs-->
        <!--Budget Component Selection-->
        <div class="modal fade" id="AllocationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <div class="panel-title">Select Budget Heads</div>
                        <div style="float:right;  position: relative; top:-10px">
                            <a href="#"></a>
                        </div>
                    </div>
                    <div class="modal-body">
                        <h3 class="text-center"></h3>

                        <select class='js-states form-control' id='chkAllocation' multiple='multiple'></select>
                        <input id="_reporttype" value="" type="hidden">
                        <div class="modal-footer">
                            <!-- Button -->
                            <div class="col-sm-12 controls">
                                <a id="addpolicygrp" href="#" class="btn btn-success" onclick="ExecuteReport()">Generate Report</a>
                                <a id="btn-cancel" href="#" class="btn btn-primary" data-dismiss="modal">Cancel</a>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--End Add New Group Code Modal-->
        <!--Start of Title Edit Modal-->
        <div class="modal fade" id="TitleEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="panel-title">Edit Report Title</div>
                    </div>
                    <div class="modal-body">
                        <h3 class="text-center">Report Title</h3>
                        <textarea class="form-control" rows="5" id="txtTitle"></textarea>
                        <div class="modal-footer">
                            <!-- Button -->
                            <div class="col-sm-12 controls">
                                <a id="addpolicygrp" href="#" class="btn btn-success" onclick="UpdateTitle()">Save</a>
                                <a id="btn-cancel" href="#" class="btn btn-primary" data-dismiss="modal">Cancel</a>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--Start of Title Edit Modal-->

        <script>
            checkLogin();
            var _budgetyear;
            //Public vals
            var _budgetyear;
            var _sectorstring;
            var _subsectorstring;
            var dataarray = "";
            $(document).ready(function () {
                getBudgetYear();
                getSectors();
                getSubSectors();
                getBudgetTypes();
                getBudgetVersions();

                $("#chkAllocation").select2();
                $('#reptable').DataTable({
                    "paging": false,
                    "ordering": false,
                    "info": false
                });
                ActiveVersion = $("#cmbBudgetVersion").val(); 
            });
            function getBudgetYear() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SectorCeilingServlet",
                    data: {
                        option: "<%= Utility.OPTION_SELECT%>"
                    },
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: budgetYearReturnValues,
                    error: function () {
                        toastr["error"]("No record selected!", "Budget Year Selection Failed!!!");
                    }
                });
            }
            function budgetYearReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    var tmp = 0;

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
                                tmp = attrValue;
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                            if (attrName === "2") {
                                    _budgetyear = attrValue;
                            }
                        }
                    }
                    $('#budget_year').html(resp);
                }
            }
            function getSectors() {
                //                                        ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SectorServlet",
                    data: {
                        option: "<%= Utility.OPTION_SELECT_ALL%>"
                    },
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: sectorReturnValues,
                    error: function () {
                        //StopLoading();
                        toastr["error"]("Sector Selection Failed!", "No record selected!");
                    }
                });
            }
            function getSubSectors() {
                //var id = document.getElementById(arg).value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SubSectorServlet",
                    data: {
                        option: "<%= Utility.OPTION_SELECT_ALL%>"
                    },
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: subSectroReturnValues,
                    error: function () {
                        toastr["error"]("Sub Sector Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function subSectroReturnValues(data) {
                var tmp = {};

                if (data.length > 0) {
                    _subsectors = data;
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName == "5") {
                                tmp.code = attrValue;
                            }
                            if (attrName == "1") {
                                tmp.name = attrValue;
                            }
                        }
                        resp += "<option value='" + tmp.code + "'>" + tmp.name + "</option>";
                    }
                    $('#lstSubSectors').html(
                            "<select class='form-control custom_select select2-accessible' id='subsectors' tabindex='-1' ><option value='0' disabled selected>Select a Sub-sector</option>" +
                            resp + "</select>");
                    _subsectorstring =
                            "<select class='form-control custom_select select2-accessible' id='subsectors0' tabindex='-1' onchange='getReport(\"SubSectors\",1)'  ><option value='0' disabled selected>Select a Sub-sector</option>" +
                            resp + "</select>";
                }
            }
            function sectorReturnValues(data) {
                //                                        StopLoading();
                var txt = "";
                if (data.length > 0) {
                    var value = {};
                    var tmp = "";
                    var sec = "";
                    var resp = null;
                    //console.log(data);
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        var recId = 0;
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = " " + attrValue;
                            if (attrName == "5") {
                                value.id = attrValue;
                            }
                            if (attrName == "1") {
                                value.name = attrValue;
                            }
                        }
                        sec += "<option value=" + value.id + ">" + value.name + "</option>";
                        tmp =
                                "<button class='btn btn-default' onclick='DoSegmentReoprtItem(5, \"Sectors\" , valueid, 1 )' type='button'>valuename</button>&nbsp;&nbsp; ";
                        tmp = tmp.replace("valueid", value.id);
                        tmp = tmp.replace("valuename", value.name);
                        txt += tmp;
                    }
                }
                $('#lstSectors').html(
                        "<select style='margin-bottom:10px;' class='form-control custom_select select2-accessible' id='sectors' tabindex='-1' ><option value='0' disabled selected>Select a Sector</option>" +
                        sec + "</select>");
                $('#SectorItems').html(txt);
                _sectorstring =
                        "<select style='margin-bottom:10px;' class='form-control custom_select select2-accessible' id='sectors0' tabindex='-1' onchange='getReport(\"Sectors\",1)' ><option value='0' disabled selected>Select a Sector</option>" +
                        sec + "</select>";
            }
            function getBudgetTypes() {
                var txt = "";
                var tmp = "";
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/BudgetTypeComponentsServlet",
                    data: {
                        option: "<%= Utility.OPTION_SELECT_BY_ID%>"
                    },
                    dataType: "json",
                    cache: false,
                    success: function (response) {
                        //console.log(response)
                        $.each(response, function (index, element) {
                            if (tmp != element[1])
                                txt += "<option value= '" + element[6] + "'>" + element[1] +
                                        "</option>";
                            tmp = element[1]
                        });
                        document.getElementById("chkAllocation").innerHTML = txt;
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        //console.log(textStatus + " " + errorThrown);
                    }
                });
                
            }
            var BudgetVersionsTable = "";
            function getBudgetVersions() {
                if (BudgetVersionsTable.length > 0) {
                    var data;
                    var optt;
                    data = BudgetVersionsTable;
                    for (i = 0; i < data.length; i++) {
                        var itm = data[i];
                        optt += "<option value='" + itm[0] + "'>" + itm[2] + "</option>";
                    }
                    $('#cmbBudgetVersion').html(optt);
                    optt="<option value='-1' disabled selected>Select a Budget Version</option>"+optt;
                    $('#ver1').html(optt);
                    $('#ver2').html(optt);
                    $('#ver3').html(optt);
                    $('#ver4').html(optt);
                    $('#ver5').html(optt);

                    return;
                }
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/ReportServlet",
                    data: {
                        option: "getBudgetVersions", budget_year: _budgetyear, 
                        ActiveVersion: ActiveVersion
                    },
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        //alert(data);
                        BudgetVersionsTable = data;
                        var optt;
                        for (i = 0; i < data.length; i++) {
                            var itm = data[i];
                            optt += "<option value='" + itm[0] + "'>" + itm[2] + "</option>";
                        }
                        $('#cmbBudgetVersion').html(optt);
                        $("#cmbBudgetVersion option[value=5]").attr('selected', 'selected'); //Update 5 with GetApprovedBudet()
                        optt="<option value='-1' disabled selected>Select a Budget Version</option>"+optt;
                        $('#ver1').html(optt);
                        $('#ver2').html(optt);
                        $('#ver3').html(optt);
                        $('#ver4').html(optt);
                        $('#ver5').html(optt);
                    },
                    error: function (a, b, c) {
                        console.log(a + b + c);
                        toastr["error"]("No record selected!", "Budget Version Selection Failed!!!");
                    }
                });
            }
            function getReport(itm, ReportType) {
                var ReportHead;
                var ReportNumber;
                var segmentName;
                if (itm == "Sectors") {
                    ReportNumber = 1
                    ReportHead = $('#sectors0').val();
                    segmentName = $("#sectors0 option:selected").text();
                    DoSegmentReoprtItem(ReportNumber, itm, ReportHead, segmentName);
                } else if (itm == "SubSectors") {
                    ReportNumber = 2
                    ReportHead = $('#subsectors0').val();
                    segmentName = $("#subsectors0 option:selected").text();
                    DoSegmentReoprtItem(ReportNumber, itm, ReportHead, segmentName);
                } else if (itm == "Segments") {
                    ReportNumber = 27
                    ReportHead = $('#SegmentItem').val();
                    segmentName = $("#selSegments option:selected").text();
                    ReportHead = ReportHead + "," + $("#SegmentItem option:selected").text();
                    dataarray = $("#SegmentItem option:selected").text();
                    DoSegmentReoprtItem(ReportNumber, itm, ReportHead, segmentName);
                } else if (itm == "Independent Revenue") {
                    ReportNumber = ReportType;
                    //Get Definition of Independent Revenue
                } else if (itm == "Allocations") {
                    ReportNumber = $('#selAllocationReport').val();
                    if (ReportNumber == '41' || ReportNumber == '40' || ReportNumber == '42') {
                        DoSegmentReoprtItem(ReportNumber, 'Personnel', '', '');
                        return;
                    }
                } else if (itm == "Revenue Details") {
                    ReportNumber = $('#selRevenueReport').val();
                } else if (itm == "Recurrent Estimates") {
                    ReportNumber = $('#selRecCapEstReport').val();
                }

                var val0 = 0;
                if (itm == "Allocations" || itm == "RecCapEstReport" || itm == "Independent Revenue" || itm ==
                        "Revenue Details" || itm == "CRF" || itm == "Expenditures") {
                    if (itm == "Allocations") {
                        val0 = $("#selAllocationReport").val();
                        if (val0 == '14') {
                            itm = "Grants";
                        }
                    } else if (itm == "RecCapEstReport") {
                        val0 = $("#selRecCapEstReport").val();
                        if (val0 == '16') {
                            ReportNumber = val0;
                            DoSegmentReoprtItem(ReportNumber, "Sectoral Summary", ReportHead, segmentName);
                        } else if (val0 == '20') {
                            ReportNumber = val0;
                            segmentName = $('#lstMdas').val();
                            ReportHead = $("#lstMdas option:selected").text();
                            DoSegmentReoprtItem(ReportNumber, "Capital Estimates", ReportHead, segmentName);
                        } else if (val0 == '21') {
                            ReportNumber = val0;
                            DoSegmentReoprtItem(ReportNumber, "Capital Estimates", ReportHead, 1);
                        } else if (val0 == '22') {
                            ReportNumber = val0;
                            DoSegmentReoprtItem(ReportNumber, "Capital Estimates", ReportHead, 1);
                            return;
                        } else if (val0 == '18' || val0 == '19' || val0 == '26' || val0 == '28') {
                            ReportNumber = val0;
                            DoSegmentReoprtItem(ReportNumber, "Budget Summary", ReportHead, 1);
                        } else if (val0 == '25') {
                            ReportNumber = val0;
                            DoSegmentReoprtItem(ReportNumber, "Budget Summary", ReportHead, 1);
                        } else if (val0 == '24') {
                            ReportNumber = val0;
                            DoSegmentReoprtItem(ReportNumber, "Budget Summary", ReportHead, 1);
                        } else if (val0 == '23') {
                            ReportNumber = 23;
                            ReportHead = "ALL";
                            segmentName = "Programme Segment";
                            DoSegmentReoprtItem(ReportNumber, "Segments", ReportHead, segmentName);
                        }
                    } else if (itm == "Independent Revenue") {
                        val0 = $("#selRevenueReport").val();
                    } else if (itm == "Revenue Details") {
                        val0 = $("#selRevenueReport").val();
                    } else if (itm == "Recurrent Estimates") {
                        val0 = 10; // $("#selRevenueReport").val();
                    } else if (itm == "CRF") {
                        val0 = 13;
                    } else if (itm == "Expenditures") {
                        val0 = $("#selExpenditureReport").val();
                        if (val0 == '33') {
                            ReportNumber = val0;
                            ReportHead = "14";
                            segmentName = "Economic Segment";
                            //DoSegmentReoprtItem(ReportNumber, "Segments", ReportHead, segmentName);                                                                                                
                        }
                    }
                    //Get Cookie if available
                    //chkAllocation
                    //

                    var AllRep1;
                    var _AllRep1;

                    _AllRep1 = readCookie("AllRep" + val0);
                    if (_AllRep1 == null ||_AllRep1 == '') {
                        if (val0 == 1) {
                            AllRep1 = "21010101,2202,2210";
                            createCookie("AllRep1", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 2) {
                            AllRep1 = "21010101";
                            createCookie("AllRep2", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 5) {
                            AllRep1 = "21010101,2202,2210,14";
                            createCookie("AllRep5", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 8) {
                            AllRep1 = "12";
                            createCookie("AllRep8", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 9) {
                            AllRep1 = "11010101,11010201,12,11010301,13,4102,11010106,14,4";
                            createCookie("AllRep9", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 10) {
                            AllRep1 = "21010101,2202,2210,13,220401,21010103";
                            createCookie("AllRep10", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 11) {
                            AllRep1 = "220401";
                            createCookie("AllRep11", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 13) {
                            AllRep1 =
                                    "21010103,21020202,22010101,22010102,22010104,22060102,22060202,22070104,22070105,22070106";
                            createCookie("AllRep13", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 14) {
                            AllRep1 = "22040105";
                            createCookie("AllRep14", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 12) {
                            AllRep1 = "2210";
                            createCookie("AllRep12", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 15) {
                            AllRep1 = "2202";
                            createCookie("AllRep15", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 17) {
                            AllRep1 = "4102,21010103,21010101,2202,2210,220401,14";
                            createCookie("AllRep17", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        } else if (val0 == 33) {
                            AllRep1 = "14";
                            createCookie("AllRep33", AllRep1, 1000);
                            _AllRep1 = AllRep1;
                        }

                    } else {
                        AllRep1 = _AllRep1;
                    }
                    dataarray = AllRep1.split(",");
                    $("#chkAllocation").val(dataarray);
                    $("#chkAllocation").trigger("change");

                    //Load the Modal   
                    $('#_reporttype').val(val0);
                    $('#AllocationModal').modal({
                        backdrop: 'static',
                        keyboard: false
                    });
                    // $('#AllocationModal').modal('show' );
                }
            }
            function ExecuteReport() {
                var ReportHead;
                var segmentName;
                var itm = $('#_reporttype').val();
                var AllRep1 = ""
                if (itm == 1 || itm == 2 || itm == 5 || itm == 8 || itm == 9 || itm == 10 || itm == 11 || itm ==
                        13 || itm == 14 || itm == 12 || itm == 15 || itm == 17 || itm == 33) {
                    AllRep1 = $("#chkAllocation").val();
                    var tmp0 = "AllRep" + itm;
                    createCookie(tmp0, AllRep1.join(), 1000);
                }

                $('#AllocationModal').modal('toggle'); //Hide Dialog
                if (itm == 1 || itm == 2 || itm == 17) {
                    if (itm == 1)
                        itm = 3; // $('#selAllocationReport').val();
                    else if (itm == 2)
                        itm = 4;
                    segmentName = "Allocation";
                } else if (itm == 5) {
                    ReportHead = $('#selRecCapEstReport').val();
                    segmentName = "Allocation";
                } else if (itm == 8) {
                    segmentName = "Independent Revenue";
                } else if (itm == 9) {
                    segmentName = "Revenue Details";
                } else if (itm == 10) {
                    segmentName = "Recurrent Estimates";
                } else if (itm == 11 || itm == 14) {
                    segmentName = "Grants";
                } else if (itm == 13) {
                    segmentName = "CRF";
                } else if (itm == 12 || itm == 15) {
                    segmentName = "Expenditures";
                } else if (itm == 33) {
                    segmentName = "Segments";
                }


                // var typ = $('#_reporttype').val();
                dataarray = AllRep1;
                ReportHead = dataarray;
                DoSegmentReoprtItem(itm, segmentName, ReportHead.toString(), segmentName);
            }
                 function RevenueReport() {

                        var ReportHead;
                        var ReportNumber;
                        var segmentName;
                        ReportNumber = $('#selRevenueReport').val();
                        if (ReportNumber == '6') {
                            ReportHead = "1";
                            segmentName = "Economic Segment";
                            DoSegmentReoprtItem(ReportNumber, "Revenue", ReportHead, segmentName);

                        } else if (ReportNumber == '7') {
                            ReportHead = "ALL";
                            segmentName = "Economic Segment";
                            DoSegmentReoprtItem(ReportNumber, "Revenue", ReportHead, segmentName);
                        } else if (ReportNumber == '8') {
                            idx = $('#selRevenueReport').val();
                            if (idx == "8") {
                                segment = "Independent Revenue";
                                getReport("Independent Revenue", ReportNumber);
                                return;
                                //alert("After Return");
                            }
                        } else if (ReportNumber == '9') {
                            segment = "Revenue Details";
                            getReport(segment, ReportNumber);
                            return;
                        } else if (ReportNumber == '13') {
                            segment = "CRF";
                            getReport(segment, ReportNumber);
                            return;
                        }

                    }
                   var ActiveVersion; 
            function DoSegmentReoprtItem(ReportNumber, ReportItem, ReportHead, ItemName) {
                //ReportType 
                // 1 =  General
                // 2 = Summary
                //ReportNumber  Sequential Numbering of Reports
                //ReportItem    Report category Sector / SubSector
                //ReportHead    Codes
                var idx;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MDAReportsServlet",
                    data: {
                        ReportNumber: ReportNumber,
                        ReportItem: ReportItem,
                        ItemName: ItemName,
                        budget_year: _budgetyear,
                        ReportHead: ReportHead,
                        ActiveVersion: ActiveVersion
                    },
                    dataType: "html",
                    cache: false,
                    async: false,
                    success: function (data) {
                        $('#txtReportPad').html(data);

                        $('#reptable').DataTable({
                            ordering: false,
                            paging: false,
                            info: true,
                            dom: 'Bfrtip',
                            buttons: ['csv', 'excel', 'pdf', 'print', {text: '<i class="fa fa-cogs fa-1x" aria-hidden="true"></i>Edit',
                                    action: function (e, dt, node, config) {
                                        EditTitle();
                                    }}, {text: '<i class="fa fa-clipboard fa-1x" aria-hidden="true"></i>Copy',
                                    action: function (e, dt, node, config) {
                                        CopyTable(document.getElementById('reptable'));
                                    }
                                }]
                        });
                        $('.dt-button').addClass('btn btn-warning').removeClass('dt-button');
                        $('#txtReportPad')[0].childNodes[0].data = ""

                    },
                    error: function (a, b, c) {
                        //console.log("a: " + a + " b: " + b + " c: " + c);
                        toastr["error"]("No record selected!", "Report Generation Failed!!!");
                        StopLoading();
                    }
                });
            }
            ;

        </script>       
    </body>    
</html>
