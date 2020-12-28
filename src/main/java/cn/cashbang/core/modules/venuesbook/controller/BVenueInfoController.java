package cn.cashbang.core.modules.venuesbook.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BUpdateVenueTime;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoDTO;
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
	public Result save(MultipartFile imgFile, BVenueInfoEntity bVenueInfo) {
		logger.info("新增banner配置开始，bBannerInfo：{}",bVenueInfo.toString());
		Result resultEntity = new Result();
		if(imgFile == null){
			logger.error("新增banner配置信息，imgFile为空");
			resultEntity = Result.error(100, "banner配置信息图片为空");
			return resultEntity;
		}
		return bVenueInfoService.saveBVenueInfo(imgFile,bVenueInfo);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody String id) {
		if(!StringUtils.isEmpty(id)) {
			id = id.replace("\"", "");
		}
		return bVenueInfoService.getBVenueInfoById(id);
	}
	
	/**
	 * 修改
	 * @param bVenueInfo
	 * @return
	 */
	@SysLog("修改场馆信息表")
	@RequestMapping("/update")
	public Result update(@RequestBody MultipartFile imgFile, BVenueInfoDTO bVenueInfo) {
		BVenueInfoEntity b = new BVenueInfoEntity();
		BeanUtils.copyProperties(bVenueInfo, b);
		return bVenueInfoService.updateBVenueInfo(imgFile,b);
	}
	
	/**
	 * 修改
	 * @param bVenueInfo
	 * @return
	 */
	@RequestMapping("/updateUnableTime")
	public Result updateUnableTime(BUpdateVenueTime bVenueInfo) {
		return bVenueInfoService.updateUnableTime(bVenueInfo);
	}
	
	/**
	 * 修改
	 * @param bVenueInfo
	 * @return
	 */
	@RequestMapping("/updateAllUnableTime")
	public Result updateAllUnableTime(BUpdateVenueTime bVenueInfo) {
		return bVenueInfoService.updateAllUnableTime(bVenueInfo);
	}
	
	/**
	 * 修改
	 * @param bVenueInfo
	 * @return
	 */
	@RequestMapping("/getDynamicTags")
	public Result getDynamicTags(String venueId) {
		System.out.println("-----------------venueId:"+venueId+"------------------------------");
		return bVenueInfoService.getDynamicTags(venueId);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除场馆信息表")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody String[] id) {
		return bVenueInfoService.batchRemove(id);
	}
	
}
