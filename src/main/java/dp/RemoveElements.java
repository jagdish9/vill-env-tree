package dp;

public class RemoveElements {

    public static void main(String[] args) {
       /*ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);*/
      /* ListNode head = new ListNode(1);
        head.next = new ListNode(2);*/
        ListNode head = new ListNode(7);
        head.next = new ListNode(7);
        head.next.next = new ListNode(7);
        head.next.next.next = new ListNode(7);

        RemoveElements removeElements = new RemoveElements();
        ListNode result = removeElements.removeElements(head, 7);
        System.out.println(result);
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = head;

        if(dummy.val == val) head = dummy.next;

        if(dummy != null) while(dummy.next != null) {
            if(dummy.next.val == val)
                dummy.next = dummy.next.next;
            else
                dummy = dummy.next;
        }

        if(dummy != null && dummy.val == val) head = dummy.next;

        return head;
    }
}
