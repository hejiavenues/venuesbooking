/**
 * 放款额度查询js
 */

var vm = new Vue({
	el:'#cashbangLTE',
	data: {
		queryLoanamtHistory: {}
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
			this.getLoanamt();		//获取放款额度
		},
		edit: function() {
			dialogOpen({
				title: '修改预放款额度',
				url: 'queryinfo/loanamt/edit.html?_' + $.now(),
				width: '420px',
				height: '280px',
				success : function(iframeId) {
					top.frames[iframeId].vm.queryLoanamtHistory = vm.queryLoanamtHistory;
					top.frames[iframeId].vm.queryLoanamtHistory.loanamtAfter = '';
				},
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				}
			});
		},
		datagrid:function(){
			var _this = this;
			$('#dataGrid').bootstrapTableEx({
				url: '../../query/loanamt/list?_' + $.now(),
				height: $(window).height()-200,
				queryParams: function(params){
					//params.name = _this.keyword;
					return params;
				},
				columns: [
					//{checkbox: true},
					{field : "gmtCreate", title : "修改时间", align:"center",width : "100px"},
					//{field : "userNameCreate", title : "修改用户", align:"center",width : "100px"}, 
					{field : "chnNameCreate", title : "修改用户", align:"center",width : "100px",
						formatter : function(value, row, index) {
							return row.userChnNameCreate + '（'+row.userNameCreate+'）';
						}
					}, 
					{field : "loanamtBefore", title : "修改前金额[元]", align:"center",width : "100px"}, 
					{field : "loanamtAfter", title : "修改后金额[元]", align:"center",width : "100px"} 
				]
			})
		
		},
		getLoanamt:function(){
			$.SetForm({
				url: '../../query/loanamt/info/loanamt?_' + $.now(),
		    	//param: vm.queryLoanamtHistory.id,
		    	success: function(data) {
		    		vm.queryLoanamtHistory = data;
		    	}
			});
		}
	 },
	 mounted:function(){
		 this.getLoanamt();		//获取放款额度
		 this.datagrid();
	 }
})