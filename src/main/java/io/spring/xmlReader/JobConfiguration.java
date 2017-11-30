package io.spring.xmlReader;

import io.spring.domain.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by USER on 2017-11-28.
 */
//@Configuration
public class JobConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public StaxEventItemReader<JangbojaItem> jangbojaItemReader() throws Exception {

        XStreamMarshaller unmarshaller = new XStreamMarshaller();

        Map<String, Class> aliases = new HashMap<>();
        aliases.put("Item", JangbojaItem.class);

        unmarshaller.setAliases(aliases);

        StaxEventItemReader<JangbojaItem> reader = new StaxEventItemReader<>();

        URL url = new URL("http://www.jangboja.com/shop/partner/posmall_goodsList.php");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());

        reader.setResource(new InputStreamResource(in));
        reader.setFragmentRootElementName("Item");
        reader.setUnmarshaller(unmarshaller);

        return reader;
    }

    @Bean
    public ItemWriter<JangbojaItem> jangbojaIItemWriter() {
        return items -> {
            for (JangbojaItem item : items) {
                System.out.println(item.toString());
            }
        };
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("xmlReaderStep1")
                .<JangbojaItem, JangbojaItem>chunk(10)
                .reader(jangbojaItemReader())
                .writer(jangbojaIItemWriter())
                .build();
    }

    @Bean
    public Job job() throws Exception  {
        return jobBuilderFactory.get("xmlReaderJob")
                .start(step1())
                .build();
    }



}
