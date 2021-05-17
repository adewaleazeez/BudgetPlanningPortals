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
        <link rel='stylesheet' href="css/jquery.loading.css" >
        <!--link href="js/DataTables/datatables.css" rel="stylesheet" /-->

        <!-- ========== THEME CSS ========== -->
        <link rel="stylesheet" href="css/main.css" media="screen">
        <link rel="stylesheet" href="css/jquery.loading.css" media="screen">
        <style>.hover-control{display: inline !important;} .select2-container .select2-selection--single { min-width: 400px;}</style>
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
                                <div class="col-sm-6">
                                    <h2 class="title">Departments</h2>
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
                                        <li class="active">Department management</li>
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
                                                        <label for="selMda_s">Select MDA</label>
                                                        <div id="selMda3"> </div>
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <div class="col-lg-6 right-side">
                                                    <a class="btn btn-info toggle-code-handle pull-right newuser" onclick="CreateDepartment();" role="button"><i class="fa fa-plus"> </i>Add Department</a>
                                                </div>
                                                <br style="clear: both;" />
                                                <!-- Nav tabs -->
                                                <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                    <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">Departments</a></li>

                                                </ul>

                                                <!-- Tab panes -->
                                                <div class="tab-content bg-white p-15">
                                                    <div role="tabpanel" class="tab-pane active" id="active">

                                                    </div>
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
                                                    <div class="form-group">
                                                        <label for="selMda_a">MDA</label>
                                                        <div id="selMda1"> </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <label for="txtEmail_a">Name</label>
                                                        <input type="text" class="form-control" id="txtName_a" placeholder="Name of new department" autofocus="">
                                                    </div>


                                                    <hr/>
                                                    <div class="form-group">
                                                        <button type="button" onclick="FinishCreate();" class="btn btn-info btn-labeled" style="float: right;">
                                                            Create Department
                                                            <span class="btn-label btn-label-right"><i class="fa fa-save"></i></span>
                                                        </button>
                                                        <button type="button" onclick="cancelAdd()" class="btn btn-danger btn-labeled" style="float: left;">
                                                            Cancel Add Department
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
                                                    <div class="form-group">
                                                        <label for="selMda_e">MDA</label>
                                                        <div id="selMda2"></div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="txtName_e">Name</label>
                                                        <input type="text" class="form-control" id="txtName_e"  autofocus="">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="selHOD_e">HoD</label>
                                                        <select class="custom_select form-control" name="selHOD_e" id="selHOD_e">
                                                            <option value=""> </option>
                                                        </select>
                                                    </div>
                                                    <hr />
                                                    <div class="form-group">
                                                        <button type="button" onclick="FinishEdit()" class="btn btn-success btn-labeled" style="float: right;">
                                                            Update Department
                                                            <span class="btn-label btn-label-right"><i class="fa fa-save"></i></span>
                                                        </button>
                                                        <button type="button" onclick="cancelEdit()" class="btn btn-danger btn-labeled" style="float: left;">
                                                            Cancel Edit Department 
                                                            <span class="btn-label btn-label-right"><i class="fa fa-remove"></i></span>
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

                    <div id="deleteModal">
                        <div class="">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-izimodal-close="">&times;</button>
                                    <h4 class="modal-title">Delete Department</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Are you sure you want to delete this item</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="FinishDelete();">Yes, Delete</button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" data-izimodal-close="" onclick="CancelDelete();">No</button>
                                </div>
                            </div>

                        </div>
                    </div> 
                </div>
                <!-- /.content-container -->
            </div>
            <!-- /.content-wrapper -->
        </div>
        <!-- /.main-wrapper -->

        <input type="hidden" id="site-url" value="<%= Utility.SITE_URL%>">
        <input type="hidden" id="select-all" value="<%= Utility.OPTION_SELECT_ALL%>">
        <input type="hidden" id="selectx" value="<%= Utility.OPTION_SELECT_A_RECORD%>">
        <input type="hidden" id="select-by-id" value="<%= Utility.OPTION_SELECT_BY_ID%>">
        <input type="hidden" id="update-user-menu" value="<%= Utility.OPTION_UPDATE_USER_MENUS%>">
        <input type="hidden" id="insertx" value="<%= Utility.OPTION_INSERT%>">
        <input type="hidden" id="updatex" value="<%= Utility.OPTION_UPDATE%>">
        <input type="hidden" id="deletex" value="<%= Utility.OPTION_DELETE%>">
        <input type="hidden" id="insertedx" value="<%= Utility.ActionResponse.INSERTED.toString()%>">
        <input type="hidden" id="updatedx" value="<%= Utility.ActionResponse.UPDATED.toString()%>">
        <input type="hidden" id="deletedx" value="<%= Utility.ActionResponse.DELETED.toString()%>"> 
        <input type="hidden" id="existsx" value="<%= Utility.ActionResponse.RECORD_EXISTS.toString()%>"> 
        <input type="hidden" id="no-recordx" value="<%= Utility.ActionResponse.NO_RECORD.toString()%>">  
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
        <script src='js/jquery.loading.js'></script>
        <script>
            checkLogin();
            $('#deleteModal').iziModal();
            $(document).ready(function () {
                createCookie("formtype", "");
                getMdas();
                $('#selMda_a, #selMda_e, #selMda_s, #selHOD_e').select2();
            });


            function DeleteDepartment(arg) {
                $('#deleteModal').iziModal('open');
                window.deleteId = arg;
            }

            function cancelDelete() {
                $('#deleteModal').iziModal('close');
                window.deleteId = 0;
            }

            function FinishDelete() {
                id = window.deleteId;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/DepartmentServlet",
                    data: {option: "<%= Utility.OPTION_DELETE%>", id: id},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {

                        if (data.indexOf("<%= Utility.ActionResponse.DELETED.toString()%>") !== -1) {
                            $('#deleteModal').iziModal('close');
                            toastr["success"]("Department deleted Successfull!", "Delete Successfull");
                            getDepartments();
                        }else if (data.indexOf("<%= Utility.ActionResponse.FAILED.toString()%>") !== -1) {
                            $('#deleteModal').iziModal('close');
                            toastr["error"]("Department still has HOD!", "Delete Failed");
                        }
                    },
                    error: function (a, b, c) {

                        toastr["error"]("Department delete Failed!", "No record deleted!");
                    }
                });
            }
            EditDepartment = function (arg) {
                createCookie("formtype", "editdepartment");
                var mda_id = document.getElementById("selMda_e").value;
                getUsersInMda(mda_id);
                $("#selMda_e").val($("#selMda_s").val()).trigger('change');
                
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/DepartmentServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: arg},
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
                                        document.getElementById("txtName_e").value = attrValue;
                                    }
                                    if (attrName === "1") {
                                        document.getElementById("txtId_e").value = attrValue;
                                    }
                                    if (attrName === "2") {
                                        $("#selHOD_e").val(attrValue).trigger('change');
                                    }
                                }
                                break;
                            }
                            toastr["success"]("Department record successfully fetched!", "Department Selection Successfull!!!");
                            $('#MainArea').hide();
                            $('#EditArea').fadeIn();
                            $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li onclick='closeEdit()'> Departments</li><li class='active'>Edit Department</li>");
                            document.getElementById("txtName_e").focus();
                        }
                    },
                    error: function (a, b, c) {

                        toastr["error"]("Record with id " + arg + " is not found!", "Department Select Fails!!!");
                    }
                });
            };
            FinishEdit = function () {
                var txtName_e = document.getElementById("txtName_e").value;
                var txtId_e = document.getElementById("txtId_e").value;
                var error = "";
                if (txtName_e === "") {
                    error += "<br>Name must not be blank<br>";
                }
                if (error !== "") {
                    toastr["error"](error, "Please Correct The Following Error(s)!!!");
                    return true;
                }
                //document.getElementById("txtFirstname_e").focus();
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/DepartmentServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE%>", name: txtName_e, id: txtId_e},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {

                        if (data.indexOf("<%= Utility.ActionResponse.NO_RECORD.toString()%>") !== -1) {
                            toastr["error"]("Department does not exist!", "Editing Failed!!!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.UPDATED.toString()%>") !== -1) {
                            SetHOD();
                            cancelEdit();
                            setTimeout(function(){ getDepartments(); }, 1000);
                            //toastr["success"]("Department record successfully updated!", "Department Editing Successfull!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown){
                    error: function () {
                        toastr["error"]("The server is not accessible!", "Editing Failed!!!");
                    }
                });
                $('#EditArea').hide();
                $('#MainArea').fadeIn();
                $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li class='active'> User Management</li>");
            };
            cancelEdit = function () {
                $('#EditArea').hide();
                $('#MainArea').fadeIn();
                $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li class='active'> User Management</li>");
            };
            CreateDepartment = function () {
                if(document.getElementById("selMda_a").value===""){
                    toastr["error"]("MDA must be selected!", "MDA not selected!!!");
                    return true;
                }
                $("#selMda_a").val($("#selMda_s").val()).trigger('change');
                createCookie("formtype", "createuser");
                $('#txtName_a').val("");
                $('#MainArea').hide();
                $('#CreateArea').fadeIn();
                $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li onclick='FinishEdit()'> Department management</li><li class='active' >Add Department</li>");
            };

            FinishCreate = function () {
                var txtName_a = document.getElementById("txtName_a").value;
                var selMDA_a = document.getElementById("selMda_a").value;
                var selHOD_a = 0;
                var error = "";
                if (txtName_a === "") {
                    error += "<br>Department Name must not be blank<br>";
                }
                if (selMDA_a === "") {
                    error += "<br>Please select an MDA<br>";
                }
                if (error !== "") {
                    toastr["error"](error, "Please Correct The Following Error(s)!!!");
                    return true;
                }
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/DepartmentServlet",
                    data: {option: "<%= Utility.OPTION_INSERT%>", name: txtName_a, mda_id: selMDA_a, hod_id: selHOD_a},

                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {

                        if (data.indexOf("<%= Utility.ActionResponse.RECORD_EXISTS.toString()%>") !== -1) {
                            toastr["error"]("A department with that name already exists!", "Department Creation Failed!!!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {

                            getDepartments();
                            cancelAdd();
                            toastr["success"]("Department successfully created!", "Department Creation Successfull!!!");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
//                    error: function(){
                        toastr["error"]("The server is not accessible!", "Department Creation Failed!!!");
                    }
                });
//                $('#CreateArea').hide();
//                $('#MainArea').fadeIn();
//                $('ul.breadcrumb').html('<li><a onclick="gotoLink('/dashboard00012');"><i class="fa fa-home"></i> Home</a></li><li class="active"> User management</li>');

            };
            cancelAdd = function () {
                $('#CreateArea').hide();
                $('#MainArea').fadeIn();
                $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li class='active'> User management</li>");
            };

            SetHOD = function () {
                var selHOD_e = document.getElementById("selHOD_e").value;
                var txtId_e = document.getElementById("txtId_e").value;
                var error = "";
                //if (selHOD_e === "0") {
                //    error += "<br>Please select a User as the HOD<br>";
                //}
                if (error !== "") {
                    toastr["error"](error, "Please Correct The Following Error(s)!!!");
                    return true;
                }
                //document.getElementById("txtFirstname_e").focus();
                $.ajax({
                    type: "POST",
                    url: "<%= Utility.SITE_URL%>/DepartmentServlet",
                    data: {option: "OPTION_UPDATE_HOD", hod_id: selHOD_e, id: txtId_e},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {

                        if (data.indexOf("<%= Utility.ActionResponse.NO_RECORD.toString()%>") !== -1) {
                            toastr["error"]("Department does not exist!", "Editing Failed!!!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.UPDATED.toString()%>") !== -1 || data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {
                            //getDepartments();
                            //toastr["success"]("HOD successfully updated!", "HOD Edit Successfull!!!");
                            getDepartments();
                            toastr["success"]("Department record successfully updated!", "Department Editing Successfull!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown){
                    error: function (a, b, c) {

                        toastr["error"]("The server is not accessible!", "Editing Failed!!!");
                    }
                });
            };

            CancelHODEdit = function () {
                $('#MainArea').fadeIn();
                $('#EditHODArea').hide();
                document.getElementById("selHOD_e").value = 0;
                //$('#selHOD_e').html("");
//                var txtDeptId_e = document.getElementById("txtDeptId_e").value;
            };

            function getMdas() {
                ShowLoading();
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
                        StopLoading();
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
                    $('#selMda1').html("<select class='js-states  form-control' id='selMda_a' readonly disabled><option  class=''></option>" + resp);
                    $('#selMda2').html("<select class='js-states  form-control' id='selMda_e' readonly disabled><option class=''></option>" + resp);
                    $('#selMda3').html("<select class='js-states custom_select form-control' id='selMda_s' onchange='setMdas(this);'><option class=''></option>" + resp);

                }
                StopLoading();
            }
            function setMdas(element) {
                if (element.id === "selMda_s") {
                    document.getElementById('selMda_e').value = element.value;
                    document.getElementById('selMda_a').value = element.value;
                    document.getElementById('selMda_s').value = element.value;
                    
                    getDepartments();
                }
            }
            function setDepartment(id) {
                if (id === "selMda_a") {
                    document.getElementById('selDepartment_a').value = "";
                }
                if (id === "selMda_e") {
                    document.getElementById('selDepartment_e').value = "";
                }
                if (id === "selMda_s") {
                    document.getElementById('selDepartment_e').value = "";
                }

            }
            function getDepartments() {
                ShowLoading();
                var selMda = document.getElementById("selMda_s").value;

                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/DepartmentServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID%>", mda_id: selMda},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: departmentReturnValues,
                    error: function (jqXHR, textStatus, errorThrown) {
                        toastr["error"]("No record selected!", "Departments Selection Fails!!!");
                    }
                });
            }
            function departmentReturnValues(data) {
                var resp = "";
                var active_div = "<table id='main-table' class='table table-clean table-striped' border='0'><tbody>";
                active_div += "<tr><td><b>S/No</b></td><td><b>Name</b></td><td><b>HOD</b></td><td></td></tr>";

                //-adewaleazeez@gmail.com-Adewale-Azeez-Accounts-System Administrator,Budget-Officer-Activate
                var count=0;
                for (var i = 0; i < data.length; i++) {
                    var obj = data[i];
                    var rec_d = "0";

                    for (var key in obj) {
                        var attrName = key;
                        var attrValue = obj[key];
                        if (attrValue === null || attrValue === 'null') {
                            attrValue = "";
                        }

                        if (attrName === "0") {
                            rec_d = attrValue;
                            resp += "<tr class='hover-row' ref='user_123'>";
                            resp += "<td class='line-height-30 w-10'><small>" + (++count) + "</small></td>";
                        }
                        if (attrName === "1") {
                            resp += "<td class='line-height-30 w-30'><small>" + attrValue + "</small></td>";
                        }

                        if (attrName === "7") {
                            resp += "<td class='line-height-30 w-30'><small>" + attrValue + " ";
                        }

                        if (attrName === "8") {
                            resp += "" + attrValue + "</small></td>";
                        }
                    }

                    resp += "<td class='line-height-30 w-40'>";
                    resp += "<button class='btn btn-info btn-labeled' onclick='EditDepartment(" + obj[0] + ")'>Edit<span class='btn-label btn-label-right'><i class='fa fa-save'></i></span></button>";
                    resp += "<span>  </span> <button class='btn btn-danger btn-labeled' onclick='DeleteDepartment(" + obj[0] + ")'>Delete<span class='btn-label btn-label-right'><i class='fa fa-remove'></i></span></button> ";
                    resp += "</tr>";
                }
                active_div += resp + "</tbody></table>";
                $('#active').html(active_div);
                StopLoading();
                $('#main_table').DataTable();
            }
            function getUsersInMda(id) {
                ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/UserServlet",
                    data: {option: "<%=Utility.OPTION_SELECT_ALL_BY_MDAS%>", mdaID: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        userReturnValues(data);
                    },
                    error: function () {
                        StopLoading();
                        toastr["error"]("No record selected!", "Users Selection Fails!!!");
                    }
                });
            }
            function userReturnValues(data) {
                StopLoading();
                var resp = "";

                if (data.length > 0) {
                    //-adewaleazeez@gmail.com-Adewale-Azeez-Accounts-System Administrator,Budget-Officer-Activate
                    var rec_id=0;
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var rec_d = "0";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }

                            if (attrName === "0") {
                                rec_id = attrValue;
                                resp += "<option value='" + rec_id + "'>";
                            }
                            if (attrName === "1" || attrName === "2") {
                                resp += " " + attrValue + " ";
                            }
                        }
                        resp += "</option>";
                    }

                    $('#selHOD_e').html("<option value='0'></option>" + resp);
                }
            }
        </script>


    </body>
</html>
