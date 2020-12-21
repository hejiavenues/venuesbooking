package cn.cashbang.core.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * http client utils
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年3月26日
 */
public class HttpClientUtils {

	protected static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	
	
	
	/**
	 * 
	 * do http post
	 * 
	 * @param params
	 * @return
	 */
	public static final String doPost(HttpPostParams params){
		
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			
			URI uri = createURI(params.getUrl());
			
			HttpPost httpPost = new HttpPost(uri);
			
			Object requestEntity = params.getRequestEntity();
			
			String requestEntityStr = JSON.toJSONString(requestEntity);
			
		    ByteArrayInputStream instream = null;
			try {
				instream = new ByteArrayInputStream(requestEntityStr.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.info(e.getMessage());
			}

			InputStreamEntity s = new InputStreamEntity(instream);
			
		 	s.setContentEncoding("UTF-8");
		 	s.setContentType("application/json");		//发送json数据需要设置contentType
		 	
		 	httpPost.setEntity(s);
		 	
		 	logger.info("发起HttpPost请求,uri="+uri+";requestEntity="+requestEntityStr);
			
			return excute(httpclient,httpPost);
		
	}
	
    
	public static final String doPostForm(String url,Map<String,String> params) throws UnsupportedEncodingException{
		
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		URI uri = createURI(url);
		
		HttpPost httpPost = new HttpPost(uri);
		
		
		httpPost.setHeader("Content-Type","application/json;charset=UTF-8");

		  //组织请求参数  
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();  
        
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();  
            for(String key : keySet) {  
                paramList.add(new BasicNameValuePair(key, params.get(key)));  
            }  
        }
	 	
        
	 	httpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
	 	
	 	logger.info("发起HttpPost请求,uri="+uri+";requestEntity="+paramList.toString());
		
	 	
	 	String res=excute(httpclient,httpPost);
	 	
	 	logger.info(res);
		return res;
	
}
	/**
	 * 
	 * do http get
	 * 
	 * @param params
	 * @return
	 */
	public static final String doGet(HttpGetParams params){
		
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		String url=params.getUrl();
		if(params.getUrl().contains("aliyuncs.com")) {
			url=OSSUrlTool.UrlAddSign(params.getUrl());
		}
		
		URI uri = createURI(concatParams(url, params.getRequestParams()));
		HttpGet httpGet = new HttpGet(uri);
		
		/*httpGet.setHeader("Accept","text/plain;charset=utf-8");*/
		logger.info("发起HttpGet请求，uri="+uri);
		
		return excute(httpclient,httpGet);
		
	}
	
	/**
	 * 
	 * do http get
	 * 
	 * @param params
	 * @return
	 */
	public static final String doGet1(String url){
		
		  String result = "";
		 try {
	            URL U = new URL(url);
	            URLConnection connection = U.openConnection();  
	            connection.connect();
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
	            result = "";
	            String line;
	            while ((line = in.readLine())!= null){  
	                result += line;  
	            }
	            in.close(); 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }  
		 
		 return result;
		
	}
	
	
	private static String excute(CloseableHttpClient httpclient,HttpUriRequest request){
		
		HttpResponse res = null;
		
		try {
			res = httpclient.execute(request);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
		 		HttpEntity entity = res.getEntity();
		 		String result = EntityUtils.toString(entity,"utf-8");
		 		if(result.length()<1000) {
		 			logger.info("Http请求："+request.getURI()+"返回结果，result="+result);
		 		}
		 		return result;
		 	}else{
		 		throw new RuntimeException("HttpClient get/post failed , HttpStatus:"+res.getStatusLine().getStatusCode());
		 	}
			
		} catch (Exception e) {
			throw new RuntimeException("HttpClient get/post failed , request:"+request,e);
		} finally {
			try {
				httpclient.close();			//close httpclient
			} catch (IOException e) {
				logger.info("close HttpClient failed",e);
			}
		}
		
	}
	
	
	private static URI createURI(String url){
		
		try {
			AssertUtils.hasLength(url, "url must not empty.");
			return new URIBuilder(url).build();
		} catch (URISyntaxException e) {
			throw new RuntimeException("create http URI failed:"+url,e);
		}
		
	}
	
	
	/**
	 * 
	 * get 请求拼接url参数
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String concatParams(String url,Map<String,String> params){
		
		if(params==null||params.isEmpty()){
			return url;
		}
		
		StringBuilder sb = new StringBuilder(url).append(StringUtils.QUESTION_MARK);
		
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String value = entry.getValue();
			if(value!=null&&value.contains(StringUtils.SPACE)){
				value = value.replace(StringUtils.SPACE, StringUtils.EMPTY);
			}
			if(value!=null&&value.contains(StringUtils.TABS)){
				value = value.replace(StringUtils.TABS, StringUtils.EMPTY);
			}
			if(value!=null&&value.contains(StringUtils.NEW_LINE)){
				value = value.replace(StringUtils.NEW_LINE, StringUtils.EMPTY);
			}
			
			sb.append(entry.getKey()).append(StringUtils.EQUALS).append(value).append(StringUtils.AND);
		}
		
		return sb.replace(sb.length()-1, sb.length(), StringUtils.EMPTY).toString();
	}
	
	
	/**
	 * 
	 * post params
	 * 
	 * @author Tiny
	 *
	 */
	public static class HttpPostParams{
		
		private String url;						//url
		private Object requestEntity;			//请求对象
		
		
		public String getUrl() {
			return url;
		}
		public Object getRequestEntity() {
			return requestEntity;
		}
		
		private HttpPostParams(String url, Object requestEntity) {
			super();
			this.url = url;
			this.requestEntity = requestEntity;
		}
		
	}
	
	/**
	 * 
	 * get params
	 * 
	 * @author Tiny
	 *
	 */
	public static class HttpGetParams{
		
		private String url;							//url
		private Map<String,String> requestParams;			//请求参数
		
		
		public String getUrl() {
			return url;
		}
		
		
		public Map<String, String> getRequestParams() {
			return requestParams;
		}

		public Map<String,String> putRequestParams(String key,String value){
			if(this.requestParams==null){
				this.requestParams = new LinkedHashMap<String, String>();
			}
			
			this.requestParams.put(key, value);
			
			return this.requestParams;
		}


		private HttpGetParams(String url, Map<String,String> requestParams) {
			super();
			this.url = url;
			this.requestParams = requestParams;
		}
		
	}
	
	public static HttpGetParams createHttpGetParams(String url){
		CloseableHttpClient httpclient = HttpClients.createDefault();

		/*if(url.contains("aliyuncs.com")) {
			url=OSSUrlTool.UrlAddSign(url);
		}logger.info("发起HttpGet请求，uri="+url);*/

		

		return new HttpGetParams(url, null);
	}
	
	public static HttpGetParams createHttpGetParams(String url,Map<String,String> params){
		return new HttpGetParams(url, params);
	}
	
	
	public static HttpPostParams createHttpPostParams(String url){
		return new HttpPostParams(url, null);
	}
	
	public static HttpPostParams createHttpPostParams(String url,Object requestEntity){
		return new HttpPostParams(url, requestEntity);
	}
	
	
	private HttpClientUtils(){}
}
