package cn.cashbang.core.modules.sys.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.cashbang.core.common.constant.SystemConstant.StatusType;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.utils.CollectionUtils;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.modules.sys.dao.SysMenuMapper;
import cn.cashbang.core.modules.sys.dao.SysRoleMapper;
import cn.cashbang.core.modules.sys.dao.SysUserMapper;
import cn.cashbang.core.modules.sys.dao.SysUserRoleMapper;
import cn.cashbang.core.modules.sys.dao.SysUserTokenMapper;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import cn.cashbang.core.modules.sys.entity.SysUserTokenEntity;
import cn.cashbang.core.modules.sys.manager.SysUserManager;
import cn.cashbang.core.modules.sys.oauth2.TokenGenerator;

/**
 * 系统用户
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月11日 上午11:44:21
 */
@Component("sysUserManager")
public class SysUserManagerImpl implements SysUserManager {

	/**
	 * token过期时间，12小时
	 */
	private final static int TOKEN_EXPIRE = 1000 * 60 * 60 * 12;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private SysUserTokenMapper sysUserTokenMapper;
	
	
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    
    @Autowired
	private StringRedisTemplate stringRedisTemplate;
   

	
	
	@Override
	public List<SysUserEntity> listUser(Page<SysUserEntity> page, Query search) {
		return sysUserMapper.listForPage(page, search);
	}

	@Override
	public SysUserEntity getByUserName(String username) {
		return sysUserMapper.getByUserName(username);
	}

	@Override
	public int saveUser(SysUserEntity user) {
		int count = sysUserMapper.save(user);
		if(CollectionUtils.isNotEmpty(user.getRoleIdList())){
			Query query = new Query();
			query.put("userId", user.getUserId());
			query.put("roleIdList", user.getRoleIdList());
			sysUserRoleMapper.save(query);
		}
		return count;
	}

	@Override
	public SysUserEntity getById(Long userId) {
		SysUserEntity user = sysUserMapper.getObjectById(userId);
		user.setRoleIdList(sysUserRoleMapper.listUserRoleId(userId));
		return user;
	}

	@Override
	public int updateUser(SysUserEntity user) {
		int count = sysUserMapper.update(user);
		Long userId = user.getUserId();
		sysUserRoleMapper.remove(userId);
		Query query = new Query();
		query.put("userId", userId);
		query.put("roleIdList", user.getRoleIdList());
		sysUserRoleMapper.save(query);
		return count;
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = sysUserMapper.batchRemove(id);
		sysUserRoleMapper.batchRemoveByUserId(id);
		return count;
	}

