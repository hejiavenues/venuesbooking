package cn.cashbang.core.modules.query.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.query.entity.TradeStatusRecordEntity;


/**
 * 交易记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月27日 上午11:13:23
 */
public interface TradeStatusRecordManager {

	List<TradeStatusRecordEntity> listTradeStatusRecord(Page<TradeStatusRecordEntity> page, Query search);
	
	int saveTradeStatusRecord(TradeStatusRecordEntity tradeStatusRecord);
	
	TradeStatusRecordEntity getTradeStatusRecordById(Long id);
	
	int updateTradeStatusRecord(TradeStatusRecordEntity tradeStatusRecord);
	
	int batchRemove(Long[] id);
	
	
}
