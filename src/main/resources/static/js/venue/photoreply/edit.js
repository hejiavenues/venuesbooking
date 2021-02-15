/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bPhotoReply: {
			reply: 0
		},
		rules:{//form 规则
		
			    reply: [ {  required: true, message: '', trigger: 'blur' } ], 
		    photoId: [ {  required: true, message: '随拍Id', trigger: 'blur' } ], 
		    uid: [ {  required: true, message: '用户id', trigger: 'blur' } ], 
		    content: [ {  required: true, message: '评论内容', trigger: 'blur' } ], 
		    createTime: [ {  required: true, message: '', trigger: 'blur' } ], 
		    updateTime: [ {  required: true, message: '', trigger: 'blur' } ]
			
		}
			
	},
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../venuesbook/photoreply/info?_' + $.now(),
		    	param: vm.bPhotoReply.reply,
		    	success: function(data) {
		    		vm.bPhotoReply = data;
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
			 $.ConfirmForm({
				    	url: '../../venuesbook/photoreply/update?_' + $.now(),
				    	param: vm.bPhotoReply,
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
