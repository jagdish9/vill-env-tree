package dp;

public class DeleteNthNodeEndOfLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int n = 2;
        DeleteNthNodeEndOfLinkedList nthNodeEndOfLinkedList = new DeleteNthNodeEndOfLinkedList();
        ListNode resultNode = nthNodeEndOfLinkedList.deleteNthNode(head, n);
        System.out.print(resultNode);
    }

    private ListNode deleteNthNode(ListNode head, int n) {
        ListNode start = new ListNode();
        start.next = head;
        ListNode fast = start;
        ListNode slow = start;

        for(int i = 1; i <= n; ++i)
            fast = fast.next;

        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return start.next;
    }


}
