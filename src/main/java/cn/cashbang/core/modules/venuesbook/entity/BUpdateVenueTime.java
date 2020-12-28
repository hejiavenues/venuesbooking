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
public class BUpdateVenueTime implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String venueId;
	
	private String dynamicTags;

	public String getVenueId() {
		return venueId;
	}

	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}

	public String getDynamicTags() {
		return dynamicTags;
	}

	public void setDynamicTags(String dynamicTags) {
		this.dynamicTags = dynamicTags;
	}
	
}
