package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BCommunityActivitiesEntity;

/**
 * 社区活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月27日 PM9:46:01
 */
public interface BCommunityActivitiesManager {

	List<BCommunityActivitiesEntity> listBCommunityActivities(Page<BCommunityActivitiesEntity> page, Query search);
	
	int saveBCommunityActivities(BCommunityActivitiesEntity bCommunityActivities);
	
	BCommunityActivitiesEntity getBCommunityActivitiesById(String id);
	
	int updateBCommunityActivities(BCommunityActivitiesEntity bCommunityActivities);
	
	int batchRemove(String[] id);

    List<BCommunityActivitiesEntity> listActByUserId(String uid);

    List<BCommunityActivitiesEntity> listByCreateUser(String uid);
}
