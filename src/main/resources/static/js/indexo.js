//生成菜单

var list_approve_name="";
var _meaus=zs_messages[localStorage.getItem('locale')].tabs;

(function ($) {
    $.dptab = {
        requestFullScreen: function () {
            var de = document.documentElement;
            if (de.requestFullscreen) {
                de.requestFullscreen();
            } else if (de.mozRequestFullScreen) {
                de.mozRequestFullScreen();
            } else if (de.webkitRequestFullScreen) {
                de.webkitRequestFullScreen();
            }
        },
        exitFullscreen: function () {
            var de = document;
            if (de.exitFullscreen) {
                de.exitFullscreen();
            } else if (de.mozCancelFullScreen) {
                de.mozCancelFullScreen();
            } else if (de.webkitCancelFullScreen) {
                de.webkitCancelFullScreen();
            }
        },
        refreshTab: function () {
            var currentId = $('.page-tabs-content').find('.active').attr('data-id');
            var target = $('.DP_iframe[data-id="' + currentId + '"]');
            var url = target.attr('src');
            target.attr('src', url).load(function () {

            });
        },
        activeTab: function () {
            var currentId = $(this).data('id');
            if (!$(this).hasClass('active')) {
                $('.mainContent .DP_iframe').each(function () {
                    if ($(this).data('id') == currentId) {
                        $(this).show().siblings('.DP_iframe').hide();
                        return false;
                    }
                });
                $(this).addClass('active').siblings('.menuTab').removeClass('active');
                $.dptab.scrollToTab(this);
            }
        },
        closeOtherTabs: function () {
            $('.page-tabs-content').children("[data-id]").find('.fa-remove').parents('a').not(".active").each(function () {
                $('.DP_iframe[data-id="' + $(this).data('id') + '"]').remove();
                $(this).remove();
            });
            $('.page-tabs-content').css("margin-left", "0");
        },
        closeTab: function () {
        	showAllMenu();
            var closeTabId = $(this).parents('.menuTab').data('id');
            var currentWidth = $(this).parents('.menuTab').width();
            if ($(this).parents('.menuTab').hasClass('active')) {
                if ($(this).parents('.menuTab').next('.menuTab').size()) {
                    var activeId = $(this).parents('.menuTab').next('.menuTab:eq(0)').data('id');
                    $(this).parents('.menuTab').next('.menuTab:eq(0)').addClass('active');

                    $('.mainContent .DP_iframe').each(function () {
                        if ($(this).data('id') == activeId) {
                            $(this).show().siblings('.DP_iframe').hide();
                            return false;
                        }
                    });
                    var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
                    if (marginLeftVal < 0) {
                        $('.page-tabs-content').animate({
                            marginLeft: (marginLeftVal + currentWidth) + 'px'
                        }, "fast");
                    }
                    $(this).parents('.menuTab').remove();
                    $('.mainContent .DP_iframe').each(function () {
                        if ($(this).data('id') == closeTabId) {
                            $(this).remove();
                            return false;
                        }
                    });
                }
                if ($(this).parents('.menuTab').prev('.menuTab').size()) {
                    var activeId = $(this).parents('.menuTab').prev('.menuTab:last').data('id');
                    $(this).parents('.menuTab').prev('.menuTab:last').addClass('active');
                    $('.mainContent .DP_iframe').each(function () {
                        if ($(this).data('id') == activeId) {
                            $(this).show().siblings('.DP_iframe').hide();
                            return false;
                        }
                    });
                    $(this).parents('.menuTab').remove();
                    $('.mainContent .DP_iframe').each(function () {
                        if ($(this).data('id') == closeTabId) {
                            $(this).remove();
                            return false;
                        }
                    });
                }
            }
            else {
                $(this).parents('.menuTab').remove();
                $('.mainContent .DP_iframe').each(function () {
                    if ($(this).data('id') == closeTabId) {
                        $(this).remove();
                        return false;
                    }
                });
                $.dptab.scrollToTab($('.menuTab.active'));
            }
            return false;
        },
        addTab: function () {
            $(".navbar-custom-menu>ul>li.open").removeClass("open");
            var dataId = $(this).attr('data-id');
            var dataUrl = $(this).attr('href');
            var menuName = $.trim($(this).text());
      
            if(dataUrl=="riskinfo/apply/list_approve.html"){
            	list_approve_name="iframe" + dataId;
            	//console.log("list_approveframe_name:"+list_approve_name);
            }
            
            var flag = true;
            if (dataUrl == undefined || $.trim(dataUrl).length == 0) {
                return false;
            }
            $('.menuTab').each(function () {
                if ($(this).data('id') == dataUrl) {
                    if (!$(this).hasClass('active')) {
                        $(this).addClass('active').siblings('.menuTab').removeClass('active');
                        $.dptab.scrollToTab(this);
                        $('.mainContent .DP_iframe').each(function () {
                            if ($(this).data('id') == dataUrl) {
                            	
                                $(this).show().siblings('.DP_iframe').hide();
                                return false;
                            }
                        });
                    }
                    flag = false;
                    return false;
                }
            });
            if (flag) {
            	if(dataUrl.indexOf("report")!=-1||dataUrl.indexOf("sign/company/sign.html")!=-1	){
                	hideAllMenu();//报表类全屏
            	}
            	//hideAllMenu();//报表类全屏
                var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-remove" ></i></a>';
                $('.menuTab').removeClass('active');
                var str1 = '<iframe class="DP_iframe" id="iframe' + dataId + '" name="iframe' + dataId + '"  width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
                $('.mainContent').find('iframe.DP_iframe').hide();
                $('.mainContent').append(str1);

                $('.mainContent iframe:visible').load(function () {

                });
                $('.menuTabs .page-tabs-content').append(str);
                $.dptab.scrollToTab($('.menuTab.active'));
            }
            return false;
        },
        scrollTabRight: function () {
            var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
            var tabOuterWidth = $.dptab.calSumWidth($(".content-tabs").children().not(".menuTabs"));
            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
            var scrollVal = 0;
            if ($(".page-tabs-content").width() < visibleWidth) {
                return false;
            } else {
                var tabElement = $(".menuTab:first");
                var offsetVal = 0;
                while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).next();
                }
                offsetVal = 0;
                while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).next();
                }
                scrollVal = $.dptab.calSumWidth($(tabElement).prevAll());
                if (scrollVal > 0) {
                    $('.page-tabs-content').animate({
                        marginLeft: 0 - scrollVal + 'px'
                    }, "fast");
                }
            }
        },
        scrollTabLeft: function () {
            var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
            var tabOuterWidth = $.dptab.calSumWidth($(".content-tabs").children().not(".menuTabs"));
            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
            var scrollVal = 0;
            if ($(".page-tabs-content").width() < visibleWidth) {
                return false;
            } else {
                var tabElement = $(".menuTab:first");
                var offsetVal = 0;
                while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).next();
                }
                offsetVal = 0;
                if ($.dptab.calSumWidth($(tabElement).prevAll()) > visibleWidth) {
                    while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                        offsetVal += $(tabElement).outerWidth(true);
                        tabElement = $(tabElement).prev();
                    }
                    scrollVal = $.dptab.calSumWidth($(tabElement).prevAll());
                }
            }
            $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            }, "fast");
        },
        scrollToTab: function (element) {
            var marginLeftVal = $.dptab.calSumWidth($(element).prevAll()),
                marginRightVal = $.dptab.calSumWidth($(element).nextAll());
            var tabOuterWidth = $.dptab.calSumWidth($(".content-tabs").children().not(".menuTabs"));
            var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
            var scrollVal = 0;
            if ($(".page-tabs-content").outerWidth() < visibleWidth) {
                scrollVal = 0;
            } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
                if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
                    scrollVal = marginLeftVal;
                    var tabElement = element;
                    while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                        scrollVal -= $(tabElement).prev().outerWidth();
                        tabElement = $(tabElement).prev();
                    }
                }
            } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
                scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
            }
            $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            }, "fast");
        },
        calSumWidth: function (element) {
            var width = 0;
            $(element).each(function () {
                width += $(this).outerWidth(true);
            });
            return width;
        },
        calSumHeight: function (element) {
            var height = 0;
            $(element).each(function () {
                height += $(this).outerHeight(true)
            });
            return height;
        },
        init: function () {
            $('.menuItem').on('click', $.dptab.addTab);
            $('.menuTabs').on('click', '.menuTab i', $.dptab.closeTab);
            $('.menuTabs').on('click', '.menuTab', $.dptab.activeTab);
            $('.tabLeft').on('click', $.dptab.scrollTabLeft);
            $('.tabRight').on('click', $.dptab.scrollTabRight);
            $('.tabReload').on('click', $.dptab.refreshTab);
            $('.tabCloseAll').on('click', function () {
                $('.page-tabs-content').children("[data-id]").find('.fa-remove').each(function () {
                    $('.DP_iframe[data-id="' + $(this).data('id') + '"]').remove();
                    $(this).parents('a').remove();
                });
                $('.page-tabs-content').children("[data-id]:first").each(function () {
                    $('.DP_iframe[data-id="' + $(this).data('id') + '"]').show();
                    $(this).addClass("active");
                });
                $('.page-tabs-content').css("margin-left", "0");
            });
            $('.tabCloseOther').on('click', $.dptab.closeOtherTabs);
            $("#sidebar-menu li a").click(function () {
                var d = $(this), e = d.next();
                if (e.is(".treeview-menu") && e.is(":visible")) {
                    e.slideUp(500, function () {
                        e.removeClass("menu-open")
                    }),
                        e.parent("li").removeClass("active")
                } else if (e.is(".treeview-menu") && !e.is(":visible")) {
                    var f = d.parents("ul").first(),
                        g = f.find("ul:visible").slideUp('fast');
                    g.removeClass("menu-open");
                    var h = d.parent("li");
                    var _height3 = $.dptab.calSumHeight($("#sidebar-menu>li"));
                    e.slideDown('fast', function () {
                        e.addClass("menu-open"),
                            f.find("li.active").removeClass("active"),
                            h.addClass("active");

                    })
                }
                e.is(".treeview-menu");
            });
        }
    };
    $.dpindex = {
        load: function () {
            $("body").removeClass("hold-transition")
          //  $("#content-wrapper").find('.mainContent').height($(window).height() - 133);
            $("#content-wrapper").find('.mainContent').height($(window).height()- 40);
            $("#sidebar-menu").height($(window).height() - 50);
            $(window).resize(function (e) {
                $("#content-wrapper").find('.mainContent').height($(window).height() - 40);
                $("#sidebar-menu").height($(window).height() - 50);
            });
        },
        jsonWhere: function (data, action) {
            if (action == null) return;
            var reval = new Array();
            $(data).each(function (i, v) {
                if (action(v)) {
                    reval.push(v);
                }
            })
            return reval;
        },
        loadMenu: function (data) {
            var _html = '';//"<li class='header'>导航菜单</li>";
            var setmian=false;
            $.each(data, function (i) {
                var row = data[i];
            

        	 if(!setmian&&row.url=="main.html"){
             	$("#main").attr("src","system/index/main.html");
            	setmian=true;
             	return;
             
             }
        	 if(!setmian&&row.url=="cs_main.html"){
              	$("#main").attr("src","cs/case/cs_main.html");
              	setmian=true;
              	return;
              }
               
                if (i == 0) {
                    _html += '<li class="active">';
                } else {
                    _html += '<li>';
                }
                if(row.type == 0) {
                    _html += "<a href='javascript:;'>";
                    if (row.icon != null) {
                        _html += "<i class='" + row.icon + "'></i>" +
                            "<span>" + (_meaus==undefined?row.name:_meaus[row.name]) + "</span>" +
                            "<i class='fa fa-angle-left pull-right'></i>" +
                            "</a>";
                    }
                    _html += "<ul class='treeview-menu'>"
                    _html += $.dpindex.getChild(row.list);
                    _html += "</ul>";
                }
                if(row.type == 1) {
                    if(row.icon == null){
                        row.icon = "fa fa-circle-o";
                    }
                    _html += "<a href='" + row.url + "' data-id='" + row.menuId + "' class='menuItem'><i class='" + row.icon + "'></i> " +(_meaus==undefined?row.name:_meaus[row.name]) + "</a>";
                }
                _html += "</li>";
            });
            
            if(!setmian){
              	$("#main").attr("src","system/index/otherMain.html");
            }
            
            $("#sidebar-menu").empty().append(_html);
        },
        getChild: function(data) {
            var _html = "";
            $.each(data, function (i) {
                var row = data[i];
                _html += "<li>";
                if(row.type == 0) {
                    _html += "<a href='javascript:;'>";
                    if (row.icon != null) {
                        _html += "<i class='" + row.icon + "'></i>" +
                            "<span>" + row.name + "</span>" +
                            "<i class='fa fa-angle-left pull-right'></i>" +
                            "</a>";
                    }
                    _html += "<ul class='treeview-menu'>"
                    _html += $.dpindex.getChild(row.list);
                    _html += "</ul>";
                }
                if(row.type == 1) {
                    if(row.icon == null){
                        row.icon = "fa fa-circle-o";
                    }
                    _html += "<a href='" + row.url + "' data-id='" + row.menuId + "' class='menuItem'><i class='" + row.icon + "'></i> " + 	(_meaus==undefined?row.name:_meaus[row.name]) + "</a>";
                }
                _html += "</li>";
            });
            return _html;
        }
    };
})(jQuery);



