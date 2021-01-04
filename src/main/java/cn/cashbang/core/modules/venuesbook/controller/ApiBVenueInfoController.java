package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BUpdateVenueTime;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoDTO;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
import cn.cashbang.core.modules.venuesbook.service.BVenueInfoService;
import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 场馆信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午12:20:39
 */
@RestController
@RequestMapping("/api/venueinfo")
public class ApiBVenueInfoController extends AbstractController {
	
	@Autowired
	private BVenueInfoService bVenueInfoService;
	
	/**
	 * 获取场馆列表
	 * @param page
	 * @return
	 */
	@RequestMapping("/getVenueList")
	public Map<String, Object> getVenueList(int page) {

		Map<String, Object> params = new HashMap<>();
		Map<String, Object> result = new HashMap<>();

		params.put("pageNumber",page);
		params.put("pageSize",2);
		params.put("keyword",null);
		params.put("sortOrde","asc");

		Page<BVenueInfoEntity> list = bVenueInfoService.listBVenueInfo(params);
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
	 * 根据id查询场馆详情
	 * @param venueId
	 * @return
	 */
	@RequestMapping("/getVenueById")
	public Result getVenueById(String venueId) {
		if(!StringUtils.isEmpty(venueId)) {
			venueId = venueId.replace("\"", "");
		}
		return bVenueInfoService.getBVenueInfoById(venueId);
	}
	
	/**
	 * 修改
	 * @param bVenueInfo
	 * @return
	 */
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
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody String[] id) {
		return bVenueInfoService.batchRemove(id);
	}
	
}
