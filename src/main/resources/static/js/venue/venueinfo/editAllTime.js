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
				dialogAlert("请先填写禁用描述");
				return ;
			}
			if(this.dynamicTags11111.indexOf(val)==-1){
				this.dynamicTags11111.push(val+" "+this.bVenueInfo.commont);
			}
			if(this.bVenueInfo.dynamicTags.indexOf(val)==-1){
				this.bVenueInfo.dynamicTags.push(val+" "+this.bVenueInfo.commont);
			}
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
				zs_postFormA(vm,{
                        url: '../../venuesbook/venueinfo/updateAllUnableTime?_' + $.now(),
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
		getDynamicTags:function(){
			var th=this;
		    zs_post({
		    	url:'../../venuesbook/dic/getDicsByCode?typeCode=unAvaTime',
		    	success:function(r){
					console.log(r.bDics);
					for(var a=0;a<r.bDics.length;a++){
						if(th.dynamicTags11111.indexOf(r.bDics[a].name)==-1){
							th.dynamicTags11111.push(r.bDics[a].name);
						}
						if(th.bVenueInfo.dynamicTags.indexOf(r.bDics[a].name)==-1){
							th.bVenueInfo.dynamicTags.push(r.bDics[a].name);
						}
					};
		    	}
		    })
		},
		handleClose(tag) {
			console.log(111111111)
        	this.dynamicTags11111.splice(this.dynamicTags11111.indexOf(tag), 1);
        	this.bVenueInfo.dynamicTags.splice(this.bVenueInfo.dynamicTags.indexOf(tag), 1);
      	},
      	showInput() {
        	this.inputVisible = true;
        	this.$nextTick(_ => {
          	this.$refs.saveTagInput.$refs.input.focus();
        	});
      	},

      	handleInputConfirm() {
			console.log(22222222)
        	var inputValue = this.inputValue;
        	if (inputValue) {
          	this.dynamicTags11111.push(inputValue);
        	}
        	this.inputVisible = false;
        	this.inputValue = '';
      }
	}
})
