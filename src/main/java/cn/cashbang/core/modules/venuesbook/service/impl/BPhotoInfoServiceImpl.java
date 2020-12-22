package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BPhotoInfoManager;
import cn.cashbang.core.modules.venuesbook.service.BPhotoInfoService;

/**
 * 随拍信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:01
 */
@Service("bPhotoInfoService")
public class BPhotoInfoServiceImpl implements BPhotoInfoService {

	@Autowired
	private BPhotoInfoManager bPhotoInfoManager;

	@Override
	public Page<BPhotoInfoEntity> listBPhotoInfo(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BPhotoInfoEntity> page = new Page<>(query);
		bPhotoInfoManager.listBPhotoInfo(page, query);
		return page;
	}

	@Override
	public Result saveBPhotoInfo(BPhotoInfoEntity role) {
		int count = bPhotoInfoManager.saveBPhotoInfo(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBPhotoInfoById(Long id) {
		BPhotoInfoEntity bPhotoInfo = bPhotoInfoManager.getBPhotoInfoById(id);
		return CommonUtils.msg(bPhotoInfo);
	}

	@Override
	public Result updateBPhotoInfo(BPhotoInfoEntity bPhotoInfo) {
		int count = bPhotoInfoManager.updateBPhotoInfo(bPhotoInfo);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bPhotoInfoManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
