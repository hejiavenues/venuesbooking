package cn.cashbang.core.modules.query.service;

import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.query.bo.OrderDetailQueryBO;

/**
 * 客户申请单信息
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年3月23日 下午6:38:56
 */
public interface OrderQueryService {

	Result unlock(OrderDetailQueryBO order);

}
