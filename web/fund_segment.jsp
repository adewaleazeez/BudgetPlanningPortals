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
        <link rel="stylesheet" href="css/font-awesome.min.css" media="screen">ale
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
                                    <h2 class="title">Fund Segments</h2>
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
                                        <li class="active">Fund Segments</li>
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
                                                            <label>Fund Segment Header 1</label>
                                                            <div id="addSegmentH1"></div>
                                                        </div>
                                                        
                                                        <div class="form-group has-feedback">
                                                            <label>Fund Segment Header 2</label>
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
                                                        <button onclick="saveFundSegment()" type="button" class="btn btn-success btn-labeled pull-left">Save Fund Segment<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
                                                        <button onclick="ReturnToList()" type="button" class="btn btn-danger btn-labeled pull-right">Cancel<span class="btn-label btn-label-right"><i class="fa fa-close"></i></span></button>
                                                    </form>
                                                </div>

                                                <!-- Nav tabs -->
                                                <div id="list-area">
                                                    <div class="col-md-12">
                                                        <form class="col-md-12" style="margin-top: 30px; margin-bottom: 30px; ">
                                                        
<!--                                                        <div class="form-group has-feedback">
                                                            <label>Fund Segment</label>
                                                            <div id="select_fundSegment1"></div>
                                                            
                                                        </div>-->
                                                        <div class="form-group H1SelContainer col-xs-12" style="margin : 10px 0;">
                                                            <label for="selFSHeader1" style="margin : 0 12px;">Fund Segment Header 1</label>
                                                            <div id="selFSHeader1" style="margin : 0 12px" class="col-xs-5"></div> 
                                                            <div class="col-xs-6">
                                                            <button onclick="manageFundSegmentHeader1()" type="button" style="margin : 0 12px" class="btn btn-info btn-labeled ">
                                                                Manage Fund Segment Header 1<span class="btn-label btn-label-right"><i class="fa fa-pencil"></i></span></button>
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="form-group hidden H2SelContainer col-xs-12" style="margin : 10px 0;">
                                                            <label for="selFSHeader2" style="margin : 0 12px;">Fund Segment Header 2</label>
                                                            <div id="selFSHeader2" style="margin : 0 12px" class="col-xs-5"></div> 
                                                            <div class="col-xs-6">
                                                            <button onclick="manageFundSegmentHeader2()" type="button" style="margin : 0 12px" class="btn btn-info btn-labeled ">
                                                                Manage Fund Segment Header 2<span class="btn-label btn-label-right"><i class="fa fa-pencil"></i></span></button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                    </div>
                                                    <div class="col-md-12 right-side">
                                                        <a class="btn btn-info toggle-code-handle pull-right" onclick="addSegment()" role="button"><i class="fa fa-plus"> </i>New Fund Segment</a>
                                                    </div>
                                                    <br style="clear: both;" />
                                                    <ul class="nav nav-tabs border-primary" role="tablist" style="margin-top: 20px;">
                                                        <li role="presentation" class="active"><a class="" href="#active" aria-controls="active" role="tab" data-toggle="tab" aria-expanded="true"> Fund Segments</a></li>
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
                                                    <h5>Edit Fund Segment</h5>
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
                                                    <button onclick="FinishEdit()" type="button" class="btn btn-success btn-labeled pull-left">Save Fund Segment<span class="btn-label btn-label-right"><i class="fa fa-save"></i></span></button>
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
            getFundSegmentHeader1();
            
            function getFundSegments(){
                let parent = Window.selHeader2;
                //alert(parent);
                if(!parent){getFundSegmentHeader1(); return;} 
                   ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL %>",entity : "FundSegment",parent : parent},
                    dataType: "json",
                    success: fundSegmentReturnValues,
                    error: function(){ StopLoading(); toastr["error"]("Fund Segment Select Failed!", "No record selected!");}
                });
            }
            
            function fundSegmentReturnValues(data){
           
            StopLoading();
//                 if(data.length>0){
                    var resp ="<table class='table table-clean table-striped' ><tbody><tr><td>Name</td><td>Code</td><td></td> </tr>";
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        var recId = 0;
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            attrValue = " " + attrValue;
                            if(attrName === "0"){
                                resp += "<tr >";
                                recId = attrValue;
                            }
                            if(attrName === "1"){
                                resp += "<td class='line-height-30 w-25'><small><b>"+attrValue+"</b></small></td>";
                            }
                            if(attrName === "2"){
                                resp += "<td class='line-height-30 w-25'><small><b>"+attrValue+"</b></small></td>";
                                resp += "<td class='line-height-30 w-50'><button type='button' onclick='EditItem("+recId+")' class='btn btn-info'><i class='fa fa-edit'></i>Edit</button>";
                                resp += " <button type='button' class='btn btn-danger' onclick='DeleteItem("+recId+")'><i class='fa fa-remove'></i>Delete</button></td></tr>";
                                resp += "";
                            }
                            
                        }
                    }
                    resp += "</tbody></table>";
                    document.getElementById("active").innerHTML=resp;
                    
