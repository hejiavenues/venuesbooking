package cn.cashbang.core.modules.query.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.query.entity.PayOrderEntity;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月30日 下午3:58:04
 */
public interface PayOrderManager {

	List<PayOrderEntity> listPayOrder(Page<PayOrderEntity> page, Query search);
	
	int savePayOrder(PayOrderEntity payOrder);
	
	PayOrderEntity getPayOrderById(Long id);
	
	int updatePayOrder(PayOrderEntity payOrder);
	
	int batchRemove(Long[] id);
	
}
