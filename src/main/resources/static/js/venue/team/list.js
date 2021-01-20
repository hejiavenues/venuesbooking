/**
 * 团队信息表js
 */

var vm = new Vue({
    el:'#zsvm',
	data: {
	    activeNames:["1"],//模板固定参数
		param:{//搜索参数
			channel:parent.vm.user.channel,//saas必要参数channel
		    pageNumber:1,//第几页
			pageSize:10,//查询条数
			keyword: null
		},
		acTypes:[],
		table:{//表格数据
			  "col":[
					{field : "tname", title : "团队名称", width : ""}, 
					{field : "uname", title : "团长名称", width : ""}, 
					{field : "peopleCount", title : "招募人数", width : ""}, 
					{field : "activityTypeDesc", title : "活动类型", width : ""}, 
					{field : "activityContent", title : "活动内容", width : ""}, 
					{field : "enterCondition", title : "加入条件", width : ""}, 
					{field : "statusStr", title : "团队状态", width : ""}, 
					{field : "deadLine", title : "截止时间", width : "150px"}, 
					{field : "createTime", title : "创建时间", width : "150px"}, 
					{field : "updateTime", title : "更新时间", width : "150px"}
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
		getAllTimes:function(){
			var th=this;
		    zs_post({
		    	url:'../../venuesbook/dic/getDicsByCode?typeCode=activityType',
		    	success:function(r){
		    		th.acTypes=r.bDics;
		    	}
		    })
		},
		orgTree: function(row) {
			console.log(row.tid);
			var ck =[row];
			dialogOpen({
				id: 'layerOrgTree',
				title: '报名人列表',
		        url: 'venue/teamentry/list.html?_' + $.now(),
		        scroll : true,
		        width: "1000px",
		        height: "550px",
				success: function(iframeId){
					console.log("2222222222222222"+row.tid);
					top.frames[iframeId].vm.param.tid = ck[0].tid;
					top.frames[iframeId].vm.load();
				},
		        yes : function(iframeId) {
		        	top.frames[iframeId].vm.acceptClick();
				}
		    })
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
				url: '../../venuesbook/team/list?_' + $.now(),
				param:th.param,
				success:function(r){
					console.log(r);
					for(var i=0;i<r.rows.length;i++){
						if(r.rows[i].status == 1){
                        r.rows[i].statusStr = '报名中';
						}
                    	else if(r.rows[i].status == 2){
                        r.rows[i].statusStr = '已关闭';
                    	}
                    	else if(r.rows[i].status == 3){
                        r.rows[i].statusStr = '已满员';
                    	}
                    	else if(r.rows[i].status == 4){
                        r.rows[i].statusStr = '组队失败';
                    	}
					}
					th.table.data=r.rows;
					th.table.total=r.total;
				}
			})
		},
		save: function() {
			dialogOpen({
				title: '新增团队信息表',
				url: 'venue/team/add.html?_' + $.now(),
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
					title: '编辑团队信息表',
					url: 'venue/team/edit.html?_' + $.now(),
					width: '40%',
					height: '80%',
					success: function(iframeId){
						top.frames[iframeId].vm.bTeam.tid = ck[0].tid;
						top.frames[iframeId].vm.setForm();
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		updateStatus: function(row) {
            var ck =[row];
            var msg = '';
			if(row.status == 3){
				//1放开、2关闭、3满员、4.组队失败
				dialogAlert("此团队已满员，不允许操作");
				return;
			}
			if(row.status == 4){
				dialogAlert("此团队已组队失败，不允许操作");
				return;
			}
            if(row.status == 2){
                msg = '您确定要放开此团队吗？';
                ck[0].status=1;
            }else{
                msg = '您确定要关闭此团队吗？';
                ck[0].status=2;
            }
            if(checkedRow(ck)){
                $.ConfirmForm({
					msg:msg,
                    url: '../../venuesbook/team/update?_' + $.now(),
                    param: ck[0],
                    success: function(data) {
                        $.currentIframe().vm.load();
                    }
                });
            }
        },
		remove: function(row) {
			var ck = [row], ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.tid;
				});
				$.RemoveForm({
					url: '../../venuesbook/team/remove?_' + $.now(),
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