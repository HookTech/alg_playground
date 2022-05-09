package ps.philo.playground.leetcode.easy;

import edu.princeton.cs.test.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 * Example 4:
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 * # 3/22/19
 */
public class P35_SearchInsertPosition {
	@Test
	public void test(){
		Assert.assertEquals(
			searchInsert(new int[]{1,3,5,6},7),
			4
		);

		Assert.assertEquals(
			searchInsert(new int[]{1,3,5,6},2),
			1
		);

		Assert.assertEquals(
			searchInsert(new int[]{1,3,5,6},5),
			2
		);
	}

	public int searchInsert(int[] nums, int target) {
		return search(nums,0,nums.length - 1, target);
	}

	public int search(int[] nums, int lo, int hi, int target){
		if(lo >= hi){
			if(nums[lo] < target){
				return lo + 1;
			}else {
				return lo;
			}
		}
		int midIndex = (lo + hi)/2;
		int mid = nums[midIndex];
		if(mid < target){
			return search(nums,midIndex + 1, hi,target);
		}else if(mid > target){
			return search(nums,lo, midIndex - 1,target);
		}else{
			return midIndex;
		}
	}
}
