package com.eghm.reentrantlock;

import com.eghm.User;
import com.google.common.collect.Lists;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {
    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(Lists.newArrayList(1,4,3,2,5,7,9,11));
        System.out.println(queue);
    }
}
