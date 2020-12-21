package cn.cashbang.core.modules.base.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.utils.CommonUtils;
import cn.cashbang.core.modules.base.dao.SysMacroMapper;
import cn.cashbang.core.modules.base.entity.SysMacroEntity;
import cn.cashbang.core.modules.base.manager.SysMacroManager;

/**
 * 通用字典
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月15日 下午12:49:26
 */
@Component("sysMacroManager")
public class SysMacroManagerImpl implements SysMacroManager {

	@Autowired
	private SysMacroMapper sysMacroMapper;
	
	@Override
	public List<SysMacroEntity> listMacro() {
		return sysMacroMapper.list();
	}

	@Override
	public List<SysMacroEntity> listNotMacro() {
		return sysMacroMapper.listNotMacro();
	}

	@Override
	public int saveMacro(SysMacroEntity macro) {
		return sysMacroMapper.save(macro);
	}

	@Override
	public SysMacroEntity getObjectById(Long id) {
		return sysMacroMapper.getObjectById(id);
	}

	/*@Override
	public int updateMacro(SysMacroEntity macro) {

		if (macro.getName().equalsIgnoreCase("解码")){
			String value = macro.getValue();
			if ("true".equalsIgnoreCase(value))
				riskDecisionResultManager.setEncrypt(true);
			else {
				riskDecisionResultManager.setEncrypt(false);
			}
		}


		return sysMacroMapper.update(macro);
	}*/

	@Override
	public int batchRemove(Long[] id) {
		return sysMacroMapper.batchRemove(id);
	}
	
	@Override
	public boolean hasChildren(Long[] id) {
		for(Long typeId : id) {
			int count = sysMacroMapper.countMacroChildren(typeId);
			if(CommonUtils.isIntThanZero(count)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<SysMacroEntity> listMacroValue(String value) {
		return this.sysMacroMapper.listMacroValue(value);
	}

}
