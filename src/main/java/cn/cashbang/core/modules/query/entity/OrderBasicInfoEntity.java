package cn.cashbang.core.modules.query.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单基本信息DO
 *
 */
public class OrderBasicInfoEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 9103446549709119231L;

    private Long orderId;

    private String orderNo;

    private Integer status;


    private String applyType;//申请类型

    private Integer isReloan;//融360告知此定单是否起复贷简化流程

    private BigDecimal applicationAmount;//申请合同金额

    private BigDecimal payAmount;//申请放款金额

    private Integer applicationPeriodDuration;//申请期限（每期时间）

    private Integer periods;//期数、期号

    private Date orderTime;//融360给的订单创建时间,并非此记录生成时间

    private String productId;//融360处的此产品的ID

    private String productName;//融360APP上展示给用户看的产品名称

    private String channelName;//渠道名称

    private String subChannel;//子渠道名称

    private String productCode;//产品编码:我方定义的产品code

    private Long passportIdentityId;

    private Long passportUserId;

    private String userId;//融360处的此用户ID

    private String userName;//用户姓名

    private String userMobile;//加密后用户手机号码

    private String idNumber;//加密后本人身份证号码

    private String encryptBankCard;//进件选择的放款银行卡号

    private String repayBankCard;//进件选择的还款银行卡号

    private String email;//邮箱

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public Integer getIsReloan() {
        return isReloan;
    }

    public void setIsReloan(Integer isReloan) {
        this.isReloan = isReloan;
    }

    public BigDecimal getApplicationAmount() {
        return applicationAmount;
    }

    public void setApplicationAmount(BigDecimal applicationAmount) {
        this.applicationAmount = applicationAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getApplicationPeriodDuration() {
        return applicationPeriodDuration;
    }

    public void setApplicationPeriodDuration(Integer applicationPeriodDuration) {
        this.applicationPeriodDuration = applicationPeriodDuration;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getPassportIdentityId() {
        return passportIdentityId;
    }

    public void setPassportIdentityId(Long passportIdentityId) {
        this.passportIdentityId = passportIdentityId;
    }

    public Long getPassportUserId() {
        return passportUserId;
    }

    public void setPassportUserId(Long passportUserId) {
        this.passportUserId = passportUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public static OrderBasicInfoEntity newInstance() {
        return new OrderBasicInfoEntity();
    }

    public String getEncryptBankCard() {
        return encryptBankCard;
    }

    public void setEncryptBankCard(String encryptBankCard) {
        this.encryptBankCard = encryptBankCard;
    }

    public String getRepayBankCard() {
        return repayBankCard;
    }

    public void setRepayBankCard(String repayBankCard) {
        this.repayBankCard = repayBankCard;
    }

    public String getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(String subChannel) {
        this.subChannel = subChannel;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "OrderBasicInfoDO{" +
                "applyType=" + applyType +
                ", isReloan=" + isReloan +
                ", applicationAmount=" + applicationAmount +
                ", payAmount=" + payAmount +
                ", applicationPeriodDuration=" + applicationPeriodDuration +
                ", periods=" + periods +
                ", orderTime=" + orderTime +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", channelName='" + channelName + '\'' +
                ", subChannel='" + subChannel + '\'' +
                ", productCode='" + productCode + '\'' +
                ", passportIdentityId=" + passportIdentityId +
                ", passportUserId=" + passportUserId +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", encryptBankCard='" + encryptBankCard + '\'' +
                ", repayBankCard='" + repayBankCard + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
