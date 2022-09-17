package dp;

import java.util.ArrayList;
import java.util.List;

public class AddTwoNumbers {

    public static void main(String[] args) {
        /*ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);*/

        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode resultNode = addTwoNumbers.addTwoNumbers(l1, l2);
        System.out.println(resultNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1;
        ListNode node2 = l2;
        ListNode resultNode = null;
        int quotient = 0;
        while(node1 != null || node2 != null) {
            if(node1 != null && node2 != null) {
                int nodeData = node1.val + node2.val + quotient;
                int newNodeData = nodeData % 10;
                quotient = nodeData / 10;
                ListNode newNode = new ListNode(newNodeData);
                resultNode = addNode(resultNode, newNode);
            }
            else if(node1 != null && node2 == null) {
                int nodeData = node1.val + quotient;
                int newNodeData = nodeData % 10;
                quotient = nodeData / 10;
                ListNode newNode = new ListNode(newNodeData);
                resultNode = addNode(resultNode, newNode);
            }
            else if(node1 == null && node2 != null) {
                int nodeData = node2.val + quotient;
                int newNodeData = nodeData % 10;
                quotient = nodeData / 10;
                ListNode newNode = new ListNode(newNodeData);
                resultNode = addNode(resultNode, newNode);
            }
            if(node1 != null) node1 = node1.next;
            if(node2 != null) node2 = node2.next;
        }

        if(quotient != 0) {
            ListNode newNode = new ListNode(quotient);
            resultNode = addNode(resultNode, newNode);
        }

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        return resultNode;
    }

    public ListNode addNode(ListNode rootNode, ListNode node) {
        ListNode tmpNode = rootNode;
        if(tmpNode == null)
            return node;

        while(tmpNode.next != null) {
            tmpNode = tmpNode.next;
        }

        tmpNode.next = node;

        return rootNode;
    }
}
