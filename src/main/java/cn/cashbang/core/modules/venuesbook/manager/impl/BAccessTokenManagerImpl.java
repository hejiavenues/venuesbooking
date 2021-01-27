package cn.cashbang.core.modules.venuesbook.manager.impl;

import java.util.List;

import cn.cashbang.core.modules.venuesbook.dao.BAccessTokenMapper;
import cn.cashbang.core.modules.venuesbook.entity.BAccessTokenEntity;
import cn.cashbang.core.modules.venuesbook.manager.BAccessTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年1月27日 PM6:08:16
 */
@Component("bAccessTokenManager")
public class BAccessTokenManagerImpl implements BAccessTokenManager {

	@Autowired
	private BAccessTokenMapper bAccessTokenMapper;
	

	@Override
	public List<BAccessTokenEntity> listBAccessToken(Page<BAccessTokenEntity> page, Query search) {
		return bAccessTokenMapper.listForPage(page, search);
	}

	@Override
	public int saveBAccessToken(BAccessTokenEntity bAccessToken) {
		return bAccessTokenMapper.save(bAccessToken);
	}

	@Override
	public BAccessTokenEntity getBAccessTokenById(Long id) {
		BAccessTokenEntity bAccessToken = bAccessTokenMapper.getObjectById(id);
		return bAccessToken;
	}

	@Override
	public int updateBAccessToken(BAccessTokenEntity bAccessToken) {
		return bAccessTokenMapper.update(bAccessToken);
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = bAccessTokenMapper.batchRemove(id);
		return count;
	}
	
}
