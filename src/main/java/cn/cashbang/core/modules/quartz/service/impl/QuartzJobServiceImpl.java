package cn.cashbang.core.modules.quartz.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.constant.SystemConstant.ScheduleStatus;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.quartz.entity.QuartzJobEntity;
import cn.cashbang.core.modules.quartz.manager.QuartzJobManager;
import cn.cashbang.core.modules.quartz.service.QuartzJobService;
import cn.cashbang.core.modules.quartz.utils.ScheduleUtils;

/**
 * 定时任务
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月20日 下午11:49:18
 */
@Service("quartzJobService")
public class QuartzJobServiceImpl implements QuartzJobService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private QuartzJobManager quartzJobManager;

	@Value("${application-properties.quartz.autoStartup}")
	boolean autoStart;

	/**
	 * 项目启动，初始化任务
	 */
	@PostConstruct
	public void init() {

		if (autoStart == false){
			logger.info("定时任务关闭.不初始化相关调度数据");
			return;
		}

		logger.info("定时任务开启.初始化相关调度数据");

		List<QuartzJobEntity> jobList = quartzJobManager.listNormalJob();
		for(QuartzJobEntity job : jobList) {
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(job.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(job);
            }else {
                ScheduleUtils.updateScheduleJob(job);
            }
		}
	}

	@Override
	public Page<QuartzJobEntity> list(Map<String, Object> params) {
		Query query = new Query(params);
		Page<QuartzJobEntity> page = new Page<>(query);
		quartzJobManager.listForPage(page, query);
		return page;
	}

	@Override
	public Result saveQuartzJob(QuartzJobEntity job) {
		job.setStatus(ScheduleStatus.NORMAL.getValue());
		int count = quartzJobManager.saveQuartzJob(job);
		ScheduleUtils.createScheduleJob(job);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getQuartzJobById(Long jobId) {
		QuartzJobEntity job = quartzJobManager.getQuartzJobById(jobId);
		return CommonUtils.msg(job);
	}

	@Override
	public Result updateQuartzJob(QuartzJobEntity job) {
		int count = quartzJobManager.updateQuartzJob(job);
		ScheduleUtils.updateScheduleJob(job);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemoveQuartzJob(Long[] id) {
		for(Long jobId : id) {
			ScheduleUtils.deleteScheduleJob(jobId);
		}
		int count = quartzJobManager.batchRemoveQuartzJob(id);
		return CommonUtils.msg(id, count);
	}
	
	@Override
	public Result run(Long[] id) {
		for(Long jobId : id) {
			ScheduleUtils.run(quartzJobManager.getQuartzJobById(jobId));
		}
		return CommonUtils.msg(1);
	}
	
	@Override
	public Result pause(Long[] id) {
		for(Long jobId : id) {
			ScheduleUtils.pauseJob(jobId);
		}
		int count = quartzJobManager.batchUpdate(id, ScheduleStatus.PAUSE.getValue());
		return CommonUtils.msg(id, count);
	}
	
	@Override
	public Result resume(Long[] id) {
		for(Long jobId : id) {
			ScheduleUtils.resumeJob(jobId);
		}
		int count = quartzJobManager.batchUpdate(id, ScheduleStatus.NORMAL.getValue());
		return CommonUtils.msg(id, count);
	}

}
