/**
 * 新增-活动信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bActivities: {
			activityId: 0
		},
		rules:{//form 规则
		
			    activityId: [ {  required: true, message: '活动id', trigger: 'blur' } ], 
		    venueId: [ {  required: true, message: '场馆id', trigger: 'blur' } ], 
		    uid: [ {  required: true, message: '预约人id', trigger: 'blur' } ], 
		    activityIdName: [ {  required: true, message: '活动名称', trigger: 'blur' } ], 
		    activityCount: [ {  required: true, message: '活动人数', trigger: 'blur' } ], 
		    activityType: [ {  required: true, message: '活动类型', trigger: 'blur' } ], 
		    activityContent: [ {  required: true, message: '活动内容', trigger: 'blur' } ], 
		    activityIconUrl: [ {  required: true, message: '活动图片url', trigger: 'blur' } ], 
		    status: [ {  required: true, message: '活动状态（1.公开 2.不公开）', trigger: 'blur' } ], 
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
				    	url: '../../venuesbook/activities/save?_' + $.now(),
				    	param: vm.bActivities,
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
