package cn.cashbang.core.modules.query.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 放款额度查询
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年4月03日 上午11:20:05
 */
public class QueryLoanamtHistoryEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 修改前金额
	 */
	private BigDecimal loanamtBefore;
	
	/**
	 * 修改后金额
	 */
	private BigDecimal loanamtAfter;
	
	/**
	 * 修改用户
	 */
	private Long userIdCreate;
	/**
	 * 修改用户名
	 * 
	 */
	private String userNameCreate;
	/**
	 * 修改用户名
	 * 
	 */
	private String userChnNameCreate;
	
	/**
	 * 修改时间
	 */
	private Date gmtCreate;
	
	/**
	 * 总放款额度
	 * 
	 */
	private BigDecimal loanamtSum;
	
	/**
	 * 剩余放款额度
	 * 
	 */
	private BigDecimal loanamtRemain;
	
	
	

	public QueryLoanamtHistoryEntity() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setLoanamtBefore(BigDecimal loanamtBefore) {
		this.loanamtBefore = loanamtBefore;
	}
	
	public BigDecimal getLoanamtBefore() {
		return loanamtBefore;
	}
	
	public void setLoanamtAfter(BigDecimal loanamtAfter) {
		this.loanamtAfter = loanamtAfter;
	}
	
	public BigDecimal getLoanamtAfter() {
		return loanamtAfter;
	}
	
	public void setUserIdCreate(Long userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	
	public Long getUserIdCreate() {
		return userIdCreate;
	}
	
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	public Date getGmtCreate() {
		return gmtCreate;
	}

	public BigDecimal getLoanamtSum() {
		return loanamtSum;
	}

	public void setLoanamtSum(BigDecimal loanamtSum) {
		this.loanamtSum = loanamtSum;
	}

	public BigDecimal getLoanamtRemain() {
		return loanamtRemain;
	}

	public void setLoanamtRemain(BigDecimal loanamtRemain) {
		this.loanamtRemain = loanamtRemain;
	}

	public String getUserNameCreate() {
		return userNameCreate;
	}

	public void setUserNameCreate(String userNameCreate) {
		this.userNameCreate = userNameCreate;
	}

	public String getUserChnNameCreate() {
		return userChnNameCreate;
	}

	public void setUserChnNameCreate(String userChnNameCreate) {
		this.userChnNameCreate = userChnNameCreate;
	}

	
	
}
