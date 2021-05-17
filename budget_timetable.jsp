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
        <link rel="stylesheet" href="js/amcharts/plugins/export/export.css" type="text/css" media="all" />
        <!-- ========== THEME CSS ========== -->
        <link rel="stylesheet" href="css/main.css" media="screen">
        <!-- ========== MODERNIZR ========== -->
        <script src="js/modernizr/modernizr.min.js"></script>
        
        <!-- DATA TABLE CSS -->
        <link rel="stylesheet" href="js/DataTables/DataTables-1.10.13/css/jquery.dataTables.min.css">
        
        <!-- For Loading -->
        <link href="css/jquery.loading.css" rel="stylesheet" />
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
                                    <h2 class="title">Budget Timetables</h2>
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
                                        <li class="active">Budget Timetables</li>
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
                                                    <h5>Add Budget Timetable</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <div id="create-area">
                                                    <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">
                                                        <div class="form-group has-feedback">
                                                            <label>Name</label>
                                                            <input type="text" required class="form-control" id="timetable-name" placeholder="Enter Name">
                                                        </div>
                                                        <div class="form-group has-feedback">
                                                            <label>Description</label>
                                                            <textarea rows="5" required class="form-control" id="timetable-desc" placeholder="Enter Description"></textarea>
                                                        </div>
                                                        <div class="form-group has-feedback">
                                                            <label>Budget Year</label>
                                                            <div id="select_year1">
                                                            </div>
                                                        </div>
                                                        <button onclick="ReturnToList();" type="button" class="btn btn-success btn-labeled pull-left"><span class="btn-label btn-label-left"><i class="fa fa-chevron-left"></i></span>Go Back</button>
                                                        <button onclick="createBudgetTimetable();" type="button" class="btn btn-success btn-labeled pull-right">Create Timetable<span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
                                                    </form>
                                                </div>

                                                <!-- Nav tabs -->
                                                <div id="list-area">
                                                    <div class="col-md-12 right-side">
                                                        <a class="btn btn-info toggle-code-handle pull-right show-create" id="show-create" role="button" onclick="$('#timetable-name').val('');$('#timetable-desc').val('');$('#select_year1').val('0');"><i class="fa fa-plus"> </i>New Budget Timetable</a>
                                                    </div>
                                                    <br style="clear: both;" />
                                                    <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                        <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">Budget Timetables</a></li>
                                                    </ul>
                                                    <div class="tab-content bg-white p-15">
                                                        <div role="tabpanel" class="tab-pane active" id="main-table">
                                                            
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="panel" id="EditSection">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <h5>Edit Budget Timetable</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <form class="col-md-6" style="margin-top: 30px; margin-bottom: 30px; ">
                                                    <div class="form-group has-feedback">
                                                        <label>Name</label>
                                                        <input type="text" required class="form-control" id="timetable-ename" placeholder="Enter Name">
                                                    </div>
                                                    <div class="form-group has-feedback">
                                                        <label>Description</label>
                                                        <textarea rows="5" required class="form-control" id="timetable-edesc" placeholder="Enter Description"></textarea>
                                                    </div>
                                                    <button onclick="CloseEdit();" type="button" class="btn btn-success btn-labeled pull-left"><span class="btn-label btn-label-left"><i class="fa fa-chevron-left"></i></span>Go Back</button>
                                                    <button onclick="editBudgetTimetable();" type="button" class="btn btn-success btn-labeled pull-right">Save<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                </form>
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
        
        <div class="modal fade in" id="divAskCreateNewYear">
	        	<div class="modal-dialog modal-center">
	        		<div class="modal-content">
	                    <div class="modal-header">
			               <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			               	<span aria-hidden="true" style="font-size:14px">x</span>
			               </button>
			            <h4 class="modal-title">New Budget Year</h4>
			          </div>
			          <div class="modal-body">
	                     <b>No Budget Year record has been created yet. Create new budget year by clicking below.</b><br/>
						 <br/>
						 <div>
						 	<button onclick="openCreateNewYear();" type="button" class="btn btn-success btn-labeled">Create Now<span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
						 </div>
	                  </div>
			       </div>
	        	</div>
	       </div>
	       
	       <div class="modal fade in" id="divCreateNewYear">
	        	<div class="modal-dialog modal-center">
	        		<div class="modal-content">
	                    <div class="modal-header">
			               <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			               	<span aria-hidden="true" style="font-size:14px">x</span>
			               </button>
			            <h4 class="modal-title">Create New Budget Year</h4>
			          </div>
			          <div class="modal-body">
	                     <div class="form-group">
                            <div class="col-xs-12">
                                <input class="form-control" id="new-year-name" type="text" required placeholder="Name" onkeypress="checkEnter(event)">
                            </div>
                         </div>
                         <div class="form-group">
                            <div class="col-xs-12">
                                <input class="form-control" id="new-year-year" type="number" required placeholder="Year" onkeypress="checkEnter(event)">
                            </div>
                         </div>
						 <div style="margin-right: 50px;">
						 	<br/><br/>
						 	<button onclick="createNewYear();" type="button" class="btn btn-success btn-labeled">Create<span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
						 </div>
	                  </div>
			       </div>
	        	</div>
	       </div>
	       
	       <div class="modal fade in" id="divViewActivities">
	         <div class="modal-dialog modal-center modal-lg">
	        	<div class="modal-content">
	                <div class="modal-header">
			            <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true" style="font-size:14px">x</span>
	                    </button>
	                	<h4 class="modal-title">Budget Timetable Activities</h4>
	                </div>
	           		<div class="modal-body">
	           			<button onclick="openCreateBudgetTimetableActivity();" type="button" class="btn btn-success btn-labeled">Add New Activity<span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
	           			<br/><br/>
	           			
	           			<div id="divAllActivities">
	           				
	           			</div>				  		
		       		</div>
			    </div>
		    </div>
	      </div>
	       
	      <div class="modal fade in" id="divAddActivity">
	         <div class="modal-dialog modal-center">
	        	<div class="modal-content">
	                <div class="modal-header">
			            <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true" style="font-size:14px">x</span>
	                    </button>
	                	<h4 class="modal-title">Create New Activity</h4>
	                </div>
	           		<div class="modal-body">
				  		<div class="form-group has-feedback">
                            <label>Name</label>
                            <input type="text" required class="form-control" id="timetable-act-name" placeholder="Enter Name">
                        </div>
                        <div class="form-group has-feedback">
                            <label>Description</label>
                            <textarea rows="5" required class="form-control" id="timetable-act-desc" placeholder="Enter Description"></textarea>
                        </div>
                        <div class="form-group has-feedback">
                            <label>From</label>
                            <div class='input-group date table-adjust custom_datepicker'>
                                <input id="timetable-act-from" data-date-format="DD/MM/YYYY hh:mm:ss A" type='text' class="form-control" />
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label>To</label>
                            <div class='input-group date table-adjust custom_datepicker'>
                                <input id="timetable-act-to" data-date-format="DD/MM/YYYY hh:mm:ss A" type='text' class="form-control" />
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label>Budget Phase</label>
                            <div id="select_phase1">
                            </div>
                        </div>
				  		
				  		<button onclick="createBudgetTimetableActivity();" type="button" class="btn btn-success btn-labeled">Create Activity<span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
		       		</div>
			    </div>
		     </div>
	      </div>
	      
	      <div class="modal fade in" id="divEditActivity">
	         <div class="modal-dialog modal-center">
	        	<div class="modal-content">
	                <div class="modal-header">
			            <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true" style="font-size:14px">x</span>
	                    </button>
	                	<h4 class="modal-title">Edit Activity</h4>
	                </div>
	           		<div class="modal-body">
				  		<div class="form-group has-feedback">
                            <label>Name</label>
                            <input type="text" required class="form-control" id="timetable-act-ename" placeholder="Enter Name">
                        </div>
                        <div class="form-group has-feedback">
                            <label>Description</label>
                            <textarea rows="5" required class="form-control" id="timetable-act-edesc" placeholder="Enter Description"></textarea>
                        </div>
                        <div class="form-group has-feedback">
                            <label>From</label>
                            <div class='input-group date table-adjust custom_datepicker'>
                                <input id="timetable-act-efrom" data-date-format="DD/MM/YYYY hh:mm:ss A" type='text' class="form-control" />
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label>To</label>
                            <div class='input-group date table-adjust custom_datepicker'>
                                <input id="timetable-act-eto" data-date-format="DD/MM/YYYY hh:mm:ss A" type='text' class="form-control" />
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label>Budget Phase</label>
                            <div id="select_ephase1">
                            </div>
                        </div>
				  		
				  		<button onclick="editBudgetTimetableActivity();" type="button" class="btn btn-success btn-labeled">Save Activity<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
		       		</div>
			    </div>
		     </div>
	      </div>
                                                
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
            
	    <input type="hidden" id="chosen-budget-timetable-id" value="">
	    <input type="hidden" id="chosen-budget-timetable-activity-id" value="">
	    
	    <!-- Constants -->
	    <input type="hidden" id="site-url" value="<%= Utility.SITE_URL %>">
	    <input type="hidden" id="select-all" value="<%= Utility.OPTION_SELECT_ALL %>">
	    <input type="hidden" id="selectx" value="<%= Utility.OPTION_SELECT %>">
	    <input type="hidden" id="insertx" value="<%= Utility.OPTION_INSERT %>">
	    <input type="hidden" id="updatex" value="<%= Utility.OPTION_UPDATE %>">
	    <input type="hidden" id="deletex" value="<%= Utility.OPTION_DELETE %>">
	    <input type="hidden" id="insertedx" value="<%= Utility.ActionResponse.INSERTED.toString() %>">
	    <input type="hidden" id="updatedx" value="<%= Utility.ActionResponse.UPDATED.toString() %>">
	    <input type="hidden" id="deletedx" value="<%= Utility.ActionResponse.DELETED.toString() %>">
	       
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

        <!-- ========== THEME JS ========== -->
        <script src="js/main.js"></script>
        <script src="js/production-chart.js"></script>
        <script src="js/traffic-chart.js"></script>
        <script src="js/task-list.js"></script>
        <script src="js/select2/select2.min.js"></script>
        
        <!-- DATA TABLE STUFF -->
        <script src="js/DataTables/DataTables-1.10.13/js/jquery.dataTables.min.js"></script>
        
        <!-- For loading -->
        <script src="js/jquery.loading.js"></script>
        
        <!-- Date-time picker -->
        <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>

        <!-- ========== ADD custom.js FILE BELOW WITH YOUR CHANGES ========== -->
        <script id="erasable" src="js/bdgaosg-6162726163616461627261/64617368626f617264-00004.js"></script>
    </body>
</html>
