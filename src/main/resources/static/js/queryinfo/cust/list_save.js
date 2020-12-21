/**
 * 客户信息js
 */


var vm = new Vue({
	el:'#cashbangLTE',
	data: {
		query : {
			mobile: '',
			idNo : ''
		},
		rows  : [],
		details:{	//交易流水
			show : false,
			query : {},
			rows : []
		}
	},
	methods : {
		load: function() {
			if(isNullOrEmpty(this.query.mobile.trim())&&isNullOrEmpty(this.query.idNo.trim())){
				dialogMsg('请输入至少一项查询条件！');
				return;
			}
		
			$.SetForm({
				url: '../../query/cust/list?_' + $.now(),
		    	param: vm.query,
		    	success: function(data) {
		    		vm.details.show = false;
		    		vm.rows = data;
		    		var lis=data[0].orderList;
		    		for( var i=0;i<lis.length;i++){
		    			lis[i].parcode=lis[i].applyid;
		    		}
		    		
		    		$('#dataGrid').bootstrapTable('load',lis);
		    		$("#dataGridall").show();
		    	/*	$("#dataGrid1all").hide();*/
		    	}
			});
			
		},
		loadDetails : function(order){
			var options = $.extend(vm.details.query, order);
			
			$.SetForm({
				url: '../../query/cust/list/details?_' + $.now(),
		    	param: vm.details.query,
		    	success: function(data) {
		    		vm.details.show = true;
		    		vm.details.rows = data;
		    		$("#dataGridall").hide();
		    		/*$("#dataGrid1all").show();*/
		    	/*	$('#dataGrid1').bootstrapTable('load',data);*/
		    	}
			});
			
		},
		loadDetails1:function(){
			
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];	
			if(checkedArray(ck)){
			
				if(!checkedRow(ck)){
					return;
				}
				var options = $.extend(vm.details.query, ck[0]);
				$.SetForm({
					url: '../../query/cust/list/details?_' + $.now(),
			    	param: vm.details.query,
			    	success: function(data) {
			    		vm.details.show = true;
			    		vm.details.rows = data;
			    		$("#dataGridall").hide();
			    		/*$("#dataGrid1all").show();*/
			    	
			    	}
				});
			}
		},
		unlock : function(orderDetail){
			$.ConfirmAjax({
				msg:'您确认要对该交易单进行解锁操作吗？',
				url: '../../query/order/unlock?_' + $.now(),
		    	param: orderDetail,
		    	success: function(data) {
		    		vm.loadDetails(orderDetail.applyId);
		    	}
			});
			
		},
		pullblack:function(item){
			
			$.ConfirmAjax({
				msg:'您确认要拉黑该客户吗？',
				url: '../../risk/blacklist/pullblack?_' + $.now(),
		    	param: {
		    		idNo : item.idNo,
		    		mobile : item.mobile,
		    		name : item.name
		    	},
		    	success: function(data) {
		    		vm.loadDetails(orderDetail.applyId);
		    	}
			});
		},
		datagrid : function(){
			var _this = this;
			$('#dataGrid').bootstrapTableData({
				height: $(window).height()-80,
				striped:true,
				/*pagination:false,
				onlyInfoPagination:true,*/
				columns:[
					{checkbox: true},
					{field : "applyId", title : "申请单号", width : "100px",align:"center"}, 
					{field : "bankCard", title : "银行卡号", width : "100px"}, 
					{field : "approveTime", title : "放款时间", width : "100px",align:"center"}, 
					{field : "applyAmount", title : "借款金额", width : "100px",align:"center"}, 
					{field : "totalNeedPaymentAmount", title : "应还总额", width : "100px"}, 
					{field : "term", title : "借款期限", width : "100px"}, 
					{field : "deadlineDate", title : "应还日期", width : "100px",align:"right"}, 
					{field : "clearDate", title : "结清日期", width : "100px",align:"center"}, 
					{field : "applyState", title : "还款状态", width : "100px",align:"center"}, 
					{field : "currentStatus", title : "当前状态", width : "100px",align:"center"},
					{field : "overdueDays", title : "逾期天数", width : "100px",align:"center"},
					{field : "channelText", title : "还款记录", width : "100px",align:"center"},
					{field : "productCode", title : "产品编号", width : "100px",align:"center"},
				],
				onDblClickRow : function(row, $element, field){
					_this.loadDetails(row);
				},
				onPostBody:function(e){
				}
			})
		},
		datagrid1 : function(){
			var _this = this;
			$('#dataGrid1').bootstrapTableData({
				height: $(window).height()-80,
				striped:true,
				columns: [
					{checkbox: true},
					{field : "applyId", title : "申请单号", width : "100px",align:"center"}, 
					{field : "paymentId", title : "交易流水号", width : "100px"}, 
					{field : "tradeTypeText", title : "交易类型", width : "100px",align:"center"}, 
					{field : "balance", title : "交易金额", width : "100px",align:"center"}, 
					{field : "editUser", title : "备注", width : "100px"}, 
					{field : "orderStatusText", title : "状态", width : "100px"}, 
					{field : "createTime", title : "交易时间", width : "100px",align:"right"}, 
					{field : "isLocked", title : "是否锁单", width : "100px",align:"center",
						formatter : function(value, row, index) {
						return	value=='1'?'是':'否';
						}
					}
				], 
				onDblClickRow : function(row, $element, field){
				},
				onPostBody:function(e){
				}
			})},
	},
	mounted : function(){
		this.datagrid();
		//this.datagrid1();
	},
})