package cn.cashbang.core.modules.query.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 交易记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月27日 上午11:13:23
 */
public class TradeStatusRecordEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 物理主键
	 */
	private Long id;
	
	/**
	 * 物理主键(避免精度丟失)
	 */
	private String  idStr;
	/**
	 * 支付流水号
	 */
	private String paymentId;
	
	public String getIdStr() {
		return idStr;
	}

	/**
	 * 账务流水
	 */
	private Long loanId;
	
	/**
	 * 进件流水号
	 */
	private String applyId;
	
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
	private Integer version;
	
	/**
	 * 
	 */
	private Long identityId;
	
	/**
	 * 
	 */
	private Long userId;
	
	/**
	 * 交易类型（1：支付，2：还款）
	 */
	private Integer tradeType;
	
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	
	/**
	 * 交易金额
	 */
	private BigDecimal balance;
	
	/**
	 * 期号
	 */
	private Integer periods;
	
	/**
	 * 支付状态
	 */
	private Integer paymentStatus;
	
	/**
	 * 账务状态
	 */
	private Integer accountStatus;
	
	/**
	 * 
	 */
	private String errorCode;
	
	/**
	 * 
	 */
	private String errorMsg;
	
	/**
	 * 操作人
	 */
	private String editUser;
	
	/**
	 * 
	 */
	private String productCode;
	

	public TradeStatusRecordEntity() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
		this.idStr=String.valueOf(id);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	
	public String getPaymentId() {
		return paymentId;
	}
	
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	
	public Long getLoanId() {
		return loanId;
	}
	
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	public String getApplyId() {
		return applyId;
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
	
	public void setIdentityId(Long identityId) {
		this.identityId = identityId;
	}
	
	public Long getIdentityId() {
		return identityId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	
	public Integer getTradeType() {
		return tradeType;
	}
	
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Integer getOrderStatus() {
		return orderStatus;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setPeriods(Integer periods) {
		this.periods = periods;
	}
	
	public Integer getPeriods() {
		return periods;
	}
	
	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public Integer getPaymentStatus() {
		return paymentStatus;
	}
	
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	public Integer getAccountStatus() {
		return accountStatus;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	
	public String getEditUser() {
		return editUser;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductCode() {
		return productCode;
	}
	
}