	@Override
	public Set<String> listUserPerms(Long userId) {
		List<String> perms = sysMenuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for(String perm : perms) {
			if(StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	@Override
	public Set<String> listUserRoles(Long userId) {
		List<String> roles = sysRoleMapper.listUserRoles(userId);
		Set<String> rolesSet = new HashSet<>();
		for(String role : roles) {
			if(StringUtils.isNotBlank(role)) {
				rolesSet.addAll(Arrays.asList(role.trim().split(",")));
			}
		}
		return rolesSet;
	}

	@Override
	public int updatePswdByUser(Query query) {
		return sysUserMapper.updatePswdByUser(query);
	}

	@Override
	public int updateUserEnable(Long[] id) {
		Query query = new Query();
		query.put("status", StatusType.ENABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return count;
	}

	@Override
	public int updateUserDisable(Long[] id) {
		Query query = new Query();
		query.put("status", StatusType.DISABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return count;
	}

	@Override
	public int updatePswd(SysUserEntity user) {
		return sysUserMapper.updatePswd(user);
	}

	@Override
	public SysUserEntity getUserById(Long userId) {//不包含角色信息
		return sysUserMapper.getObjectById(userId);
	}

	@Override
	public SysUserTokenEntity getByToken(String token) {
		 Boolean REDISTOKEN =SpringContextUtils.getApplicationProperties().getSys().get("saveTokenType").equals("redis");
		if(REDISTOKEN) {
			return getReidsTokenByToken(token);
		}else {
			return sysUserTokenMapper.getByToken(token);
		}
	}

	@Override
	public SysUserTokenEntity saveUserToken(Long userId) {
		 Boolean REDISTOKEN =SpringContextUtils.getApplicationProperties().getSys().get("saveTokenType").equals("redis");
		//生成token
		String token = TokenGenerator.generateValue();
		//当前时间
		Date now = new Date();
		Date gmtExpire = new Date(now.getTime() + TOKEN_EXPIRE);
		SysUserTokenEntity userToken =null;
		if(REDISTOKEN) {
			//userToken= getReidsTokenByUserId(userId);
			userToken = new SysUserTokenEntity();
			userToken.setUserId(userId);
			userToken.setToken(token);
			userToken.setGmtExpire(gmtExpire);
			userToken.setGmtModified(now);
			saveReidsToken(userToken);
		}else {
			userToken= sysUserTokenMapper.getByUserId(userId);
			if(userToken == null) {
				userToken = new SysUserTokenEntity();
				userToken.setUserId(userId);
				userToken.setToken(token);
				userToken.setGmtExpire(gmtExpire);
				userToken.setGmtModified(now);
				sysUserTokenMapper.save(userToken);
			} else {
				userToken.setToken(token);
				userToken.setGmtExpire(gmtExpire);
				userToken.setGmtModified(now);
				sysUserTokenMapper.update(userToken);
			}
		}
		
		
		return userToken;
	}

	@Override
	public int updateUserToken(Long userId) {
		 Boolean REDISTOKEN =SpringContextUtils.getApplicationProperties().getSys().get("saveTokenType").equals("redis");
		String token = TokenGenerator.generateValue();
		SysUserTokenEntity userToken = new SysUserTokenEntity();
		if(REDISTOKEN) {
			//userToken= getReidsTokenByUserId(userId);
			Date now = new Date();
			Date gmtExpire = new Date(now.getTime() + TOKEN_EXPIRE);
			userToken.setUserId(userId);
			userToken.setToken(token);
			userToken.setGmtExpire(gmtExpire);
			userToken.setGmtModified(now);
			saveReidsToken(userToken);
			return 1;
		}else {
			userToken.setUserId(userId);
			userToken.setToken(token);
			return sysUserTokenMapper.update(userToken);
		}
		
	}

	@Override
	public List<SysUserEntity> listByRole(Map<String, Object> role) {
		return this.sysUserMapper.listByRole(role);
	}
	
	/**
	 * 根据token 查token信息
	 * @param userId
	 * @return
	 */
	private SysUserTokenEntity getReidsTokenByToken(String token ) {
		SysUserTokenEntity res=null;//与mysql统一这里为null
	    ValueOperations<String, String> sops=redisTemplate.opsForValue();
	 	String userToken = sops.get("token_"+token);
	 	try {
	 		if(userToken!=null) {
			 	res=JSONObject.parseObject(userToken, SysUserTokenEntity.class);
	 		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}
	/**
	 * 根据userId 查token信息
	 * @param userId
	 * @return
	 */
	private SysUserTokenEntity getReidsTokenByUserId(Long userId) {
		SysUserTokenEntity res=null;//与mysql统一这里为null
	    ValueOperations<String, String> sops=redisTemplate.opsForValue();
	 	String userToken = sops.get("token_userId_"+userId);
	 	try {
	 		if(userToken!=null) {
			 	res=JSONObject.parseObject(userToken, SysUserTokenEntity.class);
	 		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}
	
	/**
	 * 根据userId 查token信息
	 * @param userId
	 * @return
	 */
	private  void saveReidsToken(SysUserTokenEntity userToken) {
	 	try {
	 		 String tokenMsg=JSONObject.toJSONString(userToken);
	 		 ValueOperations<String, String> sops=redisTemplate.opsForValue();
	 		deleteReidsTokenByUserId(userToken.getUserId());
	 		 sops.set("token_userId_"+userToken.getUserId(),tokenMsg,TOKEN_EXPIRE,TimeUnit.MILLISECONDS);
	 		 sops.set("token_"+userToken.getToken(),tokenMsg,TOKEN_EXPIRE,TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void deleteReidsTokenByUserId(Long userId) {
		SysUserTokenEntity userToken=getReidsTokenByUserId(userId);
		if(userToken!=null&&userToken.getToken()!=null) {
			 stringRedisTemplate.delete("token_userId_"+userId);
			 stringRedisTemplate.delete("token_"+userToken.getToken());
		}
	}

}
