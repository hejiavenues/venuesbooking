package cn.cashbang.core.modules.sys.service;

import java.util.List;
import java.util.Map;

import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;

/**
 * 组织机构
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月17日 上午11:32:55
 */
public interface SysOrgService {

	List<SysOrgEntity> listOrg(Map<String, Object> params);
	
	List<SysOrgEntity> listOrgTree(Map<String, Object> params);
	
	Result saveOrg(SysOrgEntity org);
	
	Result saveChannelOrg(SysOrgEntity org);
	
	Result getOrg(Long orgId);
	
	Result updateOrg(SysOrgEntity org);
	
	Result bactchRemoveOrg(Long[] id);
	
	Result getByCode(String code);
	
	Result selectCode(String table,String col,String only);
	
	
	Result getCsOrgs(String channel);

}
