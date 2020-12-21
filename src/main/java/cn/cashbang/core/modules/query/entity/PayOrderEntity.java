package cn.cashbang.core.modules.query.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月30日 下午3:58:04
 */
public class PayOrderEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private String payOrderId;
	
	/**
	 * 交易单
	 */
	private String tradeOrderId;
	
	/**
	 * 
	 */
	private BigDecimal money;
	
	/**
	 * 账户名
	 */
	private String accountName;
	
	/**
	 * 银行卡
	 */
	private String bankCard;
	
	/**
	 * 身份证
	 */
	private String identityCard;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 支付渠道
	 */
	private String payChannel;
	
	/**
	 * 支付类型
	 */
	private String payType;
	
	/**
	 * 支付单状态
	 */
	private String payOrderStatus;
	
	/**
	 * 
	 */
	private Date createTime;
	
	/**
	 * 
	 */
	private Date updateTime;
	
	/**
	 * 
	 */
	private String productCode;
	
	/**
	 * 业务渠道码
	 */
	private String businessChannelCode;
	
	/**
	 * 批次单
	 */
	private String batchId;
	
	/**
	 * 验证码
	 */
	private String confirmCode;
	
	/**
	 * 
	 */
	private String remark;
	
	/**
	 * 通知交易状态位
	 */
	private Integer notifyTrade;
	
	/**
	 * 
	 */
	private Integer version;
	
	/**
	 * 
	 */
	private Integer retryCount;
	
	/**
	 * 
	 */
	private String errorCode;
	
	/**
	 * 
	 */
	private String errorMessage;
	
	/**
	 * 
	 */
	private String userId;
	
	/**
	 * 
	 */
	private String outerPayOrderId;
	

	public PayOrderEntity() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}
	
	public String getPayOrderId() {
		return payOrderId;
	}
	
	public void setTradeOrderId(String tradeOrderId) {
		this.tradeOrderId = tradeOrderId;
	}
	
	public String getTradeOrderId() {
		return tradeOrderId;
	}
	
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public BigDecimal getMoney() {
		return money;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	public String getBankCard() {
		return bankCard;
	}
	
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	
	public String getIdentityCard() {
		return identityCard;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	
	public String getPayChannel() {
		return payChannel;
	}
	
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public String getPayType() {
		return payType;
	}
	
	public void setPayOrderStatus(String payOrderStatus) {
		this.payOrderStatus = payOrderStatus;
	}
	
	public String getPayOrderStatus() {
		return payOrderStatus;
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
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductCode() {
		return productCode;
	}
	
	public void setBusinessChannelCode(String businessChannelCode) {
		this.businessChannelCode = businessChannelCode;
	}
	
	public String getBusinessChannelCode() {
		return businessChannelCode;
	}
	
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	public String getBatchId() {
		return batchId;
	}
	
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}
	
	public String getConfirmCode() {
		return confirmCode;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setNotifyTrade(Integer notifyTrade) {
		this.notifyTrade = notifyTrade;
	}
	
	public Integer getNotifyTrade() {
		return notifyTrade;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}
	
	public Integer getRetryCount() {
		return retryCount;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setOuterPayOrderId(String outerPayOrderId) {
		this.outerPayOrderId = outerPayOrderId;
	}
	
	public String getOuterPayOrderId() {
		return outerPayOrderId;
	}
	
}
