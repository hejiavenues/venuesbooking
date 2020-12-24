/**
 * banner图表js
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
					/*{field : "bid", title : "主键", width : ""}, */
					{field : "bannerDesc", title : "banner图描述", width : ""}, 
					/*{field : "bannerImgUrl", title : "图片", width : "150px",align: 'center',
						formatter: function(value,row,index){
                    	return '<img style="height:100px;width:150px" src='+value+'>';
                		}
					}, */
					{field : "sortid", title : "排序(大值优先轮播)", width : ""}, 
					{field : "isuse", title : "禁用启用", width : ""}, 
					{field : "createTime", title : "创建时间", width : ""}, 
					{field : "updateTime", title : "更新时间", width : ""}
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
				url: '../../venuesbook/banner/list?_' + $.now(),
				param:th.param,
				success:function(r){
					console.log(r);
					for(var i=0;i<r.rows.length;i++){
						if(r.rows[i].isuse == 0){
                        r.rows[i].isuse = '禁用';
						}
                    	else if(r.rows[i].isuse == 1){
                        r.rows[i].isuse = '启用';
                    	}
					}
					
					th.table.data=r.rows;
					th.table.total=r.total;
				}
			});
		},
		save: function() {
			dialogOpen({
				title: '新增banner图表',
				url: 'venue/banner/add.html?_' + $.now(),
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
					title: '编辑banner图表',
					url: 'venue/banner/edit.html?_' + $.now(),
					width: '40%',
					height: '80%',
					success: function(iframeId){
						top.frames[iframeId].vm.bBannerInfo.bid = ck[0].bid;
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
					ids[idx] = item.bid;
				});
				$.RemoveForm({
					url: '../../venuesbook/banner/remove?_' + $.now(),
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