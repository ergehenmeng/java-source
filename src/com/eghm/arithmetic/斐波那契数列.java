package com.eghm.arithmetic;

/**
 * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增，
 * 因此从左下角开始查找，当要查找数字比左下角数字大时。右移要查找数字比左下角数字小时，上移这样找的速度最快。
 *
 * a.如果两种跳法，1阶或者2阶，那么假定第一次跳的是一阶，
 * 那么剩下的是n-1个台阶，跳法是f(n-1);
 * b.假定第一次跳的是2阶，那么剩下的是n-2个台阶，跳法是f(n-2)
 * c.由a，b假设可以得出总跳法为: f(n) = f(n-1) + f(n-2)
 * d.然后通过实际的情况可以得出：只有一阶的时候 f(1) = 1 ,只有两阶的时候可以有 f(2) = 2
 * 找规律分析法： f(1) = 1, f(2) = 2, f(3) = 3, f(4) = 5， 可以总结出f(n) = f(n-1) + f(n-2)的规律。
 * 但是为什么会出现这样的规律呢？假设现在6个台阶，我们可以从第5跳一步到6，这样的话有多少种方案跳到5就有多少种方案跳到6，
 * 另外我们也可以从4跳两步跳到6，跳到4有多少种方案的话，就有多少种方案跳到6，其他的不能从3跳到6什么的啦，
 * 所以最后就是f(6) = f(5) + f(4)；这样子也很好理解变态跳台阶的问题了。
 * @author 殿小二
 * @date 2021/3/15
 */
public class 斐波那契数列 {
}
