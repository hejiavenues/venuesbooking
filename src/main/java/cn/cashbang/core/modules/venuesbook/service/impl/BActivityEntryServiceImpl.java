package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.List;
import java.util.Map;

import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;
import cn.cashbang.core.modules.venuesbook.manager.BUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BActivityEntryEntity;
import cn.cashbang.core.modules.venuesbook.manager.BActivityEntryManager;
import cn.cashbang.core.modules.venuesbook.service.BActivityEntryService;

/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:46
 */
@Service("bActivityEntryService")
public class BActivityEntryServiceImpl implements BActivityEntryService {

	@Autowired
	private BActivityEntryManager bActivityEntryManager;

	@Autowired
	private BUserManager bUserManager;

	@Override
	public Page<BActivityEntryEntity> listBActivityEntry(Map<String, Object> params) {
		if(!StringUtils.isEmpty((String)params.get("activityId"))) {
			params.put("activityId", ((String)params.get("activityId")).replace("\"", ""));
		}
		Query query = new Query(params);
		Page<BActivityEntryEntity> page = new Page<>(query);
		bActivityEntryManager.listBActivityEntry(page, query);
		return page;
	}

	@Override
	public Result saveBActivityEntry(BActivityEntryEntity role) {

		//根据userId查询用户信息
		BUserEntity bUser = bUserManager.getBUserById(role.getUid());

		role.setUname(bUser.getUname());
		role.setMobile(bUser.getMobile());

		int count = bActivityEntryManager.saveBActivityEntry(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBActivityEntryById(Long id) {
		BActivityEntryEntity bActivityEntry = bActivityEntryManager.getBActivityEntryById(id);
		return CommonUtils.msg(bActivityEntry);
	}

	@Override
	public Result updateBActivityEntry(BActivityEntryEntity bActivityEntry) {
		int count = bActivityEntryManager.updateBActivityEntry(bActivityEntry);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bActivityEntryManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public Result getUserListById(String actId){

		List<BActivityEntryEntity> entity = bActivityEntryManager.getUserListById(actId);

		return  Result.ok().put("raws", entity);
	}
}
