package cn.cashbang.core.modules.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cashbang.core.common.entity.Page;
import cn.cashbang.core.common.entity.Query;
import cn.cashbang.core.modules.generator.entity.ColumnEntity;
import cn.cashbang.core.modules.generator.entity.GeneratorParamEntity;
import cn.cashbang.core.modules.generator.entity.TableEntity;
import cn.cashbang.core.modules.generator.manager.SysGeneratorManager;
import cn.cashbang.core.modules.generator.service.SysGeneratorService;
import cn.cashbang.core.modules.generator.utils.GenUtils;

/**
 * 代码生成器
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月28日 下午8:55:51
 */
@Service("sysGeneratorService")
public class SysGeneratorServiceImpl implements SysGeneratorService {

	@Autowired
	private SysGeneratorManager sysGeneratorManager;
	
	@Override
	public Page<TableEntity> listTable(Map<String, Object> params) {
		Query query = new Query(params);
		Page<TableEntity> page = new Page<>(query);
		sysGeneratorManager.listTable(page, query);
		return page;
	}

	@Override
	public byte[] generator(GeneratorParamEntity params) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(out);
		for(String table : params.getTables()) {
			TableEntity tableEntity = sysGeneratorManager.getTableByName(table);
			List<ColumnEntity> columns = sysGeneratorManager.listColumn(table);
			GenUtils.generatorCode(tableEntity, columns, params, zip);
		}
		IOUtils.closeQuietly(zip);
		return out.toByteArray();
	}

}
