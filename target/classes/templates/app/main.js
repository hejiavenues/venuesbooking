

layui_tab();//tab样式
function layui_tab(){
	//主要数字区域+++++++++++++++++
	layui.use('element', function(){//layui选项卡
		  var element = layui.element;
	});
}


//公用信息

var user={}	;
var mainvm={};//公用部分信息
var rateVm ={};//rate vue变量

msg_user();//用户信息+++++++1
function msg_user(){
	zs_post({
		url: '../../sys/user/info?_' + $.now(),
		async:false,
		success: function(r) {
			user=r.user;
		}
	});
};


setNowChannelAndGetChannels();//设定当前渠道++++2；
function setNowChannelAndGetChannels(){
	 if(user.channel==''){
			$("#changeChannel").show();
			zs_post({
			async:false,
			url: '../../channel/info/list?_' + $.now(),
			success: function(data) {
				var channels=data.rows;
				for(var i=0;i<channels.length;i++){
					$("#changeChannelmain").append('<div class="layui-btn layui-btn-fluid" onclick="changeChannel(\''+data.rows[i].channelCode+'\')" style="background:#00968891;">'+data.rows[i].channelName+'</div><hr>');
				}
				$("#changeChannelmain").append('<div class="layui-btn layui-btn-fluid" onclick="changeChannel()" style="background:#00968891;">取消</div><hr>');
				$("#nowChannel").html("APP");
				mainvm.channel="APP";
				$("#changeChannel").show();
			}
	      });
	 }else{
			mainvm.channel=user.channel;
	 }
};



begin(mainvm.channel);//初始化
function begin(channel){//初始化 可以选择渠道默认app
	mainvm={
			channel:channel,
			m:{
			},
			beginTime:countDay(0),//时间插件时间，初始为今天
			endTime:countDay(0),
			ratebeginTime:countDay(-7),//时间插件时间，初始为今天
			rateendTime:countDay(-0),
			msg:{
				repay:"加载中...",
				click:"加载中...",
				trade:"加载中..."
			},
	};
	//
	loanMain7days();//7天趋势;
	loadMain();//加载主要数据
	getRateVm();//加载ratevm;
}



function loanMain7days(){//7天趋势
	var _this=mainvm;
	zs_post({
		async:false,
		url: '../../report/main7days?_' + $.now(),
		param:{
			channel:_this.channel,
			beginTime7:	countDay(-7),//7日变化时间初始一次不再变化
			endTime7:countDay(0),
		},
		success: function(data) {
			if(code==100){
					getBaseEchart0(data.data.mobileAndClick7Days);
					getBaseEchart1(data.data.trade7Days);
					getBaseEchart3(data.data.credit7Days);
					addjdMsg("vmMain",mainvm);
			}
		}});
};

function loadMain() {
	var _this=mainvm;
		zs_post({
			async:false,
			url: '../../report/main?_' + $.now(),
			param:{
				channel:_this.channel,
				beginTime:_this.beginTime,
				endTime:_this.endTime,
			},
			success: function(data) {
				if(code==100){
					var m=data.data.mainMsg[0];
						mainvm.m=m;
					    getBaseEchart2(m.repaymentSum,m.repaidSum);
						mainvm.msg.click="点击至注册转化率:"+(m.clickSum==0?'0%':parseInt((m.mobileSum/m.clickSum)*100)+'%');
						mainvm.msg.repay="当日应还未还:"+(m.repaymentSum-m.repaidSum)+";";
						mainvm.msg.trade="放款失败:"+m.tradeFail+";";
						mainvm.msg.credit="点击至注册转化率:"+(m.clickSum==0?'0%':parseInt((m.creditSuccess/m.creditSum)*100)+'%');
						addjdMsg("vmMain",mainvm);
					    bigEchart0(m);
			 }
		}});
};

