package com.eghm.reentrantlock;

public class Producer  implements  Runnable{
    ConditionTest test;
    public Producer(ConditionTest test){
        this.test =test;
    }

    @Override
    public void run() {
        while (true){
            test.produce();
        }
    }
}
