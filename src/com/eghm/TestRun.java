package com.eghm;

import java.util.Random;

public class TestRun {

    public static void main(String[] args) {
        String lock = "lock";

        for (int i = 0; i < 2; i++) {
            new Thread(new Run(lock)).start();
        }

    }


    public static class Run implements Runnable {

        private static int x = 1;

        private String lock;

        public Run(String lock) {
            this.lock =  lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("start: " + Thread.currentThread().getName());
                try {
                    if (x == 1) {
                        x++;
                        lock.wait();
                    } else {
                        lock.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end: " + Thread.currentThread().getName());
            }
        }
    }

}
