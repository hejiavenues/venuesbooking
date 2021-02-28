package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BComActivityEntryEntity;

/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月28日 PM2:16:50
 */
public interface BComActivityEntryService {

	Page<BComActivityEntryEntity> listBComActivityEntry(Map<String, Object> params);
	
	Result saveBComActivityEntry(BComActivityEntryEntity bComActivityEntry);
	
	Result getBComActivityEntryById(Long id);
	
	Result updateBComActivityEntry(BComActivityEntryEntity bComActivityEntry);
	
	Result batchRemove(Long[] id);

    Result getUserListById(String actId,String uid);
}
