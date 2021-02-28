/**
 * 新增-社区活动信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bCommunityActivities: {
			comActivityId: 0
		},
		rules:{//form 规则
		
			    comActivityId: [ {  required: true, message: '活动id', trigger: 'blur' } ], 
		    uid: [ {  required: true, message: '发起人id', trigger: 'blur' } ], 
		    activityName: [ {  required: true, message: '活动名称', trigger: 'blur' } ], 
		    activityCount: [ {  required: true, message: '活动人数', trigger: 'blur' } ], 
		    activityType: [ {  required: true, message: '活动类型', trigger: 'blur' } ], 
		    activityContent: [ {  required: true, message: '活动内容', trigger: 'blur' } ], 
		    activityTime: [ {  required: true, message: '活动时段', trigger: 'blur' } ], 
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
				    	url: '../../venuesbook/save?_' + $.now(),
				    	param: vm.bCommunityActivities,
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
