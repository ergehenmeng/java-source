package com.eghm.reentrantlock;

import com.eghm.User;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {
    public static void main(String[] args) {
        PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<>();
        queue.add(new User());
    }
}
