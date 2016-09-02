package com.ciandt.poc.mongodb.conf.web;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptorAdapter;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by rodrigosd on 6/13/16.
 */
@Configuration
@Slf4j
@EnableConfigurationProperties(WebConfiguration.ThreadPoolConfigProperties.class)
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ThreadPoolConfigProperties threadPoolConfig;

    @Override
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
        configurer.registerDeferredResultInterceptors(
            new DeferredResultProcessingInterceptorAdapter() {
                @Override
                public <T> boolean handleTimeout(final NativeWebRequest request, final DeferredResult<T> result) {
                    log.error("timeout request...");
                    result.setErrorResult(new RuntimeException());
                    return false;
                }
            });
    }

    @Bean
    public TaskExecutor asyncTaskExecutor() {
        final ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(threadPoolConfig.getSize());
        pool.setThreadGroupName(threadPoolConfig.getName());
        return pool;
    }

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "thread.pool")
    public static class ThreadPoolConfigProperties {

        private Integer size;
        private String name;

    }
}
