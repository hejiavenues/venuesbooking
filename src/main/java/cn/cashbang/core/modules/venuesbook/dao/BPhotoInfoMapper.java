package cn.cashbang.core.modules.venuesbook.dao;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.venuesbook.entity.BPhotoInfoEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

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
	int getOperateCount(@Param("operateId")String operateId, @Param("week")String week, @Param("month")String month);
    List<BPhotoInfoEntity> listReplyPage(Page<BPhotoInfoEntity> page, Query query);
}
