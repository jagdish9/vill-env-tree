package dp;

public class LongestPalindromeSubstring {

    public static void main(String[] args) {
       // String s = "mojologiccigolmojo";
        String s = "babad";
        LongestPalindromeSubstring palindromeSubstring = new LongestPalindromeSubstring();
        System.out.println(palindromeSubstring.findLongestPalindromicSubstring(s));
    }

    private String longestPalindrome(String s) {
        int max = 1;
        int start = 0;

        for(int i = 0; i < s.length(); i++) {
            for(int j = i; j < s.length(); j++) {
                boolean flag = true;

                for(int k = 0; k < (j - i + 1) / 2; k++) {
                    if(s.charAt(i+k) != s.charAt(j-k)) {
                        flag = false;
                    }
                }

                if(flag && (j - i + 1) > max) {
                    start = i;
                    max = j - i + 1;
                }
            }
        }
    return s.substring(start, start + max);
    }

    public String expand(String str, int low, int high) {
        while (low >= 0 && high < str.length() &&
                (str.charAt(low) == str.charAt(high))) {
            low--;
            high++;
        }
        return str.substring(low + 1, high);
    }
    
    public String findLongestPalindromicSubstring(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        
        String maxStr = "";
        String currStr;
        
        int maxLength = 0;
        int currLength;
        
        for (int i = 0; i < str.length(); i++) {
            currStr = expand(str, i, i);
            currLength = currStr.length();

            if (currLength > maxLength) {
                maxLength = currLength;
                maxStr = currStr;
            }

            currStr = expand(str, i, i + 1);
            currLength = currStr.length();

            if (currLength > maxLength) {
                maxLength = currLength;
                maxStr = currStr;
            }
        }
        return maxStr;
    }
}
