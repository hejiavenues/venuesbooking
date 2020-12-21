package cn.cashbang.core.modules.sys.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.sys.entity.SysMenuEntity;

/**
 * 系统菜单
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月10日 上午10:34:59
 */
public interface SysMenuManager {
	
	List<SysMenuEntity> listUserMenu(Long userId);
	
	
	List<SysMenuEntity> listParentId(Long parentId, List<Long> menuIdList);
	
	List<SysMenuEntity> listMenu(Query search);
	
	List<SysMenuEntity> listMenuByManager(Query search);
	
	List<SysMenuEntity> listNotButton();
	
	int saveMenu(SysMenuEntity menu);

	SysMenuEntity getMenuById(Long id);
	
	int updateMenu(SysMenuEntity menu);
	
	int batchRemove(Long[] id);
	
	boolean hasChildren(Long[] id);
	
}
