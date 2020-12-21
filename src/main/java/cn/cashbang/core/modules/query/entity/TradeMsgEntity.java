package cn.cashbang.core.modules.query.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

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
public class TradeMsgEntity implements Serializable {
	
	private String orderNo;
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	private  List<TradeCrEntity> tradeCrMsgs; 

	public List<TradeCrEntity> getTradeCrMsgs() {
		return tradeCrMsgs;
	}

	public void setTradeCrMsgs(List<TradeCrEntity> tradeCrMsgs) {
		this.tradeCrMsgs = tradeCrMsgs;
	}

	/**
	 * 进单时间
	 */
	private String orderTime0;
	/**
	 * 当前订单状态
	 */
	private Integer orderNowState;
	
	/**
	 * 通过
	 */
	private String orderTime11;
	
	private Integer orderSum11;
	
	/**
	 * 拒绝
	 */
	private String orderTime12;
	
	private Integer orderSum12;
	
	/**
	 * 放款中
	 */
	private String orderTime20;
	
	private Integer orderSum20;
	
	
	/**
	 * 放款成功
	 */
	private String orderTime21;
	
	private Integer orderSum21;
	
	/**
	 * 放款失败
	 */
	private String orderTime22;
	
	private Integer orderSum22;
	/**
	 * 放款取消
	 */
	private String orderTime23;
	
	private Integer orderSum23;
	/**
	 * 放款超时取消
	 */
	private String orderTime24;
	
	private Integer orderSum24;
	
	/**
	 * 还款中
	 */
	private String orderTime30;
	
	private Integer orderSum30;
	
	/**
	 * 逾期
	 */
	private String orderTime31;
	
	private Integer orderSum31;
	/**
	 * 已结清
	 */
	private String orderTime32;

	private Integer orderSum32;
	
	public String getOrderTime0() {
		return orderTime0;
	}

	public void setOrderTime0(String orderTime0) {
		this.orderTime0 = orderTime0;
	}

	public Integer getOrderNowState() {
		return orderNowState;
	}

	public void setOrderNowState(Integer orderNowState) {
		this.orderNowState = orderNowState;
	}

	public String getOrderTime11() {
		return orderTime11;
	}

	public void setOrderTime11(String orderTime11) {
		this.orderTime11 = orderTime11;
	}

	public Integer getOrderSum11() {
		return orderSum11;
	}

	public void setOrderSum11(Integer orderSum11) {
		this.orderSum11 = orderSum11;
	}

	public String getOrderTime12() {
		return orderTime12;
	}

	public void setOrderTime12(String orderTime12) {
		this.orderTime12 = orderTime12;
	}

	public Integer getOrderSum12() {
		return orderSum12;
	}

	public void setOrderSum12(Integer orderSum12) {
		this.orderSum12 = orderSum12;
	}

	public String getOrderTime20() {
		return orderTime20;
	}

	public void setOrderTime20(String orderTime20) {
		this.orderTime20 = orderTime20;
	}

	public Integer getOrderSum20() {
		return orderSum20;
	}

	public void setOrderSum20(Integer orderSum20) {
		this.orderSum20 = orderSum20;
	}

	public String getOrderTime21() {
		return orderTime21;
	}

	public void setOrderTime21(String orderTime21) {
		this.orderTime21 = orderTime21;
	}

	public Integer getOrderSum21() {
		return orderSum21;
	}

	public void setOrderSum21(Integer orderSum21) {
		this.orderSum21 = orderSum21;
	}

	public String getOrderTime22() {
		return orderTime22;
	}

	public void setOrderTime22(String orderTime22) {
		this.orderTime22 = orderTime22;
	}

	public Integer getOrderSum22() {
		return orderSum22;
	}

	public void setOrderSum22(Integer orderSum22) {
		this.orderSum22 = orderSum22;
	}

	public String getOrderTime23() {
		return orderTime23;
	}

	public void setOrderTime23(String orderTime23) {
		this.orderTime23 = orderTime23;
	}

	public Integer getOrderSum23() {
		return orderSum23;
	}

	public void setOrderSum23(Integer orderSum23) {
		this.orderSum23 = orderSum23;
	}

	public String getOrderTime24() {
		return orderTime24;
	}

	public void setOrderTime24(String orderTime24) {
		this.orderTime24 = orderTime24;
	}

	public Integer getOrderSum24() {
		return orderSum24;
	}

	public void setOrderSum24(Integer orderSum24) {
		this.orderSum24 = orderSum24;
	}

	public String getOrderTime30() {
		return orderTime30;
	}

	public void setOrderTime30(String orderTime30) {
		this.orderTime30 = orderTime30;
	}

	public Integer getOrderSum30() {
		return orderSum30;
	}

	public void setOrderSum30(Integer orderSum30) {
		this.orderSum30 = orderSum30;
	}

	public String getOrderTime31() {
		return orderTime31;
	}

	public void setOrderTime31(String orderTime31) {
		this.orderTime31 = orderTime31;
	}

	public Integer getOrderSum31() {
		return orderSum31;
	}

	public void setOrderSum31(Integer orderSum31) {
		this.orderSum31 = orderSum31;
	}

	public String getOrderTime32() {
		return orderTime32;
	}

	public void setOrderTime32(String orderTime32) {
		this.orderTime32 = orderTime32;
	}

	public Integer getOrderSum32() {
		return orderSum32;
	}

	public void setOrderSum32(Integer orderSum32) {
		this.orderSum32 = orderSum32;
	}
	 
	

}
