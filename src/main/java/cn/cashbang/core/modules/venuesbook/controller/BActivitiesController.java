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
import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.service.BActivitiesService;

/**
 * 活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:35
 */
@RestController
@RequestMapping("/venuesbook/activities")
public class BActivitiesController extends AbstractController {
	
	@Autowired
	private BActivitiesService bActivitiesService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BActivitiesEntity> list(@RequestBody Map<String, Object> params) {
		return bActivitiesService.listBActivities(params);
	}
		
	/**
	 * 新增
	 * @param bActivities
	 * @return
	 */
	@SysLog("新增活动信息表")
	@RequestMapping("/save")
	public Result save(@RequestBody BActivitiesEntity bActivities) {
		return bActivitiesService.saveBActivities(bActivities);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return bActivitiesService.getBActivitiesById(id);
	}
	
	/**
	 * 修改
	 * @param bActivities
	 * @return
	 */
	@SysLog("修改活动信息表")
	@RequestMapping("/update")
	public Result update(@RequestBody BActivitiesEntity bActivities) {
		return bActivitiesService.updateBActivities(bActivities);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除活动信息表")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bActivitiesService.batchRemove(id);
	}
	
}
