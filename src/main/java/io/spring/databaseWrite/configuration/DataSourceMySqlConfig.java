package io.spring.databaseWrite.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by USER on 2017-12-01.
 */
@Configuration
public class DataSourceMySqlConfig {
    // #########################################################################
    // MySql 기본 DataSource 설정
    // #########################################################################
    @Bean(name = "dataSourceMysql")
    @Primary
    @ConfigurationProperties(prefix = "spring.mysql.datasource")
    public DataSource dataSourceMysql() {
        return DataSourceBuilder.create().build();
    }

}
