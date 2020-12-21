package cn.cashbang.core.modules.query.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.query.entity.TradeStatusRecordEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 交易记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月27日 上午11:13:23
 */
@Mapper
public interface TradeStatusRecordMapper extends BaseMapper<TradeStatusRecordEntity> {
	
}
