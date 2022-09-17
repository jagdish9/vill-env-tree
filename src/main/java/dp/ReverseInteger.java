package dp;

public class ReverseInteger {

    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();

      //  System.out.println(reverseInteger.reverseInt(1534236469));
        System.out.println(reverseInteger.reverseIntLong(460));
    }

    public int reverseInt(int num) {
        int reversed = 0;
        System.out.println("Original Number: " + num);
        // run loop until num becomes 0
        while(num != 0) {

            // get last digit from num
            int digit = num % 10;
            reversed = reversed * 10 + digit;

            // remove the last digit from num
            num = num / 10;
        }
        System.out.println("Reversed Number: " + reversed);

        return reversed;
    }

    public long reverseIntLong(int input) {
        System.out.println("Original Number: " + input);
        long reversedNum = 0;
        long input_long = input;

        while (input_long != 0) {
            reversedNum = reversedNum * 10 + input_long % 10;
            input_long = input_long / 10;
        }

        if (reversedNum > Integer.MAX_VALUE || reversedNum < Integer.MIN_VALUE) {
          //  throw new IllegalArgumentException();
        }
        return reversedNum;
    }
}
