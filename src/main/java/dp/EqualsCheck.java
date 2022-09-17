package dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class EqualsCheck {

    public static boolean isEqual(List<Integer> x, List<Integer> y) {
        if (x == null) {
            return y == null;
        }
       // return x.equals(y);
        return Objects.equals(x, y);
    }

    public static boolean listEqualsIgnoreOrder(List<Integer> list1, List<Integer> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    public static void main(String[] args) {
        List<Integer> x = new ArrayList<>();
        x.add(1);
        x.add(2);
        x.add(3);
        x.add(5);
        x.add(4);
        List<Integer> y = new ArrayList<>();
        y.add(1);
        y.add(2);
        y.add(3);
        y.add(4);
        y.add(5);

        boolean isEqual = isEqual(x, y);
        if (isEqual) {
            System.out.println("Both lists are equal");
        }
        else {
            System.out.println("Both lists are not equal");
        }

        System.out.println(listEqualsIgnoreOrder(x, y));
    }
}
