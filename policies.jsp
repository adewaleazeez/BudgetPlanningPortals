<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <!-- ========== MODERNIZR ========== -->
        <script src="js/modernizr/modernizr.min.js"></script>
        <link rel="stylesheet" href="css/jquery.loading.css" media="screen">
        <style>
            .hover-control{
                display: inline !important;
            }

            table, td, tr {
                border: 1px solid black;
            }

            table {
                border-collapse: collapse;
                width: 100%;
            }

            tr {
                text-align: left;
            }

            /*.tooltip {
                position: relative;
                display: inline-block;
                border-bottom: 1px dotted black;
            }

            .tooltip .tooltiptext {
                visibility: hidden;
                width: 120px;
                background-color: black;
                color: #fff;
                text-align: center;
                border-radius: 6px;
                padding: 5px 0;

                position: absolute;
                z-index: 1;
            }

            .tooltip:hover .tooltiptext {
                visibility: visible;
            }*/
        </style>

    </head>
    <body class="top-navbar-fixed">
        <div class="main-wrapper">
            <!-- ========== TOP NAVBAR ========== -->
            <nav class="navbar top-navbar bg-white box-shadow">
                <div class="container-fluid">
                    <div class="row">
                        <div class="navbar-header no-padding">
                            <a class="navbar-brand" onclick=gotoLink("/dashboard00012");>
                                <img src="images/logo-dark.png" alt="Options - Admin Template" class="logo" style="display: inline; margin-bottom: 10px">&nbsp;<div style="display: inline"><b>Budget</b></div>
                            </a>
                            <span class="small-nav-handle hidden-sm hidden-xs"><i class="fa fa-outdent"></i></span>
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
                                    <h2 class="title">Policies</h2>
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
                                        <li><a onclick=gotoLink("/dashboard00012");><i class="fa fa-home"></i> Home</a></li>
                                        <li class="active">Policies Setup</li>
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
                                <div class="row" id="MainArea">
                                    <div class="col-md-12">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h5></h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">                                                
                                                <div class="col-lg-12">
                                                    <a class="btn btn-info toggle-code-handle pull-right newpolicy" onclick="CreatePolicy();" role="button"><i class="fa fa-user-plus"> </i>New Policy</a>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-5">
                                                        <div class="col-md-5">
                                                            <div class="input-group">
                                                                <label for="policy_year_id0">Budget Year</label>                                                        
                                                                <select class='js-states form-control' id='policy_year_id0' ></select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-5">
                                                            <div class="input-group">
                                                                <label for="groupcode0">Policy Group</label>                                                        
                                                                <select class='js-states form-control' id='group_code0' style="width:200" onchange="getPolicies()" ></select>  
                                                            </div> 
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="input-group">
                                                                <button class="btn btn-warning" data-toggle="modal" data-target="#GroupCodeModal" onclick="getGroupCode();$('#groupcodeupdate').prop('disabled', true); $('#txtGroupCode').val(''); $('#txtGroupName').val('');"><span class="glyphicon glyphicon-plus" style="vertical-align:middle">Manage Policy Group</span></button>
                                                            </div> 
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--Add new Group Code Modaal-->
                                                <div class="modal fade" id="GroupCodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                <div class="panel-title">Add New Policy Code</div>
                                                                <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#"></a></div>

                                                            </div> 
                                                            <div class="modal-body">
                                                                <div style="display:none" id="addgroupcode-alert" class="alert alert-danger col-sm-12"></div>

                                                                <div style="margin-bottom: 3px" class="input-group">
                                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-qrcode"></i></span>
                                                                    <input id="txtGroupCode" maxlength="2"  type="text" class="form-control" name="txtGroupCode" placeholder="Group Code" onkeypress="return event.charCode === 0 || /\d/.test(String.fromCharCode(event.charCode));" onkeypress="return isNumberKey(event);">
                                                                    <input id="groupcodeid" value="" type="hidden">
                                                                </div>                                                                                
                                                                <div style="margin-bottom: 25px" class="input-group">
                                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
                                                                    <input id="txtGroupName" type="text" class="form-control" name="txtGroupName" value="" placeholder="Group Name">
                                                                </div>
                                                                <button id="groupcodeupdate" style="margin-bottom: 3px" type='button' class='btn btn-warning btn-labeled pull-right' disabled onclick="UpdatePolicyGroup()"  >Update</button>
                                                                <div style="margin-top:10px" id="lstgroupcodes" class="form-group">

                                                                </div>

                                                            </div>
                                                            <div class="modal-footer">
                                                                <!-- Button -->
                                                                <div class="col-sm-12 controls">
                                                                    <a id="addpolicygrp" href="#" class="btn btn-success" onclick="AddNewPolicyGroup()">Add Policy Group</a>
                                                                    <a id="btn-cancel" href="#" class="btn btn-primary" data-dismiss="modal" >Cancel</a>

                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>                                                
                                                <!--End Add New Group Code Modal-->     
                                                <!-- <div class="col-lg-6">
                                                     <div class="input-group">
                                                         <label for="policy_year_id0">Budget&nbsp;Year&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                         <select class='js-states form-control' id='policy_year_id0' disabled></select>
                                                     </div>
                                                      /input-group 
                                                 </div>-->

                                                <br style="clear: both;" />
                                                <!-- Nav tabs -->
                                                <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                    <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">Policies</a></li>
                                                    <!--li role="presentation" class=""><a class="" href="#blocked" aria-controls="blocked" role="tab" data-toggle="tab" aria-expanded="false">Blocked</a></li-->
                                                </ul>

                                                <!-- Tab panes -->
                                                <div class="tab-content bg-white p-15">
                                                    <div role="tabpanel" class="tab-pane active" id="active"></div>
                                                </div>
                                            </div>
                                        </div>


                                    </div>
                                    <!-- /.col-md-12 -->

                                </div>
                                <!-- /.row -->

                                <div class="row mt-30" id="CreateArea" style="display: none;">
                                    <div class="col-md-8">

                                        <div class="panel border-primary no-border border-3-top">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h5></h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <form>
                                                    <div class="row">
                                                        <div class="col-xs-3">
                                                            <div class="input-group">
                                                                <label for="policy_year_id1">Budget Year</label>
                                                                <select class='js-states form-control' id='policy_year_id1' disabled></select>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <div class="input-group">
                                                                <label for="group_code_c">Policy Group</label>                                                        
                                                                <select class='js-states form-control' id='group_code_c' style="width:200" ></select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <label for="txtDescription_a">Policy Name</label>
                                                    <input type="text" class="form-control" id="txtDescription_a" placeholder="Policy Nametion" autofocus="">
                                                    <div class="form-group">
                                                        <label for="txtWeight_a">Policy Weight</label>
                                                        <input type="text" class="form-control" id="txtWeight_a" placeholder="Policy Weight">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for='selMdas_a'>MDAs</label>
                                                        <select class='js-states form-control' id='selMdas_a' multiple='multiple'></select>
                                                    </div>
                                                    <hr />
                                                    <div class="form-group">
                                                        <button type="button" onclick="FinishCreate();" class="btn btn-success btn-labeled" style="float: right;">
                                                            Create Policy
                                                            <span class="btn-label btn-label-right"><i class="fa fa-save"></i></span>
                                                        </button>
                                                        <button type="button" onclick="cancelAdd()" class="btn btn-danger btn-labeled" style="float: left;">
                                                            Cancel Add Policy 
                                                            <span class="btn-label btn-label-right"><i class="fa fa-remove"></i></span>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col-md-9 -->


                                </div>

                                <div class="row mt-30" id="EditArea" style="display: none;">
                                    <div class="col-md-8">

                                        <div class="panel border-primary no-border border-3-top">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h5></h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <form>
                                                    <input type="hidden" id="txtId_e" >
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <div class="input-group">
                                                                <label for="policy_year_id2">Budget&nbsp;Year&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>                                                        
                                                                <select class='js-states form-control' id='policy_year_id2' disabled></select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="input-group">
                                                                <label for="groupcode2">Policy Group</label>                                                        
                                                                <select class='js-states form-control' id='group_code2'></select>
                                                            </div> 
                                                        </div>
                                                        <div class="col-md-4">

                                                        </div>

                                                    </div>
                                                    <div class="form-group">
                                                        <label for="txtDescription_e">Policy Name</label>
                                                        <input type="email" class="form-control" id="txtDescription_e" placeholder="Policy Nametion" autofocus="">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="txtWeight_e">Policy Weight</label>
                                                        <input type="text" class="form-control" id="txtWeight_e" placeholder="Policy Weight">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for='selMdas_e'>MDAs</label>
                                                        <select class='js-states form-control' id='selMdas_e' multiple='multiple'></select>
                                                    </div>
                                                    <hr />
                                                    <div class="form-group">
                                                        <button type="button" onclick="FinishEdit();" class="btn btn-success btn-labeled" style="float: right;">
                                                            Update Policy
                                                            <span class="btn-label btn-label-right"><i class="fa fa-save"></i></span>
                                                        </button>
                                                        <button type="button" onclick="cancelAdd()" class="btn btn-danger btn-labeled" style="float: left;">
                                                            Cancel Edit Policy 
                                                            <span class="btn-label btn-label-right"><i class="fa fa-remove"></i></span>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col-md-9 -->
                                </div>

                                <div id="deleteModal" role="dialog">
                                    <div class="">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-izimodal-close="">&times;</button>
                                                <h4 class="modal-title">Delete MDA</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>Are you sure you want to delete this item</p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-danger" data-dismiss="modal" id="confirmDelete" onclick="finishDeleteItem();">Yes, Delete</button>
                                                <button type="button" class="btn btn-warning" data-dismiss="modal" data-izimodal-close="" onclick="removeWindowId();" id="cancel">No</button>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="row mt-30" id="ImageArea" style="display: none;">

                                    <div class="col-md-4">


                                        <table class="table table-striped">
                                            <tbody>

                                                <tr>
                                                    <td class="p-20">
                                                        <div id="PreviewImage" style="width: 100%; max-width: 100%; "></div>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <!-- /.panel -->
                                        <!-- /.panel -->
                                    </div>
                                    <!-- /.col-md-3 -->

                                </div>
                        </section>
                    </div>
                    <!-- /.main-page -->
                    <!-- /.right-sidebar -->
                </div>
                <!-- /.content-container -->
            </div>
            <!-- /.content-wrapper -->
        </div>
        <!-- /.main-wrapper -->
        <!-- Constants -->

        <div id="GroupCodeDeleteModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header panel-warning">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Confirm Group Code Delete</h4>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete this Group Code?</p>
                        <p class="text-warning"><small>Changes cannot be reversed</small></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" data-dismiss="modal">No, Don't Delete</button>
                        <button type="button" class="btn btn-danger" onclick="DeleteGroupCode();">Yes, Delete</button>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" id="site-url" value="<%= Utility.SITE_URL%>">

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

                $('#deleteModal').iziModal();
                createCookie("formtype", "");
                getBudgetYear();
                getGroupCode();
                getMdas();
                $("#selMdas_a, #selMdas_e").select2();
            });
            var delcode;
            function getBudgetYear() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/PolicyServlet",
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
                        }
                    }
                    $('#policy_year_id0').html(resp);
                    $('#policy_year_id1').html(resp);
                    $('#policy_year_id2').html(resp);
                }
                // getPolicies();

            }
            ;

            function getMdas() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MdaServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
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
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    //resp += "</select>";
                    $('#selMdas_a').html("<option value='0'></option>" + resp);
                    $('#selMdas_e').html("<option value='0'></option>" + resp);
                }
            }
            ;
            function getGroupCode() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/PolicyGroupServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: GroupCodeReturnValues,
                    error: function () {
                        toastr["error"]("No record selected!", "Group Code Selection Failed!!!");
                    }
                });
            }
            function GroupCodeReturnValues(data) {
                if (data.length > 0) {

                    var resp = "";
                    var table = "";
                    var id = "0";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        for (var key in obj) {
                            var attrName = key;
                            //alert (key + " - " + i++);
                            var attrValue = obj[key];

                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }
                            //alert (attrName + "  -   " + attrValue);
                            if (attrName === "0") {
                                id = attrValue;
                            }
                            if (attrName === "1") {
                                resp += "<option value='" + attrValue + "' >";
                                table += "<tr><td>" + attrValue + "</td>";

                            }
                            if (attrName === "2") {
                                resp += attrValue + "</option>";
                                table += "<td>" + attrValue + "</td><td>"
                                table += "<button onclick='EditPolicyGroup(" + id + ")' type='button' class='btn btn-success btn-labeled pull-left'>Edit<span class='btn-label btn-label-right'><i class='fa fa-edit'></i></span></button></td><td>";
                                table += "<button onclick='DeletePolicyGroup(" + id + ");' type='button' class='btn btn-danger btn-labeled pull-right'>Delete<span class='btn-label btn-label-right'><i class='fa fa-remove'></i></span></button></td></tr>";
                            }
                        }
                    }

                    $('#group_code0').html("<option  value='0' selected></option>" + resp);
                    $('#group_code_c').html("<option  value='0' selected></option>" + resp);
                    $('#group_code2').html("<option  value='0' selected></option>" + resp);
                    $('#lstgroupcodes').html("<table><thead><th>Code</th><th>Policy Group</th><th></th><th></th></thead><tbody>" + table + "</tbody></table>");
                    /*$('#policy_year_id1').html(resp);
                     $('#policy_year_id2').html(resp);*/
                }
                getPolicies();

            }
            ;
            EdiPolicy = function (arg) {
                createCookie("formtype", "edituser");
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/PolicyServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: arg},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            //console.log(data);
                            for (var i = 0; i < data.length; i++) {
                                var obj = data[i];

                                for (var key in obj) {
                                    var attrName = key;
                                    var attrValue = obj[key];

                                    //alert(attrName+"    "+attrValue+"    "+typeof(attrValue));

                                    if (attrValue === null || attrValue === 'null') {
                                        attrValue = "";
                                    }
                                    if (attrName === "0") {
                                        document.getElementById("txtId_e").value = attrValue;
                                    }
                                    if (attrName === "1") {
                                        document.getElementById("txtDescription_e").value = attrValue;
                                    }
                                    if (attrName === "2") {
                                        document.getElementById("txtWeight_e").value = attrValue;
                                    }
                                    if (attrName === "6") {
                                        var obj = eval('[' + attrValue + ']');
                                        $('#selMdas_e').val(obj).trigger('change');
                                    }
                                    if (attrName === "5") {
                                        var ii = attrValue;
                                        document.getElementById("group_code2").value = attrValue;
                                        $('#group_code2').val(attrValue).trigger('change');
                                    }
                                }
                                break;
                            }
                            toastr["success"]("Policy record successfully fetched!", "Policy Selection Successfull!!!");
                            $('#MainArea').hide();
                            $('#EditArea').fadeIn();
                            $('ul.breadcrumb').html('<li><a onclick=gotoLink("/dashboard00012");><i class="fa fa-home"></i> Home</a></li><li onclick="FinishEdit()">Policies Setup</li><li class="active" >Edit User</li>');
                            document.getElementById("txtDescription_e").focus();
                        }
                    },
                    error: function () {
                        toastr["error"]("Record with id " + arg + " is not found!", "Policy Selection Failed!!!");
                    }
                });
            };

            FinishEdit = function () {
                var id = document.getElementById("txtId_e").value;
                var description = document.getElementById("txtDescription_e").value;
                var policy_weight = document.getElementById("txtWeight_e").value;
                var policy_year_id = document.getElementById("policy_year_id2").value;
                var groupcode = document.getElementById("group_code2").value;
                var selMdas_e = document.getElementById("selMdas_e");
                var selMdas = "";
                for (var i = 0; i < selMdas_e.length; i++) {
                    if (selMdas_e[i].selected === true) {
                        selMdas += selMdas_e[i].value + ",";
                    }
                }
                var error = "";
                if (description === "") {
                    error += "<br>Policy Name must not be blank<br>";
                }
                if (policy_weight === "") {
                    error += "Policy Weight must not be blank<br>";
                }
                if (selMdas === "") {
                    error += "MDAs must not be blank<br>";
                }
                if (group_code2 === "") {
                    error += "Group Code must not be blank<br>";
                }
                if (error !== "") {
                    toastr["error"](error, "Please Correct The Following Error(s)!!!");
                    return true;
                }
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/PolicyServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE%>", id: id, description: description, policy_weight: policy_weight, policy_year_id: policy_year_id, mdaId: selMdas, group_code: groupcode},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.indexOf("<%= Utility.ActionResponse.NO_RECORD.toString()%>") !== -1) {
                            toastr["error"]("Policy does not exist!", "Policy Editing Failed!!!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.UPDATED.toString()%>") !== -1) {
                            getPolicies();
                            toastr["success"](" rPolicyecord successfully updated!", "Policy Editing Successfull!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown){
                    error: function () {
                        toastr["error"]("The server is not accessible!", "Policy Editing Failed!!!");
                    }
                });
                $('#EditArea').hide();
                $('#MainArea').fadeIn();
                $('ul.breadcrumb').html('<li><a onclick=gotoLink("/dashboard00012");><i class="fa fa-home"></i> Home</a></li><li class="active"> User management</li>');
            };
            cancelEdit = function () {
                $('#EditArea').hide();
                $('#MainArea').fadeIn();
                $('ul.breadcrumb').html('<li><a onclick=gotoLink("/dashboard00012");><i class="fa fa-home"></i> Home</a></li><li class="active"> User management</li>');
            };

            CreatePolicy = function () {

                var group_code_id = document.getElementById("group_code0").value;
                if (group_code_id == 0) {
                    toastr["error"]("Please select a Policy Group!", "");
                    return;
                }
                createCookie("formtype", "createuser");
                $('#MainArea').hide();
                $('#CreateArea').fadeIn();
                $("#group_code_c").val(group_code_id);
                $('#group_code_c').attr('disabled', 'disabled');
                $('ul.breadcrumb').html('<li><a onclick=gotoLink("/dashboard00012");><i class="fa fa-home"></i> Home</a></li><li onclick="FinishEdit()"> User management</li><li class="active" >Add User</li>');
            };
            FinishCreate = function () {
                var description = document.getElementById("txtDescription_a").value;
                var policy_weight = document.getElementById("txtWeight_a").value;
                var policy_year_id = document.getElementById("policy_year_id1").value;
                var selMdas_a = document.getElementById("selMdas_a");
                var group_code = document.getElementById("group_code_c").value;
                var selMdas = "";
                for (var i = 0; i < selMdas_a.length; i++) {
                    if (selMdas_a[i].selected === true) {
                        selMdas += selMdas_a[i].value + ",";
                    }
                }
                var error = "";
                if (description === "") {
                    error += "<br>Description must not be blank<br>";
                }
                if (policy_weight === "") {
                    error += "Policy Weight must not be blank<br>";
                }
                if (selMdas === "") {
                    error += "MDAs must not be blank<br>";
                }
                if (group_code === "") {
                    error += "Policy Code must not be blank<br>";
                }
                if (error !== "") {
                    toastr["error"](error, "Please Correct The Following Error(s)!!!");
                    return true;
                }
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/PolicyServlet",
                    data: {option: "<%= Utility.OPTION_INSERT%>", description: description, policy_weight: policy_weight, policy_year_id: policy_year_id, mdaId: selMdas, group_code: group_code},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.indexOf("<%= Utility.ActionResponse.RECORD_EXISTS.toString()%>") !== -1) {
                            toastr["error"]("Policy already exists!", "Policy Creation Failed!!!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {
                            getPolicies();
                            toastr["success"]("Policy record successfully created!", "Policy Creation Successfull!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown){
                    error: function () {
                        toastr["error"]("The server is not accessible!", "User Creation Failed!!!");
                    }
                });
                $('#CreateArea').hide();
                $('#MainArea').fadeIn();
                $('ul.breadcrumb').html('<li><a onclick=gotoLink("/dashboard00012");><i class="fa fa-home"></i> Home</a></li><li class="active"> User management</li>');

            };
            cancelAdd = function () {
                $('#CreateArea').hide();
                $('#EditArea').hide();
                $('#MainArea').fadeIn();
                $('ul.breadcrumb').html('<li><a onclick=gotoLink("/dashboard00012");><i class="fa fa-home"></i> Home</a></li><li class="active"> User management</li>');
            };

