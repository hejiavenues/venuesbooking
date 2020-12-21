package cn.cashbang.core.modules.query.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.modules.query.entity.PayOrderEntity;
import cn.cashbang.core.modules.query.service.PayOrderService;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.ShiroUtils;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年12月24日 PM3:41:15
 */
@RestController
@RequestMapping("/payOrder")
public class PayOrderController extends AbstractController {
	
	@Autowired
	private PayOrderService payOrderService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<PayOrderEntity> list(@RequestBody Map<String, Object> params) {
/*		params.put("channelName",ShiroUtils.getUserEntity().getChannel() );
*/		logger.info("payOrder:{}",params.toString());
		return payOrderService.listPayOrder(params);
	}
		
	/**
	 * 新增
	 * @param payOrder
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public Result save(@RequestBody PayOrderEntity payOrder) {
		return payOrderService.savePayOrder(payOrder);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return payOrderService.getPayOrderById(id);
	}
	
	/**
	 * 修改
	 * @param payOrder
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public Result update(@RequestBody PayOrderEntity payOrder) {
		return payOrderService.updatePayOrder(payOrder);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return payOrderService.batchRemove(id);
	}
	
}
