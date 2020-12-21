package cn.cashbang.core.modules.query.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.query.entity.PayLogEntity;

/**
 * 订单日志
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月30日 下午3:54:19
 */
public interface PayLogManager {

	List<PayLogEntity> listPayLog(Page<PayLogEntity> page, Query search);
	
	int savePayLog(PayLogEntity payLog);
	
	PayLogEntity getPayLogById(Long id);
	
	int updatePayLog(PayLogEntity payLog);
	
	int batchRemove(Long[] id);
	
}
