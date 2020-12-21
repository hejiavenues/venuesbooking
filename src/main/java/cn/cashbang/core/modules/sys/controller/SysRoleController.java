package cn.cashbang.core.modules.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.constant.SystemConstant;
import cn.cashbang.core.common.constant.SystemMsg;
import cn.cashbang.core.common.constant.SystemMsg.sysOrg;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;
import cn.cashbang.core.modules.sys.entity.SysRoleEntity;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import cn.cashbang.core.modules.sys.service.SysRoleService;
import cn.cashbang.core.modules.sys.service.SysUserService;

/**
 * 系统角色
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月12日 上午12:43:10
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {

	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 角色列表
	 * @param params
	 * @return
	 * 1.只有有管理员角色的人员才能看见管理员角色 用于列表
	 */
	@RequestMapping("/list")
	public Page<SysRoleEntity> list(@RequestBody Map<String, Object> params) {
		
		if(!sysUserService.hasAdmin()) {
			params.put("notAdmin",SystemMsg.sysRole.ADMIN);
		}
		params.put("orgIdPath",super.getOrgIdPath());
		return sysRoleService.listRole(params);
	}
	
	/**
	 * 角色列表
	 * @param params
	 * @return
	 * 根据渠道查询机构
	 */
	@RequestMapping("/listInChannel")
	public Page<SysRoleEntity> listInChannel(@RequestBody Map<String, Object> params) {
		
		
		params.put("notAdmin",SystemMsg.sysRole.ADMIN);
		
		
		return sysRoleService.listChannelRole(params);
		
	}
	
	
	
	
	/**
	 * 用户角色
	 * @return
	 * 2.只有有管理员角色的人员才能看见管理员角色 用于创建用户
	 */
	@RequestMapping("/select")
	public Result listRole() {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(!sysUserService.hasAdmin()) {          
			params.put("notAdmin",SystemMsg.sysRole.ADMIN);
		}
		params.put("orgIdPath",super.getOrgIdPath());
		return sysRoleService.list(params);
	}
	
	/**
	 * 新增角色
	 * @param role
	 * @return
	 * 3.新增角色只用管理员用户可以创建系统关键用户角色包括管理员,电核，运营经理，风控经理，电核审查等
	 */
	@SysLog("新增角色")
	@RequestMapping("/save")
	public Result saveRole(@RequestBody SysRoleEntity role) {
		
		return sysRoleService.checkAndSaveRole(role ,getUser());
	}
	
	
	
	/**
	 * 渠道初始化角色
	 * @param role
	 * @return
	 * 新增角色只用管理员用户可以创建系统关键用户角色包括管理员,电核，运营经理，风控经理，电核审查等
	 */
	@SysLog("新增角色")
	@RequestMapping("/saveChannelRoles")
	public Result saveChannelRoles(@RequestBody SysOrgEntity org) {
		return sysRoleService.saveChannelRoles(org ,getUser());
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getRoleById(@RequestBody Long id) {
		return sysRoleService.getRoleById(id);
	}
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@SysLog("修改角色")
	@RequestMapping("/update")
	public Result updateRole(@RequestBody SysRoleEntity role) {
		return sysRoleService.checkAndUpdateRole(role,getUser());
	}
	
	/**
	 * 批量删除
	 * @param id
	 * @return
	 */
	@SysLog("删除角色")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody String[] idAndSign) {
		//return sysRoleService.batchRemove(id);
		return sysRoleService.checkAndBatchRemove(idAndSign,getUser());
	}
	
	/**
	 * 分配权限
	 * @param role
	 * @return
	 */
	@SysLog("操作权限")
	@RequestMapping("/authorize/opt")
	public Result updateRoleOptAuthorization(@RequestBody SysRoleEntity role) {
		return sysRoleService.updateRoleOptAuthorization(role);
	}
	
	/**
	 * 数据权限
	 * @param role
	 * @return
	 */
	@SysLog("数据权限")
	@RequestMapping("/authorize/data")
	public Result updateRoleDataAuthorization(@RequestBody SysRoleEntity role) {
		return sysRoleService.updateRoleDataAuthorization(role);
	}
	
}
