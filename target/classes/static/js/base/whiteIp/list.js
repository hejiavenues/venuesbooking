/**
 * js
 */

var vm = new Vue({
    el:'#zsvm',
	data: {
	    activeNames:["1"],//模板固定参数
		param:{//搜索参数
			channel:parent.vm.user.channel,//saas必要参数channel
		    pageNumber:1,//第几页
			pageSize:10,//查询条数
			onlyId: null,
			type:"ip",
		    loginName:null,
		},
		table:{//表格数据
			  "col":[
										/*{field : "id", title : "id", width : ""}, */
					{field : "onlyId", title : "ip/userId", width : "200"}, 
					{field : "userName", title : "创建用户", width : "100"}, 
/*					{field : "userId", title : "创建用户", width : ""}, 
*/					{field : "text", title : "备注", width : "100"}, 
					{field : "type", title : "类型", width : ""}, 
					{field : "createTime", title : "创建时间", width : "200"}, 
/*					{field : "updateTime", title : "更新时间", width : "200"}
*/			  ],
			  "pagesizes":[1,10, 20, 30, 100],//size选择器
			  "pagesize ":10,
			  "sync":1,//当前页数
			  "total":0,
	          "data":[],
	  		   selects:[],//表格选中行
		  },
	},
	watch:{
		"param.type":function(a,b){
			if(a=="ip"){
				this.param.loginName=null;
			}
			this.load();
		}
		
	},
	methods : {
	     handleCurrentChange:function(val) {//table 查询
			 this.load(val,0);
	     },
	     handleSizeChange:function(val) {//table 查询
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
			zs_post({
				url: '../../sys/whiteIp/list?_' + $.now(),
				param:th.param,
				success:function(r){
					th.table.data=r.rows;
					th.table.total=r.total;
				}
			})
		},
		save: function() {
			dialogOpen({
				title: '新增',
				url: 'base/whiteIp/add.html?_' + $.now(),
				width: '40%',
				height: '80%',
				success: function(iframeId){
				},
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function(row) {
			var ck =[row];
			if(checkedRow(ck)){
				dialogOpen({
					title: '编辑',
					url: 'base/whiteIp/edit.html?_' + $.now(),
					width: '40%',
					height: '80%',
					success: function(iframeId){
						top.frames[iframeId].vm.sysWhiteIp.id = ck[0].id;
						top.frames[iframeId].vm.setForm();
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		remove: function(row) {
			var ck = [row], ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.id;
				});
				$.RemoveForm({
					url: '../../sys/whiteIp/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		},
	
	 },
	 mounted:function(){
	 	this.load();
	 }
})