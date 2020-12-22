package cn.cashbang.core.modules.venuesbook.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 字典表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午2:29:29
 */
public class BDicEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * code值
	 */
	private String code;
	
	/**
	 * value值
	 */
	private String name;
	
	/**
	 * 类型code
	 */
	private String typecode;
	
	/**
	 * 类型描述
	 */
	private String typename;
	
	/**
	 * 状态（0.停用 1.启用）
	 */
	private Integer status;
	

	public BDicEntity() {
		super();
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	
	public String getTypecode() {
		return typecode;
	}
	
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	public String getTypename() {
		return typename;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}
	
}
