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
	public Result uerRegister(String uname,Integer sex,String birthday,String mobile,String committeeId) {

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
		return bUserService.saveBUser(bUser);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return bUserService.getBUserById(id);
	}
	
	/**
	 * 修改
	 * @param bUser
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody BUserEntity bUser) {
		return bUserService.updateBUser(bUser);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bUserService.batchRemove(id);
	}
	
}
