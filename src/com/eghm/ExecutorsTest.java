package com.eghm;

import java.util.concurrent.*;

public class ExecutorsTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(1, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10086),
                r -> new Thread(r,"线程池-"));
        threadExecutor.execute(null);
        threadExecutor.prestartAllCoreThreads();
        long taskCount = threadExecutor.getTaskCount();
        long completedTaskCount = threadExecutor.getCompletedTaskCount();
        int largestPoolSize = threadExecutor.getLargestPoolSize();
        int poolSize = threadExecutor.getPoolSize();
        int activeCount = threadExecutor.getActiveCount();
    }

}
