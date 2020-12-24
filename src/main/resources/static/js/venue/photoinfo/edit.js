/**
 * 编辑-随拍信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bPhotoInfo: {
			pid: 0
		},
		rules:{//form 规则
		
			    pid: [ {  required: true, message: '主键', trigger: 'blur' } ], 
		    uid: [ {  required: true, message: '用户id', trigger: 'blur' } ], 
		    content: [ {  required: true, message: '内容', trigger: 'blur' } ], 
		    pitureUrls: [ {  required: true, message: '图片地址逗号隔开', trigger: 'blur' } ], 
		    committeeId: [ {  required: true, message: '社区id', trigger: 'blur' } ], 
		    status: [ {  required: true, message: '状态 1.正常 2.删除', trigger: 'blur' } ], 
		    createTime: [ {  required: true, message: '创建时间', trigger: 'blur' } ], 
		    updateTime: [ {  required: true, message: '更新时间', trigger: 'blur' } ]
			
		}
			
	},
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../venuesbook/photoinfo/info?_' + $.now(),
		    	param: vm.bPhotoInfo.pid,
		    	success: function(data) {
		    		vm.bPhotoInfo = data;
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
			 $.ConfirmForm({
				    	url: '../../venuesbook/photoinfo/update?_' + $.now(),
				    	param: vm.bPhotoInfo,
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
