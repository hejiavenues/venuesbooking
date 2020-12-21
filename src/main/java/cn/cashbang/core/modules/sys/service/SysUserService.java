package cn.cashbang.core.modules.sys.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;

/**
 * 系统用户
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月11日 上午11:45:42
 */
public interface SysUserService {

	Page<SysUserEntity> listUser(Map<String, Object> params);
	
	Result saveUser(SysUserEntity user);
	
	Result saveChannelUser(String  channel,String channelName,Long creatUserId);
	
	Result getUserById(Long userId);
	
	Result updateUser(SysUserEntity user);
	
	Result batchRemove(Long[] id);
	
	Result listUserPerms(Long userId);
	
	Result updatePswdByUser(SysUserEntity user);
	
	Result updateUserEnable(Long[] id);
	
	Result updateUserDisable(Long[] id);
	
	Result updatePswd(SysUserEntity user);
	
	Result saveUserToken(Long userId);
	
	Result updateUserToken(Long userId);
	
	SysUserEntity getByUserName(String username);

	/**
	 * 查询特定角色用户
	 * 
	 * @param params
	 * @return
	 */
	
	Result listByRole(Map<String, Object> role);
	
	boolean hasAdmin();
}
