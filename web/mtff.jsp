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
    <link href="css/pace/pace-minimal.css" rel="stylesheet" />
    
    <!-- ========== PAGE STYLES ========== -->
    <link rel="stylesheet" href="css/toastr/toastr.min.css" media="screen">
    <link rel="stylesheet" href="css/icheck/skins/line/blue.css">
    <link rel="stylesheet" href="css/icheck/skins/line/red.css">
    <link rel="stylesheet" href="css/icheck/skins/line/green.css">
    <link rel="stylesheet" href="css/x-editable/css/bootstrap-editable.css">
    <link rel="stylesheet" href="css/select2/select2.min.css">
    <link rel="stylesheet" href="css/ladda/ladda-themeless.min.css" media="screen">
    <link rel="stylesheet" href="css/iziModal/iziModal.min.css" media="screen">
    <link rel="stylesheet" href="css/sweet-alert/sweetalert.css" media="screen">
    <!-- ========== THEME CSS ========== -->
    <link rel="stylesheet" href="css/main.css" media="screen">
    <link rel="stylesheet" href="css/normalize.css" />
    <link rel="stylesheet" href="css/ion.rangeSlider.css" />
    <link rel="stylesheet" href="css/ion.rangeSlider.skinFlat.css" />
    <link rel="stylesheet" href="css/icheck/skins/square/red.css">
    <link rel="stylesheet" href="css/jquery.loading.css" />
    <link rel="stylesheet" href="js/amcharts/plugins/export/export.css" type="text/css" media="all" />
    <!-- ========== MODERNIZR ========== -->
    <script src="js/modernizr/modernizr.min.js"></script>
    
    <!-- For Loading -->
    <link href="css/jquery.loading.css" rel="stylesheet" />
    <style>
        .pivot_year {
            margin-left: -2% !important;
            color: rgb(0, 0, 0) !important;
            font-size: 120% !important;
            font-weight: bold !important;
            top: 20px !important;
        }

        .gray_blanket {
            background: #f3f3f3;
            padding: 30px;
            margin-top: -25px;
            margin-left: -30px;
            margin-right: -30px;
        }

            .gray_blanket .popover {
                width: 500px !important;
                max-width: 500px !important;
                background-color: rgba(250,238,223,0.9) !important;
                font-size: 90%;
            }

                .gray_blanket .popover .table-striped > tbody > tr:nth-of-type(odd) {
                    background-color: rgba(241,226,218,0.5) !important;
                }
                .gray_blanket .popover .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
                    border: 0px !important;
                }
                .gray_blanket .popover tr td input {
                    max-width: 75px !important;
                    margin-bottom: 0px !important;
                    height: 30px;
                }
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
                                    <a href="#"><i class="fa fa-file-text"></i> <span>Budget Core</span> <i class="fa fa-angle-right arrow"></i></a>
                                    <ul class="child-nav">
                                    	<li><a onclick="gotoLink('/dashboard00004');"><i class="fa fa-thumb-tack"></i> <span>Budget Timetable</span></a></li>
                                        <li><a onclick="gotoLink('/dashboard00021');"><i class="fa fa-home"></i> <span>Meetings</span></a></li>
                                        <li class="active"><a><i class="fa fa-thumb-tack"></i> <span>Forecast</span></a></li>
                                    </ul>
                                </li>
                                <li class="has-children">
                                	<a href="#"><i class="fa fa-file-text"></i> <span>Admin Code Tables</span> <i class="fa fa-angle-right arrow"></i></a>
                                    <ul class="child-nav">
                                        <li><a onclick="gotoLink('/codem00008');"><i class="fa fa-thumb-tack"></i> <span>Budget Year</span></a></li>
                                        <li><a onclick="gotoLink('/codem00006');"><i class="fa fa-thumb-tack"></i> <span>Budget Type</span></a></li>
                                        <li><a onclick="gotoLink('/codem00005');"><i class="fa fa-thumb-tack"></i> <span>Budget Type Component</span></a></li>
                                        <li><a onclick="gotoLink('/codem00046');"><i class="fa fa-thumb-tack"></i> <span>Budget Component Type</span></a></li>
                                        <li><a onclick="gotoLink('/codem00003');"><i class="fa fa-thumb-tack"></i> <span>Budget Status</span></a></li>
                                        <li><a onclick="gotoLink('/codem00011');"><i class="fa fa-thumb-tack"></i> <span>Framework Methods</span></a></li>
                                        <li><a onclick="gotoLink('/codem00045');"><i class="fa fa-thumb-tack"></i> <span>Framework Parameter Types</span></a></li>
                                        <li><a onclick="gotoLink('/codem00019');"><i class="fa fa-thumb-tack"></i> <span>Meeting Agenda Types</span></a></li>
                                        <li><a onclick="gotoLink('/codem00020');"><i class="fa fa-thumb-tack"></i> <span>Meeting Roles</span></a></li>
                                        <li><a onclick="gotoLink('/codem00029');"><i class="fa fa-thumb-tack"></i> <span>Request Types</span></a></li>
                                        <li><a onclick="gotoLink('/codem00028');"><i class="fa fa-thumb-tack"></i> <span>Request Agent Types</span></a></li>
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
                                <h2 class="title" onclick="ShowLoading()">MTEF Budget Forecast</h2>
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
                                    <li class="active">Forecast</li>
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
                                    <div class="panel">
                                        
                                        <div class="panel-body">                                        
                                            <div class="gray_blanket" id="blanket_section">
	                                            <!-- Create New Year Version -->
	                                        	<div id="divNewYear" style="display: none;">
	                                        		<label>New Budget Year</label>
		                                           	<a id="create-new-year-version" class="btn btn-info toggle-code-handle pull-left" role="button" onclick="CreateNewVersionYear()"
		                                               data-toggle="tooltip" data-placement="top" title="Create a new version"><i class="fa fa-plus"> </i>Create New Version For Current Year</a>
		                                            <br/><br/>
	                                            </div>
	                                            
                                                <form class="form-horizontal mt-30 mb-10" style=" margin-left: 0px; margin-right: 0px;">
                                                    <div class="form-group">
                                                        <label class="col-sm-12 col-md-1 mt-5">Version</label>
                                                        <div id="mtef-versions-select" class="col-sm-12 col-md-5">
                                                            <select class='custom_select form-control' id='mtef-version'>
                                                            </select>
                                                            
                                                            <br style="clear: both;" /><br style="clear: both;" />
                                                            
                                                            <div>
                                                            	<button type="button" id="save-version" class="btn btn-success toggle-code-handle pull-left mr-5" role="button" onclick="SaveNewVersion()"
		                                                            data-toggle="tooltip" data-placement="top" title="Save a new version"><i class="fa fa-save"> </i>Save Version</button>
		                                                            
	                                                        	<button type="button" id="submit-final" class="btn btn-danger toggle-code-handle pull-right" role="button" onclick="SubmitVersion()"
	                                                            	data-toggle="tooltip" data-placement="top" title="Submit version"><i class="fa fa-send"> </i>Submit</button>
	                                                        </div>
                                                        </div>
                                                                                                                
                                                        <div class="col-md-6 right-side">
                                                            <button id="framework-trigger" type="button" class="btn btn-warning pull-right mr-5">
                                                                Framework Parameters
                                                            </button>
                                                        </div>
                                                    </div>
                                                </form>
                                                <br style="clear: both;" />
                                                <div class="col-md-12 mb-5" style="background: #f4f4f4;">
                                                	<label>MTEF Year Range</label>
                                                    <input style="padding: 20px;" type="text" id="range" value="" name="range" />
                                                </div>
                                                <br style="clear: both;" />
                                            </div>

                                            <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                <li role="presentation" class="active"><a class="" href="#revenuex" 
                                                    aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">Revenue</a></li>
                                                <li role="presentation">
                                                    <a class="" href="#expenditurex"
                                                       aria-controls="active" role="tab" data-toggle="tab" >Expenditure</a>
                                                </li>
                                            </ul>
                                            <div class="tab-content bg-white p-15">
                                                <div role="tabpanel" class="tab-pane active" id="revenuex" style="overflow: scroll; white-space: nowrap; overflow-x: visible; overflow-y: hidden;">
                                                    
                                                </div>
                                                <div role="tabpanel" class="tab-pane" id="expenditurex" style="overflow: scroll; white-space: nowrap; overflow-x: visible; overflow-y: hidden;">
                                                    
                                                </div>                                                
                                            </div>
                                            <!-- /.col-md-12 -->
                                        </div>
                                    </div>
                                </div>
                                <!-- /.col-md-12 -->

                            </div>
                            <!-- /.row -->
                            <div class="" id="modal11" data-title="Comment" data-header-color="#88a0b9" data-overlay-color="rgba(41,41,41,0.9)" data-subtitle="justification" data-icon-class="fa fa-comments">
                                <div class="p-15" style="padding-bottom: 0px !important;">
                                    <form class="form-horizontal">
                                        <div class="form-group has-feedback">
                                            <div class="col-sm-12">
                                                <textarea class="form-control" id="rightIcon4" placeholder="enter your comment here" rows="5"></textarea>
                                                <span class="glyphicon glyphicon-comment form-control-feedback"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <button type="submit" class="btn btn-primary btn-block">Save</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <div class="" id="modal_new_version" data-title="Save new version" data-header-color="#5cb85c" 
                                 data-overlay-color="rgba(41,41,41,0.9)" data-subtitle="Enter title and description" data-icon-class="">
                                <div class="p-15" style="padding-bottom: 0px !important;">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input id="version-namex" type="text" class="form-control"  placeholder="Version Title" />
                                            </div>
                                            <div class="col-sm-12">
                                                <textarea id="version-descx" rows="4" class="form-control" placeholder="Description"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <button id="save-mtef-version" onclick="saveMTEFFigures()" type="button" class="btn btn-primary btn-block">Save</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <div class="" id="modal_submit" data-title="Submit Version" data-header-color="#d43f3a"
                                 data-overlay-color="rgba(41,41,41,0.9)" data-subtitle="Current Version Name Here" data-icon-class="">
                                <div class="p-15" style="padding-bottom: 0px !important;">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <p>You are about to submit the current version. After submission, the data will not be available for editing anymore.<br/>
                                                <strong>Are you sure you want to submit the current version?</strong>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <button id="mtef-submit-final-version" type="button" onclick="submitVersionFigures()" class="btn btn-primary btn-block">Submit Version</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            
                            <div class="" id="modal_own_values" data-title="Own Value Percentages" data-header-color="#d43f3a"
                                 data-overlay-color="rgba(41,41,41,0.9)" data-subtitle="Enter Own Percentage Values per Year" data-icon-class="">
                                <div class="p-15" style="padding-bottom: 0px !important;">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-12" id="divOwnPercentageValuesTable">
                                                
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <button type="button" onclick="ownPercentageCalculator()" class="btn btn-primary btn-block">Save Values</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            
                            <div class="" id="modal_chart" data-title="Graph" data-header-color="#d43f3a"
                                 data-overlay-color="rgba(41,41,41,0.9)" data-subtitle="Past and forecasted figures" data-icon-class="">
                                <div class="p-15" style="padding-bottom: 0px !important;">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <div class="panel border-branded no-border border-3-top" data-panel-control>
                                                <div class="panel-heading">
                                                    <div class="panel-title">
                                                        <h5 id="chart-title-x"></h5>
                                                    </div>
                                                </div>
                                                <div class="panel-body">
                                                    <div id="budget-line-chart" class="op-chart">
                                                    	
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            
                            <div class="" id="modal_narration" data-title="MTEF Figure Narration" data-header-color="#5cb85c" 
                                 data-overlay-color="rgba(41,41,41,0.9)" data-subtitle="" data-icon-class="">
                                <div class="p-15" style="padding-bottom: 0px !important;">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <textarea id="narrationx" rows="4" class="form-control" placeholder="Narration"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <label id="date-who"></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <button id="save-mtef-narration" onclick="saveMTEFFigureNarration()" type="button" class="btn btn-primary btn-block">Save</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            
                            <div class="" id="long_loading" data-title="Start Process" data-header-color="#5cb85c" 
                                 data-overlay-color="rgba(41,41,41,0.9)" data-subtitle="" data-icon-class="">
                                <div class="p-15" style="padding-bottom: 0px !important;">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <p>
                                                	A new budget version for the current year will be created, this may take some minutes.
                                                </p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <button id="save-mtef-narration" onclick="startNewBudgetYearProcess()" type="button" class="btn btn-primary btn-block">Start</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
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
    
    <!-- holding params -->
    <div id="mtef-framework-params" style="display:none;"></div>
    
    <input type="hidden" id="chosen-mtef-figure-id" value="">
    <input type="hidden" id="chosen-budget-version-id" value="">
    <input type="hidden" id="current-from-year" value="">
    <input type="hidden" id="current-to-year" value="">
    <input type="hidden" id="current-base-year" value="">
    <input type="hidden" id="own-percentage-line-id" value="">
    
    <!-- Constants -->
    <input type="hidden" id="site-url" value="<%= Utility.SITE_URL %>">
    <input type="hidden" id="select-all" value="<%= Utility.OPTION_SELECT_ALL %>">
    <input type="hidden" id="selectx" value="<%= Utility.OPTION_SELECT %>">
    <input type="hidden" id="insertx" value="<%= Utility.OPTION_INSERT %>">
    <input type="hidden" id="v5er-idx" value="${sessionScope.userid}">
    <input type="hidden" id="icy" value="1"> 
    <input type="hidden" id="icy-base-year" value="">
    <input type="hidden" id="req-type-id" value="<%= Utility.ESTIMATES_SUBMISSION_REQUEST_TYPE_ID %>">
    
    <!-- /.main-wrapper -->
    <!-- ========== COMMON JS FILES ========== -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <script src="js/jquery-ui/jquery-ui.min.js"></script>
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script src="js/pace/pace.min.js"></script>
    <script src="js/lobipanel/lobipanel.min.js"></script>
    <script src="js/iscroll/iscroll.js"></script>
    <script src="js/ion.rangeSlider.js"></script>
    <script src="js/jquery.loading.js"></script>
    <!-- ========== PAGE JS FILES ========== -->
    <script src="js/prism/prism.js"></script>
    <script src="js/icheck/icheck.min.js"></script>
    <script src="js/waypoint/waypoints.min.js"></script>
    <script src="js/counterUp/jquery.counterup.min.js"></script>
    <script src="js/amcharts/amcharts.js"></script>
    <script src="js/amcharts/pie.js"></script>
    <script src="js/amcharts/serial.js"></script>
    <script src="js/amcharts/xy.js"></script>
    <script src="js/amcharts/plugins/export/export.min.js"></script>
    
    <script src="js/amcharts/themes/light.js"></script>
    <script src="js/toastr/toastr.min.js"></script>
    <script src="js/icheck/icheck.min.js"></script>
    <script src="js/iziModal/iziModal.min.js"></script>
    <script src="js/sweet-alert/sweetalert.min.js"></script>
    <script src="js/select2/select2.min.js"></script>
    <script src="js/moment/moment.min.js"></script>
    <script src="js/mockjax/jquery.mockjax.js"></script>
    <script src="js/mockjax/demo-mock.js"></script>
    <script src="js/x-editable/bootstrap-editable.min.js"></script>
    <script src="js/x-editable/demo.js"></script>

    <!-- ========== THEME JS ========== -->
    <script src="js/main.js"></script>
    
    <!-- For loading -->
    <script src="js/jquery.loading.js"></script>
    
    <script>
        checkLogin();
        var frameWorkMethods = "<%= Utility.getFrameworkMethods() %>";
    </script>
    
    <script id="erasable" src="js/bdgaosg-6162726163616461627261/64617368626f617264-00010.js"></script>   
    <script id="erasable2" src="js/bdgaosg-6162726163616461627261/64617368626f617264-6368617274-00010.js"></script>     
    <!-- ========== ADD custom.js FILE BELOW WITH YOUR CHANGES ========== -->
</body>
</html>
