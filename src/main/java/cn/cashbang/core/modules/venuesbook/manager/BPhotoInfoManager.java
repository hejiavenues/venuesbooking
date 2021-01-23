package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoInfoEntity;

/**
 * 随拍信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:01
 */
public interface BPhotoInfoManager {

	List<BPhotoInfoEntity> listBPhotoInfo(Page<BPhotoInfoEntity> page, Query search);
	
	int saveBPhotoInfo(BPhotoInfoEntity bPhotoInfo);
	
	BPhotoInfoEntity getBPhotoInfoById(Long id);
	
	int updateBPhotoInfo(BPhotoInfoEntity bPhotoInfo);
	
	int batchRemove(String[] id);

	int passApply(String[] id);
	
}
