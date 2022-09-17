package dp;

import java.util.ArrayDeque;
import java.util.Deque;

public class SingleLinkedListPalindrome {

    public static void main(String[] args) {
        SingleLinkedListPalindrome listPalindrome = new SingleLinkedListPalindrome();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);

        System.out.println(listPalindrome.isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {
        ListNode curr = head;
        Deque<ListNode> stack = new ArrayDeque<>();

        while(curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        ListNode nextCurr = head;
        boolean foundPalindrome = true;

        while(nextCurr != null) {
            ListNode poppedElement = stack.pop();
            if(poppedElement.val != nextCurr.val) {
                foundPalindrome = false;
                break;
            }
            nextCurr = nextCurr.next;
        }

        return foundPalindrome;
    }
}
