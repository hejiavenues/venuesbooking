package cn.cashbang.core.modules.venuesbook.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 居委会信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月23日 上午9:48:52
 */
public class BCommitteesEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String cid;
	
	/**
	 * 名称
	 */
	private String cname;
	
	/**
	 * 联系方式
	 */
	private String connectMobile;
	
	/**
	 * 负责人
	 */
	private String connectPerson;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	private Date updateTime;
	

	public BCommitteesEntity() {
		super();
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	public String getCid() {
		return cid;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getCname() {
		return cname;
	}
	
	public void setConnectMobile(String connectMobile) {
		this.connectMobile = connectMobile;
	}
	
	public String getConnectMobile() {
		return connectMobile;
	}
	
	public void setConnectPerson(String connectPerson) {
		this.connectPerson = connectPerson;
	}
	
	public String getConnectPerson() {
		return connectPerson;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
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
	
}
