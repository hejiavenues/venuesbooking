package cn.cashbang.core.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.sys.entity.SysMenuEntity;

/**
 * 系统菜单dao
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月10日 上午12:21:34
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
	
	List<SysMenuEntity> listParentId(Long parentId);
	
	List<SysMenuEntity> listNotButton();
	
	
	List<SysMenuEntity> listByManager(Query search);
	
	List<String> listUserPerms(Long userId);
	
	int countMenuChildren(Long parentId);

}