var vm = new Vue({
    el: '#cashbangLTE',
    i18n,
    data: {
        user: {},
        csChannel:"APP",//催收渠道，saas时候统一按APP处理
        csChannelName:"代帮主",//催收渠道，saas时候统一按APP处理
        menuList: {},
        main: "system/index/main.html",
        otherMain:"system/index/otherMain.html",
        csMain:"cs/caseLog/cs_main.html",
        pswd: null,
        newPswd: null,
        dataBase:{},
        chLanguage:false,
        international:false,//是否国际化
        sysName:"",//系统名称

    },
    methods: {
        geSysName: function () {
			$.ajax({
				type : "POST",
            	url:"sys/sysName?_" + $.now(),
				dataType : "json",
            	success:function(r){
	               vm.sysName = r.msg;
            	}
            }); 
        },
    	clang:function(la){
			this.chLanguage=false;
			localStorage.setItem('locale',la);
			i18n.locale=la;
			reload();
		},
        hideMenu: function () {
            if (!$("body").hasClass("sidebar-collapse")) {
                $("body").addClass("sidebar-collapse");
                removeScroll();
            } else {
                $("body").removeClass("sidebar-collapse");
                setScroll();
            }
        },
        fullScreen: function () {
            if (!$('#fullscreen').attr('fullscreen')) {
                $('#fullscreen').attr('fullscreen', 'true');
                $.dptab.requestFullScreen();
            } else {
                $('#fullscreen').removeAttr('fullscreen')
                $.dptab.exitFullscreen();
            }
        },
        tabCloseCurrent: function() {
            $('.page-tabs-content').find('.active i').trigger("click");
        },
        getMenuList: function (event) {
            $.getJSON("sys/menu/user?_" + $.now(), function (r) {
                vm.menuList = r.menuList;
            });
        },
        getDataBase: function (event) {
            $.getJSON("sys/user/nowDataBase?_" + $.now(), function (r) {
                if(r.msg.evn=="prod"){
                	  vm.dataBase.show = false;
                }else{
                	vm.dataBase = r.msg;
                	vm.dataBase.show = true;
                }
            });
        },
        getInternational: function (event) {
            $.getJSON("sys/international?_" + $.now(), function (r) {
                if(r.msg=="true"){
                	  vm.international = true;
                }else{
                	vm.international = false;
                }
                vm.international = true;
            });
        },
        getSysName: function () {
			$.ajax({
				type : "POST",
            	url:"sys/sysName?_" + $.now(),
				dataType : "json",
            	success:function(r){
	               vm.sysName = r.msg;
            	}
            }); 
        },
        getPermList: function (event) {
            $.getJSON("sys/user/perms?_" + $.now(), function (r) {
                window.perms = r.rows;
            });
        },
        getUser: function () {
            $.getJSON("sys/user/info?_" + $.now(), function (r) {
                vm.user = r.user;
               
                vm.csChannel=(vm.user.channel==null||vm.user.channel=="")?"APP":vm.user.channel;
                vm.csChannelName=(vm.user.channel==null||vm.user.channel=="")?"代帮主":vm.user.channelName;
                if(vm.user.channelName==null||vm.user.channelName==""){
                	vm.user.channelNamef="";
                }else{
                	vm.user.channelNamef=vm.user.channelName+"/";
                }
                window.user = r.user;
            });
        },
        updatePassword: function () {
            dialogContent({
                title: zsl("修改密码"),
                width: '420px',
                height: '250px',
                content: $("#passwordLayer"),
                btn: [zsl('确定'), zsl('取消')],
                yes: function (index) {
                    if (isNullOrEmpty(vm.pswd)) {
                        dialogMsg(zsl('原密码为空!'));
                        return false;
                    }
                    if (isNullOrEmpty(vm.newPswd)) {
                        dialogMsg(zsl('新密码为空!'));
                        return false;
                    }
                    var data = "pswd=" + vm.pswd + "&newPswd="
                        + vm.newPswd;
                    $.ajax({
                        type: "POST",
                        url: "sys/user/updatePswd?_" + $.now(),
                        data: data,
                        dataType: "json",
                        success: function (result) {
                            if (result.code == 0) {
                                layer.close(index);
                                dialogMsg(result.msg, 'success');
                                location.reload();
                            } else {
                                dialogAlert(result.msg, 'error');
                            }
                        }
                    });
                }
            });
        },
        logout: function () {
            layer.open({
                title: zsl('系统提示'),
                area: '338px',
                icon: 3,
                anim: -1,
                isOutAnim: false,
                move: false,
                content: zsl('注：您确定要安全退出本次登录吗？'),
                btn: [zsl('确定'), zsl('取消')],
                btnAlign: 'c',
                yes: function () {
                    dialogLoading(true);
                    setTimeout(function() {
                        $.ajax({
                            type: "POST",
                            url: "sys/logout",
                            dataType: "json",
                            success: function(r){
                                localStorage.removeItem("token");
                                toUrl('login.html');
                            }
                        });
                    }, 500);
                }
            });
        }
    },
    created: function () {
        this.getMenuList();
        this.getPermList();
        this.getUser();
        this.getDataBase();
        this.getInternational();
        this.getSysName();
       // this.hideMenu();
    },
    updated: function () {
        $.dpindex.load();
        $.dpindex.loadMenu(this.menuList);
        $.dptab.init();
        setScroll();
    }
});

