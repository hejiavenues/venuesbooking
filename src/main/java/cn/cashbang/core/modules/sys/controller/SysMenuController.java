package cn.cashbang.core.modules.sys.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.entity.SysMenuEntity;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import cn.cashbang.core.modules.sys.service.SysMenuService;

/**
 * 系统菜单controller
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月10日 上午12:23:44
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

	@Resource
	private SysMenuService sysMenuService;
	
	/**
	 * 用户菜单
	 * @return
	 */
	@RequestMapping("/user")
	public Result user(){
		return sysMenuService.listUserMenu(getUserId());
	}
	
	/**
	 * 菜单列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public List<SysMenuEntity> listMenu(@RequestParam Map<String, Object> params) {
		return sysMenuService.listMenu(params);
	}
	
	/**
	 * 菜单列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/listByManager")
	public List<SysMenuEntity> listByManager(@RequestParam Map<String, Object> params) {
		SysUserEntity manager=getUser();
		if(manager.getChannel()!=null&&!manager.getChannel().trim().equals("")) {
			params.put("userId",manager.getUserId());
			return sysMenuService.listMenuByManager( params);
		}else {
			return sysMenuService.listMenu(params);
		}
	}
	
	/**
	 * 选择菜单(添加、修改)
	 * @return
	 */
	@RequestMapping("/select")
	public Result select() {
		return sysMenuService.listNotButton();
	}
	
	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	@SysLog("新增菜单")
	@RequestMapping("/save")
	public Result save(@RequestBody SysMenuEntity menu) {
		return sysMenuService.saveMenu(menu);
	}

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result info(@RequestBody Long id) {
		return sysMenuService.getMenuById(id);
	}
	
	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	@SysLog("修改菜单")
	@RequestMapping("/update")
	public Result update(@RequestBody SysMenuEntity menu) {
		return sysMenuService.updateMenu(menu);
	}
	
	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@SysLog("删除菜单")
	@RequestMapping("/remove")
	public Result remove(@RequestBody Long[] id) {
		return sysMenuService.batchRemove(id);
	}
	
}
