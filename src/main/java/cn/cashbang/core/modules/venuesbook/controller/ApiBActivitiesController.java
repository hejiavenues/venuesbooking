package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
import cn.cashbang.core.modules.venuesbook.service.BActivitiesService;
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
		params.put("pageSize",2);
		params.put("keyword",null);
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
	 * 修改
	 * @param bActivities
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody BActivitiesEntity bActivities) {
		return bActivitiesService.updateBActivities(bActivities);
	}
	
}
