package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.entity.BActivityEntryEntity;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
import cn.cashbang.core.modules.venuesbook.service.BActivitiesService;
import cn.cashbang.core.modules.venuesbook.service.BActivityEntryService;
import cn.cashbang.core.modules.venuesbook.service.BUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:35
 */
@RestController
@RequestMapping("/api/activities")
public class ApiBActivitiesController extends AbstractController {
	
	@Autowired
	private BActivitiesService bActivitiesService;

	@Autowired
	private BActivityEntryService bActivityEntryService;
	
	/**
	 * 列表
	 * @param page
	 * @return
	 */
	@RequestMapping("/getActList")
	public  Map<String, Object> getActList(int page) {
		
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> result = new HashMap<>();

		params.put("pageNumber",page);
		params.put("pageSize",5);
		params.put("aStatus",1);  // 只有公开的活动才需要报名
		params.put("sortOrde","asc");

		Page<BActivitiesEntity> list = bActivitiesService.listBActivities(params);
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
	 * 根据id查询详情
	 * @param activityId
	 * @return
	 */
	@RequestMapping("/getActById")
	public Result getById(String activityId) {
		return bActivitiesService.getBActivitiesById(activityId);
	}
	
	/**
	 * 活动报名接口
	 * @param activityId
	 * @return
	 */
	@RequestMapping("/entryActById")
	public Result entryActById(String activityId,String userId) {

		BActivityEntryEntity bActivityEntry = new BActivityEntryEntity();
		bActivityEntry.setActivityId(activityId);
		bActivityEntry.setUid(userId);
		bActivityEntry.setStatus(1);
		String uuid = CommonUtils.createUUID();
		bActivityEntry.setEid(uuid);

		return bActivityEntryService.saveBActivityEntry(bActivityEntry);
	}

	/**
	 * 取消活动报名接口
	 * @param activityId
	 * @return
	 */
	@RequestMapping("/cancelActById")
	public Result cancelActById(String activityId,String userId) {

		BActivityEntryEntity bActivityEntry = new BActivityEntryEntity();
		bActivityEntry.setActivityId(activityId);
		bActivityEntry.setUid(userId);
		bActivityEntry.setStatus(2);  // 取消

		return bActivityEntryService.updateBActivityEntry(bActivityEntry);
	}

	/**
	 * 活动签到接口
	 * @param activityId
	 * @return
	 */
	@RequestMapping("/signActById")
	public Result signActById(String activityId,String userId) {

		BActivityEntryEntity bActivityEntry = new BActivityEntryEntity();
		bActivityEntry.setActivityId(activityId);
		bActivityEntry.setUid(userId);
		bActivityEntry.setIspresent(Integer.valueOf(1));  // 签到

		return bActivityEntryService.updateBActivityEntry(bActivityEntry);
	}

	@RequestMapping("/listActByUserId")
	public Result listActByUserId(String uid){

		return   bActivitiesService.listActByUserId(uid);
	}

	@RequestMapping("/getUserListById")
	public Result getUserListById(String activityId){

		return   bActivityEntryService.getUserListById(activityId);
	}
}
