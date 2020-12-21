package cn.cashbang.core.modules.query.manager;

import cn.cashbang.core.modules.query.bo.OrderDetailQueryBO;

/**
 * 客户订单信息
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年3月23日 下午6:38:56
 */
public interface OrderQueryManager {
	int unlock(OrderDetailQueryBO order);
}
