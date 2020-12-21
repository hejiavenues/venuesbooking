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
 * @date 2018年8月02日 PM6:19:44
 */
public class MTopublicLogEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private String paymentid;
	
	/**
	 * 
	 */
	private Long operuserid;
	
	/**
	 * 
	 */
	private String operuser;
	
	/**
	 * 
	 */
	private Date operdate;
	
	/**
	 * 
	 */
	private Integer period;
	
	/**
	 * 
	 */
	private BigDecimal amount;
	
	/**
	 * 
	 */
	private String accountid;
	
	/**
	 * 
	 */
	private String rep;
	
	/**
	 * 
	 */
	private String name;
	
	/**
	 * 
	 */
	private String mobile;
	

	public MTopublicLogEntity() {
		super();
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	
	public String getPaymentid() {
		return paymentid;
	}
	
	public void setOperuserid(Long operuserid) {
		this.operuserid = operuserid;
	}
	
	public Long getOperuserid() {
		return operuserid;
	}
	
	public void setOperuser(String operuser) {
		this.operuser = operuser;
	}
	
	public String getOperuser() {
		return operuser;
	}
	
	public void setOperdate(Date operdate) {
		this.operdate = operdate;
	}
	
	public Date getOperdate() {
		return operdate;
	}
	
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	public Integer getPeriod() {
		return period;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	public String getAccountid() {
		return accountid;
	}
	
	public void setRep(String rep) {
		this.rep = rep;
	}
	
	public String getRep() {
		return rep;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMobile() {
		return mobile;
	}
	
}
