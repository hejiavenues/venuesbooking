package cn.cashbang.core.modules.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.entity.SysWhiteIpEntity;
import cn.cashbang.core.modules.sys.manager.SysWhiteIpManager;
import cn.cashbang.core.modules.sys.service.SysWhiteIpService;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2019年5月31日 下午3:00:25
 */
@Service("sysWhiteIpService")
public class SysWhiteIpServiceImpl implements SysWhiteIpService {

	@Autowired
	private SysWhiteIpManager sysWhiteIpManager;

	@Override
	public Page<SysWhiteIpEntity> listSysWhiteIp(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysWhiteIpEntity> page = new Page<>(query);
		sysWhiteIpManager.listSysWhiteIp(page, query);
		return page;
	}

	@Override
	public Result saveSysWhiteIp(SysWhiteIpEntity role) {
		int count = sysWhiteIpManager.saveSysWhiteIp(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getSysWhiteIpById(String id) {
		SysWhiteIpEntity sysWhiteIp = sysWhiteIpManager.getSysWhiteIpById(id);
		return CommonUtils.msg(sysWhiteIp);
	}

	@Override
	public Result updateSysWhiteIp(SysWhiteIpEntity sysWhiteIp) {
		int count = sysWhiteIpManager.updateSysWhiteIp(sysWhiteIp);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(String[] id) {
		int count = sysWhiteIpManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
