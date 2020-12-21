package cn.cashbang.core.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2019年5月31日 下午3:00:25
 */
public class SysWhiteIpEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * ip或者userId
	 */
	private String onlyId;
	
	/**
	 * 
	 */
	private String userName;
	
	/**
	 * 创建用户
	 */
	private String userId;
	
	/**
	 * 备注
	 */
	private String text;
	
	/**
	 * 类型（user 无视ip用户第一判断，ip第二判断）
	 */
	private String type;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	
	private String LoginName;
	
	

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public SysWhiteIpEntity() {
		super();
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setOnlyId(String onlyId) {
		this.onlyId = onlyId;
	}
	
	public String getOnlyId() {
		return onlyId;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
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
