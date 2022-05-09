package ps.philo.playground.leetcode.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * # 1/20/19
 */
public class P12_IntegertoRoman {
	@Test
	public void test1(){
		Assert.assertEquals(intToRomanV1(3),"III");
		Assert.assertEquals(intToRomanV1(4),"IV");
		Assert.assertEquals(intToRomanV1(9),"IX");
		Assert.assertEquals(intToRomanV1(58),"LVIII");
		Assert.assertEquals(intToRomanV1(1994),"MCMXCIV");
	}

	@Test
	public void test2(){
		Assert.assertEquals(intToRomanV2(3),"III");
		Assert.assertEquals(intToRomanV2(4),"IV");
		Assert.assertEquals(intToRomanV2(9),"IX");
		Assert.assertEquals(intToRomanV2(58),"LVIII");
		Assert.assertEquals(intToRomanV2(1994),"MCMXCIV");
	}

	public String intToRomanV1(int num) {
		String[] M = {"", "M", "MM", "MMM"};
		String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
	}

	public String intToRomanV2(int num) {
		String[] M = generateFormatArray('M','0','0');
		String[] C = generateFormatArray('C','D','M');
		String[] X = generateFormatArray('X','L','C');
		String[] I = generateFormatArray('I','V','X');
		return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
	}

	private String[] generateFormatArray(char start, char middle, char end){
		String[] array = new String[10];
		for (int i = 0; i < 4; i++) {
			StringBuilder builder = new StringBuilder();
			for (int j = 0; j < i; j++) {
				builder.append(start);
			}
			array[i] = builder.toString();
		}
		if(middle != '0'){
			array[4] = new StringBuilder().append(start).append(middle).toString();
			array[5] = String.valueOf(middle);
			for (int i = 6; i < 9; i++) {
				StringBuilder builder = new StringBuilder();
				builder.append(middle);
				for (int j = 5; j < i;j++){
					builder.append(start);
				}
				array[i] = builder.toString();
			}
		}
		if(end != '0'){
			array[9] = new StringBuilder().append(start).append(end).toString();
		}
		return array;
	}
}
