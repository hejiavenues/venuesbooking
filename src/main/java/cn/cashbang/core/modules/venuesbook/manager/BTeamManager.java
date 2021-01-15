package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntity;

/**
 * 团队信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 上午11:57:39
 */
public interface BTeamManager {

	List<BTeamEntity> listBTeam(Page<BTeamEntity> page, Query search);
	
	int saveBTeam(BTeamEntity bTeam);
	
	BTeamEntity getBTeamById(String id);
	
	int updateBTeam(BTeamEntity bTeam);
	
	int batchRemove(Long[] id);

	List<BTeamEntity> listTeamByUserId(String uid);

	List<BTeamEntity> listTeamByCreateUserId(String uid);
}
