package dp;

public class SquareRoot {

    public static void main(String[] args) {
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(8));
    }

    public static int mySqrt(int x) {
        int left = 1;
        int right = x;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(mid < x / mid){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return (left == x / left) ? left : left - 1;
    }
}
