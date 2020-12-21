package cn.cashbang.core.modules.sys.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.sys.entity.SysWhiteIpEntity;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2019年5月31日 下午3:00:25
 */
public interface SysWhiteIpManager {

	List<SysWhiteIpEntity> listSysWhiteIp(Page<SysWhiteIpEntity> page, Query search);
	
	int saveSysWhiteIp(SysWhiteIpEntity sysWhiteIp);
	
	SysWhiteIpEntity getSysWhiteIpById(String id);
	
	int updateSysWhiteIp(SysWhiteIpEntity sysWhiteIp);
	
	int batchRemove(String[] id);
	
}
