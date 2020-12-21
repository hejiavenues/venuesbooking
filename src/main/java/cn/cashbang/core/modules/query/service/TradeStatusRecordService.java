package cn.cashbang.core.modules.query.service;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import cn.cashbang.core.common.entity.OssEntity;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.query.entity.TradeStatusRecordEntity;

/**
 * 交易记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月27日 上午11:13:23
 */
public interface TradeStatusRecordService {

	Page<TradeStatusRecordEntity> listTradeStatusRecord(Map<String, Object> params);
	
	Result saveTradeStatusRecord(TradeStatusRecordEntity tradeStatusRecord);
	
	Result getTradeStatusRecordById(Long id);
	
	Result updateTradeStatusRecord(TradeStatusRecordEntity tradeStatusRecord);
	
	Result batchRemove(Long[] id);
	
	//Result uploadFilesToOss(InputStream img,String applyId) throws IOException;
	
	Result toPublic(Map<String, Object> params,String userName,Long userId) throws IOException;
	
	Result reduceRepay(Map<String, Object> params,String userName,Long userId) throws IOException;
	
	
	/**
	 * 多期对公和减免
	 */
	Result doPublicAndReduce( MultipartFile file ,String userId,String applyId,String name,String mobile,String plans,Integer period,String userName);
}
