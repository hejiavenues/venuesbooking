package cn.cashbang.core.modules.query.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.query.entity.QueryLoanamtHistoryEntity;

/**
 * 放款额度查询
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年4月03日 上午11:20:05
 */
public interface QueryLoanamtHistoryService {

	
	/**
	 * 
	 * 查询修改可用额度操作历史
	 * 
	 * @param params
	 * @return
	 */
	Page<QueryLoanamtHistoryEntity> listQueryLoanamtHistory(Map<String, Object> params);
	
	
	/**
	 * 
	 * 修改放款可用额度
	 * 
	 * @param queryLoanamtHistory
	 * @return
	 */
	Result updateQueryLoanamtHistory(QueryLoanamtHistoryEntity queryLoanamtHistory);

	/**
	 * 查询当前可用放款额度
	 * 
	 * @return
	 */
	Result getLoanamt();
	
}
