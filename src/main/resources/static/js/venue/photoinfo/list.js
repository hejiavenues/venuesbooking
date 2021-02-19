/**
 * 随拍信息表js
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
			suoshuleixing: '',
		},
		avaTimes: [],
		table:{//表格数据
			  "col":[
				    {field : "createTime", title : "发布时间", width : "150px"}, 
					{field : "address", title : "定位地址", width : "180px"}, 
					{field : "uname", title : "发布人姓名", width : "100px"}, 
					{field : "content", title : "内容", width : "300px"}, 
					/*{field : "status", title : "随拍状态", width : ""},*/ 
			  ],
			  "pagesizes":[1,10, 20, 30, 100],//size选择器
			  "pagesize ":10,
			  "sync":1,//当前页数
			  "total":0,
	          "data":[],
	  		   selects:[],//表格选中行
		  },
	},
	created:function(){
		this.getAllTimes();
	},
	methods : {
		selectChanged (val) {
			this.param.suoshuleixing = val;
		},
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
			if(page!=undefined&&page!=0){
				this.param.pageNumber=page;
			}
			if(size!=undefined&&size!=0){
				this.param.pageSize=size;
			}
			zs_post({
				url: '../../venuesbook/photoinfo/list?_' + $.now(),
				param:th.param,
				success:function(r){
					console.log(r);
					for(var i=0;i<r.rows.length;i++){
						if(r.rows[i].status == 1){
                        r.rows[i].status = '正常';
						}
                    	else if(r.rows[i].status == 2){
                        r.rows[i].status = '已删除';
                    	}
					}
					th.table.data=r.rows;
					th.table.total=r.total;
				}
			})
		},
		save: function() {
			dialogOpen({
				title: '新增随拍信息表',
				url: 'venue/photoinfo/add.html?_' + $.now(),
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
					title: '编辑随拍信息表',
					url: 'venue/photoinfo/edit.html?_' + $.now(),
					width: '40%',
					height: '80%',
					success: function(iframeId){
						top.frames[iframeId].vm.bPhotoInfo.pid = ck[0].pid;
						top.frames[iframeId].vm.setForm();
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		getAllTimes:function(){
			var th=this;
		    zs_post({
		    	url:'../../venuesbook/dic/getDicsByCode?typeCode=contentType',
		    	success:function(r){
		    		th.avaTimes=r.bDics;
		    	}
		    })
		},
		remove: function(row) {
			var ck = [row], ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.pid;
				});
				$.RemoveForm({
					url: '../../venuesbook/photoinfo/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		},
		passApply: function(row) {
			var ck = [row], ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.pid;
				});
				/*$.RemoveForm({
					url: '../../venuesbook/photoinfo/passApply?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});*/
				zs_post({
				url: '../../venuesbook/photoinfo/passApply?_' + $.now(),
				param:ids,
				success:function(r){
					vm.load();
				}
			})
			}
		},
		orgTree: function(row) {
			console.log(row.pid);
			var ck =[row];
			dialogOpen({
				id: 'layerOrgTree',
				title: '评论列表',
		        url: 'venue/photoreply/list.html?_' + $.now(),
		        scroll : true,
		        width: "1000px",
		        height: "550px",
				success: function(iframeId){
					console.log("2222222222222222"+row.pid);
					top.frames[iframeId].vm.param.reply = ck[0].pid;
					top.frames[iframeId].vm.load();
				},
		        yes : function(iframeId) {
		        	top.frames[iframeId].vm.acceptClick();
				}
		    })
		},
	 },
	 mounted:function(){
	 	this.load();
	 }
})