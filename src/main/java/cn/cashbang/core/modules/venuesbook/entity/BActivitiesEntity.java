package cn.cashbang.core.modules.venuesbook.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:35
 */
public class BActivitiesEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 活动id
	 */
	private String activityId;
	
	/**
	 * 场馆id
	 */
	private String venueId;
	
	/**
	 * 预约人id
	 */
	private String uid;
	
	/**
	 * 活动名称
	 */
	private String activityIdName;
	
	/**
	 * 活动人数
	 */
	private Integer activityCount;
	
	/**
	 * 活动类型
	 */
	private String activityType;
	
	/**
	 * 活动内容
	 */
	private String activityContent;
	
	/**
	 * 活动图片url
	 */
	private String activityIconUrl;
	
	/**
	 * 活动状态（1.公开 2.不公开）
	 */
	private Integer status;
	
	/**
	 * 活动时段
	 */
	private String activityTime;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	

	public BActivitiesEntity() {
		super();
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	public String getActivityId() {
		return activityId;
	}
	
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	
	public String getVenueId() {
		return venueId;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setActivityIdName(String activityIdName) {
		this.activityIdName = activityIdName;
	}
	
	public String getActivityIdName() {
		return activityIdName;
	}
	
	public void setActivityCount(Integer activityCount) {
		this.activityCount = activityCount;
	}
	
	public Integer getActivityCount() {
		return activityCount;
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
	
	public void setActivityIconUrl(String activityIconUrl) {
		this.activityIconUrl = activityIconUrl;
	}
	
	public String getActivityIconUrl() {
		return activityIconUrl;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}
	
	public String getActivityTime() {
		return activityTime;
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
