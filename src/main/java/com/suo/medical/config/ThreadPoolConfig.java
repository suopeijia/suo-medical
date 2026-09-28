package com.suo.medical.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置类
 */
@Configuration
public class ThreadPoolConfig {

    @Bean(destroyMethod = "shutdown")
    public ThreadPoolExecutor bizThreadPool(){
        return new ThreadPoolExecutor(
                3,//核心线程数
                5,//最大线程数
                60, TimeUnit.SECONDS,//空闲时间
                new ArrayBlockingQueue<>(10),//阻塞队列
                new ThreadPoolExecutor.AbortPolicy()//拒绝策略
        );
    }
}
