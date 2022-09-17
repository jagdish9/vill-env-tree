package dp;

import java.util.Map;
import java.util.TreeMap;

public class RomanToInt {

    private static final TreeMap<Character, Integer> treeMap = new TreeMap<>();
    static {
        treeMap.put('I', 1);
        treeMap.put('V', 5);
        treeMap.put('X', 10);
        treeMap.put('L', 50);
        treeMap.put('C', 100);
        treeMap.put('D', 500);
        treeMap.put('M', 100);
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("IV"));
    }

    private static int romanToInt(String roman) {
        int result = 0;
        for(int i = 0; i < roman.length(); i++) {
            char ch = roman.charAt(i);
            if(i > 0 && treeMap.get(ch) > treeMap.get(roman.charAt(i-1))) {
                result = result + treeMap.get(ch) - 2 * treeMap.get(roman.charAt(i-1));
            }
            else {
                result = result + treeMap.get(ch);
            }
        }
        return result;
    }

    //correct and optimized code
    public int romanToInt2(String s) {
        Map<Character, Integer> alphabet = initAlphabet();
        int result = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (alphabet.get(s.charAt(i)) < alphabet.get(s.charAt(i + 1)))
                result = result - alphabet.get(s.charAt(i));
            else
                result = result + alphabet.get(s.charAt(i));
        }
        return result + alphabet.get(s.charAt(s.length() - 1));
    }

    private Map<Character, Integer> initAlphabet() {
        return treeMap;
    }
}
