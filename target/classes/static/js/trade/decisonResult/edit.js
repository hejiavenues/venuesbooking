/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#cashbangLTE',
	data: {
		tradeDecisionResult: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../trade/decisonResult/info?_' + $.now(),
		    	param: vm.tradeDecisionResult.id,
		    	success: function(data) {
		    		vm.tradeDecisionResult = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../trade/decisonResult/update?_' + $.now(),
		    	param: vm.tradeDecisionResult,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})