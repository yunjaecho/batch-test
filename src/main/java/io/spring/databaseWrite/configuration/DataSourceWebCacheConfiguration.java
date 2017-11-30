package io.spring.databaseWrite.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by USER on 2017-11-30.
 */
@Configuration
//@MapperScan(basePackages = "io.spring.databaseWrite.mapper")
public class DataSourceWebCacheConfiguration {

    // #########################################################################
    // WebCache DataSource 설정
    // #########################################################################

    @Bean(name = "dataSourceWebCache")
    @ConfigurationProperties(prefix = "spring.webcache.datasource")
    public DataSource dataSourceWebCache() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactoryWebCache")
    public SqlSessionFactory sqlSessionFactoryWebCache(@Qualifier("dataSourceWebCache") DataSource dataSourceWebCache, ApplicationContext applicationContext) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceWebCache);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:io/spring/databaseWrite/mapper/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name = "sqlSessionTemplateWebCache")
    public SqlSessionTemplate sqlSessionTemplateWebCache(SqlSessionFactory sqlSessionFactoryWebCache) throws Exception {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryWebCache);
        return sqlSessionTemplate;
    }


}
