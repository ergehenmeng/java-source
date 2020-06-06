package com.eghm;

import java.util.concurrent.*;

public class ExecutorsTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Integer.toBinaryString((1 << 29) - 1));
        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2),
                r -> new Thread(r,"线程池-"));
        Future<Integer> submit = threadExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1;
            }
        });
        threadExecutor.execute(new Take(submit));
        threadExecutor.execute(new Take(submit));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FutureTask<Integer> task = (FutureTask<Integer>) submit;
        long taskCount = threadExecutor.getTaskCount();
        long completedTaskCount = threadExecutor.getCompletedTaskCount();
        int largestPoolSize = threadExecutor.getLargestPoolSize();
        int poolSize = threadExecutor.getPoolSize();
        int activeCount = threadExecutor.getActiveCount();
    }
    private static class Take implements Runnable{
        Future<Integer> submit;

        public Take(Future<Integer> submit) {
            this.submit = submit;
        }

        @Override
        public void run() {
            try {
                Integer integer = submit.get();
                System.out.println(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
