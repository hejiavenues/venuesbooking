package cn.cashbang.core.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.sys.entity.SysLogEntity;

/**
 * 系统日志 
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月14日 下午8:19:01
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

	int batchRemoveAll();
	
}
