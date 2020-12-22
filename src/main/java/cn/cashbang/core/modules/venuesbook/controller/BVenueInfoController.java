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
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
import cn.cashbang.core.modules.venuesbook.service.BVenueInfoService;

/**
 * 场馆信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:20:39
 */
@RestController
@RequestMapping("/venuesbook/venueinfo")
public class BVenueInfoController extends AbstractController {
	
	@Autowired
	private BVenueInfoService bVenueInfoService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BVenueInfoEntity> list(@RequestBody Map<String, Object> params) {
		return bVenueInfoService.listBVenueInfo(params);
	}
		
	/**
	 * 新增
	 * @param bVenueInfo
	 * @return
	 */
	@SysLog("新增场馆信息表")
	@RequestMapping("/save")
	public Result save(@RequestBody BVenueInfoEntity bVenueInfo) {
		return bVenueInfoService.saveBVenueInfo(bVenueInfo);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return bVenueInfoService.getBVenueInfoById(id);
	}
	
	/**
	 * 修改
	 * @param bVenueInfo
	 * @return
	 */
	@SysLog("修改场馆信息表")
	@RequestMapping("/update")
	public Result update(@RequestBody BVenueInfoEntity bVenueInfo) {
		return bVenueInfoService.updateBVenueInfo(bVenueInfo);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除场馆信息表")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bVenueInfoService.batchRemove(id);
	}
	
}
