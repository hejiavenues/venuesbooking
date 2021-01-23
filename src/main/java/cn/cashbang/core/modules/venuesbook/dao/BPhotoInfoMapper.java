package cn.cashbang.core.modules.venuesbook.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BPhotoInfoEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 随拍信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:01
 */
@Mapper
public interface BPhotoInfoMapper extends BaseMapper<BPhotoInfoEntity> {

	int passApply(String[] id);
	
}
