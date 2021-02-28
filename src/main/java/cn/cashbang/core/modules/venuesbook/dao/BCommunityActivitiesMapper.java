package cn.cashbang.core.modules.venuesbook.dao;

import cn.cashbang.core.modules.sys.dao.BaseMapper;
import cn.cashbang.core.modules.venuesbook.entity.BCommunityActivitiesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 社区活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月27日 PM9:46:01
 */
@Mapper
public interface BCommunityActivitiesMapper extends BaseMapper<BCommunityActivitiesEntity> {

    List<BCommunityActivitiesEntity> listActByUserId(String uid);
    List<BCommunityActivitiesEntity> listByCreateUser(String uid);

}
