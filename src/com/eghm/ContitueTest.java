package com.eghm;

import java.util.concurrent.FutureTask;

public class ContitueTest {
    public static void main(String[] args) {
        int x = 0;
        retry:
        for (;;) {
            for (int i=0;i < 10;i++) {
                x++;
                if (i == 5){
                    continue retry;
                }
            }
            break;
        }
        System.out.println(x);
    }
}
