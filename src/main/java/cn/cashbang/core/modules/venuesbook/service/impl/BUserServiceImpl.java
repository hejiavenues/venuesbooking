package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;
import cn.cashbang.core.modules.venuesbook.manager.BUserManager;
import cn.cashbang.core.modules.venuesbook.service.BUserService;

/**
 * 用户信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:02
 */
@Service("bUserService")
public class BUserServiceImpl implements BUserService {

	@Autowired
	private BUserManager bUserManager;

	@Override
	public Page<BUserEntity> listBUser(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BUserEntity> page = new Page<>(query);
		bUserManager.listBUser(page, query);
		return page;
	}

	@Override
	public Result saveBUser(BUserEntity role) {
		int count = bUserManager.saveBUser(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBUserById(Long id) {
		BUserEntity bUser = bUserManager.getBUserById(id);
		return CommonUtils.msg(bUser);
	}

	@Override
	public Result updateBUser(BUserEntity bUser) {
		int count = bUserManager.updateBUser(bUser);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bUserManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
