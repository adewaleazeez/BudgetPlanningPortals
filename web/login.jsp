<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bpp.utility.Utility" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!--meta http-equiv="X-UA-Compatible" content="IE=edge"-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="/images/icon.png">
        <title>Login - Ondo Budget</title>
        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- animation CSS -->
        <link href="css/animate-css/animate.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/toastr/toastr.min.css" media="screen">
        <link href="css/login.css" rel="stylesheet">
        <link rel="stylesheet" href="css/font-awesome.min.css" media="screen">
        <link rel="stylesheet" href="css/main.css" media="screen">
        <link rel="stylesheet" href="css/jquery.loading.css" media="screen">
        <!-- color CSS -->

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <!--<script type = 'text/javascript' id ='1qa2ws' charset='utf-8' src='js/base.js'></script>-->

    </head>
    <body>
        <!-- Preloader -->
        <div class="preloader">
            <div class="cssload-speeding-wheel"></div>
        </div>
        <section id="wrapper" class="login-register">
            <div class="login-box" style="margin-top: 20px;">
                <a onclick="gotoLink('/login.jsp');" class="text-center db">
                    <img src="images/logo-dark.png" alt="Home" style="height:35px; margin-top: 40px;" />
                </a>
                <div class="white-box" style="height: 530px;">
                    <form class="form-horizontal form-material" id="loginform">
                        <div class="form-group ">
                            <div class="col-xs-12">
                                <h3>User Login</h3>
                                <p class="text-muted">Enter your Username/Email and Password! </p>
                            </div>
                        </div>
                        <div class="form-group ">
                            <div class="col-xs-12">
                                <label for="username">Username:</label>
                                <input class="form-control" id="username" type="text" required placeholder="Username">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-12">
                                <label for="password">Password:</label>
                                <input class="form-control" id="password" type="password" required placeholder="Password" onkeypress="checkEnter(event,this.id)">
                            </div>
                        </div>
                        <div class="form-group text-center m-t-20">
                            <div class="col-xs-12">
                                <button class="btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light" onclick="checkLogins();" type="button">Log In</button>
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 50px;">
                            <div class="col-md-12">
                                <a href="javascript:void(0)" id="to-change" class="text-dark pull-left"><i class="fa fa-lock m-r-5"></i> Change password  ?</a>
                                <a href="javascript:void(0)" id="to-recover" class="text-dark pull-right"><i class="fa fa-lock m-r-5"></i> Forgot password  ?</a>
                            </div>
                        </div>
                        
                    </form>
                    <form class="form-horizontal" id="recoverform">
                        <div class="form-group ">
                            <div class="col-xs-12">
                                <h3>Reset Password</h3>
                                <p class="text-muted">Enter your Email and instructions will be sent to you! </p>
                            </div>
                        </div>
                        <div class="form-group " style="margin-top: 20px;">
                            <div class="col-xs-12">
                                <input class="form-control" type="text" required id="Email" placeholder="Email" onkeypress="checkEnter(event,this.id)">
                            </div>
                        </div>
                        <div class="form-group text-center m-t-20">
                            <div class="col-xs-12" style="margin-top: 60px;">
                                <button class="btn btn-primary btn-lg btn-block text-uppercase waves-effect waves-light" onclick="resetPassword();" type="button">Reset Password</button>
                            </div>
                            <div class="col-xs-12" style="margin-top: 60px;">
                                <a href="javascript:void(0)" id="back-to-login" class="text-dark" style="text-align:center">
                                    <i class="fa fa-lock m-r-5"></i> Return to Login
                                </a>
                            </div>
                        </div>
                    </form>
                    <form class="form-horizontal" id="changeform">
                        <div class="form-group ">
                            <div class="col-xs-12">
                                <h3>Change Password</h3>
                                <p class="text-muted" style="color: blue"><b>Enter your Username, old and new passwords!</b></p>

                                <label for="name3"><b>Username</b></label>
                                <input type="text" class="form-control" id="txtUsername"  placeholder="Enter Email/Username">

                                <label for="txtOldPassword"><b>Old Password</b></label>
                                <input type="password" class="form-control" id="txtOldPassword" placeholder="Enter your old password">
                                <span class="fa fa-key form-control-feedback"></span>

                                <label for="txtPassword"><b>New Password</b></label>
                                <input type="password" class="form-control" id="txtPassword" placeholder="Enter a new password">
                                <span class="fa fa-key form-control-feedback"></span>

                                <label for="txtPasswordRetype"><b>Confirm New Password</b></label>
                                    <input type="password" class="form-control" id="txtPasswordRetype" placeholder="Retype the new password" onkeypress="checkEnter(event,this.id)">
                                    <span class="fa fa-key form-control-feedback"></span>
                                </div>

