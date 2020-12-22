package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BBannerInfoEntity;

/**
 * banner图表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:17:36
 */
public interface BBannerInfoManager {

	List<BBannerInfoEntity> listBBannerInfo(Page<BBannerInfoEntity> page, Query search);
	
	int saveBBannerInfo(BBannerInfoEntity bBannerInfo);
	
	BBannerInfoEntity getBBannerInfoById(Long id);
	
	int updateBBannerInfo(BBannerInfoEntity bBannerInfo);
	
	int batchRemove(Long[] id);
	
}
