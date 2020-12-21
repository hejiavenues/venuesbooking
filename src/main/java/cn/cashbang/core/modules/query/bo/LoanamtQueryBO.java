package cn.cashbang.core.modules.query.bo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 放款额度查询返回entity
 * 
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 上午11:41:07
 */
public class LoanamtQueryBO implements Serializable{
	
	private static final long serialVersionUID = 5080093401153432919L;
	private BigDecimal availAmount;	//剩余额度
	private BigDecimal maxAmount;	//最大金额
	
	
	public BigDecimal getAvailAmount() {
		return availAmount;
	}
	public void setAvailAmount(BigDecimal availAmount) {
		this.availAmount = availAmount;
	}
	public BigDecimal getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}
	
	
	
}
