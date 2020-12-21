package cn.cashbang.core.modules.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cashbang.core.common.utils.ShiroUtils;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;

/**
 * Controller公共组件
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 下午2:43:23
 */
public abstract class AbstractController {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
	
	protected String getOrgIdPath() {
		return ShiroUtils.getOrgIdPath();
	}
}
