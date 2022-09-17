package ds;

import java.util.Arrays;

public class KWeakestRows {

    private int binarySearch(int[] nums){
        int low = 0;
        int high = nums.length - 1;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(nums[mid] == 0) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] temp1 = new int[mat.length];
        int[] temp2 = new int[mat.length];
        int i = 0;
        for(int[] arr:mat){
            int t = binarySearch(arr);
            temp1[i] = t;
            temp2[i] = t;
            i++;
        }
        Arrays.sort(temp1);
        int[] result = new int[k];
        for(int ii = 0; ii < k ; ii++){
            for(int j = 0; j < temp1.length; j++){
                if(temp2[j] == temp1[ii]){
                    temp2[j] = -1;//To avoid repetative strenth issue
                    result[ii] = j;
                    break;
                }
            }
        }
        return result;
    }
}
