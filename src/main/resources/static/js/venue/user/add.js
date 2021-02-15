/**
 * 新增-用户信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bUser: {
			uid: 0
		},
		rules:{//form 规则
		
			    uid: [ {  required: true, message: '用户id', trigger: 'blur' } ], 
		    uname: [ {  required: true, message: '用户名称', trigger: 'blur' } ], 
		    sex: [ {  required: true, message: '用户性别（1.男 0.女）', trigger: 'blur' } ], 
		    birthday: [ {  required: true, message: '出生日期', trigger: 'blur' } ], 
		    mobile: [ {  required: true, message: '手机号', trigger: 'blur' } ], 
		    committeeId: [ {  required: true, message: '居委会id', trigger: 'blur' } ], 
		    userRole: [ {  required: true, message: '角色id （1.普通用户 2.召集人）', trigger: 'blur' } ], 
		    userAge: [ {  required: true, message: '用户年龄', trigger: 'blur' } ], 
		    password: [ {  required: true, message: '登录密码', trigger: 'blur' } ], 
		    status: [ {  required: true, message: '用户状态（1.正常 2.禁用 3.删除）', trigger: 'blur' } ], 
		    iconUrl: [ {  required: true, message: '头像地址', trigger: 'blur' } ], 
		    createTime: [ {  required: true, message: '创建时间', trigger: 'blur' } ], 
		    updateTime: [ {  required: true, message: '更新时间', trigger: 'blur' } ], 
		    openid: [ {  required: true, message: '微信唯一标识', trigger: 'blur' } ]
			
		}
	},
	methods : {
		acceptClick: function() {
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
					 $.SaveForm({
				    	url: '../../venuesbook/user/save?_' + $.now(),
				    	param: vm.bUser,
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
