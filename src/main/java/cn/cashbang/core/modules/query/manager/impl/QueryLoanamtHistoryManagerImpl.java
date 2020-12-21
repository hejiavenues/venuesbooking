package cn.cashbang.core.modules.query.manager.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.cashbang.core.common.entity.Application;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.utils.HttpClientUtils;
import cn.cashbang.core.common.utils.HttpClientUtils.HttpGetParams;
import cn.cashbang.core.common.utils.HttpClientUtils.HttpPostParams;
import cn.cashbang.core.common.utils.ShiroUtils;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.modules.query.bo.LoanamtQueryBO;
import cn.cashbang.core.modules.query.dao.QueryLoanamtHistoryMapper;
import cn.cashbang.core.modules.query.entity.QueryLoanamtHistoryEntity;
import cn.cashbang.core.modules.query.manager.QueryLoanamtHistoryManager;

/**
 * 放款额度查询
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年4月03日 上午11:20:05
 */
@Component("queryLoanamtHistoryManager")
public class QueryLoanamtHistoryManagerImpl implements QueryLoanamtHistoryManager {

	@Autowired
	private QueryLoanamtHistoryMapper queryLoanamtHistoryMapper;
	

	@Override
	public List<QueryLoanamtHistoryEntity> listQueryLoanamtHistory(Page<QueryLoanamtHistoryEntity> page, Query search) {
		return queryLoanamtHistoryMapper.listForPage(page, search);
	}


	@Override
	public int updateQueryLoanamtHistory(QueryLoanamtHistoryEntity queryLoanamtHistory) {
		
		queryLoanamtHistory.setGmtCreate(new Date());
		queryLoanamtHistory.setUserIdCreate(ShiroUtils.getUserId());
		int count = this.queryLoanamtHistoryMapper.save(queryLoanamtHistory);
		
		//调用http服务
		String maxAmountSettingUrl = SpringContextUtils.getApplicationProperties().getServerUrlByKey("maxAmountSetting");
		
		HttpPostParams httpPostParams = HttpClientUtils.createHttpPostParams(maxAmountSettingUrl+"?value="+queryLoanamtHistory.getLoanamtAfter());
		
		//response
		String result = HttpClientUtils.doPost(httpPostParams);
		
		Application application = JSON.parseObject(result, Application.class);
		
		if(!application.isSuccess()){
			throw new RuntimeException(application.getMsg());
		}
		
		return count;
	}

	@Override
	public QueryLoanamtHistoryEntity getLoanamt() {
		
		String loanAmountQueryUrl = SpringContextUtils.getApplicationProperties().getServerUrlByKey("loanAmountQuery");
		 
		HttpGetParams httpGetParams = HttpClientUtils.createHttpGetParams(loanAmountQueryUrl);
		
		//response
		String result = HttpClientUtils.doGet(httpGetParams);
		
		Application application = JSON.parseObject(result, Application.class);
		
		if(!application.isSuccess()){
			return new QueryLoanamtHistoryEntity();
		}
		
		if(application.getData()==null){
			return new QueryLoanamtHistoryEntity();
		}
		
		LoanamtQueryBO resultEntity = JSON.parseObject(application.getData().toString(), LoanamtQueryBO.class);
		
		QueryLoanamtHistoryEntity entity = new QueryLoanamtHistoryEntity();
		entity.setLoanamtRemain(resultEntity.getAvailAmount());
		entity.setLoanamtSum(resultEntity.getMaxAmount());
		entity.setLoanamtBefore(entity.getLoanamtSum());
		
		return entity;
	}
	
}
