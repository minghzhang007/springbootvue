package com.lewis.sprinbootvue;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.lewis.sprinbootvue.config.MasterSlaveDataSource;
import com.lewis.sprinbootvue.config.multi.DynamicDataSource;
import com.lewis.sprinbootvue.config.multi.DynamicDataSourceProperties;
import com.lewis.sprinbootvue.config.multi.DynamicPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.*;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@MapperScan(value = "com.lewis.sprinbootvue.biz.mybatis.dao")
public class MultiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiApplication.class, args);
    }


    @Bean(value = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource writeDataSource(){
        return new DruidDataSource();
    }

    @Bean(value = "readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cluster")
    public DataSource readDataSource(){
        return new DruidDataSource();
    }

    @Bean(value = "snailReaderDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cluster1")
    public DataSource snailReaderDataSource(){
        return new DruidDataSource();
    }

    @Bean(value = "dynamicDataSource")
    public DataSource dynamicDataSource(){
        List<MasterSlaveDataSource> masterSlaveDataSourceList = new ArrayList<>();

        MasterSlaveDataSource masterSlaveDataSource1 = new MasterSlaveDataSource();
        masterSlaveDataSource1.setMasterKey("sharding_0");
        masterSlaveDataSource1.setMasterDs(writeDataSource());
        Map<String, Object> slaveDsMap = new HashMap<>();
        slaveDsMap.put("sharding_1",readDataSource());
        masterSlaveDataSource1.setSlaveDsMap(slaveDsMap);
        masterSlaveDataSourceList.add(masterSlaveDataSource1);

        MasterSlaveDataSource masterSlaveDataSource2 = new MasterSlaveDataSource();
        Map<String, Object> slaveDsMap2 = new HashMap<>();
        slaveDsMap2.put("snailReader",snailReaderDataSource());
        masterSlaveDataSource2.setSlaveDsMap(slaveDsMap2);
        masterSlaveDataSourceList.add(masterSlaveDataSource2);

        DynamicDataSourceProperties.setMasterSlaveDataSourceList(masterSlaveDataSourceList);
        return new DynamicDataSource(masterSlaveDataSourceList);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/**/*.xml"));
        //添加分页插件
        Interceptor[] plugins = {pageHelper(),new DynamicPlugin()};
        sqlSessionFactoryBean.setPlugins(plugins);
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
