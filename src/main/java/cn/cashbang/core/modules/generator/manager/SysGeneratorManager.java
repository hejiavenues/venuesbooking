package cn.cashbang.core.modules.generator.manager;

import java.util.List;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.generator.entity.ColumnEntity;
import cn.cashbang.core.modules.generator.entity.TableEntity;

/**
 * 代码生成器
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月28日 下午8:54:09
 */
public interface SysGeneratorManager {

	void listTable(Page<TableEntity> page, Query query);
	
	TableEntity getTableByName(String tableName);
	
	List<ColumnEntity> listColumn(String tableName);
	
}
