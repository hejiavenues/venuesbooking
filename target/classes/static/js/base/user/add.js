/**
 * 新增-用户管理js
 */

var vm = new Vue({
	el:'#cashbangLTE',
	data: {
		roleList:{},
		user:{
			orgId: 0,
			orgName: null,
			status: 1,
			roleIdList:[],
			channel:parent.user.channel,
		},
		show_:true,
		parentUser:parent.user,
		channelMsg:{
			rows:[],
		}
	},
	methods : {
		loadChannels:function(){
			 $.getJSON("../../channel/info/getChannels?_" + $.now(), function (r) {
	                vm.channelMsg = r;
	            });
		},
		getRoleList: function(){
			$.get('../../sys/role/select?_' + $.now(), function(r){
				vm.roleList = r.rows;
			});
		},
		orgTree: function() {
			dialogOpen({
				id: 'layerOrgTree',
				title: '选择机构',
		        url: 'base/user/org.html?_' + $.now(),
		        scroll : true,
		        width: "300px",
		        height: "450px",
		        yes : function(iframeId) {
		        	top.frames[iframeId].vm.acceptClick();
				}
		    })
		},
		checkChannel:function(str){
			$("#userChannel_").attr("isvalid",str);
			
			 if(vm.parentUser.hasAdmin>0&&"yes"==str){
	        	  $("#userChannel_").attr("disabled",false);
	         }else{
	              $("#userChannel_").attr("disabled",true);
	         }
		},
		
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../sys/user/save?_' + $.now(),
		    	param: vm.user,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	},
	created : function() {
		this.getRoleList();
		this.loadChannels();
		if(this.parentUser.hasAdmin>0){
			$("#userChannel_").attr("disabled",false);
		}
	}
})
