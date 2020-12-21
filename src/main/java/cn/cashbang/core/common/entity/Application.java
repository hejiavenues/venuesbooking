package cn.cashbang.core.common.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 外部服务调用请求/返回对象
 * 
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 上午11:41:07
 */
public class Application {
	
	private String code;
	private String msg;
	private Object data;
	private Message message;
	private boolean index;
	
	public boolean isIndex() {
		return index;
	}


	public void setIndex(boolean index) {
		this.index = index;
	}




	private String serviceCode;

	
	@SuppressWarnings("all")
	public Application putParams(String key, Object value){
		
		if(this.data==null){
			this.data = new LinkedHashMap<String, Object>();
		}
		
		if(this.data instanceof Map){
			((Map) this.data).put(key, value);
		}
		
		return this;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	
	public boolean isSuccess(){
		return "1000000000".equals(this.code)
				||(this.message!=null&&"000000".equals(this.message.status));
	}
	
	
	public Message getMessage() {
		return message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}




	public static class Message{
		
		private String status;		
		private String desc;
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		
	}
}
