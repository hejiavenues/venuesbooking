package cn.cashbang.core.modules.query.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.query.entity.QueryLoanamtHistoryEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 放款额度查询
 *
 * @author cashbang
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2018年4月03日 上午11:20:05
 */
@Mapper
public interface QueryLoanamtHistoryMapper extends BaseMapper<QueryLoanamtHistoryEntity> {
	
}
