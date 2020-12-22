package cn.cashbang.core.modules.venuesbook.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * banner图表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:17:36
 */
public class BBannerInfoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String bid;
	
	/**
	 * banner图描述
	 */
	private String bannerDesc;
	
	/**
	 * banner图链接url
	 */
	private String bannerImgUrl;
	
	/**
	 * 
	 */
	private String bannerJumpUrl;
	
	/**
	 * banner排序id
	 */
	private Integer sortid;
	
	/**
	 * 是否上架（1.上架 0.下架）
	 */
	private Integer isuse;
	
	/**
	 * 
	 */
	private Date createTime;
	
	/**
	 * 
	 */
	private Date updateTime;
	

	public BBannerInfoEntity() {
		super();
	}

	public void setBid(String bid) {
		this.bid = bid;
	}
	
	public String getBid() {
		return bid;
	}
	
	public void setBannerDesc(String bannerDesc) {
		this.bannerDesc = bannerDesc;
	}
	
	public String getBannerDesc() {
		return bannerDesc;
	}
	
	public void setBannerImgUrl(String bannerImgUrl) {
		this.bannerImgUrl = bannerImgUrl;
	}
	
	public String getBannerImgUrl() {
		return bannerImgUrl;
	}
	
	public void setBannerJumpUrl(String bannerJumpUrl) {
		this.bannerJumpUrl = bannerJumpUrl;
	}
	
	public String getBannerJumpUrl() {
		return bannerJumpUrl;
	}
	
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	
	public Integer getSortid() {
		return sortid;
	}
	
	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}
	
	public Integer getIsuse() {
		return isuse;
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
