package cn.cashbang.core.modules.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.base.entity.SysAreaEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 行政区域
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月18日 下午3:36:04
 */
@Mapper
public interface SysAreaMapper extends BaseMapper<SysAreaEntity> {

	List<SysAreaEntity> listAreaByParentCode(Query query);
	
	int countAreaChildren(Long areaId);
	
}
