package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BComActivityEntryMapper;
import cn.cashbang.core.modules.venuesbook.entity.BComActivityEntryEntity;
import cn.cashbang.core.modules.venuesbook.manager.BComActivityEntryManager;

/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月28日 PM2:16:50
 */
@Component("bComActivityEntryManager")
public class BComActivityEntryManagerImpl implements BComActivityEntryManager {

	@Autowired
	private BComActivityEntryMapper bComActivityEntryMapper;
	

	@Override
	public List<BComActivityEntryEntity> listBComActivityEntry(Page<BComActivityEntryEntity> page, Query search) {
		return bComActivityEntryMapper.listForPage(page, search);
	}

	@Override
	public int saveBComActivityEntry(BComActivityEntryEntity bComActivityEntry) {
		return bComActivityEntryMapper.save(bComActivityEntry);
	}

	@Override
	public BComActivityEntryEntity getBComActivityEntryById(Long id) {
		BComActivityEntryEntity bComActivityEntry = bComActivityEntryMapper.getObjectById(id);
		return bComActivityEntry;
	}

	@Override
	public int updateBComActivityEntry(BComActivityEntryEntity bComActivityEntry) {
		return bComActivityEntryMapper.update(bComActivityEntry);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = bComActivityEntryMapper.batchRemove(id);
		return count;
	}

    @Override
    public List<BComActivityEntryEntity> getUserListById(String actId, String uid){
	    return  bComActivityEntryMapper.getUserListById(actId,uid);
    }
}
