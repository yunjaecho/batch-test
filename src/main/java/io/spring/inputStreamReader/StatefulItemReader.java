package io.spring.inputStreamReader;

import org.springframework.batch.item.*;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * Created by USER on 2017-11-30.
 */
public class StatefulItemReader implements ItemStreamReader<String> {

    private final List<String> items;
    private int curIndex = -1;
    private boolean restart = false;

    public StatefulItemReader(List<String> items) {
        this.items = items;
        this.curIndex = 0;
    }


    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        String item = null;

        if (this.curIndex < this.items.size()) {
            item = this.items.get(this.curIndex);
            this.curIndex++;
        }

        if (this.curIndex == 42 && !restart) {
            throw new RuntimeException("The Answer to the Ultimate Question of Life, the Universe");
        }

        return item;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        if (executionContext.containsKey("curIndex")) {
            this.curIndex = executionContext.getInt("curIndex");
            this.restart = true;
        } else {
            this.curIndex = 0;
            executionContext.put("curIndex", this.curIndex);
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        executionContext.put("curIndex", this.curIndex);
    }

    @Override
    public void close() throws ItemStreamException {
    }
}
