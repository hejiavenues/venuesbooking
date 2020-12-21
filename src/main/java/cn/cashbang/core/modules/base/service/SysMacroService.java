package cn.cashbang.core.modules.base.service;

import java.util.List;

import cn.cashbang.core.common.entity.Result;
import cn.cashbang.core.modules.base.entity.SysMacroEntity;

/**
 * 通用字典
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月15日 下午12:51:35
 */
public interface SysMacroService {

	List<SysMacroEntity> listMacro();
	
	List<SysMacroEntity> listNotMacro();
	
	Result saveMacro(SysMacroEntity macro);
	
	Result getObjectById(Long id);
	
	/*Result updateMacro(SysMacroEntity macro);*/
	
	Result batchRemove(Long[] id);

	List<SysMacroEntity> listMacroValue(String value);
	
}
