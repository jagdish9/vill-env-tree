package dp;

public class RNthNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
       /* head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);*/

        RNthNode rNthNode = new RNthNode();
        ListNode result = rNthNode.removeNthFromEnd(head, 2);
        System.out.println(result);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = getLengthOfListNode(head);
        ListNode temp = head;

        int traverseCount = 0;
        while(temp != null) {
            traverseCount++;

            if(length - traverseCount == n) {
                ListNode nextNode = temp.next;
                if(nextNode != null) {
                    temp.next = nextNode.next;
                }
                else {
                    temp.next = null;
                }
                break;
            }
            temp = temp.next;
        }

        if(traverseCount == 1 && n == 1) head = null;

        return head;
    }

    public int getLengthOfListNode(ListNode head) {
        ListNode temp = head;
        int c = 0;
        while(temp != null) {
            temp = temp.next;
            c++;
        }

        return c;
    }
}
