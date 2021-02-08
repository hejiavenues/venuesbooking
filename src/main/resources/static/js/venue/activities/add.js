/**
 * 新增-活动信息表js
 */
var vm = new Vue({
	el:'#zsvm',
	data: {
		bActivities: {
			activityId: 0,
	        imgFile: null,
		},
        avaTimes:[],
        activityTypes: [],
        venueinfos: [],
        inputVisible: false,
        imageUrl: '',
        date1:'',
		rules:{//form 规则

		    venueId: [ {  required: true, message: '场馆id', trigger: 'blur' } ],
		    activityIdName: [ {  required: true, message: '活动名称', trigger: 'blur' } ], 
		    activityCount: [ {  required: true, message: '活动人数', trigger: 'blur' } ], 
		    activityType: [ {  required: true, message: '活动类型', trigger: 'blur' } ], 
		    activityContent: [ {  required: true, message: '活动内容', trigger: 'blur' } ], 
		    // activityIconUrl: [ {  required: true, message: '活动图片url', trigger: 'blur' } ],
		    status: [ {  required: true, message: '活动状态（1.公开 2.不公开）', trigger: 'blur' } ], 
		    activityTime: [ {  required: true, message: '活动时段', trigger: 'blur' } ]
			
		} ,
        pickerOptions1: {
            disabledDate (time) {
                return time.getTime() < (Date.now()-8.64e7);
            }
        },
	},
    created:function(){
        this.getAllUser();
        this.getAllTimes();
        this.getVenueinfo();
    },
	methods : {
		acceptClick: function() {
		  this.$refs
		  ["ruleForm"].validate(function(yes,b){
				if(yes){
					 /*$.SaveForm({
				    	url: '../../venuesbook/activities/save?_' + $.now(),
				    	param: vm.bActivities,
				    	success: function(data) {
				    		$.currentIframe().vm.load();
				    	}
				    });*/
					zs_postFormA(vm,{
                        url: '../../venuesbook/activities/save?_' + $.now(),
                        param: vm.bActivities,
                        success: function(data) {
                            vm.$message.success('添加成功');
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
                url:'../../venuesbook/dic/getDicsByCode?typeCode=activityType',
                success:function(r){
                    th.activityTypes=r.bDics;
                }
            })
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
        getVenueinfo:function(){
            var th=this;
            zs_post({
                url:'../../venuesbook/venueinfo/list?_' + $.now(),
                success:function(r){
                    th.venueinfos=r.rows;
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
                vm.bActivities.imgFile = file;

            });
            return true;
        }
	}
})
