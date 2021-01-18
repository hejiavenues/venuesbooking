package cn.cashbang.core.modules.venuesbook.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BConvenerInfoEntity;

/**
 * 召集人信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:27
 */
public interface BConvenerInfoService {

	Page<BConvenerInfoEntity> listBConvenerInfo(Map<String, Object> params);
	
	Result saveBConvenerInfo(BConvenerInfoEntity bConvenerInfo);
	
	Result getBConvenerInfoById(String id);
	
	Result updateBConvenerInfo(BConvenerInfoEntity bConvenerInfo);
	
	Result batchRemove(String[] id);
	
}
