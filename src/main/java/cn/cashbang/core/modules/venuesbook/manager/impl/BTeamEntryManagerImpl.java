package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BTeamEntryMapper;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntryEntity;
import cn.cashbang.core.modules.venuesbook.manager.BTeamEntryManager;

/**
 * 团队报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:26
 */
@Component("bTeamEntryManager")
public class BTeamEntryManagerImpl implements BTeamEntryManager {

	@Autowired
	private BTeamEntryMapper bTeamEntryMapper;
	

	@Override
	public List<BTeamEntryEntity> listBTeamEntry(Page<BTeamEntryEntity> page, Query search) {
		return bTeamEntryMapper.listForPage(page, search);
	}

	@Override
	public int saveBTeamEntry(BTeamEntryEntity bTeamEntry) {
		return bTeamEntryMapper.save(bTeamEntry);
	}

	@Override
	public BTeamEntryEntity getBTeamEntryById(Long id) {
		BTeamEntryEntity bTeamEntry = bTeamEntryMapper.getObjectById(id);
		return bTeamEntry;
	}

	@Override
	public int updateBTeamEntry(BTeamEntryEntity bTeamEntry) {
		return bTeamEntryMapper.update(bTeamEntry);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = bTeamEntryMapper.batchRemove(id);
		return count;
	}

	@Override
	public List<BTeamEntryEntity> getTeamUserById(String tid){
		return bTeamEntryMapper.getTeamUserById(tid);
	}

	@Override
	public int updateTeamStatus(String tid,String uid){
		return bTeamEntryMapper.updateTeamStatus(tid,uid);
	}
}
