package ps.philo.playground.leetcode.hard;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * # 1/5/19
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class P4_MedianTwoSortedArraysTest {
	@Test
	public void test() {
		int[] nums1 = new int[]{1, 2, 3, 5};
		int[] nums2 = new int[]{2, 4, 6};
		Assert.assertEquals(findMedianSortedArrays(nums2, nums1), 3D);
		nums1 = new int[]{1, 2, 3, 5};
		nums2 = new int[]{2, 4, 6, 7};
		Assert.assertEquals(findMedianSortedArrays(nums1, nums2), 3.5D);
		nums1 = new int[]{1, 3};
		nums2 = new int[]{2};
		Assert.assertEquals(findMedianSortedArrays(nums1, nums2), 2D);
		nums1 = new int[]{1, 2};
		nums2 = new int[]{3, 4};
		Assert.assertEquals(findMedianSortedArrays(nums1, nums2), 2.5D);
	}

	private double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length) {
			int[] tmp = nums1;
			nums1 = nums2;
			nums2 = tmp;

		}
		int n = nums1.length, m = nums2.length;
		//i+j = (n+m+1)/2
		//if n+m odd case median is max(leftside)
		//if n+m even case median is avg(max(leftside),min(rightside))
		//binary search on i
		int medianTotal = (n + m + 1) / 2;
		int sp = 0, ep = n;
		int i, j, nums1leftMax = 0, nums2leftMax = 0, nums1rightMin = 0, nums2rightMin = 0;
		while (sp <= ep) {
			i = (sp + ep) / 2;
			j = medianTotal - i;
			nums1leftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
			nums2leftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
			nums1rightMin = i == n ? Integer.MAX_VALUE : nums1[i];
			nums2rightMin = j == m ? Integer.MAX_VALUE : nums2[j];
			if (nums1leftMax > nums2rightMin) {
				ep = i;
			} else if (nums2leftMax > nums1rightMin) {
				sp = i + 1;
			} else {
				break;
			}
		}
		return (n + m) % 2 == 0 ?
			(Math.max(nums1leftMax, nums2leftMax) + Math.min(nums1rightMin, nums2rightMin)) / 2.0D
			:
			Math.max(nums1leftMax, nums2leftMax)
			;
	}
}
