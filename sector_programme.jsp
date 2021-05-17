<%-- 
    Document   : sector_goles
    Created on : Sep 25, 2017, 6:36:00 PM
    Author     : Ola
--%>
<%@ page import="com.bpp.utility.Utility" %>
<%@ page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <!-- ========== THEME CSS ========== -->
        <link rel="stylesheet" href="css/main.css" media="screen">
        <!-- ========== MODERNIZR ========== -->
        <script src="js/modernizr/modernizr.min.js"></script>

        <!-- For Loading -->
        <link href="css/jquery.loading.css" rel="stylesheet" />

        <link rel="stylesheet" href="js/amcharts/plugins/export/export.css" type="text/css" media="all" />   
    </head>
    <body  class="top-navbar-fixed">
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
                                    <h2 class="title">Sector Programmes</h2>
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
                                        <li class="active">Sector Programmes</li>
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
                                        <div class="panel" id="_MainSection">
                                            <div class="panel-heading">
                                                <div class="panel-title">

                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <!--Goals Listing Table -->
                                                <div id="list-area">
                                                    <div class="col-md-12 right-side">
                                                        <button type="button" class="btn btn-info toggle-code-handle pull-right" data-toggle="modal" data-target="#AddNewSectorProgramme">Add New Sector Programme</button>
                                                        <!--<a class="btn btn-info toggle-code-handle pull-right show-create" role="button"><i class="fa fa-plus"> </i>Add New Sector Programme</a>-->
                                                    </div>
                                                    <br style="clear: both;" />
                                                    <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                        <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">Sector Programmes</a></li>
                                                    </ul>
                                                    <div class="tab-content bg-white p-15">
                                                        <div role="tabpanel" class="tab-pane active" id="Programmelisttable">

                                                        </div>

                                                    </div>
                                                </div> 
                                            </div>
                                        </div>
                                        <!--Insert Section-->
                                        <!-- Modal -->
                                        <div class="modal fade" id="AddNewSectorProgramme" tabindex="-1" role="dialog" aria-labelledby="AddNewSectorProgrammeLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                        <h4 class="modal-title" id="AddNewSectorProgrammeLabel">Add New Sector Programme</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="panel panel-control" id="_InsertSection">
                                                            <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">
                                                                <div class="form-group has-feedback">
                                                                    <label>Programme Code</label> 
                                                                    <input type="text" required class="form-control" id="txtProgrammeCode_i" placeholder="Programme Code">
                                                                    <!--<select class='js-states form-control' id='txtProgrammeCode_i'></select>-->
                                                                    <label>Name</label>
                                                                    <input type="text" required class="form-control" id="txtProgrammeName_i" placeholder="Programme Name">                                                                    
                                                                </div>
                                                                <button onclick="CloseEdit();" id="butcloseinsert" type="button" data-dismiss="modal"  class="btn btn-success btn-labeled pull-left"><span class="btn-label btn-label-left"><i class="fa fa-chevron-left"></i></span>Return</button>
                                                                <button onclick="insertProgramme();" type="button" class="btn btn-success btn-labeled pull-right">Save<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                            </form>
                                                            <br style="clear: both;" />
                                                        </div>
                                                        <div class="modal-footer">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Edit Section-->
                                        <!-- Modal -->
                                        <div class="modal fade" id="EditSectorProgramme" tabindex="-1" role="dialog" aria-labelledby="EditSectorProgrammeLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                        <h4 class="modal-title" id="EditSectorProgrammeLabel">Edit Sector Programme</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="panel" id="_EditSection">
                                                            <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">
                                                                <div class="form-group has-feedback">
                                                                    <label>Programme Code</label>
                                                                    <input type="text" required class="form-control" id="txtProgrammeCode_e" placeholder="Programme Code">
                                                                    <!--<select class='js-states form-control' id='txtProgrammeCode_e' ></select>-->
                                                                    <label>Name</label>
                                                                    <input type="text" required class="form-control" id="txtProgrammeName_e" placeholder="Programme Name">
                                                                    <input type="hidden" id="updateid" name="id"/>
                                                                </div>
                                                                <button onclick="CloseEdit();" id="butcloseedit" type="button" data-dismiss="modal"  class="btn btn-success btn-labeled pull-left"><span class="btn-label btn-label-left"><i class="fa fa-chevron-left"></i></span>Return</button>
                                                                <button onclick="FinishEdit();" type="button" class="btn btn-success btn-labeled pull-right">Save<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                            </form>
                                                            <br style="clear: both;" />

                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- /.col-md-12 -->
                                        <!-- Delete Dialogue 
                                        
                                        
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".deletedialog">Small modal</button>-->
                                        <div class="modal fade" tabindex="-1" role="dialog" id="deletedialogx">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" id="butclosedel" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                        <h3>Data will be permanently deleted</h3>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>Do you wish to continue with delete</p>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                        <button type="button" class="btn btn-primary" onclick="FinishDelete()">Yes, Delete</button>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal-dialog -->
                                        </div><!-- /.modal -->

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

        <script src="js/moment/moment.min.js"></script>
        <script src="js/mockjax/jquery.mockjax.js"></script>
        <script src="js/mockjax/demo-mock.js"></script>
        <script src="js/x-editable/bootstrap-editable.min.js"></script>
        <script src="js/x-editable/demo.js"></script>
        <script src='js/jquery.loading.js'></script>

        <!-- ========== THEME JS ========== -->
        <script src="js/main.js"></script>
        <script src="js/production-chart.js"></script>
        <script src="js/traffic-chart.js"></script>
        <script src="js/task-list.js"></script>
        <script src="js/select2/select2.min.js"></script>

        <!-- ========== ADD custom.js FILE BELOW WITH YOUR CHANGES ========== -->
        <script type="text/javascript">
            checkLogin();

            $('#deleteModal').iziModal();
            getSectorProgrammes();

            function getSectorProgrammes() {
                ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SectorProgrammesServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: sectorProgrammeReturnValues,
                    error: function () {
                        StopLoading();
                        toastr["error"]("Sector Programmes Selection Failed!", "No record selected!");
                    }
                });
            }
            function sectorProgrammeReturnValues(data) {
                popSectorProgrammes(data);
                StopLoading();
                if (data.length > 0) {
                    var resp = "<table class='table table-clean table-striped'><tbody><tr><td>Sector Programme Code</td><td>Sector Programme Name</td><td></td></tr>";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var recId = 0;
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = " " + attrValue;
                            if (attrName === "0") {
                                resp += "<tr >";
                                recId = attrValue;
                            }
                            if (attrName === "1") {
                                resp += "<td class='line-height-30 w-20'><small><b>" + attrValue + "</b></small></td>";
                            }
                            if (attrName === "2") {
                                resp += "<td class='line-height-30 w-20'><small><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + attrValue + "</b></small></td>";
                                //resp += "<td class='line-height-30 w-40'></td>";
                                //resp += "<td><button type='button' onclick='EditItem(" + recId + ")' class='btn btn-success'><i class='fa fa-edit'></i>Edit</button>";
                                resp += "<td><button type='button' onclick='EditItem(" + recId + ")' class='btn btn-success' data-toggle='modal' data-target='#EditSectorProgramme'><i class='fa fa-edit'></i>Edit</button>";
                                resp += " <button type='button' class='btn btn-danger' data-toggle='modal' data-target='#deletedialogx' onclick='DeleteItem(" + recId + ")'><i class='fa fa-remove'></i>Delete</button></td></tr>";
                            }

                        }
                    }
                    resp += "</tbody></table>";
                    document.getElementById("Programmelisttable").innerHTML = resp;

                }

            }
            function popSectorProgrammes(data) {
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
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                        }
                    }
                    //resp += "</select>";
                    $('#txtProgrammeCode_e').html("<option value='0'></option>" + resp);
                    //$('#txtProgrammeCode_i').html("<option value='0'></option>" + resp);
                }
            }
            ;
            function EditItem(arg) {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SectorProgrammesServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: arg},
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
                                        document.getElementById("updateid").value = attrValue;
                                    }
                                    if (attrName === "1") {
                                        document.getElementById("txtProgrammeCode_e").value = attrValue;
                                    }
                                    if (attrName === "2") {
                                        document.getElementById("txtProgrammeName_e").value = attrValue;
                                    }
                                }
                                break;
                            }
                            toastr["success"]("Sector successfully fetched!", "Sector Fetch Successfull!!!");
                        }
                    },
                    error: function (e, b, c) {
                        toastr["error"]("Record with id " + arg + " is not found!", "Sector Programmes Selection Failed!!!");
                    }
                });
            }
            function FinishEdit() {
                var sector_Programme_code = document.getElementById("txtProgrammeCode_e").value;
                var name = document.getElementById("txtProgrammeName_e").value;
                var id = document.getElementById("updateid").value;

                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SectorProgrammesServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE%>", id: id, sector_programme_code: sector_Programme_code, name: name},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.indexOf("<%= Utility.ActionResponse.UPDATED.toString()%>") !== -1) {
                            toastr["success"]("Sector Programme update Successfull!", "Update Successfull");
                            cancelEdit();
                        }
                        if (data.indexOf("<%= Utility.ActionResponse.RECORD_EXISTS.toString()%>") !== -1) {
                            toastr["error"]("Sector Programme with that name already exists", "Update Failed");
                        }
                    },
                    error: function (a, b, c) {
                        //alert(a + "   " + b + "   " + c);
                        toastr["error"]("Sector Programme Update Failed!", "No record updated!");
                    }
                });
            }
            function cancelEdit() {
                document.getElementById("butcloseedit").click();
                getSectorProgrammes();
                document.getElementById("updateid").value = '';
            }
            function insertProgramme() {
                var name = document.getElementById("txtProgrammeName_i").value;
                var sector_Programme_code = document.getElementById("txtProgrammeCode_i").value;
                var error = "";
                if (name === "") {
                    error += "Sector Programme Name must not be blank<br>";
                }
                if (sector_Programme_code === "") {
                    error += "Sector Programme Code must not be blank<br>";
                }
                if (error.length > 0) {
                    toastr["error"](error, "Please correct the following:");
                    return true;
                }
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SectorProgrammesServlet",
                    data: {option: "<%= Utility.OPTION_INSERT%>", name: name, sector_programme_code: sector_Programme_code},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: sectorProgrammesInsertReturnValues,
                    error: function () {
                        toastr["error"]("Sector Programmes Insert Failed!", "No record inserted!");
                                                                 document.getElementById("txtProgrammeName_i").value = '';
                    document.getElementById("txtProgrammeCode_i").value = '';
                    }
                });
            }
            function sectorProgrammesInsertReturnValues(data) {
                if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {
                    toastr["success"]("Sector Programmes Insertion Successful!", "New record successfully inserted!");
                    getSectorProgrammes();
                    document.getElementById("txtProgrammeName_i").value = '';
                    document.getElementById("txtProgrammeCode_i").value = '';
                    document.getElementById("butcloseinsert").click();
                    //ReturnToList();
                } else {
                    toastr["error"]("Sector Programmes with that name already exist", "Duplicate Entry!");
                }
            }
            function DeleteItem(arg) {
                //$('#deletedialogx').iziModal('open');
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
                    url: "<%= Utility.SITE_URL%>/SectorProgrammesServlet",
                    data: {option: "<%= Utility.OPTION_DELETE%>", id: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        $('#deleteModal').iziModal('close');
                        if (data.indexOf("<%= Utility.ActionResponse.DELETED.toString()%>") !== -1) {
                            toastr["success"]("Sector Programmes deleted Successfull!", "Delete Successfull");
                            getSectorProgrammes();
                            document.getElementById("butclosedel").click();
                        }
                    },
                    error: function (a, b, c) {
                        toastr["error"]("Sector Programmes delete Failed!", "No record deleted!");
                    }
                });
            }
        </script>
    </body>
</html>
