package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;

/**
 * 预约记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:09
 */
public interface BVenueBookManager {

	List<BVenueBookEntity> listBVenueBook(Page<BVenueBookEntity> page, Query search);
	
	int saveBVenueBook(BVenueBookEntity bVenueBook);
	
	BVenueBookEntity getBVenueBookById(Long id);
	
	int updateBVenueBook(BVenueBookEntity bVenueBook);
	
	int batchRemove(Long[] id);
	
	int deleteByVenueId(String venueId);

	List<BVenueBookEntity> getVenueBookById(String venueId);
	
}
