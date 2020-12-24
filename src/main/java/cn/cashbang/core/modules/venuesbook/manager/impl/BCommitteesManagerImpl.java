package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BCommitteesMapper;
import cn.cashbang.core.modules.venuesbook.entity.BCommitteesEntity;
import cn.cashbang.core.modules.venuesbook.manager.BCommitteesManager;

/**
 * 居委会信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月23日 上午9:48:52
 */
@Component("bCommitteesManager")
public class BCommitteesManagerImpl implements BCommitteesManager {

	@Autowired
	private BCommitteesMapper bCommitteesMapper;
	

	@Override
	public List<BCommitteesEntity> listBCommittees(Page<BCommitteesEntity> page, Query search) {
		return bCommitteesMapper.listForPage(page, search);
	}

	@Override
	public int saveBCommittees(BCommitteesEntity bCommittees) {
		return bCommitteesMapper.save(bCommittees);
	}

	@Override
	public BCommitteesEntity getBCommitteesById(String id) {
		BCommitteesEntity bCommittees = bCommitteesMapper.getObjectById(id);
		return bCommittees;
	}
	
	@Override
	public BCommitteesEntity getBCommitteesByName(String name) {
		BCommitteesEntity bCommittees = bCommitteesMapper.getCommitteeByName(name);
		return bCommittees;
	}

	@Override
	public int updateBCommittees(BCommitteesEntity bCommittees) {
		return bCommitteesMapper.update(bCommittees);
	}

	@Override
	public int batchRemove(String[] id) {
		int count = bCommitteesMapper.batchRemove(id);
		return count;
	}
	
}
