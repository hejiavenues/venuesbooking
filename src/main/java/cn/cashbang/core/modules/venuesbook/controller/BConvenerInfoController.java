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
import cn.cashbang.core.modules.venuesbook.entity.BConvenerInfoEntity;
import cn.cashbang.core.modules.venuesbook.service.BConvenerInfoService;

/**
 * 召集人信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:13:27
 */
@RestController
@RequestMapping("/venuesbook/convenerinfo")
public class BConvenerInfoController extends AbstractController {
	
	@Autowired
	private BConvenerInfoService bConvenerInfoService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BConvenerInfoEntity> list(@RequestBody Map<String, Object> params) {
		return bConvenerInfoService.listBConvenerInfo(params);
	}
		
	/**
	 * 新增
	 * @param bConvenerInfo
	 * @return
	 */
	@RequestMapping("/save")
	public Result save(@RequestBody BConvenerInfoEntity bConvenerInfo) {
		return bConvenerInfoService.saveBConvenerInfo(bConvenerInfo);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return bConvenerInfoService.getBConvenerInfoById(id);
	}
	
	/**
	 * 修改
	 * @param bConvenerInfo
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody BConvenerInfoEntity bConvenerInfo) {
		return bConvenerInfoService.updateBConvenerInfo(bConvenerInfo);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bConvenerInfoService.batchRemove(id);
	}
	
}
