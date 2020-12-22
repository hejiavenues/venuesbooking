package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BDicEntity;

/**
 * 字典表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午2:29:29
 */
public interface BDicService {

	Page<BDicEntity> listBDic(Map<String, Object> params);
	
	Result saveBDic(BDicEntity bDic);
	
	Result getBDicById(Long id);
	
	Result updateBDic(BDicEntity bDic);
	
	Result batchRemove(Long[] id);
	
}
