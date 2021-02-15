package cn.cashbang.core.modules.venuesbook.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:02
 */
public class BUserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户id
	 */
	private String uid;
	
	/**
	 * 用户名称
	 */
	private String uname;
	
	/**
	 * 用户性别（1.男 0.女）
	 */
	private Integer sex;
	
	private String sexStr;
	
	/**
	 * 出生日期
	 */
	private String birthday;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 居委会id
	 */
	private String committeeId;
	
	/**
	 * 角色id （1.普通用户 2.召集人）
	 */
	private Integer userRole;
	
	/**
	 * 用户年龄
	 */
	private Integer userAge;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 用户状态（1.正常 2.禁用 3.删除）
	 */
	private Integer status;
	
	private String statusStr;
	
	/**
	 * 头像地址
	 */
	private String iconUrl;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	private String committeeName;


	public String getCommitteeName() {
		return committeeName;
	}

	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}

	/**
	 * 微信用户唯一标识
	 */
	private String openId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public BUserEntity() {
		super();
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
	
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Integer getSex() {
		return sex;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setCommitteeId(String committeeId) {
		this.committeeId = committeeId;
	}
	
	public String getCommitteeId() {
		return committeeId;
	}
	
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	
	public Integer getUserRole() {
		return userRole;
	}
	
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	
	public Integer getUserAge() {
		return userAge;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	public String getIconUrl() {
		return iconUrl;
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

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getSexStr() {
		return sexStr;
	}

	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}
	
}
