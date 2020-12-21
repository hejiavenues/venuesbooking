package cn.cashbang.core.modules.quartz.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.quartz.entity.QuartzJobLogEntity;
import cn.cashbang.core.modules.quartz.manager.QuartzJobLogManager;
import cn.cashbang.core.modules.quartz.service.QuartzJobLogService;

/**
 * 定时任务日志
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月21日 上午11:18:22
 */
@Service("quartzJobLogService")
public class QuartzJobLogServiceImpl implements QuartzJobLogService {

	@Autowired
	private QuartzJobLogManager quartzJobLogManager;
	
	@Override
	public Page<QuartzJobLogEntity> listForPage(Map<String, Object> params) {
		Query query = new Query(params);
		Page<QuartzJobLogEntity> page = new Page<>(query);
		quartzJobLogManager.listForPage(page, query);
		return page;
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = quartzJobLogManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public Result batchRemoveAll() {
		int count = quartzJobLogManager.batchRemoveAll();
		return CommonUtils.msg(count);
	}


}
