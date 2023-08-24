package org.naarad.muni.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/design-front-middle-back-queue/description/">Front middle back queue</a>
 */
public class FrontMiddleQueue {

    public static void main(final String[] args) {

    }

    class FrontMiddleBackQueue {
        private static final int FIRST_INDEX = 0;
        private final List<Integer> queue;

        public FrontMiddleBackQueue() {
            queue = new ArrayList<>();
        }

        public void pushFront(final int val) {
            queue.add(FIRST_INDEX, val);
        }

        public void pushMiddle(final int val) {
            queue.add(queue.size() / 2, val);
        }

        public void pushBack(final int val) {
            queue.add(val);
        }

        public int popFront() {
            if (queue.isEmpty()) {
                return -1;
            } else {
                return queue.remove(FIRST_INDEX);
            }
        }

        public int popMiddle() {
            if (queue.isEmpty()) {
                return -1;
            } else {
                final int indexToDelete = queue.size() % 2 == 0 ? (queue.size() / 2) - 1 : (queue.size() / 2);
                return queue.remove(indexToDelete);
            }
        }

        public int popBack() {
            if (queue.isEmpty()) {
                return -1;
            } else {
                return queue.remove(queue.size() - 1);
            }
        }
    }
}
