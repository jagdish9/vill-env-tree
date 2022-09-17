package dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement2 {

    public static void main(String[] args) {
        NextGreaterElement2 nextGreaterElement2 = new NextGreaterElement2();
        //int[] nums = {1, 2, 1};
        int[] nums = {100,1,11,1,120,111,123,1,-1,-100};

        System.out.println(nextGreaterElement2.nextGreaterElements2(nums));
    }

    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[nums.length];

        int[] nums2 = new int[nums.length * 2];
        int x = 0;
        for(int j = 0; j < nums2.length; j++) {
            nums2[j] = nums[x];
            x++;
            if(x == nums.length) x = 0;
        }

        for(int i : nums2) {
            while(!stack.isEmpty()) {
                if(stack.peek() < i) {
                    if(!map.containsKey(stack.peek()))
                        map.put(stack.peek(), i);
                    stack.pop();
                }
                else {
                    break;
                }
            }

            stack.push(i);
        }

        for(int y = 0; y < nums.length; y++) {
            if(map.containsKey(nums[y])) result[y] = map.get(nums[y]);
            else result[y] = -1;
        }

        return result;
    }

    public int[] nextGreaterElements2(int[] nums) {
        int maxEleIdx = 0;
        for(int i = 1; i< nums.length; i++) {
            if(nums[i] >= nums[maxEleIdx]) {
                maxEleIdx = i;
            }
        }

        int ans[] = new int[nums.length];

        int idx = maxEleIdx;
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < nums.length; i++) {
            while(!st.isEmpty() && st.peek() <= nums[idx])
                st.pop();

            if(st.isEmpty()) {
                ans[idx] = -1;
            }
            else {
                ans[idx] = st.peek();
            }

            st.push(nums[idx]);

            idx--;

            if(idx < 0)
                idx = nums.length-1;
        }

        return ans;
    }
}
