package com.ruoyi.framework.config;/**
 * Created by:Ricardo
 * Description:
 * Date: 2018/6/21
 * Time: 22:40
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 *@ClassName: AsyncTaskConfig
 *@Description: TODO // 线程配置类
 *@Author: Ricardo
 *@Date: 2018/6/21 22:40
 **/
//@ComponentScan("test")
@Configuration
@EnableAsync
public class AsyncTaskConfig implements AsyncConfigurer {
    private final static Logger logger = LoggerFactory.getLogger(AsyncTaskConfig.class);
    // ThredPoolTaskExcutor的处理流程
    // 当池子大小小于corePoolSize，就新建线程，并处理请求
    // 当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去workQueue中取任务并处理
    // 当workQueue放不下任务时，就新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize，就用RejectedExecutionHandler来做拒绝处理
    // 当池子的线程数大于corePoolSize时，多余的线程会等待keepAliveTime长时间，如果无请求可处理就自行销毁

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);// 最小线程数
        taskExecutor.setMaxPoolSize(10);// 最大线程数
        taskExecutor.setQueueCapacity(25);// 等待队列

        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * 异步异常处理
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SpringAsyncExceptionHandler();
    }

    class SpringAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            logger.error("Exception occurs in async method", throwable.getMessage());
        }
    }
}