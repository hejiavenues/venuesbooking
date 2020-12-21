package cn.cashbang.core.common.support.orm.db;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * 配置多数据源
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年9月3日 下午8:03:40
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean("defaultDataSource")
    @ConfigurationProperties("spring.datasource.druid.masterDataSource")
    public DataSource defaultDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
    
    @Bean("slaveDataSourceRead")
    @ConfigurationProperties("spring.datasource.druid.slaveDataSourceRead")
    public DataSource slaveDataSourceRead(){
        return DruidDataSourceBuilder.create().build();
    }
    
    @Bean("slaveDataSourceWrite")
    @ConfigurationProperties("spring.datasource.druid.slaveDataSourceWrite")
    public DataSource slaveDataSourceWrite(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("defaultDataSource")DataSource defaultDataSource,
    		@Qualifier("slaveDataSourceRead")DataSource slaveDataSourceRead,
    		@Qualifier("slaveDataSourceWrite")DataSource slaveDataSourceWrite) {
        Map<String, DataSource> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceEnum.MASTER.getName(), defaultDataSource);
        targetDataSources.put(DataSourceEnum.SLAVEREAD.getName(), slaveDataSourceRead);
        targetDataSources.put(DataSourceEnum.SLAVEWRITE.getName(), slaveDataSourceWrite);
        return new DynamicDataSource(defaultDataSource, targetDataSources);
    }
}
