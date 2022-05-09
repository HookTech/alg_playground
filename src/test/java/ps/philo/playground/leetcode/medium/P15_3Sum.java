package ps.philo.playground.leetcode.medium;

import edu.princeton.cs.test.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * philo
 * # 1/24/19
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class P15_3Sum {
	@Test
	public void testV1() {
		List<List<Integer>> expected = new ArrayList<>();
		expected.add(Arrays.asList(-1, -1, 2));
		expected.add(Arrays.asList(-1, 0, 1));
		List<List<Integer>> actually = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
		for (int i = 0; i < actually.size(); i++) {
			Assert.assertEquals(
				TestUtil.assertTwoContainSameElements(
					actually.get(i),
					expected.get(i)
				),
				true
			);
		}
	}

	public List<List<Integer>> threeSum(int[] num) {
		Arrays.sort(num);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < num.length - 2; i++) {
			if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
				int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
				while (lo < hi) {
					if (num[lo] + num[hi] == sum) {
						res.add(Arrays.asList(num[i], num[lo], num[hi]));
						while (lo < hi && num[lo] == num[lo + 1]) lo++;
						while (lo < hi && num[hi] == num[hi - 1]) hi--;
						lo++;
						hi--;
					} else if (num[lo] + num[hi] < sum) lo++;
					else hi--;
				}
			}
		}
		return res;
	}
}
