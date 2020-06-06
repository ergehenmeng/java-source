package com.eghm.reentrantlock;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayItem> queue = new DelayQueue<>();
        queue.add(new DelayItem(TimeUnit.SECONDS.toNanos(50)));
        new Thread(new Customer(queue)).start();
        new Thread(new Customer(queue)).start();
        queue.add(new DelayItem(TimeUnit.SECONDS.toNanos(10)));
        Thread.sleep(100000);
    }

    private static class Customer implements Runnable {

        DelayQueue<DelayItem> queue;

        public Customer(DelayQueue<DelayItem> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                DelayItem take = queue.take();
                System.out.println(take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
