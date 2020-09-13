package com.sugon.gsq.lighthouse.core.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/*
 * ClassName: CoreConfig
 * Author: gsq
 * Date: 2020/5/11 10:44
 */
@Configuration
public class CoreConfig {

  /*
  * 线程池(执行任务使用)
  * */
  @Bean(value = "consumerQueueThreadPool")
  public ExecutorService buildConsumerQueueThreadPool(){
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("consumer-queue-thread-%d").build();
    ExecutorService pool = new ThreadPoolExecutor(10, 10, 0L,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(100),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
    return pool;
  }

  /*
   * 线程池(调度任务使用)
   * */
  @Bean(value = "scheduledExecutorThreadPool")
  public ExecutorService buildScheduledExecutorThreadPool(){
    return Executors.newScheduledThreadPool(10);
  }

}
