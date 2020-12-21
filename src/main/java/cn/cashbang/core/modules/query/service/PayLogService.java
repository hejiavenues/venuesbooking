package cn.cashbang.core.modules.query.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.query.entity.PayLogEntity;

/**
 * 订单日志
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月30日 下午3:54:19
 */
public interface PayLogService {

	Page<PayLogEntity> listPayLog(Map<String, Object> params);
	
	Result savePayLog(PayLogEntity payLog);
	
	Result getPayLogById(Long id);
	
	Result updatePayLog(PayLogEntity payLog);
	
	Result batchRemove(Long[] id);
	
}
