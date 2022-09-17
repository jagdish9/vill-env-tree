package dp;

public class RemoveMiddleNode {

    public static void main(String[] args) {
        RemoveMiddleNode removeMiddleNode = new RemoveMiddleNode();

        ListNode head = new ListNode(1);
       /* head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);*/

        ListNode resultNode = removeMiddleNode.deleteMiddle(head);
        System.out.println(resultNode);
    }

    public ListNode deleteMiddle(ListNode head) {
        int lenOfList = getLengthOfList(head);
        int middleIndex = 0;
        /*if(lenOfList % 2 != 0)
            middleIndex = lenOfList / 2;
        else
            middleIndex = lenOfList / 2 + 1;*/
        middleIndex = lenOfList / 2;

        ListNode result = removeMiddleNode(head, middleIndex);

        return result;
    }

    public ListNode removeMiddleNode(ListNode head, int middleIndex) {
        ListNode dummy = head;
       if(middleIndex > 0) while(middleIndex-- > 1) {
            dummy = dummy.next;
        }

       if(dummy.next != null)
           dummy.next = dummy.next.next;
       else
           head = null;

        return head;
    }

    public int getLengthOfList(ListNode head) {
        ListNode dummy = head;
        int lenCount = 0;
        while(dummy != null) {
            dummy = dummy.next;
            lenCount++;
        }

        return lenCount;
    }
}
