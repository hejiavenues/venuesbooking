package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BDicMapper;
import cn.cashbang.core.modules.venuesbook.entity.BDicEntity;
import cn.cashbang.core.modules.venuesbook.manager.BDicManager;

/**
 * 字典表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午2:29:29
 */
@Component("bDicManager")
public class BDicManagerImpl implements BDicManager {

	@Autowired
	private BDicMapper bDicMapper;
	

	@Override
	public List<BDicEntity> listBDic(Page<BDicEntity> page, Query search) {
		return bDicMapper.listForPage(page, search);
	}
	
	@Override
	public List<BDicEntity> listActivityTypeForPage(Page<BDicEntity> page, Query search) {
		return bDicMapper.listActivityTypeForPage(page, search);
	}

	@Override
	public int saveBDic(BDicEntity bDic) {
		return bDicMapper.save(bDic);
	}
	
	@Override
	public int savehdlx(BDicEntity bDic) {
		return bDicMapper.savehdlx(bDic);
	}

	@Override
	public BDicEntity getBDicById(Long id) {
		BDicEntity bDic = bDicMapper.getObjectById(id);
		return bDic;
	}
	
	@Override
	public int getMaxId() {
		return bDicMapper.getMaxId();
	}

	@Override
	public int updateBDic(BDicEntity bDic) {
		return bDicMapper.update(bDic);
	}
	
	@Override
	public int updatehdlx(BDicEntity bDic) {
		return bDicMapper.updatehdlx(bDic);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = bDicMapper.batchRemove(id);
		return count;
	}

	@Override
	public BDicEntity getBActivityDicByCode(String id) {
		BDicEntity bDic = bDicMapper.getBActivityDicByCode(id);
		return bDic;
	}
	
	@Override
	public BDicEntity getBDicByCode(String id) {
		BDicEntity bDic = bDicMapper.getBDicByCode(id);
		return bDic;
	}

	@Override
	public List<BDicEntity> getDicsByCode(String typeCode) {
		return bDicMapper.getDicsByCode(typeCode);
	}

	@Override
	public void deleteByTypeCode(String typeCode) {
		bDicMapper.deleteByTypeCode(typeCode);
	}
	
}
