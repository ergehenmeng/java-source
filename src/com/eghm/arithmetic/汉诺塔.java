package com.eghm.arithmetic;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 *
 *
 *
 * @author 殿小二
 * @date 2021/3/13
 */
public class 汉诺塔 {

    private static void move(int deep, Stack<Integer> a, Stack<Integer> b, Stack<Integer> c) {
        if (deep == 2) {
            b.push(a.pop());
            c.push(a.pop());
            c.push(b.pop());
            return;
        } else {
            move(--deep, a, b, c);
        }
        System.out.println(Arrays.toString(a.toArray()) + " " + Arrays.toString(b.toArray()) + "" + Arrays.toString(c.toArray()));
    }



    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        move(5, stack, new Stack<>(), new Stack<>());
    }
}
