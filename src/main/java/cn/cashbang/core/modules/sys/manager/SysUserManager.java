package cn.cashbang.core.modules.sys.manager;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import cn.cashbang.core.modules.sys.entity.SysUserTokenEntity;

/**
 * 系统用户
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月11日 上午11:43:01
 */
public interface SysUserManager {

	SysUserEntity getByUserName(String username);
	
	List<SysUserEntity> listUser(Page<SysUserEntity> page, Query search);
	
	int saveUser(SysUserEntity user);
	
	SysUserEntity getById(Long userId);
	
	int updateUser(SysUserEntity user);
	
	int batchRemove(Long[] id);
	
	Set<String> listUserPerms(Long userId);
	
	Set<String> listUserRoles(Long userId);
	
	int updatePswdByUser(Query query);
	
	int updateUserEnable(Long[] id);
	
	int updateUserDisable(Long[] id);
	
	int updatePswd(SysUserEntity user);
	
	SysUserEntity getUserById(Long userId);
	
	SysUserTokenEntity getByToken(String token);
	
	SysUserTokenEntity saveUserToken(Long userId);
	
	int updateUserToken(Long userId);
	

	List<SysUserEntity> listByRole(Map<String, Object> role);
	
}
