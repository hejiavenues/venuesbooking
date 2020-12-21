package cn.cashbang.core.modules.query.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.cashbang.core.common.annotation.DataSource;
import cn.cashbang.core.common.entity.Application;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.common.utils.HttpClientUtils;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.common.utils.StringUtils;
import cn.cashbang.core.common.utils.HttpClientUtils.HttpGetParams;
import cn.cashbang.core.common.utils.HttpClientUtils.HttpPostParams;
import cn.cashbang.core.modules.query.entity.PayOrderEntity;
import cn.cashbang.core.modules.query.manager.PayOrderManager;
import cn.cashbang.core.modules.query.service.PayOrderService;


/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月30日 下午3:58:04
 */
@Service("payOrderService")
public class PayOrderServiceImpl implements PayOrderService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PayOrderManager payOrderManager;

	@Override
	@DataSource("slaveDataSourceRead")
	public Page<PayOrderEntity> listPayOrder(Map<String, Object> params) {
		Query query = new Query(params);
		Page<PayOrderEntity> page = new Page<>(query);
		payOrderManager.listPayOrder(page, query);
		return page;
	}
	
	

	@Override
	public int retryLoan(String id) {
		
		
		String url = SpringContextUtils.getApplicationProperties().getServerUrlByKey("retryLoan");
		
		JSONObject json=new JSONObject();
		json.put("orderId", id);
		
		HttpPostParams httpPostParams = HttpClientUtils.createHttpPostParams(url, json);
		logger.info(httpPostParams.toString());
		String result = HttpClientUtils.doPost(httpPostParams);
		logger.info(result);
		Application application = JSON.parseObject(result, Application.class);
		
		if(application.isSuccess()){
			return 1;
		}else {
			logger.error("重新放款失败id:{}",id);
		}
		
		return 0;
	}

	@Override

	public Result savePayOrder(PayOrderEntity role) {
		int count = payOrderManager.savePayOrder(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getPayOrderById(Long id) {
		PayOrderEntity payOrder = payOrderManager.getPayOrderById(id);
		return CommonUtils.msg(payOrder);
	}

	@Override
	public Result updatePayOrder(PayOrderEntity payOrder) {
		int count = payOrderManager.updatePayOrder(payOrder);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = payOrderManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
