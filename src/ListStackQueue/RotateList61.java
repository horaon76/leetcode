package ListStackQueue;

import java.util.*;
/**
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * Example 2:
 *
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 * **/

public class RotateList61 {
    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // Solution 1: Using Length and Iteration
    public static ListNode rotateRightLength(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // Compute the length of the list
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Connect the tail to the head
        tail.next = head;

        // Find the new tail and head
        k = k % length;  // Adjust k in case it's larger than length
        if (k == 0) {
            tail.next = null;  // Break the circle
            return head;
        }

        ListNode newTail = head;
        for (int i = 1; i < length - k; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;  // Break the circle

        return newHead;
    }

    // Solution 2: Using Two Pointers
    public static ListNode rotateRightTwoPointers(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        ListNode fast = head, slow = head;
        int length = 1;

        // Find the length of the list
        while (fast.next != null) {
            fast = fast.next;
            length++;
        }

        // Connect the tail to the head
        fast.next = head;

        k = k % length;  // Adjust k
        if (k == 0) {
            fast.next = null;  // Break the circle
            return head;
        }

        // Move fast k steps ahead
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast.next is the head
        while (fast.next != head) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode newHead = slow.next;
        slow.next = null;  // Break the circle

        return newHead;
    }

    // Solution 3: Using a Dummy Node
    public static ListNode rotateRightDummyNode(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Compute the length of the list
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            length++;
        }

        // Connect the tail to the dummy node
        dummy.next = head;

        k = k % length;  // Adjust k
        if (k == 0) return head;

        // Find the new tail and new head
        ListNode newTail = dummy;
        for (int i = 0; i < length - k; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;  // Break the circle

        return newHead;
    }

    // Method to print the linked list for verification
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // Helper method to create a linked list from an array
    public static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Main method for testing
    public static void main(String[] args) {

        // Example usage
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createList(arr);
        int k = 2;

        System.out.println("Original List:");
        printList(head);

        ListNode rotatedHead1 = rotateRightLength(head, k);
        System.out.println("Rotated List (Length Method):");
        printList(rotatedHead1);

        // Recreate the list for other methods
        head = createList(arr);
        ListNode rotatedHead2 = rotateRightTwoPointers(head, k);
        System.out.println("Rotated List (Two Pointers Method):");
        printList(rotatedHead2);

        // Recreate the list for the dummy node method
        head = createList(arr);
        ListNode rotatedHead3 = rotateRightDummyNode(head, k);
        System.out.println("Rotated List (Dummy Node Method):");
        printList(rotatedHead3);
    }
}
//Time & Space complexity
//Length and Iteration	O(n)	O(1)
//Two Pointers	O(n)	O(1)
//Dummy Node	O(n)	O(1)
