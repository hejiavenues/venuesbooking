package cn.cashbang.core.modules.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.entity.SysWhiteIpEntity;
import cn.cashbang.core.modules.sys.service.SysWhiteIpService;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2019年5月31日 下午3:00:25
 */
@RestController
@RequestMapping("/sys/whiteIp")
public class SysWhiteIpController extends AbstractController {
	
	@Autowired
	private SysWhiteIpService sysWhiteIpService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<SysWhiteIpEntity> list(@RequestBody Map<String, Object> params) {
		return sysWhiteIpService.listSysWhiteIp(params);
	}
		
	/**
	 * 新增
	 * @param sysWhiteIp
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public Result save(@RequestBody SysWhiteIpEntity sysWhiteIp) {
		return sysWhiteIpService.saveSysWhiteIp(sysWhiteIp);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById( String id) {
		return sysWhiteIpService.getSysWhiteIpById(id);
	}
	
	/**
	 * 修改
	 * @param sysWhiteIp
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public Result update(@RequestBody SysWhiteIpEntity sysWhiteIp) {
		return sysWhiteIpService.updateSysWhiteIp(sysWhiteIp);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody String[] id) {
		return sysWhiteIpService.batchRemove(id);
	}
	
}
