package io.spring.databaseWrite.configuration;

import io.spring.databaseWrite.domain.WebCacheVacctData;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2017-11-30.
 */

@Configuration
public class JobConfiguration {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private SqlSessionFactory sqlSessionFactoryWebCache;

    @Value("${webcache.parameter.orgBank}")
    private String orgBank;

    @Value("${webcache.parameter.orgCd}")
    private String orgCd;

    @Bean
    public MyBatisPagingItemReader<WebCacheVacctData> webCacheItemReader() {
        MyBatisPagingItemReader<WebCacheVacctData> reader = new MyBatisPagingItemReader<>();

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("orgBank", orgBank);
        parameters.put("orgCd", orgCd);


        reader.setQueryId("io.spring.databaseWrite.domain.WebCacheVacctData");
        reader.setParameterValues(parameters);
        reader.setPageSize(1000);
        reader.setSqlSessionFactory(sqlSessionFactoryWebCache);

        return reader;
    }

    @Bean
    public ItemWriter<WebCacheVacctData> itemWriter() {
        return items -> {
            items.forEach(item -> System.out.println(">> " + item));
        };
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("databaseWriteStep1")
                .<WebCacheVacctData, WebCacheVacctData>chunk(10)
                .reader(webCacheItemReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job statefulJob() {
        return jobBuilderFactory.get("databaseWriteJob")
                .start(step1())
                .build();
    }
}
