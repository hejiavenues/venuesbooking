package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.List;
import java.util.Map;

import cn.cashbang.core.modules.venuesbook.entity.BActivityEntryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BComActivityEntryEntity;
import cn.cashbang.core.modules.venuesbook.manager.BComActivityEntryManager;
import cn.cashbang.core.modules.venuesbook.service.BComActivityEntryService;

/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月28日 PM2:16:50
 */
@Service("bComActivityEntryService")
public class BComActivityEntryServiceImpl implements BComActivityEntryService {

	@Autowired
	private BComActivityEntryManager bComActivityEntryManager;

	@Override
	public Page<BComActivityEntryEntity> listBComActivityEntry(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BComActivityEntryEntity> page = new Page<>(query);
		bComActivityEntryManager.listBComActivityEntry(page, query);
		return page;
	}

	@Override
	public Result saveBComActivityEntry(BComActivityEntryEntity role) {
		int count = bComActivityEntryManager.saveBComActivityEntry(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBComActivityEntryById(Long id) {
		BComActivityEntryEntity bComActivityEntry = bComActivityEntryManager.getBComActivityEntryById(id);
		return CommonUtils.msg(bComActivityEntry);
	}

	@Override
	public Result updateBComActivityEntry(BComActivityEntryEntity bComActivityEntry) {
		int count = bComActivityEntryManager.updateBComActivityEntry(bComActivityEntry);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bComActivityEntryManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

    @Override
    public Result getUserListById(String actId,String uid){

        List<BComActivityEntryEntity> entity = bComActivityEntryManager.getUserListById(actId,uid);

        return  Result.ok().put("raws", entity);
    }
}
