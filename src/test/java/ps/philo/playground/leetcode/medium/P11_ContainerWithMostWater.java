package ps.philo.playground.leetcode.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 *
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 *
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 * philo
 * # 1/20/19
 */
public class P11_ContainerWithMostWater {

	@Test
	public void test1() {
		Assert.assertEquals(maxAreaV1(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}), 49);
		Assert.assertEquals(maxAreaV1(new int[]{1, 5, 6, 2, 5, 4, 8, 3, 7}), 36);
	}

	@Test
	public void test2() {
		Assert.assertEquals(maxAreaV2(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}), 49);
		Assert.assertEquals(maxAreaV2(new int[]{1, 5, 6, 2, 5, 4, 8, 3, 7}), 36);
	}

	public int maxAreaV1(int[] height) {//Brute Force
		int maxArea = 0;
		for (int i = 0; i < height.length - 1; i++) {
			for (int j = i + 1; j < height.length; j++) {
				int area = (j - i) * Math.min(height[i], height[j]);
				maxArea = area > maxArea ? area : maxArea;
			}
		}
		return maxArea;
	}

	public int maxAreaV2(int[] height) {//Two Pointer Approach
		int i = 0, j = height.length - 1;
		int maxArea = (j - i) * Math.min(height[i], height[j]);
		while (j - i > 1){
			if(height[i] > height[j]){
				j--;
			}
			else {
				i++;
			}
			int area = (j - i) * Math.min(height[i], height[j]);
			maxArea = area > maxArea ? area : maxArea;
		}
		return maxArea;
	}
}
