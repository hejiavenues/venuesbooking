package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BConvenerInfoMapper;
import cn.cashbang.core.modules.venuesbook.entity.BConvenerInfoEntity;
import cn.cashbang.core.modules.venuesbook.manager.BConvenerInfoManager;

/**
 * 召集人信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:27
 */
@Component("bConvenerInfoManager")
public class BConvenerInfoManagerImpl implements BConvenerInfoManager {

	@Autowired
	private BConvenerInfoMapper bConvenerInfoMapper;
	

	@Override
	public List<BConvenerInfoEntity> listBConvenerInfo(Page<BConvenerInfoEntity> page, Query search) {
		List<BConvenerInfoEntity> lists = bConvenerInfoMapper.listForPage(page, search);
		for(BConvenerInfoEntity entity:lists) {
			if(entity.getStatus().intValue() == 0) {
				entity.setStatusDesc("待审核");
			}else if(entity.getStatus().intValue() == 1) {
				entity.setStatusDesc("已通过");
			}else if(entity.getStatus().intValue() == 2) {
				entity.setStatusDesc("已拒绝");
			}else {
				entity.setStatusDesc("未知");
			}
		}
		return lists;
	}

	@Override
	public int saveBConvenerInfo(BConvenerInfoEntity bConvenerInfo) {
		return bConvenerInfoMapper.save(bConvenerInfo);
	}

	@Override
	public BConvenerInfoEntity getBConvenerInfoById(String id) {
		BConvenerInfoEntity bConvenerInfo = bConvenerInfoMapper.getObjectById(id);
		return bConvenerInfo;
	}

	@Override
	public int updateBConvenerInfo(BConvenerInfoEntity bConvenerInfo) {
		return bConvenerInfoMapper.update(bConvenerInfo);
	}

	@Override
	public int batchRemove(String[] id) {
		int count = bConvenerInfoMapper.batchRemove(id);
		return count;
	}
	
}
