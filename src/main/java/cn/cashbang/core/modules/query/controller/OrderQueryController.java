package cn.cashbang.core.modules.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.query.bo.OrderDetailQueryBO;
import cn.cashbang.core.modules.query.service.OrderQueryService;
import cn.cashbang.core.modules.sys.controller.AbstractController;

/**
 * 客户信息
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年3月23日 下午6:38:56
 */
@RestController
@RequestMapping("/query/order")
public class OrderQueryController extends AbstractController {
	
	@Autowired
	private OrderQueryService orderQueryService;
	
	/**
	 * 交易单解锁
	 * @param orderDetailQueryBO
	 * @return
	 */
	@SysLog("交易单解锁")
	@RequestMapping("/unlock")
	public Result unlock(@RequestBody OrderDetailQueryBO orderDetailQueryBO) {
		return this.orderQueryService.unlock(orderDetailQueryBO);
	}	
	
}
