package cn.cashbang.core.modules.sys.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.constant.SystemConstant;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.IpUtils;
import cn.cashbang.core.common.utils.MD5Utils;
import cn.cashbang.core.common.utils.ShiroUtils;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.modules.sys.dao.SysWhiteIpMapper;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import cn.cashbang.core.modules.sys.entity.SysWhiteIpEntity;
import cn.cashbang.core.modules.sys.oauth2.TokenGenerator;
import cn.cashbang.core.modules.sys.service.SysUserService;
import cn.cashbang.core.modules.venuesbook.rsa.RSAEncrypt;

/**
 * 用户controller
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 下午2:48:50
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController extends AbstractController {
	
	@Autowired
	private SysUserService sysUserService;
	
	
	@Autowired
	private SysWhiteIpMapper sysWhiteIpMapper;
	
	@Resource
    private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 登录
	 * @throws Exception 
	 */
	@SysLog("登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result login(String username, String password, String requestId,HttpServletRequest request)throws Exception {
		
		/*if(StringUtils.isEmpty(requestId)) {
			return Result.error("请求码为空，请刷新页面后重试");
		}
		String privateKey = redisTemplate.opsForValue().get(requestId);
		if(StringUtils.isEmpty(privateKey)) {
			return Result.error("请求码失效，请刷新页面后重试");
		}
		
		String newPassWord = RSAEncrypt.decrypt(password, privateKey);*/
		String newPassWord = password;
		SysUserEntity user = sysUserService.getByUserName(username);
		newPassWord = MD5Utils.encrypt(username, newPassWord);
		
		if(user == null || !user.getPassword().equals(newPassWord)) {
			return Result.error("用户名或密码错误");
		}
		
		 String IPRestriction =SpringContextUtils.getApplicationProperties().getSys().get("IPRestriction");

		if(IPRestriction.equals("true")) {
			if(!username.equals(SystemConstant.SUPER_ADMIN_NAME)
					&&!username.equals(SystemConstant.SUPER_ADMIN_NAME_1)
					&&!username.equals(SystemConstant.SUPER_ADMIN_NAME_2)) {
				String ip=IpUtils.getIpAddr(request);
				List<SysWhiteIpEntity> list =sysWhiteIpMapper.getByUserAndIp(user.getUserId().toString(), ip);
				if(list==null ||list.size()==0) {
					return Result.error("当前登陆受到ip限制");
				}
			}
		}
	
		
		if(user == null || !user.getPassword().equals(newPassWord)) {
			return Result.error("用户名或密码错误");
		}
		
		if(user.getStatus() == 0) {
			return Result.error("账号已被锁定,请联系管理员");
		}
	    
		return sysUserService.saveUserToken(user.getUserId());
	}
	
	/**
	 * 登录
	 * @throws NoSuchAlgorithmException 
	 */
	@SysLog("获取登录时的rsa公钥")
	@RequestMapping(value = "/getKey", method = RequestMethod.GET)
	public Result getkey(String abc,HttpServletRequest request)throws IOException, NoSuchAlgorithmException {
		JSONObject js = new JSONObject();
		String requestId = TokenGenerator.generateValue();
		js.put("requestId", requestId);
		RSAEncrypt.genKeyPair();
		js.put("rsaPublicKey", RSAEncrypt.keyMap.get(0));
		redisTemplate.opsForValue().set(requestId, RSAEncrypt.keyMap.get(1), 5l, TimeUnit.MINUTES);
		return Result.ok(js);
	}
	
	/**
	 * 退出
	 */
	@SysLog("退出系统")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public Result logout() {
		Result r = sysUserService.updateUserToken(getUserId());
		ShiroUtils.logout();
		return r;
	}
	
	@RequestMapping(value = "/international")
	public Result international( ){
		String it=SpringContextUtils.getApplicationProperties().getSys().get("international");
		return Result.ok(it);
	}
	
	
	@RequestMapping(value = "/sysName")
	public Result sysName(){
		String it=SpringContextUtils.getApplicationProperties().getSys().get("sysName");
		return Result.ok(it);
	}
	
	
	@RequestMapping(value = "/isApproval")
	public Result isApproval(){
		String mainType=SpringContextUtils.getApplicationProperties().getSys().get("mainType");
		return Result.oko(mainType.equals("approval"));
	}
	
}
