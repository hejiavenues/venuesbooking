/**
 * 新增-居委会信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bCommittees: {
			cid: 0
		},
		rules:{//form 规则
		
			    cid: [ {  required: true, message: '主键', trigger: 'blur' } ], 
		    cname: [ {  required: true, message: '名称', trigger: 'blur' } ], 
		    connectMobile: [ {  required: true, message: '联系方式', trigger: 'blur' } ], 
		    connectPerson: [ {  required: true, message: '负责人', trigger: 'blur' } ], 
		    address: [ {  required: true, message: '地址', trigger: 'blur' } ], 
		    createTime: [ {  required: true, message: '创建时间', trigger: 'blur' } ], 
		    updateTime: [ {  required: true, message: '修改时间', trigger: 'blur' } ]
			
		}
	},
	methods : {
		acceptClick: function() {
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
					 $.SaveForm({
				    	url: '../../venuesbook/committee/save?_' + $.now(),
				    	param: vm.bCommittees,
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
