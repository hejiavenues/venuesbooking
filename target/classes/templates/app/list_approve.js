/**
 * 申请信息表js
 */
var subChannelJson={};
var nowspjg="";
var thequeue=1;;
var vm = new Vue({
	el:'#ZSKJ',
	data: {
		applyCode: null,
		userName: null,
		userMobile: null,
		idNumber: null,
		decisionResult:null,
		subChannel:"",
		subChannels:[],
		subCase:"",
		desensitization:true,
		products:[],
		product:"",
		subChannelJson:{},
		productJson:{},
	},
	methods : {
		search:function(){
			
			$("#full").popup();
		},
		
		load: function() {
			this.datagrid();
			$.closePopup();
		},
		loadSubChannels: function() {
				  zs_post({
		            	url: '../../channel/subchannel/list?_' + $.now(),
		            	param:{
		    				channel:parent.user.channel,
		    			},
		    			success: function(data) {
		    				vm.subChannels=data.rows;
		    				subChannelJson=arrToJson(data.rows,"subChannelCode",'subChannelName')
		    			}
		    	   });
	        },
		   	 loadProducts:function(){
		  	   zs_post({
		  			url: '../../m/product/list?_' + $.now(),
		  			param:{
		  				channel:parent.user.channel,
		  			},
		  			success: function(data) {
		  				vm.products=data.rows;
		  				productJson=arrToJson(data.rows,"code",'name');
		  			}
		  	   });
		    },
        loadUserQue: function() {
            $.post('../../risk/apply/getUserQue', null, function(data){
               // console.log(data)
            });
        },
		detailsPage:function(applyId){
		},
		datagrid:function(){
			var params={};
			params.applyCode = this.applyCode;
			params.userName = this.userName;
			params.userMobile = this.userMobile;
			params.idNumber = this.idNumber;
			params.decisionResult = this.decisionResult;
			nowspjg=this.decisionResult;
			params.subChannel=this.subChannel;
			params.subCase=this.subCase;
			params.product=this.product;
			
			var columns=[
				{field : "applyId", title : "主键", }, 
				{field : "applyCode", title : "申请单号", }, 
				{field : "userName", title : "客户姓名", }, 
				{field : "userMobile", title : "手机号码", }, 
				{field : "idNumber", title : "证件号码", 
				}, 
				{field : "productId", title : "产品", 
					formatter : function(value) {
						return productJson[value]==undefined?value:productJson[value];
					}}, 
				{field : "subCase", title : "子渠道", }, 
				{field : "subChannelDesc", title : "详细子渠道", 
					formatter : function(value) {
							return subChannelJson[value]==undefined?value:subChannelJson[value];
					}}, 
				{field : "applicationAmount", title : "借款金额",align:"right"}, 
				{field : "gmtCreate", title : "进件时间", }, 
				{field : "decisionResult", title : "审批结果", 
					formatter : function(value) {
						
						if (value == '0') {
							return '<span class="label label-success">审批通过</span>';
						} else if (value == '1') {
							return '<span class="label label-danger">审批拒绝</span>';
						} else if(value == '8'){
							return '<span class="label label-warning">等待分配</span>';
						} else if(value == '9'){
							return '<span class="label label-primary">等待一级审批</span>';
						}else if(value == '10'){
							return '<span class="label label-primary">等待二级审批</span>';
						}
						
					},
					 },{field : "channelName", title : "渠道", },
			];
			
			 zs_post({
	            	url: '../../risk/apply/listApprove?_' + $.now(),
	            	param:params,
	    			success: function(data) {
	    				var data=data.rows;
	    				var html="";
	    				
	    				for(var i=0;i<data.length;i++){
	    					var v=data[i];
	    					var htmli='<div class="layui-card">'+
	    				              ' <div class="layui-card-body" >'+
	    					          '<table class="layui-table" lay-skin="nob" lay-even lay-size="sm">'+
	    					          '<colgroup> <col width="30%"> <col width="70%"> <col></colgroup><tbody>';
	    				         
	    					
	    					for(var  j=0;j<columns.length;j++){
	    						var c=columns[j];
	    						  var vle=v[c.field];
	    						if(c.formatter!=undefined){
	    							vle=c.formatter(vle);
	    						}
	    						if(c.title=="审批结果"){
	    							vle="<font style='color:#009688'>"+vle+"</font>";
	    						}
	    					      
	    						htmli+='<tr><td>'+c.title+'</td><td>'+vle+'</td></tr>';
	    					}
	    					  
	    					htmli+=' </tbody></table>'+
	    					   '<hr class="layui-bg-gray">'+
	    					   
	    					   '<div class="layui-btn-group" style="width:100%">'+
				                '<button class="layui-btn layui-btn-danger " style="width:50%"onclick="detailsf(\''+v["userName"]+'\',\''+v["applyId"]+'\',\''+v["applyCode"]+'\')">拒绝</button>'+
				                '<button class="layui-btn "style="width:50%"  onclick="detailss(\''+v["userName"]+'\',\''+v["applyId"]+'\',\''+v["applyCode"]+'\')">通过</button>'+
				                ' </div>'+
	    				       ' </div>'+
	    				      '</div>';
	    				      html+=htmli;
	    				}
	    				$("#approve").html(html);
	    			}
	    	  });
		}
	},
	mounted : function(applyCode){
		this.load();
	}
	,created: function() {
		this.loadProducts();
		this.loadSubChannels();
		vueGetLayui('form');
    },
    watch : {
    },
});

