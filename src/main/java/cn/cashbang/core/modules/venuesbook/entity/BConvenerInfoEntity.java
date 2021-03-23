package cn.cashbang.core.modules.venuesbook.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 召集人信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:27
 */
public class BConvenerInfoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String uid;
	/**
	 * 
	 */
	private String uname;
	/**
	 * 
	 */
	private String mobile;
	/**
	 * 
	 */
	private String committeeName;
	
	/**
	 * 召集人活动类型
	 */
	private Integer activityId;
	
	/**
	 * 身份证正面url
	 */
	private String idcardFrontUrl;
	
	/**
	 * 身份证反面url
	 */
	private String idcardBackUrl;
	
	/**
	 * 状态 0.审核中 1.审核通过 2.审核拒绝
	 */
	private Integer status;
	
	private String statusDesc;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	private String teamContent;

    public String getTeamContent() {
        return teamContent;
    }

    public void setTeamContent(String teamContent) {
        this.teamContent = teamContent;
    }

    public BConvenerInfoEntity() {
		super();
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	
	public Integer getActivityId() {
		return activityId;
	}
	
	public void setIdcardFrontUrl(String idcardFrontUrl) {
		this.idcardFrontUrl = idcardFrontUrl;
	}
	
	public String getIdcardFrontUrl() {
		return idcardFrontUrl;
	}
	
	public void setIdcardBackUrl(String idcardBackUrl) {
		this.idcardBackUrl = idcardBackUrl;
	}
	
	public String getIdcardBackUrl() {
		return idcardBackUrl;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
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

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCommitteeName() {
		return committeeName;
	}

	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
}
