package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntity;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntryEntity;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
import cn.cashbang.core.modules.venuesbook.service.BTeamEntryService;
import cn.cashbang.core.modules.venuesbook.service.BTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 团队信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 上午11:57:39
 */
@RestController
@RequestMapping("/api/team")
public class ApiBTeamController extends AbstractController {
	
	@Autowired
	private BTeamService bTeamService;

	@Autowired
	private BTeamEntryService bTeamEntryService;
	
	/**
	 * 列表
	 * @param page
	 * @return
	 */
	@RequestMapping("/getTeamList")
	public  Map<String, Object> getTeamList(int page) {

		Map<String, Object> params = new HashMap<>();
		Map<String, Object> result = new HashMap<>();

		params.put("pageNumber",page);
		params.put("pageSize",2);
		params.put("keyword",null);
		params.put("sortOrde","asc");

		Page<BTeamEntity> list = bTeamService.listBTeam(params);
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
	 * 团队组建接口
	 * @param tname
	 * @return
	 */
	@RequestMapping("/buildTeam")
	public Result buildTeam(String uid,String tname,Integer peopleCount,String activityType,
					   String activityContent,String enterCondition) {

		BTeamEntity bTeam = new BTeamEntity();
		bTeam.setUid(uid);
		bTeam.setTname(tname);
		bTeam.setPeopleCount(peopleCount);
		bTeam.setActivityType(activityType);
		bTeam.setActivityContent(activityContent);
		bTeam.setEnterCondition(enterCondition);
		String uuid = CommonUtils.createUUID();
		bTeam.setTid(uuid);
		bTeam.setStatus(1);
		return bTeamService.saveBTeam(bTeam);
	}
	
	/**
	 * 根据id查询详情
	 * @param teamId
	 * @return
	 */
	@RequestMapping("/getBTeamById")
	public Result getById(String teamId) {
		return bTeamService.getBTeamById(teamId);
	}

	/**
	 * 团队报名
	 * @param teamId
	 * @return
	 */
	@RequestMapping("/entryTeamById")
	public Result entryTeamById(String teamId,String uid) {

		BTeamEntryEntity bTeamEntry = new BTeamEntryEntity();
		bTeamEntry.setTid(teamId);
		bTeamEntry.setMobile("1234test");
		bTeamEntry.setUname("test");
		String uuid = CommonUtils.createUUID();
		bTeamEntry.setTeid(uuid);
		bTeamEntry.setUid(uid);
		bTeamEntry.setStatus(1);
		return bTeamEntryService.saveBTeamEntry(bTeamEntry);
	}


	/**
	 * 团队状态变更接口
	 * @param teamId
	 * @return
	 */
	@RequestMapping("/updateTeamStatus")
	public Result updateTeamStatus(int status,String teamId) {

		BTeamEntity bTeam = new BTeamEntity();
		bTeam.setTid(teamId);
		bTeam.setStatus(status);  // 1放开、2关闭、3满员、4.组队失败
		return bTeamService.updateBTeam(bTeam);
	}

	@RequestMapping("/listTeamByUserId")
	public Result listTeamByUserId(String uid){

		return   bTeamService.listTeamByUserId(uid);
	}

	@RequestMapping("/listTeamByCreateUserId")
	public Result listTeamByCreateUserId(String uid){

		return   bTeamService.listTeamByCreateUserId(uid);
	}

	/**
	 * 查询团队成员列表
	 * @param teamId
	 * @return
	 */
	@RequestMapping("/getTeamUserById")
	public Result getTeamUserById(String teamId) {

		return bTeamEntryService.getTeamUserById(teamId);
	}
}
