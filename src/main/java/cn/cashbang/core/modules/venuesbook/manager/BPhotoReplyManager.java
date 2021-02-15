package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoReplyEntity;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月15日 下午2:46:57
 */
public interface BPhotoReplyManager {

	List<BPhotoReplyEntity> listBPhotoReply(Page<BPhotoReplyEntity> page, Query search);
	
	int saveBPhotoReply(BPhotoReplyEntity bPhotoReply);
	
	BPhotoReplyEntity getBPhotoReplyById(Long id);
	
	int updateBPhotoReply(BPhotoReplyEntity bPhotoReply);
	
	int batchRemove(String[] id);
	
}
