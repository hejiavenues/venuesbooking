package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntity;
import cn.cashbang.core.modules.venuesbook.manager.BTeamManager;
import cn.cashbang.core.modules.venuesbook.service.BTeamService;

/**
 * 团队信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 上午11:57:39
 */
@Service("bTeamService")
public class BTeamServiceImpl implements BTeamService {

	@Autowired
	private BTeamManager bTeamManager;

	@Override
	public Page<BTeamEntity> listBTeam(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BTeamEntity> page = new Page<>(query);
		bTeamManager.listBTeam(page, query);
		return page;
	}

	@Override
	public Result saveBTeam(BTeamEntity role) {
		int count = bTeamManager.saveBTeam(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBTeamById(Long id) {
		BTeamEntity bTeam = bTeamManager.getBTeamById(id);
		return CommonUtils.msg(bTeam);
	}

	@Override
	public Result updateBTeam(BTeamEntity bTeam) {
		int count = bTeamManager.updateBTeam(bTeam);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bTeamManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
