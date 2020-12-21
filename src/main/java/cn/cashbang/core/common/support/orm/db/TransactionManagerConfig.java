package cn.cashbang.core.common.support.orm.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 
 * 多数据源事务管理配置
 * 
 * @author Tiny
 * @date 2018/04/11
 *
 */
@Configuration
public class TransactionManagerConfig {

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("defaultDataSource")DataSource defaultDataSource) {
		return new DataSourceTransactionManager(defaultDataSource);
	}
	
	@Bean(name = "slaveTransactionReadManager")
    public PlatformTransactionManager slaveTransactionManager(@Qualifier("slaveDataSourceRead")DataSource slaveDataSourceRead) {
        return new DataSourceTransactionManager(slaveDataSourceRead);
    }
	@Bean(name = "slaveTransactionWriteManager")
    public PlatformTransactionManager slaveTransactionWriteManager(@Qualifier("slaveDataSourceWrite")DataSource slaveDataSourceWrite) {
        return new DataSourceTransactionManager(slaveDataSourceWrite);
    }
}
