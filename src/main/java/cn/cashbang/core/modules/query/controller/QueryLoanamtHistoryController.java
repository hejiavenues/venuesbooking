package cn.cashbang.core.modules.query.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.query.entity.QueryLoanamtHistoryEntity;
import cn.cashbang.core.modules.query.service.QueryLoanamtHistoryService;
import cn.cashbang.core.modules.sys.controller.AbstractController;

/**
 * 放款额度修改历史查询
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年4月03日 上午11:20:05
 */
@RestController
@RequestMapping("/query/loanamt")
public class QueryLoanamtHistoryController extends AbstractController {
	
	@Autowired
	private QueryLoanamtHistoryService queryLoanamtHistoryService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<QueryLoanamtHistoryEntity> list(@RequestBody Map<String, Object> params) {
		return queryLoanamtHistoryService.listQueryLoanamtHistory(params);
	}
		
	/**
	 * 查询当前放款可用额度
	 * @param id
	 * @return
	 */
	@RequestMapping("/info/loanamt")
	public Result getLoanamt() {
		return queryLoanamtHistoryService.getLoanamt();
	}
	
	/**
	 * 修改
	 * @param queryLoanamtHistory
	 * @return
	 */
	@SysLog("修改预放款额度")
	@RequestMapping("/update")
	public Result update(@RequestBody QueryLoanamtHistoryEntity queryLoanamtHistory) {
		return queryLoanamtHistoryService.updateQueryLoanamtHistory(queryLoanamtHistory);
	}
	
	
}