//菜单滚动条自适应
function setScroll() {
    if (!$("body").hasClass("sidebar-collapse")) {
        $("#sidebar-menu").slimScroll({
            height: $(this).height() - 50,
            alwaysVisible: false,
        });
    }
    $(window).on("resize", function() {
        if (!$("body").hasClass("sidebar-collapse")) {
            $("#sidebar-menu").slimScroll({
                height: $(this).height() - 50,
                alwaysVisible: false,
            });
        }
    });
}

function removeScroll() {
    $('.sidebar').append($('#sidebar-menu'));
    $('#sidebar-menu').removeAttr('style');
    $('.slimScrollDiv').remove();
}
//新加彈出tab

var dataIdmax=10000;//从10000开始id应该不会冲突
function openNewTab(dataUrl,menuName){
	
	//dataUrl=encodeURI(encodeURI(dataUrl));
	/*console.log(IEVersion())*/
	if(IEVersion_IEVersion){
		dataUrl=encodeURI(encodeURI(dataUrl));
	}
	if (dataUrl == undefined || $.trim(dataUrl).length == 0) {
        return false;
    }
    var op=true;
    
    $('.menuTab').each(function () {
        if ($(this).data('id') == dataUrl) {
                $(this).addClass('active').siblings('.menuTab').removeClass('active');
                $('.mainContent .DP_iframe').each(function () {
                	if($(this).attr('src')==dataUrl){
                		$(this).show().siblings('.DP_iframe').hide();
                		op=false;
                	}
                });
                op = false;
                return false;
            };
    });
	if(op){
		dataIdmax+=1;
		var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-remove" onclick="showAllMenu()"></i></a>';
		$('.menuTab').removeClass('active');
		var str1 = '<iframe class="DP_iframe" id="iframe' + dataIdmax + '" name="iframe' + dataIdmax + '"  width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
		$('.mainContent').find('iframe.DP_iframe').hide();
		$('.mainContent').append(str1);
		$('.mainContent iframe:visible').load(function () {

		});
		$('.menuTabs .page-tabs-content').append(str);
		$.dptab.scrollToTab($('.menuTab.active'));
	}
	
};
function refreshsp(){
	//此处msg报错没问题，以后再说
	window.frames[list_approve_name].refresh();
}
 function hideAllMenu() {
    if (!$("body").hasClass("sidebar-collapse")) {
        $("body").addClass("sidebar-collapse");
        removeScroll();
    }/* else {
        $("body").removeClass("sidebar-collapse");
        setScroll();
    }*/
}
 function showAllMenu() {
	 if ($("body").hasClass("sidebar-collapse")) {
	        $("body").removeClass("sidebar-collapse");
	        setScroll();
	 }
}


