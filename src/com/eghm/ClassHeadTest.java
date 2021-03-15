package com.eghm;

import org.openjdk.jol.info.ClassLayout;

/**
 *  A Reference instance is in one of four possible internal states:<br/>
 *
 *     Active: Subject to special treatment by the garbage collector.  Some
 *     time after the collector detects that the reachability of the
 *     referent has changed to the appropriate state, it changes the
 *     instance's state to either Pending or Inactive, depending upon
 *     whether or not the instance was registered with a queue when it was
 *     created.  In the former case it also adds the instance to the
 *     pending-Reference list.  Newly-created instances are Active.<br>
 *
 *     Pending: An element of the pending-Reference list, waiting to be
 *     enqueued by the Reference-handler thread.  Unregistered instances
 *     are never in this state.<br>
 *
 *     Enqueued: An element of the queue with which the instance was
 *     registered when it was created.  When an instance is removed from
 *     its ReferenceQueue, it is made Inactive.  Unregistered instances are
 *     never in this state.<br>
 *
 *     Inactive: Nothing more to do.  Once an instance becomes Inactive its
 *     state will never change again.<br>
 *
 * The state is encoded in the queue and next fields as follows:<br>
 *
 *     Active: queue = ReferenceQueue with which instance is registered, or
 *     ReferenceQueue.NULL if it was not registered with a queue; next =
 *     null.<br>
 *
 *     Pending: queue = ReferenceQueue with which instance is registered;
 *     next = this <br>
 *
 *     Enqueued: queue = ReferenceQueue.ENQUEUED; next = Following instance
 *     in queue, or this if at end of list. <br>
 *
 *     Inactive: queue = ReferenceQueue.NULL; next = this. <br>
 *
 * With this scheme the collector need only examine the next field in order
 * to determine whether a Reference instance requires special treatment: If
 * the next field is null then the instance is active; if it is non-null,
 * then the collector should treat the instance normally. <br>
 *
 * To ensure that a concurrent collector can discover active Reference
 * objects without interfering with application threads that may apply
 * the enqueue() method to those objects, collectors should link
 * discovered objects through the discovered field. The discovered
 * field is also used for linking Reference objects in the pending list.
 */
public class ClassHeadTest {
    public static void main(String[] args) throws InterruptedException {
        ClassHead head = new ClassHead();
        User[] users = new User[2];
        System.out.println(ClassLayout.parseInstance(head).toPrintable());
        System.gc();
        Thread.sleep(1000);
        System.gc();
        Thread.sleep(1000);
        System.gc();
        System.out.println(ClassLayout.parseInstance(head).toPrintable());


        synchronized (head){
            // 查看对象头信息 偏向锁等
            System.out.println(ClassLayout.parseInstance(head).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(users).toPrintable());
    }

    /**
     * 对象头是对象实例创建后才会有的
     */
    public static class ClassHead {

    }
}
