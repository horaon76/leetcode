package ListStackQueue;

import java.util.HashSet;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Cycle141 {

    public static boolean hasCycle(ListNode head){
        if(head == null || head.next == null){
            return false;
        }

        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while(fastPointer != null && fastPointer.next != null){
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if(slowPointer == fastPointer){
                return true;
            }
        }
        return false;
    }

    public boolean hasCycleCheck(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();

        while (head != null) {
            if (visited.contains(head)) {
                return true; // cycle detected
            }
            visited.add(head);
            head = head.next;
        }

        return false; // no cycle
    }
}
