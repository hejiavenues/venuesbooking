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
import cn.cashbang.core.modules.venuesbook.entity.BCommitteesEntity;
import cn.cashbang.core.modules.venuesbook.service.BCommitteesService;

/**
 * 居委会信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月23日 上午9:48:52
 */
@RestController
@RequestMapping("/venuesbook/committee")
public class BCommitteesController extends AbstractController {
	
	@Autowired
	private BCommitteesService bCommitteesService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BCommitteesEntity> list(@RequestBody Map<String, Object> params) {
		return bCommitteesService.listBCommittees(params);
	}
		
	/**
	 * 新增
	 * @param bCommittees
	 * @return
	 */
	@SysLog("新增居委会信息表")
	@RequestMapping("/save")
	public Result save(@RequestBody BCommitteesEntity bCommittees) {
		return bCommitteesService.saveBCommittees(bCommittees);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody String id) {
		return bCommitteesService.getBCommitteesById(id);
	}
	
	/**
	 * 修改
	 * @param bCommittees
	 * @return
	 */
	@SysLog("修改居委会信息表")
	@RequestMapping("/update")
	public Result update(@RequestBody BCommitteesEntity bCommittees) {
		return bCommitteesService.updateBCommittees(bCommittees);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除居委会信息表")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody String[] id) {
		return bCommitteesService.batchRemove(id);
	}
	
}
