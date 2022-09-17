package dp;

import java.util.*;

public class NextGreaterElement {

    public static void main(String[] args) {
        NextGreaterElement nextGreaterElement = new NextGreaterElement();
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
       /* int[] nums1 = {1,3,5,2,4};
        int[] nums2 = {6,5,4,3,2,1,7};*/

       // System.out.println(nextGreaterElement.nextGreaterElement(nums1, nums2));
        System.out.println(nextGreaterElement.nextGreaterElement2(nums1, nums2));
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < nums1.length; i++) {
            for(int j = 0; j < nums2.length; j++) {
                if(nums1[i] == nums2[j]) {
                    if(j == nums2.length-1)
                        queue.add(-1);
                    else if(nums1[i] < nums2[j+1]) {
                        queue.add(nums2[j+1]);
                    }
                    else if(nums1[i] > nums2[j+1]) {
                        int greaterElement = nums2[j + 1];
                        for (int m = j + 1 + 1; m < nums2.length; m++) {
                            if (greaterElement < nums2[m]) {
                                greaterElement = nums2[m];
                            }
                        }
                        if (nums1[i] < greaterElement)
                            queue.add(greaterElement);
                        else
                            queue.add(-1);
                    }
                    else
                        queue.add(-1);
                }
            }
        }

        int[] result = new int[queue.size()];
        int l = 0;
        for(Integer element : queue) {
            result[l] = element;
            l++;
        }

        return result;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();
        int[] ans = new int[nums1.length];
        for(int i : nums2){
            while(!stack.isEmpty()){
                if(stack.peek() < i){
                    map.put(stack.peek(), i);
                    stack.pop();
                }
                else break;
            }
            stack.push(i);
        }
        for(int i = 0; i < nums1.length; i++){
            if(map.containsKey(nums1[i]))
                ans[i] = map.get(nums1[i]);
            else ans[i] = -1;
        }

        return ans;
    }
}
