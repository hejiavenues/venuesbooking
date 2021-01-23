package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntryEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 团队报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:26
 */
public interface BTeamEntryManager {

	List<BTeamEntryEntity> listBTeamEntry(Page<BTeamEntryEntity> page, Query search);
	
	int saveBTeamEntry(BTeamEntryEntity bTeamEntry);
	
	BTeamEntryEntity getBTeamEntryById(Long id);
	
	int updateBTeamEntry(BTeamEntryEntity bTeamEntry);
	
	int batchRemove(Long[] id);

	List<BTeamEntryEntity> getTeamUserById(String tid);

	int updateTeamStatus(String tid,String uid);


}
