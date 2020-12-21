package cn.cashbang.core.modules.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.constant.SystemConstant;
import cn.cashbang.core.common.constant.SystemMsg;
import cn.cashbang.core.common.constant.SystemMsg.sysOrg;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import cn.cashbang.core.modules.sys.service.SysUserService;

/**
 * 系统用户
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 下午9:04:59
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 用户列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<SysUserEntity> list(@RequestBody Map<String, Object> params) {
		/*if(getUserId() != SystemConstant.SUPER_ADMIN) {
			params.put("userIdCreate", getUserId());
		}*/
		SysUserEntity user= getUser();
		
		
		//只有Admin权限才能看Admin权限的人
		if(!(user.getHasAdmin()>0)) {
			//不可看admin
			params.put("noAdmin",SystemMsg.sysRole.ADMIN);
			
			//只能看自己的平台 ,平台账号可channel相当于''
			params.put("channel",user.getChannel());
			
		}
		
		params.put("orgIdPath",super.getOrgIdPath());
		return sysUserService.listUser(params);
	}
	
	/**
	 * 用户列表暂时没有用到
	 * @param params
	 * @return
	 */
	@RequestMapping("/syslist")
	public Page<SysUserEntity> syslist(@RequestBody Map<String, Object> params) {
		/*if(getUserId() != SystemConstant.SUPER_ADMIN) {
			params.put("userIdCreate", getUserId());
		}*/
		params.put("code", SystemMsg.sysOrg.SAAS);
		params.put("orgIdPath",super.getOrgIdPath());
		return sysUserService.listUser(params);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public Result info(){
		
		SysUserEntity user=getUser();
		return Result.ok().put("user", user);
	}
	
	/**
	 * 用户权限
	 * @return
	 */
	@RequestMapping("/perms")
	public Result listUserPerms() {
		return sysUserService.listUserPerms(getUserId());
	}
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@SysLog("新增用户")
	@RequestMapping("/save")
	public Result save(@RequestBody SysUserEntity user) {
		user.setUserIdCreate(getUserId());
		return sysUserService.saveUser(user);
	}
	/**
	 * 初始化渠道用户
	 * @param user
	 * @return
	 */
	@SysLog("初始化渠道用户")
	@RequestMapping("/saveChannelUser")
	public Result saveChannelUser(@RequestBody SysOrgEntity org) {
		return sysUserService.saveChannelUser(org.getCode(),org.getName(),getUserId());
	}
	/**
	 * 根据id查询详情
	 * @param userId
	 * @return
	 */
	@RequestMapping("/infoUser")
	public Result getById(@RequestBody Long userId) {
		return sysUserService.getUserById(userId);
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@SysLog("修改用户")
	@RequestMapping("/update")
	public Result update(@RequestBody SysUserEntity user) {
		return sysUserService.updateUser(user);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除用户")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return sysUserService.batchRemove(id);
	}
	
	/**
	 * 用户修改密码
	 * @param pswd
	 * @param newPswd
	 * @return
	 */
	@SysLog("修改密码")
	@RequestMapping("/updatePswd")
	public Result updatePswdByUser(String pswd, String newPswd) {
		SysUserEntity user = getUser();
		user.setPassword(pswd);//原密码
		user.setEmail(newPswd);//邮箱临时存储新密码
		return sysUserService.updatePswdByUser(user);
	}
	
	/**
	 * 启用账户
	 * @param id
	 * @return
	 */
	@SysLog("启用账户")
	@RequestMapping("/enable")
	public Result updateUserEnable(@RequestBody Long[] id) {
		return sysUserService.updateUserEnable(id);
	}
	
	/**
	 * 禁用账户
	 * @param id
	 * @return
	 */
	@SysLog("禁用账户")
	@RequestMapping("/disable")
	public Result updateUserDisable(@RequestBody Long[] id) {
		return sysUserService.updateUserDisable(id);
	}
	
	/**
	 * 重置密码
	 * @param user
	 * @return
	 */
	@SysLog("重置密码")
	@RequestMapping("/reset")
	public Result updatePswd(@RequestBody SysUserEntity user) {
		return sysUserService.updatePswd(user);
	}
	
	/**
	 * 
	 * 查询特定角色的用户
	 * 
	 * @return
	 */
	@RequestMapping("/listByRole")
	public Result listByRole(@RequestBody  Map<String, Object> role  ){
		return sysUserService.listByRole(role);
	}
	
	/**
	 * 
	 * 查询特定角色的用户
	 * 
	 * @return
	 */
	@RequestMapping("/nowDataBase")
	public Result nowDataBase( ){
		String bs=SpringContextUtils.getApplicationProperties().getSys().get("bsManage");
		String api=SpringContextUtils.getApplicationProperties().getSys().get("apiMaster");
		String evn=SpringContextUtils.getApplicationProperties().getSys().get("evn");
		JSONObject res=new JSONObject();
		res.put("bs", bs);
		res.put("api", api);
		res.put("evn", evn);
		return Result.ok(res);
	}
	
	
	
	
}
