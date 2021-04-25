package com.eghm.arithmetic;

import java.util.Stack;

public class 第三题_使用递归加栈实现逆序栈 {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		FlashbackStack<Integer> flashbackStack = new FlashbackStack<>();
		flashbackStack.reserve(stack);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

	static class FlashbackStack<T> {

	    // 查找栈的最后一个元素
		public T removeLast(Stack<T> stack) {
			T pop = stack.pop();
			if (stack.isEmpty()) {
				return pop;
			}
			T last = removeLast(stack);
			stack.push(pop);
			return last;
		}
        // 反转栈
		public void reserve(Stack<T> stack) {
			if (stack.isEmpty()) {
				return;
			}
			T last = removeLast(stack);
			reserve(stack);
			stack.push(last);
		}
	}
}
