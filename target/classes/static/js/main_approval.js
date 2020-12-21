var vm = new Vue({
  el: "#zhishu",
  data: {
    loadm: true,
    load7: true,
    loadr: true,
    states: {},
    user: {},
    channel:parent.vm.user.channel==undefined||parent.vm.user.channel==null||parent.vm.user.channel==""? "APP":parent.vm.user.channel,
    channels: [],
    m: {},
    showChangemain: false,
    showChange: false,
    showRatetimes: false,
    showTimes: true,
    beginTime: countDay(0),
    //时间插件时间，初始为今天
    endTime: countDay(0),
    ratebeginTime: countDay( - 7),
    //时间插件时间，初始为今天
    rateendTime: countDay( - 0),
    msg: {

},

    echartData: [],
    products: [],
    param: {
      subChannel: "",
      productId: "",
      onlySameDay: 'true',
    },
    nowData: {},
    d1: countDay( - 7),
    d2: countDay( - 6),
    d3: countDay( - 5),
    d4: countDay( - 4),
    d5: countDay( - 3),
    d6: countDay( - 2),
    d7: countDay( - 1),
    d8: countDay(0),
  },
  i18n,
  created: function() {
    this.begin();
  },

  methods: {
    
    begin: function(channel) { //初始化 可以选择渠道默认app
      this.user = {};
      this.m = {};
      this.beginTime = countDay(0); //时间插件时间，初始为今天
      this.endTime = countDay(0);
      this.ratebeginTime = countDay( - 7); //时间插件时间，初始为今天
      this.rateendTime = countDay( - 0);
      this.msg = {
        repay: 0,
        click: 0,
        trade: 0,
        credit: 0
      };
      //
      this.loanMain7days(); //7天趋势;
      this.loadMain(); //加载主要数据
      this.datagrid(); //加载rate;
    },
    datagrid: function() {
      var _this = this;
      var parami = _this.param;
      var echartData = {};
      parami.channel = _this.channel;
      parami.beginTime = _this.ratebeginTime;
      parami.endTime = _this.rateendTime;
      zs_post({
        url: '../../report/mainEachLinkRate?_' + $.now(),
        param: parami,
        success: function(data) {
          _this.loadr = false;
          _this.echartData = data.data;;
          _this.getbigEchart1();
        }
      });

    },
    loanMain7days: function() { //7天趋势
      var _this = this;
      zs_post({
        //async:false,
        url: '../../report/main7days?_' + $.now(),
        param: {
          channel: _this.channel,
          beginTime7: countDay( - 7),
          //7日变化时间初始一次不再变化
          endTime7: countDay(0),
        },
        success: function(data) {
          _this.load7 = false;

          _this.getBaseEchart0(data.data.mobileAndClick7Days);
          _this.getBaseEchart1(data.data.trade7Days);
          _this.getBaseEchart3(data.data.credit7Days);
        }
      });
    },
    loadMain: function() {
      var _this = this;
      zs_post({
        url: '../../report/main?_' + $.now(),
        param: {
          channel: _this.channel,
          beginTime: _this.beginTime,
          endTime: _this.endTime,
        },
        success: function(data) {
          _this.loadm = false;

          var m = data.data.mainMsg[0];
          _this.m = m;
          _this.msg.click = m.clickSum == 0 ? 0 : parseInt((m.mobileSum / m.clickSum) * 100);
          _this.msg.credit = m.decisionSum == 0 ? 0 : parseInt((m.riskResultSuccess / m.decisionSum) * 100);
          _this.bigEchart0(m);
        }
      });
    },

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++转化率
    getbigEchart1: function() {
        var th = this;
        d = vm.echartData;
     

        if ($("#bigEchart1").width() < 200) {
          $("#bigEchart1").width($(window).width() * 0.5)
        }
        
        var bigEchart1 = echarts.init(document.getElementById('bigEchart1'));
        var ledArr = th.$t("ec"); //x轴
        var ledSelected = {
        		  注册到授信:false,风控审批通过件数:false,注册到授信通过:false,授信到系统审批通过:false,二级审批通过率:false,
					放款成功率:false,电核通过率:false
        };

        var d1 = th.d1;
        var d2 = th.d2;
        var d3 = th.d3;
        var d4 = th.d4;
        var d5 = th.d5;
        var d6 = th.d6;
        var d7 = th.d7;
        var d8 = th.d8;

        var  dateArr = [d1, d2, d3, d4, d5, d6, d7, d8]

        var nowMsg={};
        for (var i = 0; i < d.length; i++) { //筛选所有日期和子渠道 -》 渠道-》日期
          var di = d[i];
          if(di.subChannelName=="合计"){
          	nowMsg[di.createTime] = new Array();
          	nowMsg[di.createTime] = di
          }
         
        }
        var sdata = {
          clickToMobile: [],
          mobileToRisks: [],
          mobileToTd2s: [],
          mobileToTrades: [],
          mobileToCredit: [],
          mobileToCredits: [],
          creditToRisks: [],
          dtToDts: [],
          dt2ToDt2s: [],
          dt2sToTrades: []
        };

        //重新整合填补数据   渠道-》子渠道-》数组

        var sub = nowMsg; //定位子渠道
        for (var i = 0; i < dateArr.length; i++) {
          var date = dateArr[i]; //定位天
          if (sub[date] == undefined) { //子渠道某天无数据
            for (k in sdata) {
              var lx = sdata[k];
              sdata[k].push(0) //当日无数据一律为0
            }
          } else {
            for (k in sdata) {
              var lx = sdata[k];
              var sumi = 0;
              if (sub[date][k] != null && sub[date][k] != '') { //类型无数据一律为0
                sumi = parseFloat(sub[date][k].replace("%"));
              }
              sdata[k].push(sumi);
            }
          }
        }

        option = {
          tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
              type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          title: {
            text: "8天部分转化率",
            x: 'center',
            textStyle: {
              color: '#000',
              fontSize: '14'
            }
          },
          toolbox: {
            orient: 'vertical',
            //竖着排
            show: true,
            top: "10%",
            feature: {
              mark: {
                show: true
              },
              dataView: {
                show: true,
                readOnly: false
              },
              magicType: {
                show: true,
                type: ['line', 'bar']
              },
              restore: {
                show: true
              },
              saveAsImage: {
                show: true
              }
            }
          },
          legend: {
            top: "10%",
            data: ledArr,
            itemGap: 5 //图例标记的图形宽度。
            ,
            selected: ledSelected
          },
          grid: {
            left: '3%',
            right: '10%',
            bottom: '10%',
            top: '30%',
            containLabel: true
          },
          xAxis: [{
            type: 'category',
            data: dateArr
          }],
          yAxis: [{
            type: 'value',
            name: '百分比(%)',
            splitLine: {　　　　show: false　　
            },
            axisLabel: {
              formatter: function(a) {
                return a + "%";
              }
            }
          }],
          series: [{
            name: ledArr[0],
            type: 'line',
            data: sdata.clickToMobile,
            smooth: true,
            symbolSize: 0.3,
            //去掉空心
          },
          {
            symbolSize: 0.3,
            //去掉空心
            name: ledArr[1],
            type: 'line',
            smooth: true,
            data: sdata.mobileToRisks
          },
          {
            symbolSize: 0.3,
            //去掉空心
            name: ledArr[2],
            type: 'line',
            smooth: true,
            data: sdata.mobileToTd2s
          },
          {
            symbolSize: 0.3,
            //去掉空心
            name: ledArr[3],
            type: 'line',
            smooth: true,
            data: sdata.mobileToTrades
          },

          {
            symbolSize: 0.3,
            //去掉空心
            name: ledArr[4],
            type: 'line',
            smooth: true,
            data: sdata.mobileToCredit
          },
          {
            symbolSize: 0.3,
            //去掉空心
            name: ledArr[5],
            type: 'line',
            data: sdata.mobileToCredits
          },
          {
            symbolSize: 0.3,
            //去掉空心
            name: ledArr[6],
            type: 'line',
            smooth: true,
            data: sdata.creditToRisks
          },
          {
            symbolSize: 0.3,
            //去掉空心
            name: ledArr[7],
            type: 'line',
            smooth: true,
            data: sdata.dtToDts
          },
          {
            symbolSize: 0.3,
            //去掉空心
            name: ledArr[8],
            type: 'line',
            smooth: true,
            data: sdata.dt2ToDt2s
          },
          {
            symbolSize: 0.3,
            //去掉空心
            name: ledArr[9],
            type: 'line',
            smooth: true,
            data: sdata.dt2sToTrades
          },
          ]
        };
        bigEchart1.setOption(option);
        bigEchart1.hideLoading();

      },

    //echarts++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //柱形
    getBaseEchart0: function(a) {
      var th = this;
      var dateArr = [];
      var clickArr = [];
      var mobileArr = [];

      var th = this;
      var d1 = th.d1;
      var d2 = th.d2;
      var d3 = th.d3;
      var d4 = th.d4;
      var d5 = th.d5;
      var d6 = th.d6;
      var d7 = th.d7;
      var d8 = th.d8;

      var json1 = { [d1] : 0,
        [d2] : 0,
        [d3] : 0,
        [d4] : 0,
        [d5] : 0,
        [d6] : 0,
        [d7] : 0,
        [d8] : 0
      };
      var json2 = { [d1] : 0,
        [d2] : 0,
        [d3] : 0,
        [d4] : 0,
        [d5] : 0,
        [d6] : 0,
        [d7] : 0,
        [d8] : 0
      };
      for (var i = 0; i < a.length; i++) {
        var ai = a[i];
        json1[ai.createTime] = ai.clickSum;
        json2[ai.createTime] = ai.mobileSum;
      }
      var dateArr = [d1.substr(5), d2.substr(5), d3.substr(5), d4.substr(5), d5.substr(5), d6.substr(5), d7.substr(5), d8.substr(5)];
      var clickArr = [json1[d1], json1[d2], json1[d3], json1[d4], json1[d5], json1[d6], json1[d7], json1[d8], ];;
      var mobileArr = [json2[d1], json2[d2], json2[d3], json2[d4], json2[d5], json2[d6], json2[d7], json2[d8], ];;
      if ($("#echart0").width() < 200) {
        $("#echart0").width($(window).width() * 0.1)
      }
      var myChart = echarts.init(document.getElementById("echart0"));
      option = {
        //backgroundColor: '#1b1b1b',
        tooltip: {
          trigger: 'axis',
          position: function(pt) {
            return [pt[0], '10%'];
          }
        },
        title: {
          text: "8天点击/注册数",
          x: 'center',
          textStyle: {
            color: '#000',
            fontSize: '14'
          }
        },
        xAxis: {
          show: true,
          data: dateArr
        },
        yAxis: {
          show: true,
          splitLine: {　　　　show: false　　
          },
        },
        color: ['#3FA7DC', '#7091C4', '#5170A2'],
        //柱子颜色 必填参数
        grid: {
          left: '8%',
          right: '8%',
          bottom: '10%',
          top: '20%',
        },
        series: [{
          name: th.$t("clicks"),
          symbol: "none",
          data: clickArr,
          type: 'bar',
          areaStyle: {}
        },
        {
          name: th.$t("register"),
          symbol: "none",
          data: mobileArr,
          type: 'bar',
          areaStyle: {}
        }]
      };
      myChart.setOption(option);
    },
    //7天放款
    getBaseEchart1: function(a) {

      var th = this;
      var d1 = th.d1;
      var d2 = th.d2;
      var d3 = th.d3;
      var d4 = th.d4;
      var d5 = th.d5;
      var d6 = th.d6;
      var d7 = th.d7;
      var d8 = th.d8;

      var json = { [d1] : 0,
        [d2] : 0,
        [d3] : 0,
        [d4] : 0,
        [d5] : 0,
        [d6] : 0,
        [d7] : 0,
        [d8] : 0
      };

      for (var i = 0; i < a.length; i++) {
        var ai = a[i];
        json[ai.createTime] = ai.tradeSuccess;
      }
      var dateArr = [d1.substr(5), d2.substr(5), d3.substr(5), d4.substr(5), d5.substr(5), d6.substr(5), d7.substr(5), d8.substr(5)];
      var tradeArr = [json[d1], json[d2], json[d3], json[d4], json[d5], json[d6], json[d7], json[d8], ];
      if ($("#echart1").width() < 300) {
        $("#echart1").width($(window).width() * 0.1)
      }
      var myChart = echarts.init(document.getElementById("echart1"));

      option = {
        backgroundColor: '#fff',
        title: {
          text: "8天放款数",
          x: 'center',
          textStyle: {
            color: '#000',
            fontSize: '14'
          }
        },
        tooltip: {
          trigger: 'item'
        },
        grid: {
          left: '8%',
          right: '8%',
          bottom: '3%',
          top: '20%',
        },
        dataRange: {
          show: false,
          min: 0,
          max: 12,
          calculable: true,
          color: ['#d94e5d', '#eac736', '#50a3ba'],
          y: 'center',
        },

        xAxis: [{
          type: 'category',
          show: true,
          data: dateArr
        }],
        yAxis: [{
          type: 'value',
          show: true,
          splitLine: {　　　　show: false　　
          },
        }],
        animationEasing: 'elasticOut',
        animationEasingUpdate: 'elasticOut',
        animationDelay: function(idx) {
          return idx * 20;
        },
        animationDelayUpdate: function(idx) {
          return idx * 20;
        },
        series: [{
          name: th.$t("tradeSuccess"),
          type: 'bar',
          barGap: 0.1,
          barCategoryGap: 0.01,
          itemStyle: {
            normal: {
              color: function(params) {
                // build a color map as your need.
                var colorList = ['#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B', '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD', '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'];
                return colorList[params.dataIndex]
              },
              label: {
                show: true,
                position: 'top',
                formatter: '{b}'
              },
            }
          },
          data: tradeArr.map(function(a, idx) {
            return [idx, a, idx];
          })
        }]
      };
      myChart.setOption(option);
    },

    //折线
    getBaseEchart3: function(a) {
      var th = this;
      var d1 = th.d1;
      var d2 = th.d2;
      var d3 = th.d3;
      var d4 = th.d4;
      var d5 = th.d5;
      var d6 = th.d6;
      var d7 = th.d7;
      var d8 = th.d8;

      var json = { [d1] : 0,
        [d2] : 0,
        [d3] : 0,
        [d4] : 0,
        [d5] : 0,
        [d6] : 0,
        [d7] : 0,
        [d8] : 0
      };

      for (var i = 0; i < a.length; i++) {
        var ai = a[i];
        json[ai.createTime] = ai.creditSum == 0 ? 0 : ((ai.creditSuccess / ai.creditSum) * 100).toFixed(2);
      }
      var dateArr = [d1.substr(5), d2.substr(5), d3.substr(5), d4.substr(5), d5.substr(5), d6.substr(5), d7.substr(5), d8.substr(5)];
      var tradeArr = [json[d1], json[d2], json[d3], json[d4], json[d5], json[d6], json[d7], json[d8], ];

      if ($("#echart3").width() < 300) {
        $("#echart3").width($(window).width() * 0.1)
      }

      var myChart = echarts.init(document.getElementById("echart3"));
      option = {
        tooltip: {
          trigger: 'axis',
          position: function(pt) {
            return [pt[0], '10%'];
          }
        },

        title: {
          text: "8天进件审批通过率",
          x: 'center',
          textStyle: {
            color: '#000',
            fontSize: '14'
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          show: true,
          data: dateArr,
          　
        },
        grid: {
          left: '8%',
          right: '8%',
          bottom: '10%',
          top: '20%',
        },
        color: ['#3FA7DC', '#7091C4', '#5170A2'],
        //柱子颜色 必填参数
        yAxis: {
          show: true,
          type: 'value',
          splitLine: {　　　　show: false　　
          }
        },

        series: [{
          symbolSize: 0.3,
          //去掉空心
          smooth: true,
          name: th.$t("msg.msg3"),
          data: tradeArr,
          type: 'line',
          areaStyle: {}
        }]
      };
      myChart.setOption(option);
    },

    //数据详情
    bigEchart0: function(a) {
      if ($("#bigEchart0").width() < 300) {
        $("#bigEchart0").width($(window).width() * 0.5)
      }
      var myChart = echarts.init(document.getElementById("bigEchart0"));
      var xArr =  ["点击量","注册数","进件件数",
			"风控审批通过件数","进入电核件数", "等待电核件数","电核通过件数","电核拒绝件数",
			"等待二级审批件数", "二级审批通过件数", "二级审批拒绝件数","进件通过件数", "放款成功", "放款失败",
			 '应还款','已还款'];
      var xdata = [a.clickSum, a.mobileSum, a.decisionSum, a.riskSuccess, a.dtSum, a.waitDt, a.dtSuccess, a.dtFail, a.waitDt2, a.dt2Success,a.dt2Fail,  a.riskResultSuccess, a.tradeSuccess, a.tradeFail, a.repaymentSum, a.repaidSum];
      option = {
        color: ['#3398DB'],
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        toolbox: {
          orient: 'vertical',
          //竖着排
          show: true,
          top: "10%",
          feature: {
            mark: {
              show: true
            },
            dataView: {
              show: true,
              readOnly: false
            },
            magicType: {
              show: true,
              type: ['line', 'bar']
            },
            restore: {
              show: true
            },
            saveAsImage: {
              show: true
            }
          }
        },
        title: {
          text: "今日数据详情",
          x: 'center',
          textStyle: {
            color: '#000',
            fontSize: '14'
          }
        },
        grid: {
          left: '3%',
          top: "15%",
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          // 'axisLabel':{'interval':0},//刻度间隔
          data: xArr,
          axisTick: {
            alignWithLabel: false //坐标轴在 grid 区域中的分隔线
          },
          axisLabel: {
            interval: 0,
            rotate: 30,
            textStyle: {
              fontSize: 12,
            },

          },
          axisLine: {
            lineStyle: {
              type: 'solid',
              width: '1  ',
              //坐标线的宽度
            }
          },

        },
        yAxis: {
          splitLine: {
            show: true,
            lineStyle: {
              color: '#40A1EA',
              //网格横线颜色
              width: 1,
              type: 'solid'
            }
          },

          axisLine: {
            show: false,
          },
        },
        series: [{
          name: '人数',
          type: 'bar',
          barWidth: '60%',
          data: xdata,
          itemStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: '#06B5D7'
              },
              //柱图渐变色
              {
                offset: 0.5,
                color: '#44C0C1'
              },
              //柱图渐变色
              {
                offset: 1,
                color: '#71C8B1'
              },
              //柱图渐变色
              ])
            },
            emphasis: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: '#71C8B1'
              },
              //柱图高亮渐变色
              {
                offset: 0.7,
                color: '#44C0C1'
              },
              //柱图高亮渐变色
              {
                offset: 1,
                color: '#06B5D7'
              } //柱图高亮渐变色
              ])
            }
          },
        },

        ]
      };
      myChart.setOption(option);
    },

  },

});