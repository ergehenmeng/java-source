package com.eghm.reentrantlock;

public class Consumer implements Runnable {

    ConditionTest test;
    public Consumer(ConditionTest test) {
        this.test =test;
    }


    @Override
    public void run() {
        while (true){
            test.consume();
        }
    }
}
