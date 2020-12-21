package cn.cashbang.core.modules.generator.service;

import java.util.Map;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.modules.generator.entity.GeneratorParamEntity;
import cn.cashbang.core.modules.generator.entity.TableEntity;

/**
 * 代码生成器
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月28日 下午8:55:29
 */
public interface SysGeneratorService {

	Page<TableEntity> listTable(Map<String, Object> params);
	
	byte[] generator(GeneratorParamEntity params);
	
}
