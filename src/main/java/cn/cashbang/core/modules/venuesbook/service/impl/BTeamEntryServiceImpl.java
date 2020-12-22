package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntryEntity;
import cn.cashbang.core.modules.venuesbook.manager.BTeamEntryManager;
import cn.cashbang.core.modules.venuesbook.service.BTeamEntryService;

/**
 * 团队报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:26
 */
@Service("bTeamEntryService")
public class BTeamEntryServiceImpl implements BTeamEntryService {

	@Autowired
	private BTeamEntryManager bTeamEntryManager;

	@Override
	public Page<BTeamEntryEntity> listBTeamEntry(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BTeamEntryEntity> page = new Page<>(query);
		bTeamEntryManager.listBTeamEntry(page, query);
		return page;
	}

	@Override
	public Result saveBTeamEntry(BTeamEntryEntity role) {
		int count = bTeamEntryManager.saveBTeamEntry(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBTeamEntryById(Long id) {
		BTeamEntryEntity bTeamEntry = bTeamEntryManager.getBTeamEntryById(id);
		return CommonUtils.msg(bTeamEntry);
	}

	@Override
	public Result updateBTeamEntry(BTeamEntryEntity bTeamEntry) {
		int count = bTeamEntryManager.updateBTeamEntry(bTeamEntry);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bTeamEntryManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
