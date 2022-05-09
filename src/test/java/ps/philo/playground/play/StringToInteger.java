package ps.philo.playground.play;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.rmi.UnexpectedException;

/**
 * philo
 * # 3/12/19
 */
public class StringToInteger {
	@Test
	public void test() throws Exception {
		Assert.assertEquals(Integer.valueOf("-634").intValue(), -634);
		Assert.assertEquals(convert("-634").intValue(), -634);
		Assert.assertEquals(convert("634").intValue(), 634);
		Assert.assertEquals(convert("0").intValue(), 0);
		Assert.assertEquals(convert("-0634").intValue(), -634);
	}

	public Integer convert(String input) throws Exception {
		boolean negative = input.charAt(0) == '-' ? true : false;
		int result = 0;
		int start = negative ? 1 : 0;
		for (int i = start; i < input.length(); i++) {
			char cc = input.charAt(i);
			if (negative && (Math.abs(Integer.MIN_VALUE) - result * 10 < convert(cc))){
				throw new Exception("overflow");
			}else if(!negative && Integer.MAX_VALUE - result * 10 < convert(cc)){
				throw new Exception("overflow");
			}
				result = convert(cc) + result * 10;
		}
		return negative ? (0 - result) : result;
	}

	int convert(char cc) throws UnexpectedException {
		if ('0' <= cc && cc <= '9') {
			return cc - '0';
		} else {
			throw new UnexpectedException("unexpectedChar");
		}
	}
}
