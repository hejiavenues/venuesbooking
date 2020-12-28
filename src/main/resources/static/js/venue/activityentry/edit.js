/**
 * 编辑-活动报名记录表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bActivityEntry: {
			eid: 0
		},
		rules:{//form 规则
		
			    eid: [ {  required: true, message: '主键', trigger: 'blur' } ], 
		    activityId: [ {  required: true, message: '活动id', trigger: 'blur' } ], 
		    uid: [ {  required: true, message: '用户id', trigger: 'blur' } ], 
		    uname: [ {  required: true, message: '姓名', trigger: 'blur' } ], 
		    mobile: [ {  required: true, message: '手机号', trigger: 'blur' } ], 
		    ispresent: [ {  required: true, message: '是否到场', trigger: 'blur' } ], 
		    status: [ {  required: true, message: '报名状态 1.成功 2.取消', trigger: 'blur' } ], 
		    createTime: [ {  required: true, message: '创建时间', trigger: 'blur' } ], 
		    updateTime: [ {  required: true, message: '更新时间', trigger: 'blur' } ]
			
		}
			
	},
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../venuesbook/activityentry/info?_' + $.now(),
		    	param: vm.bActivityEntry.eid,
		    	success: function(data) {
		    		vm.bActivityEntry = data;
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
			 $.ConfirmForm({
				    	url: '../../venuesbook/activityentry/update?_' + $.now(),
				    	param: vm.bActivityEntry,
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
