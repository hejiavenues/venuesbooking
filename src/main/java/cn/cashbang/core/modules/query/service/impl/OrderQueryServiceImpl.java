package cn.cashbang.core.modules.query.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.query.bo.OrderDetailQueryBO;
import cn.cashbang.core.modules.query.manager.OrderQueryManager;
import cn.cashbang.core.modules.query.service.OrderQueryService;

/**
 * 客户申请单信息
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年3月23日 下午6:38:56
 */
@Service("orderQueryService")
public class OrderQueryServiceImpl implements OrderQueryService {

	@Autowired
	private OrderQueryManager orderQueryManager;
	
	
	@Override
	public Result unlock(OrderDetailQueryBO order) {
		int count = this.orderQueryManager.unlock(order);
		return CommonUtils.msg(count);
	}

}
