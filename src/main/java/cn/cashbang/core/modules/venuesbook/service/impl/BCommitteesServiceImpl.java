package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BCommitteesEntity;
import cn.cashbang.core.modules.venuesbook.manager.BCommitteesManager;
import cn.cashbang.core.modules.venuesbook.service.BCommitteesService;

/**
 * 居委会信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月23日 上午9:48:52
 */
@Service("bCommitteesService")
public class BCommitteesServiceImpl implements BCommitteesService {

	@Autowired
	private BCommitteesManager bCommitteesManager;

	@Override
	public Page<BCommitteesEntity> listBCommittees(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BCommitteesEntity> page = new Page<>(query);
		bCommitteesManager.listBCommittees(page, query);
		return page;
	}

	@Override
	public Result saveBCommittees(BCommitteesEntity role) {
		BCommitteesEntity a = bCommitteesManager.getBCommitteesByName(role.getCname());
		if(a != null) {
			return Result.error("该居委会已存在");
		}
		role.setCid(CommonUtils.createUUID());
		role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		int count = bCommitteesManager.saveBCommittees(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBCommitteesById(String id) {
		if(!StringUtils.isEmpty(id)) {
			id = id.replace("\"", "");
		}
		BCommitteesEntity bCommittees = bCommitteesManager.getBCommitteesById(id);
		return CommonUtils.msg(bCommittees);
	}

	@Override
	public Result updateBCommittees(BCommitteesEntity bCommittees) {
		if(bCommittees != null) {
			bCommittees.setUpdateTime(new Date());
		}
		int count = bCommitteesManager.updateBCommittees(bCommittees);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(String[] id) {
		int count = bCommitteesManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
