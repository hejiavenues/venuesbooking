package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BBannerInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BBannerInfoManager;
import cn.cashbang.core.modules.venuesbook.service.BBannerInfoService;

/**
 * banner图表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:17:36
 */
@Service("bBannerInfoService")
public class BBannerInfoServiceImpl implements BBannerInfoService {

	@Autowired
	private BBannerInfoManager bBannerInfoManager;

	@Override
	public Page<BBannerInfoEntity> listBBannerInfo(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BBannerInfoEntity> page = new Page<>(query);
		bBannerInfoManager.listBBannerInfo(page, query);
		return page;
	}

	@Override
	public Result saveBBannerInfo(BBannerInfoEntity role) {
		int count = bBannerInfoManager.saveBBannerInfo(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBBannerInfoById(Long id) {
		BBannerInfoEntity bBannerInfo = bBannerInfoManager.getBBannerInfoById(id);
		return CommonUtils.msg(bBannerInfo);
	}

	@Override
	public Result updateBBannerInfo(BBannerInfoEntity bBannerInfo) {
		int count = bBannerInfoManager.updateBBannerInfo(bBannerInfo);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bBannerInfoManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
