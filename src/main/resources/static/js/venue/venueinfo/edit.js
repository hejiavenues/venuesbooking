/**
 * 编辑-场馆信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bVenueInfo: {
			venueId: 0,
			times: '',
			dynamicTags:[],
			imgFile:null,
		},
		avaTimes:[],
		dynamicTags11111: [],
		committees:[],
		inputVisible: false,
		imageUrl: '',
		rules:{//form 规则
		
			    venueId: [ {  required: true, message: '主键', trigger: 'blur' } ], 
		    venueName: [ {  required: true, message: '场馆名称', trigger: 'blur' } ],
            status: [ {  required: true, message: '状态', trigger: 'blur' } ],
            maxPeople: [ {  required: true, message: '最大容纳人数', trigger: 'blur' } ],
		    address: [ {  required: true, message: '场馆地址', trigger: 'blur' } ], 
			supportActiveType: [ {  required: true, message: '支持的活动类型', trigger: 'blur' } ], 
		    /*committeeId: [ {  required: true, message: '所属居委会id', trigger: 'blur' } ], 
		    supportActiveType: [ {  required: true, message: '支持的活动类型', trigger: 'blur' } ], 
		    iconUrl: [ {  required: true, message: '图片地址', trigger: 'blur' } ], 
		    createTime: [ {  required: true, message: '创建时间', trigger: 'blur' } ], 
		    updateTime: [ {  required: true, message: '修改时间', trigger: 'blur' } ]*/
			
		}
			
	},
	watch:{
        	"bVenueInfo.supportActiveType":function(a,b){
        		this.getDynamicTags();
        	}
		},
	created:function(){
		this.getAllUser();
		this.getAllTimes();
	},
	methods : {
		selectChanged (val) {
			console.log("val.code:"+val.code);
			console.log("dynamicTags11111:"+this.dynamicTags11111);
			console.log("dynamicTags:"+this.bVenueInfo.dynamicTags);
			if(this.dynamicTags11111.indexOf(val.name)==-1){
				this.dynamicTags11111.push(val.name);
			}
			if(this.bVenueInfo.dynamicTags.indexOf(val.code)==-1){
				this.bVenueInfo.dynamicTags.push(val.code);
			}
			console.log("val.code:"+val.code);
			console.log("dynamicTags11111:"+this.dynamicTags11111);
			console.log("dynamicTags:"+this.bVenueInfo.dynamicTags);
		},
	   setForm: function() {
			$.SetForm({
				url: '../../venuesbook/venueinfo/info?_' + $.now(),
		    	param: vm.bVenueInfo.venueId,
		    	success: function(data) {
		    		vm.bVenueInfo = data;
					vm.imageUrl = data.iconUrl;
                    if(data.status == 0){
                        vm.bVenueInfo.status = '可用';
                    }else if(data.status == 1){
                        vm.bVenueInfo.status = '隐藏';
                    };
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
              if(vm.bVenueInfo.status == '可用'){
                  vm.bVenueInfo.status = 0;
              }else if(vm.bVenueInfo.status == '隐藏'){
                  vm.bVenueInfo.status = 1;
              };
              console.log("--------"+vm.bVenueInfo.status )

				if(yes){
			 /*$.ConfirmForm({
				    	url: '../../venuesbook/venueinfo/update?_' + $.now(),
				    	param: vm.bVenueInfo,
				    	success: function(data) {
				    		$.currentIframe().vm.load();
				    	}
				    });*/
				zs_postFormA(vm,{
                        url: '../../venuesbook/venueinfo/update?_' + $.now(),
                        param: vm.bVenueInfo,
                        success: function(data) {
                            vm.$message.success('修改成功');
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
		getAllUser:function(){
			var th=this;
		    zs_post({
		    	url:'../../venuesbook/committee/list?_' + $.now(),
		    	success:function(r){
		    		th.committees=r.rows;
		    	}
		    })
		},
		getAllTimes:function(){
			var th=this;
		    zs_post({
		    	url:'../../venuesbook/dic/getDicsByCode?typeCode=activityType',
		    	success:function(r){
		    		th.avaTimes=r.bDics;
		    	}
		    })
		},
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
            //var isJPG = file.type === 'image/jpeg';
            var allowTypes = ['image/jpg','image/jpeg','image/png'];
            var isLt2M = file.size / 1024 / 1024 < 2;

            if (allowTypes.indexOf(file.type) === -1) {
                this.$message.error('上传图片仅支持jpg、png格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 2MB!');
            }

            // 压缩图片
            getSmallerFile(file,0.5,2048,function (base64Code,file) {
                vm.bVenueInfo.imgFile = file;

            });
            return true;
        },
		
		getDynamicTags:function(){
			var th=this;
		    zs_post({
		    	url:'../../venuesbook/dic/getDicsByCode?typeCode=activityType',
		    	success:function(r){
					console.log(r.bDics);
					for(var a=0;a<r.bDics.length;a++){
						if(th.dynamicTags11111){
							if(th.dynamicTags11111.indexOf(r.bDics[a].code)==-1){
								if((','+th.bVenueInfo.supportActiveType+',').indexOf(','+r.bDics[a].code+',')>=0){
									th.dynamicTags11111.push(r.bDics[a].name);
								}
							}
						}
						
						th.bVenueInfo.dynamicTags = th.bVenueInfo.supportActiveType.split(',');
						/*if(th.bVenueInfo.dynamicTags){
							if(th.bVenueInfo.dynamicTags.indexOf(r.bDics[a].code)==-1){
								if((','+th.bVenueInfo.supportActiveType+',').indexOf(','+r.bDics[a].code+',')>=0){
									th.bVenueInfo.dynamicTags.push(r.bDics[a].code);
								}
							}
						}*/
					};
					console.log("th.dynamicTags11111"+th.dynamicTags11111);
					console.log("th.bVenueInfo.dynamicTags"+th.bVenueInfo.dynamicTags);
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
