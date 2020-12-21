package cn.cashbang.core.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.sys.entity.SysUserRoleEntity;

/**
 * 用户与角色关系
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月13日 上午1:01:55
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

	List<Long> listUserRoleId(Long userId);
	
	int batchRemoveByUserId(Long[] id);
	
	int batchRemoveByRoleId(Long[] id);
	
}
