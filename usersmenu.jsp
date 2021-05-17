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
                                <ul class="side-nav color-gray">

                                    <li class="nav-header">
                                        <span class="">Menu</span>
                                    </li>
                                    <li class="has-children">
                                        <a href="#"><i class="fa fa-file-text"></i> <span>Budget</span> <i class="fa fa-angle-right arrow"></i></a>
                                        <ul class="child-nav">
                                            <li><a onclick="gotoLink('/dashboard00012');"><i class="fa fa-home"></i> <span>Analytics</span></a></li>
                                            <li><a href="forecast.html"><i class="fa fa-thumb-tack"></i> <span>Preparation</span></a></li>
                                            <li><a href="userprofile.html"><i class="fa fa-user"></i> <span>User Profile</span></a></li>
                                            <li><a href="userslist.html"><i class="fa fa-user"></i> <span>Users List</span></a></li>
                                        </ul>
                                    </li>
                                </ul>

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
                                    <h2 class="title">Users Menu</h2>
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
                                        <li class="active">Users Menu</li>
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
                                            <br style="clear: both;" />
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs" role="tablist" style="margin-top: 20px;">
                                                <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">Roles Menu</a></li>
                                                <li role="presentation" class=""><a class="" href="#blocked" aria-controls="blocked" role="tab" data-toggle="tab" aria-expanded="false">Users Menu</a></li>
                                            </ul>

                                            <!-- Tab panes -->
                                            <div class="tab-content bg-white p-55">
                                                <div role="tabpanel" class="tab-pane active" id="active">
                                                    <br style="clear: both;" />
                                                    <div class="col-md-4">
                                                        <label style="font-size: large">Role</label>
                                                        <select class='js-states form-control' id='selRoles_a' onchange='getRolesMenu(this.id);'></select>
                                                    </div>
                                                    <div class="col-md-6"></div>
                                                    <div class="col-md-2" id="addmenurole">
                                                        <br style="clear: both;" />
                                                        <a class="btn btn-info toggle-code-handle pull-right" onclick="addMenuRole();" role="button"><i class="fa fa-plus"> </i>Add Menu to Role</a>

                                                    </div>
                                                    <div id="rolemenus"></div>
                                                </div>

                                                <div role="tabpanel" class="tab-pane" id="blocked">
                                                    <br style="clear: both;" />
                                                    <div class="col-md-4 col-sm-10">
                                                        <label style="font-size: large">User</label>
                                                        <select class='js-states form-control' id='selUsers_a' onchange='addMenuUser();'></select>
                                                    </div>
                                                    <div class="col-md-6 col-sm-10">
                                                        <label style="font-size: large">Role</label>
                                                        <select class='js-states form-control' id='selRoles_b' readonly disabled  multiple='multiple'></select>
                                                    </div>
                                                    <!--div class="col-md-2" id="addmenuuser">
                                                        <br style="clear: both;" />
                                                        <a class="btn btn-info toggle-code-handle pull-right" onclick="addMenuUser();" role="button"><i class="fa fa-plus"> </i>Add Menu to User</a>
                                                        <select class='js-states form-control' id='selUsers_a' onchange='getUsersMenu(this.id);'></select>
                                                    </div-->

                                                    <div id="usermenus"></div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                                <!-- /.col-md-12 -->

                            </div>
                            <!-- /.row -->

                        </section>

                    </div>

                    <!-- /.main-page -->
                    <!-- /.right-sidebar -->
                </div>
                <!-- /.content-container -->
            </div>
            <!-- /.content-wrapper -->
        </div>
        <div id="deleteModal" role="dialog">
            <div class="">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-izimodal-close="">&times;</button>
                        <h4 class="modal-title">Delete Menu Code</h4>
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
        <div class="" id="addrolemenu" data-title="Add Menu To Role" data-header-color="#d43f3a"
             data-overlay-color="rgba(41,41,41,0.9)" data-subtitle="" data-icon-class="">
            <div class="p-15" style="padding-bottom: 0px !important;">
                <form class="form-horizontal">
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label>Role</label>
                            <select class='js-states form-control' disabled readonly id='txtRole_a'></select>
                        </div>
                        <div class="col-sm-12">
                            <label>Menu Category</label>
                            <select class='js-states form-control' id='menucategory0' onchange='getMenuCodes(this.id);'></select>
                        </div>
                        <div class="col-sm-12">
                            <label>Menu Name</label>
                            <select id='txtMenuName_a' onchange='setMenuURL(this.id);'><option value='0'>Select Menu</option></select>
                        </div>
                        <div class="col-sm-12">
                            <label>Menu URL</label>
                            <input type='text' class='form-control' disabled readonly id='txtMenuURL_a' placeholder='Menu URL' class='media-right'>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <button onclick='saveMenuRole();' type='button' class='btn btn-success btn-labeled pull-right'>Save<span class='btn-label btn-label-right'><i class='fa fa-save'></i></span></button>
                            <button type='button' onclick='ReturnAddButton();' class='btn btn-danger btn-labeled' style='float: left;'>Cancel<span class='btn-label btn-label-right'><i class='fa fa-remove'></i></span>
                        </div>
                    </div>
                </form>
            </div>
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
                getRoles();
                getUsers();
                getCategorys();
                createCookie("formtype", "");
                $('#deleteModal').iziModal();

                $("#addrolemenu").iziModal();
                $("#selRoles_a, #selRoles_b, #selUsers_a, #txtRole_a, #menucategory0").select2();
                //$('#chkActive, #chkActiveB').bootstrapSwitch({onText: 'Active', offText: 'Blocked'});
                //$('.switch-checkboxes').bootstrapSwitch({ onText: 'Active', offText: 'Blocked' });
                if (readCookie("checkAllStatus") === "true") {
                    $('#checkAllActive').bootstrapSwitch('state', true);
                } else {
                    $('#checkAllActive').bootstrapSwitch('state', false);
                }
                createCookie("checkAllActive");

            });
            /*ShowLoading = function () {
                $("html").loading({
                    stoppable: false
                });
            };
            StopLoading = function () {
                $("html").loading('stop');
            };


            function createCookie(name, value, days) {
                var expires = "";
                if (days) {
                    var date = new Date();
                    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                    expires = "; expires=" + date.toGMTString();
                }
                document.cookie = name + "=" + value + expires + "; path=/";
            };

            function readCookie(name) {
                var nameEQ = name + "=";
                var ca = document.cookie.split(';');
                for (var i = 0; i < ca.length; i++) {
                    var c = ca[i];
                    while (c.charAt(0) === ' ')
                        c = c.substring(1, c.length);
                    if (c.indexOf(nameEQ) === 0)
                        return c.substring(nameEQ.length, c.length);
                }
                return "";
            };

            function eraseCookie(name) {
                createCookie(name, "", -1);
            };*/

            function addMenuRole() {
                //var id = $("#selRoles_a option:selected").text();
                var id = $("#selRoles_a").val();
                //alert(id);
                if (id === null || id === "0" || id === 0) {
                    toastr["error"]("A Role must be selected to add menus!", "No Role Selected!!!");
                    return true;
                }
                //$("#txtRole_a").val(id);
                $('#txtRole_a').val(id).trigger('change');
                $('#menucategory0').val("0").trigger('change');
                $('#txtMenuName_a').val("");
                $('#txtMenuURL_a').val("");

                $('#addrolemenu').iziModal('open', this);
            };

            function addMenuUser() {
                //var id = $("#selUsers_a option:selected").text();
                var id = $("#selUsers_a").val();
                /*if (id === null || id === "0" || id === 0) {
                 toastr["error"]("A User must be selected to add menus!", "No User Selected!!!");
                 return true;
                 }*/
                ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MenuUserServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE_USER_MENUS%>", user_id: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function () {
                        StopLoading();
                        getUsersMenu("selUsers_a");
                        //myVar = setTimeout(function(){ getUsersMenu("selUsers_a"); }, 1000); 
                    },
                    error: function () {
                        StopLoading();
                        toastr["error"]("No record selected!", "Role Menu Selection Failed!!!");
                    }
                });
            };

            function ReturnAddButton() {
                $('#addrolemenu').iziModal('close');
            };

            function getRolesMenu(arg) {
                ShowLoading();
                var id = document.getElementById(arg).value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MenuRoleServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID%>", role_id: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: rolesMenuReturnValues,
                    error: function () {
                        StopLoading();
                        toastr["error"]("No record selected!", "Role Menu Selection Failed!!!");
                    }
                });
            };
            function rolesMenuReturnValues(data) {
                StopLoading();
                if (data.length > 0) {
                    var resp = "<table id='main-table' class='table table-clean table-striped' border='0'><tbody>";
                    resp += "<tr><td><b>Menu Categories</b></td><td><b>Menu Names</b></td><td><b>Menu URLs</b></td><td></td><td></td><td></td></tr>";
                    var rec_id = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var role_id = "0";
                        var menu_id = "0";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }

                            if (attrName === "1") {
                                role_id = attrValue;
                                resp += "<tr class='hover-row'>";
                            }
                           if (attrName === "2") {
                                menu_id = attrValue;
                            }
                            if (attrName === "3") {
                                resp += "<td class='line-height-30 w-20'>" + attrValue + "</td>";
                            }
                            if (attrName === "4") {
                                resp += "<td class='line-height-30 w-30'>" + attrValue + "</td>";
                            }
                            if (attrName === "5") {
                                resp += "<td class='line-height-30 w-10'>" + attrValue + "</td>";
                            }
                            if (attrName === "6") {
                                if (attrValue === "true") {
                                    resp += "<td><input id='" + menu_id + "' type='checkbox' class='switch-checkboxes' checked onchange='chgMenuRoleStatus(this.id," + role_id + ");' /></td>";
                                } else {
                                    resp += "<td><input id='" + menu_id + "' type='checkbox' class='switch-checkboxes' onchange='chgMenuRoleStatus(this.id," + role_id + ");' /></td>";
                                }
                                //resp += "<td><input id='" + role_id + "' type='checkbox' class='switch-checkboxes' onchange='chgMenuRoleStatus(this.id," + menu_id + ");' /></td>";
                                resp += "<td class='line-height-30 w-5' style='text-align: center'>&nbsp;</td>";
                                resp += "<td><button onclick='deleteItem(" + rec_id + ");' type='button' class='btn btn-danger btn-labeled pull-right'>Delete<span class='btn-label btn-label-right'><i class='fa fa-remove'></i></span></button></td></tr>";
                            }
                        }
                    }
                    resp += "</tbody></table>";
                    $('#rolemenus').html(resp);
                } else {
                    $('#rolemenus').html("");
                }
                $('.switch-checkboxes').bootstrapSwitch({onText: 'Active', offText: 'Blocked'});
            };

            function getUsersMenu(arg) {
                ShowLoading();
                var id = document.getElementById(arg).value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MenuUserServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID%>", user_id: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: usersMenuReturnValues,
                    error: function () {
                        StopLoading();
                        toastr["error"]("No record selected!", "User Menu Selection Failed!!!");
                    }
                });
            };
            
            function usersMenuReturnValues(data) {
                StopLoading();
                //alert(data);
                var resp = readCookie("myrolecookie");
                resp = resp.substr(4, resp.length - 8);
                var object = eval('[' + resp + ']');
                $('#selRoles_b').val(object).trigger('change');
                
                if (data.length > 0) {
                    var resp = "<table id='main-table' class='table table-clean table-striped' border='0'><thead>";
                    resp += "<tr><td><b>Menu Categories</b></td><td><b>Menu Names</b></td><td><b>Menu URLs</b></td><td></td><td><input id='checkAllActive' class='switch-checkboxes' type='checkbox' class='js-switch' onchange='chgAllChekboxStatus(this.id);' /></td></tr></thead><tbody>";
                    var flag_p = 0;
                    var flag_n = 0;
                    var rec_count = 0;
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
                                rec_count++;
                                resp += "<tr class='hover-row'>";
                            }
                            if (attrName === "3") {
                                resp += "<td class='line-height-30 w-20'>" + attrValue + "</td>";
                            }
                            if (attrName === "4") {
                                resp += "<td class='line-height-30 w-20'>" + attrValue + "</td>";
                            }
                            if (attrName === "5") {
                                resp += "<td class='line-height-30 w-30'>" + attrValue + "</td>";
                                resp += "<td class='line-height-30 w-5' style='text-align: center'>&nbsp;</td>";
                            }
                            if (attrName === "6") {
                                var id = "checkActive" + rec_count;
                                if (attrValue === "true") {
                                    flag_p++;
                                    resp += "<td><input id='" + id + "' type='checkbox' class='switch-checkboxes' checked onchange='chgChekboxStatus(this.id," + rec_id + ");' /></td></tr>";
                                } else {
                                    flag_n++;
                                    resp += "<td><input id='" + id + "' type='checkbox' class='switch-checkboxes' onchange='chgChekboxStatus(this.id," + rec_id + ");' /></td></tr>";
                                }
                            }
                        }
                    }
                    resp += "</tbody></table>";
                    $('#usermenus').html(resp);
                    //alert($('#usermenus').html());
                    /*$('#main-table').dataTable({
                        "pagingType": "full_numbers",
                        "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]]
                    });*/
                } else {
                    $('#usermenus').html("");
                }

                $('#checkAllActive').bootstrapSwitch({onText: 'Active[ALL]', offText: 'Blocked[ALL]'});
                $('.switch-checkboxes').bootstrapSwitch({onText: 'Active', offText: 'Blocked'});
                createCookie('refreshmenu','0',false);
                if (flag_n === 0) {
                    $('#checkAllActive').bootstrapSwitch('state', true);
                } else {
                    $('#checkAllActive').bootstrapSwitch('state', false);
                }
                clearTimeout(myVar);
            };

            var myVar = null;

            function chgAllChekboxStatus(id) {
                if(readCookie('refreshmenu')==='0'){
                    createCookie('refreshmenu','1',false);
                    return true;
                }
                var user_id = document.getElementById("selUsers_a").value;
                var accessible = document.getElementById(id).checked;
                createCookie("checkAllStatus", accessible);
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MenuUserServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE_ALL_MENUS_STATUS%>", accessible: accessible, user_id: user_id},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function () {
                        myVar = setTimeout(function () {
                            getUsersMenu("selUsers_a");
                        }, 1000);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        toastr["error"]("No record updated!", "Menu Status Update Failed!!!");
                    }
                });
            };
            function chgChekboxStatus(id, rec_id) {
                var accessible = document.getElementById(id).checked;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MenuUserServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE_MENU_STATUS%>", id: rec_id, accessible: accessible},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function () {
                        //myVar = setTimeout(function(){ getUsersMenu("selUsers_a"); }, 1000); 
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        toastr["error"]("No record updated!", "Menu Status Update Failed!!!");
                    }
                });
            };

            function chgMenuRoleStatus(menuid, roleid) {
                var accessible = document.getElementById(menuid).checked;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MenuUserServlet",
                    data: {option: "<%= Utility.OPTION_SELECT%>", user_id: roleid, menu_id: menuid, accessible: accessible},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function () {
                        //myVar = setTimeout(function(){ getUsersMenu("selUsers_a"); }, 1000); 
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        toastr["error"]("No record updated!", "Menu Status Update Failed!!!");
                    }
                });
            };

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
            };
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
                    $('#selRoles_a').html("<option value='0'></option>" + resp);
                    $('#selRoles_b').html("<option value='0'></option>" + resp);
                    $('#txtRole_a').html("<option value='0'></option>" + resp);
                }
            };

            function getUsers() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/UserServlet",
                    data: {option: "<%= Utility.OPTION_SELECT%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: userReturnValues,
                    error: function (jqXHR, textStatus, errorThrown) {
                        toastr["error"]("No record selected!", "Users Selection Failed!!!");
                    }
                });
            };
            function userReturnValues(data) {
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
                            if (attrName === "2") {
                                resp += attrValue + "</option>";
                                break;
                            }
                        }
                    }
                    $('#selUsers_a').html("<option value='0'></option>" + resp);
                }
            };

            function getCategorys() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MenuCategoryServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: menuCategoryReturnValues,
                    error: function (jqXHR, textStatus, errorThrown) {
                        toastr["error"]("No record selected!", "Menu Category Selection Failed!!!");
                    }
                });
            };
            function menuCategoryReturnValues(data) {
            //alert(data);
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
                            if (attrName === "1") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "2") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    $('#menucategory0').html("<option value='0'></option>" + resp);
                }
            };

            function getMenuCodes(id) {
                id = $("#" + id).val();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SystemMenuServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID%>", menu_category_id: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: menuCodeReturnValues,
                    error: function () {
                        toastr["error"]("Menu Code Selection Failed!", "No record selected!");
                    }
                });
            };
            function menuCodeReturnValues(data) {
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
                            if (attrName === "1") {
                                resp += "<option value='" + attrValue + "'>";
                            }
                            if (attrName === "2") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    $('#txtMenuName_a').html("<option value='0'></option>" + resp);
                } else {
                    $('#txtMenuName_a').html("<option value='0'>No Option Availale</option>");
                }
            };

            function setMenuURL(id) {
                var name = $("#" + id + " option:selected").text();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SystemMenuServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_NAME%>", name: name},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        $("#txtMenuURL_a").val(data);
                    },
                    error: function () {
                        toastr["error"]("Menu Name Selection Failed!", "No Menu URL selected!");
                    }
                });
            };

            function saveMenuRole() {
                //showLoading();
                var menu_id = $("#txtMenuName_a").val();
                var role_id = $("#txtRole_a").val();
                var error = "";
                if (menu_id === "0") {
                    error += "Menu URL must not be blank<br>";
                }
                if (role_id === "0") {
                    error += "Role must not be blank<br>";
                }
                if (error.length > 0) {
                    toastr["error"](error, "Please correct the following:");
                    return true;
                }

                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MenuRoleServlet",
                    data: {option: "<%= Utility.OPTION_INSERT%>", menu_id: menu_id, role_id: role_id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data === "<%= Utility.ActionResponse.INSERTED%>") {
                            toastr["success"]("Menu Code Insert Successful!", "New record successfully inserted!");
                            getRolesMenu("selRoles_a");
                            ReturnAddButton();
                            //StopLoading();
                        } else {
                            toastr["error"]("Record already exists!", "No Record Inserted!!!");
                        }
                    },
                    error: function () {//StopLoading(); 
                        toastr["error"]("Menu Code Insert Failed!", "No record inserted!");
                    }
                });
            };

            deleteItem = function (arg) {
                window.deleteId = arg;
                $('#deleteModal').iziModal('open');
            };

            finishDeleteItem = function () {
                var id = window.deleteId;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/MenuRoleServlet",
                    data: {option: "<%= Utility.OPTION_DELETE%>", id: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        $('#deleteModal').iziModal('close');
                        if (data === "<%= Utility.ActionResponse.DELETED%>") {
                            toastr["success"]("Menu Code Delete Successful!", "Record Deleted!!!");
                            //getMenuCodes('menucategory0');               
                            getRolesMenu("selRoles_a");
                        } else {
                            toastr["error"]("Menu Code Delete Failed!", "No Record Deleted!!!");
                        }
                    },
                    error: function () {
                        $('#deleteModal').iziModal('close');
                        toastr["error"]("Record with id " + arg + " is not found!", "Menu Code Delete Failed!!!");
                    }
                });
            };

            var removeWindowId = function () {
                window.deleteId = 0;
            };

        </script>
    </body>
</html>
