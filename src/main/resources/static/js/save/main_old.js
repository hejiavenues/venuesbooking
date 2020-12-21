
var vm=new Vue({
	el:"#zhishu",
	data:{
		states:{
		},
		user:{},
		channel:"APP",
		channels:[],
		m:{},
		showChangemain:false,
		showChange:false,
		showRatetimes:false,
		showTimes:true,
		beginTime:countDay(0),//时间插件时间，初始为今天
		endTime:countDay(0),
		ratebeginTime:countDay(-7),//时间插件时间，初始为今天
		rateendTime:countDay(-0),
		msg:{
			
		},
		
		
		echartData:[],
		subChannels:[],
		subChannels_nowkey:"全部",
		products:[],
		param:{
			subChannel:"",
			productId:"",
			onlySameDay:'true',
		},
		nowData:{},
		
	},
	i18n,
	created:function(){
		this.layui_tab();
		this.getuser();
		this.msg={repay:this.$t("msg.load"),
		click:this.$t("msg.load"),
		trade:this.$t("msg.load"),
		credit:this.$t("msg.load")}
	},
	
	
	methods : {
		layui_tab:function(){
			//主要数字区域+++++++++++++++++
			layui.use('element', function(){//layui选项卡
				  var element = layui.element;
			});
	 		var _this=this;
	 		layui.use('laydate', function(){
	 			  var laydate = layui.laydate;
	 			  laydate.render({
	 				    elem: '#times'
	 					    ,range: true
	 					    ,
	 				        lang:zsly("layDate"),

	 					    theme: 'molv'
	 					    ,value:_this.beginTime+" - "+_this.endTime
	 					    ,btns: ['clear','week','mouth', 'ttoday','confirm']
	 					    ,done: function(v, d, e){
	 					    	var vs=v.split(" - ");
	 					    	_this.beginTime=vs[0];
	 					    	_this.endTime=vs[1];
	 					    	_this.loadEachLinks();
	 					    },
	 			});
	 			 laydate.render({
	 				    elem: '#ratetimes'
	 					    ,range: true
	 					    ,theme: 'molv',
	 					        lang:zsly("layDate"),

	 					    value:_this.ratebeginTime+" - "+_this.rateendTime
	 					    ,btns: ['clear','week','mouth', 'ttoday','confirm']
	 					    ,done: function(v, d, e){
	 					    	var vs=v.split(" - ");
	 					    	_this.ratebeginTime=vs[0];
	 						    _this.rateendTime=vs[1];
	 					    	_this.datagrid();
	 					    },
	 			});
	 	   });
		},
		getuser:function (){
			var th=this;
			zs_post({
				url: '../../sys/user/info?_' + $.now(),
				success: function(r) {
					th.user=r.user;
					th.setNowChannelAndGetChannels();//设定当前渠道++++2；
				}
			});
		},

		setNowChannelAndGetChannels:function (){
			 var th=this;
			 if(th.user.channel==''){
				
					th.showChange=true;
					zs_post({
					//async:false,
					url: '../../channel/info/list?_' + $.now(),
					success: function(data) {
						th.channels=data.rows;
						th.begin(th.channel);//初始化
					}
					
			      });
			 }else{
					th.channel=th.user.channel;
					th.begin(th.channel);//初始化
			 }
		},
		changeChannel:function (channel){
			this.showChange=true;
			this.showChangemain=false;
			if(channel!=undefined){	
				this.channel=channel;
				this.begin(channel);
			}
		},
		begin :function (channel){//初始化 可以选择渠道默认app
			this.user={};
			this.channel=channel;
			this.m={};
			this.beginTime=countDay(0);//时间插件时间，初始为今天
			this.endTime=countDay(0);
			this.ratebeginTime=countDay(-7);//时间插件时间，初始为今天
			this.rateendTime=countDay(-0);
			this.msg={
				repay:"",
				click:"",
				trade:"",
				credit:""
			};
			//
			this.loanMain7days();//7天趋势;
			this.loadMain();//加载主要数据
			$("#bigEchart1").css("width",$("#bigEchart0").width())
			this.datagrid();//加载rate;
		},
		datagrid : function(){
			var _this=this;
			var parami=_this.param;
			var echartData={};
			parami.channel=_this.channel;
			parami.beginTime=_this.ratebeginTime;
			parami.endTime=_this.rateendTime;
		    zs_post({
    			url: '../../report/eachLinkRate?_' + $.now(),
    			param:parami,
    			success: function(data) {
    				_this.echartData=data.data;;
    				_this.getbigEchart1();
    			}
    	    });
		   
		 },
		loanMain7days:function (){//7天趋势
			var _this=this;
			zs_post({
				//async:false,
				url: '../../report/main7days?_' + $.now(),
				param:{
					channel:_this.channel,
					beginTime7:	countDay(-7),//7日变化时间初始一次不再变化
					endTime7:countDay(0),
				},
				success: function(data) {
					if(code=100){
						_this.getBaseEchart0(data.data.mobileAndClick7Days);
						_this.getBaseEchart1(data.data.trade7Days);
						_this.getBaseEchart3(data.data.credit7Days);
					}
				}});
		},
		loadMain:function () {
			var _this=this;
				zs_post({
					url: '../../report/main?_' + $.now(),
					param:{
						channel:_this.channel,
						beginTime:_this.beginTime,
						endTime:_this.endTime,
					},
					success: function(data) {
						if(code=100){
							var m=data.data.mainMsg[0];
								_this.m=m;
								_this.getBaseEchart2(m.repaymentSum,m.repaidSum);
								_this.msg.click=_this.$t("msg.msg0")+(m.clickSum==0?'0%':parseInt((m.mobileSum/m.clickSum)*100)+'%');
								_this.msg.repay=_this.$t("msg.msg1")+(m.repaymentSum-m.repaidSum)+";";
								_this.msg.trade=_this.$t("msg.msg2")+m.tradeFail+";";
								_this.msg.credit=_this.$t("msg.msg3")+(m.creditSuccess==0?'0%':parseInt((m.creditSuccess/m.creditSum)*100)+'%');
								_this.bigEchart0(m);
					 }
				}});
		},
		loadEachLinks:function () {
			var  th=this;
			zs_post({
				//async:false,
				url: '../../report/main?_' + $.now(),
				param:{
					channel:th.channel,
					beginTime:th.beginTime,
					endTime:th.endTime,
				},
				success: function(data) {
					if(code=100){
						var m=data.data.mainMsg[0];
						th.m=m;
					    th.bigEchart0(m);
				 }
			}});
	},



	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++转化率

	getbigEchart1:function (schannel){
						var th=this;
					     d=vm.echartData;
					    var bigEchart1 = echarts.init(document.getElementById('bigEchart1'));
						var ledArr=th.$t("ec");//x轴
						
						var ledSelected={
								/*注册到系统审批通过:false,注册到授信:false,风控审批通过件数:false,注册到授信通过:false,授信到系统审批通过:false,二级审批通过率:false,
								注册到放款成功:false*/
						};
						
						var allSub={};//所有子渠道
						var dateArr=new Array();//所有日期

						for(var i=0;i<d.length;i++){		//筛选所有日期和子渠道 -》 渠道-》日期
							var di=d[i];
							if(di.subChannelName==null||di.subChannelName==''){
								di.subChannelName=th.$t("ecnull");
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
						th.subChannels=allSub;
						if(schannel==undefined){
							schannel="合计";
						};
						
						th.subChannels_nowkey=schannel;
						
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
				            {
				                show: true,
				                yAxisIndex: 0,
				                filterMode: 'empty',
				                width: 20,
				                height: '300px',
				                showDataShadow: false,
				                left: '90%',
				            	bottom:"10%"
				            }
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
					        top:'25%',
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
					            smooth: true,
						        symbolSize: 0.3,//去掉空心
					        },
					        {
					        	  symbolSize: 0.3,//去掉空心
					            name:ledArr[1],
					            type:'line',
					            smooth: true,
					            data:sdata.mobileToRisks
					        },
					        {
					        	  symbolSize: 0.3,//去掉空心
					            name:ledArr[2],
					            type:'line',
					            smooth: true,
					            data:sdata.mobileToTd2s
					        },
					        {
					        	  symbolSize: 0.3,//去掉空心
					            name:ledArr[3],
					            type:'line',
					            smooth: true,
					            data:sdata.mobileToTrades
					        },
					   
					        {
					        	  symbolSize: 0.3,//去掉空心
					            name:ledArr[4],
					            type:'line',
					            smooth: true,
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
					            smooth: true,
					            data:sdata.creditToRisks
					        },
					        {
					        	  symbolSize: 0.3,//去掉空心
					            name:ledArr[7],
					            type:'line',
					            smooth: true,
					            data:sdata.dtToDts
					        },
					        {
					        	  symbolSize: 0.3,//去掉空心
					            name:ledArr[8],
					            type:'line',
					            smooth: true,
					            data:sdata.dt2ToDt2s
					        },
					        {
					        	  symbolSize: 0.3,//去掉空心
					            name:ledArr[9],
					            type:'line',
					            smooth: true,
					            data:sdata.dt2sToTrades
					        },
					    ]
					};
					bigEchart1.setOption(option);
					bigEchart1.hideLoading();


		
	},

	//echarts++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	//柱形
	getBaseEchart0:function (a){
		var th=this;
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
			    	  name:th.$t("clicks"),
			    	symbol: "none",
			        data: clickArr,
			        type: 'bar',
			        areaStyle: {}
			    },{
			    	  name:th.$t("register"),
			    	 symbol: "none",
			        data:mobileArr,
			        type: 'bar',
			        areaStyle: {}
			    }]
			};
		myChart.setOption(option);
	},
	//折线
	getBaseEchart1:function (a){
		var th=this;
		if(a.length==0){
			a=[{createTime:countDay(-7),tradeSuccess: 0 },
				{createTime:countDay(-6),tradeSuccess: 0 },
				{createTime:countDay(-5),tradeSuccess: 0 },
				{createTime:countDay(-4),tradeSuccess: 0 },
				{createTime:countDay(-3),tradeSuccess: 0 },
				{createTime:countDay(-2),tradeSuccess: 0 },
				{createTime:countDay(-1),tradeSuccess: 0 },
				{createTime:countDay(0),tradeSuccess: 0 },
				]
		}
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
			    	name:th.$t("tradeSuccess"),
			        data: tradeArr,
			        type: 'line',
			        areaStyle: {}
			    }]
			};
		myChart.setOption(option);
	},
	//进度条+++
	getBaseEchart2:function (a,b){
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
	},

	//折线
	getBaseEchart3:function (a){
		var th=this;
		var dateArr=[];
		var tradeArr=[];
		for(var i=0;i<a.length;i++){
			var ai=a[i];
			if(ai.createTime!='' && ai.createTime!=null){
				dateArr.push(ai.createTime);
				tradeArr.push(((ai.creditSuccess/ai.creditSum)*100).toFixed(2));
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
			    	name:th.$t("msg.msg3"),
			        data: tradeArr,
			        type: 'line',
			        areaStyle: {}
			    }]
			};
		myChart.setOption(option);
	},
	//进度条+++
	getBaseEchart2:function (a,b){
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
	},

	//数据详情
	bigEchart0:function (a){
		var myChart = echarts.init(document.getElementById("bigEchart0"));
		var xArr=this.$t("ec1");
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
	                rotate:30,
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
	},

	},
	
});


