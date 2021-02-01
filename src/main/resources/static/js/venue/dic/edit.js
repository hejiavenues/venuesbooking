/**
 * 编辑-字典表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bDic: {
			id: 0
		},
		rules:{//form 规则
		
			    id: [ {  required: true, message: '主键', trigger: 'blur' } ], 
		    code: [ {  required: true, message: 'code值', trigger: 'blur' } ], 
		    name: [ {  required: true, message: 'value值', trigger: 'blur' } ], 
		    typecode: [ {  required: true, message: '类型code', trigger: 'blur' } ], 
		    typename: [ {  required: true, message: '类型描述', trigger: 'blur' } ], 
		    status: [ {  required: true, message: '状态（0.停用 1.启用）', trigger: 'blur' } ]
			
		}
			
	},
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../venuesbook/dic/info?_' + $.now(),
		    	param: vm.bDic.id,
		    	success: function(data) {
		    		vm.bDic = data;
					if(data.status == 0){
                        vm.bDic.status = '禁用';
                    }else if(data.status == 1){
                        vm.bDic.status = '启用';
                    };
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(vm.bDic.status == '禁用'){
                        vm.bDic.status = 0;
                    }else if(vm.bDic.status == '启用'){
                        vm.bDic.status = 1;
                    };
				if(yes){
			 $.ConfirmForm({
				    	url: '../../venuesbook/dic/update?_' + $.now(),
				    	param: vm.bDic,
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
