package cn.cashbang.core.modules.generator.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.generator.dao.SysGeneratorMapper;
import cn.cashbang.core.modules.generator.entity.ColumnEntity;
import cn.cashbang.core.modules.generator.entity.TableEntity;
import cn.cashbang.core.modules.generator.manager.SysGeneratorManager;

/**
 * 代码生成器
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月28日 下午8:54:44
 */
@Component("sysGeneratorManager")
public class SysGeneratorManagerImpl implements SysGeneratorManager {

	@Autowired
	private SysGeneratorMapper sysGeneratorMapper;
	
	@Override
	public void listTable(Page<TableEntity> page, Query query) {
		sysGeneratorMapper.listTable(page, query);
	}

	@Override
	public TableEntity getTableByName(String tableName) {
		return sysGeneratorMapper.getTableByName(tableName);
	}

	@Override
	public List<ColumnEntity> listColumn(String tableName) {
		return sysGeneratorMapper.listColumn(tableName);
	}

}
