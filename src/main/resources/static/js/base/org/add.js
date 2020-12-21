/**
 * 新增-机构管理js
 */
var vm = new Vue({
	el:'#cashbangLTE',
	data: {
		org: {
			parentId: 0,
			parentName: null,
			orderNum: 0,
			status: 1,
			layer: 1
		}
	},
	methods : {
		orgTree: function() {
			dialogOpen({
				id: 'layerOrgTree',
				title: '选择机构',
		        url: 'base/org/tree.html?_' + $.now(),
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
			
			if(checkedEx("sys_org","code",vm.org.code,"机构编码已被使用,请更换其他编码")){
				return;
			}else{
				 $.SaveForm({
				    	url: '../../sys/org/save?_' + $.now(),
				    	param: vm.org,
				    	success: function(data) {
				    		$.currentIframe().vm.load();
				    	}
				    });
			}
		   
		}
	}
})
