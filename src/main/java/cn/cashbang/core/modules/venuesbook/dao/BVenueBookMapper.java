package cn.cashbang.core.modules.venuesbook.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 预约记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:09
 */
@Mapper
public interface BVenueBookMapper extends BaseMapper<BVenueBookEntity> {
	
}
