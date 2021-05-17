/*! --------------------------------------------------------------
# main.js
#
# Main theme js file for Options-admin template.
# This is compressed js file. You get uncompressed version with download.
--------------------------------------------------------------*/
function checkLogin() {
    ShowLoading();
    $.ajax({
        type: "GET",
        url: readCookie("siteurl").replace(/\"/g, '') + "/UserServlet",
        data: {option: "checkuserstatus", id: readCookie("userid").replace(/\"/g, '') },
        dataType: "text",
        cache: false,
        async: false,
        success: function (data) {
            StopLoading();
            if (data.indexOf("FAILED")!== -1) {
                toastr["error"]("The username is not registered", "Unregistered User!!!");
                gotoLink('/codem00017');
            } else if (data.indexOf("BLOCKED")!== -1){
                toastr["error"]("The username is blocked", "Blocked User!!!");
                gotoLink('/codem00017');
            }else{
                getMenus();
            }
        },
        //error: function(jqXHR, textStatus, errorThrown) {
        error: function() {
            StopLoading();
            toastr["error"]("The server is not accessible!", "User Editing Failed!!!");
            gotoLink('/codem00017');
        }
    });       

};

function getMenus() {
    var url = window.location.href;
    url = url.split("/");
    arg = url[4];
    ShowLoading();
    $.ajax({
        type: "GET",
        url: readCookie("siteurl").replace(/\"/g, '') + "/UserServlet",
        data: {option: "resetmenu", id: readCookie("userid").replace(/\"/g, '') },
        dataType: "json",
        cache: false,
        async: false,
        success: function (data) {
            if(data.length>0){
                var mymenu = "<ul class='side-nav color-gray'>";
                mymenu += "<li class='nav-header'>";
                mymenu += "<span class=''>Menu</span>";
                mymenu += "</li>";
                var menuCategory = "";
                for (var i = 0; i < data.length; i++) {
                        var obj = data[i];
                        for (var key in obj) {
                            var attrName = key;
                            var attrValue = obj[key];
                            if(attrValue === null || attrValue === 'null'){
                                attrValue = "";
                            }
                            if(attrName === "3" && menuCategory !==attrValue){
                                if (menuCategory !=="") {
                                    mymenu += "</ul></li>";
                                }
                                menuCategory = attrValue;
                                mymenu += "<li class='has-children'>";
                                mymenu += "<a><i class='fa fa-file-text'></i> <span>" + menuCategory + "</span> <i class='fa fa-angle-right arrow'></i></a>";
                                mymenu += "<ul class='child-nav'>";
                            }

                            if (attrName === "4") {
                                var menuUrl = attrValue;
                                mymenu += "<li><a onclick=gotoLink('/"+menuUrl+"')><i class='fa fa-thumb-tack'></i>";
                            }
                            if (attrName === "5") {
                                var menuName = attrValue;
                                mymenu += "<span>" + menuName + "</span></a></li>";
                            }
                        }
                    }
                    mymenu += "</ul>";
                    if (mymenu.indexOf(arg)=== -1) {
                    	if(arg != 'dashboard00012') {
	                        toastr["error"]("The menu is not assigned to the user", "Unassigned Menu!!!");
	                        gotoLink('/codem00017');
                    	}
                    }
                    $('#mymenus').html(mymenu);
                    StopLoading();
            }else{
                toastr["error"]("The username is not registered", "Unregistered User!!!");
                gotoLink('/codem00017');
            }
        },
        //error: function(jqXHR, textStatus, errorThrown) {
        error: function() {
            StopLoading();
            toastr["error"]("The server is not accessible!", "User Editing Failed!!!");
            gotoLink('/codem00017');
        }
    });
};

/**
 * Method for getting query string parameters and respective values
 * 
 * @param name
 * @param url
 * @returns
 */
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function gotoLink(link) {
    window.location.href = readCookie("siteurl").replace(/\"/g, '') + link;
};

function logout() {
	ShowLoading();
    $.ajax({
        type: "GET",
        url: readCookie("siteurl").replace(/\"/g, '') + "/UserServlet",
        data: {option: "clearsession", id: readCookie("userid").replace(/\"/g, '') },
        dataType: "text",
        cache: false,
        async: false,
        success: function (data) {
            StopLoading();
            gotoLink('/codem00017');
        },
        error: function() {
            StopLoading();            
            gotoLink('/codem00017');
        }
    });
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
};

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)===' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length,c.length);
    }
    return "";
};

function eraseCookie(name) {
    createCookie(name,"",-1);
};

var ReturnToList = function () {
    $('#create-area').hide();
    $('#MainSection .panel-title').hide();
    $('#list-area').fadeIn();
};

$('.show-create').click(function () {
        $('#list-area').hide();
        $('#create-area').fadeIn();
        $('#MainSection .panel-title').fadeIn();
        
        if($('#programme_code1')){
            $('#programme_code1').focus();
        }else{
            $('#name1').focus();
        }
    });

var EditItem = function () {
    $('#MainSection').hide();
    $('#EditSection').fadeIn();
    $('.edit-child').fadeIn();
};

var CloseEdit = function () {
    $('#EditSection').hide();
    $('#MainSection').fadeIn();
};

ShowLoading = function () {
    $("html").loading({
        stoppable: true
    });
};
StopLoading = function () {
    $("html").loading('stop');
};

IsLoading = function () {
    //call this function to know if loading is currently displayed
    return $('html').is(':loading');
};

$(function ($) {
    $('.hover-row').hover(function () {
        var ref = $(this).attr('ref');
        $('.hover-control[ref=' + ref + ']').fadeIn();
    },
        function () {
            var ref = $(this).attr('ref');
            $('.hover-control[ref=' + ref + ']').fadeOut();
        }
    )
    "use strict";
    try {
        $('.custom_datepicker').datetimepicker({
            //debug: true
        });
    } catch (e) { }
    try {
        $('.custom_datepicker_dateonly').datetimepicker({
            format: 'DD/MM/YYYY'
        });
    } catch (e) { }

    
    try {
        $('.custom_select').select2();
    } catch (e) {}

	// Toggle user info on sidebar
	$('.user-info-handle').on('click', function(event){
		event.preventDefault();
		$('.user-info').toggleClass('closed');
	});

	// Toggle small sidebar
	$('.small-nav-handle').on('click', function(event){
		event.preventDefault();
		$('.left-sidebar').toggleClass('small-nav');
		$('.navbar-header').toggleClass('small-nav-header');
	});

	// Toggle Mobile Nav
	$('.mobile-nav-toggle').on('click', function(event){
		event.preventDefault();
		$('.left-sidebar').toggle();
	});

	// Toggle tooltips
	$('[data-toggle="tooltip"]').tooltip();

	// Toggle popovers
	//$('[data-toggle="popover"]').popover();

	// For custom modal backdrop
	$('.modal[data-backdrop-color]').on('show.bs.modal hide.bs.modal', function () {
		$('body').toggleClass('modal-color-'+ $(this).data('backdropColor'));
	});

	// Open right sidebar
	$('.open-right-sidebar').on('click', function(event){
		event.preventDefault();
		$('.right-sidebar, .right-sidebar .sidebar-content').css('right','0px');
	});
	$('.right-sidebar .close-icon').on('click', function(event){
		event.preventDefault();
		$('.right-sidebar, .right-sidebar .sidebar-content').css('right','-400px');
	});

	// Initialize panel controls
	//$('[data-panel-control]').lobiPanel();

	// Visibility of source code button
	$('.src-btn').hide();
	$('.toggle-help-handle').on('click', function(event){
		event.preventDefault();
		$('.src-btn').toggle();
	});

	// Visibility of source code button
	$('.src-code').hide();
	$('.toggle-code-handle').on('click', function(event){
		event.preventDefault();
		$('.src-code').toggle();
	});

	// Toggle full screen
	$('.full-screen-handle').on('click', function(event){
		event.preventDefault();
		if ((document.fullScreenElement && document.fullScreenElement !== null) ||
			(!document.mozFullScreen && !document.webkitIsFullScreen)) {
			if (document.documentElement.requestFullScreen) {
				document.documentElement.requestFullScreen();
			} else if (document.documentElement.mozRequestFullScreen) {
				document.documentElement.mozRequestFullScreen();
			} else if (document.documentElement.webkitRequestFullScreen) {
				document.documentElement.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
			}
		} else {
			if (document.cancelFullScreen) {
				document.cancelFullScreen();
			} else if (document.mozCancelFullScreen) {
				document.mozCancelFullScreen();
			} else if (document.webkitCancelFullScreen) {
				document.webkitCancelFullScreen();
			}
		}
	});

	// Toggle sidebar dropdown
	$('.has-children').not('.open').find('.child-nav').slideUp('100');
	$('.has-children>a').on('click', function(event){
		event.preventDefault();
		$(this).parent().toggleClass('open');
		$(this).parent().find('.child-nav').slideToggle('500');
	});

	// For Dropdown menu animation
	var dropdownSelectors = $('.dropdown, .dropup');

	// Custom function to read dropdown data
	// =========================
	function dropdownEffectData(target) {
		// @todo - page level global?
		var effectInDefault = null,
			effectOutDefault = null;
		var dropdown = $(target),
			dropdownMenu = $('.dropdown-menu', target);
		var parentUl = dropdown.parents('ul.nav');

		// If parent is ul.nav allow global effect settings
		if (parentUl.size() > 0) {
			effectInDefault = parentUl.data('dropdown-in') || null;
			effectOutDefault = parentUl.data('dropdown-out') || null;
		}

		return {
			target: target,
			dropdown: dropdown,
			dropdownMenu: dropdownMenu,
			effectIn: dropdownMenu.data('dropdown-in') || effectInDefault,
			effectOut: dropdownMenu.data('dropdown-out') || effectOutDefault,
		};
	}

	// Custom function to start effect (in or out)
	// =========================
	function dropdownEffectStart(data, effectToStart) {
		if (effectToStart) {
			data.dropdown.addClass('dropdown-animating');
			data.dropdownMenu.addClass('animated');
			data.dropdownMenu.addClass(effectToStart);
		}
	}

	// Custom function to read when animation is over
	// =========================
	function dropdownEffectEnd(data, callbackFunc) {
		var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
		data.dropdown.one(animationEnd, function() {
			data.dropdown.removeClass('dropdown-animating');
			data.dropdownMenu.removeClass('animated');
			data.dropdownMenu.removeClass(data.effectIn);
			data.dropdownMenu.removeClass(data.effectOut);

			// Custom callback option, used to remove open class in out effect
			if (typeof callbackFunc == 'function') {
				callbackFunc();
			}
		});
	}

	// Bootstrap API hooks
	// =========================
	dropdownSelectors.on({
		"show.bs.dropdown": function() {
			// On show, start in effect
			var dropdown = dropdownEffectData(this);
			dropdownEffectStart(dropdown, dropdown.effectIn);
		},
		"shown.bs.dropdown": function() {
			// On shown, remove in effect once complete
			var dropdown = dropdownEffectData(this);
			if (dropdown.effectIn && dropdown.effectOut) {
				dropdownEffectEnd(dropdown, function() {});
			}
		},
		"hide.bs.dropdown": function(e) {
			// On hide, start out effect
			var dropdown = dropdownEffectData(this);
			if (dropdown.effectOut) {
				e.preventDefault();
				dropdownEffectStart(dropdown, dropdown.effectOut);
				dropdownEffectEnd(dropdown, function() {
					dropdown.dropdown.removeClass('open');
				});
			}
		},
	});
});
