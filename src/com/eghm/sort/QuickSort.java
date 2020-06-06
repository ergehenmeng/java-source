package com.eghm.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] str = {3, 1, 4, 9, 2, 6, 5};
		sort(str,0, str.length - 1);
		System.out.println(Arrays.toString(str));
	}

	public static void sort(int[] array, int low, int high) {
		if (low > high) {
			return;
		}
		int base = array[low];
		int i = low;
		int j = high;
		while (i < j) {
			while (base <= array[j] && i < j) {
				j--;
			}
			while (base >= array[i] && i < j) {
				i++;
			}
			if (i < j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		array[low] = array[i];
		array[i] = base;
		sort(array, low, j-1);
		sort(array, j+1, high);
	}
}
