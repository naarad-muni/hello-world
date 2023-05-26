package org.naarad.muni.nocategory;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class MergeKsortedLists {
    // [[1,4,5],[1,3,4],[2,6]]
    public static void main(String[] args) {
        ListNode first3 = new ListNode(5);
        ListNode first2 = new ListNode(4, first3);
        ListNode first = new ListNode(1, first2);

        ListNode second3 = new ListNode(4);
        ListNode second2 = new ListNode(3, second3);
        ListNode second = new ListNode(1, second2);

        ListNode third2 = new ListNode(6);
        ListNode third = new ListNode(2, third2);

        ListNode[] arr = new ListNode[]{first, second, third};

        MergeKsortedLists lists = new MergeKsortedLists();

        ListNode consolidated = lists.mergeKLists(arr);

        ListNode head = consolidated;

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        final TreeSet<ListNode> sortedNodes = buildInitialSortedSet(lists);

        if (sortedNodes.isEmpty()) {
            return null;
        }

        final Iterator<ListNode> iterator = sortedNodes.iterator();
        ListNode head = null;
        ListNode tail = head;

        while (iterator.hasNext()) {
            ListNode temp = new ListNode(iterator.next().val);

            if (head == null) {
                head = tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }

        }

        return head;
    }

    private TreeSet<ListNode> buildInitialSortedSet(ListNode[] lists) {
        final TreeSet<ListNode> sortedNodes = new TreeSet<>((firstNode, secondNode) -> {
            if (firstNode.val == secondNode.val) {
                return -1;
            } else {
                return Integer.compare(firstNode.val, secondNode.val);
            }
        });

        for (int i = 0; i < lists.length; i++) {
            ListNode j = lists[i];
            while (j != null) {
                sortedNodes.add(j);
                j = j.next;
            }
        }

        return sortedNodes;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
