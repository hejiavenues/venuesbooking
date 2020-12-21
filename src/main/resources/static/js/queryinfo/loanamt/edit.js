/**
 * 修改额度-放款额度查询js
 */
var vm = new Vue({
	el:'#cashbangLTE',
	data: {
		queryLoanamtHistory: {}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
			
			if(vm.queryLoanamtHistory.loanamtAfter<0){
				dialogAlert('放款额度必须大于等于0','error');
				return false;
			}
		    $.ConfirmForm({
		    	url: '../../query/loanamt/update?_' + $.now(),
		    	param: vm.queryLoanamtHistory,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})