//                }
                
            }
            
            function getFundSegmentHeader1(){
                ShowLoading();
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL %>", entity : "FundSegmentHeader1"},
                    dataType: "json",
                    success: fundSegmentHeader1ReturnValues,
                    error: function(){ StopLoading(); toastr["error"]("Fund Segment11 Select Failed!", "No record selected!");}
                });
            }
            
            function fundSegmentHeader1ReturnValues(data){
                StopLoading();
                    var table_resp ="<table class='table table-clean table-striped' ><tbody><tr><td>Name</td><td>Code</td><td></td> </tr>";
                    var resp ="";
                    var value = {};
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
                        table_resp += "<tr> <td class='line-height-30 w-25'><small><b>"+value.name+"</b></small></td>";
                        table_resp += "<td class='line-height-30 w-25'><small><b>"+value.code+"</b></small></td>";
                        table_resp += "<td class='line-height-30 w-50'><button type='button' onclick='EditHeader1("+value.id+")' class='btn btn-info'><i class='fa fa-edit'></i>Edit</button>";
                        table_resp += " <button type='button' class='btn btn-danger' onclick='DeleteHeader1("+value.id+")'><i class='fa fa-remove'></i>Delete</button></td>";
                        table_resp += "</tr>";
                        
                        resp += "<option value='"+value.id+"'>"+value.name+"</option>";
                    }
                    table_resp += "</tbody></table>";
                    resp += "</select>";
                    $('#selFSHeader1').html("<select class='js-states form-control' onchange='setHeader1(this.value);getFundSegmentHeader2();setHeader2(0);clearSegments();' id='selFSHeader1_set'><option value='0'  class=''>Please select Header 1</option>"+resp);
                    document.getElementById("FSHeader1_table").innerHTML=table_resp;
                    $('#Header1_addH2').html("<select class='js-states form-control' onchange='setHeader1(this.value);getFundSegmentHeader2();' id='selHeader1_addH2'><option value='0' class=''>Please select Header 1</option>"+resp);
                    $('#addSegmentH1').html("<select class='js-states form-control' id='sel_addSegmentH1' disabled><option class=''>Please select Header 1</option>"+resp);
//                  $('#selMda3').html("<select class='js-states form-control' id='selMda_s' onchange='getDepartments();setMdas(this)'><option class=''>Please select MDA</option>"+resp);
                StopLoading();
                
            }
            
            function setHeader1(value){
                Window.selHeader1 = value;
                $('#sel_addSegmentH1')[0].value  = value;
//                $('#selHeader1_addH2')[0].value  = value;
                $('#selFSHeader1_set')[0].value  = value;
                 
            }
            
            function setHeader2(value){
                Window.selHeader2 = value; 
                if($('#sel_addSegmentH2')[0])$('#sel_addSegmentH2')[0].value  = value;
                
            }
            
            function getFundSegmentHeader2(){
                ShowLoading();
                parent_id = Window.selHeader1;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
                    data: {option: "<%= Utility.OPTION_SELECT_ALL %>", entity : "FundSegmentHeader2",parent : parent_id},
                    dataType: "json",
                    success: fundSegmentHeader2ReturnValues,
                    error: function(){ StopLoading(); toastr["error"]("Fund Segment Header Select Failed!", "No record selected!");}
                });
            }
            
            function fundSegmentHeader2ReturnValues(data){
            StopLoading();
            var table_resp_h2 ="<table class='table table-clean table-striped' ><tbody><tr><td>Name</td><td>Code</td><td></td> </tr>";
                    var resp ="";
                    var value = {};
                    for (var i = 0; i < data.length; i++) {
                        var obj = data[i];

                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if(attrValue === null || attrValue === 'null'){
                                attrValue = "";
                            }
                            if(attrName === "2"){
                                value.code =  attrValue;
                            }
                            if(attrName === "1"){
                                value.name = attrValue;
                            }
                            if(attrName === "0"){
                                value.id = attrValue;
                            }
                        }
                        resp += "<option value='"+value.id+"'>"+value.name+"</option>";
                        table_resp_h2 += "<tr> <td class='line-height-30 w-25'><small><b>"+value.name+"</b></small></td>";
                        table_resp_h2 += "<td class='line-height-30 w-25'><small><b>"+value.code+"</b></small></td>";
                        table_resp_h2 += "<td class='line-height-30 w-50'><button type='button' onclick='EditHeader2("+value.id+")' class='btn btn-info'><i class='fa fa-edit'></i>Edit</button>";
                        table_resp_h2 += " <button type='button' class='btn btn-danger' onclick='DeleteHeader2("+value.id+")'><i class='fa fa-remove'></i>Delete</button></td>";
                        table_resp_h2 += "</tr>";
                    }
                    resp += "</select>";
                    table_resp_h2 += "</tbody></table>";
                    $('#selFSHeader2').html("<select class='js-states form-control' onchange='setHeader2(this.value);getFundSegments();' id='selFSHeader2_set'><option value='0'  class=''>Please select Header 2</option>"+resp);
                    document.getElementById("FSHeader2_table").innerHTML=table_resp_h2;
                    $('#addSegmentH2').html("<select class='js-states form-control' id='sel_addSegmentH2' disabled ><option value='0' class=''>Please select Header 2</option>"+resp);
