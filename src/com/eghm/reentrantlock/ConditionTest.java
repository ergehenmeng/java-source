package com.eghm.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    private Lock lock = new ReentrantLock();
    private boolean flag = false;
    private Condition condition = lock.newCondition();
    private volatile int number = 1;

    /**
     * 生产者生产
     */
    public void produce() {
        lock.lock();
        try {
            while (flag) {
                condition.await();
            }
            System.out.println("-----生产-----");
            number++;
            System.out.println("number: " + number);

            flag = true;
            // 提醒消费者消费
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费者消费生产的物品
     */
    public void consume() {
        lock.lock();
        try {
            while (!flag) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "-----消费-----");
            number--;
            System.out.println("number: " + number);
            flag = false;
            // 提醒生产者生产
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();
        new Thread(new Producer(test)).start();
        new Thread(new Consumer(test)).start();
        new Thread(new Consumer(test)).start();
    }
}
