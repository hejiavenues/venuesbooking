package cn.cashbang.core.modules.sys.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.common.utils.StringUtils;
import cn.cashbang.core.modules.sys.dao.SysOrgMapper;
import cn.cashbang.core.modules.sys.dao.SysRoleOrgMapper;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;
import cn.cashbang.core.modules.sys.manager.SysOrgManager;

/**
 * 组织架构
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月17日 上午11:32:15
 */
@Component("sysOrgManager")
public class SysOrgManagerImpl implements SysOrgManager {

	@Autowired
	private SysOrgMapper sysOrgMapper;
	
	@Autowired
	private SysRoleOrgMapper sysRoleOrgMapper;
	
	@Override
	public List<SysOrgEntity> listOrg(Query query) {
		return sysOrgMapper.list(query);
	}

	@Override
	public int saveOrg(SysOrgEntity org) {
		
		this.sysOrgMapper.save(org);
		
		SysOrgEntity sysOrgEntity = new SysOrgEntity();
		sysOrgEntity.setParentId(org.getParentId());
		sysOrgEntity.setOrgIdPath(StringUtils.EMPTY);
		sysOrgEntity.setOrgId(org.getOrgId());
		
		SysOrgEntity result = this.recursive(sysOrgEntity);
		
		org.setOrgIdPath(result.getOrgIdPath());
		
		return this.sysOrgMapper.update(org);
	}

	@Override
	public SysOrgEntity getOrg(Long orgId) {
		return sysOrgMapper.getObjectById(orgId);
	}

	@Override
	public int updateOrg(SysOrgEntity org) {
		return sysOrgMapper.update(org);
	}

	@Override
	public int bactchRemoveOrg(Long[] id) {
		int count = sysOrgMapper.batchRemove(id);
		sysRoleOrgMapper.batchRemoveByOrgId(id);
		return count;
	}

	@Override
	public boolean hasChildren(Long[] id) {
		for(Long parentId : id) {
			int count = sysOrgMapper.countOrgChildren(parentId);
			if(CommonUtils.isIntThanZero(count)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean checkOrg(Long[] id) {
		for(Long orgId : id) {
			String check= sysOrgMapper.checkOrg(orgId);
			if(check.equals("has")) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public SysOrgEntity getByCode(String code) {
		return sysOrgMapper.selectByCode(code);
	}
	
	private SysOrgEntity recursive(SysOrgEntity org){
		
		String orgIdPath = org.getOrgIdPath();
		org.setOrgIdPath(org.getOrgId()+","+orgIdPath);
		
		if(org.getParentId()==0L){
			return org;
		}
		
		SysOrgEntity result = this.sysOrgMapper.getObjectById(org.getParentId());
		org.setOrgId(result.getOrgId());
		org.setParentId(result.getParentId());
		
		return this.recursive(org);
	}
	
	@Override
	public int selectByOnly(String table,String col,String only){
		Map<String, String> map= new HashMap<>();
		map.put("table",table)	;
		map.put("col", col);
		map.put("only", only);
		
		return sysOrgMapper.selectByOnly(map);
	}
	

}
