package io.spring.databaseWrite.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
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
    //@ConfigurationProperties(prefix = "spring.mysql.datasource")
    public DataSource dataSourceMysql() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/world?serverTimezone=UTC&useSSL=false");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("wofl07");
        config.setMaximumPoolSize(3);
        DataSource dataSource = new HikariDataSource(config);

        return dataSource;
    }

    // Batch 에서 다중 DataSource 사용지 Repository DataSource 지정정
   @Bean
    public BatchConfigurer batchConfig() {
        return new DefaultBatchConfigurer(dataSourceMysql());
    }

}
