/**
 * 编辑-banner图表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bBannerInfo: {
			imgFile:null,
            bannerDesc:'',
            bannerJumpUrl:'',
            bizzType:null,
            isuse:null,
		},
		imageUrl: '',
		rules:{//form 规则
		    bannerDesc: [ {  required: true, message: 'banner图描述', trigger: 'blur' } ], 
		    sortid: [ {  required: true, message: 'banner排序id', trigger: 'blur' } ], 
		    isuse: [ {  required: true, message: '是否上架（1.上架 0.下架）', trigger: 'blur' } ]
		}
			
	},
	methods : {
	   setForm: function() {
			$.SetForm({
				url: '../../venuesbook/banner/info?_' + $.now(),
		    	param: vm.bBannerInfo.bid,
		    	success: function(data) {
		    		vm.bBannerInfo = data;
					vm.imageUrl = data.bannerImgUrl;
					if(data.isuse == 0){
                        vm.bBannerInfo.isuse = '禁用';
                    }else if(data.isuse == 1){
                        vm.bBannerInfo.isuse = '启用';
                    };
					if(data.bizztype == 1){
                        vm.bBannerInfo.bizztype = '首页';
                    }else if(data.bizztype == 2){
                        vm.bBannerInfo.bizztype = '轮播';
                    };
		    	}
			});
		},
		acceptClick: function() {
		
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
					 if(vm.bBannerInfo.isuse == '禁用'){
                        vm.bBannerInfo.isuse = 0;
                    }else if(vm.bBannerInfo.isuse == '启用'){
                        vm.bBannerInfo.isuse = 1;
                    }
					 if(vm.bBannerInfo.bizztype == '首页'){
                        vm.bBannerInfo.bizztype = 1;
                    }else if(vm.bBannerInfo.bizztype == '轮播'){
                        vm.bBannerInfo.bizztype = 2;
                    }
				zs_postFormA(vm,{
                        url: '../../venuesbook/banner/update?_' + $.now(),
                        param: vm.bBannerInfo,
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
                vm.bBannerInfo.imgFile = file;

            });
            return true;
        }
	}
})
