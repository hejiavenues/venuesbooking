package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BActivityEntryMapper;
import cn.cashbang.core.modules.venuesbook.entity.BActivityEntryEntity;
import cn.cashbang.core.modules.venuesbook.manager.BActivityEntryManager;

/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:46
 */
@Component("bActivityEntryManager")
public class BActivityEntryManagerImpl implements BActivityEntryManager {

	@Autowired
	private BActivityEntryMapper bActivityEntryMapper;
	

	@Override
	public List<BActivityEntryEntity> listBActivityEntry(Page<BActivityEntryEntity> page, Query search) {
		return bActivityEntryMapper.listForPage(page, search);
	}

	@Override
	public int saveBActivityEntry(BActivityEntryEntity bActivityEntry) {
		return bActivityEntryMapper.save(bActivityEntry);
	}

	@Override
	public BActivityEntryEntity getBActivityEntryById(Long id) {
		BActivityEntryEntity bActivityEntry = bActivityEntryMapper.getObjectById(id);
		return bActivityEntry;
	}

	@Override
	public int updateBActivityEntry(BActivityEntryEntity bActivityEntry) {
		return bActivityEntryMapper.update(bActivityEntry);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = bActivityEntryMapper.batchRemove(id);
		return count;
	}

	@Override
	public List<BActivityEntryEntity> getUserListById(String actId,String uid){
		return bActivityEntryMapper.getUserListById(actId,uid);
	}

    @Override
    public int deleteByActivityId(String activityId) {
        return bActivityEntryMapper.deleteByActivityId(activityId);
    }
}
