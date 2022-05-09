package ps.philo.playground.leetcode.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * philo
 * # 1/12/19
 */
public class P7_ReverseInteger {
	@Test
	public void test() {
		Assert.assertEquals(reverse(123), 321);
		Assert.assertEquals(reverse(-123), -321);
		Assert.assertEquals(reverse(120), 21);
		Assert.assertEquals(reverse(10), 1);
		Assert.assertEquals(reverse(1534236469), 0);
		Assert.assertEquals(reverse(-2147483648), 0);
	}

	public int reverse(int x) {
		Queue<Integer> queue = new ArrayDeque<>();
		Integer ref = (x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) ? 0 : x;
		while (Math.abs(ref) >= 10) {
			Integer lastNum = ref % 10;
			queue.add(lastNum);
			ref = ref / 10;
		}
		queue.add(ref);
		Integer l = queue.size();
		Integer result = 0;
		while (!queue.isEmpty()) {
			Integer num = queue.poll();
			if ((l > 9)
				&&
				(is10PlugOverflow(result) || Math.abs(result * 10 + num) < Math.abs(result * 10))
			) {
				return 0;
			}
			result = result * 10 + num;
		}
		return result;
	}

	private boolean is10PlugOverflow(Integer v) {
		for (int i = 1; i < 10; i++) {
			if (Math.abs(v * (i + 1)) < Math.abs(v * i)) {
				return true;
			}
		}
		return false;
	}
}
