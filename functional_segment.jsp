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
                                    <h2 class="title">Functional Segments</h2>
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
                                        <li class="active">Functional Segments</li>
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
                                                    <h5>Add Segment</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <div id="create-area">
                                                    <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">

                                                        <div class="form-group has-feedback">
                                                            <label>Functional Segment Header 1</label>
                                                            <div id="addSegmentH1"></div>
                                                        </div>

                                                        <div class="form-group has-feedback">
                                                            <label>Functional Segment Header 2</label>
                                                            <div id="addSegmentH2"></div>

                                                        </div>


                                                        <div class="form-group has-feedback">
                                                            <label>Name</label>
                                                            <input type="text" class="form-control" id="name_addSegment" name="name" placeholder="name">
                                                        </div>

                                                        <div class="form-group has-feedback">
                                                            <label>Code</label>
                                                            <input type="text" class="form-control" id="code_addSegment" name="name" placeholder="code">
                                                        </div>
                                                        <button onclick="saveFunctionalSegment()" type="button" class="btn btn-success btn-labeled pull-left">Save Functional Segment<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                        <button onclick="ReturnToList()" type="button" class="btn btn-danger btn-labeled pull-right">Cancel<span class="btn-label btn-label-right"><i class="fa fa-close"></i></span></button>
                                                    </form>
                                                </div>

                                                <!-- Nav tabs -->
                                                <div id="list-area">
                                                    <div class="col-md-12">
                                                        <form class="col-md-12" style="margin-top: 30px; margin-bottom: 30px; ">

                                                            <!--                                                        <div class="form-group has-feedback">
                                                                                                                        <label>Functional Segment</label>
                                                                                                                        <div id="select_functionalSegment1"></div>
                                                                                                                        
                                                                                                                    </div>-->
                                                            <div class="form-group H1SelContainer col-xs-12" style="margin : 10px 0;">
                                                                <label for="selFSHeader1" style="margin : 0 12px;">Functional Segment Header 1</label>
                                                                <div id="selFSHeader1" style="margin : 0 12px" class="col-xs-5"></div> 
                                                                <div class="col-xs-6">
                                                                    <button onclick="manageFunctionalSegmentHeader1()" type="button" style="margin : 0 12px" class="btn btn-info btn-labeled ">
                                                                        Manage Functional Segment Header 1<span class="btn-label btn-label-right"><i class="fa fa-pencil"></i></span></button>
                                                                </div>
                                                            </div>

                                                            <div class="form-group hidden H2SelContainer col-xs-12" style="margin : 10px 0;">
                                                                <label for="selFSHeader2" style="margin : 0 12px;">Functional Segment Header 2</label>
                                                                <div id="selFSHeader2" style="margin : 0 12px" class="col-xs-5"></div> 
                                                                <div class="col-xs-6">
                                                                    <button onclick="manageFunctionalSegmentHeader2()" type="button" style="margin : 0 12px" class="btn btn-info btn-labeled ">
                                                                        Manage Functional Segment Header 2<span class="btn-label btn-label-right"><i class="fa fa-pencil"></i></span></button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class="col-md-12 right-side">
                                                        <a class="btn btn-info toggle-code-handle pull-right" onclick="addSegment()" role="button"><i class="fa fa-plus"> </i>New Functional Segment</a>
                                                    </div>
                                                    <br style="clear: both;" />
                                                    <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                        <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true"> Functional Segments</a></li>
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
                                                    <h5>Edit Functional Segment</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">


                                                    <div class="form-group has-feedback">
                                                        <label>Name</label>
                                                        <input type="text" class="form-control" id="name2" name="name" placeholder="name">
                                                        <input type="hidden" id="updateid" name="id"/>
                                                    </div>

                                                    <div class="form-group has-feedback">
                                                        <label>Name</label>
                                                        <input type="text" class="form-control" id="code2" name="code" placeholder="segment code">

                                                    </div>
                                                    <button onclick="FinishEdit()" type="button" class="btn btn-success btn-labeled pull-left">Save Functional Segment<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                    &nbsp; <button onclick="cancelEdit()" type="button" class="btn btn-danger btn-labeled pull-right">Cancel Edit<span class="btn-label btn-label-right"><i class="fa fa-close"></i></span></button>
                                                </form>
                                                <br style="clear: both;" />
                                            </div>
                                        </div>

                                        <div class="panel is-hidden" id="addH1Section">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h4>Add Header</h4>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">

                                                <div class="col-xs-12">
                                                    <form class="form-inline addHeader1Form">
                                                        <div class="form-group col-xs-12" >  
                                                            <input class="col-md-4" type='text' id='name_addh1' placeholder='Name' style="display:inline-block; margin-right : 10px; "/>
                                                            <input class="col-md-4" type='text' id='code_addh1' placeholder='Code'style="display:inline-block; margin-right : 10px;"/>
                                                        </div>
                                                    </form>

                                                    <form class="form-inline hidden editHeader1Form">
                                                        <div class="form-group col-xs-12" >  
                                                            <input class="col-md-4" type='text' id='name_edith1' placeholder='Name' style="display:inline-block; margin-right : 10px;"/>
                                                            <input class="col-md-4" type='text' id='code_edith1' placeholder='Code' style="display:inline-block; margin-right : 10px;"/>
                                                        </div>
                                                    </form>

                                                    <div class="col-xs-12">
                                                        <button onclick="FinishHeader1Edit()" type="button" id='editHeader1Btn' class="btn hidden btn-success btn-labeled pull-left">Save Header <span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
                                                        <button onclick="FinishAddHeader1()" type="button" id='addHeader1Btn' class="btn btn-success btn-labeled pull-left">Add Header <span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
                                                        <button type="button" class="btn btn-warning pull-right hidden" data-dismiss="modal"  id="cancelEditH1Btn" onclick="CancelHeader1Edit();">Clear Edit</button>

                                                    </div>
                                                </div>
                                                <br style="clear: both;" />
                                                <h4>Manage Headers</h4>
                                                <div>
                                                    <div id="FSHeader1_table"></div>
                                                </div>
                                                <div>

                                                    <button type="button" class="btn btn-warning pull-right" data-dismiss="modal" data-izimodal-close="" id="cancelManageH1Btn" onclick="CancelManageHeader1();">Cancel</button>
                                                </div>
                                            </div>

                                        </div>
                                        <!--/manage header 1 -->
                                        <div class="panel is-hidden" id="addH2Section">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h4>Add Header 2</h4>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">

                                                <div class="col-xs-12">
                                                    <form class="form-inline addHeader2Form">
                                                        <div class="form-group col-xs-12" >  
                                                            <input class="col-sm-4" type='text' id='name_addh2' placeholder='Name' style="display:inline-block; margin-right : 10px; "/>
                                                            <input class="col-sm-4" type='text' id='code_addh2' placeholder='Code'style="display:inline-block; margin-right : 10px;"/>
                                                        </div>
                                                    </form>

                                                    <form class="form-inline hidden editHeader2Form">
                                                        <div class="form-group col-xs-12" >  
                                                            <input class="col-sm-4" type='text' id='name_edith2' placeholder='Name' style="display:inline-block; margin-right : 10px;"/>
                                                            <input class="col-sm-4" type='text' id='code_edith2' placeholder='Code' style="display:inline-block; margin-right : 10px;"/>
                                                        </div>
                                                    </form>

                                                    <div class="col-xs-12">
                                                        <button onclick="FinishHeader2Edit()" type="button" id='editHeader2Btn' class="btn hidden btn-success btn-labeled pull-left">Save Header <span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
                                                        <button onclick="FinishAddHeader2()" type="button" id='addHeader2Btn' class="btn btn-success btn-labeled pull-left">Add Header <span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
                                                        <button type="button" class="btn btn-warning pull-right hidden" data-dismiss="modal"  id="cancelEditH2Btn" onclick="CancelHeader2Edit();">Clear Edit</button>

                                                    </div>
                                                </div>
                                                <br style="clear: both;" />
                                                <h4>Manage Headers 2</h4>
                                                <div>
                                                    <div id="FSHeader2_table"></div>
                                                </div>
                                                <div>

                                                    <button type="button" class="btn btn-warning pull-right" data-dismiss="modal" data-izimodal-close="" id="cancelManageH2Btn" onclick="CancelManageHeader2();">Cancel</button>
                                                </div>
                                            </div>

                                        </div>
                                        <!--/manage header 2 -->

                                    </div> 
                                    <br style="clear: both;" />
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

<!--  Modal -->


<!-- Modal -->
<div id="deleteModal">
    <div class="">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-izimodal-close="">&times;</button>
                <h4 class="modal-title">Delete Segment</h4>
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




<!-- /.Modal -->

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
<script id="erasable" src="js/bdgaosg-6162726163616461627261/636f64656d-00051.js"></script>        
</body>
</html>
