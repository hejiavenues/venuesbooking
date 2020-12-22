package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;

/**
 * 场馆信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:20:39
 */
public interface BVenueInfoService {

	Page<BVenueInfoEntity> listBVenueInfo(Map<String, Object> params);
	
	Result saveBVenueInfo(BVenueInfoEntity bVenueInfo);
	
	Result getBVenueInfoById(Long id);
	
	Result updateBVenueInfo(BVenueInfoEntity bVenueInfo);
	
	Result batchRemove(Long[] id);
	
}