//            $(function () {
//
//                // Counter for dashboard stats
//                $('.counter').counterUp({
//                    delay: 10,
//                    time: 1000
//                });
//
//                $("#modal11").iziModal();
//                $('.newpolicy').on('click', function () {
//                    $('#modal11').iziModal('open', this);
//                });
//
//                $("#selMdas_a, #selMdas_e ").select2();
//                //$('#chkActive_a').bootstrapSwitch({onText: 'Active', offText: 'Blocked'});
//                //$('#chkActive_e').bootstrapSwitch({onText: 'Active', offText: 'Blocked'});
//            });
//            $('#selMdas_a').on("select2:unselecting", function (e) {
//                var unselected_value = e.params.args.data.id;
//            }).trigger('change');

            function getPolicies() {
                ShowLoading();
                var group_code_id = document.getElementById("group_code0").value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/PolicyServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL_BY_GROUP%>", group_code: group_code_id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: policyReturnValues,
                    error: function () {
                        StopLoading();
                        toastr["error"]("No record selected!", "Policies Selection Failed!!!");
                    }
                });
            }
            ;
            function policyReturnValues(data) {
                StopLoading();
                //console.log(data);
                if (data.length > 0) {
                    var resp = "";
                    var active_div = "<table id='main_table_active' class='table table-bordered' >";
                    active_div += "<thead><tr><td><b>Policy&nbsp;Name</b></td><td><b>Policy&nbsp;Weight</b></td><td><b>MDAs</b></td><td></td><td></td></tr></thead><tbody>";

                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var rec_id = "0";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }

                            if (attrName === "0") {
                                rec_id = attrValue;
                                resp += "<tr class='hover-row' ref='user_123'>";
                            }
                            if (attrName === "1") {
                                resp += "<td style='white-space:nowrap;'>" + attrValue + "</td>";
                            }
                            if (attrName === "2") {
                                resp += "<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + attrValue + "</td>";
                            }
                            if (attrName === "5") {
                                //attrValue = attrValue.replace(/ /g, '&nbsp;').replace(/,&nbsp;/g, ',<br><br><br><br><br>');
                                resp += "<td><b>" + attrValue + "</b></td>";
                                resp += "<td><button onclick='EdiPolicy(" + rec_id + ");' type='button' class='btn btn-success btn-labeled pull-left'>Edit<span class='btn-label btn-label-right'><i class='fa fa-edit'></i></span></button></td>";
                                resp += "<td><button onclick='DeletePolicy(" + rec_id + ");' type='button' class='btn btn-danger btn-labeled pull-right'>Delete<span class='btn-label btn-label-right'><i class='fa fa-remove'></i></span></button></td></tr>";
                                active_div += resp;
                                resp = "";
                            }
                        }
                    }
                    active_div += "</tbody></table>";

                    $('#active').html(active_div);
                    //$('#main_table_active').dataTable();
                    /* $('#main_table_active').dataTable({
                     "pagingType": "full_numbers",
                     "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]]
                     });*/
                } else {
                    var group_code_id = document.getElementById("group_code0").value;
                    $('#active').html("No Policy defined for code: " + group_code_id);
                }
            }
            ;
            DeletePolicy = function (arg) {
                window.deleteId = arg;
                $('#deleteModal').iziModal('open');
            };

            finishDeleteItem = function () {
                var id = window.deleteId;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/PolicyServlet",
                    data: {option: "<%= Utility.OPTION_DELETE%>", id: id},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        $('#deleteModal').iziModal('close');
                        if (data.indexOf("<%= Utility.ActionResponse.DELETED.toString()%>") !== -1) {
                            toastr["success"]("Policy Delete Successful!", "Record Deleted!!!");
                            getPolicies();
                            ReturnToList();
                        } else {
                            toastr["error"]("Policy Delete Failed!", "No Record Deleted!!!");
                        }
                    },
                    error: function (a, b, c) {
                        //alert(a + "    " + b + "    " + c)
                        $('#deleteModal').iziModal('close');
                        toastr["error"]("Record with id " + id + " is not found!", "Policy Delete Failed!!!");
                    }
                });
            };
            function AddNewPolicyGroup() {
                var group_code = document.getElementById("txtGroupCode").value;
                var error = "";
                var group_name = document.getElementById("txtGroupName").value;

                if (group_code === "") {
                    error += "Policy Code Group can not be blank<br>";
                }
                if (group_name === "") {
                    error += "Policy Name Group can not be blank<br>";
                }

                if (error !== "") {
                    toastr["error"](error, "Please Correct The Following Error(s)!!!");
                    return true;
                }
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/PolicyGroupServlet",
                    data: {option: "<%= Utility.OPTION_INSERT%>", group_code: group_code, group_name: group_name},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        //alert(data);
                        if (data.indexOf("<%= Utility.ActionResponse.RECORD_EXISTS.toString()%>") !== -1) {
                            toastr["error"]("Policy Group already exists!", "Policy Group Creation Failed!!!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {
                            getGroupCode();
                            toastr["success"]("Policy Group record successfully created!", "Policy Group Creation Successfull!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown){
                    error: function () {
                        toastr["error"]("The server is not accessible!", "User Creation Failed!!!");
                    }
                });
                /*                                                        $('#CreateArea').hide();
                 $('#MainArea').fadeIn();
                 $('ul.breadcrumb').html('<li><a onclick=gotoLink("/dashboard00012");><i class="fa fa-home"></i> Home</a></li><li class="active"> User management</li>');
                 */
            }
            ;
            function EditPolicyGroup(idx) {
                //document.getElementById("txtGroupCode").value=null;                                                       
                //document.getElementById("txtGroupName").value=null;
                $("#txtGroupCode").prop('disabled', true);
                var error = "";
                $('#groupcodeid').val(idx);
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/PolicyGroupServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: idx},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            for (var i = 0; i < data.length; i++) {
                                var obj = data[i];
                                for (var key in obj) {
                                    var attrName = key;
                                    var attrValue = obj[key];
                                    if (attrValue === null || attrValue === 'null') {
                                        attrValue = "";
                                    }
                                    if (attrName == "1") {
                                        document.getElementById("txtGroupCode").value = attrValue;
                                    }
                                    if (attrName == "2") {
                                        //document.getElementById("txtGroupName").value = attrValue;
                                        $('#txtGroupName').val(attrValue);
                                    }
                                }
                                break;
                            }
                            $('#groupcodeupdate').prop('disabled', false);
                        }
                    },
                    error: function () {
                        toastr["error"]("The server is not accessible!", "User Creation Failed!!!");
                    }
                });
                /*                                                        $('#CreateArea').hide();
                 $('#MainArea').fadeIn();
                 $('ul.breadcrumb').html('<li><a onclick=gotoLink("/dashboard00012");><i class="fa fa-home"></i> Home</a></li><li class="active"> User management</li>');
                 */
            }
            ;
            var removeWindowId = function () {
                window.deleteId = 0;
            };
            function CreateGroupCode() {
                //alert("I am here");
            }
            ;
            function UpdatePolicyGroup() {
                var id = $('#groupcodeid').val();
                //var id = document.getElementById("txtId_e").value;
                var group_code = document.getElementById("txtGroupCode").value;
                var group_name = document.getElementById("txtGroupName").value;
                var error = "";
                if (group_code === "") {
                    error += "<br>Policy Code must not be blank<br>";
                }
                if (group_name === "") {
                    error += "Policy Group Name must not be blank<br>";
                }

                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/PolicyGroupServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE%>", id: id, group_code: group_code, group_name: group_name},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.indexOf("<%= Utility.ActionResponse.NO_RECORD.toString()%>") !== -1) {
                            toastr["error"]("Policy does not exist!", "Policy Group Edit Failed!!!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.UPDATED.toString()%>") !== -1) {
                            getPolicies();
                            toastr["success"](" Policy Group successfully updated!", "Policy Group Edit Successfull!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown){
                    error: function () {
                        toastr["error"]("The server is not accessible!", "Policy Group Edit Failed!!!");
                    }
                });
                $('#groupcodeupdate').prop('disabled', true);
                $("#txtGroupCode").prop('disabled', false);
                document.getElementById("txtGroupCode").value='';
                document.getElementById("txtGroupName").value='';
                getGroupCode();
            }
            function DeletePolicyGroup(id) {
                $('#groupcodeid').val(id);
                $("#GroupCodeDeleteModal").modal('show');
            }

            function DeleteGroupCode() {
                var id = $('#groupcodeid').val();
                //alert(id);
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/PolicyGroupServlet",
                    data: {option: "<%= Utility.OPTION_DELETE%>", id: id},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        $('#deleteModal').iziModal('close');
                        if (data.indexOf("<%= Utility.ActionResponse.DELETED.toString()%>") !== -1) {
                            toastr["success"]("Policy Group Delete Successful!", "Record Deleted!!!");
                            getPolicies();
                            ReturnToList();
                        } else {
                            toastr["error"]("Policy Group Delete Failed!", "No Record Deleted!!!");
                        }
                        getGroupCode();
                        $("#GroupCodeDeleteModal").modal('hide');
                    },
                    error: function (a, b, c) {
                        toastr["error"]("Record with id " + id + " is not found!", "Policy Delete Failed!!!");
                    }
                });
            }
            ;

        </script>
    </body>
</html>