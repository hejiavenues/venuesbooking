package cn.cashbang.core.modules.sys.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;

/**
 * 组织机构
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月17日 上午11:31:59
 */
public interface SysOrgManager {

	List<SysOrgEntity> listOrg(Query query);
	
	int saveOrg(SysOrgEntity org);
	
	SysOrgEntity getOrg(Long orgId);
	
	int updateOrg(SysOrgEntity org);
	
	int bactchRemoveOrg(Long[] id);
	
	boolean hasChildren(Long[] id);
	
	boolean  checkOrg(Long[]  id);
	
	int selectByOnly(String table,String col,String only);
	
	SysOrgEntity getByCode(String code);
	
	
}
