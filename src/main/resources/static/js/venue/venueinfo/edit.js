/**
 * 编辑-场馆信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bVenueInfo: {
			venueId: 0,
			imgFile:null,
		},
		committees:[],
		imageUrl: '',
		rules:{//form 规则
		
			    venueId: [ {  required: true, message: '主键', trigger: 'blur' } ], 
		    venueName: [ {  required: true, message: '场馆名称', trigger: 'blur' } ], 
		    maxPeople: [ {  required: true, message: '最大容纳人数', trigger: 'blur' } ], 
		    address: [ {  required: true, message: '场馆地址', trigger: 'blur' } ], 
		    /*committeeId: [ {  required: true, message: '所属居委会id', trigger: 'blur' } ], 
		    supportActiveType: [ {  required: true, message: '支持的活动类型', trigger: 'blur' } ], 
		    iconUrl: [ {  required: true, message: '图片地址', trigger: 'blur' } ], 
		    createTime: [ {  required: true, message: '创建时间', trigger: 'blur' } ], 
		    updateTime: [ {  required: true, message: '修改时间', trigger: 'blur' } ]*/
			
		}
			
	},
	created:function(){
		this.getAllUser();
	},
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../venuesbook/venueinfo/info?_' + $.now(),
		    	param: vm.bVenueInfo.venueId,
		    	success: function(data) {
		    		vm.bVenueInfo = data;
					vm.imageUrl = data.iconUrl;
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
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
        }
	}
})
