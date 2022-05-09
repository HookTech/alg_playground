package ps.philo.playground.leetcode.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * <p>
 * Example 1:
 * <p>
 * Input: "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: "(]"
 * Output: false
 * Example 4:
 * <p>
 * Input: "([)]"
 * Output: false
 * Example 5:
 * <p>
 * Input: "{[]}"
 * Output: true
 * # 3/18/19
 */
public class P20_ValidParentheses {
	@Test
	public void test(){
		Assert.assertTrue(isValid("()"));
	}

	public boolean isValid(String s) {
		char[] stack = new char[s.length()];
		int head = 0;

		for (char c: s.toCharArray()) {
			if (c == '(') {
				stack[head++] = ')';
			} else if (c == '[') {
				stack[head++] = ']';
			} else if (c == '{') {
				stack[head++] = '}';
			} else {
				if (head == 0 || stack[--head] != c) {
					return false;
				}
			}
		}
		return head == 0;

	}
}