function loadMainEchart() {
	var _this=mainvm;
		zs_post({
			async:false,
			url: '../../report/main?_' + $.now(),
			param:{
				channel:_this.channel,
				beginTime:_this.beginTime,
				endTime:_this.endTime,
			},
			success: function(data) {
				if(code==100){
					var m=data.data.mainMsg[0];
					mainvm.m=m;
				    bigEchart0(m);
			 }
		}});
};


//自定义部分方法及监听
function addjdMsg(f,m){
	　$("#"+f).find(".addmsg").each(function() {
		
	　　	if($(this).attr("id")!=undefined){
	　　		var id=$(this).attr("id");
	  		ids=id.split("_");
	　　		var val=null;
	　　		val=m;
	　　		for(var i=0;i<ids.length;i++){
	　　			val =val[ids[i]]; 
	　　		}
	　　		$(this).html(val);
	　　	}
	　});
}

$("#changeChannel").click(function(){
	$("#changeChannel").hide();
	$("#changeChannelmain").show();
});

$("#li0").click(function(){
	$("#times").show();
	$("#ratetimes").hide();
});
$("#li1").click(function(){
	$("#times").hide();
	$("#ratetimes").show();
});

$(".chalarr").click(function(){
	$(".chalarr").addClass("layui-btn-primary");
	$(this).removeClass("layui-btn-primary")
})
function changeChannel(channel){
	$("#changeChannel").show();
	$("#changeChannelmain").hide();
	if(channel!=undefined){	
		mainvm.channel=channel;
		$("#nowChannel").html(channel);
		begin(channel);
	}
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//+++++++++++++++++++++转化率

function getRateVm(){
	 rateVm = new Vue({
		el:'#rateVmRate',
		data: {
			echartData:[],
			subChannels:[],
			products:[],
			param:{
				subChannel:"",
				productId:"",
				onlySameDay:'true',
			},
			nowData:{},
		},
		methods : {
			load: function() {
				myChart.showLoading();
				this.datagrid();
			},
			//图部分
		 getbigEchart1:function(schannel){
				     d=this.echartData;
				    var bigEchart1 = echarts.init(document.getElementById('bigEchart1'));
					var ledArr=["点击到注册","注册到系统审批通过","注册到人工审批通过","注册到放款成功","注册到授信","风控审批通过件数","注册到授信通过", "授信到系统审批通过",
						 "电核通过率","二级审批通过率","放款成功率"];//x轴
					
					var ledSelected={
							/*注册到系统审批通过:false,注册到授信:false,风控审批通过件数:false,注册到授信通过:false,授信到系统审批通过:false,二级审批通过率:false,
							注册到放款成功:false*/
					};
					
					var allSub={};//所有子渠道
					var dateArr=new Array();//所有日期

					for(var i=0;i<d.length;i++){		//筛选所有日期和子渠道 -》 渠道-》日期
						var di=d[i];
						if(di.subChannelName==null||di.subChannelName==''){
							di.subChannelName="未知";
						}
						if(allSub[di.subChannelName]==undefined){
							allSub[di.subChannelName]={};
						}
						allSub[di.subChannelName][di.createTime]=new Array();
						allSub[di.subChannelName][di.createTime]=di
						if(dateArr.indexOf(di.createTime)==-1){
							dateArr.push(di.createTime);
						}
					}
					var sdata={clickToMobile:[],mobileToRisks:[],mobileToTd2s:[],mobileToTrades:[],mobileToCredit:[],mobileToCredits:[],
							creditToRisks:[],dtToDts:[], dt2ToDt2s:[],dt2sToTrades:[]};
				
					
					//重新整合填补数据   渠道-》子渠道-》数组
					
					if(schannel==undefined){
						var ok=true;
						var str="";
						for(key in allSub){
							if(ok&&key=='合计'){
								schannel=key;
								ok=false;
								str+='<div  class="layui-btn layui-btn-sm  chalarr" style="    border: none;"onclick="rateVm.getbigEchart1(\''+key+'\')"  >'+key+'</div>'
							}else{
								str+='<div  class="layui-btn layui-btn-primary chalarr layui-btn-sm" style="    border: none;" onclick="rateVm.getbigEchart1(\''+key+'\')" >'+key+'</div>'
							}
						}
						$("#schannelArr").html(str);
						
					};
					
						var sub=allSub[schannel];//定位子渠道
						
						for(var i=0;i<dateArr.length;i++){
							var date=dateArr[i];//定位天
							if(sub[date]==undefined){//子渠道某天无数据
								for(k in sdata){
									var lx=sdata[k];
									sdata[k].push(0)//当日无数据一律为0
								}
							}else{
								for(k in sdata){
									var lx=sdata[k];
									var sumi=0;
									if(sub[date][k]!=null&&sub[date][k]!=''){//类型无数据一律为0
										sumi=parseFloat(sub[date][k].replace("%"));
									}
									sdata[k].push(sumi);
								}
							}
						}
					
					

				


				option = {
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    toolbox: {
						orient: 'vertical',//竖着排
			            show : true,
			            top:"10%",
			            feature : {
			                mark : {show: true},
			                dataView : {show: true, readOnly: false},
			                magicType: {show: true, type: ['line', 'bar']},
			                restore : {show: true},
			                saveAsImage : {show: true}
			            }
			        },
			        dataZoom: [
			            {
			                show: true,
			                start: 0,
			                end: 100
			            },
			            {
			                type: 'inside',
			                start: 0,
			                end: 100
			            },
			          /*  {
			                show: true,
			                yAxisIndex: 0,
			                filterMode: 'empty',
			                width: 20,
			                height: '300px',
			                showDataShadow: false,
			                left: '90%',
			            	bottom:"10%"
			            }*/
			        ],
				    legend: {
				        data:ledArr,
			            itemGap: 5//图例标记的图形宽度。
			            ,selected:ledSelected
				    },
				    grid: {
				        left: '3%',
				        right: '10%',
				        bottom: '10%',
				        top:'40%',
				        containLabel: true
				    },
				    xAxis : [
				        {
				            type : 'category',
				            data : dateArr
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            name : '百分比(%)',
				            axisLabel: {
			                    formatter: function (a) {
			                        return a+"%";
			                    }
			                }
				        }
				    ],
				    series : [
				        {
				            name:ledArr[0],
				            type:'line',
				            data:sdata.clickToMobile,
					        symbolSize: 0.3,//去掉空心
				        },
				        {
				        	  symbolSize: 0.3,//去掉空心
				            name:ledArr[1],
				            type:'line',
				            data:sdata.mobileToRisks
				        },
				        {
				        	  symbolSize: 0.3,//去掉空心
				            name:ledArr[2],
				            type:'line',
				            data:sdata.mobileToTd2s
				        },
				        {
				        	  symbolSize: 0.3,//去掉空心
				            name:ledArr[3],
				            type:'line',
				            data:sdata.mobileToTrades
				        },
				   
				        {
				        	  symbolSize: 0.3,//去掉空心
				            name:ledArr[4],
				            type:'line',
				            data:sdata.mobileToCredit
				        },
				        {
				        	  symbolSize: 0.3,//去掉空心
				            name:ledArr[5],
				            type:'line',
				            data:sdata.mobileToCredits
				        },
				        {
				        	  symbolSize: 0.3,//去掉空心
				            name:ledArr[6],
				            type:'line',
				            data:sdata.creditToRisks
				        },
				        {
				        	  symbolSize: 0.3,//去掉空心
				            name:ledArr[7],
				            type:'line',
				            data:sdata.dtToDts
				        },
				        {
				        	  symbolSize: 0.3,//去掉空心
				            name:ledArr[8],
				            type:'line',
				            data:sdata.dt2ToDt2s
				        },
				        {
				        	  symbolSize: 0.3,//去掉空心
				            name:ledArr[9],
				            type:'line',
				            data:sdata.dt2sToTrades
				        },
				    ]
				};
				bigEchart1.setOption(option);
				bigEchart1.hideLoading();

			},
			datagrid : function(){
				var _this=this;
				var parami=_this.param;
				var echartData={};
				parami.channel=mainvm.channel;
				parami.beginTime=mainvm.ratebeginTime;
				parami.endTime=mainvm.rateendTime;
			    zs_post({
	    			url: '../../report/eachLinkRate?_' + $.now(),
	    			param:_this.param,
	    			async:false,
	    			success: function(data) {
	    				echartData=data.data;
	    			}
	    	    });
				this.echartData=echartData;
				_this.getbigEchart1();
			 },
			
		},
		
		created: function() {
			this.datagrid();
			
	    },
	 	mounted : function(){
	 		var _this=this;
	 		layui.use('laydate', function(){
	 			  var laydate = layui.laydate;
	 			  laydate.render({
	 				    elem: '#times'
	 					    ,range: true
	 					    ,	        lang:zsly("layDate"),
	 					    theme: 'molv'
	 					    ,value:mainvm.beginTime+" - "+mainvm.endTime
	 					    ,btns: ['clear','week','mouth', 'ttoday','confirm']
	 					    ,done: function(v, d, e){
	 					    	mainvm.beginTime=d.year+"-"+d.month+"-"+d.date;
	 					    	mainvm.endTime=e.year+"-"+e.month+"-"+e.date;
	 					    	loadMainEchart();
	 					    },
	 			});
	 			 laydate.render({
	 				    elem: '#ratetimes'
	 					    ,range: true
	 					    ,theme: 'molv'
	 					    ,	        lang:zsly("layDate"),
	 					    value:mainvm.ratebeginTime+" - "+mainvm.rateendTime
	 					    ,btns: ['clear','week','mouth', 'ttoday','confirm']
	 					    ,done: function(v, d, e){
	 					    	mainvm.ratebeginTime=d.year+"-"+d.month+"-"+d.date;
	 					    	mainvm.rateendTime=e.year+"-"+e.month+"-"+e.date;
	 					    	_this.datagrid();
	 					    },
	 			});
	 	   });
		},
		updated:function(){
		},
			
	});
};

	


//echarts++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//备用流水百分比
function getBasEcharts(id,a,b){
	var myChart = echarts.init(document.getElementById(id));
	option = {
			
		    series: [{
		        type: 'liquidFill',
		        data: [a/b, 0.05,0.25],
		        radius: '90%',
		        outline: {
		            // show: false
		            borderDistance: 5,
		            itemStyle: {
		                borderWidth: 5,
		                borderColor: '#FFBF11',
		            },
		        },
		        label: {
		            normal: {
		            	   borderWidth: 5,
		                   borderColor: '#156ACF',
		                   shadowBlur: 12,
		                   shadowColor: 'rgba(255, 0, 0, 1)'
		            }
		        },
		    }]
		};
	myChart.setOption(option);
};


//柱形
function getBaseEchart0(a){
	var dateArr=[];
	var clickArr=[];
	var mobileArr=[];
	for(var i=0;i<a.length;i++){
		var ai=a[i];
		if(ai.createTime!='' && ai.createTime!=null){
			dateArr.push(ai.createTime);
			clickArr.push(ai.clickSum);
			mobileArr.push(ai.mobileSum);
		}
	}
	var myChart = echarts.init(document.getElementById("echart0"));
	option = {
			   //backgroundColor: '#1b1b1b',
			  tooltip: {
			        trigger: 'axis',
			        position: function (pt) {
			            return [pt[0], '10%'];
			        }
			    },
		    xAxis: {
		        show:false,
		        data: dateArr
		    },
		    yAxis: {
		        show:false,
		    },
	        color: ['#3FA7DC', '#7091C4', '#5170A2'] ,//柱子颜色 必填参数
		    grid: {
		        left: '3%',
		        right: '3%',
		        bottom:'10%',
		        top:'10%',
		    },
		    series: [{
		    	  name:'点击量',
		    	symbol: "none",
		        data: clickArr,
		        type: 'bar',
		        areaStyle: {}
		    },{
		    	  name:'注册量',
		    	 symbol: "none",
		        data:mobileArr,
		        type: 'bar',
		        areaStyle: {}
		    }]
		};
	myChart.setOption(option);
}
//折线
function getBaseEchart1(a){
/*	if(a.length==0){
		a=[{createTime:countDay(-7),tradeSuccess: 0 },
			{createTime:countDay(-6),tradeSuccess: 0 },
			{createTime:countDay(-5),tradeSuccess: 0 },
			{createTime:countDay(-4),tradeSuccess: 0 },
			{createTime:countDay(-3),tradeSuccess: 0 },
			{createTime:countDay(-2),tradeSuccess: 0 },
			{createTime:countDay(-1),tradeSuccess: 0 },
			{createTime:countDay(0),tradeSuccess: 0 },
			]
	}*/
	var dateArr=[];
	var tradeArr=[];
	for(var i=0;i<a.length;i++){
		var ai=a[i];
		if(ai.createTime!='' && ai.createTime!=null){
			dateArr.push(ai.createTime);
			tradeArr.push(ai.tradeSuccess);
		}
	}
	var myChart = echarts.init(document.getElementById("echart1"));
	option = {
			 tooltip: {
			        trigger: 'axis',
			        position: function (pt) {
			            return [pt[0], '10%'];
			        }
			    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		         show:false,
		        data: dateArr
		    },
		    grid: {
		        left: '3%',
		        right: '3%',
		        bottom:'10%',
		        top:'10%',
		    },
	        color: ['#975fe4', '#7091C4', '#5170A2'] ,//柱子颜色 必填参数
	     
		    yAxis: {
		               show:false,
		        type: 'value'
		    },
		    
		    series: [{
		    	   symbolSize: 0,//去掉空心
		    	smooth: true,
		    	name:"授信转换率",
		        data: tradeArr,
		        type: 'line',
		        areaStyle: {}
		    }]
		};
	myChart.setOption(option);
}
//进度条+++
function getBaseEchart2(a,b){
	if(a==0){
		trate=100;
	}else{
		trate=((b/a)*100).toFixed(2)
	}
	
	var myChart = echarts.init(document.getElementById("echart2"));
	option = {
		    xAxis: {
		        show: false
		    },
		    grid: {
		    	left:'5%',
		        bottom:'15%',
		        top:'40%',
		        right:"5%"
		    },
		    yAxis: [{
		        show: true,
		        data: [''],
		        inverse: true,
		        axisLine: {
		            show: false
		        },
		        splitLine: {
		            show: false
		        },
		        axisTick: {
		            show: false
		        },
		    }, {
		        show: true,
		        inverse: true,
		        data: [],
		        axisLabel: {
		            textStyle: {
		                fontSize: 12,
		            },
		        },
		        axisLine: {
		            show: false
		        },
		        splitLine: {
		            show: false
		        },
		        axisTick: {
		            show: false
		        },

		    }],
		    series: [{
		        name: '条',
		        type: 'bar',
		        yAxisIndex: 0,
		        data: [trate],
		        barWidth: 10,
		        itemStyle: {
		            normal: {  
		               // color: '#13c2c2',
		            }
		        },
		        label: {
		            normal: {
		                show: true,
		                position: 'inside',
		                formatter: '{c}%'
		            }
		        },
		    }, {
		        type: 'bar',
		        yAxisIndex: 1,
		        data: [100],
		        barWidth: 15,
		        itemStyle: {
		            normal: {
		                color: 'none',
		               // borderColor: '#eee',
		                borderWidth: 2,
		                barBorderRadius: 5,
		            }
		        }
		    },  ]
		};
	myChart.setOption(option);
}

//折线
function getBaseEchart3(a){
	var dateArr=[];
	var tradeArr=[];
	for(var i=0;i<a.length;i++){
		var ai=a[i];
		if(ai.createTime!='' && ai.createTime!=null){
			dateArr.push(ai.createTime);
			tradeArr.push(ai.creditSuccess/ai.creditSum+i);
		}
	}
	var myChart = echarts.init(document.getElementById("echart3"));
	option = {
			 tooltip: {
			        trigger: 'axis',
			        position: function (pt) {
			            return [pt[0], '10%'];
			        }
			    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		         show:false,
		        data: dateArr
		    },
		    grid: {
		        left: '3%',
		        right: '3%',
		        bottom:'10%',
		        top:'10%',
		    },
	        color: ['#3FA7DC', '#7091C4', '#5170A2'] ,//柱子颜色 必填参数
	     
		    yAxis: {
		               show:false,
		        type: 'value'
		    },
		    
		    series: [{
		        symbolSize: 0.3,//去掉空心
		    	smooth: true,
		    	name:"放款数",
		        data: tradeArr,
		        type: 'line',
		        areaStyle: {}
		    }]
		};
	myChart.setOption(option);
}
//进度条+++
function getBaseEchart2(a,b){
	if(a==0){
		trate=100;
	}else{
		trate=((b/a)*100).toFixed(2)
	}
	
	var myChart = echarts.init(document.getElementById("echart2"));
	option = {
		    xAxis: {
		        show: false
		    },
		    grid: {
		    	left:'5%',
		        bottom:'15%',
		        top:'40%',
		        right:"5%"
		    },
		    yAxis: [{
		        show: true,
		        data: [''],
		        inverse: true,
		        axisLine: {
		            show: false
		        },
		        splitLine: {
		            show: false
		        },
		        axisTick: {
		            show: false
		        },
		    }, {
		        show: true,
		        inverse: true,
		        data: [],
		        axisLabel: {
		            textStyle: {
		                fontSize: 12,
		            },
		        },
		        axisLine: {
		            show: false
		        },
		        splitLine: {
		            show: false
		        },
		        axisTick: {
		            show: false
		        },

		    }],
		    series: [{
		        name: '条',
		        type: 'bar',
		        yAxisIndex: 0,
		        data: [trate],
		        barWidth: 10,
		        itemStyle: {
		            normal: {  
		                color: '#1089E7',
		            }
		        },
		        label: {
		            normal: {
		                show: true,
		                position: 'inside',
		                formatter: '{c}%'
		            }
		        },
		    }, {
		        type: 'bar',
		        yAxisIndex: 1,
		        data: [100],
		        barWidth: 15,
		        itemStyle: {
		            normal: {
		                color: 'none',
		                borderColor: '#00c1de',
		                borderWidth: 5,
		                barBorderRadius: 5,
		            }
		        }
		    },  ]
		};
	myChart.setOption(option);
}

//数据详情
function bigEchart0(a){
	var myChart = echarts.init(document.getElementById("bigEchart0"));
	var xArr=["点击量","注册数","授信件数","授信通过数","进件件数",
		"风控审批通过件数","进入电核件数", "等待电核件数","电核通过件数","电核拒绝件数",
		"等待二级审批件数", "二级审批通过件数", "二级审批拒绝件数", "放款成功", "放款失败",
		 '应还款','已还款'];
	var xdata=[a.clickSum,a.mobileSum,a.creditSum,a.creditSuccess,a.decisionSum,
			 a.risk_success,a.tdSum,a.waitDt,a.dtSuccess,a.dtFail,
			 a.waitDt2,a.dt2Success,a.dt2Fail,a.tradeSuccess,a.tradeFail,
			a.repaymentSum,a.repaidSum]
	option = {
	    color: ['#3398DB'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '3%',
	        top:"3%",
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            // 'axisLabel':{'interval':0},//刻度间隔
	            data : xArr,
	            axisTick: {
	                alignWithLabel: false//坐标轴在 grid 区域中的分隔线
	            }
	         ,   axisLabel :{  
                interval:0,
                rotate:90,
                textStyle: {
                	fontSize: 12,
               },
            } ,
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'人数',
	            type:'bar',
	            barWidth: '60%',
	            data:xdata
	        }
	    ]
	};
	myChart.setOption(option);
}