//                    $('#selMda4').html("<select class='js-states form-control' id='selMda_HODe' readonly disabled><option class=''>Please select MDA</option>"+resp);
//                    $('#selMda3').html("<select class='js-states form-control' id='selMda_s' onchange='getDepartments();setMdas(this)'><option class=''>Please select MDA</option>"+resp);
                $('.H2SelContainer').removeClass('hidden');
                StopLoading();
                
            }
            
            function saveFundSegment(){
                let name = document.getElementById("name_addSegment").value;
                let code = document.getElementById("code_addSegment").value;
                let parent = Window.selHeader2;
                
                if(!parent) {toastr["error"]("Please select Header parent!", "Field cannot be empty!"); return;};
                if(name.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
                    data: {option: "<%= Utility.OPTION_INSERT %>" , name: name,parent: parent,code: code, entity : "FundSegment"},
                    dataType: "json",
                    success: fundSegmentInsertReturnValues,
                    error: function(){ toastr["error"]("Fund Segment Insert Failed!", "No record inserted!");}
                });
                //
            }
            
            function fundSegmentInsertReturnValues(data){
                        document.getElementById("name_addSegment").value = '';
                        document.getElementById("code_addSegment").value = '';
                       if(data.indexOf("<%= Utility.ActionResponse.INSERTED.toString() %>") !== -1){
                           
                           toastr["success"]("Fund Segment Insert Successful!", "New record successfully inserted!");
                           
                           getFundSegments();
                           ReturnToList();
                       }else{
                           toastr["error"]("Fund Segment with that name already exist", "Duplicate Entry!");
                       }
                   }

            function EditItem(arg){
                createCookie("formtype", "editFundSegment");
         $.ajax({
             type: "GET",
             url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
             data: {option: "<%= Utility.OPTION_SELECT_A_RECORD %>", id: arg,entity : "FundSegment"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                 if(data !== null || data !== "null"){ 
                     data = data[0];
                      document.getElementById("name2").value = data[1];
                      document.getElementById("code2").value = data[2];
                      document.getElementById("updateid").value = data[0];
                     }
                     toastr["success"]("Fund Segment successfully fetched!", "Fund Segment Successfull!!!");
                     $('#MainSection').hide();
                     $('#EditSection').fadeIn();
                     $('ul.breadcrumb').html("<li><a onclick='gotoLink('/dashboard00012');'><i class='fa fa-home'></i> Home</a></li><li onclick='closeEdit()'> Fund Segment</li><li class='active' >Edit Fund Segment</li>");
                 }
             ,
             error: function(e,b,c){
                 toastr["error"]("Record with id " + arg + " is not found!", "Fund Segment Select Failed!!!");
             }
         });                
            }

            function FinishEdit(){
            let name = document.getElementById("name2").value;
            let code = document.getElementById("code2").value;
            let id = document.getElementById("updateid").value;
            
            if(name.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
            if(code.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
         $.ajax({
             type: "GET",
             url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
             data: {option: "<%= Utility.OPTION_UPDATE %>" , name: name,code:code,id:id,entity : "FundSegment"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                 if(data === '<%= Utility.ActionResponse.UPDATED.toString() %>'){
                 toastr["success"]("Fund Segment update Successfull!", "Update Successfull");
                 cancelEdit();
             }
             if(data === "<%= Utility.ActionResponse.RECORD_EXISTS.toString() %>"){
             toastr["error"]("Fund Segment with that name already exists", "Update Failed");
             }},
             error: function(a,b,c){ 
             toastr["error"]("Fund Segment Update Failed!", "No record updated!");}
         });
            }

            function cancelEdit(){
             $('#EditSection').hide();
             $('#MainSection').fadeIn();
             getFundSegments();
             document.getElementById("name2").value = '';
             document.getElementById("updateid").value = '';

            }

            function DeleteItem(arg,entity){
                if(entity == undefined || entity == '') entity = "FundSegment";
                $('#deleteModal').iziModal('open');
                window.deleteId = arg;
                window.deleteEntity = entity;
            }
            
            function DeleteHeader1(id){
                DeleteItem(id,"FundSegmentHeader1");
            }

            function DeleteHeader2(id){
                DeleteItem(id,"FundSegmentHeader2");
            }
            
            function cancelDelete(){
                $('#deleteModal').iziModal('close');
                window.deleteId = 0;
            }

            function FinishDelete(){
                let id = window.deleteId;
                let entity = window.deleteEntity;
                //console.log("Finish delete for" + id);

                $.ajax({
             type: "GET",
             url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
             data: {option: "<%= Utility.OPTION_DELETE %>" ,id:id,entity : entity},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                 //console.log(data);
                 if(data === '<%= Utility.ActionResponse.DELETED.toString() %>'){

                     $('#deleteModal').iziModal('close');
                 toastr["success"]("Fund Segment deleted Successfull!", "Delete Successfull");
                 
                 getFundSegments();
             }
             if(data === '<%= Utility.ActionResponse.NO_RECORD.toString() %>'){
                 $('#deleteModal').iziModal('close');
                 toastr["error"]("Fund Segment delete Failed!", "No record deleted!");
             }
             },
             error: function(a,b,c){ 

             toastr["error"]("Fund Segment delete Failed!", "No record deleted!");}
         });
            }

            function manageFundSegmentHeader1(){
             $('#EditSection').hide();
             $('#MainSection').hide();
             $('#addH1Section').fadeIn();
            }

            function manageFundSegmentHeader2(){
                
             if(window.selHeader1 === '0') {toastr["error"]("Please select Header !", "Field cannot be empty!");return;}
             $('#EditSection').hide();
             $('#MainSection').hide();
             $('#addH2Section').fadeIn();
            }
            
            function CancelManageHeader1(){
                $('#EditSection').hide();
             $('#addH1Section').hide();
             $('#MainSection').fadeIn();
            }

            function CancelManageHeader2(){
                $('#EditSection').hide();
             $('#addH2Section').hide();
             $('#MainSection').fadeIn();
            }
            
            function FinishAddHeader1(){
                var name_addh1 = document.getElementById("name_addh1").value;
                var code_addh1 = document.getElementById("code_addh1").value;
                
                if(name_addh1.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code_addh1.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
                
         $.ajax({
             type: "GET",
             url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
             data: {option: "<%= Utility.OPTION_INSERT %>" , name: name_addh1, code: code_addh1,entity : "FundSegmentHeader1"},
             dataType: "json",
             success: Header1InsertReturnValues,
             error: function(){ toastr["error"]("Fund Segment Insert Failed!", "No record inserted!");}
         });
            }
            
            function Header1InsertReturnValues(data){
            document.getElementById("name_addh1").value = '';
            document.getElementById("code_addh1").value = '';
                if(data.indexOf("<%= Utility.ActionResponse.INSERTED.toString() %>") !== -1){
                    toastr["success"]("Fund Segment Header 1 Insert Successful!", "New record successfully inserted!");
                    getFundSegmentHeader1();               
                    ReturnToList();
                }else{
                    toastr["error"]("Fund Segment with that name already exist", "Duplicate Entry!");
                }
            }
            
            function FinishAddHeader2(){
                let parent = Window.selHeader1;
                var name_addh2 = document.getElementById("name_addh2").value;
                var code_addh2 = document.getElementById("code_addh2").value;
                
                if(!parent) {toastr["error"]("Please select Header parent!", "Field cannot be empty!"); return;};
                if(name_addh2.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code_addh2.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
                    
         $.ajax({
             type: "GET",
             url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
             data: {option: "<%= Utility.OPTION_INSERT %>" , name: name_addh2, code: code_addh2,entity : "FundSegmentHeader2",parent : parent},
             dataType: "json",
             success: Header2InsertReturnValues,
             error: function(){ toastr["error"]("Fund Segment Insert Failed!", "No record inserted!");}
         });
            }
            
            function Header2InsertReturnValues(data){
            document.getElementById("name_addh2").value = '';
            document.getElementById("code_addh2").value = '';
                if(data.indexOf("<%= Utility.ActionResponse.INSERTED.toString() %>") !== -1){
                    toastr["success"]("Fund Segment Header 1 Insert Successful!", "New record successfully inserted!");
                    getFundSegmentHeader2();               
                    ReturnToList();
                }else{
                    toastr["error"]("Fund Segment with that name already exist", "Duplicate Entry!");
                }
            }
            
            function EditHeader1(arg){
                createCookie("formtype", "editFundSegmentHeader1");
         $.ajax({
             type: "GET",
             url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
             data: {option: "<%= Utility.OPTION_SELECT_A_RECORD %>", id: arg,entity : "FundSegmentHeader1"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                 //console.log(data);
                 if(data !== null || data !== "null"){ 
                     data = data[0];
                      document.getElementById("code_edith1").value = data[2];
                      document.getElementById("name_edith1").value = data[1];
                      Window.updateid_h1= data[0];
                      $(".addHeader1Form").addClass("hidden");
                      $("#addHeader1Btn").addClass("hidden");
                      
                      $("#cancelEditH1Btn").removeClass("hidden");
                      $("#editHeader1Btn").removeClass("hidden");
                      $(".editHeader1Form").removeClass("hidden");
                     }
                     toastr["success"]("Fund Segment successfully fetched!", "Fund Segment Successfull!!!");
                    
                 }
             ,
             error: function(e,b,c){
                 toastr["error"]("Record with id " + arg + " is not found!", "Fund Segment Select Failed!!!");
             }
         });                
            }

            function FinishHeader1Edit(){
            
            var name = document.getElementById("name_edith1").value;
            var code = document.getElementById("code_edith1").value;
            var id = Window.updateid_h1;
            
            if(name.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
         $.ajax({
             type: "GET",
             url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
             data: {option: "<%= Utility.OPTION_UPDATE %>" , name: name,id:id,code:code,entity : "FundSegmentHeader1"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                 //console.log(data);
                 if(data === '<%= Utility.ActionResponse.UPDATED.toString() %>'){
                 toastr["success"]("Fund Segment update Successfull!", "Update Successfull");
                 CancelHeader1Edit();
                 getFundSegmentHeader1();
             }
             if(data === "<%= Utility.ActionResponse.RECORD_EXISTS.toString() %>"){
             toastr["error"]("Fund Segment with that name already exists", "Update Failed");
             }
             if(data === "<%= Utility.ActionResponse.NO_RECORD.toString() %>"){
             toastr["error"]("Fund Segment with id "+id+" doesnt exist", "Update Failed");
             }
             
},
             error: function(a,b,c){ 
             toastr["error"]("Fund Segment Update Failed!", "No record updated!");}
         });
            }

            function CancelHeader1Edit(){
                      $(".addHeader1Form").removeClass("hidden");
                      $("#cancelManageH1Btn").removeClass("hidden");
                      $("#addHeader1Btn").removeClass("hidden");
                      $("#editHeader1Btn").addClass("hidden");
                      $("#cancelEditH1Btn").addClass("hidden");
                      $(".editHeader1Form").addClass("hidden");
            }
            
            function EditHeader2(arg){
               
                createCookie("formtype", "editFundSegmentHeader2");
         $.ajax({
             type: "GET",
             url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
             data: {option: "<%= Utility.OPTION_SELECT_A_RECORD %>", id: arg,entity : "FundSegmentHeader2"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                 //console.log(data);
                 if(data !== null || data !== "null"){ 
                     data = data[0];
                      document.getElementById("code_edith2").value = data[2];
                      document.getElementById("name_edith2").value = data[1];
                      Window.updateid_h2= data[0];
                      $(".addHeader2Form").addClass("hidden");
                      $("#addHeader2Btn").addClass("hidden");
                     
                      $("#cancelEditH2Btn").removeClass("hidden");
                      $("#editHeader2Btn").removeClass("hidden");
                      $(".editHeader2Form").removeClass("hidden");
                     }
                     toastr["success"]("Fund Segment successfully fetched!", "Fund Segment Successfull!!!");
                    
                 }
             ,
             error: function(e,b,c){
                 toastr["error"]("Record with id " + arg + " is not found!", "Fund Segment Select Failed!!!");
             }
         });                
            }

            function FinishHeader2Edit(){
            
            var name = document.getElementById("name_edith2").value;
            var code = document.getElementById("code_edith2").value;
            var id = Window.updateid_h2;
            
            if(name.length <= 0){ toastr["error"]("Please Enter Header name!", "Field cannot be empty!");return; }
                if(code.length <= 0) {toastr["error"]("Please select Header code!", "Field cannot be empty!");return;}
         $.ajax({
             type: "GET",
             url: "<%= Utility.SITE_URL %>/FundSegmentServlet",
             data: {option: "<%= Utility.OPTION_UPDATE %>" , name: name,id:id,code:code,entity : "FundSegmentHeader2"},
             dataType: "json",
             cache: false,
             async: false,
             success: function(data){
                 //console.log(data);
                 if(data === '<%= Utility.ActionResponse.UPDATED.toString() %>'){
                 toastr["success"]("Fund Segment update Successfull!", "Update Successfull");
                 CancelHeader2Edit();
                 getFundSegmentHeader2();
             }
             if(data === "<%= Utility.ActionResponse.RECORD_EXISTS.toString() %>"){
             toastr["error"]("Fund Segment with that name already exists", "Update Failed");
             }
             if(data === "<%= Utility.ActionResponse.NO_RECORD.toString() %>"){
             toastr["error"]("Fund Segment with id "+id+" doesnt exist", "Update Failed");
             }
             
},
             error: function(a,b,c){ 
             toastr["error"]("Fund Segment Update Failed!", "No record updated!");}
         });
            }

            function CancelHeader2Edit(){
                      $(".addHeader2Form").removeClass("hidden");
                      $("#cancelManageH2Btn").removeClass("hidden");
                      $("#addHeader2Btn").removeClass("hidden");
                      $("#editHeader2Btn").addClass("hidden");
                      $("#cancelEditH2Btn").addClass("hidden");
                      $(".editHeader2Form").addClass("hidden");
            }
            
            function addSegment(){
                if( Window.selHeader2  && Window.selHeader1 
                        && Window.selHeader2 > 0 && Window.selHeader1 > 0)
                    showCreate();
                else
                  toastr["error"]("Please select headers ", "Fund Segment Add Failed!!!");  
            }
            
            function clearSegments(){
                document.getElementById("active").innerHTML = '';
            }
        </script>
    </body>
</html>
