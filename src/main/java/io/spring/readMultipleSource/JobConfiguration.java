package io.spring.readMultipleSource;

import io.spring.domain.Customer;
import io.spring.flatFile.CustomerFieldMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by USER on 2017-11-29.
 */
//@Configuration
public class JobConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("classpath*:/multiple_data/customer*.csv")
    private Resource[] inputFiles;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("readMultipleStep1")
                .<Customer, Customer>chunk(10)
                .reader(multiResourceItemReader())
                .writer(multiResourceItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("readMultipleJob")
                .start(step1())
                .build();
    }

    private MultiResourceItemReader<Customer> multiResourceItemReader() {
        MultiResourceItemReader<Customer> reader = new MultiResourceItemReader<>();

        reader.setDelegate(customItemReader());
        reader.setResources(inputFiles);
        return reader;
    }

    private FlatFileItemReader<Customer> customItemReader() {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();

        DefaultLineMapper<Customer> customerDefaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[] {"id", "firstName", "lastName" , "birthdate"});

        customerDefaultLineMapper.setLineTokenizer(tokenizer);
        customerDefaultLineMapper.setFieldSetMapper(new CustomerFieldMapper());
        customerDefaultLineMapper.afterPropertiesSet();

        reader.setLineMapper(customerDefaultLineMapper);

        return reader;
    }

    private ItemWriter<Customer> multiResourceItemWriter() {
        return items -> {
            items.forEach(item -> System.out.println(item.toString()));
        };
    }
}
