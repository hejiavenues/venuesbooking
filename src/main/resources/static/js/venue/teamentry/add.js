/**
 * 新增-团队报名记录表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bTeamEntry: {
			teid: 0
		},
		rules:{//form 规则
		
			    teid: [ {  required: true, message: '主键', trigger: 'blur' } ], 
		    tid: [ {  required: true, message: '团队id', trigger: 'blur' } ], 
		    mobile: [ {  required: true, message: '手机号', trigger: 'blur' } ], 
		    uname: [ {  required: true, message: '姓名', trigger: 'blur' } ], 
		    status: [ {  required: true, message: '状态 1.报名成功 2.主动取消', trigger: 'blur' } ], 
		    createTime: [ {  required: true, message: '创建时间', trigger: 'blur' } ], 
		    updateTime: [ {  required: true, message: '更新时间', trigger: 'blur' } ]
			
		}
	},
	methods : {
		acceptClick: function() {
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
					 $.SaveForm({
				    	url: '../../venuesbook/teamentry/save?_' + $.now(),
				    	param: vm.bTeamEntry,
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
