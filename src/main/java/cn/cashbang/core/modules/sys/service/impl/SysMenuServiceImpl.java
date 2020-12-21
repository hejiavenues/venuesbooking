package cn.cashbang.core.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cashbang.core.common.constant.MsgConstant;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.entity.SysMenuEntity;
import cn.cashbang.core.modules.sys.manager.SysMenuManager;
import cn.cashbang.core.modules.sys.service.SysMenuService;

/**
 * 系统菜单
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月10日 上午10:36:31
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuManager sysMenuManager;

	@Override
	public Result listUserMenu(Long userId) {
		return Result.ok().put("menuList", sysMenuManager.listUserMenu(userId));
	}

	@Override
	public List<SysMenuEntity> listMenu(Map<String, Object> params) {
		Query query = new Query(params);
		List<SysMenuEntity> menuList = sysMenuManager.listMenu(query);
		return menuList;
	}
	
	@Override
	public List<SysMenuEntity> listMenuByManager(Map<String, Object> params) {
		Query query = new Query(params);
		List<SysMenuEntity> menuList = sysMenuManager.listMenuByManager(query);
		return menuList;
	}
	
	@Override
	public Result listNotButton() {
		List<SysMenuEntity> menuList = sysMenuManager.listNotButton();
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		return CommonUtils.msgNotCheckNull(menuList);
	}

	@Override
	public Result saveMenu(SysMenuEntity menu) {
		int count = sysMenuManager.saveMenu(menu);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getMenuById(Long id) {
		SysMenuEntity menu = sysMenuManager.getMenuById(id);
		return CommonUtils.msg(menu);
	}

	@Override
	public Result updateMenu(SysMenuEntity menu) {
		int count = sysMenuManager.updateMenu(menu);
		return CommonUtils.msg(count);
	}

	@Override
	@Transactional("transactionManager")
	public Result batchRemove(Long[] id) {
		boolean children = sysMenuManager.hasChildren(id);
		if(children) {
			return Result.error(MsgConstant.MSG_HAS_CHILD);
		}
		int count = sysMenuManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
