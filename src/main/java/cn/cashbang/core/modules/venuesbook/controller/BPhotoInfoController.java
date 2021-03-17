package cn.cashbang.core.modules.venuesbook.controller;

import java.util.Map;

import cn.cashbang.core.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoInfoEntity;
import cn.cashbang.core.modules.venuesbook.service.BPhotoInfoService;

/**
 * 随拍信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:01
 */
@RestController
@RequestMapping("/venuesbook/photoinfo")
public class BPhotoInfoController extends AbstractController {
	
	@Autowired
	private BPhotoInfoService bPhotoInfoService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BPhotoInfoEntity> list(@RequestBody Map<String, Object> params) {
		return bPhotoInfoService.listBPhotoInfo(params);
	}
		
	/**
	 * 新增
	 * @param bPhotoInfo
	 * @return
	 */
	@RequestMapping("/save")
	public Result save(@RequestBody BPhotoInfoEntity bPhotoInfo) {
		return bPhotoInfoService.saveBPhotoInfo(bPhotoInfo);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody String id) {
		return bPhotoInfoService.getBPhotoInfoById(id);
	}
	
	/**
	 * 修改
	 * @param bPhotoInfo
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody BPhotoInfoEntity bPhotoInfo) {
		return bPhotoInfoService.updateBPhotoInfo(bPhotoInfo);
	}

    /**
     * 修改
     * @param bPhotoInfo
     * @return
     */
    @RequestMapping("/updateStaus")
    public Result updateStaus(@RequestBody BPhotoInfoEntity bPhotoInfo) {

        SysUserEntity user=getUser();
        return bPhotoInfoService.updateStaus(bPhotoInfo,user);
    }
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody String[] id) {
		return bPhotoInfoService.batchRemove(id);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/passApply")
	public Result passApply(@RequestBody String[] id) {
		return bPhotoInfoService.passApply(id);
	}
	
}
