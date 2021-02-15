package cn.cashbang.core.modules.venuesbook.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoReplyEntity;
import cn.cashbang.core.modules.venuesbook.manager.BPhotoReplyManager;
import cn.cashbang.core.modules.venuesbook.service.BPhotoReplyService;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月15日 下午2:46:57
 */
@Service("bPhotoReplyService")
public class BPhotoReplyServiceImpl implements BPhotoReplyService {

	@Autowired
	private BPhotoReplyManager bPhotoReplyManager;

	@Override
	public Page<BPhotoReplyEntity> listBPhotoReply(Map<String, Object> params) {
		Query query = new Query(params);
		Page<BPhotoReplyEntity> page = new Page<>(query);
		bPhotoReplyManager.listBPhotoReply(page, query);
		return page;
	}

	@Override
	public Result saveBPhotoReply(BPhotoReplyEntity role) {
		int count = bPhotoReplyManager.saveBPhotoReply(role);
		return CommonUtils.msg(count);
	}

	@Override
	public Result getBPhotoReplyById(Long id) {
		BPhotoReplyEntity bPhotoReply = bPhotoReplyManager.getBPhotoReplyById(id);
		return CommonUtils.msg(bPhotoReply);
	}

	@Override
	public Result updateBPhotoReply(BPhotoReplyEntity bPhotoReply) {
		int count = bPhotoReplyManager.updateBPhotoReply(bPhotoReply);
		return CommonUtils.msg(count);
	}

	@Override
	public Result batchRemove(String[] id) {
		int count = bPhotoReplyManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
