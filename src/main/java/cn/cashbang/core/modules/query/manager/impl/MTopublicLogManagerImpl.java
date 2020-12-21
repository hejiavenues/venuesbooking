package cn.cashbang.core.modules.query.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.query.dao.MTopublicLogMapper;
import cn.cashbang.core.modules.query.entity.MTopublicLogEntity;
import cn.cashbang.core.modules.query.manager.MTopublicLogManager;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年8月02日 PM6:19:44
 */
@Component("mTopublicLogManager")
public class MTopublicLogManagerImpl implements MTopublicLogManager {

	@Autowired
	private MTopublicLogMapper mTopublicLogMapper;
	

	@Override
	public List<MTopublicLogEntity> listMTopublicLog(Page<MTopublicLogEntity> page, Query search) {
		return mTopublicLogMapper.listForPage(page, search);
	}

	@Override
	public int saveMTopublicLog(MTopublicLogEntity mTopublicLog) {
		return mTopublicLogMapper.save(mTopublicLog);
	}

	@Override
	public MTopublicLogEntity getMTopublicLogById(Long id) {
		MTopublicLogEntity mTopublicLog = mTopublicLogMapper.getObjectById(id);
		return mTopublicLog;
	}

	@Override
	public int updateMTopublicLog(MTopublicLogEntity mTopublicLog) {
		return mTopublicLogMapper.update(mTopublicLog);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = mTopublicLogMapper.batchRemove(id);
		return count;
	}
	
}
