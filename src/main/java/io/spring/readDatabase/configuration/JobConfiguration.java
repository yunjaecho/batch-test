package io.spring.readDatabase.configuration;

import io.spring.readDatabase.domain.Customer;
import io.spring.readDatabase.domain.CustomerRowMapper;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by USER on 2017-11-24.
 */
@Configuration
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Customer> cursorItemReader() {
        JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<>();

        reader.setSql("select id, firstName, lastName, birthdate from customer order by lastName, firstName");
        reader.setDataSource(this.dataSource);
        reader.setRowMapper(new CustomerRowMapper());
        return reader;
    }
}
