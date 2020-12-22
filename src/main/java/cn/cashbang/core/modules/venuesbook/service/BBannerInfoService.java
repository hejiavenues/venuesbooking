package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BBannerInfoEntity;

/**
 * banner图表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:17:36
 */
public interface BBannerInfoService {

	Page<BBannerInfoEntity> listBBannerInfo(Map<String, Object> params);
	
	Result saveBBannerInfo(BBannerInfoEntity bBannerInfo);
	
	Result getBBannerInfoById(Long id);
	
	Result updateBBannerInfo(BBannerInfoEntity bBannerInfo);
	
	Result batchRemove(Long[] id);
	
}
