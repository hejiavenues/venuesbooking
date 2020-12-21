package cn.cashbang.core.modules.query.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.query.entity.MTopublicLogEntity;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年8月02日 PM5:52:10
 */
public interface MTopublicLogManager {

	List<MTopublicLogEntity> listMTopublicLog(Page<MTopublicLogEntity> page, Query search);
	
	int saveMTopublicLog(MTopublicLogEntity mTopublicLog);
	
	MTopublicLogEntity getMTopublicLogById(Long id);
	
	int updateMTopublicLog(MTopublicLogEntity mTopublicLog);
	
	int batchRemove(Long[] id);
	
}
