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
			if(isNullOrEmpty(this.query.mobile)&&isNullOrEmpty(this.query.idNo)){
				dialogMsg('请输入至少一项查询条件！');
				return;
			}
			
			$.SetForm({
				url: '../../query/cust/list?_' + $.now(),
		    	param: vm.query,
		    	success: function(data) {
		    		vm.details.show = false;
		    		vm.rows = data;
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
		    	}
			});
			
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
			
		}
	}
})