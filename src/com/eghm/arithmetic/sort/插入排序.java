package com.eghm.arithmetic.sort;

import java.util.Arrays;

/**
 * 以第一个数为基准,第二个数与第一个数比较,小的排在前面,
 * 依次第三个,与前面第二个比较如果小于,替换位置,再与第一个比较
 * @author 殿小二
 * @date 2021/3/13
 */
public class 插入排序 {

    public static void main(String[] args) {
        int[] array = {2,4,6,3,9,7,8,5,12,35,14,42,23};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if (array[j] > array[j-1]) {
                    break;
                }
                int temp = array[j-1];
                array[j-1] = array[j];
                array[j] = temp;
            }
        }
    }
}
