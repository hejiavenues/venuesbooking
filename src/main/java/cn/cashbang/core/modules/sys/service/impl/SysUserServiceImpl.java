package cn.cashbang.core.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cashbang.core.common.constant.SystemMsg;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.common.utils.MD5Utils;
import cn.cashbang.core.common.utils.ShiroUtils;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;
import cn.cashbang.core.modules.sys.entity.SysRoleEntity;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import cn.cashbang.core.modules.sys.entity.SysUserTokenEntity;
import cn.cashbang.core.modules.sys.manager.SysOrgManager;
import cn.cashbang.core.modules.sys.manager.SysRoleManager;
import cn.cashbang.core.modules.sys.manager.SysUserManager;
import cn.cashbang.core.modules.sys.service.SysUserService;

/**
 * 系统用户
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月11日 上午11:47:17
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserManager sysUserManager;
	
	@Autowired
	private SysOrgManager sysOrgManager;
	
	@Autowired
	private SysRoleManager sysRoleManager;
	
	
	@Override
	public Page<SysUserEntity> listUser(Map<String, Object> params) {
		Query form = new Query(params);
		Page<SysUserEntity> page = new Page<>(form);
		sysUserManager.listUser(page, form);
		return page;
	}

	@Override
	@Transactional("transactionManager")
	public Result saveUser(SysUserEntity user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		int count = sysUserManager.saveUser(user);
		return CommonUtils.msg(count);
	}
	
	@Override
	@Transactional("transactionManager")
	public Result saveChannelUser(String channel,String channelName,Long userIdCreate) {
		
		SysUserEntity user =new SysUserEntity();
		//创建管理员
		
		SysUserEntity check=sysUserManager.getByUserName(channel+"_"+SystemMsg.sysUser.CHANNELADMIN);
		SysOrgEntity channelOrg=sysOrgManager.getByCode(channel);
		if(check==null) {
			user.setUserIdCreate(userIdCreate);
			user.setChannel(channel);
			user.setUsername(channel+"_"+SystemMsg.sysUser.CHANNELADMIN);
			user.setChnName(channelName+"_"+SystemMsg.sysUser.CHANNELADMINNAME);
			user.setPassword(MD5Utils.encrypt(user.getUsername(), SystemMsg.sysUser.PASSWORD));
			user.setOrgId(channelOrg.getOrgId());
			user.setStatus(SystemMsg.sysUser.STATUS);
			
			
			 List<Long> roleIdList =new ArrayList<Long>();
			 Long saasOrgId =sysOrgManager.getByCode(SystemMsg.sysOrg.SAAS).getOrgId();
			 

			 SysRoleEntity adminRole=new SysRoleEntity();
			 adminRole.setRoleSign(channel+SystemMsg.sysRole.CHANNELADMIN);
			 adminRole.setOrgId(saasOrgId);
			 roleIdList.add(sysRoleManager.selectByRole(adminRole).getRoleId());
			 user.setRoleIdList(roleIdList);
			 int count = sysUserManager.saveUser(user);
			 return CommonUtils.msg(count);

		}
		
		return Result.ok(SystemMsg.sysUser.SUCCESSMSG);
		
	}
	
	
	@Override
	public Result getUserById(Long userId) {
		SysUserEntity user = sysUserManager.getById(userId);
		return CommonUtils.msg(user);
	}

	@Override
	public Result updateUser(SysUserEntity user) {
		int count = sysUserManager.updateUser(user);
		return CommonUtils.msg(count);
	}

	@Override
	@Transactional("transactionManager")
	public Result batchRemove(Long[] id) {
		int count = sysUserManager.batchRemove(id);
		return CommonUtils.msg(count);
	}

	@Override
	public Result listUserPerms(Long userId) {
		Set<String> perms = sysUserManager.listUserPerms(userId);
		return CommonUtils.msgNotCheckNull(perms);
	}

	@Override
	public Result updatePswdByUser(SysUserEntity user) {
		String username = user.getUsername();
		String pswd = user.getPassword();
		String newPswd = user.getEmail();
		pswd = MD5Utils.encrypt(username, pswd);
		newPswd = MD5Utils.encrypt(username, newPswd);
		Query query = new Query();
		query.put("userId", user.getUserId());
		query.put("pswd", pswd);
		query.put("newPswd", newPswd);
		int count = sysUserManager.updatePswdByUser(query);
		if(!CommonUtils.isIntThanZero(count)) {
			return Result.error("原密码错误");
		}
		return CommonUtils.msg(count);
	}

	@Override
	public Result updateUserEnable(Long[] id) {
		int count = sysUserManager.updateUserEnable(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public Result updateUserDisable(Long[] id) {
		int count = sysUserManager.updateUserDisable(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public Result updatePswd(SysUserEntity user) {
		SysUserEntity currUser = sysUserManager.getUserById(user.getUserId());
		user.setPassword(MD5Utils.encrypt(currUser.getUsername(), user.getPassword()));
		int count = sysUserManager.updatePswd(user);
		return CommonUtils.msg(count);
	}

	@Override
	public Result saveUserToken(Long userId) {
		SysUserTokenEntity token = sysUserManager.saveUserToken(userId);
		Result r = Result.ok().put("token", token.getToken()).put("expire", token.getGmtExpire());
		return r;
	}

	@Override
	public Result updateUserToken(Long userId) {
		int count = sysUserManager.updateUserToken(userId);
		return CommonUtils.msg(count);
	}

	@Override
	public SysUserEntity getByUserName(String username) {
		return sysUserManager.getByUserName(username);
	}

	@Override
	public Result listByRole(Map<String, Object> role) {
		List<SysUserEntity> users = this.sysUserManager.listByRole(role);
		return CommonUtils.msgNotCheckNull(users);
	}
	@Override
	public boolean hasAdmin() {
			SysUserEntity user = ShiroUtils.getUserEntity();
			return user.getHasAdmin()>0;
	}
}
