package cn.cashbang.core.modules.venuesbook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BDicEntity;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 字典表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午2:29:29
 */
@Mapper
public interface BDicMapper extends BaseMapper<BDicEntity> {

	int getMaxId();
	
	BDicEntity getBActivityDicByCode(String id);
	
	BDicEntity listActivityTypeForPage(String id);
	
	List<BDicEntity> listActivityTypeForPage(Page<BDicEntity> page, Query query);

	int savehdlx(BDicEntity bDic);

	BDicEntity getBDicByCode(String id);

	int updatehdlx(BDicEntity bDic);
	
}
