package cn.cashbang.core.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 *警报工具类
 * 
 */
public class ErrorToPeopleBySms {

	
	
	private static Logger logger = LoggerFactory.getLogger(ErrorToPeopleBySms.class);
	private static JSONObject types=new JSONObject(); ;
	private static String[]  peopes = SpringContextUtils.getApplicationProperties().getSms().get("SMSTOPEOPLES").split(",");
	private static String  EVN = SpringContextUtils.getApplicationProperties().getSys().get("evn");

	/**
	 * 设置该类型发送时间为当日
	 * @param type
	 */
	private static void setType(String type) {
		ErrorToPeopleBySms.types.put(type,new java.sql.Date(new java.util.Date().getTime()).toString());
	}
	
	private static boolean toTypeSms(String type) {
		boolean to=false;
		if(!ErrorToPeopleBySms.types.isEmpty()&&ErrorToPeopleBySms.types.containsKey(type)) {
			String typeTime=ErrorToPeopleBySms.types.getString(type);
			if(typeTime.equals(new java.sql.Date(new java.util.Date().getTime()).toString())) {
				to=false;
			}else {
				to=true;
			}
		}else {
			to=true;
		}
		return to;
	}

	
	
	
	
	
	public  static void smsToPeople(String type,String errMsg) {
		//logger.info("errToPeopleBySms 错误类型-时间    信息:{}",ErrorToPeopleBySms.types.toJSONString());
		errMsg=errMsg+",警告时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+".当前环境："+EVN+".";
		if(toTypeSms(type)) {
			boolean success=false;
			for(int i=0;i<ErrorToPeopleBySms.peopes.length;i++) {
				
				boolean successi=SendMsgUtil.sendMsg(ErrorToPeopleBySms.peopes[i], errMsg,"01",1,"");	
				if(successi) {
					success=true;
				}
				logger.error("{}类型警报发送至:{} , msg:{}, 结果:{}",type,ErrorToPeopleBySms.peopes[i],errMsg,success);
			}
			if(success) {
				setType(type);
			}
		}else {
			logger.error("{}类型警报今日已发送过,smstime:{}",type,ErrorToPeopleBySms.types.getString(type));
		}
		logger.info("errToPeopleBySms 错误类型-时间    信息:{}",ErrorToPeopleBySms.types.toJSONString());
	}
	
	
	
	
	
}
