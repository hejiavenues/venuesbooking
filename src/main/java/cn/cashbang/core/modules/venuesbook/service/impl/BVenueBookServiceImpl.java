package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;
import cn.cashbang.core.modules.venuesbook.manager.BVenueBookManager;
import cn.cashbang.core.modules.venuesbook.service.BVenueBookService;

/**
 * 预约记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:09
 */
@Service("bVenueBookService")
public class BVenueBookServiceImpl implements BVenueBookService {

	@Autowired
	private BVenueBookManager bVenueBookManager;

	@Override
	public Page<BVenueBookEntity> listBVenueBook(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BVenueBookEntity> page = new Page<>(query);
		bVenueBookManager.listBVenueBook(page, query);
		return page;
	}

	@Override
	public Result saveBVenueBook(BVenueBookEntity role) {
		int count = bVenueBookManager.saveBVenueBook(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBVenueBookById(Long id) {
		BVenueBookEntity bVenueBook = bVenueBookManager.getBVenueBookById(id);
		return CommonUtils.msg(bVenueBook);
	}

	@Override
	public Result updateBVenueBook(BVenueBookEntity bVenueBook) {
		int count = bVenueBookManager.updateBVenueBook(bVenueBook);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(Long[] id) {
		int count = bVenueBookManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public List<BVenueBookEntity> getBVenueBookByVenueId(String venueId) {
		List<BVenueBookEntity> list = bVenueBookManager.getVenueBookById(venueId);
		return  list;
	}

	@Override
	public Result queryBookByUserId(String userId,String activityId) {
		List<BVenueBookEntity> list = bVenueBookManager.queryBookByUserId(userId,activityId);
		return Result.ok().put("raws", list);
	}

	@Override
	public BVenueBookEntity countUserBookTime(String uid){
		return bVenueBookManager.countUserBookTime(uid);
	}

    @Override
    public BVenueBookEntity getBookStatusById(String venueId,String bookDate,String bookTime){
        return bVenueBookManager.getBookStatusById(venueId,bookDate,bookTime);
    }
}
