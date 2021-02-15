package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoReplyEntity;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月15日 下午2:46:57
 */
public interface BPhotoReplyService {

	Page<BPhotoReplyEntity> listBPhotoReply(Map<String, Object> params);
	
	Result saveBPhotoReply(BPhotoReplyEntity bPhotoReply);
	
	Result getBPhotoReplyById(Long id);
	
	Result updateBPhotoReply(BPhotoReplyEntity bPhotoReply);
	
	Result batchRemove(String[] id);
	
}
