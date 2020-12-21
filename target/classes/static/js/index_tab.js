//生成菜单

var _meaus=zs_messages[localStorage.getItem('locale')].tabs;


var vm = new Vue({
    el: '#zsvm',
    i18n,
    data: {
    	

    	isM:false,//是手机
        user: {},
        csChannel:"APP",// 催收渠道，saas时候统一按APP处理
        csChannelName:"代帮主",// 催收渠道，saas时候统一按APP处理
        autoOpen:localStorage.getItem('autoOpen')==null?true:(localStorage.getItem('autoOpen')=="true"),//自动收缩菜单
        
        menuList: {},
        main: "system/index/main.html",
        main_approval:"system/index/main_approval.html",
        otherMain:"system/index/otherMain.html",
        csMain:"cs/case/cs_main.html",
        mainHtml:"system/index/otherMain.html",// 主页
        
        
        pswd: null,
        newPswd: null,
        dataBase:{},
        chLanguage:false,
        international:false,// 是否国际化
        sysName:"",// 系统名称
        fullscreen:false,// 是否全屏
        loading:true,//加载中
        passwordLayer :false,//修改密码
        
        mainHeight:$(window).height()-85,// menu高度
        _meaus:zs_messages[localStorage.getItem('locale')].tabs,// 国际化菜单
        isCollapse:false,// 菜单收起
        asideWidth:200,
        mainWidth:$(window).width()-200,
        tabs:[
        	
        ],// nowTags
        nowTab:'main',
        tab_index:0,
        pTabs:{},//用以判定用tab方式打开新页面的父页面是哪个,
        needLoadingUrls:["riskinfo/apply/details.html","cs/case/caseMsg.html","riskinfo/trade/tradeMsg.html"],//需要加载效果的url
        
    },
    watch:{
    	"autoOpen":function(a,b){
    		localStorage.setItem('autoOpen',a);
    	},
    },
    methods: {
    	menuClick:function(onlyId){
        	this.nowTab=onlyId

    	},
    	getMainType:function(){
    		var th=this;
    		try{
    			zs_post({
        			url:"sys/isApproval?_" + $.now(),
        	    	success:function(r){
        	    		if(r.code==0){
            	    		if(r.msg){
            	   	            th.mainHtml=th.main_approval;
            	    		}else{
            	   	            th.mainHtml=th.main;
            	    		}
        	    		}else{
        	   	            th.mainHtml=th.main;
        	    		}
        	    	}
        		})
    		}catch(e){
   	            th.mainHtml=th.main;
    		}
    		
    	},
    	// 逻辑类
    	clang:function(la){// 语言版本
			this.chLanguage=false;
			localStorage.setItem('locale',la);
			i18n.locale=la;
			reload();
		},
		
		getMenuList: function (event) {// 获取菜单列表并判断主页
			   var th=this;
	            $.getJSON("sys/menu/user?_" + $.now(), function (r) {
	            	th.menuList = r.menuList;
	            	
	            	for(var i=0;i<th.menuList.length;i++){
	            		  var row = th.menuList[i];
	 	   	        	 if(row.url=="main.html"){
	 	   	         	     th.getMainType();
	 	   	             	  return;
	 	   	             }
	 	   	        	 if(row.url=="cs_main.html"){
	 	   	        		th.mainHtml=th.csMain;
	 	   	              	 return;
	 	   	              }
	            	}
	            });
	        },
	        getDataBase: function (event) {// 获取当前数据库
	            $.getJSON("sys/user/nowDataBase?_" + $.now(), function (r) {
	                if(r.msg.evn=="prod"){
	                	  vm.dataBase.show = false;
	                }else{
	                	vm.dataBase = r.msg;
	                	vm.dataBase.show = true;
	                }
	            });
	        },
	        getInternational: function (event) {// 是否国际化
	            $.getJSON("sys/international?_" + $.now(), function (r) {
	                if(r.msg=="true"){
	                	  vm.international = true;
	                }else{
	                	vm.international = false;
	                }
	            });
	        },
	        getSysName: function () {// 系统名称
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
	                vm.loading=false;
	                window.user = r.user;
	            });
	        },
	        updatePassword: function () {
	        	    var th=this;
                    if (isNullOrEmpty(vm.pswd)) {
                        th.$message.error({message:zsl('原密码为空!'),
      			          showClose: true,
      				  });
                        return false;
                    }
                    if (isNullOrEmpty(vm.newPswd)) {
                        th.$message.error({message:zsl('新密码为空!'),
        			          showClose: true,
        				});
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
                                th.$message({message:result.msg,
              			          showClose: true,
              				    });
                                location.reload();
                            } else {
                            	  th.$message.error({message:result.msg,
                			          showClose: true,
                				});
                            }
                        }
                    });
	        },
	        logout: function () {
	        	var th=this;
	        	this.$confirm('您确定要安全退出本次登录吗？')
	            .then(function(){
	            	th.loading=true;
	            	  setTimeout(function() {
	            		  
	            		  /*delCookie('username'); //保存帐号到cookie，有效期7天
	  					  delCookie('password'); //保存密码到cookie，有效期7天
	  					  delCookie("savepassword");*/
	            		  
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
	            }).catch(function(){})
	        },
	        
	        doUser:function(p){
	        	if(p=="changePassWord"){
	        		this.passwordLayer=true;
	        	}
	        	if(p=="logOut"){
	        		this.logout();
	        	}
	        	if(p=="changerLangue"){
	        		this.chLanguage=true;
	        	}
	        	
	        },
	   // css类
        hideMenu: function () {// 隐藏
        	this.isCollapse=true;
    		this.asideWidth=60;
        	this.mainWidth=$(window).width()-110;
        },
        showHideMenu: function () {// 隐藏
        	this.isCollapse=!this.isCollapse;
        	if(this.isCollapse){
        		this.asideWidth=60;
            	this.mainWidth=$(window).width()-110;
        	}else{
        		this.asideWidth=200;
            	this.mainWidth=$(window).width()-250;
        	}
        	
        },
        
        fullScreen: function () {// 切换全屏
            if (!this.fullscreen) {
            	this.fullscreen=true;
                this.requestFullScreen();
            } else {
            	this.fullscreen=false;
                this.exitFullscreen();
            }
        },
        requestFullScreen: function () {// 全屏
            var de = document.documentElement;
            if (de.requestFullscreen) {
                de.requestFullscreen();
            } else if (de.mozRequestFullScreen) {
                de.mozRequestFullScreen();
            } else if (de.webkitRequestFullScreen) {
                de.webkitRequestFullScreen();
            }
        },
        exitFullscreen: function () {// 退出全屏
            var de = document;
            if (de.exitFullscreen) {
                de.exitFullscreen();
            } else if (de.mozCancelFullScreen) {
                de.mozCancelFullScreen();
            } else if (de.webkitCancelFullScreen) {
                de.webkitCancelFullScreen();
            }
        },
        
        addTab:function(row){
        	this.tab_index++;
        	
        	for(var i=0;i<this.tabs.length;i++){
        		if(this.tabs[i].url===row.url){
        			this.nowTab=this.tabs[i].onlyId;
        			return;
        		}
        	}
        	
        	var onlyId="onlyId"+this.tab_index;
        	row.onlyId=onlyId;
        	
        

        	this.tabs.push(row);
        	this.nowTab=row.onlyId;
        	
        	
        	if(this.autoOpen||this.nowTab.indexOf("report")!=-1||this.nowTab.indexOf("sign/company/sign.html")!=-1	
        			||this.nowTab.indexOf("caseMsg.html")!=-1){
                	this.hideMenu();//报表类全屏
            };
           
        },
        
        
        endTabLoading:function(onlyId){//关闭tabloading
        	
        	for(var i=0;i<this.tabs.length;i++){
        		if(this.tabs[i].onlyId===onlyId){
        			this.tabs[i].loading=false;
        			return;
        		}
        	}
        },
        refreshNowTab:function(){
        	window.frames[this.nowTab].location.reload();
        },
        removeTab:function(onlyId){
    		if(onlyId==undefined||onlyId==null||onlyId==''||onlyId=="main"){
    			return;
    		}
        	
            var tabs = this.tabs;
            var newOnlyId="";
            tabs.forEach(function(tab, index){
	            if (tab.onlyId === onlyId) {
	              var nextTab = tabs[index + 1] || tabs[index - 1];
	              if (nextTab) {
	            	  newOnlyId = nextTab.onlyId;
	              }
	            }
           });
            this.nowTab = newOnlyId;
            this.tabs = tabs.filter(
            function(tab){
            	return tab.onlyId != onlyId;
            });
            if(this.tabs.length==0){
            	this.nowTab="main";
            };
            if(this.autoOpen){
            	this.asideWidth=200;
            	this.mainWidth=$(window).width()-250;
            	this.isCollapse=false;
            }
     
    	},
        removeOtherTabs:function(){
            var tabs = this.tabs;
            var th=this;
            this.tabs = tabs.filter(
            function(tab){
            	return tab.onlyId == th.nowTab||tab.url=="main";
            });
            if(this.tabs.length==0){
            	this.nowTab="main";
            }
     
    	},
    	 refreshsp:function(onlyId){//刷新
    		// 此处msg报错没问题，以后再说
    		 if(onlyId==undefined||onlyId==null){
    			 return;
    		 }
    		 
    		 var p=this.pTabs[onlyId];
    		 
    		 
    		 if(p==undefined||p==null){
    			 return;
    		 }
    		 
    		 try{//兼容之前的版本
    			window.frames[p].refresh();
    		 }catch(e){
    			window.frames[p].location.reload();
    		 }
    		 
    	}
     
    },
    
    created: function () {
    
    	if($(window).width()<992){
    		this.isM=true;
        	this.hideMenu();//报表类全屏

    	}
        this.getMenuList();
        this.getPermList();
        this.getUser();
        this.getDataBase();
        this.getInternational();
        this.getSysName();
        
    },
    updated: function () {
    }
});


