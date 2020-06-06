package com.eghm.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public void say(String a) {
        writeLock.lock();
        try {
            if ("a".equals(a)) {
                readLock.lock();
                System.out.println(2);
            }
            System.out.println(1);
        }finally {
            writeLock.lock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
        test.say("a");
    }

}
