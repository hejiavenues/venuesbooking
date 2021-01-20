package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BActivityEntryEntity;

/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:46
 */
public interface BActivityEntryService {

	Page<BActivityEntryEntity> listBActivityEntry(Map<String, Object> params);
	
	Result saveBActivityEntry(BActivityEntryEntity bActivityEntry);
	
	Result getBActivityEntryById(Long id);
	
	Result updateBActivityEntry(BActivityEntryEntity bActivityEntry);
	
	Result batchRemove(Long[] id);

	Result getUserListById(String actId,String uid);
}
