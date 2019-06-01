package com.fatihctn.pigeon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class BaseConfig {

    @Value("${threadpool.pool.size}")
    private int size;

    @Value("${threadpool.pool.max-size}")
    private int maxPoolSize;

    @Value("${threadpool.executor.name}")
    private String executorName;

    @Value("${threadpool.queue.capacity}")
    private int queueCapacity;

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(size);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setThreadNamePrefix(executorName);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
