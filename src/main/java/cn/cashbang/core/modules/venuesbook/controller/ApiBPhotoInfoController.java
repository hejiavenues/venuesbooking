package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoInfoEntity;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
import cn.cashbang.core.modules.venuesbook.service.BPhotoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 随拍信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:12:01
 */
@RestController
@RequestMapping("/api/photoinfo")
public class ApiBPhotoInfoController extends AbstractController {
	
	@Autowired
	private BPhotoInfoService bPhotoInfoService;
	
	/**
	 * 列表
	 * @param page
	 * @return
	 */
	@RequestMapping("/getPhotoList")
	public  Map<String, Object> getPhotoList(int page) {

		Map<String, Object> params = new HashMap<>();
		Map<String, Object> result = new HashMap<>();

		params.put("pageNumber",page);
		params.put("pageSize",2);
		params.put("keyword",null);
		params.put("sortOrde","asc");

		Page<BPhotoInfoEntity> list =  bPhotoInfoService.listBPhotoInfo(params);
		if(list.getTotal()>0){
			result.put("code",0);
			result.put("rows",list.getRows());
			result.put("page",page);
			result.put("msg","查询成功！");
		}
		else{
			result.put("code",-1);
			result.put("rows",null);
			result.put("msg","没有查询到数据！");
		}
		return result;
	}
		
	/**
	 * 新增
	 * @param userId
	 * @return
	 */
	@RequestMapping("/sendPhoto")
	public Result sendPhoto(String userId,String content) {

		BPhotoInfoEntity bPhotoInfo = new BPhotoInfoEntity();
		bPhotoInfo.setUid(userId);
		bPhotoInfo.setContent(content);
		bPhotoInfo.setUname("王阿姨");
		bPhotoInfo.setStatus(1);     //状态 1.正常 2.删除
		bPhotoInfo.setPitureUrls("");
		String uuid = CommonUtils.createUUID();
		bPhotoInfo.setPid(uuid);
		return bPhotoInfoService.saveBPhotoInfo(bPhotoInfo);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(Long id) {
		return bPhotoInfoService.getBPhotoInfoById(id);
	}
	
//	/**
//	 * 修改
//	 * @param bPhotoInfo
//	 * @return
//	 */
//	@RequestMapping("/update")
//	public Result update(@RequestBody BPhotoInfoEntity bPhotoInfo) {
//		return bPhotoInfoService.updateBPhotoInfo(bPhotoInfo);
//	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody String[] id) {
		return bPhotoInfoService.batchRemove(id);
	}
	
}
