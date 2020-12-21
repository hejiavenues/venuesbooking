package cn.cashbang.core.modules.base.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.base.entity.SysAreaEntity;

/**
 * 行政区域
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月18日 下午3:39:00
 */
public interface SysAreaManager {

	List<SysAreaEntity> listAreaByParentCode(Query query);
	
	int saveArea(SysAreaEntity area);
	
	SysAreaEntity getAreaById(Long areaId);
	
	int updateArea(SysAreaEntity area);
	
	int batchRemoveArea(Long[] id);
	
	boolean hasChildren(Long[] id);
	
}
