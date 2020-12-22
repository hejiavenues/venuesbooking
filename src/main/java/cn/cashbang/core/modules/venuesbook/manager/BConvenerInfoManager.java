package cn.cashbang.core.modules.venuesbook.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.venuesbook.entity.BConvenerInfoEntity;

/**
 * 召集人信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:27
 */
public interface BConvenerInfoManager {

	List<BConvenerInfoEntity> listBConvenerInfo(Page<BConvenerInfoEntity> page, Query search);
	
	int saveBConvenerInfo(BConvenerInfoEntity bConvenerInfo);
	
	BConvenerInfoEntity getBConvenerInfoById(Long id);
	
	int updateBConvenerInfo(BConvenerInfoEntity bConvenerInfo);
	
	int batchRemove(Long[] id);
	
}
