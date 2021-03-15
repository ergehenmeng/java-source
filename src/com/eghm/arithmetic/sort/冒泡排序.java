package com.eghm.arithmetic.sort;

import java.util.Arrays;

/**
 * @author 殿小二
 * @date 2021/3/13
 */
public class 冒泡排序 {

    public static void main(String[] args) {
        int[] array = {2,4,6,3,9,7,5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                int right = array[j];
                int left = array[j-1];
                if (left > right) {
                    array[j-1] = right;
                    array[j] = left;
                }
            }
        }
    }
}
