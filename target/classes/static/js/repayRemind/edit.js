/**
 * 编辑-每日应还未还提醒表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		mRepayRemind: {
			id: 0
		},
		rules:{//form 规则
		
			    id: [ {  required: true, message: '主键', trigger: 'blur' } ], 
		    applyCode: [ {  required: true, message: '进件单号', trigger: 'blur' } ], 
		    userName: [ {  required: true, message: '姓名', trigger: 'blur' } ], 
		    userMobile: [ {  required: true, message: '电话', trigger: 'blur' } ], 
		    isReloan: [ {  required: true, message: '是否老用户', trigger: 'blur' } ], 
		    periods: [ {  required: true, message: '总期数', trigger: 'blur' } ], 
		    period: [ {  required: true, message: '期数', trigger: 'blur' } ], 
		    userId: [ {  required: true, message: '处理人id', trigger: 'blur' } ], 
		    type: [ {  required: true, message: '类型（0，不需要处理，1需要处理）', trigger: 'blur' } ], 
		    status: [ {  required: true, message: '状态（0，未处理，1已经处理，2,处理中）', trigger: 'blur' } ], 
		    text: [ {  required: true, message: '备注', trigger: 'blur' } ], 
		    createTime: [ {  required: true, message: '', trigger: 'blur' } ], 
		    updateTime: [ {  required: true, message: '', trigger: 'blur' } ]
			
		}
			
	},
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../repay/remind/info?_' + $.now(),
		    	param: vm.mRepayRemind.id,
		    	success: function(data) {
		    		vm.mRepayRemind = data;
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
			 $.ConfirmForm({
				    	url: '../../repay/remind/update?_' + $.now(),
				    	param: vm.mRepayRemind,
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
