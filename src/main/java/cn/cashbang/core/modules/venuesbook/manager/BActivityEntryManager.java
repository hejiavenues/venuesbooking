package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BActivityEntryEntity;

/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:46
 */
public interface BActivityEntryManager {

	List<BActivityEntryEntity> listBActivityEntry(Page<BActivityEntryEntity> page, Query search);
	
	int saveBActivityEntry(BActivityEntryEntity bActivityEntry);
	
	BActivityEntryEntity getBActivityEntryById(Long id);
	
	int updateBActivityEntry(BActivityEntryEntity bActivityEntry);
	
	int batchRemove(Long[] id);

	List<BActivityEntryEntity> getUserListById(String actId,String uid);
}
