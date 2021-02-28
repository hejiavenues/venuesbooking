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
import cn.cashbang.core.modules.venuesbook.entity.BCommunityActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.service.BCommunityActivitiesService;

/**
 * 社区活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月27日 PM9:46:01
 */
@RestController
@RequestMapping("/venuesbook/comactivity")
public class BCommunityActivitiesController extends AbstractController {
	
	@Autowired
	private BCommunityActivitiesService bCommunityActivitiesService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BCommunityActivitiesEntity> list(@RequestBody Map<String, Object> params) {
		return bCommunityActivitiesService.listBCommunityActivities(params);
	}
		
	/**
	 * 新增
	 * @param bCommunityActivities
	 * @return
	 */
	@SysLog("新增社区活动信息表")
	@RequestMapping("/save")
	public Result save(@RequestBody BCommunityActivitiesEntity bCommunityActivities) {
		return bCommunityActivitiesService.saveBCommunityActivities(bCommunityActivities);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody String id) {
		return bCommunityActivitiesService.getBCommunityActivitiesById(id);
	}
	
	/**
	 * 修改
	 * @param bCommunityActivities
	 * @return
	 */
	@SysLog("修改社区活动信息表")
	@RequestMapping("/update")
	public Result update(@RequestBody BCommunityActivitiesEntity bCommunityActivities) {
		return bCommunityActivitiesService.updateBCommunityActivities(bCommunityActivities);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除社区活动信息表")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bCommunityActivitiesService.batchRemove(id);
	}
	
}
