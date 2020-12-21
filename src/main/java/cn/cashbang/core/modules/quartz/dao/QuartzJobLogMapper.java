package cn.cashbang.core.modules.quartz.dao;

import org.apache.ibatis.annotations.Mapper;
import cn.cashbang.core.modules.quartz.entity.QuartzJobLogEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 定时任务日志
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月20日 下午11:04:51
 */
@Mapper
public interface QuartzJobLogMapper extends BaseMapper<QuartzJobLogEntity> {

	int batchRemoveAll();
	
}
