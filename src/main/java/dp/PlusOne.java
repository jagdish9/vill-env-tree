package dp;

public class PlusOne {

    public static int[] plusOne(int[] digits) {
        String result = "";
        for(int i = 0; i < digits.length; i++) {
            result += digits[i];
        }

        long intResult = Long.parseLong(result);
        intResult += 1;

        String midResult = String.valueOf(intResult);

        int[] output = new int[midResult.length()];
        for(int j = 0; j < output.length; j++) {
            output[j] = Integer.parseInt(String.valueOf(midResult.charAt(j)));
        }

        return output;
    }

    public static void main(String[] args) {
      //  int[] input = {9,8,7,6,5,4,3,2,1,0};
        int[] input = {7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6};
        int[] output = plusOne(input);
        System.out.println(output);
    }
}
