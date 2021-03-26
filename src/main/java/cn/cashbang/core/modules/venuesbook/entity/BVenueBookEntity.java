package cn.cashbang.core.modules.venuesbook.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 预约记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:09
 */
public class BVenueBookEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String id;
	
	/**
	 * 预约人id（或系统预约id）
	 */
	private String userId;
	
	/**
	 * 场馆id
	 */
	private String venueId;
	/**
	 * 场馆id
	 */
	private String activityContent;
	
	/**
	 * 预约日期
	 */
	private String bookDate;
	
	/**
	 * 预约时段
	 */
	private String bookTime;
	
	/**
	 * 状态（0 可选择、1、不可用 2、已预约）
	 */
	private Integer bookStatus;
	
	/**
	 * 
	 */
	private Date createTime;
	
	/**
	 * 
	 */
	private Date updateTime;

	/**
	 * 场馆名称
	 */
	private String venueName;

	/**
	 * 最大容纳人数
	 */
	private Integer maxPeople;

	/**
	 * 场馆地址
	 */
	private String address;

	/**
	 * 支持的活动类型
	 */
	private String supportActiveType;

	private String activityId;

	private String iconUrl;
	
	private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getSupportActiveType() {
		return supportActiveType;
	}

	public void setSupportActiveType(String supportActiveType) {
		this.supportActiveType = supportActiveType;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public Integer getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BVenueBookEntity() {
		super();
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	
	public String getVenueId() {
		return venueId;
	}
	
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}
	
	public String getBookDate() {
		return bookDate;
	}
	
	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}
	
	public String getBookTime() {
		return bookTime;
	}
	
	public void setBookStatus(Integer bookStatus) {
		this.bookStatus = bookStatus;
	}
	
	public Integer getBookStatus() {
		return bookStatus;
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

	public String getActivityContent() {
		return activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	
}
