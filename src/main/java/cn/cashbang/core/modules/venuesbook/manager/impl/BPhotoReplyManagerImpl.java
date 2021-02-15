package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.dao.BPhotoReplyMapper;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoReplyEntity;
import cn.cashbang.core.modules.venuesbook.manager.BPhotoReplyManager;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月15日 下午2:46:57
 */
@Component("bPhotoReplyManager")
public class BPhotoReplyManagerImpl implements BPhotoReplyManager {

	@Autowired
	private BPhotoReplyMapper bPhotoReplyMapper;
	

	@Override
	public List<BPhotoReplyEntity> listBPhotoReply(Page<BPhotoReplyEntity> page, Query search) {
		return bPhotoReplyMapper.listForPage(page, search);
	}

	@Override
	public int saveBPhotoReply(BPhotoReplyEntity bPhotoReply) {
		return bPhotoReplyMapper.save(bPhotoReply);
	}

	@Override
	public BPhotoReplyEntity getBPhotoReplyById(Long id) {
		BPhotoReplyEntity bPhotoReply = bPhotoReplyMapper.getObjectById(id);
		return bPhotoReply;
	}

	@Override
	public int updateBPhotoReply(BPhotoReplyEntity bPhotoReply) {
		return bPhotoReplyMapper.update(bPhotoReply);
	}

	@Override
	public int batchRemove(String[] id) {
		int count = bPhotoReplyMapper.batchRemove(id);
		return count;
	}
	
}
