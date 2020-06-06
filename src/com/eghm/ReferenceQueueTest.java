package com.eghm;

import java.lang.ref.WeakReference;

public class ReferenceQueueTest {

    public static void main(String[] args) {
        User user = new User();
        WeakReference<User> reference = new WeakReference<>(user);
        user = null;
        System.gc();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
