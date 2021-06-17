package com.eghm.reference;

/**
 * @author 殿小二
 * @date 2021/6/17
 */
public class ThreadLocalRef {
    
    private static final ThreadLocal<Apple> LOCAL = ThreadLocal.withInitial(() -> new Apple("红富士"));
    
    public static void main(String[] args) {
        new Thread(() -> {
           LOCAL.set(new Apple("二哥"));
           System.gc();
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            System.out.println(LOCAL.get());
    
        }).start();
    }
}
