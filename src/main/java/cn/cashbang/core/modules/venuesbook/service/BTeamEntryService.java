package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntryEntity;

/**
 * 团队报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:26
 */
public interface BTeamEntryService {

	Page<BTeamEntryEntity> listBTeamEntry(Map<String, Object> params);
	
	Result saveBTeamEntry(BTeamEntryEntity bTeamEntry);
	
	Result getBTeamEntryById(Long id);
	
	Result updateBTeamEntry(BTeamEntryEntity bTeamEntry);
	
	Result batchRemove(Long[] id);
	
}
