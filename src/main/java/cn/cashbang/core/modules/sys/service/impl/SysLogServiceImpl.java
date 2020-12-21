package cn.cashbang.core.modules.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.entity.SysLogEntity;
import cn.cashbang.core.modules.sys.manager.SysLogManager;
import cn.cashbang.core.modules.sys.service.SysLogService;

/**
 * 系统日志
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月14日 下午8:41:29
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogManager sysLogManager;
	
	@Override
	public Page<SysLogEntity> listLog(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysLogEntity> page = new Page<>(query);
		sysLogManager.listLog(page, query);
		return page;
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = sysLogManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public Result batchRemoveAll() {
		int count = sysLogManager.batchRemoveAll();
		return CommonUtils.msg(count);
	}

}
