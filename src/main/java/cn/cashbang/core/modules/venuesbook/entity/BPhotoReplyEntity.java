package cn.cashbang.core.modules.venuesbook.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月15日 下午2:46:57
 */
public class BPhotoReplyEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String reply;
	
	/**
	 * 随拍Id
	 */
	private String photoId;
	
	private String photoContent;
	
	private String uname;
	
	/**
	 * 用户id
	 */
	private String uid;
	
	/**
	 * 评论内容
	 */
	private String content;
	
	/**
	 * 
	 */
	private Date createTime;
	
	/**
	 * 
	 */
	private Date updateTime;
	

	public BPhotoReplyEntity() {
		super();
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
	
	public String getReply() {
		return reply;
	}
	
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	
	public String getPhotoId() {
		return photoId;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
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

	public String getPhotoContent() {
		return photoContent;
	}

	public void setPhotoContent(String photoContent) {
		this.photoContent = photoContent;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
}
