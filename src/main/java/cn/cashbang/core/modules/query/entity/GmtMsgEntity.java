package cn.cashbang.core.modules.query.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;




import java.io.Serializable;
import java.util.Date;



/**
 * 进件审批
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月17日 上午11:23:42
 */
public class GmtMsgEntity implements Serializable {


	/**
	 * 单号
	 */
	private String  applyCode;
	/**
	 * 系统分配
	 */
	private String  sysTime;
	
	/**
	 * 审批更新时间
	 */
	private String gmTTime;
	
	private Integer userIdDecision;
	private Integer decisionReason;
	
	
	/**
	 * 拒绝1
	 */
	private String time11;
	
	private Integer sum11;
	/**
	 * 拒绝2
	 */
	private String time12;
	
	private Integer sum12;
	
	/**
	 * 一级
	 */
	private String time10;
	
	private Integer sum10;
	
	
	/**
	 * 二级通过
	 */
	private String time0;
	
	private Integer sum0;

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getSysTime() {
		return sysTime;
	}

	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}

	public String getGmTTime() {
		return gmTTime;
	}

	public void setGmTTime(String gmTTime) {
		this.gmTTime = gmTTime;
	}

	public Integer getUserIdDecision() {
		return userIdDecision;
	}

	public void setUserIdDecision(Integer userIdDecision) {
		this.userIdDecision = userIdDecision;
	}

	public Integer getDecisionReason() {
		return decisionReason;
	}

	public void setDecisionReason(Integer decisionReason) {
		this.decisionReason = decisionReason;
	}

	public String getTime11() {
		return time11;
	}

	public void setTime11(String time11) {
		this.time11 = time11;
	}

	public Integer getSum11() {
		return sum11;
	}

	public void setSum11(Integer sum11) {
		this.sum11 = sum11;
	}

	public String getTime12() {
		return time12;
	}

	public void setTime12(String time12) {
		this.time12 = time12;
	}

	public Integer getSum12() {
		return sum12;
	}

	public void setSum12(Integer sum12) {
		this.sum12 = sum12;
	}

	public String getTime10() {
		return time10;
	}

	public void setTime10(String time10) {
		this.time10 = time10;
	}

	public Integer getSum10() {
		return sum10;
	}

	public void setSum10(Integer sum10) {
		this.sum10 = sum10;
	}

	public String getTime0() {
		return time0;
	}

	public void setTime0(String time0) {
		this.time0 = time0;
	}

	public Integer getSum0() {
		return sum0;
	}

	public void setSum0(Integer sum0) {
		this.sum0 = sum0;
	}
	

}
