/**
 * 活动报名记录表js
 */

var vm = new Vue({
    el:'#zsvm',
	data: {
	    activeNames:["1"],//模板固定参数
		param:{//搜索参数
			channel:parent.vm.user.channel,//saas必要参数channel
		    pageNumber:1,//第几页
			pageSize:10,//查询条数
			keyword: null,
			activityId: '',
		},
		activityId:'',
		table:{//表格数据
			  "col":[
					{field : "uname", title : "姓名", width : ""}, 
					{field : "mobile", title : "手机号", width : ""}, 
					{field : "ispresent", title : "是否到场", width : ""}, 
					{field : "status", title : "报名状态", width : ""}, 
					{field : "createTime", title : "报名时间", width : ""}, 
			  ],
			  "pagesizes":[1,10, 20, 30, 100],//size选择器
			  "pagesize ":10,
			  "sync":1,//当前页数
			  "total":0,
	          "data":[],
	  		   selects:[],//表格选中行
		  },
	},
	methods : {
	    selectCases:function(selects){
			this.table.selects=selects;
	    },
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
			console.log("-------"+th.param.activityId);
			console.log("-------"+th.activityId);
			if(page!=undefined&&page!=0){
				this.param.pageNumber=page;
			}
			if(size!=undefined&&size!=0){
				this.param.pageSize=size;
			}
			zs_post({
				url: '../../venuesbook/activityentry/list?_' + $.now(),
				param:th.param,
				success:function(r){
					console.log(r);
					for(var i=0;i<r.rows.length;i++){
						if(r.rows[i].ispresent == 0){
                        r.rows[i].ispresent = '未到场';
						}
                    	else if(r.rows[i].ispresent == 1){
                        r.rows[i].ispresent = '已到场';
                    	}
						if(r.rows[i].status == 1){
                        r.rows[i].status = '已报名';
						}
                    	else if(r.rows[i].status == 2){
                        r.rows[i].status = '已取消';
                    	}
					}
					th.table.data=r.rows;
					th.table.total=r.total;
				}
			})
		},
		save: function() {
			dialogOpen({
				title: '新增活动报名记录表',
				url: 'venue/activityentry/add.html?_' + $.now(),
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
					title: '编辑活动报名记录表',
					url: 'venue/activityentry/edit.html?_' + $.now(),
					width: '40%',
					height: '80%',
					success: function(iframeId){
						top.frames[iframeId].vm.bActivityEntry.eid = ck[0].eid;
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
					ids[idx] = item.eid;
				});
				$.RemoveForm({
					url: '../../venuesbook/activityentry/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		},
	
	 },
	 mounted:function(){
	 	//this.load();
	 }
})