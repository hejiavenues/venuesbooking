package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.common.utils.StringUtils;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;
import cn.cashbang.core.modules.venuesbook.service.BUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
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
	public Map<String, Object> uerRegister(String uname,Integer sex,String birthday,String mobile,
										   String committeeId, String openId,String iconUrl,Integer userRole){

		System.out.println("-----------------"+uname);

		Map<String, Object> result = new HashMap<>();

		BUserEntity bUser = new BUserEntity();
		bUser.setBirthday(birthday);
		bUser.setCommitteeId(committeeId);
		bUser.setSex(sex);
		bUser.setUname(uname);
		bUser.setMobile(mobile);
		if(userRole!=null){
            bUser.setUserRole(userRole);
        } else{
            bUser.setUserRole(1);
        }

		bUser.setStatus(1);
		String uuid = CommonUtils.createUUID();
		bUser.setUid(uuid);
		bUser.setOpenId(openId);
		bUser.setCreateTime(new Date());
		bUser.setIconUrl(iconUrl);
		Result r1 =   bUserService.saveBUser(bUser);

		if(r1.get("code").toString().equals("0")){

			result.put("code",0);
			result.put("msg","注册成功！");
			result.put("rows",bUser);
		}
		else{
			result.put("code",-1);
			result.put("msg","注册失败！");
		}

		return result;
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
	public Result updateUser(String uid,String uname,Integer sex,String birthday
			,String iconUrl){

		BUserEntity bUser = new BUserEntity();
		bUser.setBirthday(birthday);
		bUser.setUid(uid);
		bUser.setSex(sex);
		bUser.setUname(uname);

		if(StringUtils.isNotBlank(iconUrl)){
			bUser.setIconUrl(iconUrl);
		}

		return bUserService.updateBUser(bUser);
	}
	
}
