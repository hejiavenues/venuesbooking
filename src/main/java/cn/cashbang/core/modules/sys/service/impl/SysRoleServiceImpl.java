package cn.cashbang.core.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.xmlrpc.base.Array;

import cn.cashbang.core.common.constant.SystemMsg;
import cn.cashbang.core.common.constant.SystemMsg.sysOrg;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;
import cn.cashbang.core.modules.sys.entity.SysRoleEntity;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import cn.cashbang.core.modules.sys.manager.SysOrgManager;
import cn.cashbang.core.modules.sys.manager.SysRoleManager;
import cn.cashbang.core.modules.sys.service.SysRoleService;

/**
 * 系统角色
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月12日 上午12:41:19
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleManager sysRoleManager;
	

	@Autowired
	private SysOrgManager sysOrgManager;

	@Override
	public Page<SysRoleEntity> listRole(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysRoleEntity> page = new Page<>(query);
		sysRoleManager.listRole(page, query);
		return page;
	}
	@Override
	public Page<SysRoleEntity> listChannelRole(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysRoleEntity> page = new Page<>(query);
		sysRoleManager.listChannelRole(page, query);
		return page;
	}
	@Override
	public Result saveRole(SysRoleEntity role) {
		int count = sysRoleManager.saveRole(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getRoleById(Long id) {
		SysRoleEntity role = sysRoleManager.getRoleById(id);
		return CommonUtils.msg(role);
	}

	@Override
	public Result updateRole(SysRoleEntity role) {
		int count = sysRoleManager.updateRole(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = sysRoleManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public Result list(Map<String, Object> params) {
		List<SysRoleEntity> roleList = sysRoleManager.list(new Query(params));
		return CommonUtils.msgNotCheckNull(roleList);
	}

	@Override
	public Result updateRoleOptAuthorization(SysRoleEntity role) {
		int count = sysRoleManager.updateRoleOptAuthorization(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result updateRoleDataAuthorization(SysRoleEntity role) {
		int count = sysRoleManager.updateRoleDataAuthorization(role);
		return CommonUtils.msg(count);
	}
	
	@Override
	public Result checkAndSaveRole(SysRoleEntity  role,SysUserEntity user) {
		
		 JSONObject rolejson=SystemMsg.sysRole.Rlist();
		 
		 boolean hasAdmin=user.getHasAdmin()>0;
		 
		 if(!hasAdmin&&rolejson.containsKey(role.getRoleSign())) {
			 return Result.error(SystemMsg.sysRole.ADDERRMSG);
		 }
		
		role.setUserIdCreate(user.getUserId());
		
		int count = sysRoleManager.saveRole(role);
		return CommonUtils.msg(count);
	}
	
	@Override
	public Result saveChannelRoles(SysOrgEntity  org,SysUserEntity user) {
		
		 
		 	SysRoleEntity role=new SysRoleEntity();
		 
			role.setUserIdCreate(user.getUserId());
			role.setRemark(SystemMsg.sysRole.REMARK);
		 
			//获取角色所在机构
			String  channel=org.getCode();
	
			Long saasOrgId =sysOrgManager.getByCode(SystemMsg.sysOrg.SAAS).getOrgId();
			
			Long channelOrgId=sysOrgManager.getByCode(channel).getOrgId();
			
			Long riskOrgId=sysOrgManager.getByCode(channel+"_"+SystemMsg.sysOrg.RISK).getOrgId();
			
			Long OPERATIONOrgId=sysOrgManager.getByCode(channel+"_"+SystemMsg.sysOrg.OPERATION).getOrgId();
			
			Long COLLECTIONOrgId=sysOrgManager.getByCode(channel+"_"+SystemMsg.sysOrg.COLLECTION).getOrgId();

			
			//添加角色
			
			//添加管理员
			
			role.setRoleSign(channel+SystemMsg.sysRole.CHANNELADMIN);
			role.setRoleName(org.getName()+SystemMsg.sysRole.CHANNELADMINNAME);
			role.setOrgId(saasOrgId);

			SysRoleEntity check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			
			//添加风控经理
			
			role.setRoleSign(SystemMsg.sysRole.RISKMANAGER);
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.RISKMANAGERNAME);
			role.setOrgId(riskOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			
			//添加运营经理
			
			role.setRoleSign(SystemMsg.sysRole.OPERATIONMANAGER);
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.OPERATIONMANAGERNAME);
			role.setOrgId(OPERATIONOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			
			//添加电核
			role.setRoleSign(SystemMsg.sysRole.TELEPHONEDECISION);
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.TELEPHONEDECISIONNAME);
			role.setOrgId(OPERATIONOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			//添加质检
			role.setRoleSign(SystemMsg.sysRole.DECISIONINSPECT );
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.DECISIONINSPECTNAME);
			role.setOrgId(OPERATIONOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			//添加催收
			role.setRoleSign(SystemMsg.sysRole.collectionManager );
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.collectionManagerNAME);
			role.setOrgId(COLLECTIONOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			
			
			//添加催收
			role.setRoleSign(SystemMsg.sysRole.collectionGroupLeader );
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.collectionGroupLeaderNAME);
			role.setOrgId(COLLECTIONOrgId);

			check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			
			
			
			//添加催收
			role.setRoleSign(SystemMsg.sysRole.collector );
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.collectorNAME);
			role.setOrgId(COLLECTIONOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			
			//添加提醒专员
			role.setRoleSign(SystemMsg.sysRole.reminder );
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.reminderNAME);
			role.setOrgId(OPERATIONOrgId);

			check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			
/*			
			//添加催收
			role.setRoleSign(SystemMsg.sysRole.collectionS2 );
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.collectionS2NAME);
			role.setOrgId(COLLECTIONOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			
			//添加催收
			role.setRoleSign(SystemMsg.sysRole.collectionS3 );
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.collectionS3NAME);
			role.setOrgId(COLLECTIONOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
		
			
			
			//添加催收
			role.setRoleSign(SystemMsg.sysRole.collectionS4 );
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.collectionS4NAME);
			role.setOrgId(COLLECTIONOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			
			
			//添加催收
			role.setRoleSign(SystemMsg.sysRole.collectionM1 );
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.collectionM1NAME);
			role.setOrgId(COLLECTIONOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			
			
			//添加催收
			role.setRoleSign(SystemMsg.sysRole.collectionM2 );
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.collectionM2NAME);
			role.setOrgId(COLLECTIONOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			
			//添加催收
			role.setRoleSign(SystemMsg.sysRole.collectionM3 );
			role.setRoleName(org.getName()+"_"+SystemMsg.sysRole.collectionM3NAME);
			role.setOrgId(COLLECTIONOrgId);

			 check=sysRoleManager.selectByRole(role);
			
			if(check==null) {
				sysRoleManager.saveRole(role);
			}
			*/
			
			return Result.ok("生成默认角色成功");
	}
	

	@Override
	public Result checkAndUpdateRole(SysRoleEntity role,SysUserEntity user) {
		
		SysRoleEntity oldRole= sysRoleManager.getRoleById(role.getRoleId());
		
		if(!checkRole(oldRole,user)) {
			return Result.error(SystemMsg.sysRole.UPDATEERRMSG);
		}else {
			int count = sysRoleManager.updateRole(role);
			return CommonUtils.msg(count);
		}
		
	}
	
	@Override
	public Result checkAndBatchRemove(String[]  idAndSign,SysUserEntity user) {
		
		
		
		
		JSONObject rolejson=SystemMsg.sysRole.Rlist();
		 
	    boolean hasAdmin=user.getHasAdmin()>0;
	    
	    String err="";
		 //非admin无法删除关键角色
		 if(!hasAdmin) {
			 List<Long> id= new ArrayList<Long>();
		   for(int i=0;i<idAndSign.length;i++) {
				String[] idsign=idAndSign[i].split(",");
				if(rolejson.containsKey(idsign[1])) {
					err+=idsign[1]+" ";
				}
			}
			 
			 if(!err.equals("")) {
				return  Result.error(err+SystemMsg.sysRole.DETELEERRMSG);
			 }
		 }

		 
		 Long[] id=new Long[idAndSign.length];
		 
		 for(int i=0;i<idAndSign.length;i++) {
			 id[i]=Long.valueOf(idAndSign[i].split(",")[0]);
			}
		 
		 int count = sysRoleManager.batchRemove(id);
		
		 return CommonUtils.msg(id, count);
		
		
	}
	
	private boolean checkRole(SysRoleEntity role,SysUserEntity user) {
		
		JSONObject rolejson=SystemMsg.sysRole.Rlist();
		 
		 boolean hasAdmin=user.getHasAdmin()>0;
		 
		 if(!hasAdmin&&rolejson.containsKey(role.getRoleSign())) {
			 return false;
		 }else {
			 return true;
		 }
		
	}
	
	
}
