package cn.cashbang.core.modules.sys.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.sys.entity.SysRoleEntity;

/**
 * 系统角色
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月12日 上午12:39:07
 */
public interface SysRoleManager {

	List<SysRoleEntity> listRole(Page<SysRoleEntity> page, Query search);
	List<SysRoleEntity> listChannelRole(Page<SysRoleEntity> page, Query search);

	
	int saveRole(SysRoleEntity role);
	
	SysRoleEntity getRoleById(Long id);
	
	int updateRole(SysRoleEntity role);
	
	int batchRemove(Long[] id);
	
	List<SysRoleEntity> list(Query search);
	
	int updateRoleOptAuthorization(SysRoleEntity role);

	int updateRoleDataAuthorization(SysRoleEntity role);
	
	SysRoleEntity  selectByRole(SysRoleEntity role);
	
	
}
