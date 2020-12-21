package cn.cashbang.core.modules.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.constant.SystemMsg;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.ShiroUtils;
import cn.cashbang.core.modules.sys.entity.SysOrgEntity;
import cn.cashbang.core.modules.sys.service.SysOrgService;

/**
 * 组织机构
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月17日 上午11:35:00
 */
@RestController
@RequestMapping("/sys/org")
public class SysOrgController extends AbstractController {

	@Autowired
	private SysOrgService sysOrgService;
	
	/**
	 * 机构列表
	 * @return
	 */
	@RequestMapping("/list")
	public List<SysOrgEntity> list() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgIdPath", getOrgIdPath());
		return sysOrgService.listOrg(params);
	}
	
	/**
	 * 机构列表
	 * @return
	 */
	@RequestMapping("/listInChannel")
	public List<SysOrgEntity> listInChannel(String orgIdPath ) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgIdPath", orgIdPath);
		return sysOrgService.listOrg(params);
	}
	
	/**
	 * 树形机构列表，机构新增、编辑
	 * @return
	 */
	@RequestMapping("/select")
	public List<SysOrgEntity> select() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgIdPath", getOrgIdPath());
		return sysOrgService.listOrgTree(params);
	}
	
	/**
	 * 树形机构列表，机构新增、编辑
	 * @return
	 */
	@RequestMapping("/selectByCode")
	public Result selectByCode(String code) {
		return sysOrgService.getByCode(code);
	}
	/**
	 * 新增机构
	 * @param org
	 * @return
	 */
	@SysLog("新增机构")
	@RequestMapping("/save")
	public Result save(@RequestBody SysOrgEntity org) {
		return sysOrgService.saveOrg(org);
	}
	
	/**
	 * 渠道初始化机构
	 * @param org
	 * @return
	 */
	@SysLog("渠道初始化机构")
	@RequestMapping("/saveChannelOrg")
	public Result saveChannelOrg(@RequestBody SysOrgEntity org) {
		return sysOrgService.saveChannelOrg(org);
	}
	
	/**
	 * 新增机构
	 * @param org
	 * @return
	 */
	@SysLog("检查only")
	@RequestMapping("/selectOnly")
	public Result selectCode(@RequestParam String table,String col,String only) {
		logger.info("+++++++++++++++++{},{},{}",table,col,only);
		return sysOrgService.selectCode(table ,col,only);
	}
	
	
	/**
	 * 根据id查询机构详情
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/info")
	public Result info(@RequestBody Long orgId) {
		return sysOrgService.getOrg(orgId);
	}
	
	/**
	 * 修改机构
	 * @param org
	 * @return
	 */
	@SysLog("修改机构")
	@RequestMapping("/update")
	public Result update(@RequestBody SysOrgEntity org) {
		return sysOrgService.updateOrg(org);
	}
	
	/**
	 * 删除机构
	 * @param id
	 * @return
	 */
	@SysLog("删除机构")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return sysOrgService.bactchRemoveOrg(id);
	}
	
	/*
	 * 查询催收机构
	 */
	/**
	 * 树形机构列表，机构新增、编辑,催收查询渠道概念弱化必要查询从前端传入channel
	 * @return
	 */
	@RequestMapping("/getCsOrg")
	public Result getCsOrg(@RequestParam String channel) {
		//String channel=ShiroUtils.getUserEntity().getChannel();
		return sysOrgService.getCsOrgs(channel);
	}
	
}
