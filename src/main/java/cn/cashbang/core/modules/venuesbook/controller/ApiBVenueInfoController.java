package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.*;
import cn.cashbang.core.modules.venuesbook.service.BActivitiesService;
import cn.cashbang.core.modules.venuesbook.service.BVenueBookService;
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

	@Autowired
	private BActivitiesService bActivitiesService;

	@Autowired
	private BVenueBookService bVenueBookService;
	
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
	 * 场馆预约接口
	 * @param
	 * @return
	 */
	@RequestMapping("/bookVenueById")
	public Map<String, Object>  bookVenueById(String userId,String venueId,String bookDate,String bookTime,
								String activityIdName,String activityType,String activityContent
								,String activityIconUrl) {

		Map<String, Object> result = new HashMap<>();

		// 判断是不是可以预约  TODO

		// 生成一条预约记录
		BVenueBookEntity bVenueBook = new BVenueBookEntity();
		bVenueBook.setBookStatus(2); // 已预约
		bVenueBook.setBookTime(bookTime);
		bVenueBook.setBookDate(bookDate);
		bVenueBook.setUserId(userId);
		bVenueBook.setVenueId(venueId);
		String uuid = CommonUtils.createUUID();
		bVenueBook.setId(uuid);
		Result r1= bVenueBookService.saveBVenueBook(bVenueBook);

		// 生成活动信息

		BActivitiesEntity bActivities = new BActivitiesEntity();
		bActivities.setActivityContent(activityContent);
		bActivities.setActivityIdName(activityIdName);
		bActivities.setActivityIconUrl(activityIconUrl);
		bActivities.setActivityTime(bookDate +"  "+bookTime);
		bActivities.setActivityType(activityType);
		bActivities.setVenueId(venueId);
		bActivities.setUid(userId);
		String uuid2 = CommonUtils.createUUID();
		bActivities.setActivityId(uuid2);
		Result r2=bActivitiesService.saveBActivities(bActivities);

		if(r1.get("code").toString().equals("0")&&
				r2.get("code").toString().equals("0")){

			result.put("code",0);
			result.put("msg","预约成功！");
		}
		else{
			result.put("code",-1);
			result.put("msg","预约失败！");
		}

		return result;
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
