/**
 * 新增-js
 */
var vm = new Vue({
	el:'#cashbangLTE',
	data: {
		tradeDecisionResult: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../trade/decisonResult/save?_' + $.now(),
		    	param: vm.tradeDecisionResult,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
