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
            <div class="content-wrapper" style="margin-top: 10px; margin-left: -14px;">
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
            checkLogin();
            getBudgetYear();
            getObjective();

            getPolicies();                                                                        
            getMdas();
            getDepartments();
            getSectorGoals();
            getSectorProgrammes();
            getProjects();
            $('#tabLabel4, #tabLabel5').click(function() {
                       //event.preventDefault();
                       $('#ProjectCodeEx').html("");
                       $('#ProjectCode').html("");
                       $('#txtscoreEx').html("");
                       $('#txtscore').html("");                                                                                   
                       getPolicies(); 

               });


            //$('.radio').each(function(){$(this).prop('checked', '')});
            // $('.radio').prop('checked', false);
            $('input[name="criteria1"]').attr('checked', false);
            $('input[name="criteria2"]').attr('checked', false);
            $('input[name="criteria3"]').attr('checked', false);
            $('input[name="criteria4"]').attr('checked', false);
            $('input[name="criteria5"]').attr('checked', false);
            //$('.radio').removeAttr('checked');
            //getPolicy();
            //getObjective();
            // $("#selRoles_e, #selDepartment_e, #selMda_e").select2();
        });
        var _budgetyear;
        var j1 = 0;
        j2 = 0;
        j3 = 0;
        j4 = 0;
        window._obj;// = new Array();
        window.objectiveCode;
        var PolicyCode__ = "";
        var score = 0;
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
                //resp += "</select>";
                $('#txtmda').html("<option value='0'></option>" + resp);
                $('#txtmdaEx').html("<option value='0'></option>" + resp);
            }
        }
        function getDepartments() {
            //var selMda = document.getElementById("lstmdas").value;

            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/DepartmentServlet",
                data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
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
                var resp;
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
                $('#txtdepartment').html("<option value='0'></option>" + resp);
                $('#txtdepartmentEx').html("<option value='0'></option>" + resp);
            } else {
                $('#txtdepartment').html("<option value='0'></option>");
                $('#txtdepartmentEx').html("<option value='0'></option>");
            }

        }
        function getSectorGoals() {
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/SectorGoalsServlet",
                data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
                dataType: "json",
                cache: false,
                async: false,
                success: SectorGoalsReturnValues,
                error: function () {
                    toastr["error"]("No record selected!", "Sector Goals Selection Failed!!!");
                }
            });
        }
        function SectorGoalsReturnValues(data) {
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
                        }
                    }
                }
                //resp += "</select>";
                $('#txtsectorgoal').html("<option value='0'></option>" + resp);
            }
        }
        function getSectorProgrammes() {
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/SectorProgrammesServlet",
                data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
                dataType: "json",
                cache: false,
                async: false,
                success: SectorProgrammesReturnValues,
                error: function () {
                    toastr["error"]("No record selected!", "Sector Programmes Selection Failed!!!");
                }
            });
        }
        function SectorProgrammesReturnValues(data) {
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
                        }
                    }
                }
                //resp += "</select>";
                $('#txtsectorprogramme').html("<option value='0'></option>" + resp);
            }
        }
        function getProjects() {
            ShowLoading();
            //parent_id = Window.selHeader1;
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
                data: {option: "<%= Utility.OPTION_SELECT_ALL%>", entity: "ProgrammeSegmentHeader2", parent: -1},
                dataType: "json",
                cache: false,
                async: false,
                success: programmeSegmentHeader2ReturnValues,
                error: function () {
                    StopLoading();
                    toastr["error"]("Programme Segment Header Select Failed!", "No record selected!");
                }
            });
        }
        function programmeSegmentHeader2ReturnValues(data) {
            StopLoading();
            // var table_resp_h2 = "<table class='table table-clean table-striped' ><tbody><tr><td>Code</td><td>Name</td><td></td> </tr>";
            var resp = "";
            var value = {};
            ///H2c = data[0][2];
            // H2c = '' + H2c.substring(0, 2);

            for (var i = 0; i < data.length; i++) {
                var obj = data[i];

                for (var key in obj) {
                    var attrName = key;
                    var attrValue = obj[key];
                    if (attrValue === null || attrValue === 'null') {
                        attrValue = "";
                    }
                    if (attrName === "2") {
                        value.code = attrValue;
                    }
                    if (attrName === "1") {
                        value.name = attrValue;
                    }
                    if (attrName === "0") {
                        value.id = attrValue;
                    }
                }
                resp += "<option value='" + value.id + "'>" + value.name + "</option>";

            }
            // resp += "</select>";
            // table_resp_h2 += "</tbody></table>";
            $('#txtprojectname').html("<option></option>" + resp);
            $('#txtprojectnameEx').html("<option></option>" + resp);
            StopLoading();

        }
        function getObjective() {
            ShowLoading();
            // parent_id = Window.selHeader2;
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
                data: {option: "<%= Utility.OPTION_SELECT_ALL%>", entity: "ProgrammeSegmentHeader3", parent: -1},
                dataType: "json",
                cache: false,
                async: false,
                success: programmeSegmentHeader3ReturnValues,
                error: function () {
                    StopLoading();
                    toastr["error"]("Programme Segment Header Select Failed!", "No record selected!");
                }
            });
            StopLoading();
        }
        function programmeSegmentHeader3ReturnValues(data) {
            StopLoading();
            var table1 = "<table class='table table-clean table-striped' ><tbody><tr><td>Code</td><td>Name</td><td>Score</td><td>Rank</td> </tr>";
            var tableold = "<table class='table table-clean table-striped' ><tbody><tr><td>Code</td><td>Name</td><td>Score</td><td>Rank</td> </tr>";
            var resp = "";
            var value = {};
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                for (var key in obj) {
                    var attrName = key;
                    var attrValue = obj[key];
                    if (attrValue === null || attrValue === 'null') {
                        attrValue = "";
                    }
                    if (attrName === "2") {
                        value.code = attrValue;
                    }
                    if (attrName === "1") {
                        value.name = attrValue;
                    }
                    if (attrName === "0") {
                        value.id = attrValue;
                    }
                    if (attrName === "9") {
                        value.year_id = attrValue;
                    }
                    if (attrName === "10") {
                        value.project_status = attrValue;
                    }
                    if (attrName == "11") {
                        value.project_score = attrValue;
                    }
                    if (attrName === "12") {
                        value.project_rank = attrValue;
                    }                                                                                
                }
                resp += "<option value='" + value.id + "'>" + value.name + "</option>";
                if (value.year_id >= _budgetyear)
                {
                    table1 += "<tr> <td >" + value.code + "</td>";
                    table1 += "<td>" + value.name + "</td>";
                    //table1 += "<td>" + getYear(value.year_id) + "</td>";
                    table1 += "<td>" + value.project_score + "</td>";
                    table1 += "<td>" + value.project_rank+ "</td>";
                    //table1 += "<td>" + value.project_status + "</td>";
                    table1 += "</tr>";
                } else
                {
                    tableold += "<tr> <td >" + value.code + "</td>";
                    tableold += "<td>" + value.name + "</td>";
                    //tableold += "<td>" + getYear(value.year_id) + "</td>";
                    tableold += "<td>" + value.project_score + "</td>";
                    tableold += "<td>" + value.project_rank + "</td>";                                                                                
                    //tableold += "<td>" + value.project_status + "</td>";
                    tableold += "</tr>";
                }
            }
            table1 += "</tbody></table>";
            tableold += "</tbody></table>";
            document.getElementById("NewProjectsTable").innerHTML = table1;
            document.getElementById("OldProjectsTable").innerHTML = tableold;
            $('#txtobjective').html("<option disabled value='0' class=''>Select an Objective</option>" + resp);
            $('#txtobjective').val(0).trigger('change');
            $('#txtobjectiveEx').html("<option disabled value='0' class=''>Select an Objective</option>" + resp);
            $('#txtobjectiveEx').val(0).trigger('change');
            StopLoading();

        }
        function getYear(id) {
            var yr = id;
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/BudgetYearServlet",
                data: {option: "<%= Utility.OPTION_SELECT%>", id: id},
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
                                attrValue = " " + attrValue;
                                if (attrName === "1") {
                                    yr = attrValue;
                                }
                            }
                        }
                    }
                },
                error: function () {
                    toastr["error"]("Budget Year Selection Failed!", "No record selected!");
                }
            });
            return yr;
        }
        function getPolicies() {
            ShowLoading();
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/PolicyServlet",
                data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
                dataType: "json",
                cache: false,
                async: false,
                success: popPolicies,
                error: function () {
                    StopLoading();
                    toastr["error"]("Policy Selection Failed!", "No record selected!");
                }
            });
        }
        function popPolicies(data) {
            var policycode = [];
             var value = {};
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
                        if (attrName === "4") {
                            //resp += "<option value='" + attrValue + "'>";
                             value.code = attrValue;
                             policycode.push(x);
                        }
                        if (attrName === "7") {
                            var x = attrValue;

                        }
                        if (attrName === "1") {
                             value.name = attrValue;
                            //resp += attrValue + "</option>";
                        }
                    }
                    resp += "<option value='"+ value.code + "'>" + value.name + "</option>";
                }
                //resp += "</select>";
                $('#txtsectorpolicy').html(resp);//("<option value='0'>Select Policy</option>" + resp);
                $('#txtsectorpolicyEx').html(resp);//.html("<option value='0'>Select Policy</option>" + resp);
                StopLoading();
            }
        };
        function checkkk(){
            j1 = parseInt($('input:radio[name=criteria1]:checked').val());
            j2 = parseInt($('input:radio[name=criteria2]:checked').val());
            j3 = parseInt($('input:radio[name=criteria3]:checked').val());
            j4 = parseInt($('input:radio[name=criteria4]:checked').val());
            j5 = parseInt($('input:radio[name=criteria5]:checked').val());
            var out = (j1 + j2 + j3 + j4);
            if (out > 0)
                out = out / 4;
            score = out;
            $('#txtscore').html(out);
            $('#result').html(out);

        }
        function getObjective1(idx) {
            if (idx === null)
                return;
            ShowLoading();
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
                data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: idx, entity: "ProgrammeSegmentHeader3"},
                dataType: "json",
                cache: false,
                async: false, success: function (data) {
                    if (data !== null || data !== "null") {
                        data = data[0];
                        _obj = data;
                    }
                },
                error: function () {
                    StopLoading();
                    toastr["error"]("Programme Objective Select Failed!", "No record selected!");
                }
            });

            StopLoading();
            //retrun _obj;
        }
        function setPolicy(obj,newold){
        $("#txtobjective > option").remove();
        $("#txtobjectiveEx > option").remove();
        id=obj.value;
        alert(id);
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
                data: {option: "<%= Utility.OPTION_SELECT_POLICIES%>", policy: id, budgetyear:_budgetyear, NewOld: newold, entity: "ProgrammeSegmentHeader3"},
                dataType: "json",
                cache: false,
                async: false,
                success: function(data){ popObjectives(data, newold); },  
                error: function () {
                    StopLoading();
                    toastr["error"]("Policy Selection Failed!", "No record selected!");
                }
            });
        }
        function popObjectives(data,newold ) {
            var objectivecode = [];
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
                            var x = attrValue;
                            objectivecode.push(x);
                        }
                        if (attrName === "1") {
                            resp += attrValue + "</option>";
                        }
                    }
                    objectiveCode=objectivecode;
                }
                if(newold=='1')
                    $('#txtobjective').html("<option value='0'>Select Objective</option>" + resp);
                else
                    $('#txtobjectiveEx').html("<option value='0'>Select Objective</option>" + resp);

                //$('#txtobjective').val(0).trigger('change');
                StopLoading();
            }
        };                                                                                                                                      
        function setObjective(obx) {
            var k = $('#txtsectorpolicy').find(":selected").val();
            if (k < 1)
            {
                alert('Please Select a Policy first');
                return;
            }
            //Get the Sector Policycode
            var s = getPolicy(k);
            var idx = obx.value;
            if (idx > 0)
            {

                $('#tabContent5').tab('show');
                getObjective1(idx);

                $('#ProjectCode').html(PolicyCode__ + _obj[2]);
                $('#txtmda').val(_obj[6]).trigger('change');
                $('#txtdepartment').val(_obj[7]).trigger('change');
                $('#txtsectorgoal').val(_obj[8]).trigger('change');
                $('#txtsectorprogramme').val(_obj[13]).trigger('change');
                $('#txtprojectname').val(_obj[3]).trigger('change');
                if (_obj[11] != -1)
                    $('#txtscore').html(_obj[11]);
                else
                    $('#txtscore').html('-');
                if (_obj[12] != -1)
                    $('#txtrank').html(_obj[11]);
                else
                    $('#txtrank').html('-');
                //Get Scores           
                getCriteria(PolicyCode__ + _obj[2]);
                //Look up this Obj in scoringtable
            }
        }
        function setObjectiveEx(obx) {
            var k = $('#txtsectorpolicyEx').find(":selected").val();
            if (k < 1)
            {
                alert('Please Select a Policy first');
                return;
            }
            //Get the Sector Policycode
            var s = getPolicy(k);
            var idx = obx.value;
            if (idx > 0)
            {
                getObjective1(idx);

                $('#ProjectCodeEx').html(PolicyCode__ + _obj[2]);
                $('#txtmdaEx').val(_obj[6]).trigger('change');
                $('#txtdepartmentEx').val(_obj[7]).trigger('change');
                $('#txtsectorgoalEx').val(_obj[8]).trigger('change');
                $('#txtsectorprogrammeEx').val(_obj[13]).trigger('change');
                $('#txtprojectnameEx').val(_obj[3]).trigger('change');
                if (_obj[11] != -1)
                    $('#txtscoreEx').html(_obj[11]);
                else
                    $('#txtscoreEx').html('-');
                if (_obj[12] != -1)
                    $('#txtrankEx').html(_obj[11]);
                else
                    $('#txtrankEx').html('-');
                //Get Scores           
                getCriteria(PolicyCode__ + _obj[2]);
                //Look up this Obj in scoringtable
            }
        }
        function getPolicy(idx) {
            // ShowLoading();
            if(idx==null)
                return;

            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/PolicyServlet",
                data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: idx},
                dataType: "json",
                cache: false,
                async: false,
                success: function (data) {
                    if (data !== null || data !== "null") {                                                                                                                                                                        
                        data = data[0];

                        var o = data;                                                                                    
                        PolicyCode__ = o[4];
                        $('#ProjectCode').html(PolicyCode__);
                    }
                },
                error: function () {
                    StopLoading();
                    toastr["error"]("Policy Selection Failed!", "No record selected!");
                }
            });
        }
        function RankAll(crits) {
            var srt = crits.slice().sort(function (a, b) {
                return b - a;
            });
            var ranked = srt.slice().map(function (x) {
                return sorted.indexOf(x) + 1;
            });
            return ranked;
        }
        function scorethis() {
            j1 = parseInt($('input:radio[name=criteria1]:checked').val());
            j2 = parseInt($('input:radio[name=criteria2]:checked').val());
            j3 = parseInt($('input:radio[name=criteria3]:checked').val());
            j4 = parseInt($('input:radio[name=criteria4]:checked').val());
            var out = (j1 + j2 + j3 + j4);
            if (out > 0)
                out = out / 4;
            $('#txtscore').html(out);
            $('#result').html(out);
            $('#_score').html(out);


        }
        function LoadScoring() {
            //Determine if previously scored
            var _score = _obj[11];
            var _rank = _obj[12];
            //alert (_score);alert (_rank);
            if (_score == -1)//&& _rank==-1)
            {// $('#tabContent4').trigger('click');//New project
                $('#projectstatus').html("New Project");
                $('input[name="tabContent4"]').attr('active', true);// .attr('active', true);
                $('input[name="tabContent"]').removeAttr('active');//.attr('active', false);
            } else
            { //$('#tabContent5').trigger('click');//Exisiting Project
                $('#projectstatus').html("Existing Project");
                $('input[name="tabContent4"]').removeAttr('active');
                $('input[name="tabContent"]').attr('active', true);
            }
        }
        function loadscore(idx) {
            // ShowLoading();
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/PolicyServlet",
                data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: idx},
                dataType: "json",
                cache: false,
                async: false,
                success: function (data) {
                    if (data !== null || data !== "null") {
                        data = data[0];
                        var o = data;
                        PolicyCode__ = o[4];
                        $('#ProjectCode').html(PolicyCode__);
                    }
                },
                error: function () {
                    StopLoading();
                    toastr["error"]("Policy Selection Failed!", "No record selected!");
                }
            });
        }
        function SaveScore(stat) {
            checkkk();//Update scores
            var project_code = $('#ProjectCode').html();
            var project_codeEx = $('#ProjectCodeEx').html();
            var project_year = _budgetyear;
            if (stat === 4) {
                saveCriteria(project_codeEx, project_year, j1, j2, j3, j4, -1);
                $('#projectstatus').html("Existing Project");
            } else {
                saveCriteria(project_code, project_year, -1, -1, -1, -1, j5);
                $('#projectstatus').html("New Project");
            }
            $('input[name="criteria1"]').attr('checked', false);
            $('input[name="criteria2"]').attr('checked', false);
            $('input[name="criteria3"]').attr('checked', false);
            $('input[name="criteria4"]').attr('checked', false);
            $('input[name="criteria5"]').attr('checked', false);
        }
        function saveCriteria(project_code, project_year, j1, j2, j3, j4, j5) {
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/ScoringServlet",
                data: {option: "<%= Utility.OPTION_INSERT%>", project_code: project_code, project_year: project_year,  budgetyear:_budgetyear,criteria_id1: j1, criteria_id2: j2, criteria_id3: j3, criteria_id4: j4, criteria_id5: j5},
                dataType: "json",
                cache: false,
                async: false,
                success: function (data) {
                    if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {
                        toastr["success"]("Programme Segment Insert Successful!", "New record successfully inserted!");
                    } else {
                        toastr["error"]("Programme Segment with that name already exist", "Duplicate Entry!");
                    }
                },
                error: function (a, b, c) {
                    StopLoading();
                    //console.log(a + b + c);
                    toastr["error"]("Failed!", "No record inserted!");
                }
            });
        }
        function getCriteria(project_code) {
            //alert(project_code);
            $.ajax({
                type: "GET",
                url: "<%= Utility.SITE_URL%>/ScoringServlet",
                data: {option: "<%= Utility.OPTION_SELECT_ALL%>", project_code: project_code},
                dataType: "json",
                cache: false,
                async: false,
                success: function (data) {
                    if (data !== null || data !== "null") {
                        if (data.length == 1)
                        {
                            var kk = data[0];
                            $("input[name=criteria5]").val([kk[4]]);
                            $('input[name="criteria1"]').attr('checked', false);
                            $('input[name="criteria2"]').attr('checked', false);
                            $('input[name="criteria3"]').attr('checked', false);
                            $('input[name="criteria4"]').attr('checked', false);                                                                                        
                        } else if (data.length == 0)
                        {
                            $('input[name="criteria1"]').attr('checked', false);
                            $('input[name="criteria2"]').attr('checked', false);
                            $('input[name="criteria3"]').attr('checked', false);
                            $('input[name="criteria4"]').attr('checked', false);
                            $('input[name="criteria5"]').attr('checked', false);
                        } else
                        {
                            $("input[name=criteria5]").val([data[0][4]]);
                            $("input[name=criteria1]").val([data[1][4]]);
                            $("input[name=criteria2]").val([data[2][4]]);
                            $("input[name=criteria3]").val([data[3][4]]);
                            $("input[name=criteria4]").val([data[4][4]]);
                        }

                    }
                },
                error: function (a, b, c) {
                    StopLoading();
                    //console.log("ERROR  " + a + b + c);
                    toastr["error"]("Failed!", "No record Selected!");
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
                        if (attrName == "3") {
                            if (attrValue == 1) {
                                _budgetyear = tmp;
                            }
                        }
                    }
                }

            }
        }
        ;

    </script>

</body>
</html>
