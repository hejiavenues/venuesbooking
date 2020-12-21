package cn.cashbang.core.modules.quartz.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.quartz.entity.QuartzJobEntity;
import cn.cashbang.core.modules.quartz.service.QuartzJobService;

/**
 * 定时任务
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月20日 下午11:51:24
 */
@RestController
@RequestMapping("/quartz/job")
public class QuartzJobController {

	@Autowired
	private QuartzJobService quartzJobService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<QuartzJobEntity> list(@RequestBody Map<String, Object> params) {
		return quartzJobService.list(params);
	}
	
	/**
	 * 新增任务
	 * @param job
	 * @return
	 */
	@SysLog("新增定时任务")
	@RequestMapping("/save")
	public Result save(@RequestBody QuartzJobEntity job) {
		return quartzJobService.saveQuartzJob(job);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result info(@RequestBody Long id) {
		return quartzJobService.getQuartzJobById(id);
	}
	
	/**
	 * 修改任务
	 * @param job
	 * @return
	 */
	@SysLog("修改定时任务")
	@RequestMapping("/update")
	public Result update(@RequestBody QuartzJobEntity job) {
		return quartzJobService.updateQuartzJob(job);
	}
	
	/**
	 * 删除定时任务
	 * @param id
	 * @return
	 */
	@SysLog("删除定时任务")
	@RequestMapping("/remove")
	public Result remove(@RequestBody Long[] id) {
		return quartzJobService.batchRemoveQuartzJob(id);
	}
	
	/**
	 * 立即运行
	 * @param id
	 * @return
	 */
	@SysLog("立即运行定时任务")
	@RequestMapping("/run")
	public Result run(@RequestBody Long[] id) {
		return quartzJobService.run(id);
	}
	
	/**
	 * 暂停任务
	 * @param id
	 * @return
	 */
	@SysLog("暂停定时运行")
	@RequestMapping("/disable")
	public Result pause(@RequestBody Long[] id) {
		return quartzJobService.pause(id);
	}
	
	/**
	 * 启用任务
	 * @param id
	 * @return
	 */
	@SysLog("启用定时任务")
	@RequestMapping("/enable")
	public Result resume(@RequestBody Long[] id) {
		return quartzJobService.resume(id);
	}
	
}