window.addEventListener("orientationchange", function() {
    location.reload();
}, false);


function openNewTab(dataUrl,menuName){
	
	
	for(var i=0;i<vm.tabs.length;i++){

		console.log(dataUrl==vm.tabs[i].url)

		if(dataUrl==vm.tabs[i].url){
			vm.nowTab=vm.tabs[i].onlyId;//定位
			return;
		}
		
	}
	
	var parentTab=vm.nowTab;
	
	vm.tab_index++;
	var onlyId="onlyId"+vm.tab_index;
	
	vm.pTabs[onlyId]=parentTab;//建立父子关系
	
	var newTabJson=	{//添加标签
			url:dataUrl,
			name:menuName,
			onlyId:onlyId
	};
	
	for(var i=0;i<vm.needLoadingUrls.length;i++){
		if(dataUrl.indexOf( vm.needLoadingUrls[i])!=-1){
			newTabJson.loading=true;
		}
	}
	
	vm.tabs.push(newTabJson)
	
	vm.nowTab=onlyId;//定位
	if(dataUrl.indexOf("caseMsg.html")!=-1){
        	vm.hideMenu();//报表类全屏
    }
	

};
function refreshsp(){
}
 function hideAllMenu() {
     vm.hideMenu();
}
 function showAllMenu() {
	 vm.hideMenu();
}

 window.onresize = function(){
	 
     vm.mainHeight=$(window).height()-85;// menu高度

}

 

//设置cookie
 function setCookie(name,value,day){
   var date = new Date();
   date.setDate(date.getDate() + day);
   document.cookie = encrypt(name) + '=' + encrypt(value) + ';expires='+ date;
 };
 //删除cookie
 function delCookie(name){
   setCookie(name,null,-1);
 };