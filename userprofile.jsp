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
                                <div class="col-md-6">
                                    <h4 class="title"><div id="usernames"><div><small class="ml-10"></small></h4>

                                            </div>
                                            <!-- /.col-md-6 -->
                                            <div class="col-md-6 right-side">

                                            </div>
                                            <!-- /.col-md-6 text-right -->
                                        </div>
                                        <!-- /.row -->
                                        <div class="row breadcrumb-div">
                                            <div class="col-md-6">
                                                <ul class="breadcrumb">
                                                    <li><a onclick="gotoLink('/dashboard00012');"><i class="fa fa-home"></i> Home</a></li>
                                                    <li class="active">My Profile</li>
                                                </ul>
                                            </div>
                                            <!-- /.col-md-6 -->
                                            <div class="col-md-6 text-right">
                                                <a href="#" class="pl-20"><i class="fa fa-edit"></i> Edit Profile</a>
                                            </div>
                                            <!-- /.col-md-6 -->
                                        </div>
                                        <!-- /.row -->
                                        <div class="row mt-30">
                                            <div class="col-md-3">
                                                <div class="panel border-primary no-border border-3-top">

                                                    <div class="panel-body">
                                                        <div class="row">
                                                            <div class="col-md-10 col-md-offset-1">
                                                                <img id="user_dp_e" src="images/user-avatar.jpg" alt="User Picture" class="img-responsive" style="width: 100%; border-radius: 50%;">
                                                                <div class="text-center">
                                                                    <!--button type="button" class="btn btn-primary btn-xs btn-labeled mt-10">Edit Picture<span class="btn-label btn-label-right"><i class="fa fa-pencil"></i></span></button-->
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /.panel -->

                                                <div id="lastdate"></div>
                                                <div class="panel border-primary no-border border-3-top">

                                                    <div class="panel-body p-20">
                                                        <!--div class="mb-20">
                                                            <input type="checkbox" class="js-switch" id="chkActive_e" />
                                                        </div-->

                                                        <button type="button" class="btn btn-info btn-labeled" onclick="resetPassword();">
                                                            Reset Password
                                                            <span class="btn-label btn-label-right"><i class="fa fa-refresh"></i></span>
                                                        </button>
                                                    </div>
                                                </div>
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
                                                <!--div class="panel border-primary no-border border-3-top">
                                                    <div class="panel-heading">
                                                        <div class="panel-title">
                                                            <h5>Roles</h5>
                                                        </div>
                                                    </div>

                                                    <div class="panel-body p-20">
                                                        <span class="label label-success label-rounded label-bordered">Auditor</span>
                                                        <span class="label label-danger label-rounded label-bordered">Standard User</span>
                                                    </div>


                                                </div-->
                                                <!-- /.panel -->
                                            </div>
                                            <!-- /.col-md-3 -->
                                            <div class="col-md-9">


                                                <!-- /.row -->
                                                <ul class="nav nav-tabs border-primary" role="tablist" style="background-color: #ffffff; padding-top: 20px;">
                                                    <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
                                                    <li role="presentation"><a href="#password" aria-controls="password" role="tab" data-toggle="tab">Change Password</a></li>
                                                </ul>
                                                <div class="tab-content bg-white p-15">
                                                    <div role="tabpanel" class="tab-pane active" id="profile">
                                                        <table id="user" class="table noshade tall nolines" style="clear: both">

                                                            <tbody>
                                                                <tr>
                                                                    <td style="width: 10px;">
                                                                        <label for="txtEmail_e">Username/Email:</label>
                                                                    </td>
                                                                    <td style="width: 150px;">
                                                                        <input type="text" class="form-control" id="txtEmail_e" readonly="">
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <label for="txtFirstname_e">Firstname:</label>
                                                                    </td>
                                                                    <td>
                                                                        <input type="text" class="form-control" id="txtFirstname_e" readonly="">
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <label for="txtLastname_e">Lastname:</label>
                                                                    </td>
                                                                    <td>
                                                                        <input type="text" class="form-control" id="txtLastname_e" readonly="">
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <label for="selMda_e">MDA:</label>
                                                                    </td>
                                                                    <td>
                                                                        <div id="selMda2"></div>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <label for="selDepartment_e">Department:</label>
                                                                    </td>
                                                                    <td>
                                                                        <select class='js-states form-control' redonly disabled id='selDepartment_e'></select>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <label for="txtPhoneno_e">Phone No:</label>
                                                                    </td>
                                                                    <td>
                                                                    	<input type="text" class="form-control" id="txtPhoneno_e" readonly>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <label for='selRoles_e'>Roles:</label>
                                                                    </td>
                                                                    <td>
                                                                        <select class='js-states form-control' id='selRoles_e' readonly disabled multiple='multiple'></select>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane" id="password">
                                                        <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">
                                                            <div class="form-group has-feedback">
                                                                <label for="name3">Username</label>
                                                                <input type="text" class="form-control" id="txtUsername" disabled>
                                                            </div>

                                                            <div class="form-group has-feedback">
                                                                <label for="txtOldPassword">Old Password</label>
                                                                <input type="password" class="form-control" id="txtOldPassword" placeholder="Enter your old password">
                                                                <span class="fa fa-key form-control-feedback"></span>
                                                            </div>
                                                            
                                                            <div class="form-group has-feedback">
                                                                <label for="txtPassword">New Password</label>
                                                                <input type="password" class="form-control" id="txtPassword" placeholder="Enter a new password">
                                                                <span class="fa fa-key form-control-feedback"></span>
                                                            </div>
                                                            
                                                            <div class="form-group has-feedback m-b-30">
                                                                <label for="txtPasswordRetype">Confirm New Password</label>
                                                                <input type="password" class="form-control" id="txtPasswordRetype" placeholder="Retype the new password" onkeypress="checkEnter(event,this.id)">
                                                                <span class="fa fa-key form-control-feedback"></span>
                                                            </div>

                                                            <hr/>
                                                            <button type="button" class="btn btn-success btn-labeled pull-right" onclick="changePassword();">Change my password<span class="btn-label btn-label-right"><i class="fa fa-check-circle-o"></i></span></button>
                                                        </form>
                                                        <br style="clear: both;"/>
                                                    </div>



                                                    <!-- /.section-title -->
                                                </div>
                                            </div>
                                            <!-- /.col-md-9 -->
                                        </div>
                                        <!-- /.row -->
                                </div>
                                <!-- /.container-fluid -->

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
                <script>
                    checkLogin();
                    $(document).ready(function () {
                        getMdas();
                        getDepartments();
                        getRoles();
                        showUser();
                        $("#selRoles_e, #selDepartment_e, #selMda_e").select2();
                    });

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
                                toastr["error"]("No record selected!", "MDA Selection Failed!!!");
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
                            resp += "</select>";
                            $('#selMda2').html("<select class='js-states form-control' redonly disabled id='selMda_e' onchange='getDepartments();'><option value='0'></option>" + resp);
                        }
                    }

                    function getDepartments() {
                        var selMda = document.getElementById("selMda_e").value;
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/DepartmentServlet",
                            data: {option: "<%= Utility.OPTION_SELECT_BY_ID%>", mda_id: selMda},
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: departmentReturnValues,
                            error: function (jqXHR, textStatus, errorThrown) {
                                toastr["error"]("No record selected!", "Departments Selection Failed!!!");
                            }
                        });
                    }
                    function departmentReturnValues(data) {
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
                            $('#selDepartment_e').html("<option value='0'></option>" + resp);
                        } else {
                            $('#selDepartment_e').html("<option value='0'></option>");

                        }
                    }

                    function getRoles() {
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/RolesServlet",
                            data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: roleReturnValues,
                            error: function (jqXHR, textStatus, errorThrown) {
                                toastr["error"]("No record selected!", "Roles Selection Failed!!!");
                            }
                        });
                    }

                    function roleReturnValues(data) {
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
                            $('#selRoles_e').html("<option value='0'></option>" + resp);
                            //console.log(document.getElementById("selRole1").innerHTML);
                        }
                    }
                    showUser = function () {
                        ShowLoading();
                        var id = <%= session.getAttribute("userid")%>;
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/UserServlet",
                            data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: id},
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: function (data) {
                                StopLoading();
                                if (data.length > 0) {
                                    var lastday = "";
                                    var fname = "";
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
                                                //document.getElementById("txtId_e").value = attrValue;
                                            }
                                            if (attrName === "1") {
                                                if (attrValue === "") {
                                                    document.getElementById("user_dp_e").src = "images/user-avatar.jpg";
                                                } else {
                                                    document.getElementById("user_dp_e").src = attrValue;
                                                }
                                            }
                                            if (attrName === "2") {
                                                document.getElementById("txtEmail_e").value = attrValue;
                                                document.getElementById("txtUsername").value = attrValue;
                                                
                                            }
                                            if (attrName === "3") {
                                                document.getElementById("txtFirstname_e").value = attrValue;
                                                fname = attrValue;
                                            }
                                            if (attrName === "4") {
                                                document.getElementById("txtLastname_e").value = attrValue;
                                                $('#usernames').html(fname + " " + attrValue);
                                            }
                                            if (attrName === "5") {
                                                if (attrValue === "true") {
                                                    //$('#chkActive_e').bootstrapSwitch('state', true);
                                                } else {
                                                    //$('#chkActive_e').bootstrapSwitch('state', false);
                                                }
                                            }
                                            if (attrName === "6") {
                                                $('#selMda_e').val(attrValue).trigger('change');
                                                //document.getElementById("selMda_e").value = attrValue;
                                            }
                                            if (attrName === "7") {
                                                $('#selDepartment_e').val(attrValue).trigger('change');
                                                //document.getElementById("selDepartment_e").value = attrValue;
                                            }
                                            if (attrName === "8") {
                                                lastday = attrValue;
                                            }
                                            if (attrName === "9") {
                                                if (attrValue === 0) {
                                                    attrValue = " Today";
                                                } else if (attrValue === 1) {
                                                    attrValue = attrValue + " day ago";
                                                } else {
                                                    attrValue = attrValue + " days ago";
                                                }
                                                var resp = "<table class='table table-striped'><tbody><tr><th><small class='color-info'>Last&nbsp;Login</small></th>";
                                                resp += "<td><small class='color-warning'>" + lastday + "</small><br><br><small class='color-warning'>" + attrValue + "</small></td></tr></tbody></table>";
                                                //document.getElementById("lastdate").InnerHTML = resp;
                                                $('#lastdate').html(resp);
                                            }
                                            if (attrName === "10") {
                                                document.getElementById("txtPhoneno_e").value = attrValue;
                                            }
                                            if (attrName === "11") {
                                               var obj = eval('[' + attrValue + ']');
                                                $('#selRoles_e').val(obj).trigger('change');
                                            }
                                        }
                                        break;
                                    }
                                }
                            },
                            error: function () {
                                StopLoading();
                                toastr["error"]("Record not found!", "User Selection Failed!!!");
                            }
                        });
                    };
                    /*$(function(){

                     // Counter for dashboard stats
                     $('.counter').counterUp({
                     delay: 10,
                     time: 1000
                     });

                     $("#modal11").iziModal();
                     $('.preparation .dropdown-menu a').on('click', function () {
                     $('#modal11').iziModal('open', this);
                     });

                     });*/
                    function resetPassword() {
                        ShowLoading();
                        var email = document.getElementById("txtEmail_e").value;
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/UserServlet",
                            data: {option: "<%= Utility.OPTION_REQUEST_PASSWORD_RESET%>", email: email},
                            dataType: "text",
                            cache: false,
                            async: false,
                            success: function (data) {
                                StopLoading();
                                document.getElementById("back-to-login").click();
                                if (data.indexOf("<%= Utility.ActionResponse.SUCCESSFULL.toString()%>") !== -1) {
                                    toastr["success"]("Instruction for reseting the password has been sent to: " + email, "Password Reset Request Successful!!!");
                                } else if (data.indexOf("<%= Utility.ActionResponse.BLOCKED.toString()%>") !== -1) {
                                    toastr["error"]("The user account have been blocked, contact the System Administrator to resolve it!", "User Blocked!!!!");
                                } else {
                                    toastr["error"]("The Email you selected is invalid!", "Invalid Email!!!");
                                }
                            },
                            //error: function(jqXHR, textStatus, errorThrown) {
                            error: function () {
                                StopLoading();
                                toastr["error"]("The server is not accessible!", "Login Failed!!!");
                            }
                        });
                    }
                    function changePassword() {
                        ShowLoading();
                        var txtUsername = document.getElementById("txtUsername").value;
                        var txtOldPassword = document.getElementById("txtOldPassword").value;
                        var txtPassword = document.getElementById("txtPassword").value;
                        var txtPasswordRetype = document.getElementById("txtPasswordRetype").value;
                        
                        var error = "";
                        if(txtUsername===""){ error +="<br>Username must not be blank<br>"; }
                        if(txtOldPassword===""){ error +="Old Password must not be blank<br>"; }
                        if(txtPassword===""){ error +="New Password must not be blank<br>"; }
                        if(txtPasswordRetype!==txtPassword){ error +="New Password does not match the retyped password<br>"; }
                        if(error!==""){
                            toastr["error"](error, "Please Correct The Following Error(s)!!!");
                            return true;
                        }
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/UserServlet",
                            data: {option: "<%= Utility.OPTION_CHANGE_PASSWORD%>", username: txtUsername, password: txtPassword, id: txtOldPassword},
                            dataType: "text",
                            cache: false,
                            async: false,
                            success: function (data) {
                                StopLoading();
                                if (data.indexOf("<%= Utility.ActionResponse.SUCCESSFULL.toString()%>") !== -1) {
                                    toastr["success"]("Details of the account has been sent to: " + txtUsername, "Password Change Successful!!!");
                                } else if (data.indexOf("<%= Utility.ActionResponse.BLOCKED.toString()%>") !== -1) {
                                    toastr["error"]("The user account fot " + txtUsername+" have been blocked, contact the System Administrator to resolve it!", "User Blocked!!!!");
                                } else {
                                    toastr["error"]("The old password you typed is invalid!", "Invalid Old Password!!!");
                                }
                            },
                            //error: function(jqXHR, textStatus, errorThrown) {
                            error: function () {
                                StopLoading();
                                toastr["error"]("The server is not accessible!", "Password Change Failed!!!");
                            }
                        });
                    }
                    
                    function checkEnter(e, id){ //e is event object passed from function invocation
                        var characterCode; //literal character code will be stored in this variable

                        if(e && e.which){ //if which property of event object is supported (NN4)
                            e = e;
                            characterCode = e.which; //character code is contained in NN4's which property
                        } else {
                            e = event;
                            characterCode = e.keyCode; //character code is contained in IE's keyCode property
                        }

                        if(characterCode === 13){ //if generated character code is equal to ascii 13 (if enter key)
                            if(id==="txtPasswordRetype"){
                                changePassword();
                            }
                            return false;
                        } else {
                            return true;
                        }

                    }
                    
                </script>
                <!-- ========== ADD custom.js FILE BELOW WITH YOUR CHANGES ========== -->
                </body>
                </html>
