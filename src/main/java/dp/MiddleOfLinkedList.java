package dp;

public class MiddleOfLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        MiddleOfLinkedList middleOfLinkedList = new MiddleOfLinkedList();
        ListNode resultNode = middleOfLinkedList.middleNode(head);
        System.out.println(resultNode);
    }

    public ListNode middleNode(ListNode head) {
        int lenOfNode = getLengthOfNode(head);
        int middleNode = 0;
        middleNode = (lenOfNode / 2) + 1;

        return getMiddleNode(head, middleNode);

    }

    public ListNode getMiddleNode(ListNode head, int middleNode) {
        ListNode dummy = head;

        while(middleNode-- > 1) {
            dummy = dummy.next;
        }

        /*if(dummy.next != null)
            dummy.next = dummy.next.next;*/

        return dummy;
    }

    public int getLengthOfNode(ListNode head) {
        ListNode dummy = head;
        int count = 0;

        while(dummy != null) {
            dummy = dummy.next;
            count++;
        }

        return count;
    }
}
