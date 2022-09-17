package dp;

import java.util.HashMap;
import java.util.Map;

public class FibonacciLong {
    public static void main(String[] args) {
        Map<Long, Long> memoizeTable = new HashMap<>();
        System.out.println(fib(5, memoizeTable));
        System.out.println(fib(7, memoizeTable));
        System.out.println(fib(9, memoizeTable));
        System.out.println(fib(50, memoizeTable));
        System.out.println(fib(75, memoizeTable));
    }

    private static long fib(long number, Map<Long, Long> memoizeTable) {
        if(number <= 2) return 1;
        if(memoizeTable.containsKey(number)) return memoizeTable.get(number);
        long result = fib(number - 1, memoizeTable) + fib(number - 2, memoizeTable);
        memoizeTable.put(number, result);
        return result;
    }
}
