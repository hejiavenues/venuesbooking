package cn.cashbang.core.modules.sys.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.entity.SysLogEntity;

/**
 * 系统日志
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月14日 下午8:40:52
 */
public interface SysLogService {

	Page<SysLogEntity> listLog(Map<String, Object> params);
	
	Result batchRemove(Long[] id);
	
	Result batchRemoveAll();
	
}
