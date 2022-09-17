package dp;

import java.util.HashMap;
import java.util.Map;

public class CanSum {

    private static Map<Integer, Boolean> memoizeTable = new HashMap<>();

    public static void main(String[] args) {
        CanSum canSum = new CanSum();
        boolean checkValue = canSum.checkCanSum(7, new int[]{2, 3});
        System.out.println(checkValue);
        memoizeTable.clear();

        checkValue = canSum.checkCanSum(7, new int[]{5, 3, 4, 7});
        System.out.println(checkValue);
        memoizeTable.clear();

        checkValue = canSum.checkCanSum(7, new int[]{2, 4});
        System.out.println(checkValue);
        memoizeTable.clear();

        checkValue = canSum.checkCanSum(8, new int[]{2, 3, 5});
        System.out.println(checkValue);
        memoizeTable.clear();

        checkValue = canSum.checkCanSum(300, new int[]{7, 14});
        System.out.println(checkValue);
        memoizeTable.clear();
    }

    public boolean checkCanSum(int targetValue, int[] valueArray) {
        if(memoizeTable.containsKey(targetValue)) return memoizeTable.get(targetValue);

        if(targetValue == 0) return true;
        if(targetValue < 0) return false;

        for(int num : valueArray) {
            int remainder = targetValue - num;
            if(checkCanSum(remainder, valueArray)) {
                memoizeTable.put(targetValue, true);
                return true;
            }
        }

        memoizeTable.put(targetValue, false);
        return false;
    }
}
