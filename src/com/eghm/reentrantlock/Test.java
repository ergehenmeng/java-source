package com.eghm.reentrantlock;

public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runner());
        thread.start();
        thread.interrupt();
    }

    private static class Runner implements Runnable {

        @Override
        public void run() {
            while (true){
                System.out.println("我在睡觉");
            }
        }
    }
}
