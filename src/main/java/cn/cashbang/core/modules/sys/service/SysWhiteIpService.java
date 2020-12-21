package cn.cashbang.core.modules.sys.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.entity.SysWhiteIpEntity;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2019年5月31日 下午3:00:25
 */
public interface SysWhiteIpService {

	Page<SysWhiteIpEntity> listSysWhiteIp(Map<String, Object> params);
	
	Result saveSysWhiteIp(SysWhiteIpEntity sysWhiteIp);
	
	Result getSysWhiteIpById(String id);
	
	Result updateSysWhiteIp(SysWhiteIpEntity sysWhiteIp);
	
	Result batchRemove(String[] id);
	
}
