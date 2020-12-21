package cn.cashbang.core.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户token
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年9月3日 上午3:29:17
 */
@Mapper
public interface SysUserTokenMapper extends BaseMapper<SysUserTokenEntity> {

	SysUserTokenEntity getByToken(String token);
	
	SysUserTokenEntity getByUserId(Long userId);
	
}
