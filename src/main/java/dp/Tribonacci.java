package dp;

import java.util.HashMap;
import java.util.Map;

public class Tribonacci {

    public static void main(String[] args) {
        Tribonacci tribonacci = new Tribonacci();
        System.out.println(tribonacci.tribonacci(25));
    }

    public int tribonacci(int n) {
        Map<Integer, Integer> memoizeTable = new HashMap<Integer, Integer>();

        return tNum(n, memoizeTable);

    }

    public int tNum(int n, Map<Integer, Integer> memoizeTable) {
        if (memoizeTable.containsKey(n)) return memoizeTable.get(n);
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return 1;

        n = n - 3;
        int result = tNum(n, memoizeTable) + tNum(n + 1, memoizeTable) + tNum(n + 2, memoizeTable);
        if(memoizeTable.get(n) != null) memoizeTable.put(n, result);
        return result;
    }
}
