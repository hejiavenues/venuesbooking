package cn.cashbang.core.modules.query.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;




import java.io.Serializable;
import java.util.Date;



/**
 * 进件主表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月17日 上午11:23:42
 */
public class TradeApplyStatusEntity implements Serializable {



	/**
	 * 进件主表
	 *
	 * @author daibangzhu
	 * @email xxx@daibangzhu.cn
	 * @url www.daibangzhu.cn
	 * @date 2018年7月17日 上午11:23:42
	 */
	private String orderId;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	private String orderNo;
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	private String applyType;
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	/**
	 * 用户姓名
	 */
	private String userName;
	
	/**
	 * 加密后用户手机号码
	 */
	private String userMobile;
	
	/**
	 * 加密后本人身份证号码
	 */
	private String idNumber;
	
	/**
	 * 进件放款卡(app进件时存储，审核能过后，进行绑卡业务)
	 */
	private String encryptBankCard;
	
	private String isReloan;
	
	
		
		

		public String getIsReloan() {
		return isReloan;
	}

	public void setIsReloan(String isReloan) {
		this.isReloan = isReloan;
	}

		public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEncryptBankCard() {
		return encryptBankCard;
	}

	public void setEncryptBankCard(String encryptBankCard) {
		this.encryptBankCard = encryptBankCard;
	}

		private static final long serialVersionUID = 1L;
		
		/**
		 * 主键
		 */
		private Long id;
		
		/**
		 * 创建时间
		 */
		private Date createTime;
		
		/**
		 * 修改时间
		 */
		private Date updateTime;
		
		/**
		 * 版本
		 */
		private Integer version;
		
		/**
		 * 申请id
		 */
		private String applyId;
		
		/**
		 * 进件状态
		 */
		private String applyState;
		
		/**
		 * 申请金额
		 */
		private BigDecimal applyAmount;
		
		/**
		 * 
		 */
		private BigDecimal payAmount;
		
		/**
		 * passport中的
		 */
		private Long identityId;
		
		/**
		 * 渠道
		 */
		private String channel;
		
		/**
		 * 新老客户
		 */
		private String customerType;
		
		/**
		 * 用户id，passport中
		 */
		private Long userId;
		
		/**
		 * 期数
		 */
		private Integer applyPeriod;
		
		/**
		 * 期限（天）
		 */
		private Integer applyTerm;
		
		/**
		 * 放款卡号
		 */
		private String bankCard;
		
		/**
		 * 还款卡
		 */
		private String repayBankCard;
		
		/**
		 * 产品码
		 */
		private String productCode;
		
		/**
		 * 
		 */
		private String subChannel;
		

		public TradeApplyStatusEntity() {
			super();
		}

		public void setId(Long id) {
			this.id = id;
		}
		
		public Long getId() {
			return id;
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
		
		public void setApplyId(String applyId) {
			this.applyId = applyId;
		}
		
		public String getApplyId() {
			return applyId;
		}
		
		public void setApplyState(String applyState) {
			this.applyState = applyState;
		}
		
		public String getApplyState() {
			return applyState;
		}
		
		public void setApplyAmount(BigDecimal applyAmount) {
			this.applyAmount = applyAmount;
		}
		
		public BigDecimal getApplyAmount() {
			return applyAmount;
		}
		
		public void setPayAmount(BigDecimal payAmount) {
			this.payAmount = payAmount;
		}
		
		public BigDecimal getPayAmount() {
			return payAmount;
		}
		
		public void setIdentityId(Long identityId) {
			this.identityId = identityId;
		}
		
		public Long getIdentityId() {
			return identityId;
		}
		
		public void setChannel(String channel) {
			this.channel = channel;
		}
		
		public String getChannel() {
			return channel;
		}
		
		public void setCustomerType(String customerType) {
			this.customerType = customerType;
		}
		
		public String getCustomerType() {
			return customerType;
		}
		
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		
		public Long getUserId() {
			return userId;
		}
		
		public void setApplyPeriod(Integer applyPeriod) {
			this.applyPeriod = applyPeriod;
		}
		
		public Integer getApplyPeriod() {
			return applyPeriod;
		}
		
		public void setApplyTerm(Integer applyTerm) {
			this.applyTerm = applyTerm;
		}
		
		public Integer getApplyTerm() {
			return applyTerm;
		}
		
		public void setBankCard(String bankCard) {
			this.bankCard = bankCard;
		}
		
		public String getBankCard() {
			return bankCard;
		}
		
		public void setRepayBankCard(String repayBankCard) {
			this.repayBankCard = repayBankCard;
		}
		
		public String getRepayBankCard() {
			return repayBankCard;
		}
		
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		
		public String getProductCode() {
			return productCode;
		}
		
		public void setSubChannel(String subChannel) {
			this.subChannel = subChannel;
		}
		
		public String getSubChannel() {
			return subChannel;
		}

		@Override
		public String toString() {
			return "TradeApplyStatusEntity [orderNo=" + orderNo + ", userName=" + userName + ", userMobile="
					+ userMobile + ", idNumber=" + idNumber + ", encryptBankCard=" + encryptBankCard + ", id=" + id
					+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version
					+ ", applyId=" + applyId + ", applyState=" + applyState + ", applyAmount=" + applyAmount
					+ ", payAmount=" + payAmount + ", identityId=" + identityId + ", channelinfo=" + channel
					+ ", customerType=" + customerType + ", userId=" + userId + ", applyPeriod=" + applyPeriod
					+ ", applyTerm=" + applyTerm + ", bankCard=" + bankCard + ", repayBankCard=" + repayBankCard
					+ ", productCode=" + productCode + ", subChannel=" + subChannel + "]";
		}
		


}
