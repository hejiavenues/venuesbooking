package cn.cashbang.core.modules.venuesbook.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;
import cn.cashbang.core.modules.venuesbook.service.BUserService;

/**
 * 用户信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:02
 */
@RestController
@RequestMapping("/venuesbook/user")
public class BUserController extends AbstractController {
	
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
	 * 新增
	 * @param bUser
	 * @return
	 */
	@SysLog("新增用户信息表")
	@RequestMapping("/save")
	public Result save(@RequestBody BUserEntity bUser) {
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
	@SysLog("修改用户信息表")
	@RequestMapping("/update")
	public Result update(@RequestBody BUserEntity bUser) {
		return bUserService.updateBUser(bUser);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除用户信息表")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bUserService.batchRemove(id);
	}
	
}