<!--                                <hr/>
                                <button type="button" class="btn btn-success btn-labeled pull-right" onclick="changePassword();">Change my password<span class="btn-label btn-label-right"><i class="fa fa-check-circle-o"></i></span></button>-->
                                <button class="btn btn-primary btn-lg btn-block text-uppercase waves-effect waves-light" onclick="changePassword();" type="button">Change Password</button>
                                <br><br>
                                <a href="javascript:void(0)" id="go-back-to-login" class="text-dark" style="margin-left: 120px;">
                                    <i class="fa fa-lock m-r-5"></i> <b>Return to Login</b>
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <!-- jQuery -->
        <script src="js/jquery/jquery-2.2.4.min.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap/bootstrap.min.js"></script>
        <!-- Menu Plugin JavaScript -->
        <script src="js/toastr/toastr.min.js"></script>
        <script src="js/sidebar-nav.min.js"></script>

        <!--slimscroll JavaScript -->
        <script src="js/jquery.slimscroll.js"></script>
        <!--Wave Effects -->
        <script src="js/waves.js"></script>
        <!-- Custom Theme JavaScript -->
        <script src="js/login.js"></script>
        <!--Style Switcher -->
        <script src="js/jQuery.style.switcher.js"></script>
        <script src="js/jquery.loading.js"></script>
        <script type="text/javascript">
            
            $(document).ready(function(){
                var url = window.location + "";
                if(url.indexOf("option")!== -1){
                    window.location = "<%= Utility.SITE_URL %>/codem00017";
                }
                if(readCookie("passwordreset")==="successful"){
                    toastr["success"]("Your attempt to reset your password is successful<br>Check your email for te new password!", "Password Reset Successful!!!");
                }
                
                if(readCookie("passwordreset")==="failed"){
                    toastr["error"]("Your attempt to reset your password failed!", "Password Reset Failed!!!");
                }
                if(readCookie("passwordreset")!==null && readCookie("passwordreset")!==""){
                    $.ajax({
                        type: "GET",
                        url: "<%= Utility.SITE_URL %>/UserServlet",
                        data: {option: "<%= Utility.OPTION_RESET_COOKIE %>", id: "passwordreset"},
                        dataType: "text",
                        cache: false,
                        async: false
                    }); 
                }
                
            });
            
            ShowLoading = function () {
                $("html").loading({
                    stoppable: false
                });
            };
            StopLoading = function () {
                $("html").loading('stop');
            };

            toastr.options = {
                 "closeButton": true,
                  "debug": false,
                  "newestOnTop": false,
                  "progressBar": false,
                  "positionClass": "toast-top-right",
                  "preventDuplicates": false,
                  "onclick": null,
                  "showDuration": "300",
                  "hideDuration": "1000",
                  "timeOut": "5000",
                  "extendedTimeOut": "1000",
                  "showEasing": "swing",
                  "hideEasing": "linear",
                  "showMethod": "fadeIn",
                  "hideMethod": "fadeOut"
                };
                
            function createCookie(name,value,days) {
                var expires = "";
                if (days) {
                    var date = new Date();
                    date.setTime(date.getTime()+(days*24*60*60*1000));
                    expires = "; expires="+date.toGMTString();
                }
                document.cookie = name+"="+value+expires+"; path=/";
            }

            function readCookie(name) {
                var nameEQ = name + "=";
                var ca = document.cookie.split(';');
                for(var i=0;i < ca.length;i++) {
                    var c = ca[i];
                    while (c.charAt(0)==' ') c = c.substring(1,c.length);
                    if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
                }
                return "";
            }

            function eraseCookie(name) {
                createCookie(name,"",-1);
            }

            function checkLogins() {
                ShowLoading();
                var username = document.getElementById("username").value;
                var password = document.getElementById("password").value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/UserServlet",
                    data: {option: "<%= Utility.OPTION_CHECK_LOGIN %>", username: username, password: password},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        StopLoading();
                        if (data.indexOf("<%= Utility.ActionResponse.SUCCESSFULL.toString() %>")!== -1) {
                            window.location.href="<%= Utility.SITE_URL %>/dashboard00012";
                        } else if (data.indexOf("<%= Utility.ActionResponse.BLOCKED.toString() %>")!== -1){
                            toastr["error"]("Your user account have been blocked, contact the System Administrator to resolve it!", "User Blocked!!!!");
                        } else {
                            toastr["error"]("Invalid Username or Password!", "Login Failed!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown) {
                    error: function(a,b,c) {
                    alert(2+"   "+b+"   "+c);
                        StopLoading();
                    	toastr["error"]("The server is not accessible!", "Login Failed!!!");
                    }
                });
                
            }
                        
            function resetPassword(){
                ShowLoading();
                var email = document.getElementById("Email").value;
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL %>/UserServlet",
                    data: {option: "<%= Utility.OPTION_REQUEST_PASSWORD_RESET %>", email: email},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        StopLoading();
                        document.getElementById("back-to-login").click();
                        if (data.indexOf("<%= Utility.ActionResponse.SUCCESSFULL.toString() %>")!== -1) {
                            toastr["success"]("Instruction for reseting your password has been sent to: "+email, "Password Reset Request Successful!!!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.BLOCKED.toString() %>")!== -1){
                            toastr["error"]("Your user account have been blocked, contact the System Administrator to resolve it!", "User Blocked!!!!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.NO_RECORD.toString() %>")!== -1){
                            toastr["error"]("The user account does not exist, contact the System Administrator to resolve it!", "User Not Exist!!!!");
                        } else {
                            toastr["error"]("The Email you typed is invalid!", "Invalid Email!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown) {
                    error: function() {
                        StopLoading();
                    	toastr["error"]("The server is not accessible!", "Login Failed!!!");
                    }
                    
                });            
            }
            
            function checkEnter(e, id){ //e is event object passed from function invocation
                var characterCode; //literal character code will be stored in this variable

                if(e && e.which){ //if which property of event object is supported (NN4)
                    e = e;
                    characterCode = e.which; //character code is contained in NN4's which property
                } else {
                    e = event;
                    characterCode = e.keyCode; //character code is contained in IE's keyCode property
                }

                if(characterCode === 13){ //if generated character code is equal to ascii 13 (if enter key)
                    if(id==="password"){
                        checkLogins();
                    }
                    if(id==="Email"){
                        resetPassword();
                    }
                    return false;
                } else {
                    return true;
                }

            }
            function changePassword() {
                ShowLoading();
                var txtUsername = document.getElementById("txtUsername").value;
                var txtOldPassword = document.getElementById("txtOldPassword").value;
                var txtPassword = document.getElementById("txtPassword").value;
                var txtPasswordRetype = document.getElementById("txtPasswordRetype").value;

                var error = "";
                if(txtUsername===""){ error +="<br>Username must not be blank<br>"; }
                if(txtOldPassword===""){ error +="Old Password must not be blank<br>"; }
                if(txtPassword===""){ error +="New Password must not be blank<br>"; }
                if(txtPasswordRetype!==txtPassword){ error +="New Password does not match the retyped password<br>"; }
                if(error!==""){
                    toastr["error"](error, "Please Correct The Following Error(s)!!!");
                    return true;
                }
                $.ajax({
                    type: "GET",
                    url: "<%= Utility.SITE_URL%>/UserServlet",
                    data: {option: "<%= Utility.OPTION_CHANGE_PASSWORD%>", username: txtUsername, password: txtPassword, id: txtOldPassword},
                    dataType: "text",
                    cache: false,
                    async: false,
                    success: function (data) {
                        StopLoading();
                        if (data.indexOf("<%= Utility.ActionResponse.SUCCESSFULL.toString()%>") !== -1) {
                            toastr["success"]("Details of the account has been sent to: " + txtUsername, "Password Change Successful!!!");
                        } else if (data.indexOf("<%= Utility.ActionResponse.BLOCKED.toString()%>") !== -1) {
                            toastr["error"]("The user account fot " + txtUsername+" have been blocked, contact the System Administrator to resolve it!", "User Blocked!!!!");
                        } else {
                            toastr["error"]("The old password you typed is invalid!", "Invalid Old Password!!!");
                        }
                    },
                    //error: function(jqXHR, textStatus, errorThrown) {
                    error: function () {
                        StopLoading();
                        toastr["error"]("The server is not accessible!", "Password Change Failed!!!");
                    }
                });
            }

        </script>

    </body>
</html>
