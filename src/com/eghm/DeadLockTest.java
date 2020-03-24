package com.eghm;

public class DeadLockTest {
    public static void main(String[] args) {
        String a = "A";
        String b = "B";
        new Thread(new Dead(a,b)).start();
        new Thread(new Dead(b,a)).start();
    }
}

class Dead implements Runnable {

    private String lockA;

    private String lockB;

    public Dead(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println("获取锁 " + lockA);
            try{
                Thread.sleep(1000);
                synchronized (lockB){
                    System.out.println("获取锁 " + lockB);
                }
            }catch (Exception e){

            }
        }
    }
}
