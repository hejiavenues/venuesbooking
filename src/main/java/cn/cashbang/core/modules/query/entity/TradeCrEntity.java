package cn.cashbang.core.modules.query.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;




import java.io.Serializable;
import java.util.Date;



/**
 * 进件信息
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月17日 上午11:23:42
 */
public class TradeCrEntity implements Serializable {
	
	private String orderNo;
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	private String applyId;
	private String crTime1 ;
	private String crTime2 ;
	private String crStateChn ;
	private String crState ;
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getCrTime1() {
		return crTime1;
	}

	public void setCrTime1(String crTime1) {
		this.crTime1 = crTime1;
	}

	public String getCrTime2() {
		return crTime2;
	}

	public void setCrTime2(String crTime2) {
		this.crTime2 = crTime2;
	}

	public String getCrStateChn() {
		return crStateChn;
	}

	public void setCrStateChn(String crStateChn) {
		this.crStateChn = crStateChn;
	}

	public String getCrState() {
		return crState;
	}

	public void setCrState(String crState) {
		this.crState = crState;
	}


	
}
