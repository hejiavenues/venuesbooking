package cn.cashbang.core.modules.query.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.query.entity.PayLogEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 订单日志
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2018年7月30日 下午3:54:19
 */
@Mapper
public interface PayLogMapper extends BaseMapper<PayLogEntity> {
	
}
