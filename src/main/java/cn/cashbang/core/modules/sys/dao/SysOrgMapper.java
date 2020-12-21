package cn.cashbang.core.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;

/**
 * 组织架构
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月17日 上午11:26:05
 */
@Mapper
public interface SysOrgMapper extends BaseMapper<SysOrgEntity> {

	int countOrgChildren(Long parentId);
	String  checkOrg(Long orgid);
	int selectByOnly(Map<String,String> map);
	
	SysOrgEntity selectByCode(String code);
	
	List<SysOrgEntity> selectCsOrgs(Long parentId);
	
}
