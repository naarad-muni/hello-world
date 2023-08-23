package org.naarad.muni.queue;

/**
 * <a href="https://leetcode.com/problems/design-circular-queue/">Circular queue</a>
 */
public class CircularQueue {

    public static void main(final String[] args) {
        final MyCircularQueue queue = new MyCircularQueue(3);
        System.out.println("Dequeue" + queue.deQueue());
        System.out.println("Enque" + queue.enQueue(20));
        System.out.println("Enque" + queue.enQueue(10));
        System.out.println("Enque" + queue.enQueue(40));
        System.out.println("Enque" + queue.enQueue(50));
        System.out.println("Dequeue" + queue.deQueue());
        System.out.println("Dequeue" + queue.deQueue());
        System.out.println("Enque" + queue.enQueue(50));
        System.out.println("Enque" + queue.enQueue(80));
        System.out.println("Dequeue" + queue.deQueue());
        System.out.println("Is empty" + queue.isEmpty());
        System.out.println("Is full" + queue.isFull());
    }

    private static class MyCircularQueue {
        int[] queue;
        int firstElementIndex;
        int lastElementIndex;
        int queueSize;
        int numberOfElements;

        public MyCircularQueue(final int k) {
            queue = new int[k];
            queueSize = k;
            numberOfElements = 0;
            firstElementIndex = lastElementIndex = -1;
        }

        public boolean enQueue(final int value) {
            if (numberOfElements == queueSize) {
                return false;
            }

            lastElementIndex = (lastElementIndex + 1) % queueSize;
            queue[lastElementIndex] = value;
            firstElementIndex = firstElementIndex == -1 ? 0 : firstElementIndex;
            numberOfElements++;
            return true;
        }

        public boolean deQueue() {
            if (numberOfElements == 0) {
                return false;
            }

            firstElementIndex = (firstElementIndex + 1) % queueSize;
            numberOfElements--;
            return true;
        }

        public int Front() {
            if (numberOfElements == 0) {
                return -1;
            }

            return queue[firstElementIndex];
        }

        public int Rear() {
            if (numberOfElements == 0) {
                return -1;
            }

            return queue[lastElementIndex];
        }

        public boolean isEmpty() {
            return numberOfElements == 0;
        }

        public boolean isFull() {
            return numberOfElements == queueSize;
        }
    }
}
