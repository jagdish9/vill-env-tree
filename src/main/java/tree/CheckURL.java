package tree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckURL {

    public static void main(String[] args) {
      //  String url = "https://pypi.org/pypi/cachecontrol[filecache]/0.12.10/json";
        String url = "https://pypi.org/pypi/BitTornado/T-0.3.4 (BitTornado)/json";
        System.out.println(containsIllegals(url));
    }

    public static boolean containsIllegals(String toExamine) {
        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|_^]");
        Matcher matcher = pattern.matcher(toExamine);
        return matcher.find();
    }
}
