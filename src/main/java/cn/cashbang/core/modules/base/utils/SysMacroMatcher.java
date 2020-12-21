package cn.cashbang.core.modules.base.utils;

import java.util.List;

import cn.cashbang.core.common.utils.AssertUtils;
import cn.cashbang.core.common.utils.SpringContextUtils;
import cn.cashbang.core.modules.base.entity.SysMacroEntity;
import cn.cashbang.core.modules.base.manager.SysMacroManager;

/**
 * 通用字典匹配器
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月15日 下午12:34:37
 */
public final class SysMacroMatcher  {

	
	private List<SysMacroEntity> matchMacros;		//当前匹配字典集
	
	private String matchValue;						//当前匹配父字典值
	
	
	/**
	 * 
	 * 匹配方法
	 * 
	 * @param value
	 * @return
	 */
	public String match(String value){
		
		if(this.matchMacros==null||this.matchMacros.isEmpty()){
			return null;
		}
		
		for (SysMacroEntity item : matchMacros) {
			if(item.getValue()!=null&&item.getValue().equals(value)){
				return item.getName();
			}
		}
		
		return null;
	}
	
	
	/**
	 * 获取通用字典匹配器
	 * 
	 * @param matchValue
	 * @return
	 */
	public static final SysMacroMatcher getInstance(String matchValue){
		
		AssertUtils.hasLength(matchValue, "matchValue must not be empty");
		
		SysMacroMatcher matcher = new SysMacroMatcher(matchValue);
		
		SysMacroManager manager = SpringContextUtils.getBean("sysMacroManager", SysMacroManager.class);
		
		matcher.matchMacros = manager.listMacroValue(matchValue);
		
		return matcher;
		
	}
	
	/**
	 * constructor private
	 * 
	 */
	private SysMacroMatcher(String matchValue){
		this.matchValue = matchValue;
	}

	/**
	 * 
	 * 获取匹配器当前匹配父字典值
	 * 
	 * @return
	 */
	public String getMatchValue() {
		return matchValue;
	}
	
	

}
