package com;

import java.util.Arrays;
import java.util.List;

public class TestReplaceString {

    public static void main(String[] args) {
        List<String> ls = Arrays.asList("https://github.com/sourcegraph/java-artifacts - scandone",
                "https://github.com/apache/shardingsphere");
        for(String s : ls) {
            if (s.contains("scandone")) {
                s = s.replaceAll("- scandone", "");
            }
            System.out.println(s);
        }
    }
}
