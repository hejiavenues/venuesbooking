package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BTeamMapper;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntity;
import cn.cashbang.core.modules.venuesbook.manager.BTeamManager;

/**
 * 团队信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 上午11:57:39
 */
@Component("bTeamManager")
public class BTeamManagerImpl implements BTeamManager {

	@Autowired
	private BTeamMapper bTeamMapper;
	

	@Override
	public List<BTeamEntity> listBTeam(Page<BTeamEntity> page, Query search) {
		return bTeamMapper.listForPage(page, search);
	}

	@Override
	public int saveBTeam(BTeamEntity bTeam) {
		return bTeamMapper.save(bTeam);
	}

	@Override
	public BTeamEntity getBTeamById(String id) {
		BTeamEntity bTeam = bTeamMapper.getObjectById(id);
		return bTeam;
	}

	@Override
	public int updateBTeam(BTeamEntity bTeam) {
		return bTeamMapper.update(bTeam);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = bTeamMapper.batchRemove(id);
		return count;
	}
	
	@Override
	public List<BTeamEntity> listTeamByUserId(String uid){
		return bTeamMapper.listTeamByUserId(uid);
	}

	@Override
	public List<BTeamEntity> listTeamByCreateUserId(String uid){
		return bTeamMapper.listTeamByCreateUserId(uid);
	}
}
