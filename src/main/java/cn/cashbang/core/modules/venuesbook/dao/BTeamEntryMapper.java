package cn.cashbang.core.modules.venuesbook.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BTeamEntryEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 团队报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:26
 */
@Mapper
public interface BTeamEntryMapper extends BaseMapper<BTeamEntryEntity> {

    List<BTeamEntryEntity> getTeamUserById(String tid);
    
    void deleteByTid(String tid);

    int updateTeamStatus(@Param("tid") String tid, @Param("uid") String uid);
}
