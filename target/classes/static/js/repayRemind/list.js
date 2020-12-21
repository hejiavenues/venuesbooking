/**
 * 每日应还未还提醒表js
 */

var vm = new Vue({
    el:'#zsvm',
	data: {
	    activeNames:["1"],//模板固定参数
		param:{//搜索参数
			channel:parent.vm.csChannel,//saas必要参数channel
		    pageNumber:1,//第几页
			pageSize:10,//查询条数	
			type:1,
			status:null,
			remindDay:today(),
		},
	    pickerOptions1: {//时间弹框
          disabledDate(time) {
            return time.getTime() > Date.now();
          },
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }]
        },
		isReminder:true,
		userId:parent.vm.user.userId,
		table:{//表格数据
			  "col":[
/*				    {field : "id", title : "主键", width : ""}, 
 * 
*/					{field : "remindDay", title : "提醒日", width : "100"}, 
				  	{field : "applyCode", title : "进件单号", width : "200"}, 
					{field : "userName", title : "姓名", width : ""}, 
					{field : "userMobile", title : "电话", width : "150"}, 
					{field : "isReloan", title : "是否老用户", width :"", formatter:function(v,r){
						if(v==1){
							return "是"
						}
						return "否";
					} }, 
					{field : "periods", title : "总期数", width : ""}, 
					{field : "period", title : "期数", width : ""}, 
/*					{field : "userId", title : "处理人id", width : ""}, 
*/					
					{field : "chnName", title : "处理人", width : ""}, 

					{field : "type", title : "类型", width :"", formatter:function(v,r){
						if(v==1){
							return "需要处理"
						}
						return "不需处理";
					} }, 
					{field : "status", title : "状态", width : "",formatter:function(v,r){
						if(v==0){
							return "未处理"
						}else if(v==1){
							return "已处理"
						}
						return "处理中";
					}}, 
					{field : "text", title : "备注", width : "300"}, 
/*					{field : "createTime", title : "创建时间", width : "200"}, 
*/					{field : "updateTime", title : "更新时间", width : "200"}
			  ],
			  "pagesizes":[1,10, 20, 30, 100],//size选择器
			  "pagesize ":10,
			  "sync":1,//当前页数
			  "total":0,
	          "data":[],
	  		   selects:[],//表格选中行
		  },
		  
		  transferCaseMsg:{//调案
			  formVisible:false,
			  userId:null,
			  oldUsers:false,//存在已经分配人员
			  ids:[],
		  },
		  userList:[],//提醒人员
		  
		  dealMsg:{
			  show:false,
			  msg:{},
		  }
	},
	
	watch:{
		"transferCaseMsg.userId":function(a){
			var th=this;
			th.transferCaseMsg.ids=[];
			th.transferCaseMsg.oldUsers=false;
			if(a!=null&&a!=''){
				 $.each(th.table.selects, function(idx, item){
					 if(a!=item.userId){
						 if(item.userId!=null&& item.userId!=-1){
							  th.transferCaseMsg.oldUsers=true;	
						}
						 th.transferCaseMsg.ids.push(item.id);
					 }
			     });
			}
		}
	},
	methods : {
	     handleCurrentChange:function(val) {//table 查询
	       // console.log(`当前页: ${val}`);
			 this.load(val,0);
	     },
	     handleSizeChange:function(val) {//table 查询
	         // console.log(`每页 ${val} 条`);
	    	 this.load(0,val);
	     },
		 load:function(page,size){//查询
			var th = this;
			if(page!=undefined&&page!=0){
				this.param.pageNumber=page;
			}
			if(size!=undefined&&size!=0){
				this.param.pageSize=size;
			}
			/*this.param.pageNumber=null;
			this.param.pageSize=null;*/

			if(this.isReminder){
				th.param.userId=th.userId;
			}
			
			if(th.param.remindDay==null||th.param.remindDay==''){
				zs_info(th,"请选择时间")
				return;
			}
			zs_post({
				url: '../../repay/remind/dealAndList?_' + $.now(),
				param:th.param,
				success:function(result){
					var r=result.msg;
					th.table.data=r.rows;
					th.table.total=r.total;
				}
			})
		},
		selectCases:function(selects){
			this.table.selects=selects;
		},
		msg:function(){
			
		},
		addMsg:function(){//执行定时
			var th=this;
			th.param.remindDay=today();
			th.param.type=1;
			th.param.status=null;
			zs_post({
				url: '../../repay/remind/dealAndList?_' + $.now(),
				param:th.param,
				success:function(r){
					th.table.data=r.msg;
					if(r.msg.length>0){
						zs_error(th,"已存在数据,不可执行");
						return;
					}
					console.log(th.param.remindDay)
					zs_postA(th,{
						url: '../../repay/remind/getTodayRepayMsg?_' + $.now(),
						param:{date:th.param.remindDay,channel:th.param.channel},
						success:function(r){
							th.load();
						}
					})
				}
			})
		},
		transferCase:function(){//调案
			 var th=this;
			 th.transferCaseMsg.ids=[];
			 th.transferCaseMsg.oldUsers=false;
			 th.transferCaseMsg.userId=null;

			var selects=th.table.selects;
			
			if(selects.length==0){
				  zs_error(th,'未选择任何数据');
				  return ;
			}
			 var ok=true;
			 $.each(selects, function(idx, item){
				 if(item.type=="0"){
					  ok=false;
				}
			 });
			 
			 if(!ok){
				  zs_error(th,'有不需要处理的情况');
				  return;
			}
			 th.transferCaseMsg.formVisible=true;
		},
		
		transferCaseSure:function(){//确认调案
			var th=this;
			
			if(th.transferCaseMsg.ids.length==0){
				th.$message.error('无可操作数据');
				return ;
			}
			zs_confirm(th,"您确定执行调案操作吗",function(){
				zs_post({
					url: '../../repay/remind/transferCaseToUser?_' + $.now(),
			    	param: {
			    		ids:th.transferCaseMsg.ids,
			    		userId:th.transferCaseMsg.userId,

			    	},
			    	success: function(data) {
			    		th.load();
			    		th.transferCaseMsg.formVisible = false;
			    	}
				});
			})
		
			
		},
		
		save: function() {
			dialogOpen({
				title: '新增每日应还未还提醒表',
				url: 'repayRemind/add.html?_' + $.now(),
				width: '40%',
				height: '80%',
				success: function(iframeId){
				},
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		deal: function(ck) {
			if(checkedRow(ck)){
				this.doDeal(ck[0])
			}
		},
		doDeal:function(row){
			
			  var th=this;
			  if(!th.isReminder){
				  return;
			  }
			  
			  if(row.type==0){
				  zs_info(th,"请选择需要处理的类型")
				  return;
			  }
			  th.dealMsg.msg={};
			  th.dealMsg.show=true;
			 for(key in row){
				 th.$set(th.dealMsg.msg,key,row[key]);
			 }
		},
		
		sureDeal:function(row){
			var th=this;
			
			zs_post({
				url: '../../repay/remind/update?_' + $.now(),
				param:th.dealMsg.msg,
				success:function(r){
				    th.dealMsg.show=false;
					th.load();
				}
			})
		},
		getReminder: function() {
			 var th=this;
				zs_post({
					url: '../../repay/remind/getReminder?_' + $.now(),
					param:{channel:th.param.channel},
					success:function(r){
						console.log(r)
						th.userList=r.msg;
					}
				})
		},
	
	 },
	 mounted:function(){
		 var th=this;
			zs_post({
				url: '../../repay/remind/isReminder?_' + $.now(),
				param:{userId:th.userId},
				success:function(r){
					console.log(r)
					if(r.code==0&&r.msg.length>0){
						th.isReminder=true;
					}else{
						th.isReminder=false;
						th.getReminder();
					}
				 	th.load();
				}
			})
	 }
})