package cn.cashbang.core.modules.query.manager.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.cashbang.core.common.entity.Application;
import cn.cashbang.core.common.utils.HttpClientUtils;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.common.utils.HttpClientUtils.HttpPostParams;
import cn.cashbang.core.modules.query.bo.OrderDetailQueryBO;
import cn.cashbang.core.modules.query.manager.OrderQueryManager;

/**
 * 客户申请单信息
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年3月23日 下午6:38:56
 */
@Component("orderQueryManager")
public class OrderQueryManagerImpl implements OrderQueryManager {

	//@Value("${application-properties.server-url.applyInfoUnlock}")


	@Override
	public int unlock(OrderDetailQueryBO orderDetail) {
		String unlockUrl= SpringContextUtils.getApplicationProperties().getServerUrlByKey("applyInfoUnlock");
		HttpPostParams httpPostParams = HttpClientUtils.createHttpPostParams(unlockUrl, orderDetail);
		String result = HttpClientUtils.doPost(httpPostParams);
		
		Application application = JSON.parseObject(result, Application.class);
		
		if(application.isSuccess()){
			return 1;
		}
		
		return 0;
	}
	
}
