package cn.cashbang.core.modules.venuesbook.service;

import java.util.List;
import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;

/**
 * 预约记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:09
 */
public interface BVenueBookService {

	Page<BVenueBookEntity> listBVenueBook(Map<String, Object> params);
	
	Result saveBVenueBook(BVenueBookEntity bVenueBook);
	
	Result getBVenueBookById(Long id);
	
	Result updateBVenueBook(BVenueBookEntity bVenueBook);
	
	Result batchRemove(Long[] id);

	List<BVenueBookEntity> getBVenueBookByVenueId(String venueId);

	// 查询用户预约的场馆
	Result queryBookByUserId(String userId);

}
