package ListStackQueue;
public class MergeSortedList21 {
    static class ListNode{
        int data;
        ListNode next;
        ListNode(int data){
            data = data;
        }
    }


    public static ListNode buildList(int[] nums){
        if(nums == null || nums.length == 0){
            return null;
        }
        ListNode head = new ListNode(nums[0]);
        ListNode currentNode = head;
        for(int num: nums){
            currentNode.next = new ListNode(num);
            currentNode = currentNode.next;
        }
        return head;
    }

    public static ListNode mergeList(ListNode list1Node, ListNode list2Node){
        ListNode resultHead = new ListNode(list1Node.data);
        ListNode currentNode = resultHead;
        while(list1Node != null && list2Node != null){
            if(list1Node.data < list2Node.data){
                currentNode.next = list1Node;
                list1Node = list1Node.next;
            }else{
                currentNode.next = list2Node;
                list2Node = list2Node.next;
            }
            currentNode = currentNode.next;
        }
        currentNode = (list1Node == null) ? list2Node : list1Node;
        return resultHead;
    }


    public static ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.data < l2.data) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }


    public static void main(String[] args){
        int[] nums = new int[]{1,2,3};
        int[] nums2 = {1,4,5,6};
        ListNode list1 = buildList(nums);
        ListNode list2 = buildList(nums2);
        ListNode result = mergeList(list1, list2);
    }
}


//
//
//public class MergeSortedList21 {
//
//    // Definition for singly-linked list.
//    static class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode(int x) {
//            val = x;
//        }
//    }
//
//    // 1. Iterative approach using a dummy node
//    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode dummy = new ListNode(-1);
//        ListNode current = dummy;
//
//        while (l1 != null && l2 != null) {
//            if (l1.val <= l2.val) {
//                current.next = l1;
//                l1 = l1.next;
//            } else {
//                current.next = l2;
//                l2 = l2.next;
//            }
//            current = current.next;
//        }
//
//        current.next = (l1 != null) ? l1 : l2;
//        return dummy.next;
//    }
//
//    // 2. Recursive approach
//    public static ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
//        if (l1 == null) return l2;
//        if (l2 == null) return l1;
//
//        if (l1.val < l2.val) {
//            l1.next = mergeTwoListsRecursive(l1.next, l2);
//            return l1;
//        } else {
//            l2.next = mergeTwoListsRecursive(l1, l2.next);
//            return l2;
//        }
//    }
//
//    // 3. Iterative without dummy node
//    public static ListNode mergeTwoListsManual(ListNode l1, ListNode l2) {
//        if (l1 == null) return l2;
//        if (l2 == null) return l1;
//
//        ListNode head;
//        if (l1.val <= l2.val) {
//            head = l1;
//            l1 = l1.next;
//        } else {
//            head = l2;
//            l2 = l2.next;
//        }
//
//        ListNode current = head;
//
//        while (l1 != null && l2 != null) {
//            if (l1.val <= l2.val) {
//                current.next = l1;
//                l1 = l1.next;
//            } else {
//                current.next = l2;
//                l2 = l2.next;
//            }
//            current = current.next;
//        }
//
//        current.next = (l1 != null) ? l1 : l2;
//        return head;
//    }
//
//    // Helper to build a list from an array
//    public static ListNode buildList(int[] nums) {
//        if (nums.length == 0) return null;
//
//        ListNode head = new ListNode(nums[0]);
//        ListNode current = head;
//
//        for (int i = 1; i < nums.length; i++) {
//            current.next = new ListNode(nums[i]);
//            current = current.next;
//        }
//
//        return head;
//    }
//
//    // Helper to print a list
//    public static void printList(ListNode head) {
//        while (head != null) {
//            System.out.print(head.val);
//            if (head.next != null) System.out.print(" -> ");
//            head = head.next;
//        }
//        System.out.println();
//    }
//
//    // Main method for testing
//    public static void main(String[] args) {
//        int[] arr1 = {1, 2, 4};
//        int[] arr2 = {1, 3, 4};
//
//        ListNode l1 = buildList(arr1);
//        ListNode l2 = buildList(arr2);
//
//        System.out.println("List 1:");
//        printList(l1);
//
//        System.out.println("List 2:");
//        printList(l2);
//
//        // Test one of the merge methods
//        ListNode merged = mergeTwoLists(l1, l2); // iterative with dummy
//        // ListNode merged = mergeTwoListsRecursive(l1, l2); // recursive
//        // ListNode merged = mergeTwoListsManual(l1, l2); // manual head
//
//        System.out.println("Merged List:");
//        printList(merged);
//    }
//}
//
////List 1:
////        1 -> 2 -> 4
////List 2:
////        1 -> 3 -> 4
////Merged List:
////        1 -> 1 -> 2 -> 3 -> 4 -> 4
