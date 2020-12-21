package cn.cashbang.core.modules.sys.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.utils.RandomUtils;
import cn.cashbang.core.modules.sys.dao.SysWhiteIpMapper;
import cn.cashbang.core.modules.sys.entity.SysWhiteIpEntity;
import cn.cashbang.core.modules.sys.manager.SysWhiteIpManager;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2019年5月31日 下午3:00:25
 */
@Component("sysWhiteIpManager")
public class SysWhiteIpManagerImpl implements SysWhiteIpManager {

	@Autowired
	private SysWhiteIpMapper sysWhiteIpMapper;
	

	@Override
	public List<SysWhiteIpEntity> listSysWhiteIp(Page<SysWhiteIpEntity> page, Query search) {
		return sysWhiteIpMapper.listForPage(page, search);
	}

	@Override
	public int saveSysWhiteIp(SysWhiteIpEntity sysWhiteIp) {
		sysWhiteIp.setId(RandomUtils.UUID());
		sysWhiteIp.setCreateTime(new Date());
		return sysWhiteIpMapper.save(sysWhiteIp);
	}

	@Override
	public SysWhiteIpEntity getSysWhiteIpById(String id) {
		SysWhiteIpEntity sysWhiteIp = sysWhiteIpMapper.getObjectById(id);
		return sysWhiteIp;
	}

	@Override
	public int updateSysWhiteIp(SysWhiteIpEntity sysWhiteIp) {
		sysWhiteIp.setUpdateTime(new Date());
		return sysWhiteIpMapper.update(sysWhiteIp);
	}

	@Override
	public int batchRemove(String[] id) {
		int count = sysWhiteIpMapper.batchRemove(id);
		return count;
	}
	
}
