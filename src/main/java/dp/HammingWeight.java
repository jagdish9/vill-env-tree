package dp;

public class HammingWeight {

    public static void main(String[] args) {
        HammingWeight hammingWeight = new HammingWeight();
        System.out.println(hammingWeight.hammingWeight(1111111101));
        System.out.println(hammingWeight.hammingWeight2(1111111101));
    }

    public int hammingWeight(int n) {
        String s = String.valueOf(n);
        s = s.replace("0", "");
        int count = 0;
        for(int k = 0; k < s.length(); k++) {
            if(String.valueOf(s.charAt(k)).equalsIgnoreCase("1")) {
                count += 1;
            }
        }
        return count;
    }

    public int hammingWeight2(int n) {
        return Integer.bitCount(n);
    }
}
