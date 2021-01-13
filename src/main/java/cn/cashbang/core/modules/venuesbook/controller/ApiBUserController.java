package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;
import cn.cashbang.core.modules.venuesbook.service.BUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:02
 */
@RestController
@RequestMapping("/api/user")
public class ApiBUserController extends AbstractController {
	
	@Autowired
	private BUserService bUserService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BUserEntity> list(@RequestBody Map<String, Object> params) {
		return bUserService.listBUser(params);
	}
		
	/**
	 * 注册
	 * @param
	 * @return
	 */
	@RequestMapping("/uerRegister")
	public Result uerRegister(String uname,Integer sex,String birthday,String mobile,String committeeId,
	String openId){

		System.out.println("-----------------"+uname);

		BUserEntity bUser = new BUserEntity();
		bUser.setBirthday(birthday);
		bUser.setCommitteeId(committeeId);
		bUser.setSex(sex);
		bUser.setUname(uname);
		bUser.setMobile(mobile);
		bUser.setUserRole(1);
		String uuid = CommonUtils.createUUID();
		bUser.setUid(uuid);
		bUser.setOpenId(openId);
		return bUserService.saveBUser(bUser);
	}

	@RequestMapping("/uerLogin")
	public Result uerLogin(String code){

		return bUserService.loginUser(code);
	}
	
	/**
	 * 根据id查询用户详情
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getUserById")
	public Result getById(String userId) {

		return bUserService.getBUserById(userId);
	}
	
	/**
	 * 修改用户信息
	 * @param birthday
	 * @return
	 */
	@RequestMapping("/updateUser")
	public Result updateUser(String uid,String uname,Integer sex,String birthday){

		BUserEntity bUser = new BUserEntity();
		bUser.setBirthday(birthday);
		bUser.setUid(uid);
		bUser.setSex(sex);
		bUser.setUname(uname);

		return bUserService.updateBUser(bUser);
	}
	
}
