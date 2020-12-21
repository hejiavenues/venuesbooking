package cn.cashbang.core.modules.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.constant.MsgConstant;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.base.entity.SysAreaEntity;
import cn.cashbang.core.modules.base.manager.SysAreaManager;
import cn.cashbang.core.modules.base.service.SysAreaService;

/**
 * 行政区域
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月18日 下午3:40:57
 */
@Service("sysAreaService")
public class SysAreaServiceImpl implements SysAreaService {

	@Autowired
	private SysAreaManager sysAreaManager;
	
	@Override
	public List<SysAreaEntity> listAreaByParentCode(String areaCode) {
		Query query = new Query();
		query.put("parentCode", areaCode);
		List<SysAreaEntity> areas = sysAreaManager.listAreaByParentCode(query);
		for(SysAreaEntity area : areas) {
			area.checkParent();
		}
		return areas;
	}

	@Override
	public Result saveArea(SysAreaEntity area) {
		int count = sysAreaManager.saveArea(area);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getAreaById(Long areaId) {
		SysAreaEntity area = sysAreaManager.getAreaById(areaId);
		area.checkParentName();
		return CommonUtils.msg(area);
	}

	@Override
	public Result updateArea(SysAreaEntity area) {
		int count = sysAreaManager.updateArea(area);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemoveArea(Long[] id) {
		boolean children = sysAreaManager.hasChildren(id);
		if(children) {
			return Result.error(MsgConstant.MSG_HAS_CHILD);
		}
		int count = sysAreaManager.batchRemoveArea(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public Result listAreaByParentCode(Map<String, Object> params) {
		Query query = new Query(params);
		List<SysAreaEntity> areas = sysAreaManager.listAreaByParentCode(query);
		return CommonUtils.msg(areas);
	}

}
