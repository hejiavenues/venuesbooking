/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		sysWhiteIp: {
			id: ""
		},
		rules:{//form 规则
		
		    onlyId: [ {  required: true, message: 'ip或者userId', trigger: 'blur' } ], 
			
		}
			
	},
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../sys/whiteIp/info?id='+vm.sysWhiteIp.id,
		    	success: function(data) {
		    		vm.sysWhiteIp = data;
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
			 $.ConfirmForm({
				    	url: '../../sys/whiteIp/update?_' + $.now(),
				    	param: vm.sysWhiteIp,
				    	success: function(data) {
				    		$.currentIframe().vm.load();
				    	}
				    });
				}else{
					 return false;
				}
				 
			 });
		}
	}
})
