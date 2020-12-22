package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;

/**
 * 场馆信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:20:39
 */
public interface BVenueInfoManager {

	List<BVenueInfoEntity> listBVenueInfo(Page<BVenueInfoEntity> page, Query search);
	
	int saveBVenueInfo(BVenueInfoEntity bVenueInfo);
	
	BVenueInfoEntity getBVenueInfoById(Long id);
	
	int updateBVenueInfo(BVenueInfoEntity bVenueInfo);
	
	int batchRemove(Long[] id);
	
}
