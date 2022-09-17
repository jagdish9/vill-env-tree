package dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Number3Sum {

    public static void main(String[] args) {
        Number3Sum number3Sum = new Number3Sum();
      //  int[] nums = {-1,0,1,2,-1,-4};
        int[] nums = {-1,0,1,2,-1,-4,-2,-3,3,0,4};
     //   int[] nums = {0,0,0,0};
        List<List<Integer>> result = number3Sum.threeSum(nums);
        System.out.println(result);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> tempResult = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                List<Integer> midResult = new ArrayList<>();
                for(int k = j + 1; k < nums.length; k++) {
                    if(nums[i] + nums[j] + nums[k] == 0) {
                        midResult.add(nums[i]);
                        midResult.add(nums[j]);
                        midResult.add(nums[k]);
                        break;
                    }
                }
               /* if(!midResult.isEmpty()) {
                    if(!result.isEmpty()) {
                        for (List<Integer> duplicate : tempResult) {
                            if (!listEqualsIgnoreOrder(duplicate, midResult)) {
                                result.add(midResult);
                            }
                        }
                    }
                    else {
                        tempResult.add(midResult);
                        result.add(midResult);
                    }
                }*/
                if(!midResult.isEmpty()) result.add(midResult);
            }
        }

        /*for(int i = 0; i < result.size(); i++) {
            boolean isDuplicate = false;
            for(int j = 0; j < result.size(); j++) {
                if (!listEqualsIgnoreOrder(result.get(i), result.get(j))) {
                    isDuplicate = true;
                    break;
                }
            }
            if(!isDuplicate) tempResult.add(result.get(i));
        }*/
        return result;
    }

    public boolean listEqualsIgnoreOrder(List<Integer> list1, List<Integer> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }
}
