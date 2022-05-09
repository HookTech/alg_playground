package ps.philo.playground.leetcode.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * philo
 * # 1/28/19
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class P17_LetterCombinationsOfAPhoneNumber {

	@Test
	public void test(){
		Assert.assertEquals(
			letterCombinations("23"),
			Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")
			);
		Assert.assertEquals(
			letterCombinations(""),
			Arrays.asList()
		);
	}

	String[] phone = new String[]{"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

	public List<String> letterCombinations(String digits) {
		if(digits.isEmpty()) return new ArrayList<>();
		List<String> output = new ArrayList<>();
		backTrack(output, "", digits);
		return output;
	}

	private void backTrack(List<String> output, String currentStr, String nextDigits) {
		if (nextDigits.isEmpty()) {
			output.add(currentStr);
		} else {
			char num = nextDigits.charAt(0);;
			String letters = phone[index(num)];
			for (int i = 0; i < letters.length(); i++) {
				String cc = letters.substring(i, i + 1);
				backTrack(output, currentStr + cc, nextDigits.substring(1));
			}
		}
	}

	private int index(char c){
		return c - '2';
	}

}
