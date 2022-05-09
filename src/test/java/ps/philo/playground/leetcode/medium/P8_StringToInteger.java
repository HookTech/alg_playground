package ps.philo.playground.leetcode.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * # 1/14/19
 */
public class P8_StringToInteger {
	@Test
	public void test(){
		Assert.assertEquals(myAtoi("   -42"),-42);
		Assert.assertEquals(myAtoi("4193 with words"),4193);
		Assert.assertEquals(myAtoi("words and 987"),0);
		Assert.assertEquals(myAtoi("-91283472332"),-2147483648);
		Assert.assertEquals(myAtoi("91283472332"),2147483647);
		Assert.assertEquals(myAtoi("      "),0);
	}

	public int myAtoi(String str){
		int index = 0, sign = 1, total = 0;
		//1. Empty string
		if(str.length() == 0) return 0;

		//2. Remove Spaces
		while(index < str.length() && str.charAt(index) == ' ')
			index ++;

		if(index == str.length()) return 0;

		//3. Handle signs
		if(str.charAt(index) == '+' || str.charAt(index) == '-'){
			sign = str.charAt(index) == '+' ? 1 : -1;
			index ++;
		}

		//4. Convert number and avoid overflow
		while(index < str.length()){
			int digit = str.charAt(index) - '0';
			if(digit < 0 || digit > 9) break;

			//check if total will be overflow after 10 times and add digit
			if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

			total = 10 * total + digit;
			index ++;
		}
		return total * sign;
	}
}
