package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.common.utils.StringUtils;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BPhotoInfoEntity;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
import cn.cashbang.core.modules.venuesbook.service.BPhotoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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
	public  Map<String, Object> getPhotoList(int page,String uid) {

		Map<String, Object> params = new HashMap<>();
		Map<String, Object> result = new HashMap<>();

		params.put("pageNumber",page);
		params.put("pageSize",2);
		
		if(StringUtils.isNotBlank(uid)){
			params.put("queryUserId",uid);
		}
		else {
			params.put("queryUserId",null);
		}

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
	 * @param uid
	 * @return
	 */
	@RequestMapping("/sendPhoto")
	public Result sendPhoto(String uid,String content,String comId,String pitureUrls) {

		BPhotoInfoEntity bPhotoInfo = new BPhotoInfoEntity();
		bPhotoInfo.setUid(uid);
		bPhotoInfo.setContent(content);
		//bPhotoInfo.setUname("王阿姨");
		bPhotoInfo.setStatus(1);     //状态 1.正常 2.删除
		bPhotoInfo.setPitureUrls(pitureUrls);
		String uuid = CommonUtils.createUUID();
		bPhotoInfo.setPid(uuid);
		bPhotoInfo.setCommitteeId(comId);
		bPhotoInfo.setCreateTime(new Date());
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

	/**
	 * 新增
	 * @param imgFile
	 * @return
	 */
	@RequestMapping("/saveImage")
	public Result saveImage(MultipartFile imgFile, String type) {

		Result resultEntity = new Result();

		if(imgFile == null){
			logger.error("新增图片，imgFile为空");
			resultEntity = Result.error(-1, "图片为空");
			return resultEntity;
		}

		return   bPhotoInfoService.saveImage(imgFile,type);
	}
	
}
