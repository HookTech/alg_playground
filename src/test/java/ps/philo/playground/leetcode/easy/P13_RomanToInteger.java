package ps.philo.playground.leetcode.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * # 1/22/19
 */
public class P13_RomanToInteger {
	@Test
	public void test() {
		Assert.assertEquals(romanToInt("III"), 3);
		Assert.assertEquals(romanToInt("IV"), 4);
		Assert.assertEquals(romanToInt("IX"), 9);
		Assert.assertEquals(romanToInt("LVIII"), 58);
		Assert.assertEquals(romanToInt("MCMXCIV"), 1994);
	}

	public int romanToInt(String s) {
		int result = 0;
		char[] digits = s.toCharArray();
		if (digits.length > 0) {
			/* Initialize value map for Roman chars  */
			int[] valueMap = new int[26];
			valueMap['I' - 'A'] = 1;
			valueMap['V' - 'A'] = 5;
			valueMap['X' - 'A'] = 10;
			valueMap['L' - 'A'] = 50;
			valueMap['C' - 'A'] = 100;
			valueMap['D' - 'A'] = 500;
			valueMap['M' - 'A'] = 1000;
			int lastIndex = digits[0] - 'A';
			result = valueMap[lastIndex];
			for (int i = 1; i < digits.length; i++) {
				int currentIndex = digits[i] - 'A';
				// If previous number was smaller in value then remove the previous and decrease the current value by previous value
				if (valueMap[lastIndex] < valueMap[currentIndex]) {
					result = result - 2 * valueMap[lastIndex] + valueMap[currentIndex];
				} else {
					result += valueMap[currentIndex]; // just add the current value
				}
				lastIndex = currentIndex; // update last index
			}
		}
		return result;
	}
}
