package dp;

import java.util.HashMap;
import java.util.Map;

public class CanConstruct {

    private static Map<String, Boolean> memoizeTable = new HashMap<>();

    public static void main(String[] args) {
        CanConstruct canConstruct = new CanConstruct();

        System.out.println(canConstruct.canConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(canConstruct.canConstruct("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(canConstruct.canConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee", "eeeeee"}));
    }

    public boolean canConstruct(String target, String[] stArray) {
        if(memoizeTable.containsKey(target)) return memoizeTable.get(target);

        if(target.isEmpty()) return true;

        for(String eachSubstr : stArray) {
            if(target.indexOf(eachSubstr) == 0) {
                String suffix = target.substring(eachSubstr.length());
                if(canConstruct(suffix, stArray)) {
                    memoizeTable.put(suffix, true);
                    return true;
                }
            }
        }

        memoizeTable.put(target, false);
        return false;
    }
}
