package cn.cashbang.core.modules.query.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.query.entity.PayOrderEntity;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月30日 下午3:58:04
 */
public interface PayOrderService {

	Page<PayOrderEntity> listPayOrder(Map<String, Object> params);
	
	Result savePayOrder(PayOrderEntity payOrder);
	
	Result getPayOrderById(Long id);
	
	Result updatePayOrder(PayOrderEntity payOrder);
	
	Result batchRemove(Long[] id);
	
	 int retryLoan(String id);
	
}
