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
	 * 预约日期
	 */
	private String bookDate;
	
	/**
	 * 预约时段
	 */
	private String bookTime;
	
	/**
	 * 状态（1、不可用 2、已预约）
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
	
}
