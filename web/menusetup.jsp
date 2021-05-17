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
                                    <h2 class="title">Menu Code Setup</h2>
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
                                        <li class="active">Menu Code Setup</li>
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
                                                    <h5>Add Menu Code</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <div id="create-area">
                                                    <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">
                                                        
                                                        <div class="form-group has-feedback">
                                                            <label>Menu Category&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                            <select class='js-states form-control' id='menucategory1' disabled></select>
                                                        </div>
                                                        <div class="form-group has-feedback">
                                                            <label>Menu Name</label>
                                                            <input type="text" class="form-control" id="name1" name="name1" placeholder="name">
                                                        </div>
                                                        <div class="form-group has-feedback">
                                                            <label>Menu URL</label>
                                                            <input type="text" class="form-control" id="menu_url1" name="menu_url1" placeholder="name">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for='selRoles_a'>Roles</label>
                                                            <select class='js-states form-control' id='selRoles_a' multiple='multiple'></select>
                                                        </div>
                                                        <hr />
                                                        <button onclick="saveMenuCode();" type="button" class="btn btn-success btn-labeled pull-right">Create Menu Code<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                        <button type="button" onclick="ReturnToList()" class="btn btn-danger btn-labeled" style="float: left;">
                                                            Cancel Menu Code Add 
                                                            <span class="btn-label btn-label-right"><i class="fa fa-remove"></i></span>
                                                        </button>
                                                    </form>
                                                </div>

                                                <!-- Nav tabs -->
                                                <div id="list-area">
                                                    <div class="col-lg-6">
                                                        <div class="input-group col-lg-6 col-sm-10">
                                                            <label for="menucategory0">Menu Category&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                            <select class='js-states form-control' id='menucategory0' onchange='getMenuCodes(this.id);'></select>
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-6 right-side">
                                                        <a class="btn btn-info toggle-code-handle pull-right" onclick="CreateMenuCode();" role="button"><i class="fa fa-plus"> </i>New Menu Code</a>
                                                    </div>
                                                    <br style="clear: both;" />
                                                    <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                        <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">Menu Codes</a></li>
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
                                                    <h5>Edit Menu Code</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">
                                                    <input type="hidden" id="txtId_e" >
                                                    <div class="form-group has-feedback">
                                                        <label>Menu Category&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                        <select class='js-states form-control' id='menucategory2' disabled></select>
                                                    </div>
                                                    <div class="form-group has-feedback">
                                                        <label>Menu Name</label>
                                                        <input type="text" class="form-control" id="name2" name="name2" placeholder="name">
                                                    </div>
                                                    <div class="form-group has-feedback">
                                                        <label>Menu URL</label>
                                                        <input type="text" class="form-control" id="menu_url2" name="menu_url2" placmenu_url2eholder="name">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for='selRoles_e'>Roles</label>
                                                        <select class='js-states form-control' id='selRoles_e' multiple='multiple'></select>
                                                    </div>
                                                    <hr />
                                                    <button onclick="FinishEdit()" type="button" class="btn btn-success btn-labeled pull-right">Update Menu Code<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                    <button onclick="CancelEdit()" type="button" class="btn btn-danger btn-labeled pull-left">Cancel Menu Code Edit<span class="btn-label btn-label-right"><i class="fa fa-remove"></i></span></button>
                                                </form>
                                                <br style="clear: both;" />
                                            </div>
                                        </div>
                                        
                                        <div id="deleteModal" role="dialog" style="display: none">
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

                                    </div>
                                    <!-- /.col-md-12 -->

                                </div>
                                <!-- /.row -->

                            </div>
                            <!-- /.container-fluid -->
                        </section>
                    </div>
                    <input type="hidden" id="valb4edit" />
                    <input type="hidden" id="idb4edit" />
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
                getRoles();
                getCategorys();
                $("#menucategory0, #menucategory1, #menucategory2, #mda_type1, #mda_type2, #selRoles_a, #selRoles_e").select2();
            });
            
            
            function getRoles(){
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/RolesServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL %>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: roleReturnValues,
                    error: function(jqXHR, textStatus, errorThrown){ toastr["error"]("No record selected!", "Roles Selection Failed!!!");}
                });
            }
            function roleReturnValues(data){
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
            }
            
            function getCategorys(){
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/MenuCategoryServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL %>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: menuCategoryReturnValues,
                    error: function(){ toastr["error"]("No record selected!", "Menu Category Selection Failed!!!");}
                });
            }
            function menuCategoryReturnValues(data){
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
                            if(attrName === "2"){
                                resp += attrValue+"</option>";
                            }
                        }
                    }
                    //resp += "</select>";
                    $('#menucategory0').html("<option value='0'></option>"+resp);
                    $('#menucategory1').html("<option value='0'></option>"+resp);
                    $('#menucategory2').html("<option value='0'></option>"+resp);
                    //console.log(document.getElementById("selRole1").innerHTML);
                }
            }
            
            function setMenuCode(id){
                if(id==="menucategory1"){
                    document.getElementById("name1").value="";
                    document.getElementById("menu_url1").value="";
                    $('#selRoles_a').val('').trigger('change');
                    //$('#selRoles_a').html("");
                    //document.getElementById("selRoles_a").value="";
                }
                if(id==="menucategory2"){
                    document.getElementById("name2").value="";
                    document.getElementById("menu_url2").value="";
                    //$('#selRoles_e').html("");
                    //document.getElementById("selRoles_e").value="";
                }
            }
            
            
            function getMenuCodes(arg){
                //var id = document.getElementById(arg).value;
                id = $("#"+arg).val();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/SystemMenuServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_BY_ID %>", menu_category_id: id},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: menuCodeReturnValues,
                    error: function(){ toastr["error"]("Menu Code Selection Failed!", "No record selected!");}
                });
            }
            function menuCodeReturnValues(data){
                 if(data.length>0){
                    var resp ="<table id='main-table' class='table table-clean table-striped' border='0'><thead><tr><td><b>Menu&nbsp;Ranks</b></td><td><b>Menu&nbsp;Names</b></td><td><b>Menu&nbsp;URLs</b></td><td><b>Menu&nbsp;Roles</b></td><td></td></tr></thead><tbody>";
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
                                //resp += "<tr><td class='line-height-30 w-10'><b><input type='text' onblur='updateRank("+rec_id+",this.value);' value='"+attrValue+"' size='3' /></b></td>";
                                resp += "<tr><td style='text-align: center' class='line-height-30 w-10'><b><a onclick='storeVals(this.id,this.innerHTML);' class='rowDataSd editable-control envelope-fig figx' href='#' id='" + rec_id + "' data-name='" + rec_id + "' data-type='text' data-pk='1' data-title='Menu Rank'>"+attrValue+"</a>&nbsp;</b></td>";
                            }
                            if(attrName === "2"){
                                resp += "<td class='line-height-70 w-20'><b>"+attrValue+"</b></td>";
                            }
                            if(attrName === "3"){
                                resp += "<td class='line-height-70 w-20'><b>"+attrValue+"</b></td>";
                            }
                            if(attrName === "4"){
                                attrValue = attrValue.replace(/ /g, '&nbsp;').replace(/,&nbsp;/g, ', ');
                                resp += "<td class='line-height-30 w-30' style='vertical-align: middle;'><b>"+attrValue+"</b></td>";
                                resp += "<td class='line-height-30 w-20'><button onclick='EditItem("+rec_id+");' type='button' class='btn btn-success btn-labeled pull-left'>Edit<span class='btn-label btn-label-right'><i class='fa fa-edit'></i></span></button>";
                                resp += "<button onclick='deleteItem("+rec_id+");' type='button' class='btn btn-danger btn-labeled pull-right'>Delete<span class='btn-label btn-label-right'><i class='fa fa-remove'></i></span></button></td></tr>";
                            }
                        }
                    }
                    resp += "</tbody></table>";
                    $('#active').html(resp);
                    //console.log(resp);
                    //$('#main-table').dataTable({
                    //    "pagingType": "full_numbers",
                    //    "lengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
                    //});
                    initializeEditableControls();
                    
                    
                }else{
                     $('#active').html("");
                }
                
            }
            function storeVals(id,val){
                $('#valb4edit').val(val);
                $('#idb4edit').val(id);
            }
            function updateRank(id, rank){
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/SystemMenuServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE_USER_MENUS %>", id: id, rank: rank},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(data){
                        if (data.indexOf("<%= Utility.ActionResponse.UPDATED.toString() %>")!== -1) {
                            toastr["success"]("Menu Rank Update Successful!", "Record Successfully Updated!!!");
                            //getMenuCodes('menucategory0');
                        }else{
                            toastr["error"]("Record not exist!", "No Record Updated!!!");
                        }
                    },
                    error: function(){ toastr["error"]("Menu Rank Update Failed!", "No Record Updated!");}
                });
            }
            
            function saveMenuCode(){
                var menu_category_id = $("#menucategory1").val();
                var name = document.getElementById("name1").value;
                var menu_url = document.getElementById("menu_url1").value;
                var selRoles_a = document.getElementById("selRoles_a");
                var selRoles = "";
                for(var i=0;i<selRoles_a.length;i++){
                    if(selRoles_a[i].selected === true){
                        selRoles += selRoles_a[i].value + ",";
                    }
                }
                var error = "";
                if(menu_category_id==="0"){
                    error += "Category must not be blank<br>";
                }
                if(name===""){
                    error += "Menu Name must not be blank<br>";
                }
                if(menu_url===""){
                    error += "Menu URL must not be blank<br>";
                }
                if(error.length>0){
                    toastr["error"](error, "Please correct the following:");
                    return true;
                }
                //alert("menucategory: "+menucategory);
                //alert("name "+name);
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/SystemMenuServlet",
                    data: {option: "<%= Utility.OPTION_INSERT %>", menu_category_id: menu_category_id, name: name, menu_url: menu_url, roleId: selRoles},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(data){
                        if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString() %>")!== -1) {
                            toastr["success"]("MenuCode Insert Successful!", "New record successfully inserted!");
                            getMenuCodes('menucategory0');               
                            ReturnToList();
                        }else{
                            toastr["error"]("Record already exists!", "No Record Inserted!!!");
                        }
                    },
                    error: function(){ toastr["error"]("Menu Code Insert Failed!", "No record inserted!");}
                });
            }
            
            EditItem = function (arg) {
                var id = document.getElementById("menucategory0").value;
                $('#menucategory2').val(id).trigger('change'); 
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/SystemMenuServlet",
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
                                        //$('#menucategory2').val(attrValue).trigger('change');  
                                    }
                                    if(attrName === "2"){
                                        document.getElementById("name2").value = attrValue;
                                    }
                                    if(attrName === "3"){
                                        document.getElementById("menu_url2").value = attrValue;
                                    }
                                    if(attrName === "4"){
                                        var obj = eval('[' + attrValue + ']');
                                        $('#selRoles_e').val(obj).trigger('change');
                                    }
                                }
                                break;
                            }
                            toastr["success"]("Menu Code record successfully fetched!", "Menu Code Selection Successfull!!!");
                            $('#list-area').hide();
                            $('#EditSection').fadeIn();
                            document.getElementById("menucategory2").focus();
                        }
                    },
                    error: function(){ 
                        toastr["error"]("Record with id " + arg + " is not found!", "Menu Code Selection Failed!!!");
                    }
                });                
            };
            
              
            FinishEdit = function () {
                var id = document.getElementById("txtId_e").value;
                var menu_category_id = $("#menucategory2" ).val();
                var name = document.getElementById("name2").value;
                var menu_url = document.getElementById("menu_url2").value;
                var selRoles_e = document.getElementById("selRoles_e");
                var selRoles = "";
                for(var i=0;i<selRoles_e.length;i++){
                    if(selRoles_e[i].selected === true){
                        selRoles += selRoles_e[i].value + ",";
                    }
                }
                var error = "";
                if(menu_category_id===""){
                    error += "Category must not be blank<br>";
                }
                if(name===""){
                    error += "Menu Name must not be blank<br>";
                }
                if(menu_url===""){
                    error += "Menu URL must not be blank<br>";
                }
                if(error.length>0){
                    toastr["error"](error, "Please correct the following:");
                    return true;
                }
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/SystemMenuServlet",
                    data: {option: "<%= Utility.OPTION_UPDATE %>", id: id, menu_category_id: menu_category_id, name: name, menu_url: menu_url, roleId: selRoles},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(data){
                        $('#deleteModal').iziModal('close');
                        if (data.indexOf("<%= Utility.ActionResponse.UPDATED.toString() %>")!== -1) {
                            toastr["success"]("MenuCode Update Successful!", "Record Updated!!!");
                            getMenuCodes('menucategory0');               
                        }else{
                            toastr["error"]("Menu URL Code ["+menu_url+"] already exists!", "Menu Category Update Failed!!!");
                        }
                    },
                    error: function(){ 
                        $('#deleteModal').iziModal('close');
                        toastr["error"]("Record with id " + arg + " is not found!", "Menu Code Delete Failed!!!");
                    }
                });
                $('#EditSection').hide();
                $('#list-area').fadeIn();
            };
            
            CancelEdit = function () {
                $('#EditSection').hide();
                $('#list-area').fadeIn();
            };
            
            var CreateMenuCode = function () {
                setMenuCode('menucategory1');
                var id = $( "#menucategory0 option:selected" ).text();
                if(id===""){
                    toastr["error"]("Blank Menu Category is not allowed.", "Invalid Menu Category!!!");
                    return true;
                }else{
                    id = $("#menucategory0").val();
                    $('#menucategory1').val(id).trigger('change'); 
                    $('#list-area').hide();
                    $('#create-area').fadeIn();
                    $('#MainSection .panel-title').fadeIn();
                }
            };

            var ReturnToList = function () {
                $('#create-area').hide();
                $('#MainSection .panel-title').hide();
                $('#list-area').fadeIn();
            };
            
            deleteItem = function (arg) {
                window.deleteId = arg;
                $('#deleteModal').iziModal('open');
            };
            
            finishDeleteItem = function () {
                var id = window.deleteId;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/SystemMenuServlet",
                    data: {option: "<%= Utility.OPTION_DELETE %>", id: id},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function(data){
                        $('#deleteModal').iziModal('close');
                        if (data.indexOf("<%= Utility.ActionResponse.DELETED.toString() %>")!== -1) {
                            toastr["success"]("MenuCode Delete Successful!", "Record Deleted!!!");
                            getMenuCodes('menucategory0');               
                            ReturnToList();
                        }else{
                            toastr["error"]("MenuCode Delete Failed!", "No Record Deleted!!!");
                        }
                    },
                    error: function(){ 
                        $('#deleteModal').iziModal('close');
                        toastr["error"]("Record with id " + arg + " is not found!", "Menu Code Delete Failed!!!");
                    }
                });   
            };
            
            var removeWindowId = function () {
                window.deleteId = 0;
            };
              
            /**
             * Method for initialising editable controls
             * 
             * @returns
             */
            function initializeEditableControls() {
                $('.editable-control.envelope-fig').each(function () {
                    var id = $(this).attr('id');
                    var title = $(this).attr('data-title');
                    var name = $(this).attr('data-name');
                    var pk = $(this).attr('data-pk');
                    var type = $(this).attr('data-type');
                    //var classs = $(this).attr('class');
                    if (type === 'text' || type === 'number') {
                        $('#' + id).editable({
                            url: '/post',
                            type: 'number',
                            pk: pk,
                            name: name,
                            title: title,
                            display: function (value) {
                                if (!value || isNaN(value)) {
                                    //$(this).html('0.00');
                                    return;
                                } else {
                                    var grep = value.replace(/,/g, "");
                                    if (isNaN(grep)) {
                                        alert("The entered figure (" + value + ") is not a valid value");
                                        value = null;
                                        //document.getElementById("submit-final").disabled = true;
                                        return;
                                    } else {
                                        //document.getElementById("submit-final").disabled = false;
                                    }
                                }
                                var html = value.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                                //if (html.indexOf('.') === -1) {
                                //    html += '.00';
                                //}
                                $(this).html(html);
                                if($("#idb4edit").val() !== null && $("#idb4edit").val() !== ""){
                                    updateRank($("#idb4edit").val(),$(this).html());
                                    $("#idb4edit").val("");
                                }
                            }
                        });
                    }
                });
            }
              
        </script>

    </body>
</html>
