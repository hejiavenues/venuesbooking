package cn.cashbang.core.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.sys.entity.SysRoleMenuEntity;

/**
 * 系统角色与菜单关系
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月13日 下午8:32:26
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

	int batchRemoveByMenuId(Long[] id);
	
	int batchRemoveByRoleId(Long[] id);
	
	List<Long> listMenuId(Long id);
	
}
