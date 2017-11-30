package io.spring.inputStreamReader;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by USER on 2017-11-30.
 */
public class SysOutItemWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("The size of the chunk was " + items.size());

        items.stream().forEach(item -> System.out.println(">> " + item));
    }
}
