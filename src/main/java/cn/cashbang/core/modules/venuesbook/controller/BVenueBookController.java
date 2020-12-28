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
import cn.cashbang.core.modules.venuesbook.entity.BVenueBookEntity;
import cn.cashbang.core.modules.venuesbook.service.BVenueBookService;

/**
 * 预约记录表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:09
 */
@RestController
@RequestMapping("/venuesbook/venuebook")
public class BVenueBookController extends AbstractController {
	
	@Autowired
	private BVenueBookService bVenueBookService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BVenueBookEntity> list(@RequestBody Map<String, Object> params) {
		return bVenueBookService.listBVenueBook(params);
	}
		
	/**
	 * 新增
	 * @param bVenueBook
	 * @return
	 */
	@RequestMapping("/save")
	public Result save(@RequestBody BVenueBookEntity bVenueBook) {
		return bVenueBookService.saveBVenueBook(bVenueBook);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return bVenueBookService.getBVenueBookById(id);
	}
	
	/**
	 * 修改
	 * @param bVenueBook
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody BVenueBookEntity bVenueBook) {
		return bVenueBookService.updateBVenueBook(bVenueBook);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bVenueBookService.batchRemove(id);
	}
	
}
