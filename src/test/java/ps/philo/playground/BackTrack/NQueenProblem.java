package ps.philo.playground.BackTrack;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * # 2/1/19
 */
public class NQueenProblem {

	@Test
	public void test(){
		Assert.assertEquals(num(8), 92);
	}

	public int num(int n) {
		if (n < 1) {
			return 0;
		}
		int[] record = new int[n];
		return process(0, record, n);
	}

	public int process(int i, int[] record, int n) {//backTracking
		if (i == n) {
			return 1;
		}
		int res = 0;
		for (int j = 0; j < n; j++) {
			if (isValid(record, i, j)) {
				record[i] = j;
				res += process(i + 1, record, n);
			}
		}
		return res;
	}

	public boolean isValid(int[] record, int i, int j) {
		for (int k = 0; k < i; k++) {
			if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
				return false;
			}
		}
		return true;
	}
}
