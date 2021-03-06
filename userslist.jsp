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
        <link rel="stylesheet" href="css/jquery.loading.css" media="screen">																			
        <!-- ========== MODERNIZR ========== -->
        <script src="js/modernizr/modernizr.min.js"></script>
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
                                    <h2 class="title">Users</h2>
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
                                        <li class="active">User management</li>
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

                                                <div class="col-lg-6">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" placeholder="find user ..." id="finduser">
                                                        <span class="input-group-btn">
                                                            <button class="btn btn-default" onclick="getUsers();" type="button">Go!</button>
                                                        </span>
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <div class="col-lg-6 right-side">
                                                    <a class="btn btn-info toggle-code-handle pull-right newuser" onclick="CreateUser();" role="button"><i class="fa fa-user-plus"> </i>New User</a>
                                                </div>
                                                <br style="clear: both;" />
                                                <!-- Nav tabs -->
                                                <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                    <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">Active</a></li>
                                                    <li role="presentation" class=""><a class="" href="#blocked" aria-controls="blocked" role="tab" data-toggle="tab" aria-expanded="false">Blocked</a></li>
                                                </ul>

                                                <!-- Tab panes -->
                                                <div class="tab-content bg-white p-15" style="overflow: auto">
                                                    <div role="tabpanel" class="tab-pane active" id="active">
                                                        
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane" id="blocked">
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                    </div>
                                    <!-- /.col-md-12 -->

                                </div>
                                <!-- /.row -->
                                
                                <div class="row mt-30" id="CreateArea" style="display: none;">

                                    <div class="col-md-3">
                                        <div class="panel border-primary no-border border-3-top">

                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-10 col-md-offset-1">
                                                        <img id="user_dp_a" src="images/user-avatar.jpg" alt="User Picture" class="img-responsive" style="width: 100%; border-radius: 50%;">

                                                        <div class="text-center">
                                                            <button onclick="TriggerFileInput()" type="button" class="btn btn-primary btn-xs btn-labeled mt-10">Edit Picture<span class="btn-label btn-label-right"><i class="fa fa-pencil"></i></span></button>
                                                            <input id="the-file-input_a" type="file" accept="image/*" style="width:0px; height:0px; visibility:hidden;">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.panel -->

                                        <!--table class="table table-striped">
                                            <tbody>

                                                <tr>
                                                    <th>Last Login</th>
                                                    <td>
                                                        12-Mar-2017<br>
                                                        <small class="color-warning">40 days ago</small>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table-->
                                        <!-- /.panel -->
                                        <div class="panel border-primary no-border border-3-top">

                                            <div class="panel-body p-20">
                                                <div class="mb-20">
                                                    <input type="checkbox" class="js-switch" id="chkActive_a" />
                                                </div>

                                                <!--button type="submit" class="btn btn-info btn-labeled">
                                                    Reset Password
                                                    <span class="btn-label btn-label-right"><i class="fa fa-refresh"></i></span>
                                                </button-->
                                            </div>
                                        </div>
                                        <!-- /.panel -->
                                    </div>
                                    <!-- /.col-md-3 -->
                                    <div class="col-md-8">

                                        <div class="panel border-primary no-border border-3-top">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h5></h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <form>
                                                    <div class="form-group">
                                                        <label for="txtEmail_a">Username/Email</label>
                                                        <input type="email" class="form-control" id="txtEmail_a" placeholder="username/email" autofocus="">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="txtFirstname_a">Firstname</label>
                                                        <input type="text" class="form-control" id="txtFirstname_a" placeholder="firstname">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="txtLastname_a">Lastname</label>
                                                        <input type="text" class="form-control" id="txtLastname_a" placeholder="lastname">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="txtPhoneno_a">Phone No</label>
                                                        <input type="text" class="form-control" id="txtPhoneno_a" placeholder="phoneno">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="selMda_a">MDA</label>
                                                        <select class='form-control custom_select' id='selMda_a' onchange='getDepartments();setDepartment(this.id);'></select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="selDepartment_a">Department</label>
                                                        <select class='js-states form-control custom_select' id='selDepartment_a'></select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for='selRoles_a'>Roles</label>
                                                        <select class='js-states form-control custom_select' id='selRoles_a' multiple='multiple'>
                                                        </select>
                                                    </div>
                                                    <hr />
                                                    <div class="form-group">
                                                            <button type="button" onclick="FinishCreate();" class="btn btn-success btn-labeled" style="float: right;">
                                                                Create User
                                                                <span class="btn-label btn-label-right"><i class="fa fa-save"></i></span>
                                                            </button>
                                                            <button type="button" onclick="cancelAdd()" class="btn btn-danger btn-labeled" style="float: left;">
                                                                Cancel Add User 
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

                                    <div class="col-md-3">
                                        <div class="panel border-primary no-border border-3-top">

                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-10 col-md-offset-1">
                                                        <img id="user_dp_e" src="images/user-avatar.jpg" alt="User Picture" class="img-responsive" style="width: 100%; border-radius: 50%;">

                                                        <div class="text-center">
                                                            <button onclick="TriggerFileInput()" type="button" class="btn btn-primary btn-xs btn-labeled mt-10">Edit Picture<span class="btn-label btn-label-right"><i class="fa fa-pencil"></i></span></button>
                                                            <input id="the-file-input_e" type="file" accept="image/*" style="width:0px; height:0px; visibility:hidden;">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.panel -->
                                        
                                        <div id="lastdate"></div>
                                        
                                        <!-- /.panel -->
                                        <div class="panel border-primary no-border border-3-top">

                                            <div class="panel-body p-20">
                                                <div class="mb-20">
                                                    <input type="checkbox" class="js-switch" id="chkActive_e" />
                                                </div>

                                                <button type="button" class="btn btn-info btn-labeled" onclick="resetPassword();">
                                                    Reset Password
                                                    <span class="btn-label btn-label-right"><i class="fa fa-refresh"></i></span>
                                                </button>
                                            </div>
                                        </div>
                                        <!-- /.panel -->
                                    </div>
                                    <!-- /.col-md-3 -->
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
                                                    <div class="form-group">
                                                        <label for="txtEmail_e">Username/Email</label>
                                                        <input type="email" class="form-control" id="txtEmail_e" readonly=""  placeholder="username/email" autofocus="">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="txtFirstname_e">Firstname</label>
                                                        <input type="text" class="form-control" id="txtFirstname_e" placeholder="firstname">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="txtLastname_e">Lastname</label>
                                                        <input type="text" class="form-control" id="txtLastname_e" placeholder="lastname">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="txtPhoneno_e">Phone No</label>
                                                        <input type="text" class="form-control" id="txtPhoneno_e" placeholder="phoneno">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="selMda_e">MDA</label>
                                                        <select class='form-control custom_select' id='selMda_e' onchange='getDepartments();setDepartment(this.id);'></select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="selDepartment_e">Department</label>
                                                        <select class='js-states form-control custom_select' id='selDepartment_e'></select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for='selRoles_e'>Roles</label>
                                                        <select class='js-states form-control custom_select' id='selRoles_e' multiple='multiple'></select>
                                                    </div>
                                                    <hr />
                                                    <div class="form-group">
                                                        <button type="button" onclick="FinishEdit()" class="btn btn-success btn-labeled" style="float: right;">
                                                            Update User
                                                            <span class="btn-label btn-label-right"><i class="fa fa-save"></i></span>
                                                        </button>
                                                        <button type="button" onclick="cancelEdit()" class="btn btn-danger btn-labeled" style="float: left;">
                                                            Cancel Edit User 
                                                            <span class="btn-label btn-label-right"><i class="fa fa-remove"></i></span>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col-md-9 -->


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
                                    <div class="col-md-12">

                                        <div class="panel border-primary no-border border-3-top">
                                            <div class="panel-body p-20" style="padding-top: 0px !important;">
                                                <form>

                                                    <div class="form-group">
                                                        <button type="button" onclick="SaveNewImage()" class="btn btn-success btn-labeled mr-10 btn-sm" style="float: left;">
                                                            Save Image
                                                            <span class="btn-label btn-label-right"><i class="fa fa-save"></i></span>
                                                        </button>
                                                        <button type="button" onclick="TriggerFileInput()" class="btn btn-info btn-labeled mr-10 btn-sm" style="float: left;">
                                                            Change Image
                                                            <span class="btn-label btn-label-right"><i class="fa fa-refresh"></i></span>
                                                        </button>
                                                        <button type="button" onclick="CloseImageEdit()" class="btn btn-danger btn-labeled mr-10 btn-sm" style="float: left;">
                                                            Cancel
                                                            <span class="btn-label btn-label-right"><i class="fa fa-times"></i></span>
                                                        </button>


                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col-md-9 -->
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
        <input type="hidden" id="site-url" value="<%= Utility.SITE_URL %>">

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
            $(document).ready(function(){
                createCookie("formtype", "");
                getMdas();
                getDepartments();
                getRoles();
                getUsers();
            });
            
            EditUser = function (arg) {
                createCookie("formtype", "edituser");
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/UserServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD %>", id: arg},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function(data){
                        //console.log(data);
                        if(data.length>0){
                            var lastday = "";
                            for (var i = 0; i < data.length; i++) {
                                var obj = data[i];
                                for (var key in obj) {
                                    var attrName = key;
                                    var attrValue = obj[key];
                                  
                                    //alert(attrName+"    "+attrValue+"    "+typeof(attrValue));
                                        
                                    if(attrValue === null || attrValue === 'null'){
                                        attrValue = "";
                                    }
                                    if(attrName === "0"){
                                        document.getElementById("txtId_e").value = attrValue;
                                    }
                                    if(attrName === "1"){
                                        if(attrValue === ""){
                                            document.getElementById("user_dp_e").src = "images/user-avatar.jpg";
                                        }else{
                                            document.getElementById("user_dp_e").src = attrValue;
                                            ImageBase64 = attrValue;
                                        }
                                    }
                                    if(attrName === "2"){
                                        document.getElementById("txtEmail_e").value = attrValue;
                                    }
                                    if(attrName === "3"){
                                        document.getElementById("txtFirstname_e").value = attrValue;
                                    }
                                    if(attrName === "4"){
                                        document.getElementById("txtLastname_e").value = attrValue;
                                    }
                                    if(attrName === "5"){
                                        if(attrValue === "true"){
                                            $('#chkActive_e').bootstrapSwitch('state', true);
                                        }else{
                                            $('#chkActive_e').bootstrapSwitch('state', false);
                                        }
                                    }
                                    if(attrName === "6"){
                                        //console.log(attrValue);
                                        $('#selMda_e').val(attrValue).trigger('change');
                                    }
                                    if(attrName === "7"){
                                        //alert(attrValue+"       "+typeof(attrValue));
                                        $('#selDepartment_e').val(attrValue).trigger('change');
                                    }
                                    if(attrName === "8"){
                                        lastday = attrValue;
                                    }
                                    if(attrName === "9"){
                                        if(attrValue===0){
                                            attrValue = " Today";
                                        }else if(attrValue===1){
                                            attrValue = attrValue+" day ago";
                                        }else{
                                            attrValue = attrValue+" days ago";
                                        }
                                        var resp = "<table class='table table-striped'><tbody><tr><th><small class='color-info'>Last&nbsp;Login</small></th>";
                                            resp += "<td><small class='color-warning'>"+lastday+"</small><br><br><small class='color-warning'>"+attrValue+"</small></td></tr></tbody></table>";
                                        //document.getElementById("lastdate").InnerHTML = resp;
                                        $('#lastdate').html(resp);
                                    }
                                    if(attrName === "10"){
                                        document.getElementById("txtPhoneno_e").value = attrValue;
                                    }
                                    if(attrName === "11"){
                                        //alert(attrValue);
                                            var obj = eval('[' + attrValue + ']');
                                        $('#selRoles_e').val(obj).trigger('change');
                                    }
                                }
                                break;
                            }
                            toastr["success"]("User record successfully fetched!", "User Selection Successfull!!!");
                            $('#MainArea').hide();
                            $('#EditArea').fadeIn();
                            $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li onclick='FinishEdit()'> User management</li><li class='active' >Edit User</li>");
                            document.getElementById("txtFirstname_e").focus();
                        }
                    },
                    error: function(){ 
                        toastr["error"]("Record with id " + arg + " is not found!", "User Selection Failed!!!");
                    }
                });
            };
            
            FinishEdit = function () {
                var txtEmail_e = document.getElementById("txtEmail_e").value;
                var txtFirstname_e = document.getElementById("txtFirstname_e").value;
                var txtLastname_e = document.getElementById("txtLastname_e").value;
                var selMda_e = document.getElementById("selMda_e").value;
                var selDepartment_e = document.getElementById("selDepartment_e").value;
                var selRoles_e = document.getElementById("selRoles_e");
                var selRoles = "";
                for(var i=0;i<selRoles_e.length;i++){
                    if(selRoles_e[i].selected === true){
                        selRoles += selRoles_e[i].value + ",";
                    }
                }
                var userstatus = document.getElementById("chkActive_e").checked;
                var txtPhoneno_e = document.getElementById("txtPhoneno_e").value;
                //var userimage = ImageBase64;txtPhoneno
                var error = "";
                if(txtEmail_e===""){ error +="<br>Username must not be blank<br>"; }
                if(txtFirstname_e===""){ error +="Firstname must not be blank<br>"; }
                if(txtLastname_e===""){ error +="Lastname must not be blank<br>"; }
                if(selMda_e===""){ error +="MDA must not be blank<br>"; }
                if(selDepartment_e===""){ error +="Department must not be blank<br>"; }
                if(selRoles===""){ error +="Roles must not be blank<br>"; }
                if(error!==""){
                    toastr["error"](error, "Please Correct The Following Error(s)!!!");
                    return true;
                }
                //document.getElementById("txtFirstname_e").focus();
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL %>/UserServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE %>", email: txtEmail_e, username: txtEmail_e, firstname: txtFirstname_e, lastname: txtLastname_e, deptId: selDepartment_e, mdaId: selMda_e, roleId: selRoles, userstatus: userstatus, userimage: ImageBase64, phoneNo: txtPhoneno_e},
                    //contentType: "application/json; charset=utf-8",
                    contentType: "application/x-www-form-urlencoded; multipart/form-data; charset=utf-8; ",
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(data){
                        if (data.indexOf("<%= Utility.ActionResponse.NO_RECORD.toString() %>")!== -1) {
                            toastr["error"]("Username does not exist!", "User Editing Failed!!!");
                        }else if (data.indexOf("<%= Utility.ActionResponse.UPDATED.toString() %>")!== -1) {
                            getUsers();
                            toastr["success"]("User record successfully updated!", "User Editing Successfull!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown){
                    error: function(){
                        toastr["error"]("The server is not accessible!", "User Editing Failed!!!");
                    }
                });
                $('#EditArea').hide();
                $('#MainArea').fadeIn();
                $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li class='active'> User management</li>");
            };
            cancelEdit = function () {
                $('#EditArea').hide();
                $('#MainArea').fadeIn();
                $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li class='active'> User management</li>");
            };
            
            CreateUser = function () {
                $("#txtEmail_a").val("");
                $("#txtFirstname_a").val("");
                $("#txtLastname_a").val("");
                $("#selMda_a").val("0");
                $("#selDepartment_a").val("0");
                $("#selRoles_a").val("0");
                $("#txtPhoneno_a").val("");
                $("#chkActive_a").val("0");
//alert($("#txtEmail_a").val());
//alert($("#txtFirstname_a").val());
//alert($("#txtLastname_a").val());
//alert($("#selMda_a").val());
//alert($("#selDepartment_a").val());
//alert($("#selRoles_a").val());
//alert($("#txtPhoneno_a").val());
//alert($("#chkActive_a").val());
                createCookie("formtype", "createuser");
                $('#MainArea').hide();
                $('#CreateArea').fadeIn();
                $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li onclick='FinishEdit()'> User management</li><li class='active' >Add User</li>");
//                document.getElementById("txtEmail_a").value="";
//                document.getElementById("txtFirstname_a").value="";
//                document.getElementById("txtLastname_a").value="";
//                document.getElementById("txtEmail_a").value="";
//                document.getElementById("txtEmail_a").value="";
//                document.getElementById("txtEmail_a").value="";
//                document.getElementById("txtEmail_a").value="";
//                document.getElementById("txtEmail_a").value="";
//                $("#txtFirstname_a").val("");
//                $("#txtLastname_a").val("");
//                $("#selMda_a").val("0");
//                $("#selDepartment_a").val("0");
//                $("#selRoles_a").val("0");
//                $("#txtPhoneno_a").val("");
//                $("#chkActive_a").val("");
            };
            FinishCreate = function () {
                var txtEmail_a = document.getElementById("txtEmail_a").value;
                var txtFirstname_a = document.getElementById("txtFirstname_a").value;
                var txtLastname_a = document.getElementById("txtLastname_a").value;
                var selMda_a = document.getElementById("selMda_a").value;
                var selDepartment_a = document.getElementById("selDepartment_a").value;
                var selRoles_a = document.getElementById("selRoles_a");
                var selRoles = "";
                for(var i=0;i<selRoles_a.length;i++){
                    if(selRoles_a[i].selected === true){
                        selRoles += selRoles_a[i].value + ",";
                    }
                }
                var userstatus = document.getElementById("chkActive_a").checked;
                var txtPhoneno_a = document.getElementById("txtPhoneno_a").value;
                //var userimage = ImageBase64;
                var error = "";
                if(txtEmail_a===""){ error +="<br>Username must not be blank<br>"; }
                if(txtFirstname_a===""){ error +="Firstname must not be blank<br>"; }
                if(txtLastname_a===""){ error +="Lastname must not be blank<br>"; }
                if(selMda_a===""){ error +="MDA must not be blank<br>"; }
                if(selDepartment_a===""){ error +="Department must not be blank<br>"; }
                if(selRoles_a===""){ error +="Roles must not be blank<br>"; }
                if(error!==""){
                    toastr["error"](error, "Please Correct The Following Error(s)!!!");
                    return true;
                }
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL %>/UserServlet",
                    data: {option: "<%= Utility.OPTION_INSERT %>", email: txtEmail_a, username: txtEmail_a, firstname: txtFirstname_a, lastname: txtLastname_a, deptId: selDepartment_a, mdaId: selMda_a, roleId: selRoles, userstatus: userstatus, userimage: ImageBase64, phoneNo: txtPhoneno_a},
                    //contentType: "application/json; charset=utf-8",
                    contentType: "application/x-www-form-urlencoded; multipart/form-data; charset=utf-8; ",
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(data){
                        if (data.indexOf("<%= Utility.ActionResponse.RECORD_EXISTS.toString() %>")!== -1) {
                            toastr["error"]("Username already exists!", "User Creation Failed!!!");
                        }else if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString() %>")!== -1) {
                            getUsers();
                            toastr["success"]("User record successfully created!", "User Creation Successfull!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown){
                    error: function(){
                        toastr["error"]("The server is not accessible!", "User Creation Failed!!!");
                    }
                });
                $('#CreateArea').hide();
                $('#MainArea').fadeIn();
                $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li class='active'> User management</li>");
                            
            };
            cancelAdd = function () {
                $('#CreateArea').hide();
                $('#MainArea').fadeIn();
                $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li class='active'> User management</li>");
            };
            
            //image plugin code starts
            TriggerFileInput = function () {
                if(readCookie("formtype")==="createuser"){
                    $('#the-file-input_a').trigger('click');
                }
                if(readCookie("formtype")==="edituser"){
                    $('#the-file-input_e').trigger('click');
                }
            };

            GetImageData = function () {
                if($("#ImageToCrop").val() === undefined){
                    return "";
                }
                return $WorkingImage.cropper('getCroppedCanvas').toDataURL('image/jpeg');
            };
            FilePresent = false;

            function renderImage(file) {
                var reader = new FileReader();
                reader.onload = function (event) {
                    the_url = event.target.result;
                    $('#PreviewImage').html("<img id='ImageToCrop' src='" + the_url + "' />");
                    InitializeCropper();
                };
                reader.onerror = function (event) {
                    FilePresent = false;
                };
                reader.readAsDataURL(file);
            }

            InitializeCropper = function () {
                $WorkingImage = $("#ImageToCrop");

                var width = 200;
                var height = 200;
                var CropperSize = 200;
                if (width < height) {
                    CropperSize = width;
                } else {
                    CropperSize = height;
                }

                $WorkingImage.cropper({
                    aspectRatio: 1 / 1,
                    data: {
                        width: CropperSize,
                        height: CropperSize
                    }
                });

                FilePresent = true;
            };


            //image plugin code ends

            var ImageBase64="";
            SaveNewImage = function () {
                ImageBase64 = GetImageData();
                if(readCookie("formtype")==="createuser"){
                    $('#user_dp_a').attr('src', ImageBase64);
                }
                if(readCookie("formtype")==="edituser"){
                    $('#user_dp_e').attr('src', ImageBase64);
                }
                $WorkingImage.cropper('destroy');
                //setTimeout(fadeIn,1);
                CloseImageEdit();
            };

            CloseImageEdit = function () {
                $('#ImageArea').hide();
                if(readCookie("formtype")==="edituser"){
                    $('#EditArea').fadeIn();
                }
                if(readCookie("formtype")==="createuser"){
                    $('#CreateArea').fadeIn();
                }
            };
            $(function () {
                //here is the code for the cropper plugin
                $("#the-file-input_a").change(function () {
                    var fileInput = this;
                    $('#CreateArea').hide();
                    $('#ImageArea').fadeIn();
                    setTimeout(function () {
                        renderImage(fileInput.files[0]);
                    }, 1000);
                });
                $("#the-file-input_e").change(function () {
                    var fileInput = this;
                    $('#EditArea').hide();
                    $('#ImageArea').fadeIn();
                    setTimeout(function () {
                        renderImage(fileInput.files[0]);
                    }, 1000);
                });

                //end of cropper plugin code

                // Counter for dashboard stats
                $('.counter').counterUp({
                    delay: 10,
                    time: 1000
                });

                $("#modal11").iziModal();
                $('.newuser').on('click', function () {
                    $('#modal11').iziModal('open', this);
                });

                $("#selRoles_a, #selRoles_e, #selDepartment_a, #selDepartment_e, #selMda_a, #selMda_e").select2();
                $('#chkActive_a').bootstrapSwitch({onText: 'Active', offText: 'Blocked'});
                $('#chkActive_e').bootstrapSwitch({onText: 'Active', offText: 'Blocked'});
            });
            /*$('#selRoles_a').on("select2:unselecting", function (e) {
               var unselected_value = e.params.args.data.id;
            }).trigger('change');       

            function createCookie(name,value,days) {
                var expires = "";
                if (days) {
                    var date = new Date();
                    date.setTime(date.getTime()+(days*24*60*60*1000));
                    expires = "; expires="+date.toGMTString();
                }
                document.cookie = name+"="+value+expires+"; path=/";
            };

            function readCookie(name) {
                var nameEQ = name + "=";
                var ca = document.cookie.split(';');
                for(var i=0;i < ca.length;i++) {
                    var c = ca[i];
                    while (c.charAt(0)===' ') c = c.substring(1,c.length);
                    if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length,c.length);
                }
                return "";
            };

            function eraseCookie(name) {
                createCookie(name,"",-1);
            };*/

            
            function getMdas(){
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/MdaServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL %>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: mdaReturnValues,
                    error: function(){ toastr["error"]("No record selected!", "MDA Selection Failed!!!");}
                });
            };
            function mdaReturnValues(data){
                if(data.length>0){
                    var resp ="";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if(attrValue === null || attrValue === 'null'){
                                attrValue = "";
                            }
                            if(attrName === "0"){
                                resp += "<option value='"+attrValue+"'>";
                            }
                            if(attrName === "1"){
                                resp += attrValue+"</option>";
                            }
                        }
                    }
                    $('#selMda_a').html("<option value='0'></option>"+resp);
                    $('#selMda_e').html(resp);
                    
                }
            };
            
            function setDepartment(id){
                if(id==="selMda_a"){
                    document.getElementById('selDepartment_a').value="0";
                }
                //if(id==="selMda_e"){
                //    document.getElementById('selDepartment_e').value="0";
                //}
            };
            function getDepartments(){
                var selMda = "";
                if(readCookie("formtype")==="createuser"){
                    selMda = document.getElementById("selMda_a").value;
                }
                if(readCookie("formtype")==="edituser"){
                    selMda = document.getElementById("selMda_e").value;
                }
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/DepartmentServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID %>", mda_id: selMda },
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: departmentReturnValues,
                    error: function(jqXHR, textStatus, errorThrown){ toastr["error"]("No record selected!", "Departments Selection Failed!!!");}
                });
            };
            function departmentReturnValues(data){
                if(data.length>0){
                    var resp ="";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if(attrValue === null || attrValue === 'null'){
                                attrValue = "";
                            }
                            if(attrName === "0"){
                                resp += "<option value='"+attrValue+"'>";
                            }
                            if(attrName === "1"){
                                resp += attrValue+"</option>";
                            }
                        }
                    }
                    resp += "";
                    if(readCookie("formtype")==="createuser"){
                        $('#selDepartment_a').html("<option value='0'></option>"+resp);
                    }
                    if(readCookie("formtype")==="edituser"){
                        $('#selDepartment_e').html("<option value='0'></option>"+resp);
                    }
                }else{
                    $('#selDepartment_a').html("<option value='0'></option></select>");
                    $('#selDepartment_e').html("<option value='0'></option></select>");
                }
            };
            
            function getRoles(){
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/RolesServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL %>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: rolesReturnValues,
                    error: function(jqXHR, textStatus, errorThrown){ toastr["error"]("No record selected!", "Roles Selection Failed!!!");}
                });
            };

            function rolesReturnValues(data){
                if(data.length>0){
                    var resp ="";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if(attrValue === null || attrValue === 'null'){
                                attrValue = "";
                            }
                            if(attrName === "0"){
                                resp += "<option value='"+attrValue+"'>";
                            }
                            if(attrName === "1"){
                                resp += attrValue+"</option>";
                            }
                        }
                    }
                    //resp += "</select>";
                    $('#selRoles_a').html("<option value='0'></option>"+resp);
                    $('#selRoles_e').html("<option value='0'></option>"+resp);
                    //console.log(document.getElementById("selRole1").innerHTML);
                }
            };
            
            function getUsers(){
                ShowLoading();
                //alert('finduser');
                var finduser = document.getElementById("finduser").value;
                //alert(finduser);
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/UserServlet",
                    data: {option: "<%= Utility.OPTION_SELECT %>", id: finduser},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: userReturnValues,
                    error: function(){ StopLoading(); toastr["error"]("No record selected!", "Users Selection Failed!!!");}
                });
            };
            function userReturnValues(data){
                if(data.length>0){
                    var resp ="";
                    var active_div="<table id='main_table_active' class='table table-clean table-striped' border='0'>";
                    active_div +="<thead><tr><td>&nbsp;</td><td><b>Username</b></td><td><b>Firstname</b></td><td><b>Lastname</b></td><td><b>Department</b></td><td><b>Roles</b></td><td><b>Phone&nbsp;No</b></td><td></td></tr></thead><tbody>";
                    var blocked_div="<table id='main_table_blocked' class='table table-clean table-striped' border='0'>";
                    blocked_div +="<thead><tr><td>&nbsp;</td><td><b>Username</b></td><td><b>Firstname</b></td><td><b>Lastname</b></td><td><b>Department</b></td><td><b>Roles</b></td><td><b>Phone&nbsp;No</b></td><td></td></tr></thead><tbody>";
                    
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var rec_d = "0";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if(attrValue === null || attrValue === 'null'){
                                attrValue = "";
                            }
                            
                            if(attrName === "0"){
                                rec_d = attrValue;
                                resp += "<tr class='hover-row' ref='user_123'>";
                            }
                            if(attrName === "1"){
                                if(attrValue===""){
                                    resp += "<td class='line-height-60 w-10'><img src='images/user-avatar.jpg' alt='' class='border-radius-50 img-size-50'/></td>";
                                }else{
                                    //document.getElementById("finduser").value=attrValue;
                                    resp += "<td class='line-height-60 w-10'><img src='"+attrValue+"' alt='' class='border-radius-50 img-size-50'/></td>";
                                }
                            }
                            if(attrName==="2" || attrName==="3" || attrName==="4" || attrName==="5" || attrName === "6" || attrName === "7"){
                                if(attrName === "5"){
                                    attrValue = attrValue.replace(/ /g, '&nbsp;'); //.replace(/&nbsp;/g, '<br><br><br><br><br>');
                                }else if(attrName === "6"){
                                    attrValue = attrValue.replace(/ /g, '&nbsp;').replace(/,&nbsp;/g, ', ');
                                }
                                resp += "<td class='line-height-30 w-50' style='vertical-align: middle;'><b>"+attrValue+"</b></td>";
                            }
                            
                            if(attrName === "8"){
                                if(attrValue==="true"){
                                    resp += "<td class='line-height-30 w-25'><a class='btn btn-success toggle-code-handle pull-left newuser' onclick='EditUser("+rec_d+")'  role='button'>Edit User&nbsp;<i class='fa fa-edit'></i></a></td></tr>";
                                    active_div += resp;
                                }
                                if(attrValue==="false"){
                                    resp += "<td class='line-height-30 w-25'><a class='btn btn-success toggle-code-handle pull-left newuser' onclick='EditUser("+rec_d+")'  role='button'>Edit User&nbsp;<i class='fa fa-edit'> </i></a></td></tr>";
                                    blocked_div += resp;
                                }
                                
                                resp = "";
                            }
                        }
                    }
                    active_div += "</tbody></table>";
                    $('#active').html(active_div);
                    //$('#main_table_active').dataTable();
                    $('#main_table_active').dataTable({
                        "pagingType": "full_numbers",
                        "lengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
                    });
                    
                    blocked_div += "</tbody></table>";
                    $('#blocked').html(blocked_div);
                    $('#main_table_blocked').dataTable({
                        "pagingType": "full_numbers",
                        "lengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
                    });
                    StopLoading();
                }
            };
            
            function resetPassword(){
                ShowLoading();
                var email = document.getElementById("txtEmail_e").value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/UserServlet",
                    data: {option: "<%= Utility.OPTION_REQUEST_PASSWORD_RESET %>", email: email},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        StopLoading();
                        document.getElementById("back-to-login").click();
                        if (data.indexOf("<%= Utility.ActionResponse.SUCCESSFULL.toString() %>")!== -1) {
                            toastr["success"]("Instruction for reseting the password has been sent to: "+email, "Password Reset Request Successful!!!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.BLOCKED.toString() %>")!== -1){
                            toastr["error"]("The user account have been blocked, contact the System Administrator to resolve it!", "User Blocked!!!!");
                        } else {
                            toastr["error"]("The Email you selected is invalid!", "Invalid Email!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown) {
                    error: function() {
                        StopLoading();
                    	toastr["error"]("The server is not accessible!", "Login Failed!!!");
                    }
                    
                });            
            };
        </script>
    </body>
</html>