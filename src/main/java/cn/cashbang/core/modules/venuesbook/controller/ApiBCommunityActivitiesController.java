package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.entity.BCommunityActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.service.BCommunityActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 社区活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2021年2月27日 PM9:46:01
 */
@RestController
@RequestMapping("/api/comactivity")
public class ApiBCommunityActivitiesController extends AbstractController {
	
	@Autowired
	private BCommunityActivitiesService bCommunityActivitiesService;
	
	/**
	 * 列表
	 * @param page
	 * @return
	 */
	@RequestMapping("/list")
	public Map<String, Object> list(int page,String queryCGName) {
		//return bCommunityActivitiesService.listBCommunityActivities(params);

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        params.put("pageNumber",page);
        params.put("pageSize",5);
        params.put("aStatus",1);  // 只有公开的活动才需要报名
        params.put("sortOrde","asc");
//        params.put("acName",queryCGName);
//        params.put("queryComId",queryComId);
//        params.put("queryActType",queryActType);
//        params.put("queryCount",queryCount);

        Page<BCommunityActivitiesEntity> list = bCommunityActivitiesService.listBCommunityActivities(params);
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
	 * @param activityTime
	 * @return
	 */
	@SysLog("新增社区活动信息表")
	@RequestMapping("/save")
	public Result save(String userId,String activityTime, String address,
                       String activityName,String activityType,String activityContent,int activityCount) {

        BCommunityActivitiesEntity bCommunityActivities = new BCommunityActivitiesEntity();
        bCommunityActivities.setUid(userId);
        bCommunityActivities.setActivityContent(activityContent);
        bCommunityActivities.setActivityTime(activityTime);
        bCommunityActivities.setActivityType(activityType);
        bCommunityActivities.setActivityName(activityName);
        bCommunityActivities.setActivityCount(activityCount);
        String uuid = CommonUtils.createUUID();
        bCommunityActivities.setComActivityId(uuid);
        bCommunityActivities.setCreateTime(new Date());
        bCommunityActivities.setAddress(address);

		return bCommunityActivitiesService.saveBCommunityActivities(bCommunityActivities);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return bCommunityActivitiesService.getBCommunityActivitiesById(id);
	}
	
	/**
	 * 修改
	 * @param bCommunityActivities
	 * @return
	 */
	@SysLog("修改社区活动信息表")
	@RequestMapping("/update")
	public Result update(@RequestBody BCommunityActivitiesEntity bCommunityActivities) {
		return bCommunityActivitiesService.updateBCommunityActivities(bCommunityActivities);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除社区活动信息表")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bCommunityActivitiesService.batchRemove(id);
	}
	
}
