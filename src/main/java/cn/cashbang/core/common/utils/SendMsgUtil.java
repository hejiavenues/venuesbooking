package cn.cashbang.core.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.cashbang.core.common.constant.SystemMsg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMsgUtil {
	private static final Logger logger = LoggerFactory.getLogger(SendMsgUtil.class);

	private static String  EVN = SpringContextUtils.getApplicationProperties().getSys().get("evn");
	/**
	 * 单条短信发送
	 * 手机号
	 * @param mobile
	 * @param content
	 * @return
	 */
	public static final boolean sendMsg(String mobile,String content,String platformcode,int type,String header){
		String result = "";
		try {
			String url = SpringContextUtils.getApplicationProperties().getSms().get("SMSSERVER");

			if(!"online".equals(EVN)) {
				content+=" [测试环境]";
			}
			logger.info("send sms-{} url-{} platformcode-{} type-{} header-{}", url, content,platformcode,type,header);
			Map<String, String> param=getMsgContent(mobile, content,platformcode,type,header);
			result = HttpPostUtil.sendUTFPostRequest(url,param);
			logger.info(result);
			JSONObject jsonObject = JSON.parseObject(result);
			int msgObj = jsonObject.getIntValue("code");
			if(msgObj==SystemMsg.smsSuccess){
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	/**
	 * 构造短信内容
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static final Map<String, String> getMsgContent(String mobile,String content,String platformcode,int type,String header){
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", mobile);
		params.put("content", content);
		params.put("platformcode", platformcode);
		/*if(type==1){
			params.put("appid", "qbjrfdyl");
		}else if(type==2){
			params.put("appid", "fdyl_hd");
		}else{
			params.put("appid", "cst_sv");
		}*/
		params.put("appid", SpringContextUtils.getApplicationProperties().getSms().get("APPID"));
		params.put("header", header);
		return params;
	}
	
}
