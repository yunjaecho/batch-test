package io.spring.inputStreamReader;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017-11-30.
 */
//@Configuration
public class JobConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    /*@Bean
    @StepScope
    public StatefulItemReader itemReader() {
        List<String> items = new ArrayList<>(100);

        for (int i = 1; i <= 100; i++) {
            items.add(String.valueOf(i));
        }

        return new StatefulItemReader(items);
    }*/

    @Bean
    public ListItemReader<String> itemReader() {
        List<String> items = new ArrayList<>(100);

        for (int i = 1; i <= 100; i++) {
            items.add(String.valueOf(i));
        }

        return new ListItemReader<>(items);
    }


    /*@Bean
    public ItemWriter itemWriter() {
        return items -> {
            items.forEach(item -> System.out.println(">> " + item));
        };
    }*/

    @Bean
    public SysOutItemWriter itemWriter() {
        return new SysOutItemWriter();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("inputStreamReaderStep1")
                .<String, String>chunk(10)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job statefulJob() {
        return jobBuilderFactory.get("inputStreamReaderJob")
                .start(step1())
                .build();
    }

}
