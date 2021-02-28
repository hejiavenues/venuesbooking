package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BCommunityActivitiesMapper;
import cn.cashbang.core.modules.venuesbook.entity.BCommunityActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.manager.BCommunityActivitiesManager;

/**
 * 社区活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月27日 PM9:46:01
 */
@Component("bCommunityActivitiesManager")
public class BCommunityActivitiesManagerImpl implements BCommunityActivitiesManager {

	@Autowired
	private BCommunityActivitiesMapper bCommunityActivitiesMapper;
	

	@Override
	public List<BCommunityActivitiesEntity> listBCommunityActivities(Page<BCommunityActivitiesEntity> page, Query search) {
		return bCommunityActivitiesMapper.listForPage(page, search);
	}

	@Override
	public int saveBCommunityActivities(BCommunityActivitiesEntity bCommunityActivities) {
		return bCommunityActivitiesMapper.save(bCommunityActivities);
	}

	@Override
	public BCommunityActivitiesEntity getBCommunityActivitiesById(String id) {
		BCommunityActivitiesEntity bCommunityActivities = bCommunityActivitiesMapper.getObjectById(id);
		return bCommunityActivities;
	}

	@Override
	public int updateBCommunityActivities(BCommunityActivitiesEntity bCommunityActivities) {
		return bCommunityActivitiesMapper.update(bCommunityActivities);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = bCommunityActivitiesMapper.batchRemove(id);
		return count;
	}

    @Override
    public List<BCommunityActivitiesEntity> listActByUserId(String uid){
        return bCommunityActivitiesMapper.listActByUserId(uid);
    }

    @Override
    public List<BCommunityActivitiesEntity> listByCreateUser(String uid){
        return bCommunityActivitiesMapper.listByCreateUser(uid);
    }
}
