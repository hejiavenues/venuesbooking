package cn.cashbang.core.modules.query.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.query.dao.PayOrderMapper;
import cn.cashbang.core.modules.query.entity.PayOrderEntity;
import cn.cashbang.core.modules.query.manager.PayOrderManager;


/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月30日 下午3:58:04
 */
@Component("payOrderManager")
public class PayOrderManagerImpl implements PayOrderManager {

	@Autowired
	private PayOrderMapper payOrderMapper;
	

	@Override
	public List<PayOrderEntity> listPayOrder(Page<PayOrderEntity> page, Query search) {
		return payOrderMapper.listForPage(page, search);
	}

	@Override
	public int savePayOrder(PayOrderEntity payOrder) {
		return payOrderMapper.save(payOrder);
	}

	@Override
	public PayOrderEntity getPayOrderById(Long id) {
		PayOrderEntity payOrder = payOrderMapper.getObjectById(id);
		return payOrder;
	}

	@Override
	public int updatePayOrder(PayOrderEntity payOrder) {
		return payOrderMapper.update(payOrder);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = payOrderMapper.batchRemove(id);
		return count;
	}
	
}
