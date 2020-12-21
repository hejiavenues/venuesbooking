package cn.cashbang.core.modules.query.dao;

import java.util.List;
import java.util.Map;

import cn.cashbang.core.modules.query.entity.*;
import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.sys.dao.BaseMapper;

import java.util.List;

/**
 * 进件主表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月17日 上午11:23:42
 */
@Mapper
public interface TradeApplyStatusMapper extends BaseMapper<TradeApplyStatusEntity> {

	TradeMsgEntity getMainMsg(String orderNo);

	GmtMsgEntity getGmtMsg(String orderNo);

	List<TradeCrEntity> getCrMsg(String orderNo);

	List<TradeApplyStatusEntity> getTradeDecisioningList();

	List<TradeApplyStatusEntity> getPassportUserMsg(Map<String, Object> params);
	
	List<TradeApplyStatusEntity> getApiMsgByMobile(String userMobile);
	
	List<OrderBasicInfoEntity> getTradeCreditingList();

	List<OrderBasicInfoEntity> getOrderBasicInfoByApplyId(Long applyId);
}
