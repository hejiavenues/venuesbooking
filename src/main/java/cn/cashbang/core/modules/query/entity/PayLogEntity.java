package cn.cashbang.core.modules.query.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 订单日志
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月30日 下午3:54:19
 */
public class PayLogEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String id;
	
	/**
	 * 支付渠道
	 */
	private String payChannel;
	
	/**
	 * 操作
	 */
	private String operation;
	
	/**
	 * 
	 */
	private String request;
	
	/**
	 * 
	 */
	private String response;
	
	/**
	 * 
	 */
	private String exception;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 
	 */
	private Integer version;
	

	public PayLogEntity() {
		super();
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	
	public String getPayChannel() {
		return payChannel;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setRequest(String request) {
		this.request = request;
	}
	
	public String getRequest() {
		return request;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	public String getResponse() {
		return response;
	}
	
	public void setException(String exception) {
		this.exception = exception;
	}
	
	public String getException() {
		return exception;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Integer getVersion() {
		return version;
	}
	
}
