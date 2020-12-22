package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BVenueInfoManager;
import cn.cashbang.core.modules.venuesbook.service.BVenueInfoService;

/**
 * 场馆信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:20:39
 */
@Service("bVenueInfoService")
public class BVenueInfoServiceImpl implements BVenueInfoService {

	@Autowired
	private BVenueInfoManager bVenueInfoManager;

	@Override
	public Page<BVenueInfoEntity> listBVenueInfo(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BVenueInfoEntity> page = new Page<>(query);
		bVenueInfoManager.listBVenueInfo(page, query);
		return page;
	}

	@Override
	public Result saveBVenueInfo(BVenueInfoEntity role) {
		int count = bVenueInfoManager.saveBVenueInfo(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBVenueInfoById(Long id) {
		BVenueInfoEntity bVenueInfo = bVenueInfoManager.getBVenueInfoById(id);
		return CommonUtils.msg(bVenueInfo);
	}

	@Override
	public Result updateBVenueInfo(BVenueInfoEntity bVenueInfo) {
		int count = bVenueInfoManager.updateBVenueInfo(bVenueInfo);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bVenueInfoManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
