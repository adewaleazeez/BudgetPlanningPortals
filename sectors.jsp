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
        <link rel='stylesheet' href="css/jquery.loading.css" >
        <!-- ========== THEME CSS ========== -->
        <link rel="stylesheet" href="css/main.css" media="screen">
        <!-- ========== MODERNIZR ========== -->
        <script src="js/modernizr/modernizr.min.js"></script>
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
                                    <h2 class="title">Sectors</h2>
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
                                        <li class="active">Sectors</li>
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
                                                    <h5>Add Sector</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <div id="create-area">
                                                    <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">

                                                        <!--                                                        <div class="form-group has-feedback">
                                                                                                                    <label>Sector</label>
                                                                                                                    <div id="select_sector1"></div>
                                                                                                                    
                                                                                                                </div>-->
                                                        <div class="form-group has-feedback">
                                                            <label>Sector Code</label>
                                                            <input type="text" class="form-control" id="code1" name="name" placeholder="name">
                                                        </div>
                                                        <div class="form-group has-feedback">
                                                            <label>Sector Name</label>
                                                            <input type="text" class="form-control" id="name1" name="name" placeholder="name">
                                                        </div>
                                                        <div class="form-group has-feedback">
                                                            <label>Sector Weight</label>
                                                            <input type="text" class="form-control" id="sector_weight1" name="sector_weight1" placeholder="Sector Weight">
                                                        </div>
                                                        <button onclick="saveSector()" type="button" class="btn btn-success btn-labeled pull-right">Save Sector<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                        <button onclick="ReturnToList()" type="button" class="btn btn-danger btn-labeled pull-left">Cancel Add<span class="btn-label btn-label-right"><i class="fa fa-close"></i></span></button>
                                                    </form>
                                                </div>

                                                <!-- Nav tabs -->
                                                <div id="list-area">
                                                    <div class="col-md-12 right-side">
                                                        <a class="btn btn-info toggle-code-handle pull-right show-create" role="button"><i class="fa fa-plus"> </i>New Sector</a>
                                                    </div>
                                                    <br style="clear: both;" />
                                                    <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                        <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">Sectors</a></li>
                                                    </ul>
                                                    <div class="tab-content bg-white p-15">
                                                        <div role="tabpanel" class="tab-pane active" id="active">

                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="panel" id="EditSection">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h5>Edit Sector</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">


                                                    <div class="form-group has-feedback">
                                                        <label>Sector Code</label>
                                                        <input type="text" class="form-control" id="code2" name="name" placeholder="name">
                                                    </div>
                                                    <div class="form-group has-feedback">
                                                        <label>Sector Name</label>
                                                        <input type="text" class="form-control" id="name2" name="name" placeholder="name">
                                                        <input type="hidden" id="updateid" name="id"/>
                                                    </div>
                                                    <div class="form-group has-feedback">
                                                        <label>Sector Weight</label>
                                                        <input type="text" class="form-control" id="sector_weight2" name="sector_weight2" placeholder="Sector Weight">
                                                    </div>
                                                    <button onclick="FinishEdit()" type="button" class="btn btn-success btn-labeled pull-right">Update Sector<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                    &nbsp; <button onclick="cancelEdit()" type="button" class="btn btn-danger btn-labeled pull-left">Cancel Edit<span class="btn-label btn-label-right"><i class="fa fa-close"></i></span></button>
                                                </form>
                                                <br style="clear: both;" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col-md-12 -->

                                    <!-- Modal -->
                                    <div id="deleteModal" style="display: none">
                                        <div class="">

                                            <!-- Modal content-->
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-izimodal-close="">&times;</button>
                                                    <h4 class="modal-title">Delete Sector</h4>
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
                                    <!--/.Modal-->

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
            getSector();

            function getSector() {
                ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SectorServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: sectorReturnValues,
                    error: function () {
                        StopLoading();
                        toastr["error"]("Sector Selection Failed!", "No record selected!");
                    }
                });
            }
            function sectorReturnValues(data) {
                StopLoading();
                if (data.length > 0) {
                    var wght = 0.0;
                    var resp = "<table id='main-table' class='table table-clean table-striped'><tbody><tr><td><b>Codes</b></td><td><b>Names</b></td><td><b><div id='wght'></div></b></td><td></td></tr>";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var recId = 0;
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if (attrName === "0") {
                                resp += "<tr >";
                                recId = attrValue;
                                resp += "<td class='line-height-30 w-10'><small><b>" + obj[5] + "</b></small></td>";
                            }
                            if (attrName === "1") {
                                resp += "<td class='line-height-30 w-20'><small><b>" + attrValue + "</b></small></td>";
                            }
                            if (attrName === "4") {
                                wght += parseFloat(attrValue);
                                resp += "<td class='line-height-30 w-10'><small><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + parseFloat(attrValue).toFixed(2) + "</b></small></td>";
                                resp += "<td class='line-height-30 w-20'></td>";
                                resp += "<td><button type='button' onclick='EditItem(" + recId + ")' class='btn btn-success'><i class='fa fa-edit'></i>Edit</button>";
                                resp += " <button type='button' class='btn btn-danger' onclick='DeleteItem(" + recId + ")'><i class='fa fa-remove'></i>Delete</button></td></tr>";
                            }

                        }
                    }
                    resp += "</tbody></table>";
                    $('#active').html(resp);
                    //$('#main-table').dataTable({
                    //    "pagingType": "full_numbers",
                    //    "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]]
                    //});
                    //document.getElementById("active").innerHTML = resp;
                    $('#wght').html("Weights("+wght.toFixed(2)+"%)");

                }

            }

            function saveSector() {
                var code = document.getElementById("code1").value;
                var name = document.getElementById("name1").value;
                var sector_weight = document.getElementById("sector_weight1").value;
                var error = "";
                if(code===""){
                    error += "Sector Code must not be blank<br>";
                }
                if(name===""){
                    error += "Sector Name must not be blank<br>";
                }
                if(sector_weight==="0"){
                    error += "Sector Weight must not be blank<br>";
                }
                if(error.length>0){
                    toastr["error"](error, "Please correct the following:");
                    return true;
                }
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SectorServlet",
                    data: {option: "<%= Utility.OPTION_INSERT%>", code: code,  name: name, sector_weight: sector_weight},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: sectorInsertReturnValues,
                    error: function () {
                        toastr["error"]("Sector Insert Failed!", "No record inserted!");
                    }
                });
            }
            function sectorInsertReturnValues(data) {
                document.getElementById("name1").value = '';
                if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {
                    toastr["success"]("Sector Insertion Successful!", "New record successfully inserted!");
                    getSector();
                    ReturnToList();
                } else if(data.indexOf("invalidweight") !== -1){
                    toastr["error"]("Total sector weight is greater 100%", "Invalid Weight!");
                } else {
                    toastr["error"]("Sector with that code already exist", "Duplicate Entry!");
                }
            }

            function EditItem(arg) {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SectorServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: arg},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        //console.log(data);
                        if(data.length>0){
                            for (var i = 0; i < data.length; i++) {
                                var obj = data[i];
                                for (var key in obj) {
                                    var attrName = key;
                                    var attrValue = obj[key];
                                  
                                    if(attrValue === null || attrValue === 'null'){
                                        attrValue = "";
                                    }
                                    if(attrName === "0"){
                                        document.getElementById("updateid").value = attrValue;
                                        document.getElementById("code2").value = obj[5];
                                    }
                                    if(attrName === "1"){
                                        document.getElementById("name2").value = attrValue;
                                    }
                                    if(attrName === "4"){
                                        document.getElementById("sector_weight2").value = attrValue;
                                    }
                                }
                                break;
                            }
                            toastr["success"]("Sector successfully fetched!", "Sector Fetch Successfull!!!");
                            $('#MainSection').hide();
                            $('#EditSection').fadeIn();
                            $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li onclick='closeEdit()'> Sector</li><li class='active' >Edit Sector</li>");
                            document.getElementById("code2").focus();
                        }
                    },
                    error: function (e, b, c) {
                        toastr["error"]("Record with id " + arg + " is not found!", "Sector Selection Failed!!!");
                    }
                });
            }

            function FinishEdit() {
                var code = document.getElementById("code2").value;
                var name = document.getElementById("name2").value;
                var sector_weight = document.getElementById("sector_weight2").value;
                var id = document.getElementById("updateid").value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SectorServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE%>", code: code, name: name, id: id, sector_weight: sector_weight},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.indexOf("<%= Utility.ActionResponse.UPDATED.toString() %>")!== -1) {
                            toastr["success"]("Sector update Successfull!", "Update Successfull");
                            cancelEdit();
                        } else if(data.indexOf("invalidweight") !== -1){
                            toastr["error"]("Total sector weight is greater 100%", "Invalid Weight!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.NO_RECORD.toString() %>")!== -1) {
                            toastr["error"]("Sector with that name does exist", "Update Failed");
                        } else if (data.indexOf("<%= Utility.ActionResponse.RECORD_EXISTS.toString() %>")!== -1) {
                            toastr["error"]("Sector with that name already exists", "Update Failed");
                        }
                    },
                    error: function (a, b, c) {
                        alert(a+"   "+b+"   "+c);
                        toastr["error"]("Sector Update Failed!", "No record updated!");
                    }
                });
            }

            function cancelEdit() {
                $('#EditSection').hide();
                $('#MainSection').fadeIn();
                getSector();
                document.getElementById("code2").value = '';
                document.getElementById("name2").value = '';
                document.getElementById("updateid").value = '';
            }

            function DeleteItem(arg) {
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
                    url: "<%= Utility.SITE_URL%>/SectorServlet",
                    data: {option: "<%= Utility.OPTION_DELETE%>", id: id},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        $('#deleteModal').iziModal('close');
                        if (data.indexOf("<%= Utility.ActionResponse.DELETED.toString() %>")!== -1) {
                            toastr["success"]("Sector deleted Successfull!", "Delete Successfull");
                            getSector();
                        }
                    },
                    error: function (a, b, c) {
                        toastr["error"]("Sector delete Failed!", "No record deleted!");
                    }
                });
            }
        </script>
    </body>
</html>
