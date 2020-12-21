package cn.cashbang.core.modules.quartz.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.quartz.entity.QuartzJobEntity;

/**
 * 定时任务
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月20日 下午11:48:32
 */
public interface QuartzJobService {
	
	Page<QuartzJobEntity> list(Map<String, Object> params);
	
	Result saveQuartzJob(QuartzJobEntity job);
	
	Result getQuartzJobById(Long jobId);
	
	Result updateQuartzJob(QuartzJobEntity job);
	
	Result batchRemoveQuartzJob(Long[] id);
	
	Result run(Long[] id);
	
	Result pause(Long[] id);
	
	Result resume(Long[] id);
	
}
