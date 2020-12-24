/**
 * 编辑-居委会信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bCommittees: {
			cid: 0
		},
		rules:{//form 规则
		
			    cid: [ {  required: true, message: '主键', trigger: 'blur' } ], 
		    cname: [ {  required: true, message: '名称', trigger: 'blur' } ], 
		    connectMobile: [ {  required: true, message: '联系方式', trigger: 'blur' } ], 
		    connectPerson: [ {  required: true, message: '负责人', trigger: 'blur' } ], 
		    address: [ {  required: true, message: '地址', trigger: 'blur' } ], 
		}
			
	},
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../venuesbook/committee/info?_' + $.now(),
		    	param: vm.bCommittees.cid,
		    	success: function(data) {
		    		vm.bCommittees = data;
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
			 $.ConfirmForm({
				    	url: '../../venuesbook/committee/update?_' + $.now(),
				    	param: vm.bCommittees,
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
