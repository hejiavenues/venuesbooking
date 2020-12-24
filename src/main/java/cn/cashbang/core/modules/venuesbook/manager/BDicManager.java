package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BDicEntity;

/**
 * 字典表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午2:29:29
 */
public interface BDicManager {

	List<BDicEntity> listBDic(Page<BDicEntity> page, Query search);
	
	int saveBDic(BDicEntity bDic);
	
	BDicEntity getBDicById(Long id);
	
	int updateBDic(BDicEntity bDic);
	
	int batchRemove(Long[] id);

	BDicEntity getBActivityDicByCode(String id);

	List<BDicEntity> listActivityTypeForPage(Page<BDicEntity> page, Query search);

	int savehdlx(BDicEntity role);

	int getMaxId();

	BDicEntity getBDicByCode(String id);

	int updatehdlx(BDicEntity bDic);
	
}
