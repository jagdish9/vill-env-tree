package dp;

public class NumArray {

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

        int[] a = {1, 2, '_'};
        System.out.println(a);

        int[] arr = {3, 2, 2, 3};
        System.out.println(removeElement(arr, 3));
    }

    public static int removeElement(int[] nums, int val) {
        int c = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[c++] = nums[i];
            }
        }
        return c;
    }
}
