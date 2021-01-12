package cn.cashbang.core.modules.venuesbook.dao;

import cn.cashbang.core.modules.venuesbook.entity.BTeamEntity;
import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

import java.util.List;

/**
 * 活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:35
 */
@Mapper
public interface BActivitiesMapper extends BaseMapper<BActivitiesEntity> {

    List<BActivitiesEntity> listActByUserId(String uid);
}
