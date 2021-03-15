package com.eghm.arithmetic.sort;

import java.util.Arrays;

/**
 * @author 殿小二
 * @date 2021/3/13
 */
public class 选择排序 {


    public static void main(String[] args) {
        int[] array = {2,4,6,3,9,7,8,5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int oldMin = array[i];
            int min = array[i];
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            array[i] = array[minIndex];
            array[minIndex] = oldMin;
        }
    }
}
