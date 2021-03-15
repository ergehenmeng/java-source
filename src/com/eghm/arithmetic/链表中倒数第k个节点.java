package com.eghm.arithmetic;

/**
 * 0-1-2-3-4-5-6-7-8-9-10-11-12-13
 * 假设 k=2即11; 反向对称,正向时k=2
 * 链表中倒数第k个节点也就是正数第(L-K+1)个节点，知道了只一点，这一题基本就没问题！
 * 首先两个节点/指针，一个节点 node1 先开始跑，指针 node1 跑到 k-1 个节点后，另一个节点 node2 开始跑，
 * 当 node1 跑到最后时，node2 所指的节点就是倒数第k个节点也就是正数第(L-K+1)个节点。
 * @author 殿小二
 * @date 2021/3/15
 */
public class 链表中倒数第k个节点 {

    public static void main(String[] args) {
        Node.println(findKTail(Node.getInstance(14), 4));
    }

    public static Node findKTail(Node head, int k) {
        if (head == null || k < 0) {
            return null;
        }
        int i = 0;
        Node first = head;
        while (i < k) {
            i++;
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            head = head.next;
        }
        return head;
    }
}
