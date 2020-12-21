package cn.cashbang.core.modules.quartz.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.quartz.entity.QuartzJobLogEntity;

/**
 * 定时任务日志
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月20日 下午11:06:56
 */
public interface QuartzJobLogManager {

	List<QuartzJobLogEntity> listForPage(Page<QuartzJobLogEntity> page, Query query);
	
	int saveQuartzJobLog(QuartzJobLogEntity log);
	
	int batchRemove(Long[] id);
	
	int batchRemoveAll();
	
}
