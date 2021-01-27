package cn.cashbang.core.common.utils;

import cn.cashbang.core.modules.venuesbook.entity.BAccessTokenEntity;
import cn.cashbang.core.modules.venuesbook.manager.BAccessTokenManager;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * web工具类
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月13日 下午3:18:22
 */
public class WebUtils {

    @Autowired
    private BAccessTokenManager bAccessTokenManager;
	
	/**
	 * 是否为ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
		String header = "x-requested-with", httpRequest = "XMLHttpRequest";
		//如果是ajax请求响应头会有，x-requested-with
		 if (request.getHeader(header) != null
				 && request.getHeader(header)
				 .equalsIgnoreCase(httpRequest)) {
			 return true;
		 }
		 return false;
	}
	
	/**
	 * 页面输出
	 * @param response
	 * @param o
	 */
	public static void write(HttpServletResponse response,Object o){
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println(o.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public static String getAccessToken(){

        // 金顶街的小程序
        String appId="wx2d9d1a4c8beb93ab";
        String appSecret="499110e6ad602f46f89f689cfd3d0087";

        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + appId + "&secret=" + appSecret;
        //HttpClientUtils.HttpPostParams httpPostParams = HttpClientUtils.createHttpPostParams(requestUrl);
        String res = HttpClientUtils.doGet1(requestUrl);

        JSONObject jsonObject = JSONObject.parseObject(res);
        Object resO = jsonObject.get("access_token");
        String token = "";
        if(resO!=null) {
            token = resO.toString();
            //new WebUtils().saveToken(token);
        }

        return token;
    }

    public void saveToken(String token){

        if(StringUtils.isNotBlank(token)){

            BAccessTokenEntity bAccessToken = new BAccessTokenEntity();
            bAccessToken.setId(1);
            bAccessToken.setAccessToken(token);
            bAccessTokenManager.updateBAccessToken(bAccessToken);
        }
    }

    public static String msgCheck(String accessToken,String content){

        String errcode = "";

        try {
            // 金顶街的小程序
            String appId="wx2d9d1a4c8beb93ab";
            String appSecret="499110e6ad602f46f89f689cfd3d0087";

            String requestUrl = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token="+ accessToken;
            //  + accessToken + "&content=" + content;
            //HttpClientUtils.HttpPostParams httpPostParams = HttpClientUtils.createHttpPostParams(requestUrl);
            Map<String,Object> params = new HashMap<>();
           // params.put("access_token",accessToken);
            params.put("content",content);

            String res = HttpPostUtil.doPost(requestUrl,JSONObject.toJSONString(params));
            //String res = HttpClientUtils.doPostForm(requestUrl,params);

            JSONObject jsonObject = JSONObject.parseObject(res);
            Object resO = jsonObject.get("errcode");

            if(resO!=null) {
                errcode = resO.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return errcode;
    }
}
