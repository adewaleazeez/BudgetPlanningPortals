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
            @media (min-width: 992px) {
                .modal-lg {
                    width: 80%;
                    height: 80%; /* control height here */
                }
            }
            a.ex1:hover, a.ex1:active {color: orange;}
        </style>
    </head>

    <body class="top-navbar-fixed">
        <div class="main-wrapper">
            <!-- ========== TOP NAVBAR ========== -->
            <nav class="navbar top-navbar bg-white box-shadow">
                <div class="container-fluid">
                    <div class="row">
                        <div class="navbar-header no-padding">
                            <a class="navbar-brand" href="index.html">
                                <img src="images/logo-dark.png" alt="Options - Admin Template" class="logo">
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
                                                <img src='<%= session.getAttribute("userdp")%>' alt="User" class="img-circle profile-img">
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
                            <div class="container-fluid">
                                <!--***************************************************Page Area START*********************************************************************-->
                                <style>
                                    .span6 {
                                        float: left;
                                        margin-left: 20px;
                                    }

                                    .osg-box {
                                        display: block;
                                        padding: 1em;
                                        border-radius: 4px;
                                        border: 1px solid #d4dde3;
                                        background-color: #fff;
                                        margin-bottom: 0;
                                        margin-top: 5px
                                    }
                                </style>
                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <label for="budget_year">Budget&nbsp;Year</label>
                                            <select class='js-states form-control custom_select' id='budget_year' disabled></select>
                                        </div>
                                        <div class="col-md-3">
                                            <label>Budget Version</label>
                                            <select class="form-control" id="cmbBudgetVersion" onchange="ChangeBudgetVersion(this.value)"></select>
                                        </div>
                                        <div class="col-md-3"> 
                                            <label>Base Currency</label>
                                            <select class="form-control" id="cmbCurrency" onchange="ChangeCurrency(this.value)"></select>
                                        </div>
                                        <div class="col-md-3">
                                            <label>Budget Types</label>
                                            <select class="form-control" id="cmbBudgetType" onchange="ChangeBudgetType(this.value)"></select>
                                        </div>                                                
                                    </div>
                                    <!--                                    <hr>
                                                                        <br style="clear: both;" />-->
                                    <!-- Nav tabs -->
                                    <ul class="nav nav-tabs  border-primary" role="tablist" style="margin-top: 20px;">
                                        <li role="presentation" class="active">
                                            <a class="" href="#SegmentTab" aria-controls="SegmentTab" role="tab" data-toggle="tab"
                                               aria-expanded="true">Segment Reports</a>
                                        </li>
                                        <li role="presentation" class="">
                                            <a class="" href="#CustomTab" aria-controls="SectorsTab" role="tab" data-toggle="tab"
                                               aria-expanded="false">Custom Reports</a>
                                        </li>
                                        <li role="presentation" class="">
                                            <a class="" href="#BudgetBookTemplateTab" aria-controls="BudgetBookTemplateTab"
                                               role="tab" data-toggle="tab" aria-expanded="false">Budget Book Templates</a>
                                        </li>
                                        <li role="presentation" class="">
                                            <a class="" href="#BudgetVersionsTab" aria-controls="BudgetVersionsTab"
                                               role="tab" data-toggle="tab" aria-expanded="false">Budget Versions Report</a>
                                        </li>                                        
                                        <li role="presentation" class="">
                                            <a class="" href="#BudgetDocumentTab" aria-controls="BudgetDocumentTab"
                                               role="tab" data-toggle="tab" aria-expanded="false" onclick="GetBudgetReports();">Budget Report Editor</a>
                                        </li>                                       
                                        <li role="presentation" class="">
                                            <a class="" href="#BudgetBookReportTab" aria-controls="BudgetBookReportTab"
                                               role="tab" data-toggle="tab" aria-expanded="false" onclick="getBudgetVersions();">Budget Book Reports</a>
                                        </li>                             
                                    </ul>

                                    <!-- Tab panes -->
                                    <div class="tab-content bg-white p-15">
                                        <div role="tabpanel" class="tab-pane active" id="SegmentTab" style="background-color: #f0f0f1">
                                            <div class="row">
                                                <div class="col-md-12" style="display: block;float:left;">
                                                    <div class="row" style="margin-left: 0px;margin-right: 0px;">
                                                        <div class="span6 col-xs-5">
                                                            <div style="color: rgb(28, 37, 43);margin-left: 10px;margin-top: 10px;font-size: 18px;font-weight: 900">Generic Reports:</div>
                                                            <div class="osg-box">
                                                                <div class="input-group">
                                                                    <div id="Segmentsdiv"></div>
                                                                </div>
                                                                <!-- /input-group -->
                                                            </div>
                                                        </div>
                                                        <div class="span6 col-xs-5">
                                                            <div style="color: rgb(28, 37, 43);margin-left: 10px;margin-top: 10px;font-size: 18px;font-weight: 900">Select Sub Options:</div>
                                                            <div class="osg-box">
                                                                <div class="input-group" id="SegmentItems"></div>
                                                                <!-- /input-group -->
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                        </div>
                                        <div role="tabpanel" class="tab-pane" id="CustomTab" style="background-color: #f0f0f1">
                                            <div class="row">
                                                <div class="col-md-12" style="display: block;float:left;">
                                                    <div class="row" style="margin-left: 0px;margin-right: 0px;">
                                                        <div class="span6 col-xs-11">
                                                            <div style="color: rgb(28, 37, 43);margin-left: 10px;margin-top: 10px;font-size: 18px;font-weight: 900">SQL :</div>
                                                            <div class="osg-box">
                                                                <textarea  id="txtqueryPad" class="form-control" rows="5" style="resize:none" placeholder="Enter Valid SQL Query Here" required></textarea>
                                                                <button type="button" class="btn btn-success" onclick="doQuery()" >Run Report</button>
                                                                <!-- /input-group -->
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>                                                    
                                            <hr>
                                        </div>
                                        <div role="tabpanel" class="tab-pane" id="BudgetBookTemplateTab" style="background-color: #f0f0f1">
                                            <div class="row" >
                                                <!--                                                <div class="col-md-12" style="display:hidden;float:left; margin-top: 10px;">
                                                                                                    <div class="col-md-4">
                                                                                                        <label>Budget Version</label>
                                                                                                        <select id="cmbBudgetVersion0" onchange="ChangeBudgetVersion(this.value)"></select>
                                                                                                    </div>
                                                                                                    <div class="col-md-4">
                                                                                                        <label>Base Currency</label>
                                                                                                        <select id="cmbCurrency0" onchange="ChangeCurrency(this.value)"></select>
                                                                                                    </div>   
                                                                                                    <div class="col-md-4">
                                                                                                        <label>Budget Types</label>
                                                                                                        <select id="cmbBudgetType0" onchange="ChangeBudgetType(this.value)"></select>
                                                                                                    </div>                                                
                                                                                                </div>-->
                                                <div class="col-md-12" style="display: block;float:left;">
                                                    <div class="row" style="margin-left: 0px;margin-right: 0px;">
                                                        <div class="span6 col-xs-5">
                                                            <div style="color: rgb(28, 37, 43);margin-left: 10px;margin-top: 10px;font-size: 18px;font-weight: 900">Revenue Reports :</div>
                                                            <div class="osg-box">
                                                                <div class="input-group">
                                                                    <select id="selRevenueReport" class="form-control " style="width:360px!important">
                                                                        <option value="0" disabled selected>Select Revenue Report</option>
                                                                        <option value="6">Summary of Revenue by Economic Segment</option>
                                                                        <option value="7">Details of Revenue by Economic Segment</option>
                                                                        <option value="8">Summary of Total Revenue Based on Sector by Ind. Rev.</option>
                                                                        <option value="9">Revenue Details - Revenue Estimates</option>
                                                                        <option value="13">Consolidated Revenue Funds Charges</option>
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
                                                                        <option value="16">Sectoral Summary</option>                                                                                
                                                                        <option value="21">Approved Capital Estimates - Project Details</option>
                                                                        <option value="18">Consolidated Budget Summary(Master Budget)</option>
                                                                        <option value="19">Consolidated Budget Summary Based on Sectors</option>
                                                                        <option value="22">Summary of Total Capital Budget by Sectors</option>
                                                                        <option value="26">Summary of Capital Budget By Functions</option>
                                                                        <option value="28">Summary of Capital Budget By Policy</option>
                                                                        <option value="25">Summary of Capital Budget By Programme</option>
                                                                        <option value="24">Capital Budget Allocation to MDAs</option>
                                                                        <option value="23">Details of capital Project Budget under Programme</option>
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
                                                                    <select id="selAllocationReport" class="form-control" style="width:360px!important">
                                                                        <option value="0" disabled selected>Select Budget Report</option>
                                                                        <option value="1">Allocations to MDAs</option>
                                                                        <option value="2">Allocations to MDAs (Capital Estimates)</option>
                                                                        <option value="11">Grants to Parastatals / Tertiary Institutions</option>
                                                                        <option value="14">Grants and Loans</option>
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
                                                                    <select id="selExpenditureReport" class="form-control" onchange="" style="width:360px!important">
                                                                        <option value="0" disabled selected>Select Allocation Report</option>
                                                                        <option value="4">Over Head Cost by MDAs</option>
                                                                        <option value="12">Recurrent Expenditure - Special Programmes by MDAs</option>
                                                                        <option value="15">Recurrent Expenditure - Overhead Cost by MDAs</option>
                                                                        <option value="33">Capital Expenditure by Economic Segment</option>
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
                                        </div> <div role="tabpanel" class="tab-pane" id="BudgetDocumentTab" style="background-color: #f0f0f1">
                                            <div id="ReportsList" class="table-responsive" style="width: 800px;"></div>
                                            <button class="btn btn-danger" onclick="NewReport()" type="button" > <span class="fa fa-plus-circle">Add New Report</span></button>
                                            <div class="row" id="ReportEditor">
                                                <div class="col-md-12" style="display: block;float:left; margin-top:10px;margin-left: 10px; ">                                                    
                                                    <div class="container">
                                                        <button class="btn btn-warning" type="button" onclick="GetBudgetReports();"><span class="fa fa-arrow-left">List Documents</span></button>
                                                        <button class="btn btn-success" type="button" onclick="SaveBudgetDocument()"><span class="fa fa-save"> Save Document</span></button>
                                                        <label>Enter Document Title</label>
                                                        <input type="text" id="budgetDocument" style="width:90%">
                                                        <label>Enter formated text here, you can paste content from Word processors as well</label>
                                                        <textarea class="form-control" rows="50" id="txtBudgetreport" style="width:80%"></textarea>                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>   
                                        <div role="tabpanel" class="tab-pane" id="BudgetBookReportTab" style="background-color: #f0f0f1">
                                            <br>
                                            <div class="row">
                                                <div class="col-md-12" style="display: block;float:left; margin-top:10px;margin-left: 10px; ">
                                                    <button type="button" class="btn btn-success" onclick="EditReport()">Add a new Report</button>
                                                    <div class="container">
                                                        <button class="btn btn-warning" type="button" onclick="AddtoBudgetBook()"><span class="fa fa-angle-double-right"></span></button>
                                                        <div id="AvailableReports">                                                                    

                                                        </div> 
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                        </div>
                                        <div role="tabpanel" class="tab-pane" id="BudgetVersionsTab" style="background-color: #f0f0f1">
                                            <table>
                                                <tr><td>Version 1</td><td><select id="ver1" class="form-control"></select> </td></tr>
                                                <tr><td>Version 2</td><td><select id="ver2" class="form-control"></select> </td></tr>
                                                <tr><td>Version 3</td><td><select id="ver3" class="form-control"></select> </td></tr>
                                                <tr><td>Version 4</td><td><select id="ver4" class="form-control"></select> </td></tr>
                                                <tr><td>Version 5</td><td><select id="ver5" class="form-control"></select> </td></tr>
                                            </table>
                                            <button class="btn btn-success" onclick="getBudgetVersionsReport();" type="button">Compare Budget Versions</button>
                                            <div id="BudgetVersionsReport"></div>
                                        </div>                                                                                
                                        <div role="tabpanel" class="tab-pane" id="BudgetDocumentTab" style="background-color: #f0f0f1">
                                            <div id="ReportsList" class="table-responsive" style="width: 800px;"></div>
                                            <button class="btn btn-danger" onclick="NewReport()" type="button" > <span class="fa fa-plus-circle">Add New Report</span></button>
                                            <div class="row" id="ReportEditor">
                                                <div class="col-md-12" style="display: block;float:left; margin-top:10px;margin-left: 10px; ">                                                    
                                                    <div class="container">
                                                        <button class="btn btn-warning" type="button" onclick="GetBudgetReports();"><span class="fa fa-arrow-left">List Documents</span></button>
                                                        <button class="btn btn-success" type="button" onclick="SaveBudgetDocument()"><span class="fa fa-save"> Save Document</span></button>
                                                        <label>Enter Document Title</label>
                                                        <input type="text" id="budgetDocument" style="width:90%">
                                                        <label>Enter formated text here, you can paste content from Word processors as well</label>
                                                        <textarea class="form-control" rows="50" id="txtBudgetreport" style="width:80%"></textarea>                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>                                        
                                    </div>
                                </div>
                                <hr>
                                <!--                                <button onclick="pdfthis()" type="button">Print out</button>-->

                                <div id="txtReportPad" class="table-responsive"></div>
                            </div>
                            <!--***************************************************Page Area END*********************************************************************-->
                    </div>
                </div>
                <!-- /.main-page -->
                <!-- /.right-sidebar -->
            </div>
            <!-- /.content-container -->
        </div>
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

        <!--Start of Report Edit Modal-->

        <div class="modal fade modal-dialog modal-" id="ReportModal" tabindex="-1" role="dialog" aria-labelledby="ReportModal" >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="panel-title">Edit Report Title</div>
                    </div>
                    <div class="modal-body">
                        <textarea class="form-control" rows="30" id="txtTitle1"></textarea>
                        <div class="modal-footer">
                            <div class="col-sm-12 controls">
                                <a id="addpolicygrp" href="#" class="btn btn-success" onclick="EditReport()">Save</a>
                                <a id="btn-cancel" href="#" class="btn btn-primary" data-dismiss="modal">Cancel</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

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
        <script src="js/production-chart.js"></script>
        <script src="js/traffic-chart.js"></script>
        <script src="js/task-list.js"></script>
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

        <!--        HTML 2 PDF-->

        <script src="js/es6-promise.js"></script>
        <script src="js/jspdf.min.js"></script>
        <script src="js/html2canvas.min.js"></script>
        <script src="js/html2pdf.min.js"></script>
        <script src="js/html2pdf.bundle.min.js"></script>
        <!--        HTML 2 PDF-->
        <script>
                    checkLogin();
                    CKEDITOR.replace('txtBudgetreport');
                    CKEDITOR.config.width = "90%"
                    CKEDITOR.config.height = "640";
                    $(document).ready(function () {
                        //$('#txtReportPad').hide();
                        LoadReportTemplates();
                        getBudgetYear();
                        $("#chkAllocation").select2();
                        $('#Segmentsdiv').html(
                                "<select id='selSegments' class='form-control' onchange=' getSegmentItems()'><option value='0' disabled selected>Select a Segment</option><option value='1'>Administrative Segment</option><option value='2'>Economic Segment</option><option value='3'>Functional Segment</option><option value='4'>Fund Segment</option><option value='5'>Geographic Segment</option><option value='6'>Programme Segment</option></select>"
                                );
                        $('#reptable').DataTable({
                            "paging": false,
                            "ordering": false,
                            "info": false
                        });
                        ActiveCurrency = $("#cmbCurrency").val();
                        ActiveVersion = $("#cmbBudgetVersion").val();
                        ActiveBudgetType = $("#cmbBudgetType").val();
                    });
                    //Public vals
                    var _budgetyear;
                    var _sectorstring;
                    var _subsectorstring;
                    var dataarray = "";
                    var ActiveCurrency;
                    var ActiveVersion;
                    var ActiveBudgetType;
                    function PrintContent() {

                        $('txtReportPad').printElement();
                    }
                    function CopyTable(el) {
                        var body = document.body, range, sel;
                        if (document.createRange && window.getSelection) {
                            range = document.createRange();
                            sel = window.getSelection();
                            sel.removeAllRanges();
                            try {
                                range.selectNodeContents(el);
                                sel.addRange(range);
                            } catch (e) {
                                range.selectNode(el);
                                sel.addRange(range);
                            }
                        } else if (body.createTextRange) {
                            range = body.createTextRange();
                            range.moveToElementText(el);
                            range.select();
                            range.execCommand("Copy");
                        }
                        document.execCommand("Copy");
                    }
                    function EditTitle() {
                        var title = $('#reptitle').html();
                        $("textarea#txtTitle").val(title);
                        CKEDITOR.replace('txtTitle');
                        $('#TitleEditModal').modal({
                            backdrop: 'static',
                            keyboard: false
                        });
                    }
                    function UpdateTitle() {

                        var edt = CKEDITOR.instances["txtTitle"];
                        var title = edt.getData(); //($("textarea#txtTitle").val());
                        $('#reptitle').html(title);
                        //CKEDITOR.instances['reptitle'].my_editor.getData()
                        $('#TitleEditModal').modal('toggle');
                    }
                    function getReport(itm, ReportType) {
                        //var budget_type = $("#budgettype0").val();
                        var ReportHead;
                        var ReportNumber;
                        var segmentName;
                        if (itm == "Sectors") {
                            ReportNumber = 1;
                            ReportHead = $('#sectors0').val();
                            segmentName = $("#sectors0 option:selected").text();
                            DoSegmentReoprtItem(ReportNumber, itm, ReportHead, segmentName);
                        } else if (itm == "SubSectors") {
                            ReportNumber = 2;
                            ReportHead = $('#subsectors0').val();
                            segmentName = $("#subsectors0 option:selected").text();
                            DoSegmentReoprtItem(ReportNumber, itm, ReportHead, segmentName);
                        } else if (itm == "Segments") {
                            ReportNumber = 27;
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
                            if (_AllRep1 == null) {
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
                    function test() {
                        var k = "*Recipts,11010101,11010201,12,13,14,*Expenditure,21010103,22060202,22060102,*Recurrent Non-Debt,21010101,21010103,22010104,2202,22040110,22040105,2210,22070104,*Sectors,01,02,03,04,05";
                        DoSegmentReoprtItem(1, 'ReportItem', k, 'ItemName');
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
                                // GetBudgetReports();
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                //console.log(textStatus + " " + errorThrown);
                            }
                        });
                        getBudgetVersions();
                    }
                    function createCookie(name, value, days) {
                        var expires;
                        if (days) {
                            var date = new Date();
                            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                            expires = "; expires=" + date.toGMTString();
                        } else {
                            expires = "";
                        }
                        document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + expires +
                                "; path=/";
                    }
                    function readCookie(name) {
                        var nameEQ = encodeURIComponent(name) + "=";
                        var ca = document.cookie.split(';');
                        for (var i = 0; i < ca.length; i++) {
                            var c = ca[i];
                            while (c.charAt(0) == ' ')
                                c = c.substring(1, c.length);
                            if (c.indexOf(nameEQ) == 0)
                                return decodeURIComponent(c.substring(nameEQ.length, c.length));
                        }
                        return null;
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
                    function DoSegmentReoprtItem(ReportNumber, ReportItem, ReportHead, ItemName) {
                        //ReportType 
                        // 1 =  General
                        // 2 = Summary
                        //ReportNumber  Sequential Numbering of Reports
                        //ReportItem    Report category Sector / SubSector
                        //ReportHead    Codes
                        //ShowLoading();
                        //
                        //setTimeout('function(){int i=0;}', 3000);
                        var budget_type = $("#budgettype0").val();
                        var idx;
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/AdminReportsServlet",
                            data: {
                                ReportNumber: ReportNumber,
                                ReportItem: ReportItem,
                                ItemName: ItemName,
                                budget_year: _budgetyear,
                                ReportHead: ReportHead,
                                budget_type: budget_type,
                                ActiveCurrency: ActiveCurrency,
                                ActiveVersion: ActiveVersion,
                                ActiveBudgetType: ActiveBudgetType
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
                                $('#txtReportPad')[0].childNodes[0].data = "";
                            },
                            error: function (a, b, c) {
                                //console.log("a: " + a + " b: " + b + " c: " + c);
                                toastr["error"]("No record selected!", "Report Generation Failed!!!");
                                StopLoading();
                            }
                        });
                        setTimeout(function () {
                            waitingDialog.hide();
                        }, 2000);
                        StopLoading();
                    }
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
                        //alert(data);

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
                                        resp += attrValue + "</option>";
                                        _budgetyear = attrValue;
                                    }
                                }
                            }

                        }
                        $('#budget_year').html(resp);
                        getSectors();
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
                                option: "<%= Utility.OPTION_SELECT%>"
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
                        getBudgetTypes();
                    }
                    function getSegmentItems() {
                        //check segment
                        var segment = $("#selSegments").val();
                        if (segment == "0" || segment == null) {
                            swal("Please select a Segment first!");
                            //toastr["error"]("", "Budget Report Failed!!!");
                            return;
                        }
                        var obj = $("#selSegments").val(); //$("#SegmentItem option:selected").text();
                        switch (obj) {
                            case '1': //"Administrative Segment":
                                getAdministrativeSegmentHeader1();
                                break;
                            case '2': //"Economic Segment":
                                getEconomicSegmentHeader1();
                                break;
                            case '3': //"Functional Segment":
                                getFunctionalSegmentHeader1()
                                break;
                            case '4': //"Fund Segment":
                                getFundSegmentHeader1();
                                break;
                            case '5': //"Geographic Segment":
                                getGeographicSegmentHeader1();
                                break;
                            case '6': //"Programme Segment":
                                getProgrammeSegmentHeader1();
                                break;
                            default:
                            {
                                toastr["error"]("Invalid Segment!", "Budget Report Failed!!!");
                                return;
                            }
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
                        getSubSectors();
                    }
                    function getMDAS(arg) {
                        var id = document.getElementById(arg).value;
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/MdaServlet",
                            data: {
                                option: "<%= Utility.OPTION_SELECT_BY_ID%>",
                                sub_sector_id: id
                            },
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: mdaReturnValues,
                            error: function () {
                                toastr["error"]("Mda Selection Failed!", "No record selected!");
                            }
                        });
                    }
                    function mdaReturnValues(data) {
                        if (data.length > 0) {
                            var resp =
                                    "<table id='main-table' class='table table-clean table-striped' border='0'><thead><tr><td><b>MDAs</b></td><td><b>MDA&nbsp;Types</b></td><td><b>MDA&nbsp;Weights</b></td><td><b>Admin&nbsp;Segments</b></td><td></td></tr></thead><tbody>";
                            for (var i = 0; i < data.length; i++) {
                                var obj = data[i];
                                var rec_id = "";
                                for (var key in obj) {
                                    var attrName = key;
                                    var attrValue = obj[key];
                                    attrValue = " " + attrValue;
                                    if (attrName == "0") {
                                        rec_id = attrValue;
                                    }
                                    if (attrName == "1") {
                                        resp += "<tr><td class='line-height-70 w-25'><small><b>" + attrValue +
                                                "</b></small></td>";
                                    }
                                    if (attrName == "2") {
                                        resp += "<td class='line-height-30 w-15'><small><b>" + attrValue +
                                                "</b></small></td>";
                                    }
                                    if (attrName == "3") {
                                        resp += "<td class='line-height-30 w-15' align='center'><small><b>" + attrValue +
                                                "</b></small></td>";
                                    }
                                    if (attrName == "4") {
                                        resp += "<td class='line-height-30 w-10' style='text-align: center'><small><b>" +
                                                attrValue + "</b></small></td>";
                                        resp += "<td><button onclick='EditItem(" + rec_id +
                                                ");' type='button' class='btn btn-success btn-labeled pull-left'>Edit<span class='btn-label btn-label-right'><i class='fa fa-edit'></i></span></button>&nbsp;";
                                        resp += "<button onclick='deleteItem(" + rec_id +
                                                ");' type='button' class='btn btn-danger btn-labeled pull-right'>Delete<span class='btn-label btn-label-right'><i class='fa fa-remove'></i></span></button></td></tr>";
                                    }
                                }
                            }
                            resp += "</tbody></table>";
                            $('#active').html(resp);
                            $('#main-table').dataTable({
                                "pagingType": "full_numbers",
                                "lengthMenu": [
                                    [5, 10, 15, -1],
                                    [5, 10, 15, "All"]
                                ]
                            });
                        } else {
                            $('#active').html("");
                        }
                    }
                    var _budget_type = "<select style='margin-bottom:10px;' class='form-control custom_select select2-accessible' id='budgettype0' tabindex='-2'>";
                    _budget_type += "<option value='0' disabled selected>Select a Budget Type</option>";
                    _budget_type += "<option value='1'>Revenue</option>";
                    _budget_type += "<option value='2'>Expenditure</option>";
                    _budget_type += "</select>";
                    function getAdministrativeSegmentHeader1() {
                        var txt = _budget_type + "</br>" + _sectorstring + "</br>" + _subsectorstring;
                        $('#SegmentItems').html(txt);
                    }
                    function getFunctionalSegmentHeader1() {
                        //                                        ShowLoading();
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/FunctionalSegmentServlet",
                            data: {
                                option: "<%= Utility.OPTION_SELECT_ALL%>",
                                entity: "FunctionalSegmentHeader1"
                            },
                            dataType: "json",
                            success: functionalSegmentHeader1ReturnValues,
                            error: function () {
                                //                                                StopLoading();
                                toastr["error"]("Functional Segment Select Failed!", "No record selected!");
                            }
                        });
                    }
                    function functionalSegmentHeader1ReturnValues(data) {
                        // StopLoading();
                        var txt = "";
                        var value = {};
                        var tmp = "";
                        for (var i = 0; i < data.length; i++) {
                            var obj = data[i];
                            for (var key in obj) {
                                var attrName = key;
                                var attrValue = obj[key];
                                if (attrValue == null || attrValue == 'null') {
                                    attrValue = "";
                                }
                                if (attrName == "2") {
                                    value.name = attrValue;
                                }
                                if (attrName == "3") {
                                    value.code = attrValue;
                                }
                                if (attrName == "4") {
                                    value.id = attrValue;
                                }
                            }
                            var id = value.id;
                            tmp = "<option value=valueid>valuename</option>"; // "<Select' onchange='DoSegmentReoprtItem(4, 1, valueid, 1 )'>valuename</button>&nbsp;&nbsp; ";
                            tmp = tmp.replace("valueid", value.code);
                            tmp = tmp.replace("valuename", value.name);
                            txt += tmp;
                        }
                        txt =
                                "<div><Select id='SegmentItem' class='form-control custom_select select2-accessible'><option value=0 disabled selected >Select an Item</option>" +
                                txt +
                                "<option value='ALL'>All Items</option></select>&nbsp<span class='input-group-btn'><button class='btn btn-warning' onclick=\"getReport('Segments', 1)\" type='button'><i class='fa fa-info-circle'> Go</i></button></span></div>";
                        $('#SegmentItems').html(_budget_type + "</br>" + txt); // style='display:inline'
                        //                                        StopLoading();
                    }
                    function getFundSegmentHeader1() {
                        //  ShowLoading();
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/FundSegmentServlet",
                            data: {
                                option: "<%= Utility.OPTION_SELECT_ALL%>",
                                entity: "FundSegmentHeader1"
                            },
                            dataType: "json",
                            success: fundSegmentHeader1ReturnValues,
                            error: function () {
                                //                                                StopLoading();
                                toastr["error"]("Fund Segment11 Select Failed!", "No record selected!");
                            }
                        });
                    }
                    function fundSegmentHeader1ReturnValues(data) {
                        //  StopLoading();
                        var txt = "";
                        var value = {};
                        var tmp = "";
                        for (var i = 0; i < data.length; i++) {
                            var obj = data[i];
                            for (var key in obj) {
                                var attrName = key;
                                var attrValue = obj[key];
                                if (attrValue == null || attrValue == 'null') {
                                    attrValue = "";
                                }
                                if (attrName == "1") {
                                    value.name = attrValue;
                                }
                                if (attrName == "2") {
                                    value.code = attrValue;
                                }
                                if (attrName == "0") {
                                    value.id = attrValue;
                                }
                            }
                            var id = value.id;
                            tmp = "<option value=valueid>valuename</option>"; // "<Select' onchange='DoSegmentReoprtItem(4, 1, valueid, 1 )'>valuename</button>&nbsp;&nbsp; ";
                            tmp = tmp.replace("valueid", value.code);
                            tmp = tmp.replace("valuename", value.name);
                            txt += tmp;
                        }
                        txt =
                                "<div><Select id='SegmentItem' class='form-control custom_select select2-accessible'><option value=0 disabled selected >Select an Item</option>" +
                                txt +
                                "<option value='ALL'>All Items</option></select>&nbsp<span class='input-group-btn'><button class='btn btn-warning' onclick=\"getReport('Segments', 1)\" type='button'><i class='fa fa-info-circle'> Go</i></button></span></div>";
                        $('#SegmentItems').html(_budget_type + "</br>" + txt);
                        //                                        StopLoading();
                    }
                    function getGeographicSegmentHeader1() {
                        //                                        ShowLoading();
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/GeographicSegmentServlet",
                            data: {
                                option: "<%= Utility.OPTION_SELECT_ALL%>",
                                entity: "GeographicSegmentHeader1"
                            },
                            dataType: "json",
                            success: geographicSegmentHeader1ReturnValues,
                            error: function () {
                                //                                                StopLoading();
                                toastr["error"]("Geographic Segment Select Failed!", "No record selected!");
                            }
                        });
                    }
                    function geographicSegmentHeader1ReturnValues(data) {
                        //                                        StopLoading();
                        var txt = "";
                        var value = {};
                        var tmp = "";
                        for (var i = 0; i < data.length; i++) {
                            var obj = data[i];
                            for (var key in obj) {
                                var attrName = key;
                                var attrValue = obj[key];
                                if (attrValue == null || attrValue == 'null') {
                                    attrValue = "";
                                }
                                if (attrName == "1") {
                                    value.name = attrValue;
                                }
                                if (attrName == "2") {
                                    value.code = attrValue;
                                }
                                if (attrName == "0") {
                                    value.id = attrValue;
                                }
                            }
                            var id = value.id;
                            tmp = "<option value=valueid>valuename</option>"; // "<Select' onchange='DoSegmentReoprtItem(4, 1, valueid, 1 )'>valuename</button>&nbsp;&nbsp; ";
                            tmp = tmp.replace("valueid", value.code);
                            tmp = tmp.replace("valuename", value.name);
                            txt += tmp;
                        }
                        txt =
                                "<div><Select id='SegmentItem' class='form-control custom_select select2-accessible'><option value=0 disabled selected >Select an Item</option>" +
                                txt +
                                "<option value='ALL'>All Items</option></select>&nbsp<span class='input-group-btn'><button class='btn btn-warning' onclick=\"getReport('Segments', 1)\" type='button'><i class='fa fa-info-circle'> Go</i></button></span></div>";
                        $('#SegmentItems').html(_budget_type + "</br>" + txt);
                        //                                        StopLoading();
                    }
                    function getProgrammeSegmentHeader1() {
                        //                                        ShowLoading();
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
                            data: {
                                option: "<%= Utility.OPTION_SELECT_ALL%>",
                                entity: "ProgrammeSegmentHeader1"
                            },
                            dataType: "json",
                            success: programmeSegmentHeader1ReturnValues,
                            error: function (a, b, c) {
                                //                                                StopLoading();
                                toastr["error"]("Programme Segment Select Failed!", "No record selected!");
                            }
                        });
                    }
                    function programmeSegmentHeader1ReturnValues(data) {
                        //                                        StopLoading();
                        var txt = "";
                        var value = {};
                        var tmp = "";
                        var programmecode = 0;
                        for (var i = 0; i < data.length; i++) {
                            var obj = data[i];
                            for (var key in obj) {
                                var attrName = key;
                                var attrValue = obj[key];
                                if (attrValue == null || attrValue == 'null') {
                                    attrValue = "";
                                }
                                if (attrName == "1") {
                                    value.name = attrValue;
                                }
                                if (attrName == "2") {
                                    value.code = attrValue;
                                    if (programmecode < parseInt(attrValue))
                                        programmecode = parseInt(attrValue);
                                }
                                if (attrName == "0") {
                                    value.id = attrValue;
                                }
                            }
                            var id = value.id;
                            tmp = "<option value=valueid>valuename</option>"; // "<Select' onchange='DoSegmentReoprtItem(4, 1, valueid, 1 )'>valuename</button>&nbsp;&nbsp; ";
                            tmp = tmp.replace("valueid", value.code);
                            tmp = tmp.replace("valuename", value.name);
                            txt += tmp;
                        }
                        txt =
                                "<div><Select id='SegmentItem' class='form-control custom_select select2-accessible'><option value=0 disabled selected >Select an Item</option>" +
                                txt +
                                "<option value='ALL'>All Items</option></select>&nbsp<span class='input-group-btn'><button class='btn btn-warning' onclick=\"getReport('Segments', 1)\" type='button'><i class='fa fa-info-circle'> Go</i></button></span></div>";
                        $('#SegmentItems').html(_budget_type + "</br>" + txt);
                        //                                        StopLoading();
                    }
                    function getEconomicSegmentHeader1() {
                        //                                        ShowLoading();
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/EconomicSegmentServlet",
                            data: {
                                option: "<%= Utility.OPTION_SELECT_ALL%>",
                                entity: "EconomicSegmentHeader1"
                            },
                            async: false,
                            dataType: "json",
                            success: economicSegmentHeader1ReturnValues,
                            error: function () {
                                //                                                StopLoading();
                                toastr["error"]("Economic Segment Select Failed!", "No record selected!");
                            }
                        });
                    }
                    function economicSegmentHeader1ReturnValues(data) {
                        //                                        StopLoading();
                        var txt = "";
                        var value = {};
                        var tmp = "";
                        for (var i = 0; i < data.length; i++) {
                            var obj = data[i];
                            for (var key in obj) {
                                var attrName = key;
                                var attrValue = obj[key];
                                if (attrValue == null || attrValue == 'null') {
                                    attrValue = "";
                                }
                                if (attrName == "1") {
                                    value.name = attrValue;
                                }
                                if (attrName == "2") {
                                    value.code = attrValue;
                                }
                                if (attrName == "0") {
                                    value.id = attrValue;
                                }
                            }
                            var id = value.id;
                            tmp = "<option value=valueid>valuename</option>"; // "<Select' onchange='DoSegmentReoprtItem(4, 1, valueid, 1 )'>valuename</button>&nbsp;&nbsp; ";
                            tmp = tmp.replace("valueid", value.code);
                            tmp = tmp.replace("valuename", value.name);
                            txt += tmp;
                        }
                        txt =
                                "<div><Select id='SegmentItem' class='form-control custom_select select2-accessible'><option value=0 disabled selected >Select an Item</option>" +
                                txt +
                                "<option value='ALL'>All Items</option></select>&nbsp<span class='input-group-btn'><button class='btn btn-warning' onclick=\"getReport('Segments', 1)\" type='button'><i class='fa fa-info-circle'> Go</i></button></span></div>";
                        $('#SegmentItems').html(txt);
                        //                                        StopLoading();
                    }
                    function getAllMdas() {
                        //var id = document.getElementById(arg).value;
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/MdaServlet",
                            data: {
                                option: "<%= Utility.OPTION_SELECT_ALL%>"
                            },
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: allMdasReturnValues,
                            error: function () {
                                toastr["error"]("Mdas Selection Failed!", "No record selected!!!");
                            }
                        });
                    }
                    function allMdasReturnValues(data) {
                        var tmp = {};
                        if (data.length > 0) {
                            _mdas = data;
                            var resp = "<option value='0' disabled selected>Select a Mda</option>";
                            for (var i = 0; i < data.length; i++) {
                                var obj = data[i];
                                for (var key in obj) {
                                    var attrName = key;
                                    var attrValue = obj[key];
                                    attrValue = "" + attrValue;
                                    if (attrName == "3") {
                                        tmp.adminSegment = attrValue;
                                    }
                                    if (attrName == "1") {
                                        tmp.name = attrValue;
                                    }
                                }
                                resp += "<option value='" + tmp.adminSegment + "'>" + tmp.name + "</option>";
                            }
                            $('#lstMdas').html(resp);
                            _mdastring =
                                    "<select class='form-control custom_select select2-accessible' id='mdas0' tabindex='-1' onchange='getReport(\"Mda\",1,this)'  ><option value='0' disabled selected>Select an Mda</option>" +
                                    resp + "</select>";
                        }
                    }
                    function getMDAS(arg) {
                        var id = document.getElementById(arg).value;
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/MdaServlet",
                            data: {
                                option: "<%= Utility.OPTION_SELECT_BY_ID%>",
                                sub_sector_id: id
                            },
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: mdaReturnValues,
                            error: function () {
                                toastr["error"]("Mda Selection Failed!", "No record selected!");
                            }
                        });
                    }
                    function mdaReturnValues(data) {
                        if (data.length > 0) {
                            var resp =
                                    "<table id='main-table' class='table table-clean table-striped' border='0'><thead><tr><td><b>MDAs</b></td><td><b>MDA&nbsp;Types</b></td><td><b>MDA&nbsp;Weights</b></td><td><b>Admin&nbsp;Segments</b></td><td></td></tr></thead><tbody>";
                            for (var i = 0; i < data.length; i++) {
                                var obj = data[i];
                                var rec_id = "";
                                for (var key in obj) {
                                    var attrName = key;
                                    var attrValue = obj[key];
                                    attrValue = " " + attrValue;
                                    if (attrName == "0") {
                                        rec_id = attrValue;
                                    }
                                    if (attrName == "1") {
                                        resp += "<tr><td class='line-height-70 w-25'><small><b>" + attrValue +
                                                "</b></small></td>";
                                    }
                                    if (attrName == "2") {
                                        resp += "<td class='line-height-30 w-15'><small><b>" + attrValue +
                                                "</b></small></td>";
                                    }
                                    if (attrName == "3") {
                                        resp += "<td class='line-height-30 w-15' align='center'><small><b>" + attrValue +
                                                "</b></small></td>";
                                    }
                                    if (attrName == "4") {
                                        resp += "<td class='line-height-30 w-10' style='text-align: center'><small><b>" +
                                                attrValue + "</b></small></td>";
                                        resp += "<td><button onclick='EditItem(" + rec_id +
                                                ");' type='button' class='btn btn-success btn-labeled pull-left'>Edit<span class='btn-label btn-label-right'><i class='fa fa-edit'></i></span></button>&nbsp;";
                                        resp += "<button onclick='deleteItem(" + rec_id +
                                                ");' type='button' class='btn btn-danger btn-labeled pull-right'>Delete<span class='btn-label btn-label-right'><i class='fa fa-remove'></i></span></button></td></tr>";
                                    }
                                }
                            }
                            resp += "</tbody></table>";
                            $('#active').html(resp);
                            $('#main-table').dataTable({
                                "pagingType": "full_numbers",
                                "lengthMenu": [
                                    [5, 10, 15, -1],
                                    [5, 10, 15, "All"]
                                ]
                            });
                        } else {
                            $('#active').html("");
                        }
                    }
                    var waitingDialog = waitingDialog || (function ($) {
                        'use strict';
                        // Creating modal dialog's DOM
                        var $dialog = $(
                                '<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">' +
                                '<div class="modal-dialog modal-m">' +
                                '<div class="modal-content">' +
                                '<div class="modal-header"><h3 style="margin:0;"></h3></div>' +
                                '<div class="modal-body">' +
                                '<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div>' +
                                '</div>' +
                                '</div></div></div>');
                        return {
                            /**
                             * Opens our dialog
                             * @param message Custom message
                             * @param options Custom options:
                             * 				  options.dialogSize - bootstrap postfix for dialog size, e.g. "sm", "m";
                             * 				  options.progressType - bootstrap postfix for progress bar type, e.g. "success", "warning".
                             */
                            show: function (message, options) {
                                // Assigning defaults
                                if (typeof options == 'undefined') {
                                    options = {};
                                }
                                if (typeof message == 'undefined') {
                                    message = 'Loading';
                                }
                                var settings = $.extend({
                                    dialogSize: 'm',
                                    progressType: '',
                                    onHide: null // This callback runs after the dialog was hidden
                                }, options);
                                // Configuring dialog
                                $dialog.find('.modal-dialog').attr('class', 'modal-dialog').addClass(
                                        'modal-' + settings.dialogSize);
                                $dialog.find('.progress-bar').attr('class', 'progress-bar');
                                if (settings.progressType) {
                                    $dialog.find('.progress-bar').addClass('progress-bar-' + settings.progressType);
                                }
                                $dialog.find('h3').text(message);
                                // Adding callbacks
                                if (typeof settings.onHide == 'function') {
                                    $dialog.off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
                                        settings.onHide.call($dialog);
                                    });
                                }
                                // Opening dialog
                                $dialog.modal();
                            },
                            /**
                             * Closes dialog
                             */
                            hide: function () {
                                $dialog.modal('hide');
                            }
                        };
                    })(jQuery);
                    function doQuery() {
                        var qr = $('textarea#txtqueryPad').val();
                        if (qr == "")
                            return;
                        if (qr.toLowerCase().indexOf("delete") != -1) {
                            toastr["error"]("Ilegal character detected", "Report Generation Failed!!!");
                            return;
                        }
                        if (qr.toLowerCase().indexOf("insert") != -1) {
                            toastr["error"]("Ilegal character detected", "Report Generation Failed!!!");
                            return;
                        }
                        if (qr.toLowerCase().indexOf("update") != -1) {
                            toastr["error"]("Ilegal character detected", "Report Generation Failed!!!");
                            return;
                        }
                        if (qr.toLowerCase().indexOf("trunc") != -1) {
                            toastr["error"]("Ilegal character detected", "Report Generation Failed!!!");
                            return;
                        }
                        if (qr.indexOf(";") != -1) {
                            toastr["error"]("Ilegal character detected", "Report Generation Failed!!!");
                            return;
                        }

                        ReportNumber = -2;
                        ReportItem = 'Custom';
                        ReportHead = 'General'
                        ItemName = qr
                        DoSegmentReoprtItem(ReportNumber, ReportItem, ReportHead, ItemName);
                    }
                    function EditReport() {
                        //Process all selected Reports

                    }
                    function ListReportsforBudgetBook() {
                        var markUp = "<div class='checkbox'><label><input type='checkbox' value=''>Report 1</label></div>"

                    }
                    var selected = [];
                    function AddtoBudgetBook() {
                        //alert("hh");
                        $('#AvailableReports input:checked').each(function () {
                            selected.push($(this).attr('value'));
                        });
//                                        var firstNumber = selected[0];
//                                        var newPosition = firstNumber - 1 < 0 ? 0 : firstNumber - 1;
//                                        selected = selected.move(firstNumber, newPosition)
//                                        console.log(selected);

                        for (idx of selected) {
                            console.log(idx);
                            switch (idx) {
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case "13":
                                    var _ReportHead = "21010103,21020202,22010101,22010102,22010104,22060102,22060202,22070104,22070105,22070106";
                                    $('#selRevenueReport').val(idx);
                                    RevenueReport();
                                    break;
                                case 0:
                            }
                        }
                    }
                    var jCounter = 0;
                    function LoadReportTemplates() {
                        var reportNumbers = [1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25, 26, 28, 33, 40, 41, 42];
                        var reportTemplates = ["Allocations to MDAs", "Allocations to MDAs (Capital Estimates)", "Over Head Cost by MDAs", "Summary of Approved Recurrent and Capital Est.", "Summary of Revenue by Economic Segment", "Details of Revenue by Economic Segment", "Summary of Total Revenue Based on Sector by Ind. Rev.", "Revenue Details - Revenue Estimates", "Summary of Recurrent Estimates", "Grants to Parastatals / Tertiary Institutions", "Recurrent Expenditure - Special Programmes by MDAs", "Consolidated Revenue Funds Charges", "Grants and Loans", "Recurrent Expenditure - Overhead Cost by MDAs", "Sectoral Summary", "Allocations of Funds to MDAs", "Consolidated Budget Summary(Master Budget)", "Consolidated Budget Summary Based on Sectors", "Approved Capital Estimates - Project Details", "Summary of Total Capital Budget by Sectors", "Details of capital Project Budget under Programme", "Capital Budget Allocation to MDAs", "Summary of Capital Budget By Programme", "Summary of Capital Budget By Functions", "Summary of Capital Budget By Policy", "Capital Expenditure by Economic Segment", "Summary of Approved Personnel Cost", "Personnel Cost Details", "Summary of Personnel Cost"]
                        var nCount = reportTemplates.length;
                        var out = "";
                        var out2 = "";
                        var iCounter = 0;
                        for (rpt of reportTemplates) {
                            out += "<div class='row'><div class='col-md-5'> <div class='checkbox'  id=rptdiv1" + reportTemplates.indexOf(rpt) + "><input type='checkbox' value='" + reportNumbers[iCounter] + "'>" + rpt + "</div></div><div class='col-md-2'><button type='button' id='but" + iCounter + "' class='btn btn-success' onclick='MoveReport(rptdiv1" + reportTemplates.indexOf(rpt) + ", " + iCounter + " )'><span class='fa fa-angle-double-right'></span></button></div><div class='col-md-5' id='rptdiv2" + iCounter + "'></div></div>"
                            iCounter++;
                        }
                        $('#AvailableReports').html(out);
                        jCounter = 0;
                        //get the first number
                        //  alert(newPosition);
                        //"<div class='checkbox'><input type='checkbox' value=''></div>"
                    }
                    function MoveReport(idx, k) {
                        //$(idx).hide();
                        //idx=idx.replace("div1","div2");
                        jQuery(idx).detach().appendTo('#rptdiv2' + jCounter)
                        //  $('#rptdiv2' + jCounter).append(idx);
                        // var hh=$('#rptdiv2' + jCounter);//.outerHTML();
                        // console.log (hh);
//                                        $(idx).hide();
                        $('#but' + k).hide();
                        jCounter++;
                    }
                    function alert1() {}
                    function pdfthis() {

//                                        var _budget_year = request.getParameter("budget_year");
//
//                                        var _ReportNumber = request.getParameter("ReportNumber");
//                                        var _budget_type = request.getParameter("budget_type");
//                                        var _ReportItem = request.getParameter("ReportItem");
//                                        var _ItemName = request.getParameter("ItemName");
//                                        var _ReportHead = request.getParameter("ReportHead");
//                                        var vals = [11010101, 11010201, 12, 13, 14, 21010103, 22060202, 22060102, 21010101, 21010103, 22010104, 2202, 22040110, 22040105, 2210, 22070104, 01, 02, 03, 04, 05];

//                                        $.ajax({
//                                            type: "GET",
//                                            url: "<%= Utility.SITE_URL%>/ReportServlet",
//                                            data: {
//                                                ReportNumber: _ReportNumber,
//                                                budget_year: budget_year,
//                                                ReportAry: _ReportNumber,
//                                                _budget_type,
//                                                _ReportItem,
//                                                _ItemName,
//                                                _ReportHead
//                                                        vals: vals
////                                                option: "<%= Utility.OPTION_SELECT_BY_ID%>",
////                                                sub_sector_id: id
//                                            },
//                                            dataType: "json",
//                                            cache: false,
//                                            async: false,
//                                            success: mdaReturnValues,
//                                            error: function () {
//                                                toastr["error"]("Mda Selection Failed!", "No record selected!");
//                                            }
//                                        });
//                                        var element = document.getElementById('txtReportPad');
//                                        html2pdf(element);
                    }
                    function GetBudgetReports() {
                        //_budgetyear
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/ReportServlet",
                            data: {
                                option: "Report", budget_year: _budgetyear,
                                ActiveCurrency: ActiveCurrency,
                                ActiveVersion: ActiveVersion,
                                ActiveBudgetType: ActiveBudgetType
                            },
                            dataType: "html",
                            cache: false,
                            async: false,
                            success: function (data) {
                                $('#ReportsList').show();
                                $('#ReportsList').html(data);
                                $('#ReportEditor').hide();
                            },
                            error: function (a, b, c) {
                                console.log(a + b + c);
                                toastr["error"]("No record selected!", "Budget Report Selection Failed!!!");
                            }
                        });
                    }
                    var _redportId
                    function LoadBudgetDocument(idx) {
                        _redportId = idx;
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/ReportServlet",
                            data: {
                                option: "<%= Utility.OPTION_SELECT%>", budget_year: _budgetyear, ReportId: idx,
                                ActiveCurrency: ActiveCurrency,
                                ActiveVersion: ActiveVersion,
                                ActiveBudgetType: ActiveBudgetType
                            },
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: function (data) {
                                $('#budgetDocument').val(data['ReportTitle']);
                                var txt = data['ReportText'];
                                CKEDITOR.instances.txtBudgetreport.setData(txt);
                                $('#ReportsList').hide();
                                $('#ReportEditor').show();
                                console.log(data);
                            },
                            error: function (a, b, c) {
                                console.log(a + b + c);
                                toastr["error"]("No record selected!", "Budget Report Selection Failed!!!");
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
                            optt = "<option value='-1' disabled selected>Select a Budget Version</option>" + optt;
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
                                ActiveCurrency: ActiveCurrency,
                                ActiveVersion: ActiveVersion,
                                ActiveBudgetType: ActiveBudgetType
                            },
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: function (data) {
                                BudgetVersionsTable = data;
                                var optt;
                                for (i = 0; i < data.length; i++) {
                                    var itm = data[i];
                                    optt += "<option value='" + itm[0] + "'>" + itm[2] + "</option>";
                                }
                                $('#cmbBudgetVersion').html(optt);
                                $("#cmbBudgetVersion option[value=5]").attr('selected', 'selected'); //Update 5 with GetApprovedBudet()
                                optt = "<option value='-1' disabled selected>Select a Budget Version</option>" + optt;
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
                        getCurrencies();
                    }
                    var CurrencyTable = "";
                    function getCurrencies() {
                        if (CurrencyTable.length > 0) {
                            var data;
                            var optt;
                            data = CurrencyTable;
                            for (i = 0; i < data.length; i++) {
                                var itm = data[i];
                                optt += "<option value='" + itm[0] + "'>" + itm[1] + " " + itm[2] + "</option>";
                            }
                            $('#cmbCurrency').html(optt);
                            $("#cmbCurrency option[value=1]").attr('selected', 'selected'); //Update 4 with GetDefaultCurrency()
                            oppt = $('#cmbCurrency').html();
                            $('#cmbCurrency0').html(optt);
                            $("#cmbCurrency0 option[value=1]").attr('selected', 'selected'); //Update 4 with GetDefaultCurrency()
                            return;
                        }
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/ReportServlet",
                            data: {
                                option: "getCurrencies", budget_year: _budgetyear,
                                ActiveCurrency: ActiveCurrency,
                                ActiveVersion: ActiveVersion,
                                ActiveBudgetType: ActiveBudgetType
                            },
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: function (data) {
                                var optt;
                                CurrencyTable = data;
                                for (i = 0; i < data.length; i++) {
                                    var itm = data[i];
                                    optt += "<option value='" + itm[0] + "'>" + itm[1] + " " + itm[2] + "</option>";
                                }
                                $('#cmbCurrency').html(optt);
                                $("#cmbCurrency option[value=1]").attr('selected', 'selected');
                                oppt = $('#cmbCurrency').html();
                                $('#cmbCurrency0').html(optt);
                                $("#cmbCurrency0 option[value=1]").attr('selected', 'selected');
                            },
                            error: function (a, b, c) {
                                console.log(a + b + c);
                                toastr["error"]("No record selected!", "Currency Failed!!!");
                            }
                        });
                        getYearBudgetTypes();
                    }
                    var budgetTypes = "";
                    function getYearBudgetTypes() {
                        //<select id="cmbBudgetType" onchange="ChangeBudgetType(this.value)"></select>
                        if (budgetTypes.length > 0) {
                            var data;
                            var opt;
                            data = budgetTypes;
                            for (i = 0; i < data.length; i++) {
                                var itm = data[i];
                                console.log(data);
                                opt += "<option value='" + itm[2] + "'>" + itm[1] + "</option>";
                            }
                            $('#cmbBudgetType').html(opt);
                            $("#cmbBudgetType option[value=1]").attr('selected', 'selected'); //Update 1 with GetApprovedBudet()
//                                            opt = $('#cmbBudgetType').html();
//                                            $('#cmbBudgetType0').html(opt);
//                                            $("#cmbBudgetType0 option[value=4]").attr('selected', 'selected'); //Update 4 with GetDefaultCurrency()

                            return;
                        }
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/ReportServlet",
                            data: {
                                option: "getBudgetType", budget_year: _budgetyear,
                                ActiveCurrency: ActiveCurrency,
                                ActiveVersion: ActiveVersion,
                                ActiveBudgetType: ActiveBudgetType
                            },
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: function (data) {
                                console.log(data);
                                budgetTypes = data;
                                var opt;
                                for (i = 0; i < data.length; i++) {
                                    var itm = data[i];
                                    opt += "<option value='" + itm[0] + "'>" + itm[1] + "</option>";
                                }
                                $('#cmbBudgetType').html(opt);
                                $("#cmbBudgetType option[value=1]").attr('selected', 'selected'); //Update 1 with GetApprovedBudet()
//                                                opt = $('#cmbBudgetType').html();
//                                                $('#cmbBudgetType0').html(opt);
//                                                $("#cmbBudgetType0 option[value=1]").attr('selected', 'selected'); //Update 4 with GetDefaultCurrency()
                            },
                            error: function (a, b, c) {
                                console.log(a + b + c);
                                toastr["error"]("No record selected!", "Budget Version Selection Failed!!!");
                            }
                        });
                        //  getCurrencies();
                    }
                    function ChangeCurrency(idx) {
                        ActiveCurrency = idx;
                    }
                    function ChangeBudgetVersion(idx) {
                        ActiveVersion = idx;
                    }
                    function ChangeBudgetType(idx) {
                        ActiveBudgetType = idx;
                    }
                    function SaveBudgetDocument() {
                        var title = $('#budgetDocument').val();
                        var text = CKEDITOR.instances.txtBudgetreport.document.getBody().getHtml();
                        $.ajax({
                            type: "POST",
                            url: "<%= Utility.SITE_URL%>/ReportServlet",
                            data: {
                                option: "<%= Utility.OPTION_UPDATE%>",
                                budget_year: _budgetyear,
                                ReportText: text,
                                ReportTitle: title,
                                ReportId: _redportId,
                                ActiveCurrency: ActiveCurrency,
                                ActiveVersion: ActiveVersion,
                                ActiveBudgetType: ActiveBudgetType
                            },
                            dataType: "text",
                            cache: false,
                            async: false,
                            success: function (data) {
                                swal("Document Saved Successfuly", "", "success");
                                GetBudgetReports();
                            },
                            error: function (a, b, c) {
                                console.log(a + b + c);
                                toastr["error"]("No record selected!", "Budget Report Selection Failed!!!");
                            }
                        });
                    }
                    function NewReport() {
                        swal({
                            title: "Are you sure?",
                            text: "Any unsaved data will be lost!",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonClass: "btn-danger",
                            confirmButtonText: "Continue",
                            closeOnConfirm: false
                        },
                                function () {
                                    _redportId = null;
                                    $('#budgetDocument').val("");
                                    CKEDITOR.instances.txtBudgetreport.setData("");
                                    $('#ReportsList').hide();
                                    $('#ReportEditor').show();
                                    swal.close();
                                });

                    }
                    function getBudgetVersionsReport() {
                        var ReportItem = "VersionReport";
                        var ItemName = "Economic Segment";
                        var ReportHead = "ALL";
                        var ReportNumber = 7;
                        var budget_type = $("#budgettype0").val();
                        var _versions = "";
                        for (i = 1; i < 6; i++) {
                            _versions += $("#ver" + i).val() + ","
                        }
                        _versions = _versions.substring(0, _versions.length - 1);
                        //alert(_versions);
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/AdminReportsServlet",
                            data: {
                                ReportNumber: ReportNumber,
                                ReportItem: ReportItem,
                                ItemName: ItemName,
                                budget_year: _budgetyear,
                                ReportHead: ReportHead,
                                budget_type: budget_type,
                                ActiveCurrency: ActiveCurrency,
                                ActiveVersion: ActiveVersion,
                                ActiveBudgetType: ActiveBudgetType,
                                BudgetVersions2Report: _versions
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
                                $('#txtReportPad')[0].childNodes[0].data = "";

                            },
                            error: function (a, b, c) {
                                console.log(a + b + c);
                                toastr["error"]("No record selected!", "Budget Version Selection Failed!!!");
                            }
                        });
                    }
        </script>
    </body>
</html>
