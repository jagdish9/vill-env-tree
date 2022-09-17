package dp;

import java.util.HashMap;
import java.util.Map;

public class GridTraveler {
    private Map<String, Long> memoizeTable = new HashMap<>();

    public static void main(String[] args) {
        GridTraveler gridTraveler = new GridTraveler();
        System.out.println(gridTraveler.gridTravel(1,1));
        System.out.println(gridTraveler.gridTravel(2,3));
        System.out.println(gridTraveler.gridTravel(3,2));
        System.out.println(gridTraveler.gridTravel(3,3));
        System.out.println(gridTraveler.gridTravel(18,18));
    }

    public long gridTravel(long down, long right) {
        String key = down + "," + right;
        if(memoizeTable.containsKey(key)) return memoizeTable.get(key);
        if(down == 1 && right == 1) return 1;
        if(down == 0 || right == 0) return 0;
        long result = gridTravel(down-1, right) + gridTravel(down, right-1);
        memoizeTable.put(key, result);
        return result;
    }
}
