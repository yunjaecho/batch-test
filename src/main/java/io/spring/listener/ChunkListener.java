package io.spring.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * Created by USER on 2017-11-23.
 */
public class ChunkListener {
    @BeforeChunk
    public void beforeChunk(ChunkContext context) {
        System.out.println(">> Before the chunk");
    }

    @AfterChunk
    public void afterChunk(ChunkContext context) {
        System.out.println("<< After the chunk");
    }
}
