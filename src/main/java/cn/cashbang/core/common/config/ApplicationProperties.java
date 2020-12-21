package cn.cashbang.core.common.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ApplicationProperties 项目自定义配置
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年3月19日
 */
@Component("ApplicationProperties")
@ConfigurationProperties(prefix="application-properties") //接收application.yml中的application-properties下面的属性  
public class ApplicationProperties {

	/**
	 * server url 自定义配置
	 * 
	 */
	private Map<String,String> serverUrl;
	
	/**
	 * cs 自定义配置
	 * 
	 */
	private Map<String,String> cs;
	
	
	
	/**
	 * oss
	 * 
	 */
	private Map<String,String> oss;
	
	
	
	/**
	 * oss
	 * 
	 */
	private Map<String,String> ossUrl;
	
	/**
	 * ins
	 * 
	 */
	private Map<String,String> ins;
	
	
	/**
	 * sys
	 * @return
	 */
	
	private Map<String,String> sys;

	/**
	 * sms
	 * @return
	 */
	private Map<String,String> sms;
	
	private Map<String,String> uploadInfo;
	
	private Map<String,String> mqInfo;


	public void setOssUrl(Map<String, String> ossUrl) {
		this.ossUrl = ossUrl;
	}


	public Map<String, String> getSms() {
		return sms;
	}


	public void setSms(Map<String, String> sms) {
		this.sms = sms;
	}


	public Map<String, String> getOss() {
		return oss;
	}


	public void setOss(Map<String, String> oss) {
		this.oss = oss;
	}


	public Map<String, String> getIns() {
		return ins;
	}


	public void setIns(Map<String, String> ins) {
		this.ins = ins;
	}


	public Map<String, String> getSys() {
		return sys;
	}


	public void setSys(Map<String, String> sys) {
		this.sys = sys;
	}


	public Map<String, String> getCs() {
		return cs;
	}


	public void setCs(Map<String, String> cs) {
		this.cs = cs;
	}


	public Map<String, String> getServerUrl() {
		return serverUrl;
	}


	public void setServerUrl(Map<String, String> serverUrl) {
		this.serverUrl = serverUrl;
	}
	

	public Map<String, String> getUploadInfo() {
		return uploadInfo;
	}


	public void setUploadInfo(Map<String, String> uploadInfo) {
		this.uploadInfo = uploadInfo;
	}

	public Map<String, String> getMqInfo() {
		return mqInfo;
	}


	public void setMqInfo(Map<String, String> mqInfo) {
		this.mqInfo = mqInfo;
	}


	public final String getServerUrlByKey(String key){
		
		String value= this.serverUrl.get(key);
		
		if(value!=null) {
			if(value.startsWith("bizServer/")) {
				String http=this.serverUrl.get("bizServer");
				if(http!=null) {
					value=value.replace("bizServer", http);
				}
			}
			if(value.startsWith("riskServer/")) {
				String http=this.serverUrl.get("riskServer");
				if(http!=null) {
					value=value.replace("riskServer", http);
				}
			}
			if(value.startsWith("scheduleServer/")) {
				String http=this.serverUrl.get("scheduleServer");
				if(http!=null) {
					value=value.replace("scheduleServer", http);
				}
			}

		}
		
		return value;
	}
	
public final String getCsByKey(String key){
		
		String value= this.cs.get(key);
		
		if(value!=null) {
			if(value.startsWith("bizServer/")) {
				String http=this.serverUrl.get("bizServer");
				if(http!=null) {
					value=value.replace("bizServer", http);
				}
			}
			if(value.startsWith("riskServer/")) {
				String http=this.serverUrl.get("riskServer");
				if(http!=null) {
					value=value.replace("riskServer", http);
				}
			}
			if(value.startsWith("scheduleServer/")) {
				String http=this.serverUrl.get("scheduleServer");
				if(http!=null) {
					value=value.replace("scheduleServer", http);
				}
			}

		}
		
		return value;
	}




/**
 * 匹配其他系统的ossUrl,规则:环境只有dev和online,除了dev就是online,ali_endpoint_read判断是否内网读取
 * @param key
 * @return
 */
public final String getOtherOssUrl(String key){
			
     	
        String endpoint=this.oss.get("ali_endpoint_read");
        String bucketName=this.oss.get("ali_bucketName");
        String sysevn=this.sys.get("evn").equals("dev")?"dev":"online";

		String httpUrl="http://"+bucketName+"."+endpoint;
	
	    String value= this.ossUrl.get(key);
		
		if(value!=null) {
			value=value.replace("SYS_EVN", sysevn);
		}
		
		return httpUrl +=value;
	}


public Map<String, String> getOssUrl() {
	return ossUrl;
}

	
}
