package cn.cashbang.core.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import cn.cashbang.core.modules.sys.entity.SysRoleOrgEntity;

/**
 * 角色与机构的关系
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月17日 上午11:29:43
 */
@Mapper
public interface SysRoleOrgMapper extends BaseMapper<SysRoleOrgEntity> {

	List<Long> listOrgId(Long roleId);
	
	int batchRemoveByOrgId(Long[] id);
	
	int batchRemoveByRoleId(Long[] id);
	
}
