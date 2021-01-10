package cn.cashbang.core.modules.venuesbook.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.modules.sys.controller.AbstractController;
import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.venuesbook.entity.BTeamEntity;
import cn.cashbang.core.modules.venuesbook.service.BTeamService;

/**
 * 团队信息表
 *
 * @author daibangzhu
 * @email xxx@daibangzhu.cn
 * @url www.daibangzhu.cn
 * @date 2020年12月22日 上午11:57:39
 */
@RestController
@RequestMapping("/venuesbook/team")
public class BTeamController extends AbstractController {
	
	@Autowired
	private BTeamService bTeamService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<BTeamEntity> list(@RequestBody Map<String, Object> params) {
		return bTeamService.listBTeam(params);
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
	public Result getById(@RequestBody String id) {
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
