package cn.cashbang.core.modules.venuesbook.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BActivityEntryEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:46
 */
@Mapper
public interface BActivityEntryMapper extends BaseMapper<BActivityEntryEntity> {
	
}
