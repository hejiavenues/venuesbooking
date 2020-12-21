// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
}

var zs_ocale=localStorage.getItem('locale');

fmoney = function(param, n) {
	var s="";
	var istrue=param instanceof jQuery;
	if(istrue){
		s = param.val();
		
	}else if(param instanceof String){
		s=param;
	}else{
		s=param+"";
	}
	s=s.replace(/[^\d.]/g,'');
	n = n > 0 && n <= 20 ? n : 2; 
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + ""; 
	if(istrue){
		if(isNaN(s)||s==""){
			param.val("");
			return ;
		}
	}
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1]; 
	t = ""; 
	for (i = 0; i < l.length; i++) { 
	t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
	} 
	var fm = t.split("").reverse().join("") + "." + r;
	if(istrue){
		param.val(fm);
	}
	return fm; 
} 
sfmoney = function(param, n) {
	var s="";
	var istrue=param instanceof jQuery;
	if(istrue){
		s = param.val();
		
	}else if(param instanceof String){
		s=param;
	}else{
		s=param+"";
	}
	s=s.replace(/[^\d.]/g,'');
	n = n > 0 && n <= 20 ? n : 2; 
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + ""; 
	if(istrue){
		if(isNaN(s)||s==""){
			param.val("");
			return ;
		}
	}
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1]; 
	t = ""; 
	for (i = 0; i < l.length; i++) { 
	t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	} 
	var fm = t.split("").reverse().join("") + "." + r;
	if(istrue){
		param.val(fm);
	}
	return fm; 
} 

function fix_amountthis(obj){
    var fix_amount=$(obj).val();
    var fix_amountTest=/^(([1-9]\d*)|\d)(\.\d{1,2})?$/;
    if(fix_amountTest.test(fix_amount)==false){
        alert(zsl("请输入有效金额"));
        return;
       }
    }

//登录token
var token = localStorage.getItem("token");

if(token == 'null'){
	noTokenInfo();
}
//全局配置
$.ajaxSetup({
	dataType: "json",
	cache: false,
    headers: {
        "token": token,
        "locale":zs_ocale,
    },
    complete: function(xhr) {
        if(xhr.responseJSON.code == 401){
        	noTokenInfo();
        }
    }
})

//提示登录失效
function noTokenInfo(){
	console.log(	top.location.href,
			top.location.href.indexOf('index')!=-1
)
	try{
		   vm.$alert(zsl('您的登陆已经失效，请您重新登陆'), '提示', {
	          confirmButtonText: zsl('确认'),
	          callback: function(){
	        	  toUrl('../../login.html');
	          }
	        });
		
	}catch(e){
		top.layer.confirm(zsl('您的登陆已经失效，请您重新登陆'), {
			area: '338px',
			icon: 2,
	        anim: -1,
			isOutAnim: false,
	        title: zsl("系统提示"),
	        btn: [zsl('确认')],
	        btnAlign: 'c',
	    	yes: function(){
	        	  toUrl('../../login.html');
	    	}
	    });
	}
}

//权限判断
function hasPermission(permission) {
    if(isNullOrEmpty(window.parent.perms)) {
    	return false;
    }
	if (window.parent.perms.indexOf(permission) > -1) {
        return true;
    } else {
        return false;
    }
}

toUrl = function(href) {
	window.location.href = href;
}


