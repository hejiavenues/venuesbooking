/**
 * 编辑-社区活动信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bCommunityActivities: {
			comActivityId: 0
		},
		rules:{//form 规则

		    activityName: [ {  required: true, message: '活动名称', trigger: 'blur' } ], 
		    activityCount: [ {  required: true, message: '活动人数', trigger: 'blur' } ], 
		    activityType: [ {  required: true, message: '活动类型', trigger: 'blur' } ], 
		    activityContent: [ {  required: true, message: '活动内容', trigger: 'blur' } ], 
		    activityTime: [ {  required: true, message: '活动时段', trigger: 'blur' } ]
			
		},
        pickerOptions1: {
            disabledDate (time) {
                return time.getTime() < (Date.now()-8.64e7);
            }
        }
			
	},
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../venuesbook/comactivity/info?_' + $.now(),
		    	param: vm.bCommunityActivities.comActivityId,
		    	success: function(data) {
		    		vm.bCommunityActivities = data;
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
			 $.ConfirmForm({
				    	url: '../../venuesbook/update?_' + $.now(),
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
