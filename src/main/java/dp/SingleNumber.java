package dp;

public class SingleNumber {

    public static void main(String[] args) {
      //  int[] nums = {2, 2, 1};
      //  int[] nums = {4,1,2,1,2};
        int[] nums = {1};
        SingleNumber singleNumber = new SingleNumber();
        System.out.println(singleNumber.singleNumber(nums));
    }

    public int singleNumber(int[] nums) {
        int singleNumber = 0;
        for(int i = 0; i < nums.length; i++) {
            boolean notFoundSingleNumber = true;
            for(int j = i+1; j < nums.length; j++) {
                if(nums[i] == nums[j]) {
                    notFoundSingleNumber = false;
                    break;
                }
            }
            for(int k = i-1; k >= 0; k--) {
                if(nums[i] == nums[k]) {
                    notFoundSingleNumber = false;
                    break;
                }
            }
            if(notFoundSingleNumber) {
                singleNumber = nums[i];
                break;
            }
        }
        return singleNumber;
    }
}
