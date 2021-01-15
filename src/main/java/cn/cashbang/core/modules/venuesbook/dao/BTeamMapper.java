package cn.cashbang.core.modules.venuesbook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BTeamEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 团队信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 上午11:57:39
 */
@Mapper
public interface BTeamMapper extends BaseMapper<BTeamEntity> {

    List<BTeamEntity> listTeamByUserId(String uid);

    List<BTeamEntity> listTeamByCreateUserId(String uid);
}
