package cn.cashbang.core.modules.venuesbook.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:46
 */
public class BActivityEntryEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String eid;
	
	/**
	 * 活动id
	 */
	private String activityId;
	
	/**
	 * 用户id
	 */
	private String uid;
	
	/**
	 * 姓名
	 */
	private String uname;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 是否到场
	 */
	private Integer ispresent;
	
	/**
	 * 报名状态 1.成功 2.取消
	 */
	private Integer status;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	

	public BActivityEntryEntity() {
		super();
	}

	public void setEid(String eid) {
		this.eid = eid;
	}
	
	public String getEid() {
		return eid;
	}
	
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	public String getActivityId() {
		return activityId;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public String getUname() {
		return uname;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setIspresent(Integer ispresent) {
		this.ispresent = ispresent;
	}
	
	public Integer getIspresent() {
		return ispresent;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
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
