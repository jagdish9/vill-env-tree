package dp;

import java.util.HashMap;
import java.util.Map;

public class FibonacciNumber {

    public static void main(String[] args) {
        Map<Integer, Integer> memoizeTable = new HashMap<>();
        System.out.println(fib(5, memoizeTable));
        System.out.println(fib(7, memoizeTable));
        System.out.println(fib(9, memoizeTable));
        System.out.println(fib(50, memoizeTable));
    }

    private static int fib(int number, Map<Integer, Integer> memoizeTable) {
        if(number <= 2) return 1;
        if(memoizeTable.containsKey(number)) return memoizeTable.get(number);
        int result = fib(number - 1, memoizeTable) + fib(number - 2, memoizeTable);
        memoizeTable.put(number, result);
        return result;
    }

}
