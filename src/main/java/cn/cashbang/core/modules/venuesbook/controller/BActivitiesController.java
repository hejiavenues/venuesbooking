package cn.cashbang.core.modules.venuesbook.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.venuesbook.service.BVenueBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BActivitiesEntity;
import cn.cashbang.core.modules.venuesbook.service.BActivitiesService;

/**
 * 活动信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 下午1:11:35
 */
@RestController
@RequestMapping("/venuesbook/activities")
public class BActivitiesController extends AbstractController {
	
	@Autowired
	private BActivitiesService bActivitiesService;


    @Autowired
    private BVenueBookService bVenueBookService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BActivitiesEntity> list(@RequestBody Map<String, Object> params) {
		return bActivitiesService.listBActivities(params);
	}
		
	/**
	 * 新增
	 * @param bActivities
	 * @return
	 */
	@RequestMapping("/save")
	public Map<String, Object> save(@RequestBody BActivitiesEntity bActivities) {

        Map<String, Object> result = new HashMap<>();

        // 判断是不是可以预约（每人每天只能预约一次）  TODO

        if(org.apache.commons.lang3.StringUtils.isNotEmpty(bActivities.getUid())){
            if(bVenueBookService.countUserBookTime(bActivities.getUid())!=null){
                result.put("code",1);
                result.put("msg","每人每天只能预约一次！");
                return result;
            }
        }

        // 生成活动信息
        bActivities.setActivityIconUrl("/picture/"+bActivities.getActivityIconUrl());
        bActivities.setActivityTime(bActivities.getBookDate() +"  "+bActivities.getBookTime());
        String uuid2 = CommonUtils.createUUID();
        bActivities.setActivityId(uuid2);
        bActivities.setCreateTime(new Date());
        Result r2=bActivitiesService.saveBActivities(bActivities,bActivities.getBookDate(),bActivities.getBookTime());

		return r2;
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
		return bActivitiesService.getBActivitiesById(id);
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
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody String[] id) {
		return bActivitiesService.batchRemove(id);
	}
	
}
