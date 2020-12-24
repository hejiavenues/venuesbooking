package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BCommitteesEntity;

/**
 * 居委会信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月23日 上午9:48:52
 */
public interface BCommitteesService {

	Page<BCommitteesEntity> listBCommittees(Map<String, Object> params);
	
	Result saveBCommittees(BCommitteesEntity bCommittees);
	
	Result getBCommitteesById(String id);
	
	Result updateBCommittees(BCommitteesEntity bCommittees);
	
	Result batchRemove(String[] id);
	
}
