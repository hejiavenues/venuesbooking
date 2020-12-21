package cn.cashbang.core.modules.quartz.dao;

import org.apache.ibatis.annotations.Mapper;
import cn.cashbang.core.modules.quartz.entity.QuartzJobEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;


/**
 * 定时任务
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月20日 下午11:19:55
 */
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJobEntity> {

}
