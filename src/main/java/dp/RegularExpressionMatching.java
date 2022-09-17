package dp;

import java.util.HashMap;
import java.util.Map;

public class RegularExpressionMatching {

    public static void main(String[] args) {
        RegularExpressionMatching expressionMatching = new RegularExpressionMatching();
        String s = "abcdfeggdsaaa";
        String p = ".*";
        System.out.println(expressionMatching.isMatch(s, p));
    }

    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        Map<String, Boolean> memoizeTable = new HashMap<>();
        return dfs(s, p, i, j, memoizeTable);
    }

    private boolean dfs(String s, String p, int i, int j, Map<String, Boolean> memoizeTable) {
        if(memoizeTable.containsKey(i + "" + j)) return memoizeTable.get(i + "" + j);
        if(i >= s.length() && j >= p.length())
            return true;
        if(j >= p.length())
            return false;

        boolean match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if((j + 1) < p.length() && p.charAt(j + 1) == '*') {
            boolean result = (dfs(s, p, i, j + 2, memoizeTable) || match && dfs(s, p, i + 1, j, memoizeTable));
            memoizeTable.put(i + "" + j, result);
            return result;
        }
        if(match) {
            boolean result = dfs(s, p, i + 1, j + 1, memoizeTable);
            memoizeTable.put(i + "" + j, result);
            return result;
        }

        memoizeTable.put(i + "" +j, false);
        return false;
    }

}