/*var decisionReasons=[];
$.post('../../sys/macro/value?value=decisionReason', null, function(data){
	decisionReasons = data;
});
*/
function detailss(name,applyId,applyCode) {
	var riskApplyInfo={
		applyId: applyId,
        queueNo:2,
		applyCode:applyCode,
		decisionReason:'000',
		decisionResult:'0',
		decisionResultDesc:'手机审批'
	};
    riskApplyInfo.applyCode=applyCode;
    $.confirm({
    	  title: '审批通过',
    	  text: '您确定要  [审批通过] 该借款申请吗？  姓名:'+name+",单号："+riskApplyInfo.applyCode,
    	  onOK: function () {
    		  zs_post({
	            	url:'../../risk/apply/decision?_' + $.now(),
	            	param:riskApplyInfo,
	    			success: function(data) {
	    				vm.load();
	    				if(data.code==0){
	    					$.toast(data.msg);
	    				}else{
	    					$.alert(data.msg);
	    				}
	    			}
	    	   });
    	  },
    	  onCancel: function () {
    	  }
    	});      
};

function detailsf(name,applyId,applyCode) {
	var riskApplyInfo={
		applyId: applyId,
        queueNo:2,
		applyCode:applyCode,
		decisionReason:'999',
		decisionResult:'1',
		decisionResultDesc:'手机审批'
	};
    riskApplyInfo.applyCode=applyCode;
    
    $.prompt({
    	  title: '审批拒绝',
    	  text: '您确定要  [审批拒绝] 该借款申请吗？  姓名:'+name+",单号："+riskApplyInfo.applyCode+'拒绝原因默认为其他',
    	  input: '手机审批拒绝原因其他',
    	  empty: false, // 是否允许为空
    	  onOK: function (input) {
    		  riskApplyInfo.decisionResultDesc=input;
    		  zs_post({
	            	url:'../../risk/apply/decision?_' + $.now(),
	            	param:riskApplyInfo,
	    			success: function(data) {
	    				vm.load();
	    				if(data.code==0){
	    					$.toast(data.msg);
	    				}else{
	    					$.alert(data.msg);
	    				}
	    			}
	    	   });
    	  },
    	  onCancel: function () {
    	    //点击取消
    	  }
    	});
};


