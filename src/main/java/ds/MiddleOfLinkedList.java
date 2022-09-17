package ds;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class MiddleOfLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        traverseList(head);
        ListNode middleNode = middleList(head);
        System.out.println();
        traverseList(middleNode);
    }

    private static ListNode middleList(ListNode head) {
        ListNode slowNode = head;
        ListNode fastNode = head;
        while(fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        return slowNode;
    }

    private static void traverseList(ListNode head) {
        ListNode temp = head;
        while(temp != null) {
            System.out.print(temp.val+" ");
            temp = temp.next;
        }
    }
}
