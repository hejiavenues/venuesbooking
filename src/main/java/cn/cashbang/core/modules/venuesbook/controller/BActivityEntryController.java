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
import cn.cashbang.core.modules.venuesbook.entity.BActivityEntryEntity;
import cn.cashbang.core.modules.venuesbook.service.BActivityEntryService;

/**
 * 活动报名记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:46
 */
@RestController
@RequestMapping("/venuesbook/activityentry")
public class BActivityEntryController extends AbstractController {
	
	@Autowired
	private BActivityEntryService bActivityEntryService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BActivityEntryEntity> list(@RequestBody Map<String, Object> params) {
		return bActivityEntryService.listBActivityEntry(params);
	}
		
	/**
	 * 新增
	 * @param bActivityEntry
	 * @return
	 */
	@RequestMapping("/save")
	public Result save(@RequestBody BActivityEntryEntity bActivityEntry) {
		return bActivityEntryService.saveBActivityEntry(bActivityEntry);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return bActivityEntryService.getBActivityEntryById(id);
	}
	
	/**
	 * 修改
	 * @param bActivityEntry
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody BActivityEntryEntity bActivityEntry) {
		return bActivityEntryService.updateBActivityEntry(bActivityEntry);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bActivityEntryService.batchRemove(id);
	}
	
}
