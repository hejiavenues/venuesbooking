package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;

/**
 * 活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:35
 */
public interface BActivitiesService {

	Page<BActivitiesEntity> listBActivities(Map<String, Object> params);
	
	Result saveBActivities(BActivitiesEntity bActivities);
	
	Result getBActivitiesById(String id);
	
	Result updateBActivities(BActivitiesEntity bActivities);
	
	Result batchRemove(Long[] id);

	Result listActByUserId(String uid);
}
