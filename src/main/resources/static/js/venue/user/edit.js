/**
 * 编辑-用户信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bUser: {
			uid: 0
		},
        committees: [],
		rules:{//form 规则
		

		    uname: [ {  required: true, message: '用户名称', trigger: 'blur' } ],
		    mobile: [ {  required: true, message: '手机号', trigger: 'blur' } ], 
		    committeeId: [ {  required: true, message: '居委会名称', trigger: 'blur' } ],
		    userRole: [ {  required: false, message: '用户角色', trigger: 'blur' } ]
		    // userAge: [ {  required: true, message: '用户年龄', trigger: 'blur' } ],
		    // password: [ {  required: true, message: '登录密码', trigger: 'blur' } ],
		    // status: [ {  required: true, message: '用户状态（1.正常 2.禁用 3.删除）', trigger: 'blur' } ],
		    // iconUrl: [ {  required: true, message: '头像地址', trigger: 'blur' } ],
		    // createTime: [ {  required: true, message: '创建时间', trigger: 'blur' } ],
		    // updateTime: [ {  required: true, message: '更新时间', trigger: 'blur' } ],
		    // openid: [ {  required: true, message: '微信唯一标识', trigger: 'blur' } ]
			
		}
			
	},
    created:function(){
        this.getComs();
    },
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../venuesbook/user/info?_' + $.now(),
		    	param: vm.bUser.uid,
		    	success: function(data) {
		    		vm.bUser = data;
                    if(data.userRole == 1){
                        vm.bUser.userRole = '普通用户';
                    }else if(data.userRole == 2){
                        vm.bUser.userRole = '居委会';
                    }
                    else if(data.userRole == 3){
                        vm.bUser.userRole = '物业';
                    }
                    else if(data.userRole == 4){
                        vm.bUser.userRole = '综合治理';
                    }
                    else if(data.userRole == 5){
                        vm.bUser.userRole = '工作人员';
                    }
                    ;
		    	}
			});
		},
        getComs:function(){
            var th=this;
            zs_post({
                url:'../../venuesbook/committee/list?_' + $.now(),
                success:function(r){
                    th.committees=r.rows;
                }
            })
        },
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
			 $.ConfirmForm({
				    	url: '../../venuesbook/user/update?_' + $.now(),
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
