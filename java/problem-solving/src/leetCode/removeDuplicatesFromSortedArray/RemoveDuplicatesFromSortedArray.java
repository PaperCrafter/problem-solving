package leetCode.removeDuplicatesFromSortedArray;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {
    public int solution(int[] nums) {
        int idx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                nums[idx + 1] = nums[i];
                idx++;
            }
        }
        return idx + 1;
    }
}
