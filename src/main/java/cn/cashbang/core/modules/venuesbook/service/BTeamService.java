package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntity;

/**
 * 团队信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 上午11:57:39
 */
public interface BTeamService {

	Page<BTeamEntity> listBTeam(Map<String, Object> params);
	
	Result saveBTeam(BTeamEntity bTeam);
	
	Result getBTeamById(String id);
	
	Result updateBTeam(BTeamEntity bTeam);
	
	Result batchRemove(Long[] id);
	
}
