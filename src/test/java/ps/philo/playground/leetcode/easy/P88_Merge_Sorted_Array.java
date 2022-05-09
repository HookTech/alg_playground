package ps.philo.playground.leetcode.easy;

import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * <p>
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.
 * Example:
 * <p>
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * Output: [1,2,2,3,5,6]
 */
public class P88_Merge_Sorted_Array {

    public static void main(String... args) {
        int[] n1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] n2 = new int[]{2, 5, 6};
        merge(n1, 3, n2, 3);
        TestUtil.assertEqualArray(n1, new int[]{1, 2, 2, 3, 5, 6});
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        int end = m;
        while (i < m + n && j < n) {
            if (i >= end) {
                nums1[i++] = nums2[j++];
                continue;
            }
            if (nums1[i] <= nums2[j]) {
                i++;
            } else {
                forward(nums1, i, end++);
                nums1[i++] = nums2[j++];
            }
        }
    }

    private static void forward(int[] nums, int k, int end) {
        for (int i = end; i > k; i--) {
            nums[i] = nums[i - 1];
        }
    }
}
