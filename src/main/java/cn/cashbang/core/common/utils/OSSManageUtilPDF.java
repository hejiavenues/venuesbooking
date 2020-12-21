package cn.cashbang.core.common.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import cn.cashbang.core.common.entity.OssEntity;
import net.coobird.thumbnailator.Thumbnails;


/**
 * 公司临时签章用
 * @author weimi
 *
 */
public class OSSManageUtilPDF {
	
	
	private static final Logger logger = LoggerFactory.getLogger(OSSManageUtilPDF.class);
	
	/**
	 * 公司用都存到测试上
	 */
	private static  String EVN =SpringContextUtils.getApplicationProperties().getSys().get("evn");;
	private static String BUCKETNAME =SpringContextUtils.getApplicationProperties().getOss().get("ali_bucketName");;
	//阿里云API的内或外网域名
	private static String ENDPOINT = SpringContextUtils.getApplicationProperties().getOss().get("ali_endpoint");
	//"oss-cn-beijing.aliyuncs.com";
	//阿里云API的密钥Access Key ID
	private static String ACCESS_KEY_ID = SpringContextUtils.getApplicationProperties().getOss().get("ali_accessKeyId");
	//阿里云API的密钥Access Key Secret
	private static String ACCESS_KEY_SECRET =SpringContextUtils.getApplicationProperties().getOss().get("ali_accessKeySecret");
	
	
	//文本
	public static String DISKIMAGENAME = EVN +  SpringContextUtils.getApplicationProperties().getOss().get("managerPDF_DISKIMAGENAME");
	
	public static String FILEPATH = "http://"+BUCKETNAME+"."+ENDPOINT+"/";
	
	
	
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
	
	

	public static final String  uploadPdfOSS(OSSClient client, OssEntity oss) {
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
//			LOG.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
			logger.error(e.getMessage(),e);
			return "";
		}
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
     
     
     public static void main(String[] args) {
	}
}
