package cn.cashbang.core.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.sys.entity.SysWhiteIpEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2019年5月31日 下午3:00:25
 */
@Mapper
public interface SysWhiteIpMapper extends BaseMapper<SysWhiteIpEntity> {
	
	List<SysWhiteIpEntity> getByUserAndIp(String user,String ip);
	
}
