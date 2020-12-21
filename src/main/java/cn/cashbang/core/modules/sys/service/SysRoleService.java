package cn.cashbang.core.modules.sys.service;

import java.util.Map;

import cn.cashbang.core.common.constant.SystemMsg.sysOrg;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;
import cn.cashbang.core.modules.sys.entity.SysRoleEntity;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;

/**
 * 系统角色
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月12日 上午12:40:42
 */
public interface SysRoleService {

	Page<SysRoleEntity> listRole(Map<String, Object> params);
	
	Page<SysRoleEntity> listChannelRole(Map<String, Object> params);
	
	Result saveRole(SysRoleEntity role);
	
	Result getRoleById(Long id);
	
	Result updateRole(SysRoleEntity role);
	
	Result batchRemove(Long[] id);
	
	Result list(Map<String, Object> params);
	
	Result updateRoleOptAuthorization(SysRoleEntity role);
	
	Result updateRoleDataAuthorization(SysRoleEntity role);
	
	
	Result checkAndSaveRole(SysRoleEntity role,SysUserEntity user);
	
	
	
	Result saveChannelRoles(SysOrgEntity role,SysUserEntity user);
	
	
	
	Result checkAndUpdateRole(SysRoleEntity role,SysUserEntity user);
	
	Result checkAndBatchRemove(String[]  idAndSign,SysUserEntity user);
	
}
