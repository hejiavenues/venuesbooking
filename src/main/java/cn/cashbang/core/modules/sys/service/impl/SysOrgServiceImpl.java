package cn.cashbang.core.modules.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cashbang.core.common.constant.MsgConstant;
import cn.cashbang.core.common.constant.SystemMsg;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.dao.SysOrgMapper;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;
import cn.cashbang.core.modules.sys.manager.SysOrgManager;
import cn.cashbang.core.modules.sys.service.SysOrgService;

/**
 * 组织机构
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月17日 上午11:33:28
 */
@Service("sysOrgService")
public class SysOrgServiceImpl implements SysOrgService {

	@Autowired
	private SysOrgManager sysOrgManager;
	
	@Autowired
	private SysOrgMapper sysOrgMapper;
	
	@Override
	public List<SysOrgEntity> listOrg(Map<String, Object> params) {
		Query query = new Query(params);
		return sysOrgManager.listOrg(query);
	}

	@Override
	public List<SysOrgEntity> listOrgTree(Map<String, Object> params) {
		Query query = new Query(params);
		List<SysOrgEntity> orgList = sysOrgManager.listOrg(query);
		/*SysOrgEntity org = new SysOrgEntity();
		org.setOrgId(0L);
		org.setName("一级机构");
		org.setParentId(-1L);
		org.setOpen(true);
		orgList.add(org);*/
		return orgList;
	}

	@Override
	@Transactional("transactionManager")
	public Result saveOrg(SysOrgEntity org) {
		int count = sysOrgManager.saveOrg(org);
		return CommonUtils.msg(count);
	}
	
	@Override
	@Transactional("transactionManager")
	public Result saveChannelOrg(SysOrgEntity org) {
		
		//查询平台信息
		SysOrgEntity saasOrg =sysOrgManager.getByCode(SystemMsg.sysOrg.SAAS);
		if(saasOrg!=null) {
			
			org.setParentId(saasOrg.getOrgId());
			//查询是否存在
			SysOrgEntity channelOrg = sysOrgManager.getByCode(org.getCode());
			if(channelOrg==null) {
				int count = sysOrgManager.saveOrg(org);
				if(count<1) {
					return Result.error("保存错误");
				}
			}
			//插入风控和运营
			 channelOrg = sysOrgManager.getByCode(org.getCode());
			 
			 
			
			//查询是否存在
			 
			String riskCode=channelOrg.getCode()+"_"+SystemMsg.sysOrg.RISK;
			SysOrgEntity riskOrg = sysOrgManager.getByCode(riskCode);
			if(riskOrg==null) {
				
				riskOrg =new SysOrgEntity();
				riskOrg.setCode(riskCode);
				riskOrg.setName(channelOrg.getName()+"_"+SystemMsg.sysOrg.RISKNAME);
				riskOrg.setParentId(channelOrg.getOrgId());
				riskOrg.setStatus(SystemMsg.sysOrg.STATUS);
				riskOrg.setOrderNum(SystemMsg.sysOrg.ORDER);
				
				
				int count = sysOrgManager.saveOrg(riskOrg);
				if(count<1) {
					return Result.error("保存错误");
				}
			}
			
			//查询是否存在
			 
			String OPERATIONCode=channelOrg.getCode()+"_"+SystemMsg.sysOrg.OPERATION;
			SysOrgEntity OPERATIONOrg = sysOrgManager.getByCode(OPERATIONCode);
			if(OPERATIONOrg==null) {
				
				OPERATIONOrg =new SysOrgEntity();
				OPERATIONOrg.setCode(OPERATIONCode);
				OPERATIONOrg.setName(channelOrg.getName()+"_"+SystemMsg.sysOrg.OPERATIONNAME);
				OPERATIONOrg.setParentId(channelOrg.getOrgId());
				OPERATIONOrg.setStatus(SystemMsg.sysOrg.STATUS);
				OPERATIONOrg.setOrderNum(SystemMsg.sysOrg.ORDER);
				
				
				int count = sysOrgManager.saveOrg(OPERATIONOrg);
				if(count<1) {
					return Result.error("保存错误");
				}
			}
			
			
			
			//查询是否存在
			 
			String collection=channelOrg.getCode()+"_"+SystemMsg.sysOrg.COLLECTION;
			SysOrgEntity collectionOrg = sysOrgManager.getByCode(collection);
			if(collectionOrg==null) {
				
				collectionOrg =new SysOrgEntity();
				collectionOrg.setCode(collection);
				collectionOrg.setName(channelOrg.getName()+"_"+SystemMsg.sysOrg.COLLECTIONNAME);
				collectionOrg.setParentId(channelOrg.getOrgId());
				collectionOrg.setStatus(SystemMsg.sysOrg.STATUS);
				collectionOrg.setOrderNum(SystemMsg.sysOrg.ORDER);
				
				int count = sysOrgManager.saveOrg(collectionOrg);
				if(count<1) {
					return Result.error("保存错误");
				}
			}
		

		

			
			return Result.ok("机构初始化完毕");
			
		}else {
			return Result.error();
		}
		
	}


	@Override
	public Result getOrg(Long orgId) {
		SysOrgEntity org = sysOrgManager.getOrg(orgId);
		return CommonUtils.msg(org);
	}

	@Override
	public Result updateOrg(SysOrgEntity org) {
		int count = sysOrgManager.updateOrg(org);
		return CommonUtils.msg(count);
	}

	@Override
	public Result bactchRemoveOrg(Long[] id) {
		boolean children = sysOrgManager.hasChildren(id);
		if(children) {
			return Result.error(MsgConstant.MSG_HAS_CHILD);
		}
		boolean  checkOrg = sysOrgManager.checkOrg(id);
		if(checkOrg) {
			return Result.error(MsgConstant.MSG_HAS_ROLEORUSER);
		}
		
		int count = sysOrgManager.bactchRemoveOrg(id);
		return CommonUtils.msg(id, count);
	}
	
	@Override
	public Result getByCode(String code) {
		
		HashMap<String, Object>  map=new HashMap<>();
		map.put("org", sysOrgManager.getByCode(code));
		map.put("code",100);
		return Result.ok(map);
	}
	@Override
	public Result selectCode(String table,String col,String only) {
			int count = sysOrgManager.selectByOnly(table ,col,only);
			Map<String, Object> map =new HashMap<String, Object>();
			map.put("code", 100);
			map.put("count", count);
			return Result.ok(map);
	}
	
	
	@Override
	public Result getCsOrgs(String channel) {
		/*if(Strings.isNullOrEmpty(channel)) {
			return Result.error("saas管理员暂时无法查询");
		}else {*/
			String code=channel+"_"+SystemMsg.sysOrg.COLLECTION;
			SysOrgEntity corg= sysOrgManager.getByCode(code);
			if(corg!=null) {
				Long fid=corg.getOrgId();
				List<SysOrgEntity> orgs=sysOrgMapper.selectCsOrgs(fid);
				if(!orgs.isEmpty()) {
					return Result.oko(orgs);
				}
			}
	/*	}*/
		return Result.error("未发现该对应催收部门");
	}

}
