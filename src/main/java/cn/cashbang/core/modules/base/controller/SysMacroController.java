package cn.cashbang.core.modules.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.cashbang.core.common.annotation.SysLog;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.base.entity.SysMacroEntity;
import cn.cashbang.core.modules.base.service.SysMacroService;
import cn.cashbang.core.modules.sys.controller.AbstractController;

/**
 * 通用字典
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月15日 下午12:54:33
 */
@RestController
@RequestMapping("/sys/macro")
public class SysMacroController extends AbstractController {

	@Autowired
	private SysMacroService sysMacroService;
	
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("/list")
	public List<SysMacroEntity> list() {
		return sysMacroService.listMacro();
	}
	
	/**
	 * 树形列表
	 * @return
	 */
	@RequestMapping("/select")
	public List<SysMacroEntity> select() {
		return sysMacroService.listNotMacro();
	}
	
	/**
	 * 新增字典
	 * @param macro
	 * @return
	 */
	@SysLog("新增字典")
	@RequestMapping("/save")
	public Result save(@RequestBody SysMacroEntity macro) {
		return sysMacroService.saveMacro(macro);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public Result info(@RequestBody Long id) {
		return sysMacroService.getObjectById(id);
	}
	
	/**
	 * 修改字典
	 * @param macro
	 * @return
	 */
	@SysLog("修改字典")
	@RequestMapping("/update")
	public Result update(@RequestBody SysMacroEntity macro) {
		/*return sysMacroService.updateMacro(macro);*/
		return new Result();
	}
	
	/**
	 * 删除字典
	 * @param id
	 * @return
	 */
	@SysLog("删除字典")
	@RequestMapping("/remove")
	public Result batchRemove(@RequestBody Long[] id) {
		return sysMacroService.batchRemove(id);
	}
	
	/**
	 * 获取某个类型所有参数值，用于前台构建下拉框
	 * @param value
	 * @return
	 */
	@RequestMapping("/value")
	public List<SysMacroEntity> listMacroValue(@RequestParam String value) {
		return sysMacroService.listMacroValue(value);
	}
}
