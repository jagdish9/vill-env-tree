package dp;

public class RomanToInteger {

    private static final int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    private static final String[] romanLiterals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

    public static void main(String[] args) {
        System.out.println(integerToRoman2(20));
    }

    public static String integerToRoman2(int number) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                number = number - values[i];
                s.append(romanLiterals[i]);
            }
        }
        return s.toString();
    }
}