formatDate = function (v, format) {
    if (!v) return "";
    var d = v;
    if (typeof v === 'string') {
        if (v.indexOf("/Date(") > -1)
            d = new 
(parseInt(v.replace("/Date(", "").replace(")/", ""), 10));
        else
            d = new Date(Date.parse(v.replace(/-/g, "/").replace("T", " ").split(".")[0]));//.split(".")[0] 用来处理出现毫秒的情况，截取掉.xxx，否则会出错
    }
    var o = {
        "M+": d.getMonth() + 1,
        "d+": d.getDate(),
        "h+": d.getHours(),
        "m+": d.getMinutes(),
        "s+": d.getSeconds(),
        "q+": Math.floor((d.getMonth() + 3) / 3),
        "S": d.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (d.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

function today() {
    var dd = new Date();
    return formatDate(dd, 'yyyy-MM-dd');
}

function countDay(dayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+dayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0
    var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate();//获取当前几号，不足10补0
    return y+"-"+m+"-"+d;
}

isNullOrEmpty = function (obj) {
    if ((typeof (obj) == "string" && obj == "") || obj == null || obj == undefined) {
        return true;
    } else {
        return false;
    }
}

isNotNullOrEmpty = function (obj) {
    if ((typeof (obj) == "string" && obj == "") || obj == null || obj == undefined) {
        return false;
    } else {
        return true;
    }
}

checkedArray = function (id) {
    var isOK = true;
    if (id == undefined || id == "" || id == 'null' || id == 'undefined') {
        isOK = false;
        dialogMsg(zsl('您没有选中任何数据项！'));
    }
    return isOK;
}

checkedEx = function (table,col,only,msg) {
	
	var  ex=false;
	$.ajax({
		   type: "get",
		      async:false, 
		   url: '../../sys/org/selectOnly?table='+table+'&col='+col+'&only='+only,
		   success: function(r){
			    if (r.count>0) {
			        dialogMsg(msg);
			        ex=true;
			    }else{
			        ex=false;
			    }
		   }
	 });
	return ex;
}

checkedRow = function (id) {
    var isOK = true;
    if (id == undefined || id == "" || id == 'null' || id == 'undefined') {
        isOK = false;
        dialogMsg(zsl('您没有选中任何数据项！'));
    } else if (id.length > 1) {
        isOK = false;
        dialogMsg(zsl('您只能选择一条数据项！'));
    }
    return isOK;
}

reload = function () {
    location.reload();
    return false;
}

dialogOpen = function(opt){
	var defaults = {
		id : 'layerForm',
		title : '',
		width: '',
		height: '',
		url : null,
		scroll : false,
		data : {},
		btn: [zsl('确定'), zsl('取消')],
		success: function(){},
		yes: function(){}
	}
	var option = $.extend({}, defaults, opt), content = null;
	if(option.scroll){
		content = [option.url]
	}else{
		content = [option.url, 'no']
	}
	top.layer.open({
	  	type : 2,
	  	id : option.id,
		title : option.title,
		closeBtn : 1,
		anim: -1,
		isOutAnim: false,
		shadeClose : false,
		shade : 0.3,
		area : [option.width, option.height],
		content : content,
		btn: option.btn,
		success: function(){
			option.success(option.id);
		},
		yes: function(){
			option.yes(option.id);
		},
		anim: 2
    });
}

dialogContent = function(opt){
	var defaults = {
		title : zsl('系统窗口'),
		width: '',
		height: '',
		content : null,
		data : {},
		btn: [zsl('确定'), zsl('取消')],
		success: null,
		yes: null
	}
	var option = $.extend({}, defaults, opt);
	return top.layer.open({
	  	type : 1,
		title : option.title,
		closeBtn : 1,
		anim: -1,
		isOutAnim: false,
		shadeClose : false,
		shade : 0.3,
		area : [option.width, option.height],
		shift : 5,
		content : option.content,
		btn: option.btn,
		success: option.success,
		yes: option.yes
    });
}

dialogAjax = function(opt){
	var defaults = {
		title : zsl('系统窗口'),
		width: '',
		height: '',
		url : null,
		data : {},
		btn: [zsl('确定'), zsl('取消')],
		success: null,
		yes: null
	}
	var option = $.extend({}, defaults, opt);
	$.post(option.url, null, function(content){
		layer.open({
		  	type : 1,
			title : option.title,
			closeBtn : 1,
			anim: -1,
			isOutAnim: false,
			shadeClose : false,
			shade : 0.3,
			area : [option.width, option.height],
			shift : 5,
			content : content,
			btn: option.btn,
			success: option.success,
			yes: option.yes
	    });
	});
}

dialogAlert = function (content, type) {
	var msgType = {
		success:1,
		error:2,
		warn:3,
		info:7
	};
	if(isNullOrEmpty(type)){
		type='info';
	}
	top.layer.alert(content, {
        icon: msgType[type],
		title : zsl('系统提示'),
        anim: -1,
        btnAlign: 'c',
		isOutAnim: false
    });
}

dialogConfirm = function (content, callBack) {
	top.layer.confirm(content, {
		area: '338px',
		icon: 3,
        anim: -1,
		isOutAnim: false,
		title : zsl('系统提示'),
		btn: [zsl('确定'), zsl('取消')],
        btnAlign: 'c',
    	yes: callBack
    });
}

dialogMsg = function(msg, type) {
	var msgType = {
		success:1,
		error:2,
		warn:3,
		info:7
	};
	if(isNullOrEmpty(type)){
		type='info';
	}
	top.layer.msg(msg, {
		icon: msgType[type],
		time: 2000
	}); 
}

dialogClose = function() {
	var index = top.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	top.layer.close(index); //再执行关闭 
}

dialogLoading = function(flag,time) {
	if(flag){
		if(time!=undefined&&time!=null){
			if(time>0){
				top.layer.load(0, {
					shade: [0.1,'#fff'],
					time: time
				});
			}else if(time==0){
				top.layer.load(0, {
					shade: [0.1,'#fff'],
				});
			}
		}else{
			top.layer.load(0, {
				shade: [0.1,'#fff'],
				time: 2000
			});
		}
		
	}else{
		top.layer.closeAll('loading');
	}
}

$.fn.GetWebControls = function (keyValue) {
    var reVal = "";
    $(this).find('input,select,textarea').each(function (r) {
        var id = $(this).attr('id');
        var type = $(this).attr('type');
        switch (type) {
            case "checkbox":
                if ($("#" + id).is(":checked")) {
                    reVal += '"' + id + '"' + ':' + '"1",'
                } else {
                    reVal += '"' + id + '"' + ':' + '"0",'
                }
                break;
            default:
                var value = $("#" + id).val();
                if (value == "") {
                    value = "&nbsp;";
                }
                reVal += '"' + id + '"' + ':' + '"' + $.trim(value) + '",'
                break;
        }
    });
    reVal = reVal.substr(0, reVal.length - 1);
    if (!keyValue) {
        reVal = reVal.replace(/&nbsp;/g, '');
    }
    reVal = reVal.replace(/\\/g, '\\\\');
    reVal = reVal.replace(/\n/g, '\\n');
    var postdata = jQuery.parseJSON('{' + reVal + '}');
    return postdata;
};

$.fn.SetWebControls = function (data) {
    var $id = $(this)
    for (var key in data) {
        var id = $id.find('#' + key);
        if (id.attr('id')) {
            var type = id.attr('type');
            var value = $.trim(data[key]).replace(/&nbsp;/g, '');
            switch (type) {
                case "checkbox":
                    if (value == 1) {
                        id.attr("checked", 'checked');
                    } else {
                        id.removeAttr("checked");
                    }
                    break;
                default:
                    id.val(value);
                    break;
            }
        }
    }
}

tabiframeId = function () {
	try{
		if(top.vm.nowTab==undefined|| top.vm.nowTab==null){
			var iframeId = top.$(".DP_iframe:visible").attr("id");
			 return iframeId;
		}
		return top.vm.nowTab;
	}catch(e){//兼容
	}
}

$.currentIframe = function () {
    var tabId = tabiframeId();
	if(isNullOrEmpty(tabId)) {//单页iframe嵌套
		return $(window.parent.document).contents().find('#main')[0].contentWindow;
    }
    return $(window.parent.document).contents().find('#'+tabiframeId())[0].contentWindow;//多层tab页嵌套
}

function getQueryString(name,z){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r="";
     if(IEVersion_IEVersion){
       r = decodeURI(decodeURI(window.location.search.substr(1))).match(reg);
     }else{
       r = decodeURI(window.location.search.substr(1)).match(reg);
     }
     if(r!=null){
    	 return  unescape(r[2]); 
     }
    	
     return null;
}
IEVersion_IEVersion=false;
IEVersion_IEVersion=IEVersion();
function IEVersion() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器  
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器  
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if(isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if(fIEVersion == 7) {
          //  return 7;
       	 return true;
        } else if(fIEVersion == 8) {
           // return 8;
       	 return true;
        } else if(fIEVersion == 9) {
          //  return 9;
       	 return true;
        } else if(fIEVersion == 10) {
         //   return 10;
       	 return true;
        } else {
            //return 6;//IE版本<=7
       	 return true;
        }   
    } else if(isEdge) {
      //  return 'edge';//edge
   	 return false;
    } else if(isIE11) {
       // return 11; //IE11 
   	 return true;
 
    }else{
       // return -1;//不是ie浏览器
   	
     	 return false;
    }
}


function toBrotherDo(url,a,b,c,d,e,f){
	try{
		 parent.$("#"+url)[0].contentWindow.MAKE(a, b,c,d,e,f);
	}catch(e){
		console.log('执行错误:',url,e)
	}
}
function zs_loading(t){
	if(t){
		layer.load(0, {
			shade: [0.1,'#fff'],
		});
		return;
	}
	if(!t){
		layer.closeAll();
		return;
	}
}

function zs_post(options){
		var defaults = {
			url : "",
			param :{},
			type : "post",
			dataType : "json",
			contentType : 'application/json',
		    async:true,
			success : null,
			pageSize:99999
		};
		var options = $.extend(defaults, options);
		options.param.sortOrde="asc";
		if(options.param.pageSize==undefined){
			options.param.pageSize=99999;
		}
		if(options.param.pageNumber==undefined){
			options.param.pageNumber=1;
		}
		console.log(JSON.stringify(options.param));
		$.ajax({
			url : options.url,
			data : JSON.stringify(options.param),
			type : options.type,
			dataType : options.dataType,
			async:options.async,
			contentType : options.contentType,
			success : function(data) {
				options.success&&options.success(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				options.error&&options.error(XMLHttpRequest);
			},
			beforeSend : function() {
				dialogLoading(true);
			},
			complete : function(xhr) {
				  if(xhr.responseJSON.code == 401){
			        	
					  noTokenInfo();
			        	
			        }
				dialogLoading(false);
			}
		});
};

function zs_postFormA(th,options){//发送form 表单数据主要是文件
	 
	 var data=options.param;
	 var formData = new FormData();
	 if(data!=undefined){
		 for(key in data){
			  formData.append(key,data[key]);
		 }
	 }
	$.ajax({
		url : options.url,
		data : formData,
		type : "post",
		dataType : "json",
		async:options.async,
		contentType : false,
		processData:false,
		success : function(data) {
			
			var msg=data.msg
			if(data.msg.message!=undefined){
				msg=data.msg.message.desc
			}
			
			if (data.code == '500') {
				th.$message.error({message:msg,
			          showClose: true,
				});
			} else if(data.code == '100'){
				th.$message({message: msg,
			          showClose: true,
						type: 'warning'});
			} else if (data.code == '0') {
				options.success&&options.success(data);
				th.$message({message:msg,
			          showClose: true,
						type: 'success'});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			options.error&&options.error(XMLHttpRequest);
		},
		beforeSend : function() {
			dialogLoading(true);
		},
		complete : function(xhr) {
			  if(xhr.responseJSON.code == 401){
		        	
				  noTokenInfo();
		        	
		        }
			dialogLoading(false);
		}
	});
}

function zs_postA(th,options){//加提示
		var defaults = {
			url : "",
			param :{},
			type : "post",
			dataType : "json",
			contentType : 'application/json',
		    async:true,
			success : null,
		};
		var options = $.extend(defaults, options);
		$.ajax({
			url : options.url,
			data : JSON.stringify(options.param),
			type : options.type,
			dataType : options.dataType,
			async:options.async,
			contentType : options.contentType,
			success : function(data) {
				var msg="";
				if(data.msg!=undefined){
					msg=data.msg
					if(data.msg.message!=undefined){
						msg=data.msg.message.desc
					}
				}
				
				
				if (data.code == '500') {
					th.$message.error({message:msg,
				          showClose: true,
					});
				} else if(data.code == '100'){
					th.$message({message: msg,
				          showClose: true,
							type: 'warning'});
				} else if (data.code == '0') {
					options.success&&options.success(data);
					th.$message({message:msg,
				          showClose: true,
							type: 'success'});
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				options.error&&options.error(XMLHttpRequest);
			},
			beforeSend : function() {
				dialogLoading(true);
			},
			complete : function(xhr) {
				  if(xhr.responseJSON.code == 401){
			        	
					  noTokenInfo();
			        	
			        }
				dialogLoading(false);
			}
		});
};




//arrToJson方法-
function arrToJson(a,b,c){
	var json={};
	for(var i=0;i<a.length;i++){
		json[a[i][b]]=a[i][c];
	}
	return json;
}


function hdIdno(str){
	if(str==undefined){
		return str;
	}
	return str.replace(str.substring(3,14),'****');
}
function hdm(str,dot){//手机脱敏
	if(str==undefined){
		return undefined;
	}
	if(dot!=undefined&&!dot){
			return str;
	}
	return str.replace(str.substring(3,7),'****');
}

 function strip (num) {
	return +parseFloat(num.toPrecision(4));
}
 
 

 function zs_postForm(options){//发送form 表单数据主要是文件
 	 
 	 var data=options.param;
 	 var formData = new FormData();
 	 if(data!=undefined){
 		 for(key in data){
 			  formData.append(key,data[key]);
 		 }
 	 }
 	$.ajax({
 		url : options.url,
 		data : formData,
 		type : "post",
 		dataType : "json",
 		async:options.async,
 		contentType : false,
 		processData:false,
 		success : function(data) {
 			
 			var msg=data.msg
 			if(data.msg.message!=undefined){
 				msg=data.msg.message.desc
 			}
 			
 			if (data.code == '500') {
 			} else if(data.code == '100'){
 			} else if (data.code == '0') {
 				options.success&&options.success(data);
 			}
 		},
 		error : function(XMLHttpRequest, textStatus, errorThrown) {
 			options.error&&options.error(XMLHttpRequest);
 		},
 		beforeSend : function() {
 			dialogLoading(true);
 		},
 		complete : function(xhr) {
 			  if(xhr.responseJSON.code == 401){
 		        	
 				 noTokenInfo();
 		        	
 		        }
 			dialogLoading(false);
 		}
 	});
 }
 
 
 function getdecisionName(decisionResult,_this){//获取审批对应名称
	var r=
 		decisionResult==0?_this.$t("s.v0"):
 	    decisionResult==10?_this.$t("s.v10"):
 		decisionResult==1?_this.$t("s.v1"):
 		decisionResult==8?_this.$t("s.v8"): 
 		decisionResult==9?_this.$t("s.v9"):
 		decisionResult==23?"主动放弃借款":_this.$t("s.vnull");
 	return r;
 		
 }
 
 zs_download=function(url){
		toUrl(url+ '&token=' + localStorage.getItem("token"));
 }
 
 

 function formatJson(msg) {
     var rep = "~";
     var jsonStr = JSON.stringify(msg, null, rep)
     var str = "";
     for (var i = 0; i < jsonStr.length; i++) {
         var text2 = jsonStr.charAt(i)
         if (i > 1) {
             var text = jsonStr.charAt(i - 1)
             if (rep != text && rep == text2) {
                 str += "<br/>"
             }
         }
         str += text2;
     }
     jsonStr = "";
     for (var i = 0; i < str.length; i++) {
         var text = str.charAt(i);
         if (rep == text)
             jsonStr += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
         else {
             jsonStr += text;
         }
         if (i == str.length - 2)
             jsonStr += "<br/>"
     }
     return jsonStr;
 }
 
 
 function zs_info(th,msg){//提示
	 th.$message({
         showClose: true,
         message: msg,
         type: 'info'
    });
 }
 function zs_error(th,msg){//错误提示
	 th.$message({
         showClose: true,
         message: msg,
         type: 'error'
    });
 }
 
 function zs_confirm(th,msg,tof){
	 th.$confirm(msg, '提示', {
         confirmButtonText: '确定',
         cancelButtonText: '取消',
         type: 'warning'
       }).then(function(){
    	   tof&&tof();
       }).catch(function(){
          
       });
 }