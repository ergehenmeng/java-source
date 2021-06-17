package com.eghm.reference;

import java.lang.ref.WeakReference;

/**
 * @author 殿小二
 * @date 2021/6/17
 */
public class AppleRef extends WeakReference<Apple> {
    
    private String ref;
    
    public AppleRef(Apple referent, String ref) {
        super(referent);
        this.ref = ref;
    }
    
    public String getRef() {
        return ref;
    }
    
    public static void main(String[] args) throws InterruptedException {
        AppleRef ref = new AppleRef(new Apple("红富士"), "ref");
        System.out.println("apple: " + ref.get());
        System.gc();
        Thread.sleep(10000);
        if (ref.get() == null) {
            System.out.println("apple gc ");
        }
        System.out.println("ref: " + ref.getRef());
    }
}
