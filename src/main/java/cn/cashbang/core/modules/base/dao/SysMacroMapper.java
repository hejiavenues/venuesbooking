package cn.cashbang.core.modules.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import cn.cashbang.core.modules.base.entity.SysMacroEntity;
import cn.cashbang.core.modules.sys.dao.BaseMapper;


/**
 * 通用字典
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月15日 下午12:46:31
 */
@Mapper
public interface SysMacroMapper extends BaseMapper<SysMacroEntity> {

	List<SysMacroEntity> listNotMacro();
	
	int countMacroChildren(Long typeId);

	List<SysMacroEntity> listMacroValue(String value);
	
}
