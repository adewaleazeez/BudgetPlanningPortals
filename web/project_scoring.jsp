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
            <div class="content-wrapper" >
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
                    </div>                    <!-- /.left-sidebar -->
                    <div class="main-page">
                        <div class="container-fluid">
                            <div class="row page-title-div">
                                <div class="col-sm-6">
                                    <h2 class="title">Project Scoring</h2>
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
                                        <li class="active">Project Scoring</li>
                                    </ul>
                                </div>
                                <!-- /.col-sm-6 -->
                                <!-- /.col-sm-6 -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.container-fluid -->
                        <section class="section preparation">
                            <!--***************************************************Page Area START*********************************************************************-->
                            <div id="scoringtab">
                                <div class="row" style="display:inline-block; margin-left:  10px;">
                                    
                                    <div class="col-xs-12">
                                        
                                        <div class="col-xs-12  gv-droppable-grid ui-sortable" data-alignment="Right">
                                            
                                            <div class="row gv-container" id="tab3" data-role="tab" style="display:inline-block; margin-left:  30px;">
                                                <div class="panel-body p-20">
                                                    <div class="col-xs-5">
                                                        <label for="budget_year">Budget Year:</label>
                                                        <select class='js-states form-control custom_select' id='budget_year' disabled></select>
                                                    </div>
                                                    <div class="col-xs-7"></div>
                                                    <br style="clear: both;" />
                                                    <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
    <!--                                                    <li class=""><a href="#tabContent4" data-toggle="tab" id="tabLabel4">New Projects</a></li>
                                                        <li class=""><a data-toggle="tab" href="#tabContent5" id="tabLabel5">Existing Projects</a></li>-->
                                                        <li class="active"><a data-toggle="tab" href="#tabContent7" id="tabLabel7">Existing Project Listing</a></li>
                                                        <li class=""><a data-toggle="tab" href="#tabContent6" id="tabLabel6">New Project Listing</a></li>
                                                        <!--<li>&nbsp;&nbsp;&nbsp; <div id="result"></div></li>-->
                                                    </ul>
                                                    <div class="tab-content" >

                                                        <div class="tab-pane" id="tabContent7" name="tabContent7">
                                                            <div class="tab-canvas ui-sortable">
                                                                <div>
                                                                    <h5><b>Existing Projects Listing</b></h5>
                                                                    <div id="OldProjectsTable"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="tab-pane" id="tabContent6" name="tabContent6">
                                                            <div class="tab-canvas ui-sortable">
                                                                <div>
                                                                    <br style="clear: both;" />
                                                                    <div class="col-md-6 left-side">
                                                                        <b>New Projects Listing</b>
                                                                    </div>
                                                                    <div class="col-md-6 right-side">
                                                                        <a class="btn btn-info toggle-code-handle pull-right show-create" onclick="AddNewItem('0')" role="button"><i class="fa fa-plus"> </i>Add New Project</a>
                                                                    </div>
                                                                    <br style="clear: both;" />
                                                                    <br style="clear: both;" />
                                                                    <div id="NewProjectsTable"></div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--***************************************************Page Area END*********************************************************************-->
                            <div class="modal fade in" id="editNewModal">
                                <div class="modal-dialog modal-center modal-md" style="margin-left: 150px">
                                    <div class="modal-content" style="width: 1200px;">
                                        <div class="modal-header">
                                            <button id="xclose" type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="$('#editNewModal').modal('hide');">
                                                <span aria-hidden="true" style="font-size:14px">x</span>
                                            </button>
                                            <h4 class="modal-title" id="modal-title">Add New Project</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" id="txtId" />
                                            <input type="hidden" id="txtYr" />
                                            <div class="tab-pane active" id="tabContent4" name="tabContent4">
                                                <div class="tab-canvas ui-sortable">
                                                    <div class="form-inline addHeader3Form"  id="formaddh3">
                                                        <div class="form-group col-xs-10" >
                                                            <div class="col-xs-10" >
                                                                <div class="col-sm-5"  style="display:inline-block;  margin-left:  10px;margin-top : 15px;">
                                                                    <label  for="txtmda" >MDA</label>
                                                                    <select class='form-control custom_select' id='txtmda'  onchange="OnMDAChange()"></select>
                                                                </div>
                                                                <div class="col-sm-5" style="display:inline-block;  margin-left:  10px;margin-top : 15px;">
                                                                    <label   for="txtdepartment" >Department</label>
                                                                    <select class='form-control custom_select ' id='txtdepartment' ></select>
                                                                </div>
                                                                <div  class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                    <label  for="txtsectorpolicy" >Policy Group</label>
                                                                    <select  class='form-control custom_select ' id='txtpolicygroup'  onchange="onPolicyGroupChange();"></select>
                                                                </div>
                                                                <div  class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                    <label  for="txtsectorpolicy" >Project Policy</label>
                                                                    <select  class='form-control custom_select ' id='txtsectorpolicy'></select>
                                                                </div>
                                                                <div class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                    <label  for="txtprogrmame" >Programme</label>
                                                                    <select class='form-control custom_select' id="txtprogrmame" onchange="onProgrammeChange();"></select>
                                                                </div>
                                                                <div  class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                    <label  for="txtobjective" >Project Objective</label>
                                                                    <select  class='form-control custom_select ' id='txtobjective'></select>
                                                                </div>
                                                                <div class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                    <label   for="txtsectorgoal" >Sector Goal</label>
                                                                    <select class='form-control custom_select ' id="txtsectorgoal"></select>
                                                                </div>
                                                                <div class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                    <label  for="txtsectorprogramme" >Sector Programme</label>
                                                                    <select class='form-control custom_select ' id="txtsectorprogramme"></select>
                                                                </div>
                                                                <div class="col-sm-10" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                    <label  for="txtprojectname" >Project Name</label>
                                                                    <input type="tt"  class="form-control w-100" id="txtprojectname" />
                                                                </div>
                                                                <div  class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;"> 
                                                                    <label>Project Code : </label>
                                                                    <label id="ProjectCode" class="text-primary">000000</label>
                                                                </div>
                                                                <div  class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;"> 
                                                                    <label>Project Status : </label>
                                                                    <select class='form-control custom_select ' id="projectstatus">
                                                                        <option value="1">Active<option>
                                                                        <option value="0">Disabled<option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-2 list-group-item-success" >
                                                            <h4> Project Score</h4>
                                                            <div style ="margin-left :10px">Score : <span id="txtscore">0.00</span></div>
                                                            <div style ="margin-left: 10px"> Rank : <span id="txtrank">0</span></div>
                                                            <!--button class="btn btn-warning" onclick="Scorethis();" type="button">Compute Score</button-->
                                                            <hr>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="tab-canvas ui-sortable">

                                                    <div id="scoringform">

                                                        <div id="criteria5" style="display:inline-block; margin-top:25px;">
                                                            <h5>Criteria Five: How critical is this project to the achievement of the Sector's goals under the Vision 20:2020, or Caring Heart Policy, etc? </h5>  </div>
                                                        <div style="margin-left: 50px;"> 
                                                            <div class="radio">  <label>    <input type="radio" name="criteria5" id="optionsRadios1" value="4" onchange="checkkk('txtscore');" ><Strong>Vital - </Strong>Goal cannot be achieved otherwise</label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria5" id="optionsRadios1" value="3" onchange="checkkk('txtscore');"  ><Strong>Important -</Strong>  The project will make a substantial and measurable contribution to achieving the Goal </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria5" id="optionsRadios1" value="2" onchange="checkkk('txtscore');" ><Strong>Moderately - </Strong>This project will make some contribution to achieving the goal</label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria5" id="optionsRadios1" value="1" onchange="checkkk('txtscore');"  ><Strong>Limited - </Strong>the Project will make no significant contribution to achieving the goal</label></div>
                                                        </div>
                                                        <input id="_score" type="hidden" value="0.00">
                                                        <button class="btn btn-success" onclick="SaveScore('txtscore');" id="action-button" type="button">Score Project</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal fade in" id="editOldModal">
                                <div class="modal-dialog modal-center modal-md" style="margin-left: 150px">
                                    <div class="modal-content" style="width: 1200px;">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-izimodal-close="" onclick="$('#editOldModal').modal('hide');">&times;</button>
                                            <h4 class="modal-title" id="modal-title-ex">Edit Project</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" id="txtIdEx" />
                                            <input type="hidden" id="txtYrEx" />
                                            <div class="tab-pane"   id="tabContent5" name="tabContent5">
                                                <div style="margin-left: 50px;" class="tab-canvas ui-sortable">
                                                    <div class="tab-canvas ui-sortable">
                                                        <div class="form-inline addHeader3Form"  id="formaddh3Ex">
                                                            <div class="form-group col-xs-10" >
                                                                <div class="col-xs-10" >
                                                                    <div class="col-sm-5"  style="display:inline-block;  margin-left:  10px;margin-top : 15px;">
                                                                        <label  for="txtmdaEx" >MDA</label>
                                                                        <select class='form-control custom_select' id='txtmdaEx'  onchange="OnMDAChangeEx()"></select>
                                                                    </div>
                                                                    <div class="col-sm-5" style="display:inline-block;  margin-left:  10px;margin-top : 15px;">
                                                                        <label   for="txtdepartmentEx" >Department</label>
                                                                        <select class='form-control custom_select ' id='txtdepartmentEx' ></select>
                                                                    </div>
                                                                    <div  class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                        <label  for="txtsectorpolicyEx" >Policy Group</label>
                                                                        <select  class='form-control custom_select ' id='txtpolicygroupEx'  onchange="onPolicyGroupChangeEx();"></select>
                                                                    </div>
                                                                    <div  class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                        <label  for="txtsectorpolicyEx" >Project Policy</label>
                                                                        <select  class='form-control custom_select ' id='txtsectorpolicyEx'></select>
                                                                    </div>
                                                                    <div class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                        <label  for="txtprogrmameEx" >Programme</label>
                                                                        <select class='form-control custom_select' id="txtprogrmameEx" onchange="onProgrammeChangeEx();"></select>
                                                                    </div>
                                                                    <div  class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                        <label  for="txtobjectiveEx" >Project Objective</label>
                                                                        <select  class='form-control custom_select ' id='txtobjectiveEx'></select>
                                                                    </div>
                                                                    <div class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                        <label   for="txtsectorgoalEx" >Sector Goal</label>
                                                                        <select class='form-control custom_select ' id="txtsectorgoalEx"></select>
                                                                    </div>
                                                                    <div class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                        <label  for="txtsectorprogrammeEx" >Sector Programme</label>
                                                                        <select class='form-control custom_select ' id="txtsectorprogrammeEx"></select>
                                                                    </div>
                                                                    <div class="col-sm-10" style="display:inline-block; margin-left:  10px;margin-top : 15px;">
                                                                        <label  for="txtprojectnameEx" >Project Name</label>
                                                                        <input type="text"  class="form-control w-100" id="txtprojectnameEx" />
                                                                    </div>
                                                                    <div  class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;"> 
                                                                        <label>Project Code : </label>
                                                                        <label id="ProjectCodeEx" class="text-primary">000000</label>
                                                                    </div>
                                                                    <div  class="col-sm-5" style="display:inline-block; margin-left:  10px;margin-top : 15px;"> 
                                                                        <label>Project Status : </label>
                                                                        <select class='form-control custom_select ' id="projectstatusEx">
                                                                            <option value="1">Active<option>
                                                                            <option value="0">Disabled<option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-group col-xs-2 list-group-item-success">
                                                                <h4> Project Score</h4>
                                                                <div style ="margin-left :10px">Score : <span id="txtscoreEx">0.00</span></div>
                                                                <div style ="margin-left: 10px"> Rank : <span id="txtrankEx">0</span></div>
                                                                <!--button class="btn btn-warning" onclick="Scorethis();" type="button">Compute Score</button-->
                                                                <hr>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div>
                                                        <div id="criteria1" style="display:inline-block; margin-top:25px;">
                                                            <strong>Criteria One: Does the budget commitment correspond to an ongoing?</strong>
                                                        </div>
                                                        <div style="margin-left: 50px;"> 
                                                            <div class="radio">  <label>    <input type="radio" name="criteria1" id="optionsRadios1" value="4" onchange="checkkk('txtscoreEx');" ><Strong>Abundant</Strong> and convincing evidence that project is onging (e.g Exco approval; contract awards; details of contractor(s); detailed project work plan with deliverables; milestones and targets; engineering design; cost revision; contract variation; implementation progress reports; etc).  </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria1" id="optionsRadios1" value="3" onchange="checkkk('txtscoreEx');" ><Strong>Sufficient</Strong> and convincing evidence that project is ongoing.   </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria1" id="optionsRadios1" value="2" onchange="checkkk('txtscoreEx');" ><Strong>Some</Strong> evidence or moderate evidence that project is ongoing.   </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria1" id="optionsRadios1" value="1" onchange="checkkk('txtscoreEx');" ><Strong>No</Strong> substantial evidence that project is ongoing.   </label></div>
                                                        </div>

                                                        <div id="criteria2" >
                                                            <h5>Criteria Two: How well can the Sector account for the level of funds currently allocated to the Budget commitment?</h5>
                                                        </div>
                                                        <div style="margin-left: 50px;">
                                                            <div class="radio">  <label>    <input type="radio" name="criteria2" id="optionsRadios1" value="4" onchange="checkkk('txtscoreEx');" ><Strong>Very Well</Strong> - All cost components can be clearly identified and a strong argument presented for all cost   </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria2" id="optionsRadios1" value="3" onchange="checkkk('txtscoreEx');" ><Strong>Well</Strong> - The cost componets can be clearly indentified,although not all can be fully justied as necessary   </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria2" id="optionsRadios1" value="2" onchange="checkkk('txtscoreEx');" ><Strong>Moderately</Strong>- Some but not all of the cost componets can be indentified,with limited justification   </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria2" id="optionsRadios1" value="1" onchange="checkkk('txtscoreEx');" ><Strong>Not at all</Strong> - The cost components can be neither indentified nor can these be justified.   </label></div>
                                                        </div>

                                                        <div id="criteria3">
                                                            <h5>Criteria Three:  What are the Tangible Positive Impacts of the Budget Commitment?</h5>
                                                        </div>
                                                        <div style="margin-left: 50px;">  
                                                            <div class="radio">  <label>    <input type="radio" name="criteria3" id="optionsRadios1" value="4"  onchange="checkkk('txtscoreEx');"  ><Strong>Abundant</Strong> and convincing evidence of substantial positive impact from existing commitments  </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria3" id="optionsRadios1" value="3"  onchange="checkkk('txtscoreEx');" ><Strong>Sufficient</Strong> and convincing evidence of moderate positive impact  </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria3" id="optionsRadios1" value="2"  onchange="checkkk('txtscoreEx');" ><Strong>Some</Strong> evidence of moderate positive impact   </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria3" id="optionsRadios1" value="1"  onchange="checkkk('txtscoreEx');" ><Strong>No</Strong> substantial evidence of positive impact   </label></div>
                                                        </div>

                                                        <div id="criteria4">
                                                            <h5>Criteria Four: How well can the MDA justify that the current budget commitment and planned future spending will complete the project, and run the project post completion? This should be based on the contract awarded and the data collected.</h5>  
                                                        </div>
                                                        <div style="margin-left: 50px;"> 
                                                            <div class="radio">  <label>    <input type="radio" name="criteria4" id="optionsRadios1" value="4" onchange="checkkk('txtscoreEx');" ><Strong>All</Strong> evidence suggests that the project will be completed with the budgeted funds and that future runinig costs have been fully taken into account  </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria4" id="optionsRadios1" value="3" onchange="checkkk('txtscoreEx');"  ><Strong>MDA can show</Strong> taht the project is likely to be completed with budgeted funds future runinig costs have been adequately considered   </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria4" id="optionsRadios1" value="2" onchange="checkkk('txtscoreEx');" ><Strong>MDA can show</Strong> that budgeted funds will allow for substantial progress but not completion and future runining costs can be identified   </label></div>
                                                            <div class="radio">  <label>    <input type="radio" name="criteria4" id="optionsRadios1" value="1" onchange="checkkk('txtscoreEx');" ><Strong>Not at all</Strong> - allocated funds will not allow for substantial progress nor can future runining costs be adequately indentified   </label></div>
                                                        </div>
                                                        <input id="_score" type="hidden" value="0.00">
                                                        <button class="btn btn-success" onclick="SaveScore('txtscoreEx');" type="button">Score Project</button>
                                                        <!--<button class="btn btn-success" onclick="SaveScore(4)" type="button">Save</button>-->
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
        <!--<script src="js/jquery/jquery.min.js"></script>-->
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
        <script >
            checkLogin();
            $(document).ready(function () {
                $("#txtmdaEx, #txtdepartmentEx, #txtpolicygroupEx, #txtsectorpolicyEx, #txtprogrmameEx, #txtobjectiveEx, #txtsectorgoalEx, #txtsectorprogrammeEx").select2();
                getBudgetYear();
                getProjectDetails();
                
                getMdas();
                getPolicyGroups();
                getProgrammes();
                getSectorGoals();
                getSectorProgrammes();
                
                $('#tabLabel4, #tabLabel5').click(function () {
                    //event.preventDefault();
                    //$('#ProjectCodeEx').html("");
                    //$('#ProjectCode').html("");
                    $('#txtscore').html("");
                    $('#txtscoreEx').html("");
                    $('#txtrank').html("");
                    $('#txtrankEx').html("");
                    
                });


                $('input[name="criteria1"]').attr('checked', false);
                $('input[name="criteria2"]').attr('checked', false);
                $('input[name="criteria3"]').attr('checked', false);
                $('input[name="criteria4"]').attr('checked', false);
                $('input[name="criteria5"]').attr('checked', false);
                
                
            });
            var _budgetyear;
            var _currentyear;
            
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

                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }
                            if (attrName === "0") {
                                resp += "<option value='" + attrValue + "' selected>";
                                _budgetyear = attrValue;
                            }
                            if (attrName === "1") {
                                resp += attrValue + "</option>";
                            }
                            if (attrName === "2") {
                                _currentyear = attrValue;
                            }
                            
                        }
                    }
                    $('#budget_year').html(resp);
                }
                
            };

            function getProjectDetails() {
                ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/ProjectDetailServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: projectDetailsReturnValues,
                    error: function () {
                        StopLoading();
                        toastr["error"]("Project Detail Selection Failed!", "No record selected!");
                    }
                });
                StopLoading();
            }
            function projectDetailsReturnValues(data) {
            //console.log(data);
                StopLoading();
                var table_new = "<table class='table table-clean table-striped' ><tbody><tr><td><b>S/No</b></td><td><b>Code</b></td><td><b>Name</b></td><td><b>Year</b></td><td><b>Score</b></td><td><b>Rank</b></td><td><b>Status</b></td><td colspan='2'><b>Action</b></td></tr>";
                var table_old = "<table class='table table-clean table-striped' ><tbody><tr><td><b>S/No</b></td><td><b>Code</b></td><td><b>Name</b></td><td><b>Year</b></td><td><b>Score</b></td><td><b>Rank</b></td><td><b>Status</b></td><td colspan='2'><b>Action</b></td></tr>";
                //var resp = "";
                var value = {};
                var count_a=0;
                var count_b=0;
                for (var i = 0; i < data.length; i++) {
                    var obj = data[i];
                    for (var key in obj) {
                        var attrName = key;
                        var attrValue = obj[key];
                        if (attrValue === null || attrValue === 'null') {
                            attrValue = "";
                        }
                        if (attrName === "0") {
                            value.id = attrValue;
                        }
//                        if (attrName === "3") {
//                            value.code = attrValue;
//                        }
                        if (attrName === "6") {
                            value.project_rank = attrValue;
                        }
                        if (attrName === "9") {
                            value.project_score = attrValue;
                        }
                        if (attrName === "10") {
                            value.name = attrValue;
                        }
//                        if (attrName === "13") {
//                            value.code = attrValue + value.code;
//                        }
                        if (attrName === "12") {
                            value.project_status = ((attrValue===1) ? "Acitve" : "Disabled");
                        }
//                        if (attrName === "14") {
//                            value.code = attrValue + value.code;
//                        }
                        if (attrName === "15") {
                            value.code = obj[13] + obj[14] + obj[3] + obj[15]; //value.code + attrValue;
                        }
                        if (attrName === "17") {
                            value.year_id = attrValue;
                        }
                        
                    }
                    //resp += "<option value='" + value.id + "'>" + value.name + "</option>";
                    if (value.year_id >= _currentyear) {
                        table_new += "<tr><td>" + (++count_a) + "</td>";
                        table_new += "<td>" + value.code + "</td>";
                        table_new += "<td>" + value.name + "</td>";
                        table_new += "<td>" + value.year_id + "</td>";
                        table_new += "<td>" + parseFloat(value.project_score).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</td>";
                        table_new += "<td>" + value.project_rank + "</td>";
                        table_new += "<td>" + value.project_status + "</td>";
                        table_new += "<td><a class='btn btn-success toggle-code-handle pull-left newpolicy' onclick=EditNewItem('" + value.id + "','E') role='button'><i class='fa fa-pencil'></i>Edit</a>  ";
                        table_new += "&nbsp;&nbsp;<a class='btn btn-warning toggle-code-handle pull-right newpolicy' onclick=EditNewItem('" + value.id + "','D') role='button'><i class='fa fa-remove'></i>Del</a></td>";
                        table_new += "</tr>";
                    } else {
                        table_old += "<tr><td>" + (++count_b) + "</td>";
                        table_old += "<td>" + value.code + "</td>";
                        table_old += "<td>" + value.name + "</td>";
                        table_old += "<td>" + value.year_id + "</td>";
                        table_old += "<td>" + parseFloat(value.project_score).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</td>";
                        table_old += "<td>" + value.project_rank + "</td>";
                        table_old += "<td>" + value.project_status + "</td>";
                        table_old += "<td><a class='btn btn-success toggle-code-handle pull-right newpolicy' onclick=EditOldItem('" + value.id + "','1') role='button'><i class='fa fa-pencil'></i>Edit</a></td>";
                        table_old += "</tr>";
                    }
                }
                table_new += "</tbody></table>";
                table_old += "</tbody></table>";
                //alert(table_old);
                //alert(document.getElementById("OldProjectsTable").innerHTML);
                //document.getElementById("NewProjectsTable").innerHTML = table_new;
                //document.getElementById("OldProjectsTable").innerHTML = table_old;
                $('#NewProjectsTable').html(table_new);
                $('#OldProjectsTable').html(table_old);
                $('#tabLabel6').trigger('click');
                $('#tabLabel7').trigger('click');
                
//                $('#txtobjective').html("<option disabled value='0' class=''>Select an Objective</option>" + resp);
//                $('#txtobjective').val(0).trigger('change');
//                $('#txtobjectiveEx').html("<option disabled value='0' class=''>Select an Objective</option>" + resp);
//                $('#txtobjectiveEx').val(0).trigger('change');
                StopLoading();
                

            }

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
                    $('#txtmda').html("<option value='0'></option>" + resp);
                    $('#txtmdaEx').html("<option value='0'></option>" + resp);
                }else{
                    $('#txtmda').html("<option value='0'></option>");
                    $('#txtmdaEx').html("<option value='0'></option>");
                }
                getDepartments("txtmdaEx");
            }
            
            function OnMDAChange() {
                getDepartments("txtmda");
            }
            function OnMDAChangeEx() {
                getDepartments("txtmdaEx");
            }
            function getDepartments(arg) {
                var mda = $("#"+arg).val();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/DepartmentServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>", MDA: mda},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: departmentReturnValues,
                    error: function (jqXHR, textStatus, errorThrown) {
                        toastr["error"]("No record selected!", "Departments Selection Failed!!!  ");
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
                }else{
                    $('#txtdepartment').html("<option value='0'></option>");
                    $('#txtdepartmentEx').html("<option value='0'></option>");
                }
                
            }
            
            function onPolicyGroupChange() {
                getPolicies("txtpolicygroup");
            }
            function onPolicyGroupChangeEx() {
                getPolicies("txtpolicygroupEx");
            }
            function getPolicyGroups() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/PolicyGroupServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: PolicyGroupsReturnValues,
                    error: function () {
                        toastr["error"]("No record selected!", "Policy Group Selection Failed!!!");
                    }
                });
            }
            function PolicyGroupsReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var code="";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }
                            if (attrName === "1") {
                                resp += "<option value='" + attrValue + "'>";
                                code = attrValue;
                            }
                            if (attrName === "2") {
                                resp += "[" + code+"] "+attrValue + "</option>";
                            }
                        }
                    }
                    $('#txtpolicygroup').html("<option value='00'></option>" + resp);
                    $('#txtpolicygroupEx').html("<option value='00'></option>" + resp);
                }else{
                    $('#txtpolicygroup').html("<option value='00'></option>");
                    $('#txtpolicygroupEx').html("<option value='00'></option>");
                }
                getPolicies("txtpolicygroupEx");
            }
            
            function getPolicies(arg) {
                var group_code = $("#"+arg).val();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/PolicyServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>", group_code: group_code},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: popPolicies,
                    error: function () {
                        //StopLoading();
                        toastr["error"]("Policy Selection Failed!", "No record selected!");
                    }
                });
            }
            function popPolicies(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var code="";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }
                            if (attrName === "1") {
                                code = attrValue;
                            }
                            if (attrName === "7") {
                                resp += "<option value='" + attrValue + "'>";
                                resp += "[" + attrValue +"] "+ code + "</option>";
                            }
                        }
                    }
                    $('#txtsectorpolicy').html("<option value='00'></option>" + resp);//("<option value='0'>Select Policy</option>" + resp);
                    $('#txtsectorpolicyEx').html("<option value='00'></option>" + resp);//.html("<option value='0'>Select Policy</option>" + resp);
                }else{
                    $('#txtsectorpolicy').html("<option value='00'></option>");
                    $('#txtsectorpolicyEx').html("<option value='00'></option>");
                }
                
            };
            
            function onProgrammeChange() {
                getObjectives("txtprogrmame");
            }
            function onProgrammeChangeEx() {
                getObjectives("txtprogrmameEx");
            }
            function getProgrammes() {
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/ProgrammeServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: ProgrammesReturnValues,
                    error: function () {
                        toastr["error"]("No record selected!", "Programmes Selection Failed!!!");
                    }
                });
            }
            function ProgrammesReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var code="";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }
                            if (attrName === "1") {
                                resp += "<option value='" + attrValue + "'>";
                                code = attrValue;
                            }
                            if (attrName === "2") {
                                resp += "[" + code + "] "+attrValue + "</option>";
                            }
                        }
                    }
                    $('#txtprogrmame').html("<option value='00'></option>" + resp);
                    $('#txtprogrmameEx').html("<option value='00'></option>" + resp);
                }else{
                    $('#txtprogrmame').html("<option value='00'></option>");
                    $('#txtprogrmameEx').html("<option value='00'></option>");
                }
                getObjectives("txtprogrmameEx");
            }
            
            function getObjectives(arg) {
                var programme = $("#"+arg).val();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/ObjectiveServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL%>", programme: programme},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: popObjectives,
                    error: function () {
                        toastr["error"]("Objective Selection Failed!", "No record selected!");
                    }
                });
            }
            function popObjectives(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var code="";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }
                            if (attrName === "2") {
                                resp += "<option value='" + attrValue + "'>";
                                code = attrValue;
                            }
                            if (attrName === "3") {
                                resp += "[" + code + "] "+attrValue + "</option>";
                            }
                        }
                    }
                    $('#txtobjective').html("<option value='00'></option>" + resp);
                    $('#txtobjectiveEx').html("<option value='00'></option>" + resp);
                }else{
                    $('#txtobjective').html("<option value='00'></option>");
                    $('#txtobjectiveEx').html("<option value='00'></option>");
                }
                
            };
            
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
                        var code="";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }
                            if (attrName === "1") {
                                resp += "<option value='" + attrValue + "'>";
                                code = attrValue;
                            }
                            if (attrName === "2") {
                                resp += "[" + code+"] "+attrValue + "</option>";
                            }
                        }
                    }
                    $('#txtsectorgoal').html("<option value='00'></option>" + resp);
                    $('#txtsectorgoalEx').html("<option value='00'></option>" + resp);
                }else{
                    $('#txtsectorgoal').html("<option value='00'></option>");
                    $('#txtsectorgoalEx').html("<option value='00'></option>");
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
                        toastr["error"]("Sector Programme Selection Failed!", "No record selected!");
                    }
                });
            }
            function SectorProgrammesReturnValues(data) {
                if (data.length > 0) {
                    var resp = "";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var code="";
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if (attrValue === null || attrValue === 'null') {
                                attrValue = "";
                            }
                            if (attrName === "1") {
                                resp += "<option value='" + attrValue + "'>";
                                code = attrValue;
                            }
                            if (attrName === "2") {
                                resp += "[" + code+"] "+attrValue + "</option>";
                            }
                        }
                    }
                    $('#txtsectorprogramme').html("<option value='00'></option>" + resp);
                    $('#txtsectorprogrammeEx').html("<option value='00'></option>" + resp);
                }else{
                    $('#txtsectorprogramme').html("<option value='00'></option>");
                    $('#txtsectorprogrammeEx').html("<option value='00'></option>");
                }
            }
            
            var j1 = 0;
            var j2 = 0;
            var j3 = 0;
            var j4 = 0;
            var j5 = 0;
            var score = 0;
            function checkkk(arg) {
                
                if(arg==="txtscoreEx"){
                    j1 = parseInt($('input:radio[name=criteria1]:checked').val());
                    j2 = parseInt($('input:radio[name=criteria2]:checked').val());
                    j3 = parseInt($('input:radio[name=criteria3]:checked').val());
                    j4 = parseInt($('input:radio[name=criteria4]:checked').val());
                }
                if(arg==="txtscore"){
                    j5 = parseInt($('input:radio[name=criteria5]:checked').val());
                }
                if(isNaN(j1)) j1 = 0;
                if(isNaN(j2)) j2 = 0;
                if(isNaN(j3)) j3 = 0;
                if(isNaN(j4)) j4 = 0;
                if(isNaN(j5)) j5 = 0;
                score = (j1 + j2 + j3 + j4 + j5);
                if(arg==="txtscoreEx"){
                    if (score > 0) score = score / 4;
                }
                $('#'+arg).html(score);
                $('#result').html(score);
            }
    
            EditOldItem = function (arg) {
                ShowLoading();
                $('#editOldModal').modal('show');
                $("#modal-title-ex").text("Edit Old Project");
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/ProjectDetailServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: arg},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            var dept="";
                            var policy="";
                            var programme="";
                            var objective="";
                            var sectorgoal="";
                            var sectorprogramme="";
                            var projectcode="";
                            for (var i = 0; i < data.length; i++) {
                                var obj = data[i];
                                for (var key in obj) {
                                    var attrName = key;
                                    var attrValue = obj[key];
                                    if (attrValue === null || attrValue === 'null') {
                                        attrValue = "";
                                    }
                                    if (attrName === "0") {
                                        $("#txtIdEx").val(attrValue);
                                    }
                                    if (attrName === "1") {
                                        $('#txtmdaEx').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "2") {
                                        dept = attrValue;
                                    }
                                    if (attrName === "17") {
                                        $('#txtpolicygroupEx').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "11") {
                                        $('#txtYrEx').val(attrValue);
                                    }
                                    if (attrName === "13") {
                                        policy = attrValue;
                                    }
                                    if (attrName === "14") {
                                        programme = attrValue;
                                    }
                                    if (attrName === "15") {
                                        objective = attrValue;
                                    }
                                    if (attrName === "4") {
                                        sectorgoal = attrValue;
                                    }
                                    if (attrName === "5") {
                                        sectorprogramme = attrValue;
                                    }
                                    if (attrName === "12") {
                                        $('#projectstatusEx').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "10") {
                                        $('#txtprojectnameEx').val(attrValue);
                                    }
                                    if (attrName === "6") {
                                        $('#txtrankEx').html(attrValue);
                                    }
                                    if (attrName === "3") {
                                        projectcode = attrValue;
                                        
                                    }
                                }
                            }
                        }
                        $('#txtprogrmameEx').val(programme).trigger('change');
                        $('#txtdepartmentEx').val(dept).trigger('change');
                        $('#txtsectorpolicyEx').val(policy).trigger('change');
                        $('#txtobjectiveEx').val(objective).trigger('change');
                        $('#txtsectorgoalEx').val(sectorgoal).trigger('change');
                        $('#txtsectorprogrammeEx').val(sectorprogramme).trigger('change');
                        $('#ProjectCodeEx').html(projectcode+" ["+policy+programme+projectcode+objective+"]");
                        
                        $('#txtmdaEx').attr('disabled', true);
                        $('#txtpolicygroupEx').attr('disabled', true);
                        //$('#projectstatusEx').attr('disabled', true);
                        $('#txtprojectnameEx').attr('disabled', true);
                        $('#txtprogrmameEx').attr('disabled', true);
                        $('#txtdepartmentEx').attr('disabled', true);
                        $('#txtsectorpolicyEx').attr('disabled', true);
                        $('#txtobjectiveEx').attr('disabled', true);
                        $('#txtsectorgoalEx').attr('disabled', true);
                        $('#txtsectorprogrammeEx').attr('disabled', true);
                        //$('input[name="criteria1]').attr('disabled', true);
                        //$('input[name="criteria2"]').attr('disabled', true);
                        //$('input[name="criteria3"]').attr('disabled', true);
                        //$('input[name="criteria4"]').attr('disabled', true);
                       
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/ScoringServlet",
                            data: {option: "<%= Utility.OPTION_SELECT_ALL%>", project_code: policy+programme+projectcode+objective, project_year: $('#txtYrEx').val()},
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: function (data) {
//console.log(data);
                                if (data.length > 0) {
                                    score = 0;
                                    for (var i = 0; i < data.length; i++) {
                                        var obj = data[i];
                                        score += parseInt(obj[4]);
                                        if(i===0) $("input:radio[name=criteria1][value="+obj[4]+"]").click();
                                        if(i===1) $("input:radio[name=criteria2][value="+obj[4]+"]").click();
                                        if(i===2) $("input:radio[name=criteria3][value="+obj[4]+"]").click();
                                        if(i===3) $("input:radio[name=criteria4][value="+obj[4]+"]").click();
                                    }
                                    score /= 4;
                                    toastr["success"]("Record Selection Successful!", "Record Selected!!!");
                                    
                                } else {
                                    //toastr["error"]("Record Selection Failed!", "No Record Selected!!!");
                                    $('input[name="criteria1"]').attr('checked', false);
                                    $('input[name="criteria2"]').attr('checked', false);
                                    $('input[name="criteria3"]').attr('checked', false);
                                    $('input[name="criteria4"]').attr('checked', false);
                                    $('input[name="criteria5"]').attr('checked', false);
                                }
                            },
                            error: function () {
                                $('#editOldModal').modal('hide');
                                toastr["error"]("Server not accessible!", "Failed!!!");
                            }
                        });
                    },
                    error: function () {
                        toastr["error"]("Server not accessible!", "Failed!!!");
                    }
                });
                StopLoading();
            };
            
            EditNewItem = function (arg, arg2) {
                ShowLoading();
                $('#editNewModal').modal('show');
                $("#modal-title").text("Edit New Project");
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/ProjectDetailServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: arg},
                    dataType: "json",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            var dept="";
                            var policy="";
                            var programme="";
                            var objective="";
                            var sectorgoal="";
                            var sectorprogramme="";
                            var projectcode="";
                            for (var i = 0; i < data.length; i++) {
                                var obj = data[i];
                                for (var key in obj) {
                                    var attrName = key;
                                    var attrValue = obj[key];
                                    if (attrValue === null || attrValue === 'null') {
                                        attrValue = "";
                                    }
                                    if (attrName === "0") {
                                        $("#txtId").val(attrValue);
                                    }
                                    if (attrName === "1") {
                                        $('#txtmda').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "2") {
                                        dept = attrValue;
                                    }
                                    if (attrName === "17") {
                                        $('#txtpolicygroup').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "11") {
                                        $('#txtYr').val(attrValue);
                                    }
                                    if (attrName === "13") {
                                        policy = attrValue;
                                    }
                                    if (attrName === "14") {
                                        programme = attrValue;
                                    }
                                    if (attrName === "15") {
                                        objective = attrValue;
                                    }
                                    if (attrName === "4") {
                                        sectorgoal = attrValue;
                                    }
                                    if (attrName === "5") {
                                        sectorprogramme = attrValue;
                                    }
                                    if (attrName === "12") {
                                        $('#projectstatus').val(attrValue).trigger('change');
                                    }
                                    if (attrName === "10") {
                                        $('#txtprojectname').val(attrValue);
                                    }
                                    if (attrName === "6") {
                                        $('#txtrank').html(attrValue);
                                    }
                                    if (attrName === "3") {
                                        projectcode = attrValue;
                                        
                                    }
                                }
                            }
                        }
                        $('#txtprogrmame').val(programme).trigger('change');
                        $('#txtdepartment').val(dept).trigger('change');
                        $('#txtsectorpolicy').val(policy).trigger('change');
                        $('#txtobjective').val(objective).trigger('change');
                        $('#txtsectorgoal').val(sectorgoal).trigger('change');
                        $('#txtsectorprogramme').val(sectorprogramme).trigger('change');
                        $('#ProjectCode').html(projectcode+" ["+policy+programme+projectcode+objective+"]");
                        $.ajax({
                            type: "GET",
                            url: "<%= Utility.SITE_URL%>/ScoringServlet",
                            data: {option: "<%= Utility.OPTION_SELECT_ALL%>", project_code: policy+programme+projectcode+objective, project_year: $('#txtYr').val()},
                            dataType: "json",
                            cache: false,
                            async: false,
                            success: function (data) {
                                if (data.length > 0) {
                                    for (var i = 0; i < data.length; i++) {
                                        var obj = data[i];
                                        if(i===0) $("input:radio[name=criteria5][value="+obj[4]+"]").click();
                                    }
                                    toastr["success"]("Record Selection Successful!", "Record Selected!!!");
                                    
                                } else {
                                    //toastr["error"]("Record Selection Failed!", "No Record Selected!!!");
                                    $('input[name="criteria1"]').attr('checked', false);
                                    $('input[name="criteria2"]').attr('checked', false);
                                    $('input[name="criteria3"]').attr('checked', false);
                                    $('input[name="criteria4"]').attr('checked', false);
                                    $('input[name="criteria5"]').attr('checked', false);
                                }
                            },
                            error: function () {
                                $('#editNewModal').modal('hide');
                                toastr["error"]("Server not accessible!", "Failed!!!");
                            }
                        });
                    },
                    error: function () {
                        toastr["error"]("Server not accessible!", "Failed!!!");
                    }
                });
                if(arg2==="D"){
                    $("#modal-title").text("Delete Project");
                    $("#action-button").text("Delete Project");
                    $('#txtmda').attr('disabled', true);
                    $('#txtpolicygroup').attr('disabled', true);
                    $('#projectstatus').attr('disabled', true);
                    $('#txtprojectname').attr('disabled', true);
                    $('#txtprogrmame').attr('disabled', true);
                    $('#txtdepartment').attr('disabled', true);
                    $('#txtsectorpolicy').attr('disabled', true);
                    $('#txtobjective').attr('disabled', true);
                    $('#txtsectorgoal').attr('disabled', true);
                    $('#txtsectorprogramme').attr('disabled', true);
                    $('input[name="criteria5"]').attr('disabled', true);
                }else{
                    $("#modal-title").text("Edit Project");
                    $("#action-button").text("Score Project");
                    $('#txtmda').attr('disabled', false);
                    $('#txtpolicygroup').attr('disabled', false);
                    $('#projectstatus').attr('disabled', false);
                    $('#txtprojectname').attr('disabled', false);
                    $('#txtprogrmame').attr('disabled', false);
                    $('#txtdepartment').attr('disabled', false);
                    $('#txtsectorpolicy').attr('disabled', false);
                    $('#txtobjective').attr('disabled', false);
                    $('#txtsectorgoal').attr('disabled', false);
                    $('#txtsectorprogramme').attr('disabled', false);
                    $('input[name="criteria5"]').attr('disabled', false);
                }
                StopLoading();
            };

            AddNewItem = function (arg) {
                $('#editNewModal').modal('show');
                $("#modal-title").text("Add New Project");
                
                $("#txtscore").val("");
                $("#txtrank").val("");
                
                $("#txtId").val(arg);
                $('#txtmda').val("0").trigger('change');
                $('#txtpolicygroup').val("00").trigger('change');
                $('#txtYr').val("");
                $('#projectstatus').val("1");
                $('#txtprojectname').val("");
                $('#txtprogrmame').val("00").trigger('change');
                $('#txtdepartment').val("0").trigger('change');
                $('#txtsectorpolicy').val("00").trigger('change');
                $('#txtobjective').val("00").trigger('change');
                $('#txtsectorgoal').val("00").trigger('change');
                $('#txtsectorprogramme').val("00").trigger('change');
                $('#ProjectCode').html("000000 [00000000000000]");
                
                $('input[name="criteria1"]').attr('checked', false);
                $('input[name="criteria2"]').attr('checked', false);
                $('input[name="criteria3"]').attr('checked', false);
                $('input[name="criteria4"]').attr('checked', false);
                $('input[name="criteria5"]').attr('checked', false);
                
                $('#txtmda').attr('disabled', false);
                $('#txtpolicygroup').attr('disabled', false);
                $('#projectstatus').attr('disabled', false);
                $('#txtprojectname').attr('disabled', false);
                $('#txtprogrmame').attr('disabled', false);
                $('#txtdepartment').attr('disabled', false);
                $('#txtsectorpolicy').attr('disabled', false);
                $('#txtobjective').attr('disabled', false);
                $('#txtsectorgoal').attr('disabled', false);
                $('#txtsectorprogramme').attr('disabled', false);
                $('input[name="criteria5"]').attr('disabled', false);
            };

            function SaveScore(arg) {
                var id = "";
                var mda = "";
                var department = "";
                var code = "";
                var sector_goal = "";
                var sector_programme = "";
                var rank = "";
                var name = "";
                var year_id = "";
                var project_status = "";
                var policy = "";
                var programme = "";
                var objective = "";
                var project_code = "";
                if(arg==='txtscoreEx'){
                    id = $("#txtIdEx").val();
                    mda = $("#txtmdaEx").val();
                    department = $("#txtdepartmentEx").val();
                    code = $("#ProjectCodeEx").html();
                    code = code.split(" ");
                    code = code[0];
                    sector_goal = $("#txtsectorgoalEx").val();
                    sector_programme = $("#txtsectorprogrammeEx").val();
                    rank = 0;
                    name = $("#txtprojectnameEx").val();
                    year_id = $("#txtYrEx").val();
                    project_status = $("#projectstatusEx").val();
                    policy = $("#txtsectorpolicyEx").val();
                    programme = $("#txtprogrmameEx").val();
                    objective = $("#txtobjectiveEx").val();
                }else{
                    id = $("#txtId").val();
                    mda = $("#txtmda").val();
                    department = $("#txtdepartment").val();
                    code = $("#ProjectCode").html();
                    code = code.split(" ");
                    code = code[0];
                    sector_goal = $("#txtsectorgoal").val();
                    sector_programme = $("#txtsectorprogramme").val();
                    rank = 0;
                    name = $("#txtprojectname").val();
                    year_id = $("#txtYr").val();
                    project_status = $("#projectstatus").val();
                    policy = $("#txtsectorpolicy").val();
                    programme = $("#txtprogrmame").val();
                    objective = $("#txtobjective").val();
                }
                if(year_id===null || year_id===""){
                    year_id = $("#budget_year").val();
                }
                project_code = policy+programme+code+objective;
                var action;
                if($("#action-button").text()==="Delete Project"){
                    action = "<%= Utility.OPTION_DELETE%>";
                }else{
                    action = "<%= Utility.OPTION_UPDATE%>";
                }
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/ProjectDetailServlet",
                    data: {option: action, id: id, mda: mda, department: department, code: code, sector_goal: sector_goal, 
                        sector_programme: sector_programme, rank: rank, score: score, name: name, year_id: year_id, project_status: project_status, 
                        policy: policy, programme: programme, objective: objective, project_code: project_code},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.indexOf("<%= Utility.ActionResponse.UPDATED.toString()%>") !== -1 || 
                                data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {
                            code = policy + programme + code + objective;
                            //var project_year = $("#budget_year").val();
                            if(data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1){
                                code = data.split("<%= Utility.ActionResponse.INSERTED.toString()%>");
                                code = code[1].trim();
                            }
                            //alert(project_code+"    "+code);
                            $.ajax({
                                type: "GET",
                                url: "<%= Utility.SITE_URL%>/ScoringServlet",
                                data: {option: "<%= Utility.OPTION_UPDATE%>", project_code: code, project_year: year_id, project_type: arg, score1: j1, score2: j2, score3: j3, score4: j4, score5: j5},
                                dataType: "text",
                                cache: false,
                                async: false,
                                success: function (data) {
                                    if (data.indexOf("<%= Utility.ActionResponse.UPDATED.toString()%>") !== -1) {
                                        toastr["success"]("Project Update Successful!", "Record successfully updated!");
                                    } else if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1){
                                        toastr["success"]("Project Insertion Successful!", "Record successfully inserted!");
                                    } else {
                                        toastr["error"]("Project does exist", "No Record Found!");
                                    }
                                },
                                error: function (a, b, c) {
                                    StopLoading();
                                    toastr["error"]("Failed!", "Server Not Accessible!");
                                }
                            });
                        } else if (data.indexOf("<%= Utility.ActionResponse.DELETED.toString()%>") !== -1){
                            toastr["success"]("Project deleted successfully", "Record successfully deleted!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.RECORD_EXISTS.toString()%>") !== -1){
                            toastr["error"]("Project already exists for another MDA", "Record Exists!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.NO_RECORD.toString()%>") !== -1){
                            toastr["error"]("Project does exist", "No Record Found!");
                        }
                    },
                    error: function (a, b, c) {
                        StopLoading();
                        toastr["error"]("Failed!", "Server Not Accessible!");
                    }
                });
                getProjectDetails();
                if(arg==='txtscoreEx'){
                    $('#editOldModal').modal('hide');
                }else{
                    $('#editNewModal').modal('hide');
                    $('#tabLabel6').trigger('click');
                }
                
            }
                        
//checkkk();//Update scores
//txtIdEx txtmdaEx txtdepartmentEx txtpolicygroupEx txtsectorpolicyEx txtprogrmameEx
//txtobjectiveEx txtsectorgoalEx txtsectorprogrammeEx txtprojectnameEx ProjectCodeEx projectstatusEx

//[id] ,[mda] ,[department] ,[code] ,[sector_goal] ,[sector_programme] ,[rank] ,[date_created] ,[org_id]
//      ,[score] ,[name] ,[year_id] ,[project_status] ,[policy] ,[programme] ,[objective]
          

        </script>
    </body>
</html>

