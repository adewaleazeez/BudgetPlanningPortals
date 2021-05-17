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
<!--                                <img src="http://placehold.it/90/c2c2c2?text=User" alt="User" class="img-circle profile-img">-->
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
                                    <h2 class="title">MTSS Costing</h2>
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
                                        <li class="active">MTSS Costing</li>
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
                                                        <label for="budget_year">Budget&nbsp;Year</label>
                                                        <select class='js-states form-control custom_select' id='budget_year' disabled></select>
                                                    </div>
                                                    <div class="col-md-5">
                                                        <label for="mda_id0">MDA</label>
                                                        <select class='js-states form-control custom_select' id='mda_id0' onchange='resetBudgetHeads();'></select>
                                                    </div>

                                                    <div class="col-md-5">
                                                        <label for="budget_head0">Budget Head</label>
                                                        <select class='js-states form-control custom_select' id='budget_head0' onchange='getMTSSCostingEntries(this.id);'></select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div style='margin-top: -20px'  class="col-md-12 ml-15 mr-15" id='active'></div>                                                        

                                        <div class="modal fade in" id="addModal">
                                            <div class="modal-dialog modal-center modal-md">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close" data-izimodal-close="" onclick="removeWindowId();">
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
                                                        <div id="div_cost_a" class="input-group col-lg-12">
                                                            <label for="cost_a">Cost Per Quantity</label>
                                                            <input type="text" required class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="cost_a" style="text-align: right" placeholder="Cost per unit">
                                                        </div>
                                                        <div id="div_1_a" class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <div id="div_qty1_a" class="col-md-5">
                                                                <label id="quantity_id_a_1" for="quantity_a_1"></label>
                                                                <input type="text" disabled class="form-control" onblur="if(this.value!==''){$('#budget_amount_a_1').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_a').val().toString().replace(/,/g, ''))); $('#budget_amount_a_1').val(parseFloat($('#budget_amount_a_1').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_a_1" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_a_id_1" for="budget_amount_a_1"></label>
                                                                <input type="text" disabled class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_a_1" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        <div class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <div id="div_qty2_a" class="col-md-5">
                                                                <label id="quantity_id_a_2" for="quantity_a_2"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){$('#budget_amount_a_2').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_a').val().toString().replace(/,/g, ''))*(1 + (parseFloat(inflation_rate)/100))); $('#budget_amount_a_2').val(parseFloat($('#budget_amount_a_2').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_a_2" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_a_id_2" for="budget_amount_a_2"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_a_2" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        <div class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <div id="div_qty3_a" class="col-md-5">
                                                                <label id="quantity_id_a_3" for="quantity_a_3"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){$('#budget_amount_a_3').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_a').val().toString().replace(/,/g, ''))*(1 + (parseFloat(inflation_rate)/100))*(1 + (parseFloat(inflation_rate)/100))); $('#budget_amount_a_3').val(parseFloat($('#budget_amount_a_3').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_a_3" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_a_id_3" for="budget_amount_a_3"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_a_3" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        <div class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <div id="div_qty4_a" class="col-md-5">
                                                                <label id="quantity_id_a_4" for="quantity_a_4"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){$('#budget_amount_a_4').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_a').val().toString().replace(/,/g, ''))*(1 + (parseFloat(inflation_rate)/100))*(1 + (parseFloat(inflation_rate)/100))*(1 + (parseFloat(inflation_rate)/100))); $('#budget_amount_a_4').val(parseFloat($('#budget_amount_a_4').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_a_4" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_a_id_4" for="budget_amount_a_4"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_a_4" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        
                                                        <!--div id="addCap">
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
                                                        </div-->
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
                                                        <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close" data-izimodal-close="" onclick="removeWindowId();">
                                                            <span aria-hidden="true" style="font-size:14px">x</span>
                                                        </button>
                                                        <h4 class="modal-title">Edit Record</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="input-group col-lg-12">
                                                            <label for="admin_seg_e">Admin Segment</label>
                                                            <select class='js-states form-control' id='admin_seg_e' disabled="" ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="eco_seg_e">Economic Segment</label>
                                                            <select class='js-states form-control' id='eco_seg_e' disabled="" ></select>
                                                        </div>
                                                        <input type="hidden" id="prog_seg_e" />
                                                        <!--div class="input-group col-lg-12">
                                                            <label for="prog_seg_e">Programme Segment</label>
                                                            <select class='js-states form-control' id='prog_seg_e' disabled="" ></select>
                                                        </div-->
                                                        <div class="input-group col-lg-12">
                                                            <label for="func_seg_e">Functional Segment</label>
                                                            <select class='js-states form-control' id='func_seg_e' disabled="" ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="fund_seg_e">Fund Segment</label>
                                                            <select class='js-states form-control' id='fund_seg_e' disabled="" ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="geo_seg_e">Geo Segment</label>
                                                            <select class='js-states form-control' id='geo_seg_e' disabled="" ></select>
                                                        </div>
                                                        <div class="input-group col-lg-12">
                                                            <label for="dept_seg_e">Department</label>
                                                            <select class='js-states form-control' id='dept_seg_e' disabled="" ></select>
                                                        </div>
                                                        <input type="hidden" id="oldval" />
                                                        <div id="div_cost_e" class="input-group col-lg-12">
                                                            <label for="cost_e">Cost Per Quantity</label>
                                                            <input type="text" required class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="cost_e" style="text-align: right" placeholder="Cost per unit">
                                                        </div>
                                                        <div id="div_1_e" class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <input type="hidden" id="txtId_e" />
                                                            <div id="div_qty1_e" class="col-md-5">
                                                                <label id="quantity_id_e_1" for="quantity_e_1"></label>
                                                                <input type="text" disabled class="form-control" onblur="if(this.value!==''){$('#budget_amount_e_1').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_e').val().toString().replace(/,/g, ''))); $('#budget_amount_e_1').val(parseFloat($('#budget_amount_e_1').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_e_1" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_e_id_1" for="budget_amount_e_1"></label>
                                                                <input type="text" disabled class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_e_1" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        <div class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <input type="hidden" id="txtId_e_2" />
                                                            <div id="div_qty2_e" class="col-md-5">
                                                                <label id="quantity_id_e_2" for="quantity_e_2"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){$('#budget_amount_e_2').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_e').val().toString().replace(/,/g, ''))*(1 + (parseFloat(inflation_rate)/100))); $('#budget_amount_e_2').val(parseFloat($('#budget_amount_e_2').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_e_2" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_e_id_2" for="budget_amount_e_2"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_e_2" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        <div class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <input type="hidden" id="txtId_e_3" />
                                                            <div id="div_qty3_e" class="col-md-5">
                                                                <label id="quantity_id_e_3" for="quantity_e_3"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){$('#budget_amount_e_3').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_e').val().toString().replace(/,/g, ''))*(1 + (parseFloat(inflation_rate)/100))*(1 + (parseFloat(inflation_rate)/100))); $('#budget_amount_e_3').val(parseFloat($('#budget_amount_e_3').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_e_3" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_e_id_3" for="budget_amount_e_3"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_e_3" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        <div class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <input type="hidden" id="txtId_e_4" />
                                                            <div id="div_qty4_e" class="col-md-5">
                                                                <label id="quantity_id_e_4" for="quantity_e_4"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){$('#budget_amount_e_4').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_e').val().toString().replace(/,/g, ''))*(1 + (parseFloat(inflation_rate)/100))*(1 + (parseFloat(inflation_rate)/100))*(1 + (parseFloat(inflation_rate)/100))); $('#budget_amount_e_4').val(parseFloat($('#budget_amount_e_4').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_e_4" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_e_id_4" for="budget_amount_e_4"></label>
                                                                <input type="text" required class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_e_4" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        
                                                        <!--div id="editCap">
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
                                                        </div-->
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-success" data-dismiss="modal" id="confirmAdd" onclick="FinishEdit();"><i class='fa fa-save'></i>Update</button>
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
                                                        <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close" data-izimodal-close="" onclick="removeWindowId();">
                                                            <span aria-hidden="true" style="font-size:14px">x</span>
                                                        </button>
                                                        <h4 class="modal-title">Delete Record</h4>
                                                    </div>
                                                    <div class="modal-body">
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
                                                        <div id="div_cost_d" class="input-group col-lg-12">
                                                            <label for="cost_d">Cost Per Quantity</label>
                                                            <input type="text" required disabled="" class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="cost_d" style="text-align: right" placeholder="Cost per unit">
                                                        </div>
                                                        <div id="div_1_d" class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <input type="hidden" id="txtId_d" />
                                                            <div id="div_qty1_d" class="col-md-5">
                                                                <label id="quantity_id_d_1" for="quantity_d_1"></label>
                                                                <input type="text" required disabled="" class="form-control" onblur="if(this.value!==''){$('#budget_amount_d_1').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_d').val().toString().replace(/,/g, ''))); $('#budget_amount_d_1').val(parseFloat($('#budget_amount_d_1').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_d_1" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_d_id_1" for="budget_amount_d_1"></label>
                                                                <input type="text" required disabled="" class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_d_1" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        <div class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <input type="hidden" id="txtId_d_2" />
                                                            <div id="div_qty2_d" class="col-md-5">
                                                                <label id="quantity_id_d_2" for="quantity_d_2"></label>
                                                                <input type="text" required disabled="" class="form-control" onblur="if(this.value!==''){$('#budget_amount_d_2').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_d').val().toString().replace(/,/g, ''))*(1 + (parseFloat(inflation_rate)/100))); $('#budget_amount_d_2').val(parseFloat($('#budget_amount_d_2').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_d_2" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_d_id_2" for="budget_amount_d_2"></label>
                                                                <input type="text" required disabled="" class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_d_2" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        <div class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <input type="hidden" id="txtId_d_3" />
                                                            <div id="div_qty3_d" class="col-md-5">
                                                                <label id="quantity_id_d_3" for="quantity_d_3"></label>
                                                                <input type="text" required disabled="" class="form-control" onblur="if(this.value!==''){$('#budget_amount_d_3').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_d').val().toString().replace(/,/g, ''))*(1 + (parseFloat(inflation_rate)/100))*(1 + (parseFloat(inflation_rate)/100))); $('#budget_amount_d_3').val(parseFloat($('#budget_amount_d_3').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_d_3" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_d_id_3" for="budget_amount_d_3"></label>
                                                                <input type="text" required disabled="" class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_d_3" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        <div class="input-group col-lg-12" style="margin-left: -15px;">
                                                            <input type="hidden" id="txtId_d_4" />
                                                            <div id="div_qty4_d" class="col-md-5">
                                                                <label id="quantity_id_d_4" for="quantity_d_4"></label>
                                                                <input type="text" required disabled="" class="form-control" onblur="if(this.value!==''){$('#budget_amount_d_4').val(parseFloat(this.value.replace(/,/g, ''))*parseFloat($('#cost_d').val().toString().replace(/,/g, ''))*(1 + (parseFloat(inflation_rate)/100))*(1 + (parseFloat(inflation_rate)/100))*(1 + (parseFloat(inflation_rate)/100))); $('#budget_amount_d_4').val(parseFloat($('#budget_amount_d_4').val().replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')); this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="quantity_d_4" style="text-align: right" placeholder="Quantity">
                                                            </div>
                                                            <div class="col-md-2"></div>
                                                            <div class="col-md-5">
                                                                <label id="amount_d_id_4" for="budget_amount_d_4"></label>
                                                                <input type="text" required disabled="" class="form-control" onblur="if(this.value!==''){this.value = parseFloat(this.value.replace(/,/g, '')).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');}" id="budget_amount_d_4" style="text-align: right" placeholder="Amount">
                                                            </div>
                                                        </div>
                                                        
                                                        <!--div id="delCap">
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
                                                        </div-->
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

                    </div>
                    <!-- /.container-fluid -->
                    </section>
                </div>
                <!-- /.main-page -->
                <!-- /.right-sidebar -->
            </div>
            <!-- /.content-container -->
        </div>
        <!-- /.content-wrapper -->
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

    <script type="text/javascript">
        checkLogin();
        $(document).ready(function () {
            //$('#deleteModal').modal("hide");
            //$('#addModal').modal("hide");
            //$('#editModal').modal("hide");
            
            //Close side-menu
            $('#nav-togglerx').click();
            
            //getSectors();
            getBudgetYear();
            //getMDAS('sector_id0');
            $("#mda_id0, #admin_seg_a, #eco_seg_a, #prog_seg_a, #fund_seg_a, #func_seg_a, #geo_seg_a").select2();//, #dept_seg_a, #admin_seg_e, #eco_seg_e, #prog_seg_e, #fund_seg_e, #func_seg_e, #geo_seg_e, #dept_seg_e, #admin_seg_d, #eco_seg_d, #prog_seg_d, #fund_seg_d, #func_seg_d, #geo_seg_d, #dept_seg_d").select2();
            //$("").select2();
            //$("").select2();
                
        });
        
        //$("#addCap").hide();
        //$('#editCap').hide(); 
        //$('#delCap').hide();
        
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
                            currentyear = parseInt(attrValue)+1;
                        }
                    }
                }
                $('#budget_year').html(resp);
            }
            getMDAS();

        };
        function getMDAS() {
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
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
                //$('#mda_id0').html("<option value='0'></option>" + resp);
                //$('#admin_seg_a').html(resp);
                //$('#admin_seg_e').html(resp);
                //$('#admin_seg_d').html(resp);
            }
            getBudgetHeads();
        }

        function getBudgetHeads() {
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                data: {option: "<%= Utility.OPTION_SELECT%>"},
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
        var inflation_rate = null;

        function getMTSSCostingEntries(id) {
            var budget_head_name = $('#' + id + " option:selected").text();
            id = $('#' + id).val();
            if (id === '0') {
                return true;
            }
            budget_head_id = id;
            mda_id = $('#mda_id0 option:selected').val();

            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: id, admin_segment: mda_id},
                dataType: "json",
                cache: false,
                async: false,
                success: function (data) {
                    //console.log(data);
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
                                    $("#quantity_id_a_1").html("Quantity For "+(parseInt(budget_year_id) - 1));
                                    document.getElementById("quantity_a_1").placeholder = "Quantity For "+(parseInt(budget_year_id) - 1);
                                    $("#amount_a_id_1").html("Amount For "+(parseInt(budget_year_id) - 1));
                                    document.getElementById("budget_amount_a_1").placeholder = "Amount For "+(parseInt(budget_year_id) - 1);
                                    $("#quantity_id_a_2").html("Quantity For "+(parseInt(budget_year_id) + 0));
                                    document.getElementById("quantity_a_2").placeholder = "Quantity For "+(parseInt(budget_year_id) + 0);
                                    $("#amount_a_id_2").html("Amount For "+(parseInt(budget_year_id) + 0));
                                    document.getElementById("budget_amount_a_2").placeholder = "Amount For "+(parseInt(budget_year_id) + 0);
                                    $("#quantity_id_a_3").html("Quantity For "+(parseInt(budget_year_id) + 1));
                                    document.getElementById("quantity_a_3").placeholder = "Quantity For "+(parseInt(budget_year_id) + 1);
                                    $("#amount_a_id_3").html("Amount For "+(parseInt(budget_year_id) + 1));
                                    document.getElementById("budget_amount_a_3").placeholder = "Amount For "+(parseInt(budget_year_id) + 1);
                                    $("#quantity_id_a_4").html("Quantity For "+(parseInt(budget_year_id) + 2));
                                    document.getElementById("quantity_a_4").placeholder = "Quantity For "+(parseInt(budget_year_id) + 2);
                                    $("#amount_a_id_4").html("Amount For "+(parseInt(budget_year_id) + 2));
                                    document.getElementById("budget_amount_a_4").placeholder = "Amount For "+(parseInt(budget_year_id) + 2);
                                     
                                    $("#quantity_id_e_1").html("Quantity For "+(parseInt(budget_year_id) - 1));
                                    document.getElementById("quantity_e_1").placeholder = "Quantity For "+(parseInt(budget_year_id) - 1);
                                    $("#amount_e_id_1").html("Amount For "+(parseInt(budget_year_id) - 1));
                                    document.getElementById("budget_amount_e_1").placeholder = "Amount For "+(parseInt(budget_year_id) - 1);
                                    $("#quantity_id_e_2").html("Quantity For "+(parseInt(budget_year_id) + 0));
                                    document.getElementById("quantity_e_2").placeholder = "Quantity For "+(parseInt(budget_year_id) + 0);
                                    $("#amount_e_id_2").html("Amount For "+(parseInt(budget_year_id) + 0));
                                    document.getElementById("budget_amount_e_2").placeholder = "Amount For "+(parseInt(budget_year_id) + 0);
                                    $("#quantity_id_e_3").html("Quantity For "+(parseInt(budget_year_id) + 1));
                                    document.getElementById("quantity_e_3").placeholder = "Quantity For "+(parseInt(budget_year_id) + 1);
                                    $("#amount_e_id_3").html("Amount For "+(parseInt(budget_year_id) + 1));
                                    document.getElementById("budget_amount_e_3").placeholder = "Amount For "+(parseInt(budget_year_id) + 1);
                                    $("#quantity_id_e_4").html("Quantity For "+(parseInt(budget_year_id) + 2));
                                    document.getElementById("quantity_e_4").placeholder = "Quantity For "+(parseInt(budget_year_id) + 2);
                                    $("#amount_e_id_4").html("Amount For "+(parseInt(budget_year_id) + 2));
                                    document.getElementById("budget_amount_e_4").placeholder = "Amount For "+(parseInt(budget_year_id) + 2);
                                    
                                    $("#quantity_id_d_1").html("Quantity For "+(parseInt(budget_year_id) - 1));
                                    document.getElementById("quantity_d_1").placeholder = "Quantity For "+(parseInt(budget_year_id) - 1);
                                    $("#amount_d_id_1").html("Amount For "+(parseInt(budget_year_id) - 1));
                                    document.getElementById("budget_amount_d_1").placeholder = "Amount For "+(parseInt(budget_year_id) - 1);
                                    $("#quantity_id_d_2").html("Quantity For "+(parseInt(budget_year_id) + 0));
                                    document.getElementById("quantity_d_2").placeholder = "Quantity For "+(parseInt(budget_year_id) + 0);
                                    $("#amount_d_id_2").html("Amount For "+(parseInt(budget_year_id) + 0));
                                    document.getElementById("budget_amount_d_2").placeholder = "Amount For "+(parseInt(budget_year_id) + 0);
                                    $("#quantity_id_d_3").html("Quantity For "+(parseInt(budget_year_id) + 1));
                                    document.getElementById("quantity_d_3").placeholder = "Quantity For "+(parseInt(budget_year_id) + 1);
                                    $("#amount_d_id_3").html("Amount For "+(parseInt(budget_year_id) + 1));
                                    document.getElementById("budget_amount_d_3").placeholder = "Amount For "+(parseInt(budget_year_id) + 1);
                                    $("#quantity_id_d_4").html("Quantity For "+(parseInt(budget_year_id) + 2));
                                    document.getElementById("quantity_d_4").placeholder = "Quantity For "+(parseInt(budget_year_id) + 2);
                                    $("#amount_d_id_4").html("Amount For "+(parseInt(budget_year_id) + 2));
                                    document.getElementById("budget_amount_d_4").placeholder = "Amount For "+(parseInt(budget_year_id) + 2);
                                }
                                if (attrName === "4") {
                                    inflation_rate = attrValue;
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
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>", admin_segment: admin_seg, programme_segment: gl_code, budget_year_id: budget_year_id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: capitalMTSSCostingReturnValues,
                    error: function () {
                        toastr["error"]("Record Selection Failed!", "No record selected!!!");
                    }
                });
            } else {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>", admin_segment: admin_seg, economic_segment: gl_code, budget_year_id: budget_year_id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: mtssCostingReturnValues,
                    error: function () {
                        toastr["error"]("Record Selection Failed!", "No record selected!!!");
                    }
                });
            }
        }

        function getProgSegment() {
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
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
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
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
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
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
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
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
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
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
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
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

        function capitalMTSSCostingReturnValues(data) {
            //console.log(data);
            //var balance = 0.0;
            //balance = balance.toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            budget_envelope = parseFloat(budget_envelope).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            var balance = budget_envelope;
            var resp = "<div style='margin-top: 10px;'  class='col-md-12 ml-45 mr-45'>" +
                    "<div class='col-md-7'></div>" +
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
                    "<td align='right'><b>Total For Existing Projects: </b>" +
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
                    "<td align='right'><b>Inflation Rate: " +
                    inflation_rate+" %</b>" +
                    "</td>" +
                    "<td align='right'><b>Total For New Projects: </b>" +
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
            var total5 = 0.0;
            var total6 = 0.0;
            var total7 = 0.0;
            $('#active').html(resp);
            $("#budget_envelope").val(budget_envelope);
            $("#balance").val(balance);
            var resp = "";
            var oldproject = "";
            var newproject = "";
            var id = "0";
            var code = "";
            var current_code = "";
            var name = "";
            var amt1 = 0;
            var amt2 = 0;
            var amt3 = 0;
            var amt4 = 0;
            var amt5 = 0;
            var amt6 = 0;
            var amt7 = 0;
            var year_id = null;
            var old_year_id = null;
            var total_concat = "";
            var oldproj = 0;
            var newproj = 0;
            if (data.length > 0) {
                var counter = 0;
                
//console.log(data);
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
                            amt5 = parseFloat(attrValue);
                        }
                        if (attrName === "8") {
                            amt6 = parseFloat(attrValue);
                        }
                        if (attrName === "9") {
                            amt7 = parseFloat(attrValue);
                        }
                        if (attrName === "10") {
                            year_id = parseInt(attrValue);
                        }
                    }
                    if (code.length === 12 && code !== current_code) {
                        if (current_code !== "") {
                            balance = parseFloat(balance.replace(/,/g, ""));
                            balance -= total4;
                            if (old_year_id < budget_year_id) {
                                oldproj += total4;
                            } else {
                                newproj += total4;
                            }
                            total1 = total1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            total2 = total2.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            total3 = total3.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            total4 = total4.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            total5 = total5.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            total6 = total6.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            total7 = total7.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            balance = balance.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            resp += "</tr><tr>" +
                                    "<td colspan='3' align='right'>TOTAL</td>" +
                                    "<td align='right'>" + total1 + "</td>" +
                                    "<td align='right'>" + total2 + "</td>" +
                                    "<td align='right'>" + total3 + "</td>" +
                                    "<td align='right'>" + total4 + "</td>" +
                                    "<td align='right'>" + total5 + "</td>" +
                                    "<td align='right'>" + total6 + "</td>" +
                                    "<td align='right'>" + total7 + "</td>" +
                                    "</tr></tr></table>" +
                                    "</div>" +
                                    "</div>" +
                                    "</div>" +
                                    "</div>" +
                                    "</div>";
                            $("#balance").val(balance);

                            if (old_year_id < budget_year_id) {
                                oldproject += resp;
                            } else {
                                newproject += resp;
                            }
                            total_concat += current_code + "!" + total4 + "|";
                        }
                        current_code = code;
                        old_year_id = year_id;
                        total1 = 0.0;
                        total2 = 0.0;
                        total3 = 0.0;
                        total4 = 0.0;
                        total5 = 0.0;
                        total6 = 0.0;
                        total7 = 0.0;

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
                                "<td colspan='3' align='center'><b>Approved Budget</b></td>" +
                                "<td align='center'><b>Budget&nbsp;"+(parseInt(budget_year_id) - 1)+"</b></td>" +
                                "<td colspan='3' align='center'><b>Proposed Budget</b></td>" +
                                "<td colspan='5'></td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td><b>S/No</b></td><td><b>" + code + "</b></td>" +
                                "<td><b>" + name + "</b></td>" +
                                "<td align='center'><b>" + (parseInt(budget_year_id) - 4) + "</b></td>" +
                                "<td align='center'><b>" + (parseInt(budget_year_id) - 3) + "</b></td>" +
                                "<td align='center'><b>" + (parseInt(budget_year_id) - 2) + "</b></td>" +
                                "<td align='center'><b>" + (parseInt(budget_year_id) - 1) + "</b></td>" +
                                "<td align='center'><b>" + (parseInt(budget_year_id) + 0) + "</b></td>" +
                                "<td align='center'><b>" + (parseInt(budget_year_id) + 1) + "</b></td>" +
                                "<td align='center'><b>" + (parseInt(budget_year_id) + 2) + "</b></td>";
                                if((parseInt(budget_year_id) - 1)===parseInt(year_id)){
                                    resp += "<td colspan='2'><a class='btn btn-info toggle-code-handle pull-right newpolicy' onclick=addItem('" + code + "','new','cap') role='button'><i class='fa fa-user-plus'> </i>Add Record</a></td>";
                                }else{
                                    resp += "<td colspan='2'><a class='btn btn-info toggle-code-handle pull-right newpolicy' onclick=addItem('" + code + "','old','cap') role='button'><i class='fa fa-user-plus'> </i>Add Record</a></td>";
                                }
                                resp += "</tr>";
                    } else {
                        total1 += amt1;
                        total2 += amt2;
                        total3 += amt3;
                        total4 += amt4;
                        total5 += amt5;
                        total6 += amt6;
                        total7 += amt7;
                        amt1 = parseFloat(amt1).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        amt2 = parseFloat(amt2).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        amt3 = parseFloat(amt3).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        amt4 = parseFloat(amt4).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        amt5 = parseFloat(amt5).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        amt6 = parseFloat(amt6).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        amt7 = parseFloat(amt7).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        resp += "<tr>" +
                                "<td align='right'>" + (++counter) + ".</td>" +
                                "<td>" + code + "</td><td>" + name + "</td>" +
                                "<td align='right'>" + amt1 + "</td>" +
                                "<td align='right'>" + amt2 + "</td>" +
                                "<td align='right'>" + amt3 + "</td>" +
                                "<td align='right'>" + amt4 + "</td>" +
                                "<td align='right'>" + amt5 + "</td>" +
                                "<td align='right'>" + amt6 + "</td>" +
                                "<td align='right'>" + amt7 + "</td>";
                        if (amt4 === 0) {
                            resp += "<td><a class='btn toggle-code-handle pull-right newpolicy' onclick='' role='button'><i class='fa fa-pencil'></i>Edit</a></td>";
                            resp += "<td><a class='btn toggle-code-handle pull-right newpolicy' onclick='' role='button'><i class='fa fa-remove'></i>Del</a></td></tr>";
                        } else {
                            if(parseInt(budget_year_id)===parseInt(year_id)){
                                resp += "<td><a class='btn btn-success toggle-code-handle pull-right newpolicy' onclick=EditItem('" + id + "','new','cap') role='button'><i class='fa fa-pencil'></i>Edit</a></td>";
                                resp += "<td><a class='btn btn-warning toggle-code-handle pull-right newpolicy' onclick=deleteItem('" + id + "','new','cap') role='button'><i class='fa fa-remove'></i>Del</a></td></tr>";
                            }else{
                                resp += "<td><a class='btn btn-success toggle-code-handle pull-right newpolicy' onclick=EditItem('" + id + "','old','cap') role='button'><i class='fa fa-pencil'></i>Edit</a></td>";
                                resp += "<td><a class='btn btn-warning toggle-code-handle pull-right newpolicy' onclick=deleteItem('" + id + "','old','cap') role='button'><i class='fa fa-remove'></i>Del</a></td></tr>";
                            }
                        }
                    }
                }
            }
            
            balance = parseFloat(balance.replace(/,/g, ""));
            balance -= total4;
            if (old_year_id < budget_year_id) {
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
            total5 = total5.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            total6 = total6.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            total7 = total7.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            balance = balance.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            resp += "</tr><tr>" +
                    "<td colspan='3' align='right'>TOTAL</td><td align='right'>" + total1 + "</td>" +
                    "<td align='right'>" + total2 + "</td>" +
                    "<td align='right'>" + total3 + "</td>" +
                    "<td align='right'>" + total4 + "</td>" +
                    "<td align='right'>" + total5 + "</td>" +
                    "<td align='right'>" + total6 + "</td>" +
                    "<td align='right'>" + total7 + "</td>" +
                    "</tr></tr></table>";
            $("#balance").val(balance);

            if (year_id < budget_year_id) {
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
            oldproj += total7;
            var total_concat_array = total_concat.split("|");
            for (var count = 0; count < total_concat_array.length; count++) {
                var totals = total_concat_array[count].split("!");
                $("#f_" + totals[0]).val(totals[1]);
            }

        }

        function mtssCostingReturnValues(data) {
            //var balance = 0.0;
            //balance = balance.toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            budget_envelope = parseFloat(budget_envelope).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            var balance = budget_envelope;
            var resp = "<table id='main-table' class='table table-clean table-striped' border='0'><thead>";
            resp += "<tr><td rowspan='2' colspan='3'></td><td colspan='3' align='right'><b>Envelope Amount:<input type='text' disabled style='text-align: right' value='" + budget_envelope + "'></b></td><td></td><td colspan='3' align='right'><b>Amount Left:<input id='balance' type='text' disabled style='text-align: right' value='" + balance + "'></b></td><td colspan='2'></td></tr></thead><tbody>";
            resp += "<tr><td colspan='3'></td><td colspan='3' align='center'><b>Approved Budget</b></td><td><b>Budget&nbsp;"+(budget_year_id-1)+"</b></td><td colspan='3' align='center'><b>Proposed Budget</b></td><td colspan='2'></td></tr>";
            resp += "<tr><td><b>S/No</b></td><td><b>Economic Segment</b></td><td><b>" + gl_narration + " (" + gl_code + ")</b></td><td align='center'><b>" + (parseInt(budget_year_id) - 4)+"</b></td><td align='center'><b>" + (parseInt(budget_year_id) - 3)+"</b></td><td align='center'><b>"+(parseInt(budget_year_id) - 2)+"</b></td><td align='center'><b>"+(parseInt(budget_year_id) - 1)+"</b></td><td align='center'><b>"+(parseInt(budget_year_id) + 0)+"</b></td><td align='center'><b>"+(parseInt(budget_year_id) + 1)+"</b></td><td align='center'><b>"+(parseInt(budget_year_id) + 2)+"</b></td><td colspan='2'><a class='btn btn-info toggle-code-handle pull-right newpolicy' onclick=addItem('','new','rec') role='button'><i class='fa fa-user-plus'> </i>Add Record</a></td></tr>";
            var total1 = 0.0;
            var total2 = 0.0;
            var total3 = 0.0;
            var total4 = 0.0;
            var total5 = 0.0;
            var total6 = 0.0;
            var total7 = 0.0;
            if (data.length > 0) {
                ////console.log(data);
                var counter = 0;

                for (var i = 0; i < data.length; i++) {
                    var obj = data[i];
                    var id = "";
                    var disability = false;
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
                        }
                        if (attrName === "7") {
                            total5 += parseFloat(attrValue);
                        }
                        if (attrName === "8") {
                            total6 += parseFloat(attrValue);
                        }
                        if (attrName === "9") {
                            total7 += parseFloat(attrValue);
                            if (attrValue === "0") {
                                disability = true;
                            }
                        }
                        if (attrName >= "3" && attrName <= "9") {
                            attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                            resp += "<td align='right'>" + attrValue + "</td>";
                        }
                        if (attrName === "9") {
                            if (disability) {
                                resp += "<td><a class='btn toggle-code-handle pull-right newpolicy' onclick='' role='button'><i class='fa fa-pencil'></i>Edit</a></td>";
                                resp += "<td><a class='btn toggle-code-handle pull-right newpolicy' onclick='' role='button'><i class='fa fa-remove'></i>Del</a></td>";
                            } else {
                                resp += "<td><a class='btn btn-success toggle-code-handle pull-right newpolicy' onclick=EditItem('" + id + "','old','rec') role='button'><i class='fa fa-pencil'></i>Edit</a></td>";
                                resp += "<td><a class='btn btn-warning toggle-code-handle pull-right newpolicy' onclick=deleteItem('" + id + "','old','rec') role='button'><i class='fa fa-remove'></i>Del</a></td>";
                            }
                        }
                    }
                }
            }
            balance = parseFloat(balance.replace(/,/g, ""));
            balance -= total7;
            total1 = total1.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            total2 = total2.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            total3 = total3.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            total4 = total4.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            total5 = total5.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            total6 = total6.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            total7 = total7.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            balance = balance.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            resp += "<tr><td colspan='3' align='right'>TOTAL</td><td align='right'>" + total1 + "</td><td align='right'>" + total2 + "</td><td align='right'>" + total3 + "</td><td align='right'>" + total4 + "</td><td align='right'>" + total5 + "</td><td align='right'>" + total6 + "</td><td align='right'>" + total7 + "</td></tr>";
            resp += "</tr></tbody></table>";
            $('#active').html(resp);
            $("#balance").val(balance);
        }


        var prog_seg_g = null;
        addItem = function (arg,arg2,arg3) {
//alert(arg+"       "+arg2+"    "+arg3);
            if(arg2==='new'){
                $("#div_cost_a").hide(); $("#div_cost_e").hide(); $("#div_cost_d").hide();
                //$("#div_qty1_a").hide(); $("#div_qty1_e").hide(); $("#div_qty1_d").hide();
                $("#div_qty2_a").hide(); $("#div_qty2_e").hide(); $("#div_qty2_d").hide();
                $("#div_qty3_a").hide(); $("#div_qty3_e").hide(); $("#div_qty3_d").hide();
                $("#div_qty4_a").hide(); $("#div_qty4_e").hide(); $("#div_qty4_d").hide();
            }else{
                $("#div_cost_a").show(); $("#div_cost_e").show(); $("#div_cost_d").show();
                //$("#div_qty1_a").show(); $("#div_qty1_e").show(); $("#div_qty1_d").show();
                $("#div_qty2_a").show(); $("#div_qty2_e").show(); $("#div_qty2_d").show();
                $("#div_qty3_a").show(); $("#div_qty3_e").show(); $("#div_qty3_d").show();
                $("#div_qty4_a").show(); $("#div_qty4_e").show(); $("#div_qty4_d").show();
            }
            $("#div_1_a").hide(); $("#div_1_e").hide(); $("#div_1_d").hide();
            var budget_head_name = $('#budget_head0 option:selected').text();
            if (budget_head_name.indexOf("Capital") !== -1) {
                $("#addCap").show();
            }
            prog_seg_g = arg;
            $('#admin_seg_a').val($('#mda_id0').val()).trigger('change');
            $("#budget_amount_a").val("");
            $('#addModal').modal('show');
            
            $('#quantity_a_1').val("");
            $('#quantity_a_2').val("");
            $('#quantity_a_3').val("");
            $('#quantity_a_4').val("");
            
            $('#budget_amount_a_1').val(""); 
            $('#budget_amount_a_2').val(""); 
            $('#budget_amount_a_3').val(""); 
            $('#budget_amount_a_4').val(""); 
            //$('#divViewMeeting').modal('show');
        };

        finishAddItem = function () {
            var admin_seg = $('#admin_seg_a option:selected').text();
            admin_seg1 = admin_seg.split("[");
            admin_seg2 = admin_seg1[1].split("]");
            admin_seg = admin_seg2[0];
            var eco_seg = $('#eco_seg_a option:selected').val();
            var prog_seg = '99999999999999'; //$('#prog_seg_a option:selected').val();
            if (prog_seg_g !== null && prog_seg_g !== undefined && prog_seg_g !=="") {
                prog_seg = prog_seg_g;
            }
            var func_seg = $('#func_seg_a option:selected').val();
            var fund_seg = $('#fund_seg_a option:selected').val();
            var geo_seg = $('#geo_seg_a option:selected').val();
            var dept_seg = $('#dept_seg_a option:selected').val();
            if (dept_seg === undefined) {
                dept_seg = "";
            }
            //var budget_amount = $('#budget_amount_a').val().replace(/,/g, "");
            //var bal = $("#balance").val().replace(/,/g, "");
            //var newbal = parseFloat(bal) - parseFloat(budget_amount);
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
            //if (newbal < 0) {
            //    error += "Amount throws Envelope balance below zero<br>";
            //}
            /*if (budget_amount === "") {
                error += "Budget Amount must not be blank<br>";
            }
            if (isNaN(budget_amount)) {
                error += "Budget Amount must be numeric<br>";
            }*/
            if (error.length > 0) {
                toastr["error"](error, "Please correct the following:");
                return true;
            }
            var cost = $('#cost_a').val().replace(/,/g, "");
            var quantity_a_1 = $('#quantity_a_1').val().replace(/,/g, "");
            var budget_amount_a_1 = $('#budget_amount_a_1').val().replace(/,/g, "");
            var quantity_a_2 = $('#quantity_a_2').val().replace(/,/g, "");
            var budget_amount_a_2 = $('#budget_amount_a_2').val().replace(/,/g, "");
            var quantity_a_3 = $('#quantity_a_3').val().replace(/,/g, "");
            var budget_amount_a_3 = $('#budget_amount_a_3').val().replace(/,/g, "");
            var quantity_a_4 = $('#quantity_a_4').val().replace(/,/g, "");
            var budget_amount_a_4 = $('#budget_amount_a_4').val().replace(/,/g, "");
            var budget_year = (parseInt(budget_year_id) - 1).toString();
            
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                data: {option: "<%= Utility.OPTION_INSERT%>", admin_segment: admin_seg, programme_segment: prog_seg, economic_segment: eco_seg,
                    functional_segment: func_seg, fund_segment: fund_seg, geo_segment: geo_seg, dept_id: dept_seg, budget_year_id: budget_year,
                    budget_amount: budget_amount_a_1, cost: cost, quantity: quantity_a_1},
                dataType: "json",
                cache: false,
                async: false,
                success: function (data) {
                    $('#addModal').modal('hide');
                    if (data.indexOf("INSERTED") !== -1) {
                        var prog_segment = data.split("_");
                        prog_seg = prog_segment[1];
                        budget_year = (parseInt(budget_year_id) + 0).toString();
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                            data: {option: "<%= Utility.OPTION_INSERT%>", admin_segment: admin_seg, programme_segment: prog_seg, economic_segment: eco_seg,
                                functional_segment: func_seg, fund_segment: fund_seg, geo_segment: geo_seg, dept_id: dept_seg, budget_year_id: budget_year,
                                budget_amount: budget_amount_a_2, cost: cost, quantity: quantity_a_2},
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: function (data) {
                                $('#addModal').modal('hide');
                                if (data.indexOf("INSERTED") !== -1) {
                                    var prog_segment = data.split("_");
                                    prog_seg = prog_segment[1];
                                    budget_year = (parseInt(budget_year_id) + 1).toString();
                                    $.ajax({
                                        type: "GET",
                                        url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                                        data: {option: "<%= Utility.OPTION_INSERT%>", admin_segment: admin_seg, programme_segment: prog_seg, economic_segment: eco_seg,
                                            functional_segment: func_seg, fund_segment: fund_seg, geo_segment: geo_seg, dept_id: dept_seg, budget_year_id: budget_year,
                                            budget_amount: budget_amount_a_3, cost: cost, quantity: quantity_a_3},
                                        dataType: "json",
                                        cache: false,
                                        async: false,
                                        success: function (data) {
                                            $('#addModal').modal('hide');
                                            if (data.indexOf("INSERTED") !== -1) {
                                                var prog_segment = data.split("_");
                                                prog_seg = prog_segment[1];
                                                budget_year = (parseInt(budget_year_id) + 2).toString();
                                                $.ajax({
                                                    type: "GET",
                                                    url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                                                    data: {option: "<%= Utility.OPTION_INSERT%>", admin_segment: admin_seg, programme_segment: prog_seg, economic_segment: eco_seg,
                                                        functional_segment: func_seg, fund_segment: fund_seg, geo_segment: geo_seg, dept_id: dept_seg, budget_year_id: budget_year,
                                                        budget_amount: budget_amount_a_4, cost: cost, quantity: quantity_a_4},
                                                    dataType: "json",
                                                    cache: false,
                                                    async: false,
                                                    success: function (data) {
                                                        $('#addModal').modal('hide');
                                                        if (data.indexOf("INSERTED") !== -1) {
                                                            toastr["success"]("Record Insertion Successful!", "Record Deleted!!!");
                                                            getMTSSCostingEntries("budget_head0");
                                                        } else {
                                                            toastr["error"]("Record already exists!", "Record Exists!!!");
                                                        }
                                                    },
                                                    error: function () {
                                                        $('#addModal').modal('hide');
                                                        toastr["error"]("Record not found!", "Record Insertion Failed!!!");
                                                    }
                                                });                        
                                                toastr["success"]("Record Insertion Successful!", "Record Deleted!!!");
                                                getMTSSCostingEntries("budget_head0");
                                            } else {
                                                toastr["error"]("Record already exists!", "Record Exists!!!");
                                            }
                                        },
                                        error: function () {
                                            $('#addModal').modal('hide');
                                            toastr["error"]("Record not found!", "Record Insertion Failed!!!");
                                        }
                                    });                        
                                    toastr["success"]("Record Insertion Successful!", "Record Deleted!!!");
                                    getMTSSCostingEntries("budget_head0");
                                } else {
                                    toastr["error"]("Record already exists!", "Record Exists!!!");
                                }
                            },
                            error: function () {
                                $('#addModal').modal('hide');
                                toastr["error"]("Record not found!", "Record Insertion Failed!!!");
                            }
                        });                        
                        toastr["success"]("Record Insertion Successful!", "Record Deleted!!!");
                        getMTSSCostingEntries("budget_head0");
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
        
        EditItem = function (arg, arg2, arg3) {
        //alert(arg+"   "+arg2+"   "+arg3);
            if(arg3==='cap'){
                $("#div_cost_a").show(); $("#div_cost_e").show(); $("#div_cost_d").show();
                //$("#div_qty1_a").show(); $("#div_qty1_e").show(); $("#div_qty1_d").show();
                $("#div_qty2_a").show(); $("#div_qty2_e").show(); $("#div_qty2_d").show();
                $("#div_qty3_a").show(); $("#div_qty3_e").show(); $("#div_qty3_d").show();
                $("#div_qty4_a").show(); $("#div_qty4_e").show(); $("#div_qty4_d").show();
            }else if(arg2!=='new'){
                $("#div_cost_a").hide(); $("#div_cost_e").hide(); $("#div_cost_d").hide();
                //$("#div_qty1_a").hide(); $("#div_qty1_e").hide(); $("#div_qty1_d").hide();
                $("#div_qty2_a").hide(); $("#div_qty2_e").hide(); $("#div_qty2_d").hide();
                $("#div_qty3_a").hide(); $("#div_qty3_e").hide(); $("#div_qty3_d").hide();
                $("#div_qty4_a").hide(); $("#div_qty4_e").hide(); $("#div_qty4_d").hide();
            }else  {
                $("#div_cost_a").show(); $("#div_cost_e").show(); $("#div_cost_d").show();
                //$("#div_qty1_a").show(); $("#div_qty1_e").show(); $("#div_qty1_d").show();
                $("#div_qty2_a").show(); $("#div_qty2_e").show(); $("#div_qty2_d").show();
                $("#div_qty3_a").show(); $("#div_qty3_e").show(); $("#div_qty3_d").show();
                $("#div_qty4_a").show(); $("#div_qty4_e").show(); $("#div_qty4_d").show();
            }
            $("#div_1_a").hide(); $("#div_1_e").hide(); $("#div_1_d").hide();
            
//alert(arg);
            //var budget_head_name = $('#budget_head0 option:selected').text();
            //if (budget_head_name.indexOf("Capital") !== -1) {
            //    $("#editCap").show();
            //}
            $('#editModal').modal('show');
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                data: {option: "<%= Utility.OPTION_SELECT_BY_ID%>", id: arg},
                dataType: "json",
                cache: false,
                async: false,
                success: function (data) {
//console.log(data);
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
                                    $('#budget_amount_e_2').val(attrValue);
                                }
                                if (attrName === "12") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#cost_e').val(attrValue);
                                }
                                if (attrName === "13") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#quantity_e_2').val(attrValue);
                                }
                                if (attrName === "14") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#quantity_e_3').val(attrValue);
                                }
                                if (attrName === "15") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#budget_amount_e_3').val(attrValue);
                                }
                                if (attrName === "16") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#quantity_e_4').val(attrValue);
                                }
                                if (attrName === "17") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#budget_amount_e_4').val(attrValue);
                                }
                                if (attrName === "18") {
                                //    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                //    $('#quantity_e_4').val(attrValue);
                                }
                                if (attrName === "19") {
                                //    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                //    $('#budget_amount_e_4').val(attrValue);
                                }
                                if (attrName === "20") {
                                    document.getElementById("txtId_e_2").value = attrValue;
                                }
                                if (attrName === "21") {
                                    document.getElementById("txtId_e_3").value = attrValue;
                                }
                                if (attrName === "22") {
                                    document.getElementById("txtId_e_4").value = attrValue;
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
                    toastr["error"]("Record not found!", "Record Selection Failed!!!");
                }
            });
        };


        FinishEdit = function () {
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
            //var budget_amount = $('#budget_amount_e').val().replace(/,/g, "");
            //var oldval = $("#oldval").val().replace(/,/g, "");
            //var bal = $("#balance").val().replace(/,/g, "");
            //var newbal = parseFloat(bal) + parseFloat(oldval) - parseFloat(budget_amount);
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
            /*if (newbal < 0) {
                error += "Amount throws Envelope balance below zero<br>";
            }
            if (budget_amount === "") {
                error += "Budget Amount must not be blank<br>";
            }
            if (isNaN(budget_amount)) {
                error += "Budget Amount must be numeric<br>";
            }*/
            if (error.length > 0) {
                toastr["error"](error, "Please correct the following:");
                return true;
            }
            var cost = $('#cost_e').val().replace(/,/g, "");
//            var quantity_e_1 = $('#quantity_e_1').val().replace(/,/g, "");
//            var budget_amount_e_1 = $('#budget_amount_e_1').val().replace(/,/g, "");
            var quantity_e_1 = $('#quantity_e_2').val().replace(/,/g, "");
            var budget_amount_e_1 = $('#budget_amount_e_2').val().replace(/,/g, "");
            var quantity_e_2 = $('#quantity_e_3').val().replace(/,/g, "");
            var budget_amount_e_2 = $('#budget_amount_e_3').val().replace(/,/g, "");
            var quantity_e_3 = $('#quantity_e_4').val().replace(/,/g, "");
            var budget_amount_e_3 = $('#budget_amount_e_4').val().replace(/,/g, "");
            //var budget_year = (parseInt(budget_year_id) - 1).toString();
            var budget_year = (parseInt(budget_year_id)).toString();
            //alert(budget_amount_e_1);
//console.log("A. id: "+id+"    budget_year: "+budget_year+"    quantity_e_1: "+quantity_e_1+"    budget_amount_e_1: "+budget_amount_e_1);
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                data: {option: "<%= Utility.OPTION_UPDATE%>", id: id, admin_segment: admin_seg, programme_segment: prog_seg, economic_segment: eco_seg,
                    functional_segment: func_seg, fund_segment: fund_seg, geo_segment: geo_seg, dept_id: dept_seg, budget_year_id: budget_year,
                    budget_amount: budget_amount_e_1, cost: cost, quantity: quantity_e_1},
                dataType: "json",
                cache: false,
                async: false,
                success: function (data) {
                    $('#addModal').modal('hide');
                    if (data.indexOf("UPDATED") !== -1) {
                        var prog_segment = data.split("_");
                        prog_seg = prog_segment[1];
                        //var budget_year = (parseInt(budget_year_id) + 0).toString();
                        var budget_year = (parseInt(budget_year_id) + 1).toString();
                        var id = document.getElementById("txtId_e_2").value;
//console.log("B. id: "+id+"    budget_year: "+budget_year+"    quantity_e_1: "+quantity_e_1+"    budget_amount_e_1: "+budget_amount_e_1);
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                            data: {option: "<%= Utility.OPTION_UPDATE%>", id: id, admin_segment: admin_seg, programme_segment: prog_seg, economic_segment: eco_seg,
                                functional_segment: func_seg, fund_segment: fund_seg, geo_segment: geo_seg, dept_id: dept_seg, budget_year_id: budget_year,
                                budget_amount: budget_amount_e_2, cost: cost, quantity: quantity_e_2},
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: function (data) {
                                $('#addModal').modal('hide');
                                if (data.indexOf("UPDATED") !== -1) {
                                    var prog_segment = data.split("_");
                                    prog_seg = prog_segment[1];
                                    //var budget_year = (parseInt(budget_year_id) + 1).toString();
                                    var budget_year = (parseInt(budget_year_id) + 2).toString();
                                    var id = document.getElementById("txtId_e_3").value;
 //console.log("C. id: "+id+"    budget_year: "+budget_year+"    quantity_e_1: "+quantity_e_1+"    budget_amount_e_1: "+budget_amount_e_1);
                                   $.ajax({
                                        type: "GET",
                                        url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                                        data: {option: "<%= Utility.OPTION_UPDATE%>", id: id, admin_segment: admin_seg, programme_segment: prog_seg, economic_segment: eco_seg,
                                            functional_segment: func_seg, fund_segment: fund_seg, geo_segment: geo_seg, dept_id: dept_seg, budget_year_id: budget_year,
                                            budget_amount: budget_amount_e_3, cost: cost, quantity: quantity_e_3},
                                        dataType: "json",
                                        cache: false,
                                        async: false,
                                        success: function (data) {
                                            $('#addModal').modal('hide');
                                            if (data.indexOf("UPDATED") !== -1) {
//                                                var prog_segment = data.split("_");
//                                                prog_seg = prog_segment[1];
//                                                var budget_year = (parseInt(budget_year_id) + 2).toString();
//                                                var id = document.getElementById("txtId_e_4").value;
//console.log("D. id: "+id+"    budget_year: "+budget_year+"    quantity_e_1: "+quantity_e_1+"    budget_amount_e_1: "+budget_amount_e_1);
//                                                $.ajax({
//                                                    type: "GET",
//                                                    url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
//                                                    data: {option: "<%= Utility.OPTION_UPDATE%>", id: id, admin_segment: admin_seg, programme_segment: prog_seg, economic_segment: eco_seg,
//                                                        functional_segment: func_seg, fund_segment: fund_seg, geo_segment: geo_seg, dept_id: dept_seg, budget_year_id: budget_year,
//                                                        budget_amount: budget_amount_e_4, cost: cost, quantity: quantity_e_4},
//                                                    dataType: "json",
//                                                    cache: false,
//                                                    async: false,
//                                                    success: function (data) {
//                                                        $('#editModal').modal('hide');
//                                                        if (data.indexOf("UPDATED") !== -1) {
//                                                            toastr["success"]("Record Update Successful!", "Record Updated!!!");
//                                                            getMTSSCostingEntries("budget_head0");
//                                                        } else {
//                                                            toastr["error"]("Record Update failed!", "No Record Updated!!!");
//                                                        }
//                                                    },
//                                                    error: function () {
//                                                        $('#editModal').modal('hide');
//                                                        toastr["error"]("Record not found!", "Record Update Failed!!!");
//                                                    }
//                                                });                        
                                                toastr["success"]("Record Update Successful!", "Record Updated!!!");
                                                getMTSSCostingEntries("budget_head0");
                                            } else {
                                                toastr["error"]("Record Update failed!", "No Record Updated!!!");
                                            }
                                        },
                                        error: function () {
                                            $('#editModal').modal('hide');
                                            toastr["error"]("Record not found!", "Record Update Failed!!!");
                                        }
                                    });                        
                                    toastr["success"]("Record Update Successful!", "Record Updated!!!");
                                    getMTSSCostingEntries("budget_head0");
                                } else {
                                    toastr["error"]("Record Update failed!", "No Record Updated!!!");
                                }
                            },
                            error: function () {
                                $('#addModal').modal('hide');
                                toastr["error"]("Record not found!", "Record Update Failed!!!");
                            }
                        });                        
                        toastr["success"]("Record Insertion Successful!", "Record Deleted!!!");
                        getMTSSCostingEntries("budget_head0");
                    } else {
                        toastr["error"]("Record Update failed!", "No Record Updated!!!");
                    }
                },
                error: function () {
                    $('#addModal').modal('hide');
                    toastr["error"]("Record not found!", "Record Update Failed!!!");
                }
            });
        };
        
        var ReturnToList = function () {
            $('#create-area').hide();
            $('#MainSection .panel-title').hide();
            $('#list-area').fadeIn();
        };

        deleteItem = function (arg,arg2,arg3) {
            if(arg2!=='new'){
                $("#div_cost_a").hide(); $("#div_cost_e").hide(); $("#div_cost_d").hide();
                $("#div_qty1_a").hide(); $("#div_qty1_e").hide(); $("#div_qty1_d").hide();
                $("#div_qty2_a").hide(); $("#div_qty2_e").hide(); $("#div_qty2_d").hide();
                $("#div_qty3_a").hide(); $("#div_qty3_e").hide(); $("#div_qty3_d").hide();
                $("#div_qty4_a").hide(); $("#div_qty4_e").hide(); $("#div_qty4_d").hide();
            }else{
                $("#div_cost_a").show(); $("#div_cost_e").show(); $("#div_cost_d").show();
                $("#div_qty1_a").show(); $("#div_qty1_e").show(); $("#div_qty1_d").show();
                $("#div_qty2_a").show(); $("#div_qty2_e").show(); $("#div_qty2_d").show();
                $("#div_qty3_a").show(); $("#div_qty3_e").show(); $("#div_qty3_d").show();
                $("#div_qty4_a").show(); $("#div_qty4_e").show(); $("#div_qty4_d").show();
            }
            var budget_head_name = $('#budget_head0 option:selected').text();
            if (budget_head_name.indexOf("Capital") !== -1) {
                $("#delCap").show();
            }
            window.deleteId = arg;
            $('#deleteModal').modal('show');
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
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
                                    $('#budget_amount_d_2').val(attrValue);
                                }
                                if (attrName === "12") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#cost_d').val(attrValue);
                                }
                                if (attrName === "13") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#quantity_d_2').val(attrValue);
                                }
                                if (attrName === "14") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#quantity_d_3').val(attrValue);
                                }
                                if (attrName === "15") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#budget_amount_d_3').val(attrValue);
                                }
                                if (attrName === "16") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#quantity_d_4').val(attrValue);
                                }
                                if (attrName === "17") {
                                //    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                //    $('#budget_amount_d_4').val(attrValue);
                                }
                                if (attrName === "18") {
                                //    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                //    $('#quantity_d_4').val(attrValue);
                                }
                                if (attrName === "19") {
                                    attrValue = parseFloat(attrValue).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                    $('#budget_amount_d_4').val(attrValue);
                                }
                                if (attrName === "20") {
                                    document.getElementById("txtId_d_2").value = attrValue;
                                }
                                if (attrName === "21") {
                                    document.getElementById("txtId_d_3").value = attrValue;
                                }
                                if (attrName === "22") {
                                    document.getElementById("txtId_d_4").value = attrValue;
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
                    toastr["error"]("Record not found!", "Record Selection Failed!!!");
                }
            });
        };

        finishDeleteItem = function () {
            var id = document.getElementById("txtId_d").value;
            id += "_"+document.getElementById("txtId_d_2").value;
            id += "_"+document.getElementById("txtId_d_3").value;
            id += "_"+document.getElementById("txtId_d_4").value;
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/MtssCostingServlet",
                data: {option: "<%= Utility.OPTION_DELETE%>", id: id},
                dataType: "json",
                cache: false,
                async: false,
                success: function (data) {
                    $('#deleteModal').modal('hide');
                    if (data === "<%= Utility.ActionResponse.DELETED%>") {
                        toastr["success"]("Record Delete Successful!", "Record Deleted!!!");
                        getMTSSCostingEntries("budget_head0");
                    } else {
                        toastr["error"]("Record Delete Failed!", "No Record Deleted!!!");
                    }
                },
                error: function () {
                    $('#deleteModal').modal('hide');
                    toastr["error"]("Record not found!", "Record Delete Failed!!!");
                }
            });
        };

        var removeWindowId = function () {
            //$("#addCap").hide();
            //$("#editCap").hide();
            //$("#delCap").hide();
            window.deleteId = 0;
        };

    </script>

</body>
</html>
