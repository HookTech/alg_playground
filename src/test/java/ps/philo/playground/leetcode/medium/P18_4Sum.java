package ps.philo.playground.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * philo
 * # 1/26/19
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class P18_4Sum {

	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < nums.length - 3; i++) {
			if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
				for (int j = i + 1; j < nums.length - 2; j++) {
					if (j == i + 1 || j > i + 1 && nums[j] != nums[j - 1]) {
						int lo = j + 1, hi = nums.length - 1, sum = target - nums[i] - nums[j];
						while (lo < hi) {
							if (nums[lo] + nums[hi] == sum) {
								res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
								while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
								while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
								lo++;
								hi--;
							} else if (nums[lo] + nums[hi] < sum) {
								lo++;
							} else {
								hi--;
							}
						}
					}
				}
			}
		}
		return res;
	}


}
