/**
 * js
 */

var vm = new Vue({
	el:'#cashbangLTE',
	data: {
		params:{
			orderNo:"",
			applyCode:"",
			mobile:"",
			type:"err",
		}
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		save: function() {
			dialogOpen({
				title: '新增',
				url: 'trade/decisonResult/add.html?_' + $.now(),
				width: '420px',
				height: '350px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if(checkedRow(ck)){
				dialogOpen({
					title: '编辑',
					url: 'trade/decisonResult/edit.html?_' + $.now(),
					width: '420px',
					height: '350px',
					success: function(iframeId){
						top.frames[iframeId].vm.tradeDecisionResult.id = ck[0].id;
						top.frames[iframeId].vm.setForm();
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		remove: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.id;
				});
				$.RemoveForm({
					url: '../../trade/decisonResult/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		},
		datagrid:function(){
			var _this = this;
			$('#dataGrid').bootstrapTableEx({
				url: '../../trade/decisonResult/list?_' + $.now(),
				height: $(window).height()-56,
				queryParams: function(params){
					for(key in _this.params){
						params[key]=_this.params[key];
					}
					return params;
				},
				columns: [
					{checkbox: true},
				/*	{field : "id", title : "物理主键", width : "100px"}, */
					{field : "createTime", title : "创建时间", width : "100px"}, 
				/*	{field : "updateTime", title : "修改时间", width : "100px"}, */
					/*{field : "version", title : "", width : "100px"}, */
					{field : "orderNo", title : "appserver单号", width : "100px"}, 
					{field : "applyCode", title : "申请件id", width : "100px"}, 
					{field : "applyType", title : "类型", width : "100px"}, //:Credit授信,Decision决策
					{field : "mobile", title : "手机号", width : "100px"}, 
					{field : "name", title : "姓名", width : "100px"}, 
					{field : "remark", title : "备注", width : "100px"}, 
					{field : "auditResult", title : "决策结果", width : "100px"}, //（0：通过，1：拒绝，3：失败， 9：未处理）
					/*{field : "finalResult", title : "", width : "100px"}, 
					{field : "zhimaCredit", title : "", width : "100px"}, */
					{field : "hitType", title : "", width : "100px"}, 
					{field : "ruleResult", title : "", width : "100px"}, 
					{field : "decisionScore", title : "", width : "100px"}, 
					{field : "scoreLevel", title : "", width : "100px"}, 
					{field : "creditAmount", title : "", width : "100px"}, 
					{field : "creditPeriod", title : "", width : "100px"}, 
					{field : "validityStart", title : "有效期开始", width : "100px"}, 
					{field : "validityEnd", title : "有效期结束", width : "100px"}, 
					/*{field : "outRuleNum", title : "", width : "100px"}, 
					{field : "testSetName", title : "", width : "100px"}, 
					{field : "testGroupName", title : "", width : "100px"}, 
					{field : "testRandom", title : "", width : "100px"}, 
					{field : "leafNodeName", title : "", width : "100px"},  */
					{field : "inputMap", title : "", width : "100px"},
					{field : "innerRandom", title : "", width : "100px"}, 
					{field : "userType", title : "", width : "100px"}, 
					{field : "customerType", title : "", width : "100px"}
				]
			})
		
		}
	 },
	 mounted:function(){
	 	this.datagrid();
	 },
	 created: function() {
			vueGetLayui('form');
	  },
})