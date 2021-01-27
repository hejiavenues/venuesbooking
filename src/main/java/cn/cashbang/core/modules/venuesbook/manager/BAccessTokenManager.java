package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BAccessTokenEntity;


/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年1月27日 PM6:08:16
 */
public interface BAccessTokenManager {

	List<BAccessTokenEntity> listBAccessToken(Page<BAccessTokenEntity> page, Query search);
	
	int saveBAccessToken(BAccessTokenEntity bAccessToken);
	
	BAccessTokenEntity getBAccessTokenById(Long id);
	
	int updateBAccessToken(BAccessTokenEntity bAccessToken);
	
	int batchRemove(Long[] id);
	
}
