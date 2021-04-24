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
	public  Map<String, Object> getPhotoList(int page,String uid,String committeeId,String operateId,
    String queryStatus) {

		Map<String, Object> params = new HashMap<>();
		Map<String, Object> result = new HashMap<>();

		params.put("pageNumber",page);
		params.put("pageSize",10);
		
		if(StringUtils.isNotBlank(uid)){
			params.put("queryUserId",uid);
			//params.put("queryStatus",null);
		}
		else {
			params.put("queryUserId",null);
			//params.put("queryStatus",1);
		}

        if(StringUtils.isNotBlank(committeeId)){
            params.put("committeeId",committeeId);
        }
        else {
            params.put("committeeId",null);
        }

        if(StringUtils.isNotBlank(queryStatus)){

            params.put("queryStatus",queryStatus);
        }
        else {

            params.put("queryStatus",null);
        }

        if(StringUtils.isNotBlank(operateId)){
            params.put("operateId",operateId);
        }
        else {
            params.put("operateId",null);
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
			result.put("code",0);
			result.put("rows",null);
			result.put("msg","没有查询到数据！");
		}
		return result;
	}

    /**
     * 查询“我关注的人人拍”列表
     * @param page
     * @return
     */
    @RequestMapping("/getPhotoListReply")
    public  Map<String, Object> getPhotoListReply(int page,String operateId) {

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        params.put("pageNumber",page);
        params.put("pageSize",10);

        if(StringUtils.isNotBlank(operateId)){
            params.put("queryUserId",operateId);
        }
        else {
            params.put("queryUserId",null);
        }
        params.put("sortOrde","asc");

        Page<BPhotoInfoEntity> list =  bPhotoInfoService.listReplyPage(params);
        if(list.getTotal()>0){
            result.put("code",0);
            result.put("rows",list.getRows());
            result.put("page",page);
            result.put("msg","查询成功！");
        }
        else{
            result.put("code",0);
            result.put("rows",null);
            result.put("msg","没有查询到数据！");
        }
        return result;
    }

    @RequestMapping("/getOperateCount")
    public  Map<String, Object> getOperateCount(String operateId) {

        int all = bPhotoInfoService.getOperateCount(operateId,null,null);
        int week = bPhotoInfoService.getOperateCount(operateId,"week",null);
        int month = bPhotoInfoService.getOperateCount(operateId,null,"month");
        Map<String, Integer> counts = new HashMap<>();
        counts.put("all",all);
        counts.put("month",week);
        counts.put("week",month);

        Map<String, Object> result = new HashMap<>();
        result.put("code",0);
        result.put("rows",counts);
        result.put("msg","查询成功！");
        return  result;
    }
		
	/**
	 * 新增
	 * @param uid
	 * @return
	 */
	@RequestMapping("/sendPhoto")
	public Result sendPhoto(String uid,String content,String pitureUrls,
                            String address,String contentTypes,String committeeId) {

		BPhotoInfoEntity bPhotoInfo = new BPhotoInfoEntity();
		bPhotoInfo.setUid(uid);
		bPhotoInfo.setContent(content);
		bPhotoInfo.setStatus(0);     //状态 0.审核中，1.正常 2.删除
		bPhotoInfo.setPitureUrls(pitureUrls);
		String uuid = CommonUtils.createUUID();
		bPhotoInfo.setPid(uuid);
		bPhotoInfo.setCreateTime(new Date());
        bPhotoInfo.setAddress(address);
        bPhotoInfo.setContentType(contentTypes);
        bPhotoInfo.setCommitteeId(committeeId);
		return bPhotoInfoService.saveBPhotoInfo(bPhotoInfo);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(String id) {
		return bPhotoInfoService.getBPhotoInfoById(id);
	}
	
	/**
	 * 修改随拍的状态（3已处理）
	 * @param operateId
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public Result update(String pid,String operateId,Integer status) {
        BPhotoInfoEntity bPhotoInfo = new BPhotoInfoEntity();
        bPhotoInfo.setPid(pid);
        bPhotoInfo.setStatus(status);
        bPhotoInfo.setOperateId(operateId);
		return bPhotoInfoService.updateBPhotoInfo(bPhotoInfo);
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
