package ps.philo.playground.leetcode.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * # 1/22/19
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * Example 1:
 * <p>
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * <p>
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
public class P14_LongestCommonPrefix {
	@Test
	public void test1() {
		Assert.assertEquals(longestCommonPrefixV1(new String[]{"flower", "flow", "flight"}), "fl");
		Assert.assertEquals(longestCommonPrefixV1(new String[]{"dog","racecar","car"}), "");
	}

	@Test
	public void test2() {
		Assert.assertEquals(longestCommonPrefixV2(new String[]{"flower", "flow", "flight"}), "fl");
		Assert.assertEquals(longestCommonPrefixV2(new String[]{"dog","racecar","car"}), "");
	}

	@Test
	public void test3() {
		Assert.assertEquals(longestCommonPrefixV3(new String[]{"flower", "flow", "flight"}), "fl");
		Assert.assertEquals(longestCommonPrefixV3(new String[]{"dog","racecar","car"}), "");
	}

	public String longestCommonPrefixV1(String[] strs) {//Horizontal scanning
		if(strs.length == 0) return "";
		String prefix = strs[0];
		boolean tag = true;
		for (int i = 1; i < strs.length && tag; i++) {
			while (strs[i].indexOf(prefix) != 0){
				prefix = prefix.substring(0, prefix.length() - 1);
				if(prefix.isEmpty()){
					tag = false;
					break;
				}
			}
		}
		return prefix;
	}

	public String longestCommonPrefixV2(String[] strs) {//Vertical scanning
		if (strs == null || strs.length == 0) return "";
		for (int i = 0; i < strs[0].length() ; i++){
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j ++) {
				if (i == strs[j].length() || strs[j].charAt(i) != c)
					return strs[0].substring(0, i);
			}
		}
		return strs[0];
	}

	public String longestCommonPrefixV3(String[] strs) {//Divide and conquer
		if (strs == null || strs.length == 0) return "";
		return longestCommonPrefix(strs, 0 , strs.length - 1);
	}

	private String longestCommonPrefix(String[] strs, int l, int r) {
		if (l == r) {
			return strs[l];
		}
		else {
			int mid = (l + r)/2;
			String lcpLeft =   longestCommonPrefix(strs, l , mid);
			String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
			return commonPrefix(lcpLeft, lcpRight);
		}
	}

	String commonPrefix(String left,String right) {
		int min = Math.min(left.length(), right.length());
		for (int i = 0; i < min; i++) {
			if ( left.charAt(i) != right.charAt(i) )
				return left.substring(0, i);
		}
		return left.substring(0, min);
	}
}
