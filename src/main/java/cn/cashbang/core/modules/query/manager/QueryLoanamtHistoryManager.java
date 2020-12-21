package cn.cashbang.core.modules.query.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.query.entity.QueryLoanamtHistoryEntity;

/**
 * 放款额度查询
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年4月03日 上午11:20:05
 */
public interface QueryLoanamtHistoryManager {

	List<QueryLoanamtHistoryEntity> listQueryLoanamtHistory(Page<QueryLoanamtHistoryEntity> page, Query search);
	
	int updateQueryLoanamtHistory(QueryLoanamtHistoryEntity queryLoanamtHistory);
	
	QueryLoanamtHistoryEntity getLoanamt();
	
}
