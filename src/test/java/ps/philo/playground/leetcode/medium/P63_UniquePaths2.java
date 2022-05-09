package ps.philo.playground.leetcode.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * # 2019/10/30
 */
public class P63_UniquePaths2 {

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[][] dp = new int[m][n];
		int i=0;
		int j=0;
		while(i<m){
			if(obstacleGrid[i][0] == 1) break;
			dp[i][0] = 1;
			i++;
		}
		for(int k = i; k<m;k++){
			dp[k][0] = 0;
		}
		while(j<n){
			if(obstacleGrid[0][j] == 1) break;
			dp[0][j] = 1;
			j++;
		}
		for(int k=j;k<n;k++){
			dp[0][k] = 0;
		}
		for (i = 1; i < m; i++) {
			for (j = 1; j < n; j++) {
				if(obstacleGrid[i][j] == 1){
					dp[i][j] = 0;
				}else{
					dp[i][j] =
						(obstacleGrid[i-1][j] == 1 ? 0 :dp[i - 1][j])
							+
							(obstacleGrid[i][j-1] == 1 ? 0 : dp[i][j - 1]);
				}

			}
		}
		return dp[m-1][n-1];

	}

	@Test
	public void test(){
		int[][] obstacleGrid = new int[][] {{0,1,0},{0,1,0},{0,0,0}};
		Assert.assertEquals(uniquePathsWithObstacles(obstacleGrid), 1);
	}
}
