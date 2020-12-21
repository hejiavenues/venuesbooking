package cn.cashbang.core.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;

/**
 * 系统用户dao
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 下午3:26:05
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

	SysUserEntity getByUserName(String username);
	
	List<Long> listAllMenuId(Long userId);
	
	List<Long> listAllOrgId(Long userId);
	
	int updatePswdByUser(Query query);
	
	int updateUserStatus(Query query);
	
	int updatePswd(SysUserEntity user);

	/**
	 * 根据角色查询
	 * 
	 * @param search
	 * @return
	 */
	List<SysUserEntity> listByRole(Map<String, Object> role);
	
}
