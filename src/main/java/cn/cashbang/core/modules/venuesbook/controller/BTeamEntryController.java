package cn.cashbang.core.modules.venuesbook.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntryEntity;
import cn.cashbang.core.modules.venuesbook.service.BTeamEntryService;

/**
 * 团队报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:26
 */
@RestController
@RequestMapping("/venuesbook/teamentry")
public class BTeamEntryController extends AbstractController {
	
	@Autowired
	private BTeamEntryService bTeamEntryService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BTeamEntryEntity> list(@RequestBody Map<String, Object> params) {
		return bTeamEntryService.listBTeamEntry(params);
	}
		
	/**
	 * 新增
	 * @param bTeamEntry
	 * @return
	 */
	@SysLog("新增团队报名记录表")
	@RequestMapping("/save")
	public Result save(@RequestBody BTeamEntryEntity bTeamEntry) {
		return bTeamEntryService.saveBTeamEntry(bTeamEntry);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return bTeamEntryService.getBTeamEntryById(id);
	}
	
	/**
	 * 修改
	 * @param bTeamEntry
	 * @return
	 */
	@SysLog("修改团队报名记录表")
	@RequestMapping("/update")
	public Result update(@RequestBody BTeamEntryEntity bTeamEntry) {
		return bTeamEntryService.updateBTeamEntry(bTeamEntry);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除团队报名记录表")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bTeamEntryService.batchRemove(id);
	}
	
}
