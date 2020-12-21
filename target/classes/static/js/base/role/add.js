/**
 * 新增-角色管理js
 */
var vm = new Vue({
	el:'#cashbangLTE',
	data: {
		role: {
			orgId: 0,
			orgName: null
		}
	},
	methods : {
		orgTree: function() {
			dialogOpen({
				id: 'layerOrgTree',
				title: '选择机构',
		        url: 'base/role/org.html?_' + $.now(),
		        scroll : true,
		        width: "300px",
		        height: "450px",
		        yes : function(iframeId) {
		        	top.frames[iframeId].vm.acceptClick();
				}
		    })
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		/*	
			if(checkedEx("sys_role","role_sign",vm.role.roleSign,"标识已被使用,请更换其他标识")){
				return;
			}else{*/
				 $.SaveForm({
				    	url: '../../sys/role/save?_' + $.now(),
				    	param: vm.role,
				    	success: function(data) {
				    		$.currentIframe().vm.load();
				    	}
				    });
		/*	}*/
		   
		}
	}
})
