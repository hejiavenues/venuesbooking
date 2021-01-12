package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.manager.BActivitiesManager;
import cn.cashbang.core.modules.venuesbook.service.BActivitiesService;

/**
 * 活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:35
 */
@Service("bActivitiesService")
public class BActivitiesServiceImpl implements BActivitiesService {

	@Autowired
	private BActivitiesManager bActivitiesManager;

	@Override
	public Page<BActivitiesEntity> listBActivities(Map<String, Object> params) {
		
		Query query = new Query(params);
		Page<BActivitiesEntity> page = new Page<>(query);
		bActivitiesManager.listBActivities(page, query);
		return page;
	}

	@Override
	public Result saveBActivities(BActivitiesEntity role) {
		int count = bActivitiesManager.saveBActivities(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBActivitiesById(String id) {
		BActivitiesEntity bActivities = bActivitiesManager.getBActivitiesById(id);
		return CommonUtils.msg(bActivities);
	}

	@Override
	public Result updateBActivities(BActivitiesEntity bActivities) {
		int count = bActivitiesManager.updateBActivities(bActivities);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bActivitiesManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public  Result listActByUserId(String uid) {
		List<BActivitiesEntity>  list = bActivitiesManager.listActByUserId(uid);
		return  Result.ok().put("raws", list);
	}
}
