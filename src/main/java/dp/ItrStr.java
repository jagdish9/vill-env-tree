package dp;

public class ItrStr {

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll"));
       // System.out.println(strStr("a", "a"));
    }

    public static int strStr(String haystack, String needle) {
        if(needle.isEmpty()) return 0;
        for(int x = 0; x < haystack.length() - (needle.length()-1); x++) {
            if(needle.equalsIgnoreCase(haystack.substring(x, x+needle.length()))) {
                return x;
            }
        }
        return -1;
    }
}
