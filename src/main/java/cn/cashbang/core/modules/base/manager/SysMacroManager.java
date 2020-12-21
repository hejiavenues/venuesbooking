package cn.cashbang.core.modules.base.manager;

import java.util.List;

import cn.cashbang.core.modules.base.entity.SysMacroEntity;


/**
 * 通用字典
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月15日 下午12:49:14
 */
public interface SysMacroManager {

	List<SysMacroEntity> listMacro();
	
	List<SysMacroEntity> listNotMacro();
	
	int saveMacro(SysMacroEntity macro);
	
	SysMacroEntity getObjectById(Long id);
	
	/*int updateMacro(SysMacroEntity macro);*/
	
	int batchRemove(Long[] id);
	
	boolean hasChildren(Long[] id);

	List<SysMacroEntity> listMacroValue(String value);
	
}
