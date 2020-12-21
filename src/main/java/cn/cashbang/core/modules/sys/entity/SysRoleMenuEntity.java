package cn.cashbang.core.modules.sys.entity;


import java.io.Serializable;

/**
 * 角色与菜单对应关系
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月10日 上午10:48:34
 */
public class SysRoleMenuEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录id
	 */
	private Long id;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 菜单ID
	 */
	private Long menuId;

	public SysRoleMenuEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}
