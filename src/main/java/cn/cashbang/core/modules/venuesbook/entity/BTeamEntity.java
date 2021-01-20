package cn.cashbang.core.modules.venuesbook.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 团队信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 上午11:57:39
 */
public class BTeamEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String tid;
	
	/**
	 * 团长id
	 */
	private String uid;
	/**
	 * 团长id
	 */
	private String uname;
	
	/**
	 * 姓名
	 */
	private String tname;
	
	/**
	 * 招募人数
	 */
	private Integer peopleCount;
	
	/**
	 * 活动类型
	 */
	private String activityType;
	/**
	 * 活动类型
	 */
	private String activityTypeDesc;
	
	/**
	 * 活动内容
	 */
	private String activityContent;
	
	/**
	 * 加入条件
	 */
	private String enterCondition;
	
	/**
	 * 状态 1放开、2关闭、3满员、4.组队失败
	 */
	private Integer status;
	/**
	 * 状态 1放开、2关闭、3满员、4.组队失败
	 */
	private String statusStr;
	
	/**
	 * 截止时间
	 */
	private Date deadLine;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	

	public BTeamEntity() {
		super();
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
	
	public String getTid() {
		return tid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	public String getTname() {
		return tname;
	}
	
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
	
	public Integer getPeopleCount() {
		return peopleCount;
	}
	
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	
	public String getActivityType() {
		return activityType;
	}
	
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	
	public String getActivityContent() {
		return activityContent;
	}
	
	public void setEnterCondition(String enterCondition) {
		this.enterCondition = enterCondition;
	}
	
	public String getEnterCondition() {
		return enterCondition;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	
	public Date getDeadLine() {
		return deadLine;
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

	public String getActivityTypeDesc() {
		return activityTypeDesc;
	}

	public void setActivityTypeDesc(String activityTypeDesc) {
		this.activityTypeDesc = activityTypeDesc;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	
}
