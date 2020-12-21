package cn.cashbang.core.modules.query.bo;

import java.util.Date;

/**
 * 查询服务调用返回客户申请单交易流水
 * 
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年3月23日
 */
public class OrderDetailQueryBO {
	
	private Date createTime;
	private Date updateTime;
	private String version;
	private String applyId;				//申请单号
	private String paymentId;			
	private String tradeType;			
	private String tradeTypeText;			
	private String orderStatus;			
	private String orderStatusText;			
	private String balance;			
	private String periods;			
	private String errorCode;			
	private String errorMsg;			
	private String tradeDate;			
	private String productCode;			
	private String editUser;
	private String isLocked;  //是否锁单  0 否  1锁单
	private String id;			//交易单id
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getPeriods() {
		return periods;
	}
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getEditUser() {
		return editUser;
	}
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	public String getTradeTypeText() {
		return tradeTypeText;
	}
	public void setTradeTypeText(String tradeTypeText) {
		this.tradeTypeText = tradeTypeText;
	}
	public String getOrderStatusText() {
		return orderStatusText;
	}
	public void setOrderStatusText(String orderStatusText) {
		this.orderStatusText = orderStatusText;
	}
	public String getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}			
	
	
	
}
