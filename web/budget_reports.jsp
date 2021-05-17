<%-- 
    Document   : budget_reports
    Created on : Oct 12, 2017, 6:38:44 AM
    Author     : Ola
--%>

<%@page import="com.bpp.utility.Utility"%>
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
                                    <h2 class="title">Budget Reports </h2>
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
                                        <li class="active">Budget Reports</li>
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
                                <!--***************************************************Page Area START*********************************************************************-->
                                <!************************************************-->
                                <div class="container">
                                    
                                                       <br style="clear: both;" />
                                                <!-- Nav tabs -->
                                                <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                    <li role="presentation" class="active"><a class="" href="#EconomicSegmentTab" aria-controls="EconomicSegmentTab" role="tab" data-toggle="tab" aria-expanded="true">Economic Segment</a></li>
                                                    <li role="presentation" class=""><a class="" href="#AdministrativeSegmentTab" aria-controls="AdministrativeSegmentTab" role="tab" data-toggle="tab" aria-expanded="false">Administrative Segment</a></li>
                                                    <li role="presentation" class=""><a class="" href="#SectorsTab" aria-controls="SectorsTab" role="tab" data-toggle="tab" aria-expanded="false">Sectors / Sub-sectors</a></li>
                                                </ul>

                                                <!-- Tab panes -->
                                                <div class="tab-content bg-white p-15">
                                                    <div role="tabpanel" class="tab-pane active" id="EconomicSegmentTab">
                                                        <div class="row">
                                                            <div class="col-xs-10 col-md-10">
                                                                <!--div>  <button class="btn btn-success" onclick="DoSegmentReoprt(3,'EconomicSegment')" type="button"><span class="glyphicon glyphicon-home"></span> <br/>Economic Segment</button> </div-->
                                                                <div id="EconomicSgements" style="margin-top: 10px"></div>
                                                                <div id="SegmentItems"></div>
                                                            </div>
                                                            <div class="col-xs-6 col-md-6">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane" id="AdministrativeSegmentTab">
                                                        <div id="lstSectors"></div><div id="lstSubSectors"></div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane" id="SectorsTab">
                                                            <div id="lstSubSectors1"></div><div id="SectorItems"></div>
                                                    </div>
                                                </div>
                                                
                                                

                                </div>
                                <!************************************************-->
                                <hr>
                                <div id="txtReportPad" class="table-responsive"></div>
                                <!--***************************************************Page Area END*********************************************************************-->
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
                getEconomicSegmentHeader1();
                getSectors();
                getBudgetYear();
                
               // doReport(0);

            });
            var _budgetyear;
            function doReport(id) {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/BudgetReportServlet",
                    data: {option: "<%= Utility.OPTION_SELECT%>", option1: id},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: ReportsReturnValues,
                    error: function (a, b, c) {
                        //console.log("a: " + a + " b: " + b + " c: " + c);
                        toastr["error"]("No record selected!", "Budget Year Selection Failed!!!");
                    }
                });
            }
            function getBudgetYear() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/SectorCeilingServlet",
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
            function ReportsReturnValues(data) {

                $('#txtReportPad').html(data);
            };
            function DoSegmentReoprt(id,segment){
                ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/BudgetReportServlet",
                    data: {option: "<%= Utility.OPTION_SELECT%>", option1: id, Heading: segment, budget_year:_budgetyear},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(data){
                        $('#txtReportPad').html(data);
                    },
                    error: function (a, b, c) {
                        //console.log("a: " + a + " b: " + b + " c: " + c);
                        toastr["error"]("No record selected!", "Budget Year Selection Failed!!!");
                    }
                });
                StopLoading();
            }
            function DoSegmentReoprtItem(id,segment, itm){
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/BudgetReportServlet",
                    data: {option: "<%= Utility.OPTION_SELECT%>", option1: id, Heading: segment, budget_year:_budgetyear, EH1ReportItem:itm},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(data){
                        $('#txtReportPad').html(data);
                        StopLoading();
                    },
                    error: function (a, b, c) {
                        //console.log("a: " + a + " b: " + b + " c: " + c);
                        toastr["error"]("No record selected!", "Budget Year Selection Failed!!!");
                        StopLoading();
                    }
                });           
                StopLoading();
            };      
            function budgetYearReturnValues(data) {
                                                                        if (data.length > 0) {
                                                                            var resp = "";
                                                                            var tmp = 0;

                                                                            for (var i = 0; i < data.length; i++) {
                                                                                var obj = data[i];
                                                                                for (var key in obj) {
                                                                                    var attrName = key;
                                                                                    var attrValue = obj[key];
                                                                                    if (attrValue === null || attrValue === 'null') {
                                                                                        attrValue = "";
                                                                                    }
                                                                                    if (attrName === "0") {
                                                                                        tmp = attrValue;
                                                                                    }
                                                                                    if (attrName == "2") {
                                                                                        if (tmp == 1) {
                                                                                            _budgetyear = attrValue;
                                                                                            //alert(_budgetyear);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }

                                                                        }
                                                                    }                      
            function getEconomicSegmentHeader1(){
                ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/EconomicSegmentServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL %>", entity : "EconomicSegmentHeader1"},
                    async:false,
                    dataType: "json",
                    success: economicSegmentHeader1ReturnValues,
                    error: function(){ StopLoading();  toastr["error"]("Economic Segment Select Failed!", "No record selected!");}
                });
            }          
            function economicSegmentHeader1ReturnValues(data){
            
            StopLoading();          
                    var txt="";
                    var value = {};
                    var tmp="";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if(attrValue === null || attrValue === 'null'){
                                attrValue = "";
                            }
                            if(attrName === "1"){
                                value.name = attrValue;
                            }
                            if(attrName === "2"){
                                value.code = attrValue;
                            }
                            if(attrName === "0"){
                                value.id = attrValue;
                            }
                        }
                      var id=value.id;
                        tmp="<button class='btn btn-success' onclick='DoSegmentReoprtItem(4, \"EconomicSegment\" , valueid )' type='button'>valuename</button>&nbsp;&nbsp; ";
                        tmp=tmp.replace("valueid", value.id);
                        tmp=tmp.replace("valuename", value.name);
                        txt += tmp;
                    }
                   $('#SegmentItems').html(txt);
                StopLoading();

            }
            function getSectors() {
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
                var txt="";
                if (data.length > 0) {
                    var value = {};
                    var tmp="";
                    var sec="";
                    var resp =null;
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        
                        var recId = 0;
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = " " + attrValue;
                            if (attrName === "5") {                                
                                value.id = attrValue;
                            }
                            if (attrName === "1") {
                                value.name = attrValue ;
                            }
                        }
                        sec+="<option value='"+value.id+"'>"+ value.name + "</option>";
                        tmp="<button class='btn btn-default' onclick='DoSegmentReoprtItem(5, \"Sectors\" , valueid )' type='button'>valuename</button>&nbsp;&nbsp; ";
                        tmp=tmp.replace("valueid", value.id);
                        tmp=tmp.replace("valuename", value.name);
                        txt += tmp;
                    }
                }
                $('#SectorItems').html(txt);
            }            
            function getSubSectors(arg){
                var id = document.getElementById(arg).value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/SubSectorServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID %>", sector_id: id},
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
                    $('#sub_sector_id0').html("<option value='0'></option>"+resp);
                    $('#sub_sector_id1').html(resp);
                    $('#sub_sector_id2').html(resp);
                }
            }            
            function getMDAS(arg){
                var id = document.getElementById(arg).value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/MdaServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID %>", sub_sector_id: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: mdaReturnValues,
                    error: function(){ toastr["error"]("Mda Selection Failed!", "No record selected!");}
                });
            }
            function mdaReturnValues(data){
                 if(data.length>0){
                    var resp ="<table id='main-table' class='table table-clean table-striped' border='0'><thead><tr><td><b>MDAs</b></td><td><b>MDA&nbsp;Types</b></td><td><b>MDA&nbsp;Weights</b></td><td><b>Admin&nbsp;Segments</b></td><td></td></tr></thead><tbody>";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var rec_id="";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = " " + attrValue;
                            if(attrName === "0"){
                                rec_id = attrValue;
                            }
                            if(attrName === "1"){
                                resp += "<tr><td class='line-height-70 w-25'><small><b>"+attrValue+"</b></small></td>";
                            }
                            if(attrName === "2"){
                                resp += "<td class='line-height-30 w-15'><small><b>"+attrValue+"</b></small></td>";
                            }
                            if(attrName === "3"){
                                resp += "<td class='line-height-30 w-15' align='center'><small><b>"+attrValue+"</b></small></td>";
                            }
                            if(attrName === "4"){
                                resp += "<td class='line-height-30 w-10' style='text-align: center'><small><b>"+attrValue+"</b></small></td>";
                                resp += "<td><button onclick='EditItem("+rec_id+");' type='button' class='btn btn-success btn-labeled pull-left'>Edit<span class='btn-label btn-label-right'><i class='fa fa-edit'></i></span></button>&nbsp;";
                                resp += "<button onclick='deleteItem("+rec_id+");' type='button' class='btn btn-danger btn-labeled pull-right'>Delete<span class='btn-label btn-label-right'><i class='fa fa-remove'></i></span></button></td></tr>";
                            }
                        }
                    }
                    resp += "</tbody></table>";
                    $('#active').html(resp);
                    $('#main-table').dataTable({
                        "pagingType": "full_numbers",
                        "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]]
                    });
                }else{
                     $('#active').html("");
                }
            }
              
        </script>

</html>
