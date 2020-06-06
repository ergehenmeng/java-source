package com.eghm.arithmetic;

import java.util.Stack;

public class 第一题_有getMin的栈 {


	public static void main(String[] args) {
		MinStack stack = new MinStack();
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(1);
		stack.push(2);
		stack.push(1);
		System.out.println(stack.getMin());
		System.out.println(stack.pop());
	}



	static class MinStack {

		private Stack<Integer> stack;

		private Stack<Integer> minStack;

		public MinStack() {
			this.stack = new Stack<>();
			this.minStack = new Stack<>();
		}

		public Integer getMin() {
			if (minStack.isEmpty()) {
				return null;
			}
			return minStack.peek();
		}

		public Integer pop() {
			if (stack.isEmpty()) {
				return null;
			}
			Integer pop = stack.pop();
			Integer min = this.getMin();
			if (min != null && min.equals(pop)) {
				minStack.pop();
			}
			return pop;
		}

		public void push(Integer value) {
			stack.push(value);
			Integer min = this.getMin();
			// 如果新插入的值比头元素大,则不插入,在取的时候依旧做额外处理
			if (min == null || min.compareTo(value) >= 0) {
				minStack.push(value);
			}
		}
	}

	static class MinStackSecond {

		private Stack<Integer> stack;

		private Stack<Integer> minStack;

		public MinStackSecond() {
			this.stack = new Stack<>();
			this.minStack = new Stack<>();
		}

		public Integer getMin() {
			if (minStack.isEmpty()) {
				return null;
			}
			return minStack.peek();
		}

		public Integer pop() {
			if (stack.isEmpty()) {
				return null;
			}
			Integer pop = stack.pop();
			Integer min = this.getMin();
			if (min != null && min.equals(pop)) {
				minStack.pop();
			}
			return pop;
		}

		public void push(Integer value) {
			stack.push(value);
			Integer min = this.getMin();
			if (min == null || min.compareTo(value) >= 0) {
				minStack.push(value);
			} else {
				// 将头元素再次压入一次(占坑)
				minStack.push(min);
			}
		}
	}
}
