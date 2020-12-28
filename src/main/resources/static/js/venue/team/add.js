/**
 * 新增-团队信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bTeam: {
			tid: 0
		},
		rules:{//form 规则
		
			    tid: [ {  required: true, message: '主键', trigger: 'blur' } ], 
		    uid: [ {  required: true, message: '团长id', trigger: 'blur' } ], 
		    tname: [ {  required: true, message: '姓名', trigger: 'blur' } ], 
		    peopleCount: [ {  required: true, message: '招募人数', trigger: 'blur' } ], 
		    activityType: [ {  required: true, message: '活动类型', trigger: 'blur' } ], 
		    activityContent: [ {  required: true, message: '活动内容', trigger: 'blur' } ], 
		    enterCondition: [ {  required: true, message: '加入条件', trigger: 'blur' } ], 
		    status: [ {  required: true, message: '状态 1放开、2关闭、3满员、4.组队失败', trigger: 'blur' } ], 
		    deadLine: [ {  required: true, message: '截止时间', trigger: 'blur' } ], 
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
				    	url: '../../venuesbook/team/save?_' + $.now(),
				    	param: vm.bTeam,
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
