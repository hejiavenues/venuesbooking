package cn.cashbang.core.modules.query.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.annotation.DataSource;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.query.entity.PayLogEntity;
import cn.cashbang.core.modules.query.manager.PayLogManager;
import cn.cashbang.core.modules.query.service.PayLogService;


/**
 * 订单日志
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月30日 下午3:54:19
 */
@Service("payLogService")
public class PayLogServiceImpl implements PayLogService {

	@Autowired
	private PayLogManager payLogManager;

	@Override
	@DataSource("slaveDataSourceRead")
	public Page<PayLogEntity> listPayLog(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PayLogEntity> page = new Page<>(query);
		payLogManager.listPayLog(page, query);
		return page;
	}

	@Override
	public Result savePayLog(PayLogEntity role) {
		int count = payLogManager.savePayLog(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getPayLogById(Long id) {
		PayLogEntity payLog = payLogManager.getPayLogById(id);
		return CommonUtils.msg(payLog);
	}

	@Override
	public Result updatePayLog(PayLogEntity payLog) {
		int count = payLogManager.updatePayLog(payLog);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = payLogManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
