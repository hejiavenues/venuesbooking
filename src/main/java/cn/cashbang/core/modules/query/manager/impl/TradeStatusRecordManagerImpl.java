package cn.cashbang.core.modules.query.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.query.dao.TradeStatusRecordMapper;
import cn.cashbang.core.modules.query.entity.TradeStatusRecordEntity;
import cn.cashbang.core.modules.query.manager.TradeStatusRecordManager;


/**
 * 交易记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月27日 上午11:13:23
 */
@Component("tradeStatusRecordManager")
public class TradeStatusRecordManagerImpl implements TradeStatusRecordManager {

	@Autowired
	private TradeStatusRecordMapper tradeStatusRecordMapper;
	

	@Override
	public List<TradeStatusRecordEntity> listTradeStatusRecord(Page<TradeStatusRecordEntity> page, Query search) {
		return tradeStatusRecordMapper.listForPage(page, search);
	}

	@Override
	public int saveTradeStatusRecord(TradeStatusRecordEntity tradeStatusRecord) {
		return tradeStatusRecordMapper.save(tradeStatusRecord);
	}

	@Override
	public TradeStatusRecordEntity getTradeStatusRecordById(Long id) {
		TradeStatusRecordEntity tradeStatusRecord = tradeStatusRecordMapper.getObjectById(id);
		return tradeStatusRecord;
	}

	@Override
	public int updateTradeStatusRecord(TradeStatusRecordEntity tradeStatusRecord) {
		return tradeStatusRecordMapper.update(tradeStatusRecord);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = tradeStatusRecordMapper.batchRemove(id);
		return count;
	}
	
}
