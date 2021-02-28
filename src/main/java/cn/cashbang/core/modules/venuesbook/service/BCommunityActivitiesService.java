package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BCommunityActivitiesEntity;

/**
 * 社区活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月27日 PM9:46:01
 */
public interface BCommunityActivitiesService {

	Page<BCommunityActivitiesEntity> listBCommunityActivities(Map<String, Object> params);
	
	Result saveBCommunityActivities(BCommunityActivitiesEntity bCommunityActivities);
	
	Result getBCommunityActivitiesById(Long id);
	
	Result updateBCommunityActivities(BCommunityActivitiesEntity bCommunityActivities);
	
	Result batchRemove(Long[] id);
	
}
