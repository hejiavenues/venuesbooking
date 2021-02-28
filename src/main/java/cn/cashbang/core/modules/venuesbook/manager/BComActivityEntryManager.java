package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BActivityEntryEntity;
import cn.cashbang.core.modules.venuesbook.entity.BComActivityEntryEntity;

/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月28日 PM2:16:50
 */
public interface BComActivityEntryManager {

	List<BComActivityEntryEntity> listBComActivityEntry(Page<BComActivityEntryEntity> page, Query search);
	
	int saveBComActivityEntry(BComActivityEntryEntity bComActivityEntry);
	
	BComActivityEntryEntity getBComActivityEntryById(Long id);
	
	int updateBComActivityEntry(BComActivityEntryEntity bComActivityEntry);
	
	int batchRemove(Long[] id);

    List<BComActivityEntryEntity> getUserListById(String actId, String uid);
}
