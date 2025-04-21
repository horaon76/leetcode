package ListStackQueue;

public class IntersectionOfLinkedlist160 {
    static class ListNode{
        int data;
        ListNode next;
        ListNode(int data){
            data = data;
        }
    }

    //pointer method;
    public static ListNode getIntersectionNode(ListNode list1, ListNode list2){
        ListNode list1Head = list1;
        ListNode list2Head = list2;
        while(list1Head != list2Head){
            list1Head = (list1Head == null) ? list2Head : list1Head.next;
            list2Head = (list2Head == null) ? list1Head : list1Head.next;
        }
        return list1Head;
    }

    private static int getLength(ListNode list){
        int length = 0;
        ListNode currentNode = list;
        while(currentNode != null){
            currentNode = currentNode.next;
            length++;
        }
        return length;
    }

    public static ListNode getIntersectionByDistanceMethod(ListNode headA, ListNode headB){
        int lenA = getLength(headA);
        int lenB = getLength(headB);

        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }

        while (lenB > lenA) {
            headB = headB.next;
            lenB--;
        }

        while (headA != null && headB != null) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }
    // Utility to print node
    private static void printResult(ListNode result) {
        if (result != null) {
            System.out.println("Intersection at node with value: " + result.data);
        } else {
            System.out.println("No intersection");
        }
    }
    public static void main(String[] args) {
        // Test 1: Both lists null
        printResult(getIntersectionNode(null, null));

        // Test 2: One list null
        ListNode headA2 = null;
        ListNode headB2 = new ListNode(1);
        headB2.next = new ListNode(2);
        printResult(getIntersectionNode(headA2, headB2));

        // Test 3: No intersection
        ListNode headA3 = new ListNode(1);
        headA3.next = new ListNode(2);
        headA3.next.next = new ListNode(3);

        ListNode headB3 = new ListNode(4);
        headB3.next = new ListNode(5);
        headB3.next.next = new ListNode(6);
        printResult(getIntersectionNode(headA3, headB3));

        // Test 4: Intersection at beginning (shared list)
        ListNode common4 = new ListNode(7);
        common4.next = new ListNode(8);
        ListNode headA4 = common4;
        ListNode headB4 = common4;
        printResult(getIntersectionNode(headA4, headB4));

        // Test 5: Intersection in middle
        ListNode common5 = new ListNode(8);
        common5.next = new ListNode(4);
        common5.next.next = new ListNode(5);

        ListNode headA5 = new ListNode(4);
        headA5.next = new ListNode(1);
        headA5.next.next = common5;

        ListNode headB5 = new ListNode(5);
        headB5.next = new ListNode(0);
        headB5.next.next = new ListNode(1);
        headB5.next.next.next = common5;

        printResult(getIntersectionNode(headA5, headB5));

        // Test 6: Long A, short B, intersecting
        ListNode common6 = new ListNode(9);
        ListNode headA6 = new ListNode(1);
        headA6.next = new ListNode(2);
        headA6.next.next = new ListNode(3);
        headA6.next.next.next = common6;

        ListNode headB6 = common6;

        printResult(getIntersectionNode(headA6, headB6));
    }
}
