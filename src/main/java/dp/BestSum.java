package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestSum {

    private static Map<Integer, List<Integer>> memoizeTable = new HashMap<>();

    public static void main(String[] args) {
        BestSum bestSum = new BestSum();
        List<Integer> shortestArray = new ArrayList<>();
        List<Integer> number = bestSum.bestSum(7, new int[]{2, 3});
        System.out.println(number);
        memoizeTable.clear();

        number = bestSum.bestSum(7, new int[]{5, 3, 4, 7});
        System.out.println(number);
        memoizeTable.clear();

        number = bestSum.bestSum(7, new int[]{5, 3, 4, 7});
        System.out.println(number);
        memoizeTable.clear();

        number = bestSum.bestSum(7, new int[]{2, 4});
        System.out.println(number);
        memoizeTable.clear();

        number = bestSum.bestSum(8, new int[]{2, 3, 5});
        System.out.println(number);
        memoizeTable.clear();

        number = bestSum.bestSum(300, new int[]{7, 14});
        System.out.println(number);
        memoizeTable.clear();

        number = bestSum.bestSum(100, new int[]{1, 2, 5, 25});
        System.out.println(number);
        memoizeTable.clear();
    }

    private List<Integer> bestSum(int targetSum, int[] numbers) {
        if(memoizeTable.containsKey(targetSum)) return memoizeTable.get(targetSum);
        if(targetSum == 0) return new ArrayList<>();
        if(targetSum < 0) return null;

        List<Integer> shortestArray = null;

        for(int num : numbers) {
            int remainder = targetSum - num;
            List<Integer> resultSum = bestSum(remainder, numbers);
            if(resultSum != null) {
                resultSum.add(num);
                if(shortestArray == null || resultSum.size() < shortestArray.size())
                    shortestArray = resultSum;
            }
        }

        memoizeTable.put(targetSum, shortestArray);
        return shortestArray;
    }
}
