package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;

/**
 * 活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:35
 */
public interface BActivitiesManager {

	List<BActivitiesEntity> listBActivities(Page<BActivitiesEntity> page, Query search);
	
	int saveBActivities(BActivitiesEntity bActivities);
	
	BActivitiesEntity getBActivitiesById(Long id);
	
	int updateBActivities(BActivitiesEntity bActivities);
	
	int batchRemove(Long[] id);
	
}
