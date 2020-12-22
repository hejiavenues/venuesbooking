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
import cn.cashbang.core.modules.venuesbook.entity.BDicEntity;
import cn.cashbang.core.modules.venuesbook.service.BDicService;

/**
 * 字典表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午2:29:29
 */
@RestController
@RequestMapping("/venuesbook/dic")
public class BDicController extends AbstractController {
	
	@Autowired
	private BDicService bDicService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BDicEntity> list(@RequestBody Map<String, Object> params) {
		return bDicService.listBDic(params);
	}
		
	/**
	 * 新增
	 * @param bDic
	 * @return
	 */
	@SysLog("新增字典表")
	@RequestMapping("/save")
	public Result save(@RequestBody BDicEntity bDic) {
		return bDicService.saveBDic(bDic);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return bDicService.getBDicById(id);
	}
	
	/**
	 * 修改
	 * @param bDic
	 * @return
	 */
	@SysLog("修改字典表")
	@RequestMapping("/update")
	public Result update(@RequestBody BDicEntity bDic) {
		return bDicService.updateBDic(bDic);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除字典表")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bDicService.batchRemove(id);
	}
	
}
