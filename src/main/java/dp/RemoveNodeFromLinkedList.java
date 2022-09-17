package dp;

public class RemoveNodeFromLinkedList {

    ListNode head;

    public static void main(String[] args) {
        RemoveNodeFromLinkedList removeNodeFromLinkedList = new RemoveNodeFromLinkedList();
        removeNodeFromLinkedList.addNode(7);
        removeNodeFromLinkedList.addNode(8);
        removeNodeFromLinkedList.addNode(9);
        removeNodeFromLinkedList.addNode(10);
        removeNodeFromLinkedList.addNode(11);

        removeNodeFromLinkedList.printList(removeNodeFromLinkedList.head);

        removeNodeFromLinkedList.removeNode(3);

        System.out.println();

        removeNodeFromLinkedList.printList(removeNodeFromLinkedList.head);
    }

    private void removeNode(int position) {
        if(head == null) return;

        ListNode tempNode = head;

        if(position == 0) {
            head = tempNode.next;
        }

        int c = 0;
        while(tempNode != null) {
            c++;
            if(position - 1 == c) {
                ListNode nextNode = tempNode.next;
                tempNode.next = nextNode.next;
                break;
            }
            tempNode = tempNode.next;
        }
    }

    private void printList(ListNode head) {
        ListNode tmpNode = head;
        while(tmpNode != null) {
            System.out.print(tmpNode.val + " ");
            tmpNode = tmpNode.next;
        }
    }

    public void addNode(int val) {
      if(head == null) {
          head = new ListNode(val);
      }
      else {
          ListNode newNode = new ListNode(val);
          ListNode tmp = head;
          while(tmp.next != null) {
              tmp = tmp.next;
          }
          tmp.next = newNode;
      }
    }
}
