package io.spring.reader;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by USER on 2017-11-24.
 */
//@Configuration
public class JobConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public StatelessItemReader statelessItemReader() {
        List<String> data = Arrays.asList("Foo", "Bar", "Baz");
        return new StatelessItemReader(data);
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(2)
                .reader(statelessItemReader())
                .writer(list -> list.forEach(curItem ->{
                    System.out.println("curItem = " + curItem);
                } )).build();
    }

    @Bean
    public Job interfacesJob() {
        return jobBuilderFactory.get("interfacesJob")
                .start(step1())
                .build();
    }


}
