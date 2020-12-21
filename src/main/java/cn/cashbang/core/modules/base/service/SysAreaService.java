package cn.cashbang.core.modules.base.service;

import java.util.List;
import java.util.Map;

import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.base.entity.SysAreaEntity;

/**
 * 行政区域
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月18日 下午3:40:18
 */
public interface SysAreaService {

	List<SysAreaEntity> listAreaByParentCode(String areaCode);
	
	Result listAreaByParentCode(Map<String, Object> params);
	
	Result saveArea(SysAreaEntity area);
	
	Result getAreaById(Long areaId);
	
	Result updateArea(SysAreaEntity area);
	
	Result batchRemoveArea(Long[] id);
	
}
