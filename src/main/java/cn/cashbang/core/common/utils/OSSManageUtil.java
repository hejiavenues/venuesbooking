package cn.cashbang.core.common.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import cn.cashbang.core.common.entity.OssEntity;
import net.coobird.thumbnailator.Thumbnails;


public class OSSManageUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(OSSManageUtil.class);

	
	
	private static  String EVN =SpringContextUtils.getApplicationProperties().getSys().get("evn");
	
	private static String BUCKETNAME = SpringContextUtils.getApplicationProperties().getOss().get("ali_bucketName");

	
	//阿里云API的内或外网域名
	private static String ENDPOINT = SpringContextUtils.getApplicationProperties().getOss().get("ali_endpoint");
	//"oss-cn-beijing.aliyuncs.com";
	//阿里云API的密钥Access Key ID
	private static String ACCESS_KEY_ID = SpringContextUtils.getApplicationProperties().getOss().get("ali_accessKeyId");
	
	//阿里云API的密钥Access Key Secret
    private static String ACCESS_KEY_SECRET =SpringContextUtils.getApplicationProperties().getOss().get("ali_accessKeySecret");
	
	
	//文本
	public static String DISKIMAGENAME = EVN +  SpringContextUtils.getApplicationProperties().getOss().get("managerImg_DISKIMAGENAME");
	
	
	/**
	 * 返回路径
	 */
	public static String REURL="https://"+BUCKETNAME+"."+ENDPOINT+"/"+DISKIMAGENAME;
	
	private static OSSClient instance;
	
	public static final OSSClient getOSSClient(){
		if(instance == null){
			instance = createOSSClient();
		}
		return instance;
	}
	/**
	 * 获取阿里云OSS客户端对象
	 * */
	public static final OSSClient createOSSClient(){
		return new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);
	}
	
	/**
	 * 新建Bucket  --Bucket权限:私有
	 * @param bucketName bucket名称
	 * @return true 新建Bucket成功
	 * */
	public static final boolean createBucket(OSSClient client, String bucketName){
		Bucket bucket = client.createBucket(bucketName); 
		return bucketName.equals(bucket.getName());
	}
	
	
	/**
	 * 文件是否存在
	 * @param ossUrl
	 * @return
	 */
	public static final boolean  OssFileIsExists( String ossUrl) {
		
		if(!ossUrl.contains(BUCKETNAME)) {
			logger.info("该ossUrl不为系统设置根目录自动判断为存在:{}",ossUrl);
			return true;
		}
		
		if(!ossUrl.contains("aliyuncs.com/")) {
			return false;
		}
		
		String[] urls=ossUrl.split("aliyuncs.com/");
		if(urls.length<2) {
			return false;
		}
		String fileUrl=urls[1];
		
		OSSClient client=OSSManageUtil.getOSSClient();
		try {
			boolean exists=client.doesObjectExist(BUCKETNAME,fileUrl);
			logger.info("查询oss文件是否存在{},{}+++++++++++++{}",exists,fileUrl,ossUrl);
		     return exists;
		}catch (Exception e) {
			logger.info("查询文件是否存在错误：{}",e);
		}/*finally {
			client.shutdown();
		}*/
		
		return false;
		
	}
	public static final String  uploadimageOSS(OSSClient client, OssEntity oss) {
		/*String resultStr = null;*/
		try {
			InputStream is = oss.getOssInputStream();
			String fileName = oss.getFilename();
			int fileSize = oss.getOssInputStream().available();
			//创建上传Object的Metadata
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(is.available());
			metadata.setCacheControl("no-cache");
			metadata.setHeader("Pragma", "no-cache");
			metadata.setContentEncoding("GBK");
			metadata.setContentType(getContentType(fileName));
			metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
			//上传文件 
			client.putObject(BUCKETNAME, DISKIMAGENAME + fileName, is, metadata);
			return REURL+fileName;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			logger.info("+++++++++++==={}",e);
			return "";
		}
	}
	
    /** 
     * 根据key获取OSS服务器上的文件输入流
 	 * @param client OSS客户端
 	 * @param bucketName bucket名称
 	 * @param diskName 文件路径
 	 * @param key Bucket下的文件的路径名+文件名
     */  
     public static final InputStream getOSS2InputStream(OSSClient client, String bucketName, String diskName, String key){ 
		OSSObject ossObj = client.getObject(bucketName, diskName + key);
		return ossObj.getObjectContent();	
     }  
     
    
   /** 
    * 根据key删除OSS服务器上的文件 
  	* @param client OSS客户端
  	* @param bucketName bucket名称
  	* @param diskName 文件路径
  	* @param key Bucket下的文件的路径名+文件名
    */  
	  public static void deleteFile(OSSClient client, String bucketName, String diskName, String key){  
	  	client.deleteObject(bucketName, EVN + diskName + key); 
	  	logger.info("删除" + bucketName + "下的文件" + diskName + key + "成功");
	  }  
     
    /** 
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType 
     * @param fileName 文件名
     * @return 文件的contentType   
     */  
     public static final String getContentType(String fileName){  
    	String fileExtension = fileName.substring(fileName.lastIndexOf("."));
    	fileExtension = fileExtension.substring(1,fileExtension.length());
    	if("bmp".equalsIgnoreCase(fileExtension)) return "image/bmp";
    	if("gif".equalsIgnoreCase(fileExtension)) return "image/gif";
    	if("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)  || "png".equalsIgnoreCase(fileExtension) ) return "image/jpeg";
    	if("html".equalsIgnoreCase(fileExtension)) return "text/html";
    	if("txt".equalsIgnoreCase(fileExtension)) return "text/plain";
    	if("vsd".equalsIgnoreCase(fileExtension)) return "application/vnd.visio";
    	if("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) return "application/vnd.ms-powerpoint";
    	if("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) return "application/msword";
    	if("xml".equalsIgnoreCase(fileExtension)) return "text/xml";
        return "text/html";  
     }  
     
     
     
     /**
      * 图片压缩
      * @param data
      * @return
      * @throws Exception
      */
     public static byte[] compress(byte[] data) throws Exception{
 		if(data==null||data.length<300000){
 			return data;
 		}
 		logger.info("压缩图片size:{}",data.length);
 		InputStream big = null;
 		byte[] newdata = null;
 		big = new ByteArrayInputStream(data);
 		BufferedImage bi;
 		bi = ImageIO.read(big);
 		ByteArrayOutputStream out = new ByteArrayOutputStream();
 		Thumbnails.of(bi).size(1280*2, 1024*2).outputFormat("jpg")
 				.toOutputStream(out);
 		newdata = out.toByteArray();
 		logger.info("压缩后图片size:{}",newdata.length);
 		return newdata;
 	}
     
     
     
     /**
      * 下载
      * @param key
      * @param fileName
      */
     public static final void downloadFile(String key,String fileName) {
    	 OSSManageUtil.getOSSClient().getObject(new GetObjectRequest(BUCKETNAME, DISKIMAGENAME+key), 
				 new File(fileName));
     }
     
     /**
      * 下载
      * @param key
      * @param fileName
      */
     public static final void  downloadByBrowser( HttpServletResponse response,String fileName) {
    	 OSSClient	 ossClient= OSSManageUtil.getOSSClient();
    	 OSSObject ossObj = ossClient.getObject(BUCKETNAME, DISKIMAGENAME + fileName);
    	 BufferedInputStream in= new BufferedInputStream(ossObj.getObjectContent());
	       //fileName = URLEncoder.encode(fileName, "UTF-8");  
	       response.reset();  
	       response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	      // response.addHeader("Content-Length", "" + bytes.length);  
	       response.setContentType("application/octet-stream;charset=UTF-8"); 
	       OutputStream out;
	       
    	  byte[] car=new byte[1024];  
         
         try {
        	    out = new BufferedOutputStream(response.getOutputStream());
        		logger.info("{}",in.available()+"");
        		 int L=0;  
                 while((L=in.read(car))!=-1){  
                     out.write(car, 0,L);  
                 }  
                 if(out!=null){  
                     out.flush();  
                     out.close();  
                 }
                 if(in!=null){  
                     in.close();  
                 }  
                 ossClient.shutdown();  
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("导出保险失败：{}",fileName);
		}finally {
			 
		}
     }
     
     
     public static void main(String[] args) {
    	/* InputStream stream = OSSManageUtil.getOSS2InputStream(OSSManageUtil.getOSSClient(), BUCKETNAME,DISKNAME, "rongreport_18311400069.txt");
    	 try {
			System.out.println(CommonUtil.inputStream2String(stream,"utf-8"));
		 } catch (IOException e) {
			e.printStackTrace();
		 }*/
	}
}
