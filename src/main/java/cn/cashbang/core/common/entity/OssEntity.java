package cn.cashbang.core.common.entity;

import java.io.InputStream;

public class OssEntity {

	private InputStream ossInputStream;	//
	private String filename;			//文件名
	private String ossUrl;			//oss返回url
	private String contentType;
	
	
	
	
	public static final OssEntity getInstance(InputStream ossInputStream,String filename){
		
		OssEntity ossEntity = new OssEntity();
		ossEntity.setOssInputStream(ossInputStream);
		ossEntity.setFilename(filename);
		
		return ossEntity;
		
	}
	public static final OssEntity getInstance(InputStream ossInputStream,String filename,String contentType ){
		
		OssEntity ossEntity = new OssEntity();
		ossEntity.setOssInputStream(ossInputStream);
		ossEntity.setFilename(filename);
		ossEntity.setContentType(contentType);
		return ossEntity;
		
	}
	

	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getOssUrl() {
		return ossUrl;
	}

	public void setOssUrl(String ossUrl) {
		this.ossUrl = ossUrl;
	}

	public InputStream getOssInputStream() {
		return ossInputStream;
	}

	public void setOssInputStream(InputStream ossInputStream) {
		this.ossInputStream = ossInputStream;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private OssEntity() {}
	
	
	
}
