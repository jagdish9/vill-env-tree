package dp;

public class MergeSortedArray {

    public static void main(String[] args) {
        /*int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 3;*/
        /*int[] nums1 = {4, 5, 6, 0, 0, 0};
        int[] nums2 = {1, 2, 3};
        int m = 3;*/
        /*int[] nums1 = {1, 2, 4, 5, 6, 0};
        int[] nums2 = {3};
        int m = 5;*/
       /* int[] nums1 = {1};
        int[] nums2 = {};
        int m = 1;*/
       /* int[] nums1 = {0};
        int[] nums2 = {1};
        int m = 0;*/
        int[] nums1 = {-1,0,0,3,3,3,0,0,0};
        int[] nums2 = {1,2,2};
        int m = 6;
        merge3(nums1, m, nums2, nums2.length);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int copy = m;
        for(int t = 0; t < n; t++) {
            for(int g = 0; g < m+n; g++) {
                if(nums1[g] > nums2[t]) {
                    while(copy != g) {
                        nums1[copy] = nums1[copy-1];
                        copy--;
                    }
                    nums1[g] = nums2[t];
                    copy = m + g + 1;
                    break;
                }
                if(nums1[g] == 0 && g >= m && nums2[t] != 0) {
                    nums1[g] = nums2[t];
                    break;
                }
            }
        }

        System.out.println(nums1);
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int copy = m;
        for(int t = 0; t < n; t++) {
            for(int g = 0; g < m+n; g++) {
                if(nums1[g] > nums2[t]) {
                    nums1[copy++] = nums1[g];
                    nums1[g] = nums2[t];
                    break;
                }
                if(nums1[g] == 0 && nums2[t] != 0) {
                    nums1[g] = nums2[t];
                    break;
                }
            }
        }
    }

    public static void merge3(int[] nums1, int m, int[] nums2, int n) {
        while(m > 0 && n > 0){
            if(nums1[m-1] > nums2[n-1]){
                nums1[m+n-1] = nums1[m-1];
                m--;
            }else{
                nums1[m+n-1] = nums2[n-1];
                n--;
            }
        }
        while(n > 0){
            nums1[m+n-1] = nums2[n-1];
            n--;
        }

        System.out.println(nums1);
    }
}
