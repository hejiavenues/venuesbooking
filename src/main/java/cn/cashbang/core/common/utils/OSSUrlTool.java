package cn.cashbang.core.common.utils;

import java.net.URL;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;

public class OSSUrlTool {

	private static final Logger logger = LoggerFactory.getLogger(OSSUrlTool.class);

	/*
	 * AccessKeySecret 表示签名所需的密钥 VERB表示HTTP 请求的Method，主要有PUT，GET，POST，HEAD，DELETE等
	 * \n 表示换行符 Content-MD5
	 * 表示请求内容数据的MD5值，对消息内容（不包括头部）计算MD5值获得128比特位数字，对该数字进行base64编码而得到。该请求头可用于消息合法性的检查（
	 * 消息内容是否与发送时一致），如”eB5eJF1ptWaXm4bijSPyxw==”，也可以为空。详情参看RFC2616 Content-MD5。
	 * Content-Type 表示请求内容的类型，如”application/octet-stream”，也可以为空 Date
	 * 表示此次操作的时间，且必须为GMT格式，如”Sun, 22 Nov 2015 08:16:38 GMT” CanonicalizedOSSHeaders
	 * 表示以 x-oss- 为前缀的http header的字典序排列 CanonicalizedResource 表示用户想要访问的OSS资源 Expires
	 * 这个参数的值是一个UNIX时间（自UTC时间1970年1月1号开始的秒数，详见Wikipedia），用于标识该URL的超时时间。
	 * 如果OSS接收到这个URL请求的时候晚于签名中包含的Expires参数时，则返回请求超时的错误码。例如：当前时间是1141889060，
	 * 开发者希望创建一个60秒后自动失效的URL，则可以设置Expires时间为1141889120。 OSSAccessKeyId
	 * 即密钥中的AccessKeyId。 Signature
	 * 表示签名信息。所有的OSS支持的请求和各种Header参数，在URL中进行签名的算法和在Header中包含签名的算法基本一样。
	 * 
	 * Signature = urlencode(base64(hmac-sha1(AccessKeySecret, VERB + "\n" +
	 * CONTENT-MD5 + "\n" + CONTENT-TYPE + "\n" + EXPIRES + "\n" +
	 * CanonicalizedOSSHeaders + CanonicalizedResource)))
	 */
	public static String UrlAddSign(String ossUrl) {

		int Expires_ADD = Integer.parseInt(SpringContextUtils.getApplicationProperties().getOss().get("Expires_ADD"))
				* 60 * 1000;// 超时时间1小时

		// 阿里云API的内或外网域名
		String ENDPOINT = SpringContextUtils.getApplicationProperties().getOss().get("ali_endpoint");

		// 是否内网读取
		String endpointRead = SpringContextUtils.getApplicationProperties().getOss().get("ali_endpoint_read");

		// "oss-cn-beijing.aliyuncs.com";
		// 云API的密钥Access Key ID
		String ACCESS_KEY_ID = SpringContextUtils.getApplicationProperties().getOss().get("ali_accessKeyId");

		// 阿里云API的密钥Access Key Secret
		String ACCESS_KEY_SECRET = SpringContextUtils.getApplicationProperties().getOss().get("ali_accessKeySecret");

		String BUCKETNAME = SpringContextUtils.getApplicationProperties().getOss().get("ali_bucketName");

		if (ossUrl == null) {
			return "";
		}

		if (!ossUrl.contains("aliyuncs.com/")) {
			return ossUrl;
		}
		String[] urlArr = ossUrl.split("aliyuncs.com/");

		if (urlArr.length < 2) {
			return ossUrl;
		}

		if(!ossUrl.contains(BUCKETNAME)) {
			return ossUrl;
		}
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建RAM账号。
		String objectName = urlArr[1];

		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

		// 设置URL过期时间为1小时。
		Date expiration = new Date(new Date().getTime() + Expires_ADD);
		// 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
		URL url = ossClient.generatePresignedUrl(BUCKETNAME, objectName, expiration);

		String returnUrl = url.toString().replace(ENDPOINT, endpointRead);
		// 关闭OSSClient。
		// ossClient.shutdown();
		logger.info("oss路径处理后:{}", returnUrl);
		return returnUrl;
	}

	public static String UrlAddSign_giveOther(String ossUrl) {

		int Expires_ADD = Integer.parseInt(SpringContextUtils.getApplicationProperties().getOss().get("Expires_ADD"))
				* 60 * 1000;// 超时时间1小时

		// 阿里云API的内或外网域名
		String ENDPOINT = SpringContextUtils.getApplicationProperties().getOss().get("ali_endpoint");

		// 是否内网读取
		String endpointRead = SpringContextUtils.getApplicationProperties().getOss().get("ali_endpoint_read");

		// "oss-cn-beijing.aliyuncs.com";
		// 云API的密钥Access Key ID
		String ACCESS_KEY_ID = SpringContextUtils.getApplicationProperties().getOss().get("ali_accessKeyId");

		// 阿里云API的密钥Access Key Secret
		String ACCESS_KEY_SECRET = SpringContextUtils.getApplicationProperties().getOss().get("ali_accessKeySecret");

		String BUCKETNAME = SpringContextUtils.getApplicationProperties().getOss().get("ali_bucketName");

		if (ossUrl == null) {
			return "";
		}

		if (!ossUrl.contains("aliyuncs.com/")) {
			return ossUrl;
		}
		String[] urlArr = ossUrl.split("aliyuncs.com/");

		if (urlArr.length < 2) {
			return ossUrl;
		}

		// Endpoint以杭州为例，其它Region请按实际情况填写。
		// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建RAM账号。
		String objectName = urlArr[1];

		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

		// 设置URL过期时间为1小时。
		Date expiration = new Date(new Date().getTime() + Expires_ADD);
		// 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
		URL url = ossClient.generatePresignedUrl(BUCKETNAME, objectName, expiration);

		String returnUrl = url.toString();
		// 关闭OSSClient。
		// ossClient.shutdown();
		logger.info("oss路径处理后:{}", returnUrl);
		return returnUrl;
	}
}
