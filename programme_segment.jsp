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
                    <div class="main-page">
                        <div class="container-fluid">
                            <div class="row page-title-div">
                                <div class="col-sm-6">
                                    <h2 class="title">Programme Segments</h2>
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
                                        <li class="active">Programme Segments</li>
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
                                                            <label>Programmes</label>
                                                            <div id="addSegmentH1"></div>
                                                        </div>

                                                        <div class="form-group has-feedback">
                                                            <label>Projects</label>
                                                            <div id="addSegmentH2"></div>

                                                        </div>

                                                        <div class="form-group has-feedback">
                                                            <label>Objectives</label>
                                                            <div id="addSegmentH3"></div>

                                                        </div>


                                                        <div class="form-group has-feedback">
                                                            <label>Name</label>
                                                            <input type="text" class="form-control" id="name_addSegment" name="name" placeholder="name">
                                                        </div>

                                                        <div class="form-group has-feedback">
                                                            <label>Code</label>
                                                            <input type="text" class="form-control" id="code_addSegment" name="name" placeholder="code">
                                                        </div>
                                                        <button onclick="saveProgrammeSegment()" type="button" class="btn btn-success btn-labeled pull-left">Save Programme Segment<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                        <button onclick="ReturnToList()" type="button" class="btn btn-danger btn-labeled pull-right">Cancel<span class="btn-label btn-label-right"><i class="fa fa-close"></i></span></button>
                                                    </form>
                                                </div>

                                                <!-- Nav tabs -->
                                                <div id="list-area">
                                                    <div class="col-md-12">
                                                        <form class="col-md-12" style="margin-top: 30px; margin-bottom: 30px; ">

                                                            <!--                                                        <div class="form-group has-feedback">
                                                                                                                        <label>Programme Segment</label>
                                                                                                                        <div id="select_programmeSegment1"></div>
                                                                                                                        
                                                                                                                    </div>-->
                                                            <div class="form-group H1SelContainer col-xs-12" style="margin : 10px 0;">
                                                                <label for="selFSHeader1" style="margin : 0 12px;">Programmes</label>
                                                                <div id="selFSHeader1" style="margin : 0 12px" class="col-xs-5"></div> 
                                                                <div class="col-xs-6">
                                                                    <button onclick="manageProgrammeSegmentHeader1()" type="button" style="margin : 0 12px" class="btn btn-info btn-labeled ">
                                                                        Manage Programmes<span class="btn-label btn-label-right"><i class="fa fa-pencil"></i></span></button>
                                                                </div>
                                                            </div>

                                                            <div class="form-group hidden H2SelContainer col-xs-12" style="margin : 10px 0;">
                                                                <label for="selFSHeader2" style="margin : 0 12px;">Projects</label>
                                                                <div id="selFSHeader2" style="margin : 0 12px" class="col-xs-5"></div> 
                                                                <div class="col-xs-6">
                                                                    <button onclick="manageProgrammeSegmentHeader2()" type="button" style="margin : 0 12px" class="btn btn-info btn-labeled ">Manage Projects<span class="btn-label btn-label-right"><i class="fa fa-pencil"></i></span></button>
                                                                </div>
                                                            </div>

                                                            <div class="form-group hidden H3SelContainer col-xs-12" style="margin : 10px 0;">
                                                                <label for="selFSHeader3" style="margin : 0 12px;">Objectives</label>
                                                                <div id="selFSHeader3" style="margin : 0 12px" class="col-xs-5"></div> 
                                                                <div class="col-xs-6">
                                                                    <button onclick="manageProgrammeSegmentHeader3()" type="button" style="margin : 0 12px" class="btn btn-info btn-labeled ">
                                                                        Manage Objectives<span class="btn-label btn-label-right"><i class="fa fa-pencil"></i></span></button>
                                                                </div>
                                                            </div>




                                                        </form>
                                                    </div>
                                                    <div class="col-md-12 right-side">
                                                        <a class="btn btn-info toggle-code-handle pull-right" onclick="addSegment()" role="button"><i class="fa fa-plus"> </i>New Programme Segment</a>
                                                    </div>
                                                    <br style="clear: both;" />
                                                    <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                        <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true"> Programme Segments</a></li>
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
                                                    <h5>Edit Programme Segment</h5>
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
                                                    <button onclick="FinishEdit()" type="button" class="btn btn-success btn-labeled pull-left">Save Programme Segment<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                    &nbsp; <button onclick="cancelEdit()" type="button" class="btn btn-danger btn-labeled pull-right">Cancel Edit<span class="btn-label btn-label-right"><i class="fa fa-close"></i></span></button>
                                                </form>
                                                <br style="clear: both;" />
                                            </div>
                                        </div>

                                        <div class="panel is-hidden" id="addH1Section">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <!--h4>Add Programme</h4-->
                                                </div>
                                            </div>
                                            <div class="panel-body p-20">

                                                <div class="col-xs-12">
                                                    <form class="form-inline addHeader1Form" id="formaddh1">
                                                        <h4>Add Programme</h4>
                                                        <div class="form-group col-xs-12" >  
                                                            <label class="col-md-4 " >Code</label>
                                                            <label class="col-md-4" >Name</label>
                                                        </div>
                                                        <div class="form-group col-xs-12" >  
                                                            <input disabled class="col-md-4 " type='text' id='code_addh1' placeholder='Code'style="display:inline-block; margin-right : 10px;"/>
                                                            <input class="col-md-4" type='text' id='name_addh1' placeholder='Name' style="display:inline-block; margin-right : 10px; "/>
                                                            <button type="button"  class="col-md-1 btn btn-danger" style=" width: 35px;" onclick="getNewProgrammeCode();" ><span class="btn-label "><i class="fa fa-plus"></i></span></button>
                                                        </div>
                                                    </form>

                                                    <form class="form-inline hidden editHeader1Form" id="formedith1">
                                                        <h4>Edit Programme</h4>
                                                        <div class="form-group col-xs-12" >  
                                                            <label class="col-md-4 " >Code</label>
                                                            <label class="col-md-4" >Name</label>
                                                        </div>
                                                        <div class="form-group col-xs-12" >  
                                                            <input disabled class="col-md-4 " type='text' id='code_edith1' placeholder='Code' style="display:inline-block; margin-right : 10px;"/>
                                                            <input class="col-md-4" type='text' id='name_edith1' placeholder='Name' style="display:inline-block; margin-right : 10px; "/>
                                                        </div>
                                                    </form>

                                                    <div class="col-xs-12">
                                                        <button onclick="FinishHeader1Edit()" type="button" id='editHeader1Btn' class="btn hidden btn-success">Save Header <span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>
                                                        <button onclick="FinishAddHeader1()" type="button" id='addHeader1Btn' class="btn btn-success ">Save <span class="btn-label btn-label-right"><i class="fa fa-Save"></i></span></button>
                                                        <button type="button" class="btn btn-warning hidden" data-dismiss="modal"  id="cancelEditH1Btn" onclick="CancelHeader1Edit();">Exit Edit</button>
                                                    </div>
                                                </div>                                                
                                                <br style="clear: both;" /><p> </p>
                                                <hr>
                                                <button type="button" class="btn btn-danger" data-dismiss="modal" data-izimodal-close="" id="cancelManageH1Btnb" onclick="CancelManageHeader1();">Return</button>
                                                <h4>Manage Programmes</h4>
                                                <div>
                                                    <div id="FSHeader1_table"></div>
                                                </div>
                                                <div>

                                                    <button type="button" class="btn btn-warning " data-dismiss="modal" data-izimodal-close="" id="cancelManageH1Btn" onclick="CancelManageHeader1();">Cancel</button>
                                                </div>
                                            </div>

                                        </div>
                                        <!--/manage header 2-->
                                        <div class="panel is-hidden" id="addH2Section">
                                            <!--div class="panel-heading">
                                                <div class="panel-title">
                                                    <h4>Add Project</h4>
                                                </div>
                                            </div-->
                                            <div class="panel-body p-20">

                                                <div class="col-xs-12">
                                                    <form class="form-inline addHeader2Form"  id="formaddh2">
                                                        <h4>Add Project</h4>
                                                        <div class="form-group col-xs-12" >  
                                                            <label class="col-md-4 " >Code</label>
                                                            <label class="col-md-4" >Name</label>
                                                        </div>
                                                        <div class="form-group col-xs-12" >   
                                                            <input disabled class="col-md-4" type='text' id='code_addh2' placeholder='Code'style="display:inline-block; margin-right : 10px;"/>                                                          
                                                            <input class="col-md-4" type='text' id='name_addh2' placeholder='Name' style="display:inline-block; margin-right : 10px; "/>                                                            
                                                            <button type="button"  class="col-md-1 btn btn-danger" style=" width: 35px;" onclick="getNewProjectCode();" ><span class="btn-label "><i class="fa fa-plus"></i></span></button>
                                                        </div>
                                                    </form>

                                                    <form class="form-inline hidden editHeader2Form" id="formedith2">
                                                        <h4>Edit Project</h4>
                                                        <div class="form-group col-xs-12" >  
                                                            <label class="col-md-4 " >Code</label>
                                                            <label class="col-md-4" >Name</label>
                                                        </div>
                                                        <div class="form-group col-xs-12" > 
                                                            <input disabled class="col-md-4"  type='text' id='code_edith2' placeholder='Code' style="display:inline-block; margin-right : 10px;"/>
                                                            <input class="col-md-4"  type='text' id='name_edith2' placeholder='Name' style="display:inline-block; margin-right : 10px;"/>
                                                        </div>
                                                    </form>

                                                    <div class="col-xs-12">
                                                        <button onclick="FinishHeader2Edit()" type="button" id='editHeader2Btn' class="btn hidden btn-success">Save <span class="btn-label btn-label-right"><i class="fa fa-plus"></i></span></button>                                                        
                                                        <button onclick="FinishAddHeader2()" type="button" id='addHeader2Btn' class="btn btn-success">Save <span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>                                                        
                                                        <button type="button" class="btn btn-warning hidden" data-dismiss="modal"  id="cancelEditH2Btn" onclick="CancelHeader2Edit();">Exit Edit</button>

                                                    </div>
                                                </div>
                                                <br style="clear: both;" />
                                                <hr>
                                                <button type="button" class="btn btn-danger " data-dismiss="modal" data-izimodal-close="" id="cancelManageH2Btnb" onclick="CancelManageHeader2();">Return</button>
                                                <h4>Manage Projects</h4>
                                                <div>
                                                    <div id="FSHeader2_table"></div>
                                                </div>
                                                <div>

                                                    <button type="button" class="btn btn-warning" data-dismiss="modal" data-izimodal-close="" id="cancelManageH2Btn" onclick="CancelManageHeader2();">Cancel</button>
                                                </div>
                                            </div>

                                        </div>
                                        <!--/manage header 2 -->

                                        <div class="panel is-hidden" id="addH3Section">
                                            <!--div class="panel-heading">
                                                <div class="panel-title">
                                                    <h4>Add Objective</h4>
                                                </div>
                                            </div-->
                                            <div class="panel-body p-20">

                                                <div class="col-xs-12">
                                                    <form class="form-inline addHeader3Form"  id="formaddh3">
                                                        <div class="form-group col-xs-12" >  

                                                            <h4>Add Objective</h4>
                                                            <div class="form-group col-xs-12" >  
                                                                <label class="col-md-4 " >Code</label>
                                                                <label class="col-md-4" >Name</label>
                                                            </div>
                                                            <div class="form-group col-xs-12" >  
                                                                <input disabled class="col-sm-4" type='text' id='code_addh3' placeholder='Code' style="display:inline-block; margin-right : 10px; "/>
                                                                <input class="col-sm-4" type='text' id='name_addh3' placeholder='Name'style="display:inline-block; margin-right : 10px;"/>
                                                                <button type="button"  class="col-md-1 btn btn-danger" style=" width: 35px;" onclick="getNewObjectiveCode();" ><span class="btn-label "><i class="fa fa-plus"></i></span></button>
                                                            </div>                                                            
                                                            <div class="col-sm-4"  style="display:inline-block;  margin-right : 10px;margin-top : 15px;"><label  for="mda_addh3" >MDA</label><select class='form-control custom_select' id='mda_addh3' onchange='getDepartments();'></select></div>
                                                            <div class="col-sm-4" style="display:inline-block;  margin-right : 10px;margin-top : 15px;"><label   for="department_addh3" >Department</label><select class='form-control custom_select ' id='department_addh3' ></select></div>
                                                            <div class="col-sm-4" style="display:inline-block; margin-right : 10px;margin-top : 15px;"><label   for="sectorprogramme_addh3" >Sector Programme</label><select class='form-control custom_select ' id="sectorprogramme_addh3"><</select></div>
                                                            <div class="col-sm-4" style="display:inline-block; margin-right : 10px;margin-top : 15px;"><label  for="sectorgoal_addh3" >Sector Goal</label><select class='form-control custom_select ' id="sectorgoal_addh3"></select></div>                                                            
                                                            <div class="col-sm-4" style="display:inline-block; margin-right : 10px;margin-top : 15px;" ><label  for="project_addh3" >Project</label><select class='form-control custom_select ' id="project_addh3"><</select></div>
                                                            <div  class="col-sm-4" style="display:inline-block; margin-right : 10px;margin-top : 15px;"><label  for="budgetyear_addh3" >Budget Year</label><select disabled class='form-control custom_select ' id='budgetyear_addh3' ><</select></div>

                                                            <br>
                                                            <div class="checkbox col-sm-4" style="display:inline-block; margin-right : 10px;margin-top : 25px;margin-bottom : 35px;"><label id="projectstatus_addh3">Project Status<input type="checkbox"></label></div>                                                            

                                                        </div>
                                                        <button onclick="FinishAddHeader3()" type="button" id='addHeader3Btn' class="btn btn-success "> Save<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                    </form>

                                                    <form class="form-inline hidden editHeader3Form" id="formedith3">
                                                        <h4>Edit Objective</h4>
                                                        <div class="form-group col-xs-12" >  
                                                            <div class="form-group col-xs-12" >  
                                                                <label class="col-md-4 " >Code</label>
                                                                <label class="col-md-4" >Name</label>
                                                            </div>
                                                            <div class="form-group col-xs-12" >  
                                                                <input disabled class="col-sm-4" type='text' id='code_edith3' placeholder='Code' style="display:inline-block; margin-right : 10px;"/>
                                                                <input class="col-sm-4" type='text' id='name_edith3' placeholder='Name' style="display:inline-block; margin-right : 10px;"/>
                                                            </div>

                                                            <div class="col-sm-4" style="display:inline-block;  margin-right : 10px;margin-top : 15px;"><label  for="mda_edith3" >MDA</label><select class='form-control custom_select' id='mda_edith3' onchange='getDepartments();'></select></div>
                                                            <div class="col-sm-4" style="display:inline-block;  margin-right : 10px;margin-top : 15px;"><label   for="department_edith3" >Department</label><select class='form-control custom_select ' id='department_edith3' ></select></div>
                                                            <div class="col-sm-4" style="display:inline-block; margin-right : 10px;margin-top : 15px;"><label   for="sectorprogramme_edith3" >Sector Programme</label><select class='form-control custom_select ' id="sectorprogramme_edith3"><</select></div>
                                                            <div class="col-sm-4" style="display:inline-block; margin-right : 10px;margin-top : 15px;"><label  for="sectorgoal_edith3" >Sector Goal</label><select class='form-control custom_select ' id="sectorgoal_edith3"></select></div>
                                                            <div  class="col-sm-4" style="display:inline-block; margin-right : 10px;margin-top : 15px;" ><label  for="project_edith3" >Project</label><select  class=' form-control  custom_select js-states' id="project_edith3"></select></div>
                                                            <div  class="col-sm-4" style="display:inline-block; margin-right : 10px;margin-top : 15px;"><label  for="budgetyear_edith3" >Budget Year</label><select disabled class='form-control custom_select col-sm-4' id='budgetyear_edith3' ><</select></div>

                                                            <br>
                                                            <div class="checkbox col-sm-4" style="display:inline-block; margin-right : 10px;margin-top : 25px;margin-bottom : 35px;"><label id="projectstatus_edith3">Project Status<input id="project_status_edith3" type="checkbox"></label></div>                                                            

                                                        </div>
                                                    </form>

                                                    <div class="col-xs-12">
                                                        <button onclick="FinishHeader3Edit()" type="button" id='editHeader3Btn' class="btn hidden btn-success "> Save <span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>                                                        
                                                        <button type="button" class="btn btn-warning  hidden" data-dismiss="modal"  id="cancelEditH3Btn" onclick="CancelHeader3Edit();">Exit Edit</button>

                                                    </div>
                                                </div>
                                                <br style="clear: both;" />
                                                <hr>
                                                <button type="button" class="btn btn-danger " data-dismiss="modal" data-izimodal-close="" id="cancelManageH2Btnbx" onclick="CancelManageHeader3();">Return</button>
                                                <h4>Manage Objectives</h4>
                                                <div>
                                                    <div id="FSHeader3_table"></div>
                                                </div>
                                                <div>

                                                    <button type="button" class="btn btn-warning pull-right" data-dismiss="modal" data-izimodal-close="" id="cancelManageH3Btn" onclick="CancelManageHeader3();">Cancel</button>
                                                </div>
                                            </div>

                                        </div>
                                        <!--/manage header 3 -->
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

<script type="text/javascript">
    checkLogin();
    CancelManageHeader2();
    $('#deleteModal').iziModal();
    $('#manageHeader1Modal').iziModal();
    $('.is-modal').iziModal();

    $('.is-hidden').hide();


    var H2 = null;// H2 Parent Id
    var H3 = null; //H3 Paraent Id
    var NewH3Code = ''; //New H3 Code
    var H2c = null;// H1 Code
    var H3c = null; //
    var NewH2Code = '';// New H2 Code
    var NewH1Code = '';//

    getProgrammeSegmentHeader1();
    getMdas();
    getDepartments();
    getSectorGoals();
    getBudgetYears();
    getSectorProgrammes();

    function getProgrammeSegments() {
        let parent = Window.selHeader3;
        if (!parent) {
            getProgrammeSegmentHeader1();
            return;
        }
        ShowLoading();
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_SELECT_ALL%>", entity: "ProgrammeSegment", parent: parent},
            dataType: "json",
            success: programmeSegmentReturnValues,
            error: function () {
                StopLoading();
                toastr["error"]("Programme Segment Select Failed!", "No record selected!");
            }
        });
    }

    function programmeSegmentReturnValues(data) {
        StopLoading();
//                 if(data.length>0){
        var resp = "<table class='table table-clean table-striped' ><tbody><tr><td>Name</td><td>Code</td><td></td> </tr>";
        for (var i = 0; i < data.length; i++) {
            var obj = data[i];
            var recId = 0;
            for (var key in obj) {
                var attrName = key;
                var attrValue = obj[key];
                attrValue = " " + attrValue;
                if (attrName === "0") {
                    resp += "<tr >";
                    recId = attrValue;
                }
                if (attrName === "1") {
                    resp += "<td class='line-height-30 w-25'><small><b>" + attrValue + "</b></small></td>";
                }
                if (attrName === "2") {
                    resp += "<td class='line-height-30 w-25'><small><b>" + attrValue + "</b></small></td>";
                    resp += "<td class='line-height-30 w-50'><button type='button' onclick='EditItem(" + recId + ")' class='btn btn-info'><i class='fa fa-edit'></i>Edit</button>";
                    resp += " <button type='button' class='btn btn-danger' onclick='DeleteItem(" + recId + ")'><i class='fa fa-remove'></i>Delete</button></td></tr>";
                    resp += "";
                }

            }
        }
        resp += "</tbody></table>";
        document.getElementById("active").innerHTML = resp;

//                }

    }

    function getProgrammeSegmentHeader1() {
        ShowLoading();
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_SELECT_ALL%>", entity: "ProgrammeSegmentHeader1"},
            dataType: "json",
            success: programmeSegmentHeader1ReturnValues,
            error: function (a, b, c) {
                StopLoading();
                toastr["error"]("Programme Segment Select Failed!", "No record selected!");
            }
        });
    }

    function programmeSegmentHeader1ReturnValues(data) {
        StopLoading();

        var table_resp = "<table class='table table-clean table-striped' ><tbody><tr><td>Code</td><td>Name</td><td></td> </tr>";
        var resp = "";
        var value = {};
        var programmecode = 0;
        for (var i = 0; i < data.length; i++) {
            var obj = data[i];

            for (var key in obj) {
                var attrName = key;
                var attrValue = obj[key];
                if (attrValue === null || attrValue === 'null') {
                    attrValue = "";
                }
                if (attrName === "1") {
                    value.name = attrValue;
                }
                if (attrName === "2") {
                    value.code = attrValue;
                    if (programmecode < parseInt(attrValue))
                        programmecode = parseInt(attrValue);
                }
                if (attrName === "0") {
                    value.id = attrValue;
                }
            }
            table_resp += "<tr> <td >" + value.code + "</td>";
            table_resp += "<td >" + value.name + "</td>";
            table_resp += "<td ><button type='button' onclick='EditHeader1(" + value.id + ")' class='btn btn-info'><i class='fa fa-edit'></i>Edit</button>";
            table_resp += " <button type='button' class='btn btn-danger' onclick='DeleteHeader1(" + value.id + ")'><i class='fa fa-remove'></i>Delete</button></td>";
            table_resp += "</tr>";

            resp += "<option value='" + value.id + "'>" + value.name + "</option>";
        }
        programmecode = programmecode + 1;

        if (('' + programmecode).length === 1)
            NewH1Code = +'0' + programmecode;
        else
            NewH1Code = +'0' + programmecode;
        table_resp += "</tbody></table>";
        resp += "</select>";
        $('#selFSHeader1').html("<select class='js-states form-control' onchange='setHeader1(this.value);getProgrammeSegmentHeader2();' id='selFSHeader1_set'><option value='0'  class=''>Please select a Programme</option>" + resp);
        document.getElementById("FSHeader1_table").innerHTML = table_resp;
        $('#Header1_addH2').html("<select class='js-states form-control' onchange='setHeader1(this.value);getProgrammeSegmentHeader2();' id='selHeader1_addH2'><option value='0' class=''>Please select a Programme</option>" + resp);
        $('#addSegmentH1').html("<select class='js-states form-control' id='sel_addSegmentH1' disabled><option class=''>Please select a Programme</option>" + resp);
//                  $('#selMda3').html("<select class='js-states form-control' id='selMda_s' onchange='getDepartments();setMdas(this)'><option class=''>Please select MDA</option>"+resp);
        StopLoading();

    }

    function setHeader1(value) {
        Window.selHeader1 = value;
        H2 = value;
        $('#sel_addSegmentH1')[0].value = value;
//                $('#selHeader1_addH2')[0].value  = value;
        $('#selFSHeader1_set')[0].value = value;

    }

    function setHeader2(value) {
        Window.selHeader2 = value;
        H3 = value;
        $('#sel_addSegmentH2')[0].value = value;
        var id = document.getElementById("sel_addSegmentH1").value;
        $("#project_edith3").val(id).trigger('change');

    }

    function setHeader3(value) {
        Window.selHeader3 = value;
        $('#sel_addSegmentH3')[0].value = value;
        var prj = document.getElementById("sel_addSegmentH2").innerHTML;
        var id = document.getElementById("sel_addSegmentH2").value;


        var md = readCookie('formtype');
        /*
         document.getElementById("project_addh3").innerHTML = prj;
         document.getElementById("project_addh3").value = id;
         document.getElementById("project_edith3").innerHTML = prj;
         document.getElementById("project_edith3").value = id;*/
    }

    function getProgrammeSegmentHeader2() {
        ShowLoading();
        parent_id = Window.selHeader1;
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_SELECT_ALL%>", entity: "ProgrammeSegmentHeader2", parent: parent_id},
            dataType: "json",
            success: programmeSegmentHeader2ReturnValues,
            error: function () {
                StopLoading();
                toastr["error"]("Programme Segment Header Select Failed!", "No record selected!");
            }
        });
    }

    function programmeSegmentHeader2ReturnValues(data) {
        StopLoading();
        var table_resp_h2 = "<table class='table table-clean table-striped' ><tbody><tr><td>Code</td><td>Name</td><td></td> </tr>";
        var resp = "";
        var value = {};
        H2c = data[0][2];
        H2c = '' + H2c.substring(0, 2);

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
            table_resp_h2 += "<tr> <td ><small><b>" + value.code + "</b></small></td>";
            table_resp_h2 += "<td ><small><b>" + value.name + "</b></small></td>";
            table_resp_h2 += "<td ><button type='button' onclick='EditHeader2(" + value.id + ")' class='btn btn-info'><i class='fa fa-edit'></i>Edit</button>";
            table_resp_h2 += " <button type='button' class='btn btn-danger' onclick='DeleteHeader2(" + value.id + ")'><i class='fa fa-remove'></i>Delete</button></td>";
            table_resp_h2 += "</tr>";
        }
        resp += "</select>";
        table_resp_h2 += "</tbody></table>";
        $('#selFSHeader2').html("<select class='js-states form-control' onchange='setHeader2(this.value);getProgrammeSegmentHeader3();' id='selFSHeader2_set'><option value='0'  class=''>Please Select a Project</option>" + resp);
        document.getElementById("FSHeader2_table").innerHTML = table_resp_h2;
        $('#addSegmentH2').html("<select class='js-states form-control' id='sel_addSegmentH2' disabled ><option value='0' class=''>Please Select a Project</option>" + resp);
        $('#project_addh3').html("<option class=''></option>" + resp);
        $('#project_edith3').html("<option class=''></option>" + resp);
        $('.H2SelContainer').removeClass('hidden');
        StopLoading();

    }

    function getProgrammeSegmentHeader3() {
        ShowLoading();
        parent_id = Window.selHeader2;
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_SELECT_ALL%>", entity: "ProgrammeSegmentHeader3", parent: parent_id},
            dataType: "json",
            success: programmeSegmentHeader3ReturnValues,
            error: function () {
                StopLoading();
                toastr["error"]("Programme Segment Header Select Failed!", "No record selected!");
            }
        });
    }

    function programmeSegmentHeader3ReturnValues(data) {
        StopLoading();
        H3c = data[0][2];
        //var table_resp_h3 = "<table class='table table-clean table-striped' ><tbody><tr><td>Code</td><td>Name</td><td>Year</td><td>Status</td><td></td> </tr>";
        var table_resp_h3 = "<table class='table table-clean table-striped' ><tbody><tr><td>Code</td><td>Name</td><td>Status</td><td></td> </tr>";
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

            }
            resp += "<option value='" + value.id + "'>" + value.name + "</option>";
            table_resp_h3 += "<tr> <td >" + value.code + "</td>";
            table_resp_h3 += "<td>" + value.name + "</td>";
            if (value.project_status == true)
                table_resp_h3 += "<td>Active</td>";
            else
                table_resp_h3 += "<td>Inactive</td>";
            table_resp_h3 += "<td><button type='button' onclick='EditHeader3(" + value.id + ")' class='btn btn-info'><i class='fa fa-edit'></i>Edit</button>";
            table_resp_h3 += " <button type='button' class='btn btn-danger' onclick='DeleteHeader3(" + value.id + ")'><i class='fa fa-remove'></i>Delete</button></td>";
            table_resp_h3 += "</tr>";
        }
        resp += "</select>";
        table_resp_h3 += "</tbody></table>";
        $('#selFSHeader3').html("<select class='js-states form-control' onchange='setHeader3(this.value);getProgrammeSegments();' id='selFSHeader3_set'><option value='0' class=''>Please Select an Objective</option>" + resp);
        document.getElementById("FSHeader3_table").innerHTML = table_resp_h3;
        $('#addSegmentH3').html("<select class='js-states form-control' id='sel_addSegmentH3' disabled ><option value='0' class=''>Please Select an Objective</option>" + resp);
