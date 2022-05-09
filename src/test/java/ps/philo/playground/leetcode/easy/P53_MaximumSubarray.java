package ps.philo.playground.leetcode.easy;

import edu.princeton.cs.test.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class P53_MaximumSubarray {

    public static void main(String... args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        Assert.assertEquals(maxSubArray(nums), 6);
    }

    //dp[0] = -2, dp[1] = 1; dp[2] = -2, dp[3] = 4, dp[4] = 3, dp[5] = 5, dp[6] = 6, dp[7] = [1], dp[8] = 5
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int sum = dp[0], max = dp[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
