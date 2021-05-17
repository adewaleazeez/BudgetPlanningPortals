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
        <link rel="stylesheet" href="js/amcharts/plugins/export/export.css" type="text/css" media="all" />
        <!--link href="js/DataTables/datatables.css" rel="stylesheet" /-->

        <!-- ========== THEME CSS ========== -->
        <link rel="stylesheet" href="css/main.css" media="screen">
        <link rel="stylesheet" href="css/jquery.loading.css" media="screen">
        <style>.hover-control{display: inline !important;}</style>
        <!-- ========== MODERNIZR ========== -->
        <script src="js/modernizr/modernizr.min.js"></script>
        <!--script src="js/DataTables/datatables.min.js"></script-->
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
                font-size: 13px;
            }

            tr {
                text-align: left;
            }
            td, th {
                padding: 10px;
                border: 1px solid #ccc;
            }
            body {
                padding: 1rem;
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
            <div class="content-wrapper" style="margin-top: 25px; margin-left: -14px;">
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
                                    <h2 class="title">Yearly Budget Entries</h2>
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
                                        <li class="active">Yearly Budget Entries</li>
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
                                <div class="row">
                                    <div class="col-md-12">

                                        <div class="panel" id="MainSection">

                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h5>Add MDA</h5>
                                                </div>
                                            </div>

                                            <div class="panel-body p-20">
                                                <!-- Nav tabs -->
                                                <div id="list-area">
                                                    <div class="col-md-2">
                                                        <label for="budget_year">Yearly&nbsp;Budget&nbsp;For:</label>
                                                        <select class='js-states form-control custom_select' id='budget_year' disabled></select>
                                                    </div>
                                                    <div class="col-md-8"></div>
                                                    <div class="col-md-2">
                                                        <!--<label for="approvebudget">Approve/Sync Budget</label>--> 
                                                        <a class="btn btn-success toggle-code-handle pull-right" id="ao-approve-budget"
                                                           role="button" onclick="approveBudget()" data-toggle="tooltip" data-placement="top" title="Submit budget for approval">
                                                            <i class="fa fa-refresh"> </i>Approve Budget (Account Officer)
                                                        </a>
                                                        <br/><br/>
                                                        <a class="btn btn-warning toggle-code-handle pull-right" id="admin-unlock-budget"
                                                           role="button" onclick="unlockBudget()" data-toggle="tooltip" data-placement="top" title="Submit budget for approval">
                                                            <i class="fa fa-refresh"> </i>Unlock Budget (System Administrator)
                                                        </a>
                                                        <br/><br/>
                                                        <a class="btn btn-info toggle-code-handle pull-right" id="admin-approve-budget"
                                                           role="button" onclick="submitBudget()" data-toggle="tooltip" data-placement="top" title="Submit budget for approval">
                                                            <i class="fa fa-refresh"> </i>Approve Budget (System Administrator)
                                                        </a>
                                                        <br/><br/>
                                                        <a class="btn btn-success toggle-code-handle pull-right" id="syncdbudget"
                                                           role="button" onclick="SyncBudget()" data-toggle="tooltip" data-placement="top" title="Push approved budget to System">
                                                            <i class="fa fa-refresh"> </i>Sync Approved Budget (System)
                                                        </a>
                                                        <br/><br/>
                                                        <a class="btn btn-success toggle-code-handle pull-right" id="viewenvelope"
                                                           role="button" onclick="viewEnvelope()" data-toggle="tooltip" data-placement="top" title="Click to view envelope for MDA">
                                                            <i class="fa fa-refresh"> </i>View Envelope
                                                        </a>
                                                    </div> 
                                                    <br/>
                                                    <div class="col-md-4">
                                                        <label for="budget_version0">Budget Version</label>
                                                        <select class='js-states form-control custom_select' id='budget_version0' disabled="" bonchange=''></select>
                                                    </div>
                                                    <div class="col-md-4"> 
                                                        <label for="sap_budget_type0">SAP Budget Type</label>
                                                        <select class='js-states form-control custom_select' id='sap_budget_type0' disabled="" onchange=''></select>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label for="currency0">Currency</label>
                                                        <select class='js-states form-control custom_select' id='currency0' onchange=''></select>
                                                    </div>
                                                    <br/>
                                                    <div class="col-md-4">
                                                        <label for="mda_id0">MDA</label>
                                                        <select class='js-states form-control custom_select' id='mda_id0' onchange='resetBudgetHeads();'></select>
                                                    </div>
                                                    <div class="col-md-4"> 
                                                        <label for="budget_type0">Budget Type</label>
                                                        <select class='js-states form-control custom_select' id='budget_type0' onchange='resetBudgetHeads();getBudgetHeads();'></select>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label for="budget_head0">Budget Head</label>
                                                        <select class='js-states form-control custom_select' id='budget_head0' onchange='getYearBudgetEntries(this.id);'></select>
                                                    </div>
                                                    <div class="col-md-4">
                                                        
                                                    </div>
                                                    
                                                </div>
                                            </div>
                                        </div>

                                        <div style='margin-top: -20px'  class="col-md-12 ml-15 mr-15" id='active'></div>                                                        

                                        <div class="modal fade in" id="addModal">
                                            <div class="modal-dialog modal-center modal-md">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="removeWindowId();">
                                                            <span aria-hidden="true" style="font-size:14px">x</span>
                                                        </button>
                                                        <h4 class="modal-title">Add Activities</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="input-group col-lg-12">
                                                            <label for="admin_seg_a">Admin Segment</label>
                                                            <select class='js-states form-control' id='admin_seg_a' disabled="" ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="eco_seg_a">Economic Segment</label>
                                                            <select class='js-states form-control' id='eco_seg_a' ></select>
                                                        </div>
                                                        <!--div class="input-group col-lg-12">
                                                            <label for="prog_seg_a">Programme Segment</label>
                                                            <select class='js-states form-control' id='prog_seg_a' ></select>
                                                        </div-->
                                                        <div class="input-group col-lg-12">
                                                            <label for="func_seg_a">Functional Segment</label>
                                                            <select class='js-states form-control' id='func_seg_a' ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="fund_seg_a">Fund Segment</label>
                                                            <select class='js-states form-control' id='fund_seg_a' ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="geo_seg_a">Geo Segment</label>
                                                            <select class='js-states form-control' id='geo_seg_a' ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="dept_seg_a">Department</label>
                                                            <select class='js-states form-control' id='dept_seg_a' ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="budget_amount_a">Budget Amount</label>
                                                            <input type="text" required class="form-control" onblur="this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');" id="budget_amount_a" style="text-align: right" placeholder="Budget Amount">
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="budget_amount_a">Narration</label>
                                                            <textarea type="text" class="form-control" id="narration_a" placeholder="Budget Narration"></textarea>
                                                        </div>
                                                        <div id="addCap">
                                                            <div class="input-group col-lg-12">
                                                                <label for="p_completed_a">% Completed</label>
                                                                <input type="text" class="form-control" id="p_completed_a" placeholder="Percentage Completed">
                                                            </div>
                                                            <div class="input-group col-lg-12">
                                                                <label for="c_from_a">From</label>
                                                                <select class='js-states form-control' id='c_from_a'><option value=""></option><option value="Jan">Jan</option><option value="Feb">Feb</option><option value="Mar">Mar</option>
                                                                    <option value="Apr">Apr</option><option value="May">May</option><option value="Jun">Jun</option><option value="Jul">Jul</option><option value="Aug">Aug</option>
                                                                    <option value="Sep">Sep</option><option value="Oct">Oct</option><option value="Nov">Nov</option><option value="Dec">Dec</option>
                                                                </select>
                                                            </div>
                                                            <div class="input-group col-lg-12">
                                                                <label for="c_to_a">To</label>
                                                                <select class='js-states form-control' id='c_to_a'><option value=""></option><option value="Jan">Jan</option><option value="Feb">Feb</option><option value="Mar">Mar</option>
                                                                    <option value="Apr">Apr</option><option value="May">May</option><option value="Jun">Jun</option><option value="Jul">Jul</option><option value="Aug">Aug</option>
                                                                    <option value="Sep">Sep</option><option value="Oct">Oct</option><option value="Nov">Nov</option><option value="Dec">Dec</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-success" data-dismiss="modal" id="confirmAdd" onclick="finishAddItem();"><i class='fa fa-save'></i>Save</button>
                                                            <button type="button" class="btn btn-warning" data-dismiss="modal" data-izimodal-close="" onclick="removeWindowId();" id="cancelAdd"><i class='fa fa-remove'></i>Cancel</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="modal fade in" id="editModal">
                                            <div class="modal-dialog modal-center modal-md">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-izimodal-close="" onclick="removeWindowId();">&times;</button>
                                                        <h4 class="modal-title">Edit Record</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <input type="hidden" id="txtId_e" />
                                                        <input type="hidden" id="sap_doc_e" />
                                                        <div class="input-group col-lg-12">
                                                            <label for="admin_seg_e">Admin Segment</label>
                                                            <select class='js-states form-control' id='admin_seg_e' disabled="" ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="eco_seg_e">Economic Segment</label>
                                                            <select class='js-states form-control' id='eco_seg_e' ></select>
                                                        </div>
                                                        <input type="hidden" id="prog_seg_e" />
                                                        <!--div class="input-group col-lg-12">
                                                            <label for="prog_seg_e">Programme Segment</label>
                                                            <select class='js-states form-control' id='prog_seg_e' ></select>
                                                        </div-->
                                                        <div class="input-group col-lg-12">
                                                            <label for="func_seg_e">Functional Segment</label>
                                                            <select class='js-states form-control' id='func_seg_e' ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="fund_seg_e">Fund Segment</label>
                                                            <select class='js-states form-control' id='fund_seg_e' ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="geo_seg_e">Geo Segment</label>
                                                            <select class='js-states form-control' id='geo_seg_e' ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="dept_seg_e">Department</label>
                                                            <select class='js-states form-control' id='dept_seg_e' ></select>
                                                        </div>
                                                        <input type="hidden" id="oldval" />
                                                        <div class="input-group col-lg-12">
                                                            <label for="budget_emount_e">Budget Amount</label>
                                                            <input type="text" required class="form-control" onfocus="keepOldValue(this.value)" onblur="this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');" id="budget_amount_e" style="text-align: right" placeholder="Budget Amount">
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="budget_amount_a">Narration</label>
                                                            <textarea type="text" class="form-control" id="narration_e" placeholder="Budget Narration"></textarea>
                                                        </div>
                                                        <div id="editCap">
                                                            <div class="input-group col-lg-12">
                                                                <label for="p_completed_e">% Completed</label>
                                                                <input type="text" class="form-control" id="p_completed_e" placeholder="Percentage Completed">
                                                            </div>
                                                            <div class="input-group col-lg-12">
                                                                <label for="c_from_e">From</label>
                                                                <select class='js-states form-control' id='c_from_e'><option value=""></option><option value="Jan">Jan</option><option value="Feb">Feb</option><option value="Mar">Mar</option>
                                                                    <option value="Apr">Apr</option><option value="May">May</option><option value="Jun">Jun</option><option value="Jul">Jul</option><option value="Aug">Aug</option>
                                                                    <option value="Sep">Sep</option><option value="Oct">Oct</option><option value="Nov">Nov</option><option value="Dec">Dec</option>
                                                                </select>
                                                            </div>
                                                            <div class="input-group col-lg-12">
                                                                <label for="c_to_e">To</label>
                                                                <select class='js-states form-control' id='c_to_e'><option value=""></option><option value="Jan">Jan</option><option value="Feb">Feb</option><option value="Mar">Mar</option>
                                                                    <option value="Apr">Apr</option><option value="May">May</option><option value="Jun">Jun</option><option value="Jul">Jul</option><option value="Aug">Aug</option>
                                                                    <option value="Sep">Sep</option><option value="Oct">Oct</option><option value="Nov">Nov</option><option value="Dec">Dec</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-success" data-dismiss="modal" id="confirmEdit" onclick="FinishEdit();"><i class='fa fa-save'></i>Update</button>
                                                            <button type="button" class="btn btn-warning" data-dismiss="modal" data-izimodal-close="" onclick="removeWindowId();" id="cancelAdd"><i class='fa fa-remove'></i>Cancel</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="modal fade in" id="deleteModal">
                                            <div class="modal-dialog modal-center modal-md">
                                                <div class="modal-content">

                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-izimodal-close="" onclick="removeWindowId();">&times;</button>
                                                        <h4 class="modal-title">Delete Record</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <input type="hidden" id="txtId_d" />
                                                        <div class="input-group col-lg-12">
                                                            <label for="admin_seg_d">Admin Segment</label>
                                                            <select class='js-states form-control' id='admin_seg_d' disabled="" ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="eco_seg_d">Economic Segment</label>
                                                            <select class='js-states form-control' id='eco_seg_d' disabled="" ></select>
                                                        </div>
                                                        <input type="hidden" id="prog_seg_d" />
                                                        <!--div class="input-group col-lg-12">
                                                            <label for="prog_seg_d">Programme Segment</label>
                                                            <select class='js-states form-control' id='prog_seg_d' disabled="" ></select>
                                                        </div-->
                                                        <div class="input-group col-lg-12">
                                                            <label for="func_seg_d">Functional Segment</label>
                                                            <select class='js-states form-control' id='func_seg_d' disabled="" ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="fund_seg_d">Fund Segment</label>
                                                            <select class='js-states form-control' id='fund_seg_d' disabled="" ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="geo_seg_d">Geo Segment</label>
                                                            <select class='js-states form-control' id='geo_seg_d' disabled="" ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="dept_seg_d">Department</label>
                                                            <select class='js-states form-control' id='dept_seg_d' disabled="" ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="budget_emount_d">Budget Amount</label>
                                                            <input type="text" required class="form-control" disabled="" onblur="this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');" id="budget_amount_d" style="text-align: right" placeholder="Budget Amount">
                                                        </div>
                                                        <div id="delCap">
                                                            <div class="input-group col-lg-12">
                                                                <label for="p_completed_d">% Completed</label>
                                                                <input type="text" class="form-control" disabled="" id="p_completed_d" placeholder="Percentage Completed">
                                                            </div>
                                                            <div class="input-group col-lg-12">
                                                                <label for="c_from_d">From</label>
                                                                <select class='js-states form-control' disabled="" id='c_from_d'><option value=""></option><option value="Jan">Jan</option><option value="Feb">Feb</option><option value="Mar">Mar</option>
                                                                    <option value="Apr">Apr</option><option value="May">May</option><option value="Jun">Jun</option><option value="Jul">Jul</option><option value="Aug">Aug</option>
                                                                    <option value="Sep">Sep</option><option value="Oct">Oct</option><option value="Nov">Nov</option><option value="Dec">Dec</option>
                                                                </select>
                                                            </div>
                                                            <div class="input-group col-lg-12">
                                                                <label for="c_to_d">To</label>
                                                                <select class='js-states form-control' disabled="" id='c_to_d'><option value=""></option><option value="Jan">Jan</option><option value="Feb">Feb</option><option value="Mar">Mar</option>
                                                                    <option value="Apr">Apr</option><option value="May">May</option><option value="Jun">Jun</option><option value="Jul">Jul</option><option value="Aug">Aug</option>
                                                                    <option value="Sep">Sep</option><option value="Oct">Oct</option><option value="Nov">Nov</option><option value="Dec">Dec</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-danger" data-dismiss="modal" id="confirmDelete" onclick="finishDeleteItem();"><i class='fa fa-recycle'></i>Delete</button>
                                                            <button type="button" class="btn btn-warning" data-dismiss="modal" data-izimodal-close="" onclick="removeWindowId();" id="cancel"><i class='fa fa-remove'></i>Cancel</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                    </div>

                                </div>
                                <!-- /.col-md-12 -->

                            </div>
                            <!-- /.row -->

                            <!--</div>-->
                            <!-- /.container-fluid -->
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
        <script type="text/javascript" src="js/jspdf.js"></script>
        <!-- ========== ADD custom.js FILE BELOW WITH YOUR CHANGES ========== -->

        <script type="text/javascript">
            checkLogin();
            $(document).ready(function () {
                //$('#deleteModal').modal("hide");
                //$('#addModal').modal("hide");
                //$('#editModal').modal("hide");

                //Close side-menu
                $('#nav-togglerx').click();

                //getSectors();
                var user_role = '<%= session.getAttribute("userrole")%>';
                if (user_role.indexOf("Account Officer") !== -1) {
                    $("#syncdbudget").hide();
                    $("#ao-approve-budget").show();
                }else{
                    $("#syncdbudget").hide();
                    $("#ao-approve-budget").hide();
                }
                setButtons();
                //getMDAS('sector_id0');
                $("#mda_id0, #admin_seg_a, #eco_seg_a, #fund_seg_a, #func_seg_a, #geo_seg_a, #dept_seg_a").select2(); //#prog_seg_a, 
                $("#budget_version0, #sap_budget_type0, #budget_type0, #currency0").select2();
                $("#admin_seg_e, #eco_seg_e, #fund_seg_e, #func_seg_e, #geo_seg_e, #dept_seg_e").select2(); //#prog_seg_e, 
                $("#admin_seg_d, #eco_seg_d, #fund_seg_d, #func_seg_d, #geo_seg_d, #dept_seg_d").select2(); //#prog_seg_d, 
            });
            
            function setButtons() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BUDGET_VERSION%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function(data){
                        //console.log(data);
                        if (data.length > 0) {
                            var status="";
                            for (var i = 0; i < data.length; i++) {
                                var obj = data[i];
                                for (var key in obj) {
                                    var attrName = key;
                                    var attrValue = obj[key];
                                    attrValue = "" + attrValue;
                                    if (attrName === "0") {
                                        status = attrValue;
                                    }
                                }
                            }
                            var user_role = '<%= session.getAttribute("userrole")%>';
                            if (user_role.indexOf("System Administrator") !== -1) {
                                if(status==="selected"){
                                    $("#syncdbudget").show();
                                    $("#admin-approve-budget").hide();
                                    $("#admin-unlock-budget").hide();
                                }else{
                                    $("#syncdbudget").hide();
                                    $("#admin-approve-budget").show();
                                    $("#admin-unlock-budget").show();
                                }
                            }else{
                                $("#syncdbudget").hide();
                                $("#admin-approve-budget").hide();
                                $("#admin-unlock-budget").hide();
                            }
                        }
                    },
                    error: function () {
                        toastr["error"]("Version Selection Failed!", "No record selected!!!");
                    }
                });
                getBudgetVersions();
            }
            
            function getBudgetVersions() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BUDGET_VERSION%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: budgetVersionsReturnValues,
                    error: function () {
                        toastr["error"]("Version Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function budgetVersionsReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option " + attrValue;
                            }
                            if (attrName === "1") {
                                resp += " value='" + attrValue + "'>";
                            }
                            if (attrName === "2") {
                                resp += attrValue + " VERSION</option>";
                            }
                        }
                    }
                    //console.log(resp);
                    $('#budget_version0').html(resp);
                    // sap_budget_type0 currency0
                }
                getSapBudgetTypes();
            }
             
            function getSapBudgetTypes() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_SAP_BUDGET_TYPE%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: sapBudgetTypesReturnValues,
                    error: function () {
                        toastr["error"]("Version Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function sapBudgetTypesReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    $('#sap_budget_type0').html(resp);
                    //  currency0 getCurrencies
                }
                getBudgetTypes();
            }
             
            function getBudgetTypes() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BUDGET_TYPE%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: budgetTypesReturnValues,
                    error: function () {
                        toastr["error"]("Version Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function budgetTypesReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    $('#budget_type0').html(resp);
                    //  currency0 getCurrencies
                }
                getCurrencies();
            }
            
            function getCurrencies() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BUDGET_CURRENCY%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: currenciesReturnValues,
                    error: function () {
                        toastr["error"]("cURRENCY Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function currenciesReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "2") {
                                resp += attrValue;
                            }
                            if (attrName === "4") {
                                attrValue = parseFloat(attrValue);
                                attrValue = attrValue.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                resp += " [" + attrValue + "]</option>";
                            }
                        }
                    }
                    $('#currency0').html(resp);
                }
                getBudgetYear();
            }

            $("#addCap").hide();
            $('#editCap').hide();
            $('#delCap').hide();

            function keepOldValue(oldval) {
                $("#oldval").val(oldval);
            }
            function getBudgetYear() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MDACeilingServlet",
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
            var currentyear_id = null;
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
                                currentyear_id = attrValue;
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
                getMDAS();
                //resetBudgetVersion(currentyear);
            };
            
            function getMDAS() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_NAME%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: mdaReturnValues,
                    error: function () {
                        toastr["error"]("MDA Selection Failed!", "No record selected!!!");
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
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    if(data.length > 1){
                        $('#mda_id0').html("<option value='0'></option>" + resp);
                        $('#admin_seg_a').html(resp);
                        $('#admin_seg_e').html(resp);
                        $('#admin_seg_d').html(resp);
                    }else{
                        $('#mda_id0').html(resp);
                        $('#admin_seg_a').html(resp);
                        $('#admin_seg_e').html(resp);
                        $('#admin_seg_d').html(resp);
                        $('#mda_id0').attr("disabled", true);
                        $('#admin_seg_a').attr("disabled", true);
                        $('#admin_seg_e').attr("disabled", true);
                        $('#admin_seg_d').attr("disabled", true);
                    }
                }
                getBudgetHeads();
            }

            function getBudgetHeads() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT%>", budget_type_id: $('#budget_type0').val()},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: budgetHeadsReturnValues,
                    error: function () {
                        toastr["error"]("Budget Heads Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function budgetHeadsReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    $('#budget_head0').html("<option value='0'></option>" + resp);
                }

            }

            function resetBudgetHeads() {
                $('#budget_head0').val('0').trigger('change');
                $('#active').html("");
            }

            var mda_id = null;
            var budget_head_id = null;
            var budget_head_name = null;
            var gl_narration = null;
            var gl_code = null;
            var budget_envelope = null;
            var budget_year_id = null;
            
            var search_string = null;
            function textSearch(){
                search_string = $('#search_text').val();
                getYearBudgetEntries("budget_head0"); 
            }
            function getYearBudgetEntries(id) {
                var budget_head_name = $('#' + id + " option:selected").text();
                id = $('#' + id).val();
                if (id === '0') {
                    return true;
                }
                budget_head_id = id;
                mda_id = $('#mda_id0 option:selected').val();

                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: id, admin_segment: mda_id},
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
                                    attrValue = "" + attrValue;
                                    if (attrName === "0") {
                                        gl_code = budget_head_name;
                                        gl_code1 = gl_code.split("[");
                                        gl_code2 = gl_code1[1].split("]");
                                        gl_code = gl_code2[0];
                                    }
                                    if (attrName === "1") {
                                        gl_narration = attrValue;
                                    }
                                    if (attrName === "2") {
                                        budget_envelope = attrValue;
                                    }
                                    if (attrName === "3") {
                                        budget_year_id = attrValue;
                                    }
                                }
                                break;
                            }
                            getProgSegment();
                        }
                    },
                    error: function () {
                        toastr["error"]("Record not found!", "No Record Found!!!");
                    }
                });
                var admin_seg = $('#mda_id0 option:selected').text();
                admin_seg1 = admin_seg.split("[");
                admin_seg2 = admin_seg1[1].split("]");
                admin_seg = admin_seg2[0];

                if (budget_head_name.indexOf("Capital") !== -1) {
                    //alert(admin_seg+"    "+gl_code)
                    var sap_budget_type = $('#sap_budget_type0 option:selected').val();
                    $.ajax({
                        type: "GET",
                        url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                        data: {option: "<%= Utility.OPTION_SELECT_ALL%>", admin_segment: admin_seg, programme_segment: gl_code, budget_year_id: budget_year_id, narration: search_string, budget_type_id: sap_budget_type},
                        dataType: "json",
                        cache: false,
                        async: false,
                        success: capitalYearBudgetReturnValues,
                        error: function () {
                            toastr["error"]("Record Selection Failed!", "No record selected!!!");
                        }
                    });
                } else {
                    $.ajax({
                        type: "GET",
                        url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                        data: {option: "<%= Utility.OPTION_SELECT_ALL%>", admin_segment: admin_seg, economic_segment: gl_code, budget_year_id: budget_year_id, narration: search_string},
                        dataType: "json",
                        cache: false,
                        async: false,
                        success: yearBudgetReturnValues,
                        error: function () {
                            toastr["error"]("Record Selection Failed!", "No record selected!!!");
                        }
                    });
                }
            }

            function getProgSegment() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", programme_segment: "1"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: progSegReturnValues,
                    error: function () {
                        toastr["error"]("Programme Segment Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function progSegReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    $('#prog_seg_a').html("<option value='0'></option>" + resp);
                    $('#prog_seg_e').html("<option value='0'></option>" + resp);
                    $('#prog_seg_d').html("<option value='0'></option>" + resp);
                }
                getEconomicSegment();
            }

            function getEconomicSegment() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", economic_segment: gl_code},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: ecoSegReturnValues,
                    error: function () {
                        toastr["error"]("Economic Segment Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function ecoSegReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    $('#eco_seg_a').html("<option value='0'></option>" + resp);
                    $('#eco_seg_e').html("<option value='0'></option>" + resp);
                    $('#eco_seg_d').html("<option value='0'></option>" + resp);
                }
                getFuncSegment();
            }

            function getFuncSegment() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", functional_segment: "1"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: funcSegReturnValues,
                    error: function () {
                        toastr["error"]("Functional Segment Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function funcSegReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    $('#func_seg_a').html("<option value='0'></option>" + resp);
                    $('#func_seg_e').html("<option value='0'></option>" + resp);
                    $('#func_seg_d').html("<option value='0'></option>" + resp);
                }
                getFundSegment();
            }

            function getFundSegment() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", fund_segment: "1"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: fundSegReturnValues,
                    error: function () {
                        toastr["error"]("Fund Segment Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function fundSegReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    $('#fund_seg_a').html("<option value='0'></option>" + resp);
                    $('#fund_seg_e').html("<option value='0'></option>" + resp);
                    $('#fund_seg_d').html("<option value='0'></option>" + resp);
                }
                getGeoSegment();
            }

            function getGeoSegment() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", geo_segment: "1"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: geoSegReturnValues,
                    error: function () {
                        toastr["error"]("Geographic Segment Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function geoSegReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    $('#geo_seg_a').html("<option value='0'></option>" + resp);
                    $('#geo_seg_e').html("<option value='0'></option>" + resp);
                    $('#geo_seg_d').html("<option value='0'></option>" + resp);
                }
                getDept();
            }

            function getDept() {
                var mda_id = $('#mda_id0 option:selected').val();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", dept_id: mda_id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: deptReturnValues,
                    error: function () {
                        toastr["error"]("Geographic Segment Selection Failed!", "No record selected!!!");
                    }
                });
            }
            function deptReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    $('#dept_seg_a').html("<option value='0'></option>" + resp);
                    $('#dept_seg_e').html("<option value='0'></option>" + resp);
                    $('#dept_seg_d').html("<option value='0'></option>" + resp);
                }
            }

            var approval=null;
            function capitalYearBudgetReturnValues(data) {
                search_string = null;
                //alert(data);
                //var balance = 0.0;
                //balance = balance.toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                budget_envelope = parseFloat(budget_envelope).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                var balance = budget_envelope;
                var resp = "<div style='margin-top: 10px;'  class='col-md-12 ml-45 mr-45'>" +
                        "<div class='col-md-7'>" +
                        "<label>Search Text:</label>" + 
                        "<input style='display: inline; width: 600px;' id='search_text' type='text'>&nbsp;&nbsp;&nbsp;<input style='display: inline;' type='button' value='Search' onclick='textSearch()'>" +
                        "</div>" +
                        "<div class='col-md-2'>" +
                        "<label>Envelope Amount:</label>" +
                        "<input id='budget_envelope' type='text' disabled style='text-align: right'>" +
                        "</div>" +
                        "<div class='col-md-2'>" +
                        "<label>Amount Left:</label>" +
                        "<input id='balance' type='text' disabled style='text-align: right'>" +
                        "</div>" +
                        "</div>" +
                        "<br style='clear: both;' />" +
                        "<div class='bs-example'>" +
                        "<div class='panel-group'>" +
                        "<div class='panel panel-default'>" +
                        "<div class='panel-heading'>" +
                        "<h4 class='panel-title'>" +
                        "<table border='0'>" +
                        "<tr style='background-color: #00ffcc; font-size: 20px;'>" +
                        "<td>" +
                        "<a data-toggle='collapse' data-parent='#accordion' href='#collapseOne'><span class='glyphicon glyphicon-plus'></span> EXISTING PROJECTS</a>" +
                        "</td>" +
                        "<td align='right'>" +
                        "<input type='text' id='oldproj' disabled style='text-align: right; display: inline'>" +
                        "</td>" +
                        "</tr>" +
                        "</table>" +
                        "</h4>" +
                        "</div>" +
                        "<div id='collapseOne' class='panel-collapse collapse in'>" +
                        "<div class='panel-body' id='oldproject'>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "<div class='panel panel-default'>" +
                        "<div class='panel-heading'>" +
                        "<h4 class='panel-title'>" +
                        "<table border='0'>" +
                        "<tr style='background-color: #00ffcc; font-size: 20px;'>" +
                        "<td>" +
                        "<a data-toggle='collapse' data-parent='#accordion' href='#collapseTwo'><span class='glyphicon glyphicon-plus'></span> NEW PROJECTS</a>" +
                        "</td>" +
                        "<td align='right'>" +
                        "<input type='text' id='newproj' disabled style='text-align: right; display: inline'>" +
                        "</td>" +
                        "</tr>" +
                        "</table>" +
                        "</h4>" +
                        "</div>" +
                        "<div id='collapseTwo' class='panel-collapse collapse in'>" +
                        "<div class='panel-body' id='newproject'>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>";
                var total1 = 0.0;
                var total2 = 0.0;
                var total3 = 0.0;
                var total4 = 0.0;
                $('#active').html(resp);
                $("#budget_envelope").val(budget_envelope);
                $("#balance").val(balance);
                var resp = "";
                var oldproject = "";
                var newproject = "";
                var id = 0;
                var code = "";
                var current_code = "";
                var name = "";
                var narration = "";
                var amt1 = 0;
                var amt2 = 0;
                var amt3 = 0;
                var amt4 = 0;
                var year_id = null;
                var old_year_id = null;
                var c_perc = "";
                var c_from = "";
                var c_to = "";
                var total_concat = "";
                var oldproj = 0;
                var newproj = 0;
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                id = attrValue;
                            }
                            if (attrName === "1") {
                                code = attrValue;
                            }
                            if (attrName === "2") {
                                name = attrValue;
                            }
                            if (attrName === "3") {
                                amt1 = parseFloat(attrValue);
                            }
                            if (attrName === "4") {
                                amt2 = parseFloat(attrValue);
                            }
                            if (attrName === "5") {
                                amt3 = parseFloat(attrValue);
                            }
                            if (attrName === "6") {
                                amt4 = parseFloat(attrValue);
                            }
                            if (attrName === "7") {
                                year_id = parseInt(attrValue);
                            }
                            if (attrName === "9") {
                                c_perc = attrValue;
                            }
                            if (attrName === "10") {
                                c_from = attrValue;
                            }
                            if (attrName === "11") {
                                c_to = attrValue;
                            }
                            if (attrName === "12") {
                                narration = attrValue;
                                if(narration===""){
                                    narration = name;
                                }
                            }
                            if (attrName === "13") {
                                if(approval===null){
                                    approval = attrValue;
                                }
                            }
                            
                        }
                        if (code.length === 12 && code !== current_code) {
                            if (current_code !== "") {
                                balance = parseFloat(balance.replace(/,/g, ""));
                                balance -= total4;
                                if (old_year_id < currentyear_id) {
                                    oldproj += total4;
                                } else {
                                    newproj += total4;
                                }
                                total1 = total1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                total2 = total2.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                total3 = total3.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                total4 = total4.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                balance = balance.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                resp += "</tr><tr>" +
                                        "<td colspan='3' align='right'>TOTAL</td>" +
                                        "<td align='right'>" + total1 + "</td>" +
                                        "<td align='right'>" + total2 + "</td>" +
                                        "<td align='right'>" + total3 + "</td>" +
                                        "<td align='right'>" + total4 + "</td>" +
                                        "</tr></tr></table>" +
                                        "</div>" +
                                        "</div>" +
                                        "</div>" +
                                        "</div>" +
                                        "</div>";
                                $("#balance").val(balance);
                                if (old_year_id < currentyear_id) {
                                    oldproject += resp;
                                } else {
                                    newproject += resp;
                                }
                                total_concat += current_code + "!" + total4 + "|";
                            }
                            current_code = code;
                            old_year_id = year_id;
                            var counter = 0;
                            total1 = 0.0;
                            total2 = 0.0;
                            total3 = 0.0;
                            total4 = 0.0;

                            resp = "<div class='bs-example'>" +
                                    "<div class='panel-group'>" +
                                    "<div class='panel panel-default'>" +
                                    "<div class='panel-heading'>" +
                                    "<h4 class='panel-title'>" +
                                    "<table border='0'>" +
                                    "<tr style='background-color: #0099ff;'>" +
                                    "<td>" +
                                    "<a data-toggle='collapse' data-parent='#accordion' href='#" + code + "'><span class='glyphicon glyphicon-plus'></span>" + name + " [" + code + "]</a>" +
                                    "</td>" +
                                    "<td align='right'>" +
                                    "<input type='text' id='f_" + code + "' disabled style='text-align: right; display: inline'>" +
                                    "</td>" +
                                    "</tr>" +
                                    "</table>" +
                                    "</h4>" +
                                    "</div>" +
                                    "<div id='" + code + "' class='panel-collapse collapse in'>" +
                                    "<div class='panel-body'>" +
                                    "<table id='main-table' class='table table-clean table-striped' border='0'>" +
                                    "<tr>" +
                                    "<td></td><td><b>Programme Segment</b></td><td></td>" +
                                    "<td colspan='2' align='center'><b>Actual Expenditure</b></td>" +
                                    "<td colspan='2' align='center'><b>Approved Budget</b></td>" +
                                    "<td colspan='5'></td>" +
                                    "</tr>" +
                                    "<tr>" +
                                    "<td><b>S/No</b></td><td><b>" + code + "</b></td>" +
                                    "<td><b>" + narration + "</b></td>" +
                                    "<td align='center'><b>Jan - Dec " + (budget_year_id - 2) + "</b></td>" +
                                    "<td align='center'><b>Jan - To Date " + (budget_year_id - 1) + "</b></td>" +
                                    "<td align='center'><b>" + (budget_year_id - 1) + "</b></td>" +
                                    "<td align='center'><b>" + (budget_year_id) + "</b></td>" +
                                    "<td colspan='3' align='center'><b>%&nbsp;Completed/Period</b></td>" +
                                    "<td colspan='2'><a class='btn btn-info btn-fig toggle-code-handle pull-right newpolicy' onclick=addItem('" + code + "') role='button'><i class='fa fa-user-plus'> </i>Add Record</a></td>" +
                                    "</tr>";
                        } else {
                            total1 += amt1;
                            total2 += amt2;
                            total3 += amt3;
                            total4 += amt4;
                            amt1 = parseFloat(amt1).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            amt2 = parseFloat(amt2).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            amt3 = parseFloat(amt3).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            amt4 = parseFloat(amt4).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            resp += "<tr>" +
                                    "<td align='right'>" + (++counter) + ".</td>" +
                                    "<td>" + code + "</td><td>" + narration + "</td>" +
                                    "<td align='right'>" + amt1 + "</td>" +
                                    "<td align='right'>" + amt2 + "</td>" +
                                    "<td align='right'>" + amt3 + "</td>" +
                                    "<td align='right'>" + amt4 + "</td>" +
                                    "<td align='center'>" + c_perc + "</td>" +
                                    "<td colspan='2' align='center'>" + c_from + " - " + c_to + "</td>";
                            //alert(amt4+"  "+typeof(amt4));
                            if (amt4 === "0.00") {
                                resp += "<td><a class='btn btn-info toggle-code-handle btn-fig pull-right newpolicy' onclick=EditItem('" + id + "','0') role='button'><i class='fa fa-pencil'></i>Add</a></td>";
                                resp += "<td><a class='btn toggle-code-handle btn-fig pull-right newpolicy' onclick='' role='button'><i class='fa fa-remove'></i>Del</a></td></tr>";
                            } else {
                                resp += "<td><a class='btn btn-success toggle-code-handle btn-fig pull-right newpolicy' onclick=EditItem('" + id + "','1') role='button'><i class='fa fa-pencil'></i>Edit</a></td>";
                                resp += "<td><a class='btn btn-danger toggle-code-handle btn-fig pull-right newpolicy' onclick=deleteItem('" + id + "') role='button'><i class='fa fa-remove'></i>Del</a></td></tr>";
                            }
                        }
                    }
                }

                balance = parseFloat(balance.replace(/,/g, ""));
                balance -= total4;
                if (old_year_id === 0) {
                    oldproj += total4;
                } else {
                    newproj += total4;
                }
                oldproj = oldproj.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                newproj = newproj.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                total1 = total1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                total2 = total2.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                total3 = total3.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                total4 = total4.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                balance = balance.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                resp += "</tr><tr>" +
                        "<td colspan='3' align='right'>TOTAL</td><td align='right'>" + total1 + "</td>" +
                        "<td align='right'>" + total2 + "</td>" +
                        "<td align='right'>" + total3 + "</td>" +
                        "<td align='right'>" + total4 + "</td>" +
                        "</tr></tr></table>";
                $("#balance").val(balance);

                if (year_id < currentyear_id) {
                    oldproject += resp;
                } else {
                    newproject += resp;
                }
                //alert(resp);
                $('#oldproject').html(oldproject);
                $('#newproject').html(newproject);
                $("#f_" + current_code).val(total4);
                $("#newproj").val(newproj);
                $("#oldproj").val(oldproj);
                oldproj += total4;
                var total_concat_array = total_concat.split("|");
                for (var count = 0; count < total_concat_array.length; count++) {
                    var totals = total_concat_array[count].split("!");
                    $("#f_" + totals[0]).val(totals[1]);
                }
                checkApproval();
            }

            function checkApproval() {
                if(approval==="Approved"){
                    $('.btn-fig').each(function () {
                        $(this).attr("disabled", 'disabled');
                        $(this).attr('onClick','alert("Function Locked, Contact The Admministrator!!!"); return true;');
//                        if($(this).attr('id').includes("description")){
//                            if($("#"+$(this).attr('id')).val()===""){
//                                error += "Description must not be blank<br>";
//                            }
//                            descriptionArray.push($("#"+$(this).attr('id')).val());
//                        }
                    });
                }
            }
            
            function yearBudgetReturnValues(data) {
                //alert(data);
                //var balance = 0.0;
                //balance = balance.toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                budget_envelope = parseFloat(budget_envelope).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                if(isNaN(budget_envelope)){
                    budget_envelope = "0.00";
                }
                
                var balance = budget_envelope;
                var resp = "<table id='main-table' class='table table-clean table-striped' border='0'><thead><tr><td rowspan='2' colspan='3'>"+
                        "<label>Search Text:</label>" + 
                        "<input style='display: inline; width: 400px;' id='search_text' type='text'>&nbsp;&nbsp;&nbsp;<input style='display: inline;' type='button' value='Search' onclick='textSearch()'>" +
                        "</td><td colspan='2' align='right'><b>Envelope Amount:<input type='text' disabled style='text-align: right' value='" + budget_envelope + "'></b></td><td colspan='2' align='right'><b>Amount Left:<input id='balance' type='text' disabled style='text-align: right' value='" + balance + "'></b></td><td colspan='2'></td></tr></thead><tbody>";
                resp += "<tr><td colspan='3'></td><td colspan='2' align='center'><b>Actual Expenditure</b></td><td colspan='2' align='center'><b>Approved Budget</b></td><td colspan='2'></td></tr>";
                resp += "<tr><td><b>S/No</b></td><td><b>Economic Segment</b></td><td><b>" + gl_narration + " (" + gl_code + ")</b></td><td align='center'><b>Jan - Dec " + (budget_year_id - 2) + "</b></td><td align='center'><b>Jan - To Date " + (budget_year_id - 1) + "</b></td><td align='center'><b>" + (budget_year_id - 1) + "</b></td><td align='center'><b>" + (budget_year_id) + "</b></td><td colspan='2'><a class='btn btn-info toggle-code-handle btn-fig pull-right newpolicy' onclick='addItem();' role='button'><i class='fa fa-user-plus'> </i>Add Record</a></td></tr>";
                var total1 = 0.0;
                var total2 = 0.0;
                var total3 = 0.0;
                var total4 = 0.0;
                if (data.length > 0) {
                    //alert(data);
                    var counter = 0;

                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var id = "";
                        var disability = false;
                        var noprint=1;
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                id = attrValue;
                            }
                            if ((attrName === "3" || attrName === "4" || attrName === "5" || attrName === "6") && parseFloat(attrValue)>=0.0){
                                noprint=0;
                            }
                        }
                                
                        if(noprint===0){        
                            for (var key in obj) {
                                var attrName = key;
                                var attrValue = obj[key];
                                attrValue = "" + attrValue;
                                if (attrName === "0") {
                                    id = attrValue;
                                }
                                if (attrName === "1") {
                                    resp += "<tr><td align='right'>" + (++counter) + ".</td><td>" + attrValue + "</td>";
                                }
                                if (attrName === "2") {
                                    resp += "<td>" + attrValue + "</td>";
                                }
                                if (attrName === "3") {
                                    total1 += parseFloat(attrValue);
                                }
                                if (attrName === "4") {
                                    total2 += parseFloat(attrValue);
                                }
                                if (attrName === "5") {
                                    total3 += parseFloat(attrValue);
                                }
                                if (attrName === "6") {
                                    total4 += parseFloat(attrValue);
                                    if (attrValue < "0") {
                                        disability = true;
                                    }
                                }
                                if (attrName >= "3" && attrName <= "6") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    resp += "<td align='right'>" + attrValue + "</td>";
                                }
                                if (attrName === "7") {
                                    //if(approval===null){
                                        approval = attrValue;
                                    //}
                                }
                                if (attrName === "6") {
                                    if (disability) {
                                        resp += "<td><a class='btn btn-info toggle-code-handle btn-fig pull-right newpolicy' onclick=EditItem('" + id + "','0') role='button'><i class='fa fa-pencil'></i>Edit</a></td>";
                                        resp += "<td><a class='btn toggle-code-handle btn-fig pull-right newpolicy' onclick='' role='button'><i class='fa fa-remove'></i>Del</a></td>";
                                    } else {
                                        resp += "<td><a class='btn btn-success toggle-code-handle btn-fig pull-right newpolicy' onclick=EditItem('" + id + "','1') role='button'><i class='fa fa-pencil'></i>Edit</a></td>";
                                        resp += "<td><a class='btn btn-warning toggle-code-handle btn-fig pull-right newpolicy' onclick=deleteItem('" + id + "') role='button'><i class='fa fa-remove'></i>Del</a></td>";
                                    }
                                }
                            }
                        }
                    }
                }
                balance = parseFloat(balance.replace(/,/g, ""));
                balance -= total4;
                total1 = total1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                total2 = total2.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                total3 = total3.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                total4 = total4.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                balance = balance.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                resp += "<tr><td colspan='3' align='right'>TOTAL</td><td align='right'>" + total1 + "</td><td align='right'>" + total2 + "</td><td align='right'>" + total3 + "</td><td align='right'>" + total4 + "</td></tr>";
                resp += "</tr></tbody></table>";
                $('#active').html(resp);
                if(parseFloat(balance)<0.0){
                    balance = "0.00";
                }
                $("#balance").val(balance);
                checkApproval();
            }
            var rec_status;
            EditItem = function (arg,status) {
                rec_status = status;
                $("#confirmEdit").prop("disabled",false);
                
                var budget_head_name = $('#budget_head0 option:selected').text();
                if (budget_head_name.indexOf("Capital") !== -1) {
                    $("#editCap").show();
                }
                $('#editModal').modal('show');
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID%>", id: arg},
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
                                    if (attrName === "0") {
                                        document.getElementById("txtId_e").value = attrValue;
                                    }
                                    if (attrName === "1") {
                                        $('#admin_seg_e').val($('#mda_id0').val()).trigger('change');
                                    }
                                    if (attrName === "2") {
                                        //$('#prog_seg_e').val(attrValue).trigger('change');
                                        $('#prog_seg_e').val(attrValue); //.trigger('change');
                                        prog_seg_g = attrValue;
                                    }
                                    if (attrName === "3") {
                                        $('#eco_seg_e').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "4") {
                                        $('#func_seg_e').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "5") {
                                        $('#fund_seg_e').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "6") {
                                        $('#geo_seg_e').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "11") {
                                        $('#dept_seg_e').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "8") {
                                        attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                        $('#budget_amount_e').val(attrValue);
                                    }
                                    if (attrName === "15") {
                                        $('#sap_doc_e').val(attrValue);
                                    }
                                    if (attrName === "18") {
                                        $('#narration_e').val(attrValue);
                                    }
                                }
                                break;
                            }
                            $("#admin_seg_e").prop("disabled",true);
                            $("#prog_seg_e").prop("disabled",true);
                            $("#eco_seg_e").prop("disabled",true);
                            $("#func_seg_e").prop("disabled",true);
                            $("#fund_seg_e").prop("disabled",true);
                            $("#geo_seg_e").prop("disabled",true);
                            $("#dept_seg_e").prop("disabled",true);
                            $("#budget_amount_e").prop("disabled",false);
                            $("#narration_e").prop("disabled",false);
                            if(status==="0"){
                                $('#budget_amount_e').val("");  
                                $('#narration_e').val("");
                                $('#p_completed_e').val("");
                                $('#c_from_e').val("");
                                $('#c_to_e').val("");
                                $("#confirmEdit").html("Save");
                            }else{
                                $("#confirmEdit").html("Update");
                            }
                            toastr["success"]("Record successfully fetched!", "Record Selection Successfull!!!");
                            //$('#list-area').hide();
                            //$('#EditSection').fadeIn();
                            //document.getElementById("sector_id2").focus();
                        }
                    },
                    error: function () {
                        toastr["error"]("Record not found!", "MDA Selection Failed!!!");
                    }
                });
            };


            FinishEdit = function () {
                var sap_doc_e = document.getElementById("sap_doc_e").value;
                var id = document.getElementById("txtId_e").value;
                var admin_seg = $('#admin_seg_e option:selected').text();
                admin_seg1 = admin_seg.split("[");
                admin_seg2 = admin_seg1[1].split("]");
                admin_seg = admin_seg2[0];
                var eco_seg = $('#eco_seg_e option:selected').val();
                var prog_seg = '99999999999999'; //$('#prog_seg_a option:selected').val();
                if (prog_seg_g !== null) {
                    prog_seg = prog_seg_g;
                }
                var func_seg = $('#func_seg_e option:selected').val();
                var fund_seg = $('#fund_seg_e option:selected').val();
                var geo_seg = $('#geo_seg_e option:selected').val();
                var dept_seg = $('#dept_seg_e option:selected').val();
                if (dept_seg === undefined) {
                    dept_seg = "";
                }
                var budget_amount = $('#budget_amount_e').val().replace(/,/g, "");
                var oldval = $("#oldval").val().replace(/,/g, "");
                var bal = $("#balance").val().replace(/,/g, "");
                var newbal = parseFloat(bal) + parseFloat(oldval) - parseFloat(budget_amount);
                var error = "";
                if (admin_seg === "") {
                    error += "Admin Segment must not be blank<br>";
                }
                if (eco_seg === "") {
                    error += "Economic Segment must not be blank<br>";
                }
                if (prog_seg === "") {
                    error += "Programme Segment must not be blank<br>";
                }
                if (func_seg === "") {
                    error += "Functional Segment must not be blank<br>";
                }
                if (fund_seg === "") {
                    error += "Fund Segment must not be blank<br>";
                }
                if (geo_seg === "") {
                    error += "Geographic Segment must not be blank<br>";
                }
                //if (dept_seg === "") {
                //    error += "Department must not be blank<br>";
                //}
                //alert("A     "+$('#budget_type0').val());
                if (newbal < 0 && $('#budget_type0').val()!=='1') {
                    error += "Amount throws Envelope balance below zero<br>";
                }
                if (sap_doc_e !== "" && rec_status!=="0") {
                    error += "Editting not allowed, budget already pushed to execution<br>";
                }
//                if (budget_amount === "") {
//                    error += "Budget Amount must not be blank<br>";
//                }
                if (isNaN(budget_amount)) {
                    error += "Budget Amount must be numeric<br>";
                }
                if (error.length > 0) {
                    toastr["error"](error, "Please correct the following:");
                    return true;
                }
                var p_completed = $('#p_completed_e').val();
                var c_from = $('#c_from_e option:selected').val();
                var c_to = $('#c_to_e option:selected').val();
                var budget_version_id = $('#budget_version0 option:selected').val();
                var budget_type_id = $('#sap_budget_type0 option:selected').val();
                var narration  = $('#narration_e').val();
                var action;
                if(rec_status==="0"){
                    action = "<%= Utility.OPTION_INSERT%>";
                }else{
                    action = "<%= Utility.OPTION_UPDATE%>";
                }
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: action, id: id, admin_segment: admin_seg, programme_segment: prog_seg, economic_segment: eco_seg,
                        functional_segment: func_seg, fund_segment: fund_seg, geo_segment: geo_seg, dept_id: dept_seg, budget_year_id: budget_year_id,
                        budget_amount: budget_amount, percent_complete: p_completed, complete_from: c_from, complete_to: c_to, 
                        budget_version_id: budget_version_id, budget_type_id: budget_type_id, narration: narration },
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        $('#editModal').modal('hide');
                        if (data === "<%= Utility.ActionResponse.UPDATED%>") {
                            toastr["success"]("Record Update Successful!", "Record Updated!!!");
                            getYearBudgetEntries("budget_head0");
                        } else if (data === "<%= Utility.ActionResponse.INSERTED%>"){
                            toastr["success"]("Record Insertion Successful!", "Record Inserted!!!");
                            getYearBudgetEntries("budget_head0"); 
                        } else {
                            toastr["error"]("Record Update Failed!", "No Record Updated!!!");
                        }
                    },
                    error: function () {
                        $('#editModal').modal('hide');
                        toastr["error"]("The server is not accessible!", "Server Not Accessible!!!");
                    }
                });
                //$('#EditSection').hide();
                //$('#list-area').fadeIn();
            };

            var ReturnToList = function () {
                $('#create-area').hide();
                $('#MainSection .panel-title').hide();
                $('#list-area').fadeIn();
            };

            deleteItem = function (arg) {
                var budget_head_name = $('#budget_head0 option:selected').text();
                if (budget_head_name.indexOf("Capital") !== -1) {
                    $("#delCap").show();
                }
                window.deleteId = arg;
                $('#deleteModal').modal('show');
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID%>", id: arg},
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
                                    if (attrName === "0") {
                                        document.getElementById("txtId_d").value = attrValue;
                                    }
                                    if (attrName === "1") {
                                        $('#admin_seg_d').val($('#mda_id0').val()).trigger('change');
                                    }
                                    if (attrName === "2") {
                                        //$('#prog_seg_d').val(attrValue).trigger('change');
                                        $('#prog_seg_d').val(attrValue); //.trigger('change');
                                        prog_seg_g = attrValue;
                                    }
                                    if (attrName === "3") {
                                        $('#eco_seg_d').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "4") {
                                        $('#func_seg_d').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "5") {
                                        $('#fund_seg_d').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "6") {
                                        $('#geo_seg_d').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "11") {
                                        $('#dept_seg_d').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "8") {
                                        attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                        $('#budget_amount_d').val(attrValue);
                                    }
                                }
                                break;
                            }
                            toastr["success"]("Record successfully fetched!", "Record Selection Successfull!!!");
                            //$('#list-area').hide();
                            //$('#EditSection').fadeIn();
                            //document.getElementById("sector_id2").focus();
                        }
                    },
                    error: function () {
                        toastr["error"]("Record not found!", "MDA Selection Failed!!!");
                    }
                });
            };

            finishDeleteItem = function () {
                var id = document.getElementById("txtId_d").value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_DELETE%>", id: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        $('#deleteModal').modal('hide');
                        if (data === "<%= Utility.ActionResponse.DELETED%>") {
                            toastr["success"]("Record Delete Successful!", "Record Deleted!!!");
                            getYearBudgetEntries("budget_head0");
                        } else {
                            toastr["error"]("Record Delete Failed!", "No Record Deleted!!!");
                        }
                    },
                    error: function () {
                        $('#deleteModal').modal('hide');
                        toastr["error"]("Record not found!", "MDA Delete Failed!!!");
                    }
                });
            };

            var prog_seg_g = null;
            addItem = function (arg) {
                var budget_head_name = $('#budget_head0 option:selected').text();
                if (budget_head_name.indexOf("Capital") !== -1) {
                    $("#addCap").show();
                }
                prog_seg_g = arg;
                $('#admin_seg_a').val($('#mda_id0').val()).trigger('change');
                $("#budget_amount_a").val("");
                $('#addModal').modal('show');
                //$('#divViewMeeting').modal('show');
            };

            finishAddItem = function () {
                var admin_seg = $('#admin_seg_a option:selected').text();
                admin_seg1 = admin_seg.split("[");
                admin_seg2 = admin_seg1[1].split("]");
                admin_seg = admin_seg2[0];
                var eco_seg = $('#eco_seg_a option:selected').val();
                var prog_seg = '99999999999999'; //$('#prog_seg_a option:selected').val();
                if (prog_seg_g !== undefined) {
                    prog_seg = prog_seg_g;
                }
                var func_seg = $('#func_seg_a option:selected').val();
                var fund_seg = $('#fund_seg_a option:selected').val();
                var geo_seg = $('#geo_seg_a option:selected').val();
                var dept_seg = $('#dept_seg_a option:selected').val();
                if (dept_seg === undefined) {
                    dept_seg = "";
                }
                var budget_amount = $('#budget_amount_a').val().replace(/,/g, "");
                var bal = $("#balance").val().replace(/,/g, "");
                var newbal = parseFloat(bal) - parseFloat(budget_amount);
                var error = "";
                if (admin_seg === "") {
                    error += "Admin Segment must not be blank<br>";
                }
                if (eco_seg === "") {
                    error += "Economic Segment must not be blank<br>";
                }
                if (prog_seg === "") {
                    error += "Programme Segment must not be blank<br>";
                }
                if (func_seg === "") {
                    error += "Functional Segment must not be blank<br>";
                }
                if (fund_seg === "") {
                    error += "Fund Segment must not be blank<br>";
                }
                if (geo_seg === "") {
                    error += "Geographic Segment must not be blank<br>";
                }
                //if (dept_seg === "") {
                //    error += "Department must not be blank<br>";
                //}
                //alert("B     "+$('#budget_type0').val());
                if (newbal < 0 && $('#budget_type0').val()!=='1') {
                    error += "Amount throws Envelope balance below zero<br>";
                }
//                if (budget_amount === "") {
//                    error += "Budget Amount must not be blank<br>";
//                }
                if (isNaN(budget_amount)) {
                    error += "Budget Amount must be numeric<br>";
                }
                if (error.length > 0) {
                    toastr["error"](error, "Please correct the following:");
                    return true;
                }
                var p_completed = $('#p_completed_a').val();
                var c_from = $('#c_from_a option:selected').val();
                var c_to = $('#c_to_a option:selected').val();
                var budget_version_id = $('#budget_version0 option:selected').val();
                var budget_type_id = $('#sap_budget_type0 option:selected').val();;
                var narration  = $('#narration_a').val();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: {option: "<%= Utility.OPTION_INSERT%>", admin_segment: admin_seg, programme_segment: prog_seg, economic_segment: eco_seg,
                        functional_segment: func_seg, fund_segment: fund_seg, geo_segment: geo_seg, dept_id: dept_seg, budget_year_id: budget_year_id,
                        budget_amount: budget_amount, percent_complete: p_completed, complete_from: c_from, complete_to: c_to, 
                        budget_version_id: budget_version_id, budget_type_id: budget_type_id, narration: narration },
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        $('#addModal').modal('hide');
                        if (data === "<%= Utility.ActionResponse.INSERTED%>") {
                            toastr["success"]("Record Insertion Successful!", "Record Deleted!!!");
                            getYearBudgetEntries("budget_head0");
                        } else {
                            toastr["error"]("Record already exists!", "Record Exists!!!");
                        }
                    },
                    error: function () {
                        $('#addModal').modal('hide');
                        toastr["error"]("Record not found!", "Record Insertion Failed!!!");
                    }
                });
            };

            var removeWindowId = function () {
                $('#addModal').modal('hide');
                $("#editModal").modal('hide');
                $("#deleteModal").modal('hide');
                window.deleteId = 0;
            };

            /**
             * Sync Budget Actuals from System
             *  
             * @returns
             */

            function approveBudget() {
                ShowLoading();
                var admin_seg = $('#mda_id0 option:selected').text();
                if(admin_seg===""){
                    StopLoading();
                    toastr["error"]("MDA must be selected!", "No MDA selected!");
                    return true;
                }
                admin_seg1 = admin_seg.split("[");
                admin_seg2 = admin_seg1[1].split("]");
                admin_seg = admin_seg2[0];
                
                data = {};
                data['option'] = "<%= Utility.OPTION_APPROVE_BUDGET%>";
                data['budget_year_id'] = $('#budget_year option:selected').val();
                data['budget_version_id'] = $('#budget_version0 option:selected').val();
                data['budget_type_id'] = $('#sap_budget_type0 option:selected').val();;
                data['dept_id'] = admin_seg1[0];
                data['admin_segment'] = admin_seg;
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    success: function (response) {
                        resetBudgetHeads();
                        //alert(response);
                        if (response.indexOf("<%= Utility.ActionResponse.SUCCESSFULL.toString()%>") !== -1) {
                            toastr["success"]("Budget successfully approved!", "Budget Approval Successful");
                            //window.setInterval( window.location=window.location, 100 );
                        } else {
                            toastr["error"]("Something went wrong with budget approval, please contact administrator.", "Budget Approval Failed");
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

            function unlockBudget() {
                ShowLoading();
                var admin_seg = $('#mda_id0 option:selected').text();
                if(admin_seg===""){
                    StopLoading();
                    toastr["error"]("MDA must be selected!", "No MDA selected!");
                    return true;
                }
                admin_seg1 = admin_seg.split("[");
                admin_seg2 = admin_seg1[1].split("]");
                admin_seg = admin_seg2[0];
                
                data = {};
                data['option'] = "<%= Utility.OPTION_UNLOCK_BUDGET%>";
                data['budget_year_id'] = $('#budget_year option:selected').val();
                data['budget_version_id'] = $('#budget_version0 option:selected').val();
                data['budget_type_id'] = $('#sap_budget_type0 option:selected').val();;
                data['dept_id'] = admin_seg1[0];
                data['admin_segment'] = admin_seg;
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    success: function (response) {
                        //alert(response);
                        resetBudgetHeads();
                        if (response.indexOf("<%= Utility.ActionResponse.SUCCESSFULL.toString()%>") !== -1) {
                            toastr["success"]("Budget successfully unlocked!", "Budget Unlock Successful");
                            //window.setInterval( window.location=window.location, 100 );
                        } else {
                            toastr["error"]("Something went wrong with budget unlock.", "Budget Unlock Failed");
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
                
            function submitBudget() {
                ShowLoading();
                data = {};
                data['option'] = "<%= Utility.OPTION_CHANGE_BUDGET_VERSION%>";
                data['budget_year_id'] = $('#budget_year option:selected').val();
                data['budget_version_id'] = $('#budget_version0 option:selected').val();
                data['budget_type_id'] = $('#sap_budget_type0 option:selected').val();;
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    success: function (response) {
                        //alert(response);
                        if (response.indexOf("<%= Utility.ActionResponse.SUCCESSFULL.toString()%>") !== -1) {
                            toastr["success"]("Budget successfully moved to next version!", "Budget Transition Successful");
                            window.setInterval( window.location=window.location, 100 );
                        } else {
                            toastr["error"]("Something went wrong with moving budget to next version, please contact administrator.", "Budget Transition Failed");
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
                
                
                
            function SyncBudget() {
                ShowLoading();

                data = {};
                data['option'] = "<%= Utility.OPTION_PUSH_BUDGET%>";
                data['budgetYear'] = currentyear;
                data['budgetType'] = "<%= Utility.SAP_HOUSE_APPROVED_BUDGET_TYPE%>";

                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: data,
                    dataType: "text",
                    cache: false,
                    success: function (response) {
                        //alert(response);
                        if (response.indexOf("UPDATED") !== -1) {
                            toastr["success"]("Approved Budget synchronized successfully!", "Approved Budget Synchronization");
                        } else if (response.indexOf("error") !== -1){
                            var msg = response.replace('error', '').replace(/_~_/g,'<br><br>');
                            toastr["error"]("Something went wrong with synchronizing approved budget, please contact administrator for the following error: <br><br>"+msg, "Approved Budget Synchronization");
                        } else {
                            toastr["error"]("Something went wrong with synchronizing approved budget, please contact administrator.", "Approved Budget Synchronization");
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
            
            function viewEnvelope(){
                var budget_year = $('#budget_year option:selected').val();
                var mda_id = $('#mda_id0 option:selected').val();
                var mda = $('#mda_id0 option:selected').text();
                mda = mda.split("[");
                mda = mda[0];
                if(mda_id==="0"){
                    toastr["error"]("MDA must be selected!!!.", "No MDA Selected");
                    return true;
                }
                //oWin = window.open("viewEnvelope.jsp?budget_year="+budget_year+"&mda_id="+mda_id+"&mda="+mda, "_blank", "directories=0,scrollbars=1,resizable=1,location=0,status=0,toolbar=0,menubar=0,width=800,height=500,left=100,top=100");
		
                var data_array = {};
                data_array['option'] = "<%= Utility.OPTION_GET_ENVELOPES_BY_MDAS%>";
                data_array['admin_segment'] = mda_id;
                data_array['budget_year_id'] = budget_year;
                
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/YearBudgetServlet",
                    data: data_array,
                    dataType: "json",
                    cache: false,
                    success: function (response) {
                        var counter=0;
                        if(response.length>0){
                            //var doc = new jsPDF();
                            var doc = new jsPDF();
                            doc.setFont("courier");
                            doc.setFontSize(24);
                            doc.setTextColor(0, 0, 255);
                            doc.setFontType("bold");
                            doc.text(53, 20, "<%= Utility.ONDOSTATEOFNIGERIA%>");
                            doc.setTextColor(0, 0, 0);
                            doc.setFontSize(11);
                            var report_head = currentyear+" MDA ENVELOPE DISTRIBUTION FOR:";
                            doc.text(5, 27, " ".repeat(((88 - report_head.trim().length))/2) + report_head);
                            doc.text(5, 32, " ".repeat(((88 - mda.trim().length))/2) + mda);
                            doc.setFontSize(10);
                            doc.text(5, 40, " S/No");
                            doc.text(16, 40, " BUDGET HEADS");
                            doc.text(117, 40, "                               AMOUNT");
                            doc.rect(5, 37, 12, 4);
                            doc.rect(17, 37, 130, 4);
                            doc.rect(147, 37, 50, 4);
                            doc.setFontType("normal");
                            var row = 44;
                            var col = 5;
                            var total = 0;
                            for (var i = 0; i < response.length; i++) {
                                var obj = response[i];
                                
                                if(obj[3]===null || obj[3]===""){
                                    obj[3]=0;
                                }
                                total += obj[3];
                                ++counter;
                                var count = counter + "";
                                doc.text(col, row, " ".repeat(4 - count.trim().length) + count.trim() + ".");
                                doc.rect(col, row - 3, 12, 4);
                                
                                col += 12;
                                doc.text(col+1, row, obj[9]);
                                doc.rect(col, row - 3, 130, 4);

                                //var amt = parseFloat(obj[3].toFixed(2)).toString();
                                var amt = parseFloat((obj[3]+"").replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                                col += 130;
                                doc.text(col, row, " ".repeat(23 - amt.trim().length) + amt);
                                doc.rect(col, row - 3, 50, 4);
                                row += 4;
                                col = 5;
                            }
                            col = 5;
                            total = total+"";
                            var total = parseFloat(total.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                            doc.rect(col, row - 3, 12, 4);
                            col += 12;
                            doc.text(col + 115, row, " TOTAL:");
                            doc.rect(col, row - 3, 130, 4);
                            col += 130;
                            doc.text(col, row, " ".repeat(23 - total.trim().length) + total);
                            doc.rect(col, row - 3, 50, 4);
                            doc.setFontSize(10);
                            doc.setFontType("bold");
                            doc.text(100, 295, "Page: 1");
                            //doc.output('datauri');
                            //doc.save('stocktransfereport.pdf');
                            var string = doc.output('datauristring');
                            var iframe = "<iframe width='100%' height='100%' src='" + string + "'></iframe>"
                            var x = window.open();
                            x.document.open();
                            x.document.write(iframe);
                            x.document.close();                            
                            toastr["success"]("Envelope viewing successfull!", "Envelope Viewing");
                        }else{
                            toastr["error"]("Envelope viewing failed!!!", "Envelope Viewing");
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
            
            function repeat(s, n, d) {
                return --n ? s + (d || "") + repeat(s, n, d) : "" + s;
            }

        </script>
    </body>
</html>
