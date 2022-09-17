package dp;

import java.util.TreeMap;

public class IntegerToRoman {

    private static final TreeMap<Integer, String> treeMap = new TreeMap<>();
    static {
        treeMap.put(1000, "M");
        treeMap.put(900, "CM");
        treeMap.put(500, "D");
        treeMap.put(400, "CD");
        treeMap.put(100, "C");
        treeMap.put(90, "XC");
        treeMap.put(50, "L");
        treeMap.put(40, "XL");
        treeMap.put(10, "X");
        treeMap.put(9, "IX");
        treeMap.put(5, "V");
        treeMap.put(4, "IV");
        treeMap.put(1, "I");
    }

    public static String integerToRoman(int number) {
        int l = treeMap.floorKey(number);
        if (number == l) {
            return treeMap.get(number);
        }
        return treeMap.get(l) + integerToRoman(number - l);
    }

    public static void main(String[] args) {
        System.out.println(integerToRoman(20));
    }
}
