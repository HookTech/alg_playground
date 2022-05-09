package ps.philo.playground.leetcode.medium;

import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * philo
 * # 1/29/19
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class P22_GenerateParentheses {

	@Test
	public void testV1(){
		TestUtil.printCollections(generateParenthesisV1(3));
	}

	@Test
	public void testV2(){
		TestUtil.printCollections(generateParenthesisV2(3));
	}

	public List<String> generateParenthesisV1(int n) {//Brute Force
		List<String> combinations = new ArrayList();
		generateAll(new char[2 * n], 0, combinations);
		return combinations;
	}

	private void generateAll(char[] current, int pos, List<String> result) {
		if (pos == current.length) {
			if (valid(current))
				result.add(new String(current));
		} else {
			current[pos] = '(';
			generateAll(current, pos+1, result);
			current[pos] = ')';
			generateAll(current, pos+1, result);
		}
	}

	private boolean valid(char[] current) {
		int balance = 0;
		for (char c: current) {
			if (c == '(') balance++;
			else balance--;
			if (balance < 0) return false;
		}
		return (balance == 0);
	}

	public List<String> generateParenthesisV2(int n) {//backTracking
		List<String> ans = new ArrayList();
		backtrack(ans, "", 0, 0, n);
		return ans;
	}

	public void backtrack(List<String> ans, String cur, int open, int close, int max){
		if (cur.length() == max * 2) {
			ans.add(cur);
			return;
		}

		if (open < max)
			backtrack(ans, cur+"(", open+1, close, max);
		if (close < open)
			backtrack(ans, cur+")", open, close+1, max);
	}
}
