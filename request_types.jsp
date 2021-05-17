<%@ page import="com.bpp.utility.Utility" %>
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
        <!-- ========== THEME CSS ========== -->
        <link rel="stylesheet" href="css/main.css" media="screen">
        <!-- ========== MODERNIZR ========== -->
        <script src="js/modernizr/modernizr.min.js"></script>
        
        <!-- CK Editor -->
        <script src="js/ckeditor/ckeditor.js"></script>
        
        <!-- For Loading -->
        <link href="css/jquery.loading.css" rel="stylesheet" />
        
        <link rel="stylesheet" href="js/amcharts/plugins/export/export.css" type="text/css" media="all" />
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
                                    <h2 class="title">Request Types</h2>
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
                                        <li class="active">Request Types</li>
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
                                                    <h5>Add New Request Type</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <div id="create-area">
                                                    <form style="margin-top: 30px; margin-bottom: 30px; ">
                                                        <div class="form-group has-feedback">
                                                            <label>Name</label>
                                                            <input type="text" required class="form-control" id="obj-name" placeholder="Enter Name">
                                                        </div>
                                                        <div>
                                                        	<label style="font-size: 15px;"><i>Request Agents</i></label>
                                                            <table class="table table-striped" id="tab_logic">
					                                           <thead>
					                                               <tr >
					                                                 <th style="width: 5px;">
					                                                    +
					                                                 </th>
					                                                 <th style="width: 20%;">
					                                                    Agent Type
					                                                 </th>
					                                                 <th style="width: 25%;">
					                                                    Role
					                                                 </th>
					                                                 <th style="width: 35%;">
					                                                    MDA
					                                                 </th>
					                                                 <th style="width: 20%;">
					                                                    Notifications
					                                                 </th>
					                                               </tr>
					                                           </thead>
					                                           <tbody>
					                                               <tr id='addr0'>
					                                                  <td>1</td>
					                                                  <td><select class='form-control custom_select' id='magent-type0'></select></td>
					                                                  <td><select class='form-control custom_select' id='mrole0'></select></td>
					                                                  <td><select class='form-control custom_select' id='mmda0'></select></td>
					                                                  <td><button type="button" id="notif-setup0" class="btn btn-info" role="button" onclick="showSubNotificationSetup()"
	                                                            				  data-toggle="tooltip" data-placement="top" title="Create notification message"><i class="fa fa-comment"> </i>
	                                                            				  Add Notification</button></td>
					                                               </tr>
					                                               <tr id='addr1'>
					                                                  <td>2</td>
					                                                  <td><select class='form-control custom_select' id='magent-type1'></select></td>
					                                                  <td><select class='form-control custom_select' id='mrole1'></select></td>
					                                                  <td><select class='form-control custom_select' id='mmda1'></select></td>
					                                                  <td><button type="button" id="notif-setup1" class="btn btn-info" role="button" onclick="showNotificationSetup(1)"
	                                                            				  data-toggle="tooltip" data-placement="top" title="Create notification messages"><i class="fa fa-comment"> </i>
	                                                            				  Add Notifications</button></td>
					                                               </tr>
					                                               <tr id='addr2'></tr>
					                                           </tbody>
					                                        </table>
					                                        <a id='add_row' class='btn btn-sm btn-info btn-labeled'>
					                                        	<span class="btn-label btn-label-left"><i class="fa fa-plus"></i></span>Add New Row
					                                        </a>&nbsp;&nbsp;
					                                        <a id='delete_row' class="btn btn-sm btn-danger btn-labeled">
					                                        	<span class="btn-label btn-label-left"><i class="fa fa-remove"></i></span>Delete Row
					                                        </a>
                                                        </div>
                                                        <br/><br/>
                                                        <div>
                                                        	<label style="font-size: 15px;"><i>Full Approval Notification</i></label>
                                                        	<table class="table table-striped">
					                                           <thead>
					                                               <tr>
					                                                 <th>
					                                                    Notification Type
					                                                 </th>
					                                                 <th>
					                                                    Subject
					                                                 </th>
					                                                 <th style="width: 40%;">
					                                                    Body Text
					                                                 </th>
					                                               </tr>
					                                           </thead>
					                                           <tbody>
					                                               <tr>
					                                                  <td>Full Approval<input type="hidden" id="mnotif-type3" value="<%= Utility.FULL_APPROVAL_NOTIFICATION_TYPE %>"></td>
					                                                  <td><input type="text" required class="form-control" id="mnotif-subject3" placeholder="Enter Subject"></td>
					                                                  <td><textarea rows="2" cols="10" required class="form-control" id="mnotif-text3" placeholder="Enter Full Approval Message"></textarea></td>
					                                               </tr>
					                                           </tbody>
					                                        </table>
                                                        </div>
                                                        <br/><br/>
                                                        <div>
                                                        	<label style="font-size: 15px;"><i>Report Template</i></label>
                                                        	<textarea rows="4" cols="10" required class="form-control" id="mrequest-report" placeholder="Paste Report Template"></textarea>
                                                        </div>
                                                        <br/><br/>
                                                        <button onclick="ReturnToList();" type="button" class="btn btn-success btn-labeled pull-left"><span class="btn-label btn-label-left"><i class="fa fa-chevron-left"></i></span>Go Back</button>
                                                        <button onclick="createObj();" type="button" class="btn btn-success btn-labeled pull-right">Create<span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
                                                    </form>
                                                </div>

                                                <!-- Nav tabs -->
                                                <div id="list-area">
                                                    <div class="col-md-12 right-side">
                                                        <a class="btn btn-info toggle-code-handle pull-right show-create2" id="show-create" role="button"><i class="fa fa-plus"> </i>New Request Type</a>
                                                    </div>
                                                    <br style="clear: both;" />
                                                    <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                        <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">Request Types</a></li>
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
                                                    <h5>Edit Request Type</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <form style="margin-top: 30px; margin-bottom: 30px; ">
                                                    <div class="form-group has-feedback">
                                                        <label>Name</label>
                                                        <input type="text" required class="form-control" id="obj-ename" placeholder="Enter Request Type Name">
                                                    </div>
                                                    <div>
	                                                	<label style="font-size: 15px;"><i>Request Agents</i></label>
	                                                    <table class="table table-striped" id="etab_logic">
				                                           <thead>
				                                               <tr>
				                                                 <th style="width: 20%;">
				                                                    Agent Type
				                                                 </th>
				                                                 <th style="width: 25%;">
				                                                    Role
				                                                 </th>
				                                                 <th style="width: 35%;">
				                                                    MDA
				                                                 </th>
				                                                 <th style="width: 20%;">
				                                                    Notifications
				                                                 </th>
				                                                 <th style="width: 10%;">				                                                    
				                                                 </th>
				                                               </tr>
				                                           </thead>
				                                           <tbody>
				                                               <tr id='eaddr0'></tr>
				                                           </tbody>
				                                        </table>
				                                        <a id='eadd_row' class='btn btn-sm btn-info btn-labeled'>
				                                        	<span class="btn-label btn-label-left"><i class="fa fa-plus"></i></span>Add New Row
				                                        </a>&nbsp;&nbsp;
				                                        <a id='edelete_row' class="btn btn-sm btn-danger btn-labeled">
				                                        	<span class="btn-label btn-label-left"><i class="fa fa-remove"></i></span>Delete Row
				                                        </a>
	                                                </div>
	                                                <br/><br/>
                                                    <div>
                                                       	<label style="font-size: 15px;"><i>Full Approval Notification</i></label>
                                                       	<table class="table table-striped">
				                                           <thead>
				                                               <tr>
				                                                 <th>
				                                                    Notification Type
				                                                 </th>
				                                                 <th>
				                                                    Subject
				                                                 </th>
				                                                 <th style="width: 40%;">
				                                                    Body Text
				                                                 </th>
				                                               </tr>
				                                           </thead>
				                                           <tbody>
				                                               <tr>
				                                                  <td>Full Approval<input type="hidden" id="emnotif-type3" value="<%= Utility.FULL_APPROVAL_NOTIFICATION_TYPE %>"></td>
				                                                  <td><input type="text" required class="form-control" id="emnotif-subject3" placeholder="Enter Subject"></td>
				                                                  <td><textarea rows="2" cols="10" required class="form-control" id="emnotif-text3" placeholder="Enter Full Approval Message"></textarea></td>
				                                               </tr>
				                                           </tbody>
				                                        </table>
                                                    </div>
                                                    <br/><br/>
                                                    <div>
                                                       	<label style="font-size: 15px;"><i>Report Template</i></label>
                                                       	<textarea rows="4" cols="10" required class="form-control" id="emrequest-report" placeholder="Paste Report Template"></textarea>
                                                    </div>
                                                    <br/><br/>
                                                    <button onclick="CloseEdit();" type="button" class="btn btn-success btn-labeled pull-left"><span class="btn-label btn-label-left"><i class="fa fa-chevron-left"></i></span>Go Back</button>
                                                    <button onclick="editObj();" type="button" class="btn btn-success btn-labeled pull-right">Save<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
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
        
        <div class="modal fade in" id="divNotifModal">
	         <div class="modal-dialog modal-center modal-lg">
	        	<div class="modal-content">
	                <div class="modal-header">
			            <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true" style="font-size:14px">x</span>
	                    </button>
	                	<h4 class="modal-title">Notifications Setup</h4>
	                </div>
	           		<div class="modal-body">
                       <table class="table table-striped">
                          <thead>
                              <tr>
                                <th>
                                   Notification Type
                                </th>
                                <th>
                                   Subject
                                </th>
                                <th style="width: 40%;">
                                   Body Text
                                </th>
                              </tr>
                          </thead>
                          <tbody>
                              <tr>
                                 <td>Approval<input type="hidden" id="mnotif-type1" value="<%= Utility.APPROVAL_NOTIFICATION_TYPE %>"></td>
                                 <td><input type="text" required class="form-control" id="mnotif-subject1" placeholder="Enter Subject"></td>
                                 <td><textarea rows="2" cols="10" required class="form-control" id="mnotif-text1" placeholder="Enter Approval Message"></textarea></td>
                              </tr>
                              <tr>
                                 <td>Rejection<input type="hidden" id="mnotif-type2" value="<%= Utility.REJECTION_NOTIFICATION_TYPE %>"></td>
                                 <td><input type="text" required class="form-control" id="mnotif-subject2" placeholder="Enter Subject"></td>
                                 <td><textarea rows="2" cols="10" required class="form-control" id="mnotif-text2" placeholder="Enter Rejection Message"></textarea></td>
                              </tr>
                          </tbody>
                       </table>
                       <br/>
                       <button onclick="storeNotifValues();" type="button" class="btn btn-success btn-labeled">Done<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
		       		</div>
			    </div>
		    </div>
	    </div>
	    
	    <div class="modal fade in" id="divSubNotifModal">
	         <div class="modal-dialog modal-center modal-lg">
	        	<div class="modal-content">
	                <div class="modal-header">
			            <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true" style="font-size:14px">x</span>
	                    </button>
	                	<h4 class="modal-title">Submission Notification Setup</h4>
	                </div>
	           		<div class="modal-body">
                       <table class="table table-striped">
                          <thead>
                              <tr>
                                <th>
                                   Notification Type
                                </th>
                                <th>
                                   Subject
                                </th>
                                <th style="width: 40%;">
                                   Body Text
                                </th>
                              </tr>
                          </thead>
                          <tbody>
                              <tr>
                                 <td>Submission<input type="hidden" id="mnotif-type0" value="<%= Utility.SUBMISSION_NOTIFICATION_TYPE %>"></td>
                                 <td><input type="text" required class="form-control" id="mnotif-subject0" placeholder="Enter Subject"></td>
                                 <td><textarea rows="2" cols="10" required class="form-control" id="mnotif-text0" placeholder="Enter Submission Message"></textarea></td>
                              </tr>
                          </tbody>
                       </table>
                       <br/>
                       <button onclick="hideSubNotificationSetup();" type="button" class="btn btn-success btn-labeled">Done<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
		       		</div>
			    </div>
		    </div>
	    </div>
	            
        <div class="modal fade in" id="divViewAgents">
	         <div class="modal-dialog modal-center modal-lg">
	        	<div class="modal-content">
	                <div class="modal-header">
			            <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true" style="font-size:14px">x</span>
	                    </button>
	                	<h4 class="modal-title">Request Type Agents</h4>
	                </div>
	           		<div id="allAgents" class="modal-body">
                       	
		       		</div>
			    </div>
		    </div>
	    </div>
	      
	    <div class="modal fade in" id="divViewUsers">
	         <div class="modal-dialog modal-center modal-lg">
	        	<div class="modal-content">
	                <div class="modal-header">
			            <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true" style="font-size:14px">x</span>
	                    </button>
	                	<h4 class="modal-title">Current Users in Role</h4>
	                </div>
	           		<div id="allUsers" class="modal-body">
                       			  		
		       		</div>
			    </div>
		    </div>
	    </div>
	    
	    <input type="hidden" id="obj-id" value="">
	    <input type="hidden" id="row-index" value="">
	    <input type="hidden" id="is-edit" value="0">
	    <input type="hidden" id="curr-role" value="0">
	    <input type="hidden" id="sub-role" value="">
	    
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
        
        <!-- For loading -->
        <script src="js/jquery.loading.js"></script>

        <!-- ========== ADD custom.js FILE BELOW WITH YOUR CHANGES ========== -->
        <script id="erasable" src="js/bdgaosg-6162726163616461627261/636f64656d-00029.js"></script>
    </body>
</html>
