package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BActivitiesMapper;
import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.manager.BActivitiesManager;

/**
 * 活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:35
 */
@Component("bActivitiesManager")
public class BActivitiesManagerImpl implements BActivitiesManager {

	@Autowired
	private BActivitiesMapper bActivitiesMapper;
	

	@Override
	public List<BActivitiesEntity> listBActivities(Page<BActivitiesEntity> page, Query search) {
		List<BActivitiesEntity> lists = bActivitiesMapper.listForPage(page, search);
		for(BActivitiesEntity entity:lists) {
			if(entity.getStatus().intValue() == 1) {
				entity.setStatusDesc("公开");
			}else if((entity.getStatus().intValue() == 2)) {
				entity.setStatusDesc("不公开");
			}else {
				entity.setStatusDesc("未知");
			}
		}
		return lists;
	}

	@Override
	public int saveBActivities(BActivitiesEntity bActivities) {
		return bActivitiesMapper.save(bActivities);
	}

	@Override
	public BActivitiesEntity getBActivitiesById(String id) {
		BActivitiesEntity bActivities = bActivitiesMapper.getObjectById(id);
		return bActivities;
	}

	@Override
	public int updateBActivities(BActivitiesEntity bActivities) {
		return bActivitiesMapper.update(bActivities);
	}

	@Override
	public int batchRemove(String[] id) {
		int count = bActivitiesMapper.batchRemove(id);
		return count;
	}

	@Override
	public List<BActivitiesEntity> listActByUserId(String uid){

		return bActivitiesMapper.listActByUserId(uid);
	}
}
