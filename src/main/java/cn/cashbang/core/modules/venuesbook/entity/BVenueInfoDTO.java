package cn.cashbang.core.modules.venuesbook.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * 场馆信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:20:39
 */
public class BVenueInfoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String venueId;
	
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
	 * 场馆简介
	 */
	private String describtion;
	
	/**
	 * 所属居委会id
	 */
	private String committeeId;
	/**
	 * 所属居委会id
	 */
	private String committeeName;
	
	/**
	 * 支持的活动类型
	 */
	private String supportActiveType;
	
	/**
	 * 图片地址
	 */
	private String iconUrl;
	

	public BVenueInfoDTO() {
		super();
	}

	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	
	public String getVenueId() {
		return venueId;
	}
	
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	
	public String getVenueName() {
		return venueName;
	}
	
	public void setMaxPeople(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}
	
	public Integer getMaxPeople() {
		return maxPeople;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setCommitteeId(String committeeId) {
		this.committeeId = committeeId;
	}
	
	public String getCommitteeId() {
		return committeeId;
	}
	
	public void setSupportActiveType(String supportActiveType) {
		this.supportActiveType = supportActiveType;
	}
	
	public String getSupportActiveType() {
		return supportActiveType;
	}
	
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	public String getIconUrl() {
		return iconUrl;
	}
	
	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public String getCommitteeName() {
		return committeeName;
	}

	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}
	
}
