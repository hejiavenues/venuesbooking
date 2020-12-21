package cn.cashbang.core.modules.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.constant.MsgConstant;
import cn.cashbang.core.common.constant.SystemConstant.MacroType;
import cn.cashbang.core.common.constant.SystemConstant.StatusType;
import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.base.entity.SysMacroEntity;
import cn.cashbang.core.modules.base.manager.SysMacroManager;
import cn.cashbang.core.modules.base.service.SysMacroService;

/**
 * 通用字典
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月15日 下午12:52:38
 */
@Service("sysMacroService")
public class SysMacroServiceImpl implements SysMacroService {

	@Autowired
	private SysMacroManager sysMacroManager;
	
	@Override
	public List<SysMacroEntity> listMacro() {
		return sysMacroManager.listMacro();
	}

	@Override
	public List<SysMacroEntity> listNotMacro() {
		List<SysMacroEntity> macros = sysMacroManager.listNotMacro();
		SysMacroEntity macro = new SysMacroEntity();
		macro.setMacroId(0L);
		macro.setTypeId(-1L);
		macro.setName("一级目录");
		macro.setOpen(true);
		macros.add(macro);
		return macros;
	}

	@Override
	public Result saveMacro(SysMacroEntity macro) {
		int count = sysMacroManager.saveMacro(validateMacro(macro));
		return CommonUtils.msg(count);
	}

	@Override
	public Result getObjectById(Long id) {
		SysMacroEntity macro = sysMacroManager.getObjectById(id);
		return CommonUtils.msg(macro);
	}

	/*@Override
	public Result updateMacro(SysMacroEntity macro) {
		int count = sysMacroManager.updateMacro(validateMacro(macro));
		return CommonUtils.msg(count);
	}*/

	@Override
	public Result batchRemove(Long[] id) {
		boolean children = sysMacroManager.hasChildren(id);
		if(children) {
			return Result.error(MsgConstant.MSG_HAS_CHILD);
		}
		int count = sysMacroManager.batchRemove(id);
		return CommonUtils.msg(id, count);
	}
	
	/**
	 * 当为参数类型时，状态为显示
	 * @param macro
	 * @return
	 */
	public SysMacroEntity validateMacro(SysMacroEntity macro) {
		if(macro.getType() == MacroType.TYPE.getValue()) {
			macro.setStatus(StatusType.SHOW.getValue());
		}
		return macro;
	}

	@Override
	public List<SysMacroEntity> listMacroValue(String value) {
		return this.sysMacroManager.listMacroValue(value);
	}

}
