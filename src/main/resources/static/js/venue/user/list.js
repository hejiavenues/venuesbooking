/**
 * 用户信息表js
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
					{field : "uname", title : "用户名称", width : "110px"}, 
					/*{field : "sexStr", title : "用户性别", width : ""}, 
					{field : "birthday", title : "出生日期", width : "110px"}, */
					{field : "mobile", title : "手机号", width : "110px"}, 
					{field : "committeeName", title : "居委会", width : "250px"},
					/*（1.正常 2.禁用 3.删除） */
					{field : "statusStr", title : "用户状态", width : ""}, 
					/*{field : "iconUrl", title : "头像地址", width : ""}, */
					{field : "createTime", title : "注册时间", width : ""}, 
					/*{field : "updateTime", title : "更新时间", width : ""}, 
					{field : "openid", title : "微信唯一标识", width : ""}*/
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
				url: '../../venuesbook/user/list?_' + $.now(),
				param:th.param,
				success:function(r){
					console.log(r);
					for(var i=0;i<r.rows.length;i++){
						if(r.rows[i].status == 1){
                        r.rows[i].statusStr = '正常';
						}
                    	else if(r.rows[i].status == 2){
                        r.rows[i].statusStr = '禁用';
                    	}
					}
					th.table.data=r.rows;
					th.table.total=r.total;
				}
			})
		},
		save: function() {
			dialogOpen({
				title: '新增用户信息表',
				url: 'venue/user/add.html?_' + $.now(),
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
					title: '编辑用户信息表',
					url: 'venue/user/edit.html?_' + $.now(),
					width: '40%',
					height: '80%',
					success: function(iframeId){
						top.frames[iframeId].vm.bUser.uid = ck[0].uid;
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
			
            if(row.status == 2){
                msg = '您确定要放开此用户吗？';
                ck[0].status=1;
            }else{
                msg = '您确定要禁用此用户吗？';
                ck[0].status=2;
            }
            if(checkedRow(ck)){
                $.ConfirmForm({
					msg:msg,
                    url: '../../venuesbook/user/update?_' + $.now(),
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
					ids[idx] = item.uid;
				});
				$.RemoveForm({
					url: '../../venuesbook/user/remove?_' + $.now(),
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