package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BCommitteesEntity;

/**
 * 居委会信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月23日 上午9:48:52
 */
public interface BCommitteesManager {

	List<BCommitteesEntity> listBCommittees(Page<BCommitteesEntity> page, Query search);
	
	int saveBCommittees(BCommitteesEntity bCommittees);
	
	BCommitteesEntity getBCommitteesById(String id);
	
	BCommitteesEntity getBCommitteesByName(String name);
	
	int updateBCommittees(BCommitteesEntity bCommittees);
	
	int batchRemove(String[] id);
	
}
