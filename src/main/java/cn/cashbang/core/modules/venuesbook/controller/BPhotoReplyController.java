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
import cn.cashbang.core.modules.venuesbook.entity.BPhotoReplyEntity;
import cn.cashbang.core.modules.venuesbook.service.BPhotoReplyService;

/**
 * 
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月15日 下午2:46:57
 */
@RestController
@RequestMapping("/venuesbook/photoreply")
public class BPhotoReplyController extends AbstractController {
	
	@Autowired
	private BPhotoReplyService bPhotoReplyService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BPhotoReplyEntity> list(@RequestBody Map<String, Object> params) {
		return bPhotoReplyService.listBPhotoReply(params);
	}
		
	/**
	 * 新增
	 * @param bPhotoReply
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public Result save(@RequestBody BPhotoReplyEntity bPhotoReply) {
		return bPhotoReplyService.saveBPhotoReply(bPhotoReply);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return bPhotoReplyService.getBPhotoReplyById(id);
	}
	
	/**
	 * 修改
	 * @param bPhotoReply
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public Result update(@RequestBody BPhotoReplyEntity bPhotoReply) {
		return bPhotoReplyService.updateBPhotoReply(bPhotoReply);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody String[] id) {
		return bPhotoReplyService.batchRemove(id);
	}
	
}
