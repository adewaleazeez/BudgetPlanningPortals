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
                                    <h2 class="title">MDAs</h2>
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
                                        <li class="active">MDAs</li>
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
                                                <div id="create-area">
                                                    <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">
                                                        
                                                        <!--div class="form-group has-feedback">
                                                            <label>Sector</label>
                                                            <select class='js-states form-control' id='sector_id1' disabled ></select>
                                                        </div-->
                                                        <div class="form-group has-feedback col-sm-10">
                                                            <label>Sub Sector</label>
                                                            <select class='js-states form-control' id='sub_sector_id1' disabled></select>
                                                        </div>
                                                        <div class="form-group has-feedback col-sm-10">
                                                            <label>Name</label>
                                                            <input type="text" class="form-control" id="name1" name="name1" placeholder="name">
                                                        </div>
                                                        <div class="form-group has-feedback col-sm-10">
                                                            <label>MDA Type</label>
                                                            <select id="mda_type1"><option value="0"></option><option value="1">Agency</option><option value="2">Department</option><option value="3">Ministry</option><option value="3">Parastatals</option></select>
                                                        </div>
                                                        <div class="form-group has-feedback col-sm-10">
                                                            <label>MDA Weight</label>
                                                            <input type="text" class="form-control" id="mda_weight1" name="name1" placeholder="MDA Weight">
                                                        </div>
                                                        <div class="form-group has-feedback col-sm-10">
                                                            <label>Admin Segment</label>
                                                            <input type="text" class="form-control" id="admin_segment1" name="name1" placeholder="Administrative Segment">
                                                        </div>
                                                        <button onclick="saveMda();" type="button" class="btn btn-success btn-labeled pull-right">Create MDA<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                        <button type="button" onclick="ReturnToList()" class="btn btn-danger btn-labeled" style="float: left;">
                                                            Cancel Add MDA 
                                                            <span class="btn-label btn-label-right"><i class="fa fa-remove"></i></span>
                                                        </button>
                                                    </form>
                                                </div>

                                                <!-- Nav tabs -->
                                                <div id="list-area">
                                                    <!--div class="col-sm-3">
                                                        <div class="input-group">
                                                            <label for="sector_id0">Sector&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                            <select class='js-states form-control' id='sector_id0' onchange='getSubSectors(this.id);'></select>
                                                        </div>
                                                    </div-->

                                                    <div class="col-sm-6">
                                                        <div class="input-group col-sm-8">
                                                            <label for="sub_sector_id0">Sub&nbsp;Sector</label>
                                                            <select class='js-states form-control custom_select' id='sub_sector_id0' onchange='getMDAS(this.id);'></select>
                                                        </div>
                                                    </div>

                                                    <div class="col-sm-6">
                                                        <a class="btn btn-info toggle-code-handle pull-right" onclick="crateMDA();" role="button"><i class="fa fa-plus"> </i>New MDA</a>
                                                    </div>
                                                    
                                                    <br style="clear: both;" />
                                                    <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                        <li role="presentation" class="active"><a class="" onclick="setDropdownMDA();" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">MDAs</a></li>
                                                        <li role="presentation" class="blocked"><a class="" onclick="getUncategorizedMDA();" href="#blocked" aria-controls="blocked" role="tab" data-toggle="tab" aria-expanded="true">Uncategorized MDAs</a></li>
                                                        <li role="presentation" class="blocked"><a class="" onclick="getAllMDA();" href="#blocked2" aria-controls="blocked" role="tab" data-toggle="tab" aria-expanded="true">All MDAs</a></li>
                                                    </ul>
                                                    <div class="tab-content bg-white p-15">
                                                        <div role="tabpanel" class="tab-pane active" id="active"></div>
                                                        <div role="tabpanel" class="tab-pane" id="blocked"></div>
                                                        <div role="tabpanel" class="tab-pane" id="blocked2"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="panel" id="EditSection">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h5>Edit MDA</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">
                                                    <input type="hidden" id="txtId_e" >
                                                    <!--div class="form-group has-feedback">
                                                        <label>Sector</label>
                                                        <select class='js-states form-control' id='sector_id2' disabled ></select>
                                                    </div-->
                                                    <div class="form-group has-feedback col-sm-10">
                                                        <label>Sub Sector</label>
                                                        <select class='js-states form-control' id='sub_sector_id2' ></select>
                                                    </div>
                                                    <div class="form-group has-feedback col-sm-10">
                                                        <label>Name</label>
                                                        <input type="text" class="form-control" id="name2" name="name2" placeholder="name">
                                                    </div>
                                                    <div class="form-group has-feedback col-sm-10">
                                                        <label>MDA Type</label>
                                                        <select id="mda_type2"><option value="0"></option><option value="1">Agency</option><option value="2">Department</option><option value="3">Ministry</option><option value="3">Parastatals</option></select>
                                                    </div>
                                                    <div class="form-group has-feedback col-sm-10">
                                                        <label>MDA Weight</label>
                                                        <input type="text" class="form-control" id="mda_weight2" name="name1" placeholder="MDA Weight">
                                                    </div>
                                                    <div class="form-group has-feedback col-sm-10">
                                                        <label>Admin Segment</label>
                                                        <input type="text" class="form-control" id="admin_segment2" name="name1" placeholder="Administrative Segment">
                                                    </div>
                                                    <button onclick="FinishEdit()" type="button" class="btn btn-success btn-labeled pull-right">Update MDA<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                    <button onclick="CancelEdit()" type="button" class="btn btn-danger btn-labeled pull-left">Cancel Edit MDA<span class="btn-label btn-label-right"><i class="fa fa-remove"></i></span></button>
                                                </form>
                                                <br style="clear: both;" />
                                            </div>
                                        </div>
                                        
                                        <div id="deleteModal" role="dialog" style="display: none;">
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

        <script type="text/javascript">
            checkLogin();
            $(document).ready(function(){
                $('#deleteModal').iziModal();
                //getSectors();
                getSubSectors();
                //getMDAS('sector_id0');
                $("#sector_id0, #sector_id1, #sector_id2, #sub_sector_id0, #sub_sector_id1, #sub_sector_id2, #sub_sector_id3, #mda_type1, #mda_type2").select2();
            });
            
            function getSectors(){
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/SectorServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL %>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: sectroReturnValues,
                    error: function(){ toastr["error"]("Sector Selection Failed!", "No record selected!!!");}
                });
            }
            function sectroReturnValues(data){
                 if(data.length>0){
                    var resp ="";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if(attrName === "0"){
                                resp += "<option value='"+attrValue+"'>";
                            }
                            if(attrName === "1"){
                                resp += attrValue+"</option>";
                            }
                        }
                    }
                    $('#sector_id0').html("<option value='0'></option>"+resp);
                    $('#sector_id1').html(resp);
                    $('#sector_id2').html(resp);
                }
            }
            
            function getSubSectors(){
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/SubSectorServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID %>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: subSectroReturnValues,
                    error: function(){ toastr["error"]("Sub Sector Selection Failed!", "No record selected!!!");}
                });
            }
            function subSectroReturnValues(data){
                 if(data.length>0){
                    var resp ="";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if(attrName === "0"){
                                resp += "<option value='"+attrValue+"'>";
                            }
                            if(attrName === "1"){
                                resp += attrValue+"</option>";
                            }
                        }
                    }
                    $('#sub_sector_id0').html("<option value='00'></option>"+resp);
                    $('#sub_sector_id1').html(resp);
                    $('#sub_sector_id2').html("<option value='00'></option>"+resp);
                    subsectorcodes = "<option value=''></option>"+resp;
                }
            }
            var subsectorcodes = null;

            function setMda(id){
                if(id==="sector_id1"){
                    document.getElementById("name1").value="";
                    $('#mda_type1').val("0").trigger('change');
                    document.getElementById("mda_weight1").value="";
                }
                if(id==="sector_id2"){
                    document.getElementById("name2").value="";
                    $('#mda_type2').val("0").trigger('change');
                    document.getElementById("mda_weight2").value="";
                }
            }
            
            function getMDAS(arg){
                ShowLoading();
                var id = document.getElementById(arg).value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/MdaServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID %>", sub_sector_code: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: mdaReturnValues,
                    error: function(){ StopLoading(); toastr["error"]("Mda Selection Failed!", "No record selected!");}
                });
            }
            function mdaReturnValues(data){
                if(data.length>0){
                    var wght = 0.0;
                    var resp ="<table id='main-table' class='table table-clean table-striped' border='0'><thead><tr><td><b>Admin&nbsp;Seg.</b></td><td><b>MDAs</b></td><td><b>Types</b></td><td><b><div id='wght'></div></b></td><td></td><td></td></tr></thead><tbody>";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var rec_id="";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if(attrName === "0"){
                                rec_id = attrValue;
                            }
                            if(attrName === "1"){
                                resp += "<tr><td class='line-height-25 w-10'><b>"+attrValue+"</b></td>";
                            }
                            if(attrName === "2"){
                                resp += "<td class='line-height-30 w-25'><b>"+attrValue+"</b></td>";
                            }
                            if(attrName === "3"){
                                resp += "<td class='line-height-30 w-15' align='center'><b>"+attrValue+"</b></td>";
                            }
                            if(attrName === "4"){
                                resp += "<td class='line-height-30 w-10' style='text-align: center'><b>"+parseFloat(attrValue).toFixed(2)+"</b></td>";
                                wght += parseFloat(attrValue);
                                resp += "<td class='line-height-30 w-10'><button onclick='EditItem("+rec_id+");' type='button' class='btn btn-success btn-labeled pull-left'>Edit<span class='btn-label btn-label-right'><i class='fa fa-edit'></i></span></button></td>";
                                resp += "<td class='line-height-30 w-10'><button onclick='deleteItem("+rec_id+");' type='button' class='btn btn-danger btn-labeled pull-right'>Delete<span class='btn-label btn-label-right'><i class='fa fa-remove'></i></span></button></td></tr>";
                            }
                        }
                    }
                    resp += "</tbody></table>";
                    $('#active').html(resp);
                    $('#main-table').dataTable({
                        "pagingType": "full_numbers",
                        "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]]
                    });
                    $('#wght').html("Weights("+wght.toFixed(2)+"%)");
                    
                }else{
                     $('#active').html("");
                }
                StopLoading();
            }
            function setDropdownMDA(){
                document.getElementById('sub_sector_id0').disabled = false;
            }
            
            function getAllMDA(){
                $('#sub_sector_id0').val("0").trigger('change');
                document.getElementById('sub_sector_id0').disabled = true;
                $('#active').html("");
                ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/MdaServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL_BY_MDAS %>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: allMDAReturnValues,
                    error: function(){ StopLoading(); toastr["error"]("Mda Selection Failed!", "No record selected!");}
                });
            }
            function allMDAReturnValues(data){
                if(data.length>0){
                    var wght = 0.0;
                    var resp ="<table id='main-table-blocked2' class='table table-clean table-striped' border='0'><thead><tr><td><b>Admin&nbsp;Seg.</b></td><td><b>MDAs</b></td><td><b>Types</b></td><td><b><div id='wght2'></div></b></td><td>Sub&nbsp;Sector</td></tr></thead><tbody>";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var rec_id="";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if(attrName === "0"){
                                rec_id = attrValue;
                            }
                            if(attrName === "1"){
                                resp += "<tr><td class='line-height-25 w-10'><b>"+attrValue+"</b></td>";
                            }
                            if(attrName === "2"){
                                resp += "<td class='line-height-30 w-25'><b>"+attrValue+"</b></td>";
                            }
                            if(attrName === "3"){
                                resp += "<td class='line-height-30 w-15' align='center'><b>"+attrValue+"</b></td>";
                            }
                            if(attrName === "4"){
                                resp += "<td class='line-height-30 w-10' style='text-align: center'><b>"+parseFloat(attrValue).toFixed(2)+"</b></td>";
                                wght += parseFloat(attrValue);
                            }
                            if(attrName === "5"){
                                resp += "<td class='line-height-30 w-15' align='center'><b><select class='js-states form-control' onchange=updateSubSectorCode(this.value,'"+rec_id+"') >"+subsectorcodes+"</select></b></td></tr>";
                            }
                        }
                    }
                    resp += "</tbody></table>";
                    $('#blocked2').html(resp);
                    $('#main-table-blocked2').dataTable({
                        "pagingType": "full_numbers",
                        "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]]
                    });
                    $('#wght2').html("Weights("+wght.toFixed(2)+"%)");
                    
                }else{
                     $('#blocked2').html("");
                }
                StopLoading();
            }

            function getUncategorizedMDA(){
                $('#sub_sector_id0').val("0").trigger('change');
                document.getElementById('sub_sector_id0').disabled = true;
                $('#active').html("");
                ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/MdaServlet",
                    data: {option: "<%= Utility.OPTION_SELECT %>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: uncategorizedMDAReturnValues,
                    error: function(){ StopLoading(); toastr["error"]("Mda Selection Failed!", "No record selected!");}
                });
            }
            function uncategorizedMDAReturnValues(data){
                if(data.length>0){
                    var wght = 0.0;
                    var resp ="<table id='main-table-blocked' class='table table-clean table-striped' border='0'><thead><tr><td><b>Admin&nbsp;Seg.</b></td><td><b>MDAs</b></td><td><b>Types</b></td><td><b><div id='wght2'></div></b></td><td>Sub&nbsp;Sector</td></tr></thead><tbody>";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var rec_id="";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = "" + attrValue;
                            if(attrName === "0"){
                                rec_id = attrValue;
                            }
                            if(attrName === "1"){
                                resp += "<tr><td class='line-height-25 w-10'><b>"+attrValue+"</b></td>";
                            }
                            if(attrName === "2"){
                                resp += "<td class='line-height-30 w-25'><b>"+attrValue+"</b></td>";
                            }
                            if(attrName === "3"){
                                resp += "<td class='line-height-30 w-15' align='center'><b>"+attrValue+"</b></td>";
                            }
                            if(attrName === "4"){
                                resp += "<td class='line-height-30 w-10' style='text-align: center'><b>"+parseFloat(attrValue).toFixed(2)+"</b></td>";
                                wght += parseFloat(attrValue);
                            }
                            if(attrName === "5"){
                                resp += "<td class='line-height-30 w-15' align='center'><b><select class='js-states form-control' onchange=updateSubSectorCode(this.value,'"+rec_id+"') >"+subsectorcodes+"</select></b></td></tr>";
                            }
                        }
                    }
                    resp += "</tbody></table>";
                    $('#blocked').html(resp);
                    $('#main-table-blocked').dataTable({
                        "pagingType": "full_numbers",
                        "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]]
                    });
                    $('#wght2').html("Weights("+wght.toFixed(2)+"%)");
                    
                }else{
                     $('#blocked').html("");
                }
                StopLoading();
            }
            function updateSubSectorCode(val, id){
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/MdaServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE_ALL %>", id: id, sub_sector_code: val},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(){ getUncategorizedMDA() },
                    error: function(){ StopLoading(); toastr["error"]("Mda Update Failed!", "No record updated!");}
                });
            }
                
            EditItem = function (arg) {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/MdaServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD %>", id: arg},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function(data){
                        if(data.length>0){
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
                                        //alert($('#sub_sector_id0').val());
                                        $('#sub_sector_id2').val(attrValue).trigger('change');  
                                    }
                                    if(attrName === "2"){
                                        document.getElementById("name2").value = attrValue;
                                    }
                                    if(attrName === "3"){
                                        if(attrValue==="Agency"){
                                            attrValue = "1";
                                        }
                                        if(attrValue==="Department"){
                                            attrValue = "2";
                                        }
                                        if(attrValue==="Ministry"){
                                            attrValue = "3";
                                        }
                                        $('#mda_type2').val(attrValue).trigger('change');  
                                    }
                                    if(attrName === "4"){
                                        document.getElementById("mda_weight2").value = attrValue;
                                    }
                                    if(attrName === "5"){
                                        document.getElementById("admin_segment2").value = attrValue;
                                    }
                                }
                                break;
                            }
                            toastr["success"]("MDA record successfully fetched!", "MDA Selection Successfull!!!");
                            $('#list-area').hide();
                            $('#EditSection').fadeIn();
                            document.getElementById("sector_id2").focus();
                        }
                    },
                    error: function(){ 
                        toastr["error"]("Record with id " + arg + " is not found!", "MDA Selection Failed!!!");
                    }
                });                
            };
            
              
            FinishEdit = function () {
                var id = document.getElementById("txtId_e").value;
                var sub_sector_code = document.getElementById("sub_sector_id2").value;
                var name = document.getElementById("name2").value;
                var mda_type = $( "#mda_type2 option:selected" ).text();
                var mda_weight = document.getElementById("mda_weight2").value;
                var admin_segment = document.getElementById("admin_segment2").value;
                var error = "";
                if(sub_sector_code==="0"){
                    sub_sector_code="";
                }
                //if(sub_sector_code===""){
                //    error += "Sector must not be blank<br>";
                //}
                if(name===""){
                    error += "MDA must not be blank<br>";
                }
                if(admin_segment.length!==12){
                    error += "Admin Segment must be 12 digits<br>";
                }
                if(error.length>0){
                    toastr["error"](error, "Please correct the following:");
                    return true;
                }
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/MdaServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE %>", sub_sector_code: sub_sector_code, name: name, id: id, mda_type: mda_type, mda_weight: mda_weight, administrative_segment: admin_segment},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function(data){
                        $('#deleteModal').iziModal('close');
                        if(data.indexOf("<%= Utility.ActionResponse.UPDATED.toString() %>")!== -1){
                            toastr["success"]("Mda Update Successful!", "Record Updated!!!");
                            getMDAS('sub_sector_id0');
                            $('#EditSection').hide();
                            $('#list-area').fadeIn();
                        } else if(data.indexOf("invalidweight") !== -1){
                            toastr["error"]("Total sector weight is greater 100%", "Invalid Weight!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.NO_RECORD.toString() %>")!== -1) {
                            toastr["error"]("Sector with that name does exist", "Update Failed");
                        }else{
                            toastr["error"]("Mda Update Failed!", "No Record Deleted!!!");
                        }
                    },
                    error: function(){ 
                        $('#deleteModal').iziModal('close');
                        toastr["error"]("Record with id " + arg + " is not found!", "MDA Delete Failed!!!");
                    }
                });
                
            };
            
            CancelEdit = function () {
                $('#EditSection').hide();
                $('#list-area').fadeIn();
            };
            
            var crateMDA = function () {
                var id = $( "#sub_sector_id0 option:selected" ).text();
                if(id===""){
                    toastr["error"]("Blank sub sector is not allowed.", "Invalid Sector!!!");
                    return true;
                }else{
                    id = $("#sub_sector_id0").val();
                    $('#sub_sector_id1').val(id).trigger('change'); 
                    $('#list-area').hide();
                    $('#create-area').fadeIn();
                    $('#MainSection .panel-title').fadeIn();
                    document.getElementById("name1").value="";
                    $( "#mda_type1 option:selected" ).val(0);
                    document.getElementById("mda_weight1").value="";
                    document.getElementById("admin_segment1").value="";
                }
            };

            var ReturnToList = function () {
                $('#create-area').hide();
                $('#MainSection .panel-title').hide();
                $('#list-area').fadeIn();
            };
            
            function saveMda(){
                var sub_sector_code = document.getElementById("sub_sector_id1").value;
                var name = document.getElementById("name1").value;
                var mda_type = $( "#mda_type1 option:selected" ).text();
                var mda_weight = document.getElementById("mda_weight1").value;
                var admin_segment = document.getElementById("admin_segment1").value;
                var error = "";
                if(sub_sector_code==="0"){
                    error += "Sub Sector must not be blank<br>";
                }
                if(name===""){
                    error += "MDA must not be blank<br>";
                }
                if(admin_segment.length!==12){
                    error += "Admin Segment must be 12 digits<br>";
                }
                if(error.length>0){
                    toastr["error"](error, "Please correct the following:");
                    return true;
                }
                //alert("sector_id: "+sector_id);
                //alert("name "+name);
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/MdaServlet",
                    data: {option: "<%= Utility.OPTION_INSERT %>", sub_sector_code: sub_sector_code, name: name, mda_type: mda_type, mda_weight: mda_weight, administrative_segment: admin_segment},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: mdaInsertReturnValues,
                    error: function(){ toastr["error"]("MDA Insert Failed!", "No record inserted!");}
                });
            }
            
            function mdaInsertReturnValues(data){
                if(data.indexOf("<%= Utility.ActionResponse.INSERTED.toString() %>")!== -1){
                            toastr["success"]("Mda Insert Successful!", "New record successfully inserted!");
                    getMDAS('sub_sector_id0');               
                    ReturnToList();
                } else if(data.indexOf("invalidweight") !== -1){
                    toastr["error"]("Total sector weight is greater 100%", "Invalid Weight!");
                }else{
                    toastr["error"]("Record already exists!", "No Record Inserted!!!");
                }
            }
            
            deleteItem = function (arg) {
                window.deleteId = arg;
                $('#deleteModal').iziModal('open');
            };
            
            finishDeleteItem = function () {
                var id = window.deleteId;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/MdaServlet",
                    data: {option: "<%= Utility.OPTION_DELETE %>", id: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function(data){
                        $('#deleteModal').iziModal('close');
                        if(data.indexOf("<%= Utility.ActionResponse.DELETED.toString() %>")!== -1){
                            toastr["success"]("Mda Delete Successful!", "Record Deleted!!!");
                            getMDAS('sub_sector_id0');               
                            ReturnToList();
                        }else{
                            toastr["error"]("Mda Delete Failed!", "No Record Deleted!!!");
                        }
                    },
                    error: function(){ 
                        $('#deleteModal').iziModal('close');
                        toastr["error"]("Record with id " + arg + " is not found!", "MDA Delete Failed!!!");
                    }
                });   
            };
            
            var removeWindowId = function () {
                window.deleteId = 0;
            };
              
        </script>

    </body>
</html>
