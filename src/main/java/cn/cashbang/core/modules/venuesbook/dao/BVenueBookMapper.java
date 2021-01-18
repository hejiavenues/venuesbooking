package cn.cashbang.core.modules.venuesbook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

	int deleteByVenueId(String venueId);

	List<BVenueBookEntity> getVenueBookById(String venueId);

	// 查询用户预约的场馆
	List<BVenueBookEntity> queryBookByUserId(@Param("userId") String userId,@Param("activityId") String activityId);

	BVenueBookEntity getBookStatusById(String venueId,String bookDate,String bookTime);
}
