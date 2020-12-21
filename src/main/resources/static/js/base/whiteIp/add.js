/**
 * 新增-js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		sysWhiteIp: {
			id: null,
			userId:parent.vm.user.userId,
			userName:parent.vm.user.chnName,
			type:"ip",
			onlyId:null,
		},
		users:[],
		rules:{//form 规则
		
		    onlyId: [ {  required: true, message: 'ip或者userId', trigger: 'blur' } ], 
		    updateTime: [ {  required: true, message: '更新时间', trigger: 'blur' } ],
	        tpye: [ {  required: true, message: '类型', trigger: 'blur' } ]

		}
	},
	created:function(){
		this.getAllUser();
	},
	watch:{
        "sysWhiteIp.type":function(){
        	this.sysWhiteIp.onlyId=null;
        }
	},
	methods : {
		acceptClick: function() {
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
					 $.SaveForm({
				    	url: '../../sys/whiteIp/save?_' + $.now(),
				    	param: vm.sysWhiteIp,
				    	success: function(data) {
				    		$.currentIframe().vm.load();
				    	}
				    });
				}else{
					 return false;
				}
				 
			 });
		},
		getAllUser:function(){
			var th=this;
		    zs_post({
		    	url:'../../sys/user/list?_' + $.now(),
		    	success:function(r){
		    		th.users=r.rows;
		    	}
		    })
		}
	}
})
