package cn.cashbang.core.modules.query.bo;

/**
 * 查询服务调用返回data结构
 * 
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 上午11:41:07
 */
public class CustQueryResultBO {
	
	private String version;
	private String id;
	private String serviceCode;
	private String cacheId;
	private String succ;
	private String outputResult;		//返回结果  String 字符串   Json 格式
	private String outputResultPath;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getCacheId() {
		return cacheId;
	}
	public void setCacheId(String cacheId) {
		this.cacheId = cacheId;
	}
	public String getSucc() {
		return succ;
	}
	public void setSucc(String succ) {
		this.succ = succ;
	}
	public String getOutputResult() {
		return outputResult;
	}
	public void setOutputResult(String outputResult) {
		this.outputResult = outputResult;
	}
	public String getOutputResultPath() {
		return outputResultPath;
	}
	public void setOutputResultPath(String outputResultPath) {
		this.outputResultPath = outputResultPath;
	}
	
}
