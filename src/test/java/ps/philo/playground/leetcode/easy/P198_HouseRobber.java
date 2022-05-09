package ps.philo.playground.leetcode.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * # 4/6/19
 */
public class P198_HouseRobber {

	@Test
	public void test() {
		Assert.assertEquals(
			rob(new int[]{2, 7, 9, 3, 1}),
			12
		);
	}

	public int rob(int[] nums) {
		if (nums.length == 0) return 0;
		int prev1 = 0;
		int prev2 = 0;
		for (int num : nums) {
			int tmp = prev1;
			prev1 = Math.max(prev2 + num, prev1);
			prev2 = tmp;
		}
		return prev1;
	}
}
