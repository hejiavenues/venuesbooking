/**
 * 场馆信息表js
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
		table:{//表格数据
			  "col":[
					{field : "venueName", title : "场馆名称", width : "100px"},
                  {field : "status", title : "状态", width : "50px"},
                  {field : "lockId", title : "智能锁Id", width : "50px"},
                  {field : "lockPwd", title : "智能锁密码", width : "50px"},
                  {field : "maxPeople", title : "最大容纳人数", width : "100px"},
					{field : "address", title : "场馆地址", width : "200px"}, 
					{field : "describtion", title : "场馆简介", width : "200px"}, 
					{field : "committeeName", title : "所属居委会", width : "150px"}, 
					/*{field : "supportActiveType", title : "支持的活动类型", width : ""}, 
					{field : "iconUrl", title : "图片地址", width : ""}, 
					{field : "createTime", title : "创建时间", width : ""}, 
					{field : "updateTime", title : "修改时间", width : ""}*/
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
			if(page!=undefined&&page!=0){
				this.param.pageNumber=page;
			}
			if(size!=undefined&&size!=0){
				this.param.pageSize=size;
			}
			zs_post({
				url: '../../venuesbook/venueinfo/list?_' + $.now(),
				param:th.param,
				success:function(r){
					console.log(r);
                    for(var i=0;i<r.rows.length;i++){
                        if(r.rows[i].status == 0){
                            r.rows[i].status = '可用';
                        }
                        else if(r.rows[i].status == 1){
                            r.rows[i].status = '隐藏';
                        }
                    }
					th.table.data=r.rows;
					th.table.total=r.total;
				}
			})
		},
		save: function() {
			dialogOpen({
				title: '新增场馆',
				url: 'venue/venueinfo/add.html?_' + $.now(),
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
					title: '编辑场馆',
					url: 'venue/venueinfo/edit.html?_' + $.now(),
					width: '40%',
					height: '80%',
					success: function(iframeId){
						top.frames[iframeId].vm.bVenueInfo.venueId = ck[0].venueId;
						top.frames[iframeId].vm.setForm();
						/*setTimeout(function(){},2000);
						top.frames[iframeId].vm.getDynamicTags();*/
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		editTime: function(row) {
			var ck =[row];
			if(checkedRow(ck)){
				dialogOpen({
					title: '编辑场馆禁用时段',
					url: 'venue/venueinfo/editTime.html?_' + $.now(),
					width: '30%',
					height: '80%',
					success: function(iframeId){
						top.frames[iframeId].vm.bVenueInfo.venueId = ck[0].venueId;
						/*top.frames[iframeId].vm.setForm();*/
						top.frames[iframeId].vm.getDynamicTags(ck[0].venueId);
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		editAllTime: function() {
			dialogOpen({
					title: '编辑所有场馆禁用时段',
					url: 'venue/venueinfo/editAllTime.html?_' + $.now(),
					width: '30%',
					height: '65%',
					success: function(iframeId){
						top.frames[iframeId].vm.getDynamicTags();
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
		},
		remove: function(row) {
			var ck = [row], ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.venueId;
				});
				$.RemoveForm({
					url: '../../venuesbook/venueinfo/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		},
        getPwd: function(row) {
            var ck = [row], ids = [];
            if(checkedArray(ck)){
                $.SetForm({
                    url: '../../venuesbook/venueinfo/getRoomLockPwd?_' + $.now(),
                    param: ck[0].venueId,
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