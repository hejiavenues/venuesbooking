package cn.cashbang.core.modules.sys.service;

import java.util.List;
import java.util.Map;

import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.entity.SysMenuEntity;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;

/**
 * 系统菜单
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月10日 上午10:35:58
 */
public interface SysMenuService {
	
	Result listUserMenu(Long userId);
	
	List<SysMenuEntity> listMenuByManager(Map<String, Object> params);
	
	List<SysMenuEntity> listMenu(Map<String, Object> params);
	
	Result listNotButton();
	
	Result saveMenu(SysMenuEntity menu);

	Result getMenuById(Long id);
	
	Result updateMenu(SysMenuEntity menu);
	
	Result batchRemove(Long[] id);

}
