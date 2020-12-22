package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BDicEntity;
import cn.cashbang.core.modules.venuesbook.manager.BDicManager;
import cn.cashbang.core.modules.venuesbook.service.BDicService;

/**
 * 字典表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午2:29:29
 */
@Service("bDicService")
public class BDicServiceImpl implements BDicService {

	@Autowired
	private BDicManager bDicManager;

	@Override
	public Page<BDicEntity> listBDic(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BDicEntity> page = new Page<>(query);
		bDicManager.listBDic(page, query);
		return page;
	}

	@Override
	public Result saveBDic(BDicEntity role) {
		int count = bDicManager.saveBDic(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBDicById(Long id) {
		BDicEntity bDic = bDicManager.getBDicById(id);
		return CommonUtils.msg(bDic);
	}

	@Override
	public Result updateBDic(BDicEntity bDic) {
		int count = bDicManager.updateBDic(bDic);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bDicManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
