package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BVenueBookMapper;
import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;
import cn.cashbang.core.modules.venuesbook.manager.BVenueBookManager;

/**
 * 预约记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:09
 */
@Component("bVenueBookManager")
public class BVenueBookManagerImpl implements BVenueBookManager {

	@Autowired
	private BVenueBookMapper bVenueBookMapper;
	

	@Override
	public List<BVenueBookEntity> listBVenueBook(Page<BVenueBookEntity> page, Query search) {
		return bVenueBookMapper.listForPage(page, search);
	}

	@Override
	public int saveBVenueBook(BVenueBookEntity bVenueBook) {
		return bVenueBookMapper.save(bVenueBook);
	}

	@Override
	public BVenueBookEntity getBVenueBookById(Long id) {
		BVenueBookEntity bVenueBook = bVenueBookMapper.getObjectById(id);
		return bVenueBook;
	}

	@Override
	public int updateBVenueBook(BVenueBookEntity bVenueBook) {
		return bVenueBookMapper.update(bVenueBook);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = bVenueBookMapper.batchRemove(id);
		return count;
	}
	
}
