
      /*weiminghai
                三个参数
        file：一个是文件(类型是图片格式)，
        w：一个是文件压缩的后宽度比例，宽度越小，字节越小 ，默认0.2
        size：压缩界限，超过size就压缩 默认500k
        resf:输出base64和file
         */

        function  getSmallerFile(file,w,size,resf){
            console.log("压缩图片1")
           var allowTypes = ['image/jpg', 'image/jpeg', 'image/png'];//可处理格式

             if (allowTypes.indexOf(file.type) === -1) {
            	 
            	var ready=new FileReader();
 	            ready.readAsDataURL(file);
 	            ready.onload=function(){
 	                var re=this.result;
 	                resf&&resf(re,file);
 	            }
 	            
            }else{
            	
            	var type=file.type;
            	var sizes=10*10;
            	var ws= {
                        quality: 0.2
                	};
            	if(w!=undefined){
            		ws.quality=w;
            	}
            	if(size!=undefined){
            		sizes=size*10;
            	}
            	if(file.size>sizes){
            	    console.log("压缩图片2")
            		photoCompress(file,ws,function(base64){
                		file=convertBase64UrlToBlob(base64);
            			resf&&resf(base64,file);
                	});
            	}else{
            		var ready=new FileReader();
		            ready.readAsDataURL(file);
		            ready.onload=function(){
		                var re=this.result;
		                resf&&resf(re,file);
		            }
            	}
            }
        }
        /*图片压缩
         * 
         * 开始读取指定的Blob对象或File对象中的内容. 当读取操作完成时,readyState属性的值会成为DONE,
        如果设置了onloadend事件处理程序,则调用之.同时,result属性中将包含一个data: URL格式的字符串以表示所读取文件的内容.*/
        function photoCompress(file,w,callback){
            var ready=new FileReader();
         
            ready.readAsDataURL(file);
            ready.onload=function(){
                var re=this.result;
                canvasDataURL(re,w,callback)
            }
        }
        
        function canvasDataURL(path, obj, callback){
            var img = new Image();
            img.src = path;
            img.onload = function(){
                var that = this;
                // 默认按比例压缩
                var w = that.width,
                    h = that.height,
                    scale = w / h;
                w = obj.width || w;
                h = obj.height || (w / scale);
                var quality = 0.2;  // 默认图片质量为1
                //生成canvas
                var canvas = document.createElement('canvas');
                var ctx = canvas.getContext('2d');
                // 创建属性节点
                var anw = document.createAttribute("width");
                anw.nodeValue = w;
                var anh = document.createAttribute("height");
                anh.nodeValue = h;
                canvas.setAttributeNode(anw);
                canvas.setAttributeNode(anh);
                ctx.drawImage(that, 0, 0, w, h);
                // 图像质量
                if(obj.quality && obj.quality <= 1 && obj.quality > 0){
                    quality = obj.quality;
                }
                // quality值越小，所绘制出的图像越模糊
                var base64 = canvas.toDataURL('image/jpeg', quality);
                // 回调函数返回base64的值
                callback(base64);
            }
        }
        /**
         * 将以base64的图片url数据转换为Blob
         * @param urlData
         *            用url方式表示的base64图片数据
         */
        function convertBase64UrlToBlob(urlData){
            var arr = urlData.split(','), mime = arr[0].match(/:(.*?);/)[1],
                bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
            while(n--){
                u8arr[n] = bstr.charCodeAt(n);
            }
            return new Blob([u8arr], {type:mime});
        }


       