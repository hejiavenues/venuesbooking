package cn.cashbang.core.modules.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.constant.SystemConstant;
import cn.cashbang.core.common.constant.SystemMsg;
import cn.cashbang.core.common.constant.SystemMsg.sysOrg;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.OSSUrlTool;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;
import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import cn.cashbang.core.modules.sys.service.SysUserService;

/**
 * 系统用户
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 下午9:04:59
 */
@RestController
@RequestMapping("/oss")
public class OSSController extends AbstractController {
	
	
	/**
	 * 用户列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/addSign")
	public Result list(@RequestBody JSONObject params) {
		if(!params.containsKey("theUrl")) {
			return Result.error("参数缺失");
		}
		return  Result.ok(OSSUrlTool.UrlAddSign(params.getString("theUrl")));
	}
	
	
	
	
	
	
}
