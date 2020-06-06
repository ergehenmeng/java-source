package com.eghm.sort;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		int[] array = {3, 1, 4, 9, 2, 6, 5};
		sort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void sort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
				//相邻两个比较把大的放后面,这样最终循环完毕时,可以将最大的值放在最后面
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}
}
