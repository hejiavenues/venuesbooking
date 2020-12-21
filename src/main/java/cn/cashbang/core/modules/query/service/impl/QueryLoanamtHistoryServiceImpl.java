package cn.cashbang.core.modules.query.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.query.entity.QueryLoanamtHistoryEntity;
import cn.cashbang.core.modules.query.manager.QueryLoanamtHistoryManager;
import cn.cashbang.core.modules.query.service.QueryLoanamtHistoryService;

/**
 * 放款额度查询
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年4月03日 上午11:20:05
 */
@Service("queryLoanamtHistoryService")
public class QueryLoanamtHistoryServiceImpl implements QueryLoanamtHistoryService {

	@Autowired
	private QueryLoanamtHistoryManager queryLoanamtHistoryManager;

	@Override
	public Page<QueryLoanamtHistoryEntity> listQueryLoanamtHistory(Map<String, Object> params) {
		Query query = new Query(params);
		Page<QueryLoanamtHistoryEntity> page = new Page<>(query);
		queryLoanamtHistoryManager.listQueryLoanamtHistory(page, query);
		return page;
	}

	@Override
	@Transactional("transactionManager")
	public Result updateQueryLoanamtHistory(QueryLoanamtHistoryEntity queryLoanamtHistory) {
			
		int count = this.queryLoanamtHistoryManager.updateQueryLoanamtHistory(queryLoanamtHistory);
		
		return CommonUtils.msg(count);
	}


	@Override
	public Result getLoanamt() {
		QueryLoanamtHistoryEntity queryLoanamtHistory = queryLoanamtHistoryManager.getLoanamt();
		return CommonUtils.msg(queryLoanamtHistory);
	}

}
