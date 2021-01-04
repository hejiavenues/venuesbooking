package cn.cashbang.core.modules.venuesbook.controller;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntity;
import cn.cashbang.core.modules.venuesbook.entity.BVenueInfoEntity;
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
	 * 新增
	 * @param bTeam
	 * @return
	 */
	@RequestMapping("/save")
	public Result save(@RequestBody BTeamEntity bTeam) {
		return bTeamService.saveBTeam(bTeam);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result getById(@RequestBody Long id) {
		return bTeamService.getBTeamById(id);
	}
	
	/**
	 * 修改
	 * @param bTeam
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody BTeamEntity bTeam) {
		return bTeamService.updateBTeam(bTeam);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return bTeamService.batchRemove(id);
	}
	
}
