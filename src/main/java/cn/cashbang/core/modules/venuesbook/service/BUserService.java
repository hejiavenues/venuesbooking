package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;

/**
 * 用户信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:02
 */
public interface BUserService {

	Page<BUserEntity> listBUser(Map<String, Object> params);
	
	Result saveBUser(BUserEntity bUser);
	
	Result getBUserById(String id);
	
	Result updateBUser(BUserEntity bUser);
	
	Result batchRemove(String[] id);

	Result loginUser(String code);
	
}
