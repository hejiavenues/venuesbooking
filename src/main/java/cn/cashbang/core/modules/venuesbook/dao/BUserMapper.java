package cn.cashbang.core.modules.venuesbook.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BUserEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 用户信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:02
 */
@Mapper
public interface BUserMapper extends BaseMapper<BUserEntity> {
	
}
