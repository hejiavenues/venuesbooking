/**
 * 编辑-召集人信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bConvenerInfo: {
			uid: 0
		},
		rules:{//form 规则
		
			    uid: [ {  required: true, message: '', trigger: 'blur' } ], 
		    activityId: [ {  required: true, message: '召集人活动类型', trigger: 'blur' } ], 
		    idcardFrontUrl: [ {  required: true, message: '身份证正面url', trigger: 'blur' } ], 
		    idcardBackUrl: [ {  required: true, message: '身份证反面url', trigger: 'blur' } ], 
		    status: [ {  required: true, message: '请选择通过或拒绝', trigger: 'blur' } ], 
		    createTime: [ {  required: true, message: '创建时间', trigger: 'blur' } ], 
		    updateTime: [ {  required: true, message: '更新时间', trigger: 'blur' } ]
			
		}
			
	},
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../venuesbook/convenerinfo/info?_' + $.now(),
		    	param: vm.bConvenerInfo.uid,
		    	success: function(data) {
		    		vm.bConvenerInfo = data;
					if(data.status == 0){
                        vm.bConvenerInfo.status = '';
                    }
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
			 $.ConfirmForm({
				    	url: '../../venuesbook/convenerinfo/update?_' + $.now(),
				    	param: vm.bConvenerInfo,
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
