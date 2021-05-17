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
        <link rel="stylesheet" href="js/amcharts/plugins/export/export.css" type="text/css" media="all" />

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
                                    <h2 class="title">Meeting Manager</h2>
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
                                        <li class="active">Meeting Manager</li>
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
                                                    <h5>Create New Meeting</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <div id="create-area">
                                                    <form class="col-md-6" style="margin-top: 0px; margin-bottom: 20px; ">
                                                        <div class="form-group has-feedback">
                                                            <label>Meeting Title</label>
                                                            <input type="text" required class="form-control" id="meeting-name" placeholder="Enter Meeting Title">
                                                        </div>
                                                        
                                                        <div class="form-group has-feedback">
                                                            <label>Venue</label>
                                                            <textarea rows="3" required class="form-control" id="meeting-venue" placeholder="Enter Venue"></textarea>
                                                        </div>
                                                        
                                                        <div class="form-group has-feedback">
                                                            <label>Meeting Date</label>
                                                            <div class='input-group date table-adjust custom_datepicker'>
	                                                            <input id="meeting-date" data-date-format="DD/MM/YYYY hh:mm:ss A" type='text' class="form-control" />
	                                                            <span class="input-group-addon">
	                                                                <span class="glyphicon glyphicon-calendar"></span>
	                                                            </span>
	                                                        </div>
                                                        </div>
                                                        
                                                        <div class="form-group has-feedback">
                                                            <label>Budget Timetable Activity</label>
                                                            <div id="select_activity1">
                                                            </div>
                                                        </div>
                                                        
                                                        <div>
                                                        	<br/><label style="font-size: 15px;"><i>Meeting Agenda</i></label>
                                                            <table class="table table-striped" id="tab_logice">
					                                           <thead>
					                                               <tr >
					                                                 <th style="width: 5px;">
					                                                 </th>
					                                                 <th style="width: 50%;">
					                                                    Agenda Type
					                                                 </th>
					                                                 <th style="width: 50%;">
					                                                    Details
					                                                 </th>
					                                               </tr>
					                                           </thead>
					                                           <tbody>
					                                               <tr id='addre0'>
					                                                  <td>1</td>
					                                                  <td><div id="select_magendatype0"></div></td>
					                                                  <td><textarea rows="2" required class="form-control" id="magenda-details[]" placeholder="Enter Details"></textarea></td>
					                                               </tr>
					                                               <tr id='addre1'></tr>
					                                           </tbody>
					                                        </table>
					                                        <a id='add_rowe' class='btn btn-sm btn-info btn-labeled'>
					                                        	<span class="btn-label btn-label-left"><i class="fa fa-plus"></i></span>Add New Row
					                                        </a>&nbsp;&nbsp;
					                                        <a id='delete_rowe' class="btn btn-sm btn-danger btn-labeled">
					                                        	<span class="btn-label btn-label-left"><i class="fa fa-remove"></i></span>Delete Row
					                                        </a>
                                                        </div>
                                                        
                                                        <div>
                                                        	<br/>
                                                        	<label style="font-size: 15px;"><i>Meeting Attendees</i></label>
                                                        	<div class="col-md-12">
                                                        		<label>Attendee Options</label>
	                                                        	
	                                                        	<div class="form-md-checkboxes">
		                                                           <div class="md-checkbox-inline">
		                                                               <div class="md-checkbox">
		                                                                   <input type="checkbox" id="all-users" class="md-check">
		                                                                   <label for="all-users">
		                                                                       <span></span>
		                                                                       <span class="check"></span>
		                                                                       <span class="box"></span>
		                                                                       All Users <small>(across all MDAs)</small>
		                                                                   </label>
		                                                               </div>
		                                                           </div>
		                                                        </div>
		                                                        
		                                                        <div class="form-md-checkboxes">
		                                                           <div class="md-checkbox-inline">
		                                                               <div class="md-checkbox">
		                                                                   <input type="checkbox" id="specific-role" class="md-check">
		                                                                   <label for="specific-role">
		                                                                       <span></span>
		                                                                       <span class="check"></span>
		                                                                       <span class="box"></span>
		                                                                       Specific Role <small>(across all MDAs)</small>
		                                                                   </label>
		                                                               </div>
		                                                           </div>
		                                                        </div>
		                                                        
		                                                        <div class="form-md-checkboxes">
		                                                           <div class="md-checkbox-inline">
		                                                               <div class="md-checkbox">
		                                                                   <input type="checkbox" id="specific-mda" class="md-check">
		                                                                   <label for="specific-mda">
		                                                                       <span></span>
		                                                                       <span class="check"></span>
		                                                                       <span class="box"></span>
		                                                                       Specific MDA
		                                                                   </label>
		                                                               </div>
		                                                           </div>
		                                                        </div>
		                                                        
		                                                        <div class="form-md-checkboxes">
		                                                           <div class="md-checkbox-inline">
		                                                               <div class="md-checkbox">
		                                                                   <input type="checkbox" id="specific-users" class="md-check">
		                                                                   <label for="specific-users">
		                                                                       <span></span>
		                                                                       <span class="check"></span>
		                                                                       <span class="box"></span>
		                                                                       Specific Users
		                                                                   </label>
		                                                               </div>
		                                                           </div>
		                                                        </div>
	                                                        </div>
	                                                        
	                                                        <hr>
	                                                        
	                                                        <div id="divAttendees" style="display: none;">
	                                                        	<label>Select Specific Users</label>
	                                                            <table class="table table-striped" id="tab_logic">
						                                           <thead>
						                                               <tr >
						                                                 <th style="width: 5px;">
						                                                 </th>
						                                                 <th style="width: 50%;">
						                                                    MDA
						                                                 </th>
						                                                 <th style="width: 50%;">
						                                                    Attendee
						                                                 </th>
						                                                 <th style="width: 50%;">
						                                                    Meeting Role
						                                                 </th>
						                                               </tr>
						                                           </thead>
						                                           <tbody>
						                                               <tr id='addr0'>
						                                                  <td>1</td>
						                                                  <td><select class='form-control custom_select' id='mmda-id0'></select></td>
						                                                  <td><div id="select_mattendee0"></div></td>
						                                                  <td><div id="select_mrole0"></div></td>
						                                               </tr>
						                                               <tr id='addr1'></tr>
						                                           </tbody>
						                                        </table>
						                                        <a id='add_row' class='btn btn-sm btn-info btn-labeled'>
						                                        	<span class="btn-label btn-label-left"><i class="fa fa-plus"></i></span>Add New Row
						                                        </a>&nbsp;&nbsp;
						                                        <a id='delete_row' class="btn btn-sm btn-danger btn-labeled">
						                                        	<span class="btn-label btn-label-left"><i class="fa fa-remove"></i></span>Delete Row
						                                        </a>
					                                        </div>
					                                        
					                                        <div id="divRolesOnly" style="display: none;">
					                                        	<label>Select Role</label>
					                                        	<select class='form-control custom_select' id='role-only'></select>
					                                        </div>
					                                        
					                                        <div id="divMDAOnly" style="display: none;">
					                                        	<label>Select MDA</label>
					                                        	<select class='form-control custom_select' id='mda-only'></select>
					                                        </div>
					                                    </div>
                                                        
                                                        <br/>
                                                        
                                                        <hr>
                                                        
                                                        <button onclick="ReturnToList();" type="button" class="btn btn-success btn-labeled pull-left"><span class="btn-label btn-label-left"><i class="fa fa-chevron-left"></i></span>Go Back</button>
                                                        <button onclick="createMeeting();" type="button" class="btn btn-success btn-labeled pull-right">Create Meeting<span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
                                                    </form>
                                                </div>

                                                <!-- Nav tabs -->
                                                <div id="list-area">
                                                    <div class="col-md-12 right-side">
                                                        <a class="btn btn-info toggle-code-handle pull-right show-create" id="show-create" role="button"><i class="fa fa-plus"> </i>Create Meeting</a>
                                                    </div>
                                                    <br style="clear: both;" />
                                                    <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                        <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true">All Meetings</a></li>
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
                                                    <h5>Edit Meeting</h5>
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">
                                                <form class="col-md-6" style="margin-top: 0px; margin-bottom: 30px; ">
                                                    <div class="form-group has-feedback">
                                                        <label>Meeting Title</label>
                                                        <input type="text" required class="form-control" id="emeeting-name" placeholder="Enter Meeting Title">
                                                    </div>
                                                    
                                                    <div class="form-group has-feedback">
                                                        <label>Venue</label>
                                                        <textarea rows="3" required class="form-control" id="emeeting-venue" placeholder="Enter Venue"></textarea>
                                                    </div>
                                                    
                                                    <div class="form-group has-feedback">
                                                        <label>Meeting Date</label>
                                                        <div class='input-group date table-adjust custom_datepicker'>
                                                            <input id="emeeting-date" data-date-format="DD/MM/YYYY hh:mm:ss A" type='text' class="form-control" />
                                                            <span class="input-group-addon">
                                                                <span class="glyphicon glyphicon-calendar"></span>
                                                            </span>
                                                        </div>
                                                    </div>
                                                    
                                                    <div style="margin-top: 10px;">
                                                    		<br/><label style="font-size: 15px;"><i>Meeting Agenda</i></label>
                                                            <table class="table table-striped" id="etab_logice">
					                                           <thead>
					                                               <tr >
					                                                 <th style="width: 5px;">
					                                                 </th>
					                                                 <th style="width: 50%;">
					                                                    Agenda Type
					                                                 </th>
					                                                 <th style="width: 50%;">
					                                                    Details
					                                                 </th>
					                                               </tr>
					                                           </thead>
					                                           <tbody>
					                                               <tr id='eaddre0'>
					                                                  <td>1</td>
					                                                  <td><div id="eselect_magendatype0"></div></td>
					                                                  <td><textarea rows="2" required class="form-control" id="emagenda-details[]" placeholder="Enter Details"></textarea></td>
					                                               </tr>
					                                               <tr id='eaddre1'></tr>
					                                           </tbody>
					                                        </table>
					                                        <a id='eadd_rowe' class='btn btn-sm btn-info btn-labeled'>
					                                        	<span class="btn-label btn-label-left"><i class="fa fa-plus"></i></span>Add New Row
					                                        </a>&nbsp;&nbsp;
					                                        <a id='edelete_rowe' class="btn btn-sm btn-danger btn-labeled">
					                                        	<span class="btn-label btn-label-left"><i class="fa fa-remove"></i></span>Delete Row
					                                        </a>
                                                        </div>
                                                        
                                                        <div>
                                                        	<br/><label style="font-size: 15px;"><i>Meeting Attendees</i></label>
                                                            <table class="table table-striped" id="etab_logic">
					                                           <thead>
					                                               <tr >
					                                                 <th style="width: 5px;">
					                                                 </th>
					                                                 <th style="width: 50%;">
					                                                    Attendee
					                                                 </th>
					                                                 <th style="width: 50%;">
					                                                    Meeting Role
					                                                 </th>
					                                               </tr>
					                                           </thead>
					                                           <tbody>
					                                               <tr id='eaddr0'>
					                                                  <td>1</td>
					                                                  <td><div id="eselect_mattendee0"></div></td>
					                                                  <td><div id="eselect_mrole0"></div></td>
					                                               </tr>
					                                               <tr id='eaddr1'></tr>
					                                           </tbody>
					                                        </table>
					                                        <a id='eadd_row' class='btn btn-sm btn-info btn-labeled'>
					                                        	<span class="btn-label btn-label-left"><i class="fa fa-plus"></i></span>Add New Row
					                                        </a>&nbsp;&nbsp;
					                                        <a id='edelete_row' class="btn btn-sm btn-danger btn-labeled">
					                                        	<span class="btn-label btn-label-left"><i class="fa fa-remove"></i></span>Delete Row
					                                        </a>
                                                        </div>
                                                        
                                                        <br/>
                                                        
                                                        <hr>
                                                    
                                                    <button onclick="CloseEdit();" type="button" class="btn btn-success btn-labeled pull-left"><span class="btn-label btn-label-left"><i class="fa fa-chevron-left"></i></span>Go Back</button>
                                                    <button onclick="editMeeting();" type="button" class="btn btn-success btn-labeled pull-right">Save<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
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
        
	       <div class="modal fade in" id="divViewMeeting">
	         <div class="modal-dialog modal-center modal-lg">
	        	<div class="modal-content">
	                <div class="modal-header">
			            <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true" style="font-size:14px">x</span>
	                    </button>
	                	<h4 class="modal-title">Meeting Details</h4>
	                </div>
	           		<div class="modal-body">
	           			<button onclick="openViewDeliberations();" type="button" class="btn btn-success btn-labeled">View Meeting Deliberations<span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
	           			<br/><br/>
	           			
	           			<table>
	           				<tr>
	           					<td>
				           			<div class="form-group has-feedback">
			                            <label>Title</label>
			                            <label id="vmeeting-title"></label>
			                        </div>
		                        </td>
		                        <td>
			                        <div class="form-group has-feedback">
			                            <label>Venue</label>
			                            <label id="vmeeting-venue"></label>
			                        </div>
		                        </td>
		                    </tr>
		                    <tr>
	           					<td>
				           			<div class="form-group has-feedback">
			                            <label>Timetable Activity</label>
			                            <label id="vmeeting-timetable"></label>
			                        </div>
		                        </td>
		                        <td>
			                        <div class="form-group has-feedback">
			                            <label>Meeting Date</label>
			                            <label id="vmeeting-date"></label>
			                        </div>
		                        </td>
		                    </tr>
                        </table>
                        
                        <div style="margin-top: 10px;">
                          <br/><label style="font-size: 15px;"><i>Meeting Agenda</i></label>
                          <table class="table table-striped" id="vtab_logice">
                            <thead>
                                <tr >
                                  <th style="width: 5px;">
                                  </th>
                                  <th style="width: 50%;">
                                     Agenda Type
                                  </th>
                                  <th style="width: 50%;">
                                     Details
                                  </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr id='vaddre0'>
                                   <td>1</td>
                                   <td><div id="vselect_magendatype0"></div></td>
                                   <td><textarea rows="2" readonly class="form-control" id="vmagenda-details[]" placeholder="Enter Details"></textarea></td>
                                </tr>
                                <tr id='vaddre1'></tr>
                            </tbody>
                         </table>
                       </div>
                                    
                       <div>
                       	   <br/><label style="font-size: 15px;"><i>Attendees</i></label>
                           <table class="table table-striped" id="vtab_logic">
                            <thead>
                                <tr >
                                  <th style="width: 5px;">
                                     +
                                  </th>
                                  <th style="width: 50%;">
                                     Attendee
                                  </th>
                                  <th style="width: 50%;">
                                     Meeting Role
                                  </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr id='vaddr0'>
                                   <td>1</td>
                                   <td><div id="vselect_mattendee0"></div></td>
                                   <td><div id="vselect_mrole0"></div></td>
                                </tr>
                                <tr id='vaddr1'></tr>
                            </tbody>
                         </table>
                       </div>				  		
		       		</div>
			    </div>
		    </div>
	      </div>
	       
	      <div class="modal fade in" id="divViewDeliberations">
	         <div class="modal-dialog modal-center modal-lg">
	        	<div class="modal-content">
	                <div class="modal-header">
			            <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true" style="font-size:14px">x</span>
	                    </button>
	                	<h4 class="modal-title">All Deliberations</h4>
	                </div>
	           		<div class="modal-body">
				  		<button onclick="openAddDeliberation();" id="add-delib" type="button" class="btn btn-info btn-labeled">Add Deliberation<span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
	           			<br/><br/>
	           			
	           			<div id="divAllDeliberations">
	           				
	           			</div>
		       		</div>
			    </div>
		     </div>
	      </div>
	      
	      <div class="modal fade in" id="divAddDeliberation">
	         <div class="modal-dialog modal-center">
	        	<div class="modal-content">
	                <div class="modal-header">
			            <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close">
			            	<span aria-hidden="true" style="font-size:14px">x</span>
	                    </button>
	                	<h4 class="modal-title">New Deliberation</h4>
	                </div>
	           		<div class="modal-body">
				  		<div class="form-group has-feedback">
                            <label>Deliberation</label>
                            <textarea required class="form-control" id="deliberation-text" placeholder="Enter Deliberation"></textarea>
                        </div>
                        <div class="form-group has-feedback">
                            <label>Person Responsible</label>
                            <div id="select_person_responsible1">
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label>Timeline</label>
                            <div class='input-group date table-adjust custom_datepicker'>
                                <input id="deliberation-timeline" data-date-format="DD/MM/YYYY hh:mm:ss A" type='text' class="form-control" />
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
				  		
				  		<button onclick="createDeliberation();" type="button" class="btn btn-success btn-labeled">Save Deliberation<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
		       		</div>
			    </div>
		     </div>
	      </div>
	    
	    <input type="hidden" id="chosen-meeting-id" value="">
	    <input type="hidden" id="chosen-meeting-deliberation-id" value="">
	    <input type="hidden" id="chosen-meeting-is-creator" value="">
	    
	    <!-- Constants -->
	    <input type="hidden" id="site-url" value="<%= Utility.SITE_URL %>">
	    <input type="hidden" id="select-all" value="<%= Utility.OPTION_SELECT_ALL %>">
	    <input type="hidden" id="select-all-mdax" value="<%= Utility.OPTION_SELECT_ALL_BY_MDAS %>">
	    <input type="hidden" id="selectx" value="<%= Utility.OPTION_SELECT %>">
	    <input type="hidden" id="insertx" value="<%= Utility.OPTION_INSERT %>">
	    <input type="hidden" id="updatex" value="<%= Utility.OPTION_UPDATE %>">
	    <input type="hidden" id="deletex" value="<%= Utility.OPTION_DELETE %>">
	    <input type="hidden" id="insertedx" value="<%= Utility.ActionResponse.INSERTED.toString() %>">
	    <input type="hidden" id="updatedx" value="<%= Utility.ActionResponse.UPDATED.toString() %>">
	    <input type="hidden" id="v5er-idx" value="${sessionScope.userid}">
	    <input type="hidden" id="current-timetable-id" value="${sessionScope.currenttimetableid}">
	       
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
        
        <!-- Date-time picker -->
        <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>

        <!-- ========== ADD custom.js FILE BELOW WITH YOUR CHANGES ========== -->
        <script id="erasable" src="js/bdgaosg-6162726163616461627261/64617368626f617264-00021.js"></script> 
    </body>
</html>
