package com.eghm;

public class TryFinallyTest {

    private int x = 0;

    public int tryFinally(){
        try {
            return x++;
        }finally {
            return ++x;
        }
    }

    public static void main(String[] args) {
        TryFinallyTest test = new TryFinallyTest();
        System.out.println(test.tryFinally());
    }
}
