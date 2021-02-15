package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;

/**
 * 用户信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:02
 */
public interface BUserManager {

	List<BUserEntity> listBUser(Page<BUserEntity> page, Query search);
	
	int saveBUser(BUserEntity bUser);
	
	BUserEntity getBUserById(String id);
	
	int updateBUser(BUserEntity bUser);
	
	int batchRemove(String[] id);

	BUserEntity getUserByOpenId(String openId);
}
