package dp;

public class InsertPosition {

    public static void main(String[] args) {

    }

    public int searchInsert(int[] nums, int target) {
        for(int z = 0; z < nums.length; z++) {
            if(nums[z] == target) {
                return z;
            }
            else if(nums[z] < target && nums[z+1] > target) {
                return z+1;
            }
        }
        return nums.length;
    }
}
