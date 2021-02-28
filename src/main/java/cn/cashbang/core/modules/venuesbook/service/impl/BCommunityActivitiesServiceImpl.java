package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BCommunityActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.manager.BCommunityActivitiesManager;
import cn.cashbang.core.modules.venuesbook.service.BCommunityActivitiesService;

/**
 * 社区活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月27日 PM9:46:01
 */
@Service("bCommunityActivitiesService")
public class BCommunityActivitiesServiceImpl implements BCommunityActivitiesService {

	@Autowired
	private BCommunityActivitiesManager bCommunityActivitiesManager;

	@Override
	public Page<BCommunityActivitiesEntity> listBCommunityActivities(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BCommunityActivitiesEntity> page = new Page<>(query);
		bCommunityActivitiesManager.listBCommunityActivities(page, query);
		return page;
	}

	@Override
	public Result saveBCommunityActivities(BCommunityActivitiesEntity role) {
		int count = bCommunityActivitiesManager.saveBCommunityActivities(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBCommunityActivitiesById(String id) {
		BCommunityActivitiesEntity bCommunityActivities = bCommunityActivitiesManager.getBCommunityActivitiesById(id);
		return CommonUtils.msg(bCommunityActivities);
	}

	@Override
	public Result updateBCommunityActivities(BCommunityActivitiesEntity bCommunityActivities) {
		int count = bCommunityActivitiesManager.updateBCommunityActivities(bCommunityActivities);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bCommunityActivitiesManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

    @Override
    public Result listActByUserId(String uid){
        List<BCommunityActivitiesEntity> list = bCommunityActivitiesManager.listActByUserId(uid);
        return  Result.ok().put("raws", list);
    }
    
    @Override
    public Result listByCreateUser(String uid){
        List<BCommunityActivitiesEntity> list = bCommunityActivitiesManager.listByCreateUser(uid);
        return  Result.ok().put("raws", list);
    }
}
