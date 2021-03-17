package com.eghm.arithmetic;

/**
 * 1-2-3-4-5
 * next = 2-3-4-5
 * head=1
 * pre=1
 *
 * @author 殿小二
 * @date 2021/3/15
 */
public class 反转链表 {

    public static void main(String[] args) {
        Node.println(reversal(Node.getInstance(5)));
    }

    public static Node reversal(Node head) {
        Node next;
        Node pre = null;
        while (head != null) {
            // 保存要反转到头来的那个节点
            next = head.next;
            // 要反转的那个节点指向已经反转的上一个节点
            head.next = pre;
            // 上一个已经反转到头部的节点
            pre = head;
            // 一直向链表尾走
            head = next;
        }
        return pre;
    }
}
