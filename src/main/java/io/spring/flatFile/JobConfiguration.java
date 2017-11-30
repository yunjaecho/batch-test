package io.spring.flatFile;

import com.sun.corba.se.impl.oa.toa.TOA;
import io.spring.domain.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 * Created by USER on 2017-11-27.
 */
//@Configuration
public class JobConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Customer> customerFlatFileItemReader() {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();

        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("/data/customer.csv"));

        DefaultLineMapper<Customer> customerDefaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[] {"id", "firstName", "lastName" , "birthdate"});

        customerDefaultLineMapper.setLineTokenizer(tokenizer);
        customerDefaultLineMapper.setFieldSetMapper(new CustomerFieldMapper());
        customerDefaultLineMapper.afterPropertiesSet();

        reader.setLineMapper(customerDefaultLineMapper);

        return reader;
    }

    @Bean
    public ItemWriter<Customer> customerItemWriter() {
        return items -> {
            items.forEach(item -> System.out.println(item.toString()));
//            for (Customer item : items) {
//                System.out.println(item.toString());
//            }
        };
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("flatFileStep1")
                .<Customer, Customer>chunk(10)
                .reader(customerFlatFileItemReader())
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("flatFileJob")
                .start(step1())
                .build();
    }

}
