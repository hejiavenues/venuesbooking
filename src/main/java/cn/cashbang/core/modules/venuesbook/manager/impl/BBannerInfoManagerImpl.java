package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BBannerInfoMapper;
import cn.cashbang.core.modules.venuesbook.entity.BBannerInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BBannerInfoManager;

/**
 * banner图表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:17:36
 */
@Component("bBannerInfoManager")
public class BBannerInfoManagerImpl implements BBannerInfoManager {

	@Autowired
	private BBannerInfoMapper bBannerInfoMapper;
	

	@Override
	public List<BBannerInfoEntity> listBBannerInfo(Page<BBannerInfoEntity> page, Query search) {
		return bBannerInfoMapper.listForPage(page, search);
	}

	@Override
	public int saveBBannerInfo(BBannerInfoEntity bBannerInfo) {
		return bBannerInfoMapper.save(bBannerInfo);
	}

	@Override
	public BBannerInfoEntity getBBannerInfoById(Long id) {
		BBannerInfoEntity bBannerInfo = bBannerInfoMapper.getObjectById(id);
		return bBannerInfo;
	}

	@Override
	public int updateBBannerInfo(BBannerInfoEntity bBannerInfo) {
		return bBannerInfoMapper.update(bBannerInfo);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = bBannerInfoMapper.batchRemove(id);
		return count;
	}
	
}
