package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BVenueInfoMapper;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BVenueInfoManager;

/**
 * 场馆信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:20:39
 */
@Component("bVenueInfoManager")
public class BVenueInfoManagerImpl implements BVenueInfoManager {

	@Autowired
	private BVenueInfoMapper bVenueInfoMapper;
	

	@Override
	public List<BVenueInfoEntity> listBVenueInfo(Page<BVenueInfoEntity> page, Query search) {
		return bVenueInfoMapper.listForPage(page, search);
	}

	@Override
	public int saveBVenueInfo(BVenueInfoEntity bVenueInfo) {
		return bVenueInfoMapper.save(bVenueInfo);
	}

	@Override
	public BVenueInfoEntity getBVenueInfoById(Long id) {
		BVenueInfoEntity bVenueInfo = bVenueInfoMapper.getObjectById(id);
		return bVenueInfo;
	}

	@Override
	public int updateBVenueInfo(BVenueInfoEntity bVenueInfo) {
		return bVenueInfoMapper.update(bVenueInfo);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = bVenueInfoMapper.batchRemove(id);
		return count;
	}
	
}
