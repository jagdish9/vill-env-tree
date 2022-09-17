package tree;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SplitArrayToSubArrays {

    public static void main(String args[]) {
        int[] inputArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        int chunkSize = 5;

        int[][] output = splitArray(inputArray, chunkSize);
        for (int[] x : output) {
            for(int i : x) {
                System.out.println(i);
            }
          //  System.out.println(Arrays.toString(x));
        }
    }

    public static int[][] splitArray(int[] inputArray, int chunkSize) {
        return IntStream.iterate(0, i -> i + chunkSize)
                .limit((int) Math.ceil((double) inputArray.length / chunkSize))
                .mapToObj(j -> Arrays.copyOfRange(inputArray, j, Math.min(inputArray.length, j + chunkSize)))
                .toArray(int[][]::new);
    }
}
