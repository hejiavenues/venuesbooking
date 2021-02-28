package cn.cashbang.core.modules.venuesbook.dao;

import cn.cashbang.core.modules.venuesbook.entity.BActivityEntryEntity;
import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BComActivityEntryEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月28日 PM2:16:50
 */
@Mapper
public interface BComActivityEntryMapper extends BaseMapper<BComActivityEntryEntity> {
    List<BComActivityEntryEntity> getUserListById(@Param("activityId")String activityId, @Param("uid")String uid);
}