//                    $('#selMda4').html("<select class='js-states form-control' id='selMda_HODe' readonly disabled><option class=''>Please select MDA</option>"+resp);
//                    $('#selMda3').html("<select class='js-states form-control' id='selMda_s' onchange='getDepartments();setMdas(this)'><option class=''>Please select MDA</option>"+resp);
        $('.H3SelContainer').removeClass('hidden');
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
    function yearReturnValues(data) {
        if (data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                for (var key in obj) {
                    var attrName = key;
                    var attrValue = obj[key];
                    attrValue = " " + attrValue;
                    if (attrName === "1") {
                        return attrValue;
                    }
                }
            }
        }
        return '';
    }

    function getBudgetYears() {
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/BudgetYearServlet",
            data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
            dataType: "json",
            cache: false,
            async: false,
            success: budgetyearsReturnValues,
            error: function () {
                toastr["error"]("No record selected!", "Budget Year Selection Failed!!!");
            }
        });
    }
    ;
    function budgetyearsReturnValues(data) {
        var idx;
        var tmp = 0;
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
                        tmp = attrValue;
                    }
                    if (attrName === "1") {
                        resp += attrValue + "</option>";
                    }
                    if (attrName === "3") {
                        if (attrValue == 1)
                            idx = tmp;
                    }
                }
            }
            $('#budgetyear_addh3').html("<option value='0'></option>" + resp);
            $('#budgetyear_edith3').html(resp);//("<option value='0'></option>" + resp);
        }
        $('#budgetyear_addh3').val(idx).trigger('change');
        $('#budgetyear_edith3').val(idx).trigger('change');
    }
    ;



    function saveProgrammeSegment() {
        let name = document.getElementById("name_addSegment").value;
        let code = document.getElementById("code_addSegment").value;
        let parent = Window.selHeader3;
        if (!parent) {
            toastr["error"]("Please select Header parent!", "Field cannot be empty!");
            return;
        }
        ;
        if (name.length <= 0) {
            toastr["error"]("Please Enter Header name!", "Field cannot be empty!");
            return;
        }
        if (code.length <= 0) {
            toastr["error"]("Please select Header code!", "Field cannot be empty!");
            return;
        }
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_INSERT%>", name: name, parent: parent, code: code, entity: "ProgrammeSegment"},
            dataType: "json",
            success: programmeSegmentInsertReturnValues,
            error: function () {
                toastr["error"]("Programme Segment Insert Failed!", "No record inserted!");
            }
        });
        //
    }

    function programmeSegmentInsertReturnValues(data) {
        document.getElementById("name_addSegment").value = '';
        document.getElementById("code_addSegment").value = '';
        if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {

            toastr["success"]("Programme Segment Insert Successful!", "New record successfully inserted!");
            getProgrammeSegments();
            ReturnToList();
        } else {
            toastr["error"]("Programme Segment with that name already exist", "Duplicate Entry!");
        }
    }

    function EditItem(arg) {
        createCookie("formtype", "editProgrammeSegment");
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: arg, entity: "ProgrammeSegment"},
            dataType: "json",
            cache: false,
            async: false,
            success: function (data) {
                if (data !== null || data !== "null") {
                    data = data[0];
                    document.getElementById("name2").value = data[1];
                    document.getElementById("code2").value = data[2];
                    document.getElementById("updateid").value = data[0];
                }
                toastr["success"]("Programme Segment successfully fetched!", "Programme Segment Successfull!!!");
                $('#MainSection').hide();
                $('#EditSection').fadeIn();
                $('ul.breadcrumb').html('<li><a onclick=gotoLink("/dashboard00012")><i class="fa fa-home"></i> Home</a></li><li onclick="closeEdit()"> Programme Segment</li><li class="active" >Edit Programme Segment</li>');
            }
            ,
            error: function (e, b, c) {
                toastr["error"]("Record with id " + arg + " is not found!", "Programme Segment Select Failed!!!");
            }
        });
    }

    function FinishEdit() {
        let name = document.getElementById("name2").value;
        let code = document.getElementById("code2").value;
        let id = document.getElementById("updateid").value;
        if (name.length <= 0) {
            toastr["error"]("Please Enter Header name!", "Field cannot be empty!");
            return;
        }
        if (code.length <= 0) {
            toastr["error"]("Please select Header code!", "Field cannot be empty!");
            return;
        }
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_UPDATE%>", name: name, code: code, id: id, entity: "ProgrammeSegment"},
            dataType: "json",
            cache: false,
            async: false,
            success: function (data) {
                if (data === '<%= Utility.ActionResponse.UPDATED.toString()%>') {
                    toastr["success"]("Programme Segment update Successfull!", "Update Successfull");
                    cancelEdit();
                }
                if (data === "<%= Utility.ActionResponse.RECORD_EXISTS.toString()%>") {
                    toastr["error"]("Programme Segment with that name already exists", "Update Failed");
                }
            },
            error: function (a, b, c) {
                toastr["error"]("Programme Segment Update Failed!", "No record updated!");
            }
        });
    }

    function cancelEdit() {
        $('#EditSection').hide();
        $('#MainSection').fadeIn();
        getProgrammeSegments();
        document.getElementById("name2").value = '';
        document.getElementById("updateid").value = '';
    }

    function DeleteItem(arg, entity) {
        if (entity === undefined || entity === '')
            entity = "ProgrammeSegment";
        $('#deleteModal').iziModal('open');
        window.deleteId = arg;
        window.deleteEntity = entity;
    }

    function DeleteHeader1(id) {
        DeleteItem(id, "ProgrammeSegmentHeader1");
    }

    function DeleteHeader2(id) {
        DeleteItem(id, "ProgrammeSegmentHeader2");
    }

    function DeleteHeader3(id) {
        DeleteItem(id, "ProgrammeSegmentHeader3");
    }



    function cancelDelete() {
        $('#deleteModal').iziModal('close');
        window.deleteId = 0;
    }

    function FinishDelete() {
        let id = window.deleteId;
        let entity = window.deleteEntity;
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_DELETE%>", id: id, entity: entity},
            dataType: "json",
            cache: false,
            async: false,
            success: function (data) {
                if (data === '<%= Utility.ActionResponse.DELETED.toString()%>') {

                    $('#deleteModal').iziModal('close');
                    toastr["success"]("Programme Segment deleted Successfull!", "Delete Successfull");
                    getProgrammeSegments();
                }
                if (data === '<%= Utility.ActionResponse.NO_RECORD.toString()%>') {
                    $('#deleteModal').iziModal('close');
                    toastr["error"]("Programme Segment delete Failed!", "No record deleted!");
                }
            },
            error: function (a, b, c) {

                toastr["error"]("Programme Segment delete Failed!", "No record deleted!");
            }
        });
    }

    function manageProgrammeSegmentHeader1() {
        cleanInputs();
        $('#EditSection').hide();
        $('#MainSection').hide();
        $('#addH1Section').fadeIn();
    }

    function manageProgrammeSegmentHeader2() {
        cleanInputs();
        $('#EditSection').hide();
        $('#MainSection').hide();
        $('#addH2Section').fadeIn();
    }

    function manageProgrammeSegmentHeader3() {

        // $("#sectorprogramme_edith3").val(0).change();
        // $("#project_edith3").val(0).change();
        $("#sectorprogramme_addh3").val(0).trigger('change');
        //$("#project_addh3").val(0).trigger('change');
//$("#project_edith3").val(0).trigger('change');

        $('#EditSection').hide();
        $('#MainSection').hide();
        $('#addH3Section').fadeIn();
        cleanInputs();
    }

    function CancelManageHeader1() {
        $('#EditSection').hide();
        $('#addH1Section').hide();
        $('#MainSection').fadeIn();
    }

    function CancelManageHeader2() {
        $('#EditSection').hide();
        $('#addH2Section').hide();
        $('#MainSection').fadeIn();
    }

    function CancelManageHeader3() {
        CancelHeader3Edit();
        $('#EditSection').hide();
        $('#addH3Section').hide();
        $('#MainSection').fadeIn();
    }

    function FinishAddHeader1() {
        var name_addh1 = document.getElementById("name_addh1").value;
        var code_addh1 = document.getElementById("code_addh1").value;
        if (name_addh1.length <= 0) {
            toastr["error"]("Please Enter Header name!", "Field cannot be empty!");
            return;
        }
        if (code_addh1.length <= 0) {
            toastr["error"]("Please select Header code!", "Field cannot be empty!");
            return;
        }

        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_INSERT%>", name: name_addh1, code: code_addh1, entity: "ProgrammeSegmentHeader1"},
            dataType: "json",
            success: Header1InsertReturnValues,
            error: function () {
                toastr["error"]("Programme Segment Insert Failed!", "No record inserted!");
            }
        });
    }

    function Header1InsertReturnValues(data) {
        document.getElementById("name_addh1").value = '';
        document.getElementById("code_addh1").value = '';
        if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {
            toastr["success"]("Programmes Insert Successful!", "New record successfully inserted!");
            getProgrammeSegmentHeader1();
            ReturnToList();
        } else {
            toastr["error"]("Programme Segment with that name already exist", "Duplicate Entry!");
        }
    }

    function FinishAddHeader2() {
        let parent = Window.selHeader1;
        var name_addh2 = document.getElementById("name_addh2").value;
        var code_addh2 = document.getElementById("code_addh2").value;
        if (!parent) {
            toastr["error"]("Please select Header parent!", "Field cannot be empty!");
            return;
        }
        ;
        if (name_addh2.length <= 0) {
            toastr["error"]("Please Enter Header name!", "Field cannot be empty!");
            return;
        }
        if (code_addh2.length <= 0) {
            toastr["error"]("Please select Header code!", "Field cannot be empty!");
            return;
        }

        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_INSERT%>", name: name_addh2, code: code_addh2, entity: "ProgrammeSegmentHeader2", parent: parent},
            dataType: "json",
            success: Header2InsertReturnValues,
            error: function () {
                toastr["error"]("Programme Segment Insert Failed!", "No record inserted!");
            }
        });
    }

    function Header2InsertReturnValues(data) {
        document.getElementById("name_addh2").value = '';
        document.getElementById("code_addh2").value = '';
        if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {
            toastr["success"]("Programmes Insert Successful!", "New record successfully inserted!");
            getProgrammeSegmentHeader2();
            ReturnToList();
        } else {
            toastr["error"]("Programme Segment with that name already exist", "Duplicate Entry!");
        }
    }

    function FinishAddHeader3() {
        let parent = Window.selHeader2;
        var name = document.getElementById("name_addh3").value;
        var code = document.getElementById("code_addh3").value;
        var mda = document.getElementById("mda_addh3").value;
        var department = document.getElementById("department_addh3").value;
        var sector_goal = document.getElementById("sectorgoal_addh3").value;
        var year_id = document.getElementById("budgetyear_addh3").value;
        var project_status = document.getElementById("projectstatus_addh3").value;
        var sector_programme = document.getElementById("sectorprogramme_addh3").value;
        var budgetyear = document.getElementById("budgetyear_addh3").value;
        var score = -1;
        var rank = -1;
        //TODO Validate inputs
        if (!parent) {
            toastr["error"]("Please select Header parent!", "Field cannot be empty!");
            return;
        }
        ;
        if (name.length <= 0) {
            toastr["error"]("Please Enter Header name!", "Field cannot be empty!");
            return;
        }
        if (code.length <= 0) {
            toastr["error"]("Please select Header code!", "Field cannot be empty!");
            return;
        }

        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_INSERT%>", name: name, code: code, entity: "ProgrammeSegment", parent: parent, mda: mda, department: department, sector_goal: sector_goal, year_id: year_id, project_status: project_status, score: score, rank: rank, sector_programme: sector_programme, year_id: budgetyear},
            dataType: "json",
            success: Header3InsertReturnValues,
            error: function () {
                toastr["error"]("Programme Objective Insert Failed!", "No record inserted!");
            }
        });
    }

    function Header3InsertReturnValues(data) {
        document.getElementById("name_addh3").value = '';
        document.getElementById("code_addh3").value = '';
        if (data.indexOf("<%= Utility.ActionResponse.INSERTED.toString()%>") !== -1) {
            toastr["success"]("Objective Insert Successful!", "New record successfully inserted!");
            getProgrammeSegmentHeader3();
            ReturnToList();
        } else {
            toastr["error"]("Programme Objective with that name already exist", "Duplicate Entry!");
        }
    }

    function EditHeader1(arg) {
        createCookie("formtype", "editProgrammeSegmentHeader1");
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: arg, entity: "ProgrammeSegmentHeader1"},
            dataType: "json",
            cache: false,
            async: false,
            success: function (data) {
                if (data !== null || data !== "null") {
                    data = data[0];
                    document.getElementById("code_edith1").value = data[2];
                    document.getElementById("name_edith1").value = data[1];
                    Window.updateid_h1 = data[0];
                    $(".addHeader1Form").addClass("hidden");
                    $("#addHeader1Btn").addClass("hidden");
                    $("#cancelEditH1Btn").removeClass("hidden");
                    $("#editHeader1Btn").removeClass("hidden");
                    $(".editHeader1Form").removeClass("hidden");
                }
                toastr["success"]("Programme Segment successfully fetched!", "Programme Segment Successfull!!!");
            }
            ,
            error: function (e, b, c) {
                toastr["error"]("Record with id " + arg + " is not found!", "Programme Segment Select Failed!!!");
            }
        });
    }

    function FinishHeader1Edit() {

        var name = document.getElementById("name_edith1").value;
        var code = document.getElementById("code_edith1").value;
        var id = Window.updateid_h1;
        if (name.length <= 0) {
            toastr["error"]("Please Enter Header name!", "Field cannot be empty!");
            return;
        }
        if (code.length <= 0) {
            toastr["error"]("Please select Header code!", "Field cannot be empty!");
            return;
        }
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_UPDATE%>", name: name, id: id, code: code, entity: "ProgrammeSegmentHeader1"},
            dataType: "json",
            cache: false,
            async: false,
            success: function (data) {
                if (data === '<%= Utility.ActionResponse.UPDATED.toString()%>') {
                    toastr["success"]("Programme Segment update Successfull!", "Update Successfull");
                    CancelHeader1Edit();
                    getProgrammeSegmentHeader1();
                }
                if (data === "<%= Utility.ActionResponse.RECORD_EXISTS.toString()%>") {
                    toastr["error"]("Programme Segment with that name already exists", "Update Failed");
                }
                if (data === "<%= Utility.ActionResponse.NO_RECORD.toString()%>") {
                    toastr["error"]("Programme Segment with id " + id + " doesnt exist", "Update Failed");
                }

            },
            error: function (a, b, c) {
                toastr["error"]("Programme Segment Update Failed!", "No record updated!");
            }
        });
    }

    function CancelHeader1Edit() {
        $(".addHeader1Form").removeClass("hidden");
        $("#cancelManageH1Btn").removeClass("hidden");
        $("#addHeader1Btn").removeClass("hidden");
        $("#editHeader1Btn").addClass("hidden");
        $("#cancelEditH1Btn").addClass("hidden");
        $(".editHeader1Form").addClass("hidden");
    }

    function EditHeader2(arg) {
        createCookie("formtype", "editProgrammeSegmentHeader2");
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: arg, entity: "ProgrammeSegmentHeader2"},
            dataType: "json",
            cache: false,
            async: false,
            success: function (data) {
                if (data !== null || data !== "null") {
                    data = data[0];
                    document.getElementById("code_edith2").value = data[2];
                    document.getElementById("name_edith2").value = data[1];
                    Window.updateid_h2 = data[0];
                    $(".addHeader2Form").addClass("hidden");
                    $("#addHeader2Btn").addClass("hidden");
                    $("#cancelEditH2Btn").removeClass("hidden");
                    $("#editHeader2Btn").removeClass("hidden");
                    $(".editHeader2Form").removeClass("hidden");
                }
                toastr["success"]("Programme Segment successfully fetched!", "Programme Segment Successfull!!!");
            }
            ,
            error: function (e, b, c) {
                toastr["error"]("Record with id " + arg + " is not found!", "Programme Segment Select Failed!!!");
            }
        });
    }

    function FinishHeader2Edit() {

        var name = document.getElementById("name_edith2").value;
        var code = document.getElementById("code_edith2").value;
        var id = Window.updateid_h2;
        if (name.length <= 0) {
            toastr["error"]("Please Enter Header name!", "Field cannot be empty!");
            return;
        }
        if (code.length <= 0) {
            toastr["error"]("Please select Header code!", "Field cannot be empty!");
            return;
        }
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_UPDATE%>", name: name, id: id, code: code, entity: "ProgrammeSegmentHeader2"},
            dataType: "json",
            cache: false,
            async: false,
            success: function (data) {
                if (data === '<%= Utility.ActionResponse.UPDATED.toString()%>') {
                    toastr["success"]("Programme Segment update Successfull!", "Update Successfull");
                    CancelHeader2Edit();
                    getProgrammeSegmentHeader2();
                }
                if (data === "<%= Utility.ActionResponse.RECORD_EXISTS.toString()%>") {
                    toastr["error"]("Programme Segment with that name already exists", "Update Failed");
                }
                if (data === "<%= Utility.ActionResponse.NO_RECORD.toString()%>") {
                    toastr["error"]("Programme Segment with id " + id + " doesnt exist", "Update Failed");
                }

            },
            error: function (a, b, c) {
                toastr["error"]("Programme Segment Update Failed!", "No record updated!");
            }
        });
    }

    function CancelHeader2Edit() {
        $(".addHeader2Form").removeClass("hidden");
        $("#cancelManageH2Btn").removeClass("hidden");
        $("#addHeader2Btn").removeClass("hidden");
        $("#editHeader2Btn").addClass("hidden");
        $("#cancelEditH2Btn").addClass("hidden");
        $(".editHeader2Form").addClass("hidden");
    }

    function EditHeader3(arg) {
        CancelHeader3Edit();
        createCookie("formtype", "editProgrammeSegmentHeader3");
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_SELECT_A_RECORD%>", id: arg, entity: "ProgrammeSegmentHeader3"},
            dataType: "json",
            cache: false,
            async: false,
            success: function (data) {
                if (data !== null || data !== "null") {
                    data = data[0];
                    //console.log(data);
                    document.getElementById("code_edith3").value = data[2];
                    document.getElementById("name_edith3").value = data[1];
                    //document.getElementById("parent_edith3").value = data[3];
                    //var selmda=document.getElementById("mda_edith3");
                    $("#project_edith3").val(data[3]).trigger('change');
                    $("#mda_edith3").val(data[6]).trigger('change');
                    $("#department_edith3").val(data[7]).trigger('change');
                    $("#sectorgoal_edith3").val(data[8]).trigger('change');
                    $("#budgetyear_edith3").val(data[9]).trigger('change');
                    if (data[10] == 1)
                        $("#project_status_edith3").prop('checked', true);
                    else
                        $("#project_status_edith3").prop('checked', false);
                    //$("#project_status_edith3").val(data[10]).trigger('change');
                    $("#sectorprogramme_edith3").val(data[13]).trigger('change');
                    Window.updateid_h3 = data[0];
                    $(".addHeader3Form").addClass("hidden");
                    $("#addHeader3Btn").addClass("hidden");
                    $("#cancelEditH3Btn").removeClass("hidden");
                    $("#editHeader3Btn").removeClass("hidden");
                    $(".editHeader3Form").removeClass("hidden");
                }
                toastr["success"]("Programme Segment successfully fetched!", "Programme Segment Successfull!!!");
            }
            ,
            error: function (e, b, c) {
                toastr["error"]("Record with id " + arg + " is not found!", "Programme Segment Select Failed!!!");
            }
        });
    }

    function FinishHeader3Edit() {

        let name = document.getElementById("name_edith3").value;
        let code = document.getElementById("code_edith3").value;
        var mda = $("#mda_edith3").val();
        var department = $("#department_edith3").val();
        var sectorgoal = $("#sectorgoal_edith3").val();
        var sectorprogramme = $("#sectorprogramme_edith3").val();
        var budgetyear = $("#budgetyear_edith3").val();
        var projectstatus = $("#project_status_edith3").val();
        if (name.length <= 0) {
            toastr["error"]("Please Enter Header name!", "Field cannot be empty!");
            return;
        }
        if (code.length <= 0) {
            toastr["error"]("Please select Header code!", "Field cannot be empty!");
            return;
        }
        let id = Window.updateid_h3;
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_UPDATE%>", name: name, id: id, code: code, mda: mda, department: department, sector_goal: sectorgoal, sector_programme: sectorprogramme, budgetyear: budgetyear, projectstatus: projectstatus, entity: "ProgrammeSegmentHeader3"},
            dataType: "json",
            cache: false,
            async: false,
            success: function (data) {
                if (data === '<%= Utility.ActionResponse.UPDATED.toString()%>') {
                    toastr["success"]("Programme Segment update Successfull!", "Update Successfull");
                    CancelHeader3Edit();
                    getProgrammeSegmentHeader3();
                }
                if (data === "<%= Utility.ActionResponse.RECORD_EXISTS.toString()%>") {
                    toastr["error"]("Programme Segment with that name already exists", "Update Failed");
                }
                if (data === "<%= Utility.ActionResponse.NO_RECORD.toString()%>") {
                    toastr["error"]("Programme Segment with id " + id + " doesnt exist", "Update Failed");
                }

            },
            error: function (a, b, c) {
                toastr["error"]("Programme Segment Update Failed!", "No record updated!");
            }
        });
    }

    function CancelHeader3Edit() {
        $(".addHeader3Form").removeClass("hidden");
        $("#cancelManageH3Btn").removeClass("hidden");
        $("#addHeader3Btn").removeClass("hidden");
        $("#editHeader3Btn").addClass("hidden");
        $("#cancelEditH3Btn").addClass("hidden");
        $(".editHeader3Form").addClass("hidden");
    }


    function addSegment() {
        if (Window.selHeader3 && Window.selHeader2
                && Window.selHeader1 && Window.selHeader3 > 0
                && Window.selHeader2 > 0 && Window.selHeader1 > 0) {
            $('#list-area').hide();
            $('#create-area').fadeIn();
            $('#MainSection .panel-title').fadeIn();
            $('#create-area').show();
        }
        //showCreate();
        else
            toastr["error"]("Please select headers ", "Programme Segment Add Failed!!!");
    }

    function clearSegments() {
        document.getElementById("active").innerHTML = '';
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
    ;
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
            $('#mda_addh3').html("<option value='0'></option>" + resp);
            $('#mda_edith3').html("<option value='0'></option>" + resp);
        }
    }
    ;

    function setDepartment(id) {
        if (id === "mda_addh3") {
            document.getElementById('department_addh3').value = "0";
        }
        if (id === "selMda_e") {
            document.getElementById('selDepartment_e').value = "0";
        }
    }
    ;
    function getDepartments() {
        var selMda = "";
        var md = readCookie('formtype');
        if (md == 'editProgrammeSegmentHeader3')
            selMda = document.getElementById("mda_edith3").value;
        else
            selMda = document.getElementById("mda_addh3").value;
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/DepartmentServlet",
            data: {option: "<%= Utility.OPTION_SELECT_BY_ID%>", mda_id: selMda},
            dataType: "json",
            cache: false,
            async: false,
            success: departmentReturnValues,
            error: function (jqXHR, textStatus, errorThrown) {
                toastr["error"]("No record selected!", "Departments Selection Failed!!!");
            }
        });
    }
    ;
    function departmentReturnValues(data) {
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
            resp += "";
            $('#department_addh3').html("<option value='0'></option>" + resp);
            $('#department_edith3').html("<option value='0'></option>" + resp);

        } else {
            $('#department_addh3').html("<option value='0' >Select Department</option></select>");
            $('#department_edith3').html("<option value='0'></option></select>");
        }
    }
    ;
    function getSectorGoals() {
        // ShowLoading();
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/SectorGoalsServlet",
            data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
            dataType: "json",
            cache: false,
            async: false,
            success: popSectorGoals,
            error: function () {
                StopLoading();
                toastr["error"]("Sector Goals Selection Failed!", "No record selected!");
            }
        });
    }

    function popSectorGoals(data) {
        StopLoading();
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
            $('#sectorgoal_addh3').html("<option value='0'></option>" + resp);
            $('#sectorgoal_edith3').html("<option value='0'></option>" + resp);
        }
    }
    ;
    function getNewProjectCode()
    {
        var parent = H2;
        if (H2 === null)
        {
            toastr["error"]("Unable to generate Code!", "You have to Sectect a Programme first!!!");
        }
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_GET_NEW_CODE%>", parent: parent, entity: "ProgrammeSegmentHeader2"},
            dataType: "text",
            cache: false,
            async: false,
            success: codeReturnValues,
            error: function () {
                toastr["error"]("Unable to generate Code!", "Code Generation Failed!!!");
            }
        });

    }
    ;
    function codeReturnValues(data) {
        var code = data.slice(2, data.length - 4);
        var len = code.length;
        code = parseInt(code) + 1;
        var newcode = '' + code;
        newcode = '' + H2c + newcode.padStart(len, '0');
        NewH2Code = newcode;

        $('#code_addh2').val(newcode);
        // $('#code_edith2').val(newcode);
        ;
    }
    ;

    function getNewProgrammeCode()
    {
        if (NewH1Code !== null)
        {
            $('#code_addh1').val(NewH1Code);
            //$('#code_edith1').val(NewH1Code);
        } else
        {
            toastr["error"]("Unable to generate Code!", "");
        }

    }
    ;


    function getNewObjectiveCode()
    {
        var parent = H3;
        if (H3 === null)
        {
            toastr["error"]("Unable to generate Code!", "You have to Sectect a Project first!!!");
        }
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/ProgrammeSegmentServlet",
            data: {option: "<%= Utility.OPTION_GET_NEW_CODE%>", parent: parent, entity: "ProgrammeSegmentHeader3"},
            dataType: "text",
            cache: false,
            async: false,
            success: ObjectivecodeReturnValues,
            error: function () {
                toastr["error"]("Unable to generate Code!", "Code Generation Failed!!!");
            }
        });

    }
    ;
    function ObjectivecodeReturnValues(data) {
        var code = data.slice(2, data.length - 4);
        var len = code.length;
        code = parseInt(code) + 1;

        var newcode = '' + code;
        newcode = '' + H3c + newcode.padStart(len, '0');
        NewH3Code = newcode;

        $('#code_addh3').val(newcode);
        // $('#code_edith2').val(newcode);

    }
    ;

    function cleanInputs()
    {
        document.getElementById("code_edith3").value = "";
        document.getElementById("code_addh3").value = "";

    }

    function getSectorProgrammes() {
        ShowLoading();
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/SectorProgrammesServlet",
            data: {option: "<%= Utility.OPTION_SELECT_ALL%>"},
            dataType: "json",
            cache: false,
            async: false,
            success: popSectorProgrammes,
            error: function () {
                StopLoading();
                toastr["error"]("Sector Programmes Selection Failed!", "No record selected!");
            }
        });
    }
    function popSectorProgrammes(data) {
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
            $('#sectorprogramme_addh3').html("<option value='0'></option>" + resp);
            $('#sectorprogramme_edith3').html("<option value='0'></option>" + resp);
            StopLoading();
        }
    }
    ;

    function getPolicies() {
        ShowLoading();
        $.ajax({
            type: "GET",
            url: "<%= Utility.SITE_URL%>/PolicyServlet",
            data: {option: "<%= Utility.OPTION_SELECT_ALL%>", },
            dataType: "json",
            cache: false,
            async: false,
            success: policyReturnValues,
            error: function () {
                StopLoading();
                toastr["error"]("No record selected!", "Policies Selection Failed!!!");
            }
        });
    }
    ;
    function policyReturnValues(data) {
        StopLoading();
        if (data.length > 0) {
            var resp = "";
            var active_div = "<table id='main_table_active' class='table table-clean table-striped' border='0'>";
            active_div += "<thead><tr><td><b>Policy&nbsp;Name</b></td><td><b>Policy&nbsp;Weight</b></td><td><b>MDAs</b></td><td></td></tr></thead><tbody>";

            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                var rec_id = "0";
                for (var key in obj) {
                    var attrName = key;
                    var attrValue = obj[key];
                    if (attrValue === null || attrValue === 'null') {
                        attrValue = "";
                    }

                    if (attrName === "0") {
                        rec_id = attrValue;
                        resp += "<tr class='hover-row' ref='user_123'>";
                    }
                    if (attrName === "1") {
                        resp += "<td class='line-height-30 w-35'>" + attrValue + "</td>";
                    }
                    if (attrName === "2") {
                        resp += "<td class='line-height-30 w-10'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + attrValue + "</td>";
                    }
                    if (attrName === "3") {
                        attrValue = attrValue.replace(/ /g, '&nbsp;').replace(/,&nbsp;/g, ',<br><br><br><br><br>');
                        resp += "<td class='line-height-30 w-35' style='vertical-align: middle;'><small><b>" + attrValue + "</b></small></td>";
                        resp += "<td class='line-height-30 w-20'><button onclick='EdiPolicy(" + rec_id + ");' type='button' class='btn btn-success btn-labeled pull-left'>Edit<span class='btn-label btn-label-right'><i class='fa fa-edit'></i></span></button>&nbsp;";
                        resp += "<button onclick='DeletePolicy(" + rec_id + ");' type='button' class='btn btn-danger btn-labeled pull-right'>Delete<span class='btn-label btn-label-right'><i class='fa fa-remove'></i></span></button></td></tr>";
                        active_div += resp;
                        resp = "";
                    }
                }
            }
            active_div += "</tbody></table>";

            $('#active').html(active_div);
            //$('#main_table_active').dataTable();
            $('#main_table_active').dataTable({
                "pagingType": "full_numbers",
                "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]]
            });
        }
    };


</script>
</body>
</html>
