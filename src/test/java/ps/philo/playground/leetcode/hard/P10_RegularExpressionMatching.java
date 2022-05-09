package ps.philo.playground.leetcode.hard;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * # 1/16/19
 */
public class P10_RegularExpressionMatching {
	@Test
	public void test1() {
		Assert.assertEquals(isMatchV1("aa", "a"), false);
		Assert.assertEquals(isMatchV1("aa", "a*"), true);
		Assert.assertEquals(isMatchV1("ab", ".*"), true);
		Assert.assertEquals(isMatchV1("aab", "c*a*b*"), true);
		Assert.assertEquals(isMatchV1("mississippi", "mis*is*p*."), false);
	}

	@Test
	public void test2() {
		Assert.assertEquals(isMatchV2("aa", "a"), false);
		Assert.assertEquals(isMatchV2("aa", "a*"), true);
		Assert.assertEquals(isMatchV2("ab", ".*"), true);
		Assert.assertEquals(isMatchV2("aab", "c*a*b*"), true);
		Assert.assertEquals(isMatchV2("mississippi", "mis*is*p*."), false);
		Assert.assertEquals(isMatchV2("ab", ".*c"), false);
	}

	public boolean isMatchV1(String text, String pattern) {//Recursion approach
		if (pattern.isEmpty()) return text.isEmpty();
		boolean first_match = (!text.isEmpty() &&
			(pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

		if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
			return (isMatchV1(text, pattern.substring(2)) ||
				(first_match && isMatchV1(text.substring(1), pattern)));
		} else {
			return first_match && isMatchV1(text.substring(1), pattern.substring(1));
		}
	}

	public boolean isMatchV2(String text, String pattern) {//Dynamic Programming approach
		Boolean[][] memo = new Boolean[text.length() + 1][pattern.length() + 1];
		return dp(0, 0, text, pattern, memo);
	}

	public boolean dp(int i, int j, String text, String pattern, Boolean[][] memo) {
		if (memo[i][j] != null) {
			return memo[i][j];
		}
		boolean ans;
		if (j == pattern.length()) {
			ans = i == text.length();
		} else {
			boolean first_match = (i < text.length() && (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.'));
			if (j < pattern.length() - 1 && pattern.charAt(j + 1) == '*') {
				ans = dp(i, j + 2, text, pattern, memo)
					|| (first_match && dp(i + 1, j, text, pattern, memo));
			} else {
				ans = (first_match && dp(i + 1, j + 1, text, pattern, memo));
			}
		}
		memo[i][j] = ans;
		return ans;
	}

}
