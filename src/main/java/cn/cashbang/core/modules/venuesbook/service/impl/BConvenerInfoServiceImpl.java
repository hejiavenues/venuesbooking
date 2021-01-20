package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BConvenerInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BConvenerInfoManager;
import cn.cashbang.core.modules.venuesbook.service.BConvenerInfoService;

/**
 * 召集人信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:27
 */
@Service("bConvenerInfoService")
public class BConvenerInfoServiceImpl implements BConvenerInfoService {

	@Autowired
	private BConvenerInfoManager bConvenerInfoManager;

	@Override
	public Page<BConvenerInfoEntity> listBConvenerInfo(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BConvenerInfoEntity> page = new Page<>(query);
		bConvenerInfoManager.listBConvenerInfo(page, query);
		return page;
	}

	@Override
	public Result saveBConvenerInfo(BConvenerInfoEntity role) {
		int count = bConvenerInfoManager.saveBConvenerInfo(role);

		return CommonUtils.msg(count);
	}

	@Override
	public Result getBConvenerInfoById(String id) {
		BConvenerInfoEntity bConvenerInfo = bConvenerInfoManager.getBConvenerInfoById(id);
		return CommonUtils.msg(bConvenerInfo);
	}

	@Override
	public Result updateBConvenerInfo(BConvenerInfoEntity bConvenerInfo) {
		if(bConvenerInfo != null) {
			bConvenerInfo.setUpdateTime(new Date());
		}
		int count = bConvenerInfoManager.updateBConvenerInfo(bConvenerInfo);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(String[] id) {
		int count = bConvenerInfoManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
