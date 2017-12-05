package io.spring.databaseWrite.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by USER on 2017-12-01.
 */
@Configuration
@MapperScan(value="io.spring.databaseWrite.mapper", sqlSessionFactoryRef="sqlSessionFactoryMssql")
public class DataSourceMssqlConfig {
    @Bean(name = "dataSourceMssql")
    //@ConfigurationProperties(prefix = "spring.mssql.datasource")
    public DataSource dataSourceMssql() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:jtds:sqlserver://210.103.25.151:1433/iTMSNTData");
        config.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
        config.setUsername("SA");
        config.setPassword("p@ssw0rd");
        config.setMaximumPoolSize(3);
        config.setAutoCommit(false);
        config.setConnectionTestQuery("SELECT 1");
        DataSource dataSource = new HikariDataSource(config);

        return dataSource;
    }

    @Bean(name = "sqlSessionFactoryMssql")
    public SqlSessionFactory sqlSessionFactoryMssql() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceMssql());
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:io/spring/databaseWrite/mapper/*.xml"));
        return sqlSessionFactory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager1() {
        return new DataSourceTransactionManager(dataSourceMssql());
    }

    /*
    @Bean(name = "sqlSessionTemplateMssql")
    public SqlSessionTemplate sqlSessionTemplateMssql(SqlSessionFactory sqlSessionFactoryMssql) throws Exception {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryMssql);
        return sqlSessionTemplate;
    }
    */
}
