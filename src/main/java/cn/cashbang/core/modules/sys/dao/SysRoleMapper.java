package cn.cashbang.core.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.sys.entity.SysRoleEntity;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 系统角色
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月12日 上午12:35:51
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

	List<String> listUserRoles(Long userId);

	SysRoleEntity selectByRole(SysRoleEntity role);

	List<SysRoleEntity> listForChannelPage(Page<SysRoleEntity> page, Query search);

	SysUserEntity userContainRole(@Param("sign") String sign,@Param("userId") Long userId);

}
