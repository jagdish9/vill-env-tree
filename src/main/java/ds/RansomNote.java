package ds;

public class RansomNote {

    public static void main(String[] args) {
        RansomNote ransomNote = new RansomNote();
        System.out.println(ransomNote.canConstruct("ihgg", "ch"));
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;

        int[] n = setArray(ransomNote);
        int[] m = setArray(magazine);

        for (int i = 0; i < n.length; i++) {
            if (n[i] != 0) {
                if (n[i] > m[i]) return false;
            }
        }

        return true;
    }

    private int[] setArray(String string) {
        int[] countArray = new int[26];
        for (char c : string.toCharArray()) {
            int index = c - 'a';
            countArray[index] += 1;
        }
        return countArray;
    }
}
