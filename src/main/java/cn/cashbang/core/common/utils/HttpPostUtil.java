package cn.cashbang.core.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * 网络请求
 * @author QZJ
 *
 */
public class HttpPostUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpPostUtil.class);

	// 第一步，创建HttpPost对象
	public static String sendPostRequest(String path, Map<String, String> params)
			throws Exception {
		
        String postString=getChangeResult(params);
		byte[] entitydata = postString.getBytes("utf-8");
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",
				String.valueOf(entitydata.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		// 把内存中的数据刷新输送给对方
		outStream.flush();
		outStream.close();
		String resultString = "";
		if (conn.getResponseCode() == 200) {
			InputStream inptStream = conn.getInputStream();
			resultString = dealResponseResult(inptStream);

		}
		return resultString;
	}
	
	public static String sendJSONPostRequest(String path, Map<String, Object> params)
			throws Exception {
		
        String postString=getObjectChangeResult(params);
		byte[] entitydata = postString.getBytes("utf-8");
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type",
				"application/json");
		conn.setRequestProperty("Content-Length",
				String.valueOf(entitydata.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		// 把内存中的数据刷新输送给对方
		outStream.flush();
		outStream.close();
		String resultString = "";
		if (conn.getResponseCode() == 200) {
			InputStream inptStream = conn.getInputStream();
			resultString = dealResponseResult(inptStream);

		}
		return resultString;
	}
	
	public static String sendh5PostRequest(String path, Map<String, String> params)
			throws Exception {
		
        String postString=getChangeResult(params);
		byte[] entitydata = postString.getBytes("utf-8");
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);
		conn.setRequestProperty("Charset", "UTF-8"); 
		conn.setRequestProperty("Authorization", "ZHhIogwBELnFu5ukZNyVCQ==:6lJJDflAFuC5B3b4hHN7Gg==");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",
				String.valueOf(entitydata.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		// 把内存中的数据刷新输送给对方
		outStream.flush();
		outStream.close();
		String resultString = "";
		if (conn.getResponseCode() == 200) {
			InputStream inptStream = conn.getInputStream();
			resultString = dealResponseResult(inptStream);

		}
		return resultString;
	}

	public static String sendPostRequest(String path, String params)
			throws Exception {
		return sendPostRequest(path,params,null);
	}

	public static String sendPostRequest2(String path, String params)
			throws Exception {
		return sendPostRequest2(path,params,null);
	}
	// 第一步，创建HttpPost对象
	public static String sendPostRequest(String path, String params, Map<String, String> header)
			throws Exception {
        String postString=params;
		
		byte[] entitydata = postString.getBytes("UTF-8");//GBK
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);
//		conn.setRequestProperty("Content-Type",
//				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Type",
				"application/json");
		conn.setRequestProperty("Content-Length",
				String.valueOf(entitydata.length));

		if (header != null) {
			Iterator iterator = header.entrySet().iterator();

			while (iterator.hasNext()) {

				Map.Entry entry = (Map.Entry) iterator.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				conn.setRequestProperty((String) key, (String) val);
			}
		}

		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		// 把内存中的数据刷新输送给对方
		outStream.flush();
		outStream.close();
		String resultString = "";
		if (conn.getResponseCode() == 200) {
			InputStream inptStream = conn.getInputStream();
			resultString = dealResponseResult(inptStream);
		}

		return resultString;
	}

	// 第一步，创建HttpPost对象
	public static String sendPostRequest2(String path, String params, Map<String, String> header)
			throws Exception {
		String postString=params;

		byte[] entitydata = postString.getBytes("UTF-8");//GBK
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
//		conn.setRequestProperty("Content-Type",
//				"application/json");
		conn.setRequestProperty("Content-Length",
				String.valueOf(entitydata.length));

		if (header != null) {
			Iterator iterator = header.entrySet().iterator();

			while (iterator.hasNext()) {

				Map.Entry entry = (Map.Entry) iterator.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				conn.setRequestProperty((String) key, (String) val);
			}
		}

		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		// 把内存中的数据刷新输送给对方
		outStream.flush();
		outStream.close();
		String resultString = "";
		if (conn.getResponseCode() == 200) {
			InputStream inptStream = conn.getInputStream();
			resultString = dealResponseResult(inptStream);
		}

		return resultString;
	}

	// 第一步，创建HttpPost对象
	public static String sendUTFPostRequest(String path, Map<String, String> params)
			throws Exception {
		
        String postString=getChangeResult(params);
		logger.info("sms url{}-{}",path, postString);
		byte[] entitydata = postString.getBytes("UTF-8");
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",
				String.valueOf(entitydata.length));
		
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		// 把内存中的数据刷新输送给对方
		outStream.flush();
		outStream.close();
		String resultString = "";
		if (conn.getResponseCode() == 200) {
			InputStream inptStream = conn.getInputStream();
			resultString = dealResponseResult(inptStream);

		}
		return resultString;
	}

	public static String dealResponseResult(InputStream inputStream) {
		String resultData = null; // 存储处理结果
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(data)) != -1) {
				byteArrayOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			resultData = new String(byteArrayOutputStream.toByteArray(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return resultData;
	}

	private static String getChangeResult(Map<String, String> params)
	{
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {

			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(entry.getKey()).append('=').append(entry.getValue())
						.append('&');
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		
        return  sb.toString();
	}
	
	private static String getObjectChangeResult(Map<String, Object> params)
	{
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {

			for (Map.Entry<String, Object> entry : params.entrySet()) {
				sb.append(entry.getKey()).append('=').append(entry.getValue())
						.append('&');
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		
        return  sb.toString();
	}

	public static String doPost(String path, String params)
			throws Exception {
        String postString=params;
		
		byte[] entitydata = postString.getBytes("UTF-8");
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(10 * 1000);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type",	"application/json");
		conn.setRequestProperty("Content-Length",
				String.valueOf(entitydata.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		// 把内存中的数据刷新输送给对方
		outStream.flush();
		outStream.close();
		String resultString = "";

		//System.out.println("code:"+conn.getResponseCode());
		if (conn.getResponseCode() == 200) {
			InputStream inptStream = conn.getInputStream();
			resultString = dealResponseResult(inptStream);
		}else{
			logger.error("Post Response Code={}",conn.getResponseCode());
		}
		return resultString;
	}
	
	public static String doGet(String path, String params)
			throws Exception {
        String postString=params;
		
		byte[] entitydata = postString.getBytes("UTF-8");
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(10 * 1000);
		conn.setDoOutput(false);
		
		String resultString = "";
		//System.out.println("code:"+conn.getResponseCode());
		if (conn.getResponseCode() == 200) {
			InputStream inptStream = conn.getInputStream();
			resultString = dealResponseResult(inptStream);

		}
		return resultString;
	}

	/*@SuppressWarnings("rawtypes")
	public static String formUpload(String urlStr, Map<String, String> textMap,
			Map<String, String> fileMap) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "-----------------12345654321-----------"; 
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			conn.setRequestProperty("Charset", "UTF-8"); 
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					
					strBuf.append(inputName);
					strBuf.append("=");
					strBuf.append(inputValue);
					strBuf.append("&");
				}
				out.write(strBuf.toString().substring(0, strBuf.toString().length()-1).getBytes());
			}
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:application/octet-stream\r\n\r\n");
					out.write(strBuf.toString().getBytes());
					DataInputStream in = new DataInputStream(
							new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			//byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			//out.write(endData);
			out.flush();
			out.close();
			int responseCode = conn.getResponseCode();
		    if (responseCode==200) {
		    	// 读取返回数据
				StringBuffer strBuf = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				   
				String line = null;
				while ((line = reader.readLine()) != null) {
					strBuf.append(line).append("\n");
				}
				res = strBuf.toString();
				reader.close();
				reader = null;
			}else{
				StringBuffer error = new StringBuffer();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
						conn.getErrorStream()));
				String line1 = null;
				while ((line1=bufferedReader.readLine())!=null) {
				    error.append(line1).append("\n");
				}
				res=error.toString();
				bufferedReader.close();
				bufferedReader=null;
			}
				
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}*/
	
	@SuppressWarnings("rawtypes")
	public static String formUpload(String urlStr, Map<String, String> textMap,
			Map<String, String> fileMap) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "-----------------12345654321-----------"; 
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			conn.setRequestProperty("Charset", "UTF-8"); 
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes("UTF-8"));
			}
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:application/octet-stream\r\n\r\n");
					out.write(strBuf.toString().getBytes());
					DataInputStream in = new DataInputStream(
							new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			int responseCode = conn.getResponseCode();
		    if (responseCode==200) {
		    	// 读取返回数据
				StringBuffer strBuf = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						conn.getInputStream(),"UTF-8"));
				   
				String line = null;
				while ((line = reader.readLine()) != null) {
					strBuf.append(line).append("\n");
				}
				res = strBuf.toString();
				reader.close();
				reader = null;
			}else{
				StringBuffer error = new StringBuffer();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
						conn.getErrorStream(),"UTF-8"));
				String line1 = null;
				while ((line1=bufferedReader.readLine())!=null) {
				    error.append(line1).append("\n");
				}
				res=error.toString();
				bufferedReader.close();
				bufferedReader=null;
			}
				
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}
}