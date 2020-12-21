package cn.cashbang.core.modules.generator.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
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
 * @date 2017年8月28日 下午8:47:12
 */
@Mapper
public interface SysGeneratorMapper {

	List<TableEntity> listTable(Page<TableEntity> page, Query query);
	
	TableEntity getTableByName(String tableName);
	
	List<ColumnEntity> listColumn(String tableName);
	
}
