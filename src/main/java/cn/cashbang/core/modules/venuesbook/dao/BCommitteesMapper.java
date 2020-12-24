package cn.cashbang.core.modules.venuesbook.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BCommitteesEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 居委会信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月23日 上午9:48:52
 */
@Mapper
public interface BCommitteesMapper extends BaseMapper<BCommitteesEntity> {
	BCommitteesEntity getCommitteeByName(String cname);
}
