package cn.cashbang.core.modules.sys.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.sys.entity.SysLogEntity;

/**
 * 系统日志
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月14日 下午8:43:06
 */
public interface SysLogManager {

	void saveLog(SysLogEntity log);
	
	List<SysLogEntity> listLog(Page<SysLogEntity> page, Query query);
	
	int batchRemove(Long[] id);
	
	int batchRemoveAll();
	
}
