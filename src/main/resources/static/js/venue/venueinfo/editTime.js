/**
 * 编辑-场馆信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bVenueInfo: {
			venueId: 0,
			times: '',
			commont:'',
			dynamicTags:[],
		},
		avaTimes:[],
		date1:'',
		dynamicTags11111: [],
        inputVisible: false,
        inputValue: '',
		value3: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
		rules:{
		    activityTime: [
	  			 { required: false, message: '请选择活动时间', trigger: 'change' }
	 		],
		},
		pickerOptions1: {
  			disabledDate (time) {
    			return time.getTime() < (Date.now()-8.64e7);
  			}
		},
			
	},
	created:function(){
		this.getAllTimes();
	},
	methods : {
		selectChanged (val) {
			if(!this.bVenueInfo.commont){
				this.bVenueInfo.times='';
				this.date1 = '';
				dialogAlert("请先填写禁用描述");
				return;
			}
			if(!this.date1){
				this.bVenueInfo.times='';
				dialogAlert("请先选择日期");
				return;
			}
			if(this.dynamicTags11111.indexOf(this.date1+' '+val+' '+this.bVenueInfo.commont)==-1){
				this.dynamicTags11111.push(this.date1+' '+val+' '+this.bVenueInfo.commont);
			}
			if(this.bVenueInfo.dynamicTags.indexOf(this.date1+' '+val+' '+this.bVenueInfo.commont)==-1){
				this.bVenueInfo.dynamicTags.push(this.date1+' '+val+' '+this.bVenueInfo.commont);
			}
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
				zs_postFormA(vm,{
                        url: '../../venuesbook/venueinfo/updateUnableTime?_' + $.now(),
                        param: vm.bVenueInfo,
                        success: function(data) {
                            $.currentIframe().vm.load();
                            setTimeout(function() {
                                dialogClose();
                            }, 1000);
                        }
                    });
				}else{
					 return false;
				}
				 
			 });
		},
		getAllTimes:function(){
			var th=this;
		    zs_post({
		    	url:'../../venuesbook/dic/getDicsByCode?typeCode=avaTime',
		    	success:function(r){
		    		th.avaTimes=r.bDics;
		    	}
		    })
		},
		getDynamicTags:function(val){
			var th=this;
		    zs_post({
		    	url:'../../venuesbook/venueinfo/getDynamicTags?venueId='+val,
		    	success:function(r){
					console.log(r.dynamicTags);
		    		th.dynamicTags11111=r.dynamicTags;
					console.log(r.dynamicTags);
					th.bVenueInfo.dynamicTags=r.dynamicTags;
		    	}
		    })
		},
		handleClose(tag) {
        	this.dynamicTags11111.splice(this.dynamicTags11111.indexOf(tag), 1);
      	},
      	showInput() {
        	this.inputVisible = true;
        	this.$nextTick(_ => {
          	this.$refs.saveTagInput.$refs.input.focus();
        	});
      	},

      	handleInputConfirm() {
        	var inputValue = this.inputValue;
        	if (inputValue) {
          	this.dynamicTags11111.push(inputValue);
        	}
        	this.inputVisible = false;
        	this.inputValue = '';
      }
	}
})
