package cn.cashbang.core.modules.query.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.query.dao.PayLogMapper;
import cn.cashbang.core.modules.query.entity.PayLogEntity;
import cn.cashbang.core.modules.query.manager.PayLogManager;


/**
 * 订单日志
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月30日 下午3:54:19
 */
@Component("payLogManager")
public class PayLogManagerImpl implements PayLogManager {

	@Autowired
	private PayLogMapper payLogMapper;
	

	@Override
	public List<PayLogEntity> listPayLog(Page<PayLogEntity> page, Query search) {
		return payLogMapper.listForPage(page, search);
	}

	@Override
	public int savePayLog(PayLogEntity payLog) {
		return payLogMapper.save(payLog);
	}

	@Override
	public PayLogEntity getPayLogById(Long id) {
		PayLogEntity payLog = payLogMapper.getObjectById(id);
		return payLog;
	}

	@Override
	public int updatePayLog(PayLogEntity payLog) {
		return payLogMapper.update(payLog);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = payLogMapper.batchRemove(id);
		return count;
	}
	
}
