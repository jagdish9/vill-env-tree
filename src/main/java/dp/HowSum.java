package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HowSum {

    private static Map<Integer, List<Integer>> memoizeTable = new HashMap<>();

    public static void main(String[] args) {
        HowSum howSumSum = new HowSum();
        List<Integer> number = howSumSum.howSum(7, new int[]{2, 3});
        System.out.println(number);
        memoizeTable.clear();

        number = howSumSum.howSum(7, new int[]{5, 3, 4, 7});
        System.out.println(number);
        memoizeTable.clear();

        number = howSumSum.howSum(7, new int[]{2, 4});
        System.out.println(number);
        memoizeTable.clear();

        number = howSumSum.howSum(8, new int[]{2, 3, 5});
        System.out.println(number);
        memoizeTable.clear();

        number = howSumSum.howSum(300, new int[]{7, 14});
        System.out.println(number);
        memoizeTable.clear();
    }

    public List<Integer> howSum(int targetSum, int[] numbers) {
        if(memoizeTable.containsKey(targetSum)) return memoizeTable.get(targetSum);

        if(targetSum == 0) return new ArrayList<>();
        if(targetSum < 0) return null;

        for(int num : numbers) {
            int remainder = targetSum - num;
            List<Integer> resultArray = howSum(remainder, numbers);
            if(resultArray != null) {
                resultArray.add(num);
                memoizeTable.put(targetSum, resultArray);
                return resultArray;
            }
        }

        memoizeTable.put(targetSum, null);
        return null;
    }
}
