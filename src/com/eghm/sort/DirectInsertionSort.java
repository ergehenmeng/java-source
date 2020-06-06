package com.eghm.sort;

import java.util.Arrays;

/**
 * 直接插入排序
 */
public class DirectInsertionSort {

	public static void main(String[] args) {
		int[] str = {3, 1, 4, 9, 2, 6, 5};
		sort(str);
		System.out.println(Arrays.toString(str));
	}
	/**
	 * 3 1 4 9 2 6 5
	 * 1 3 4 9 2 6 5
	 * 1 3 4 2 9 6 5
	 * 1 3 2 4 9 6 5
	 * 1 2 3 4 9 6 5
	 * 1 2 3 4 6 9 5
	 * 1 2 3 4 6 5 9
	 * 1 2 3 4 5 6 9
	 *
	 */
	public static void sort(int[] array) {
		//从1开始依次和前面的进行比较
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int j = i - 1;
			//将大于temp的值整体后移,直到temp在合适的位置(从头开始遍历时,最开始已经保持了有序性,因此该for循环只需移动位置即可)
			for (; j >= 0 && array[j] > temp; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = temp;
		}
	}
}
