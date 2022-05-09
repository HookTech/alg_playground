package ps.philo.playground.leetcode.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * # 1/12/19
 */
public class P6_ZigZagConversion {
	@Test
	public void test() {
		Assert.assertEquals(convert("PAYPALISHIRING", 3), "PAHNAPLSIIGYIR");
		Assert.assertEquals(convert("PAYPALISHIRING", 4), "PINALSIGYAHRPI");
		Assert.assertEquals(convert("AB", 1), "AB");
	}

	/**
	 * mock map to locate cols link to store the value
	 * */
	public String convert(String s, int numRows) {
		if (numRows < 1) {
			return "";
		}
		if (numRows == 1) {
			return s;
		}
		PairNode[] org = new PairNode[numRows];
		PairNode[] current = new PairNode[numRows];
		for (int i = 0; i < numRows; i++) {
			org[i] = new PairNode(-1, (char) 0, null);
			current[i] = org[i];
		}

		int col = 0, row = -1, increase = 1;
		for (int i = 0; i < s.length(); i++) {
			row += increase;
			col += increase > 0 ? 0 : 1;
			if (row == 0) {
				increase = 1;
			} else if (row == numRows - 1) {
				increase = -1;
			}
			current[row].next = new PairNode(col, s.charAt(i), null);
			current[row] = current[row].next;
		}
		char[] res = new char[s.length()];
		for (int i = 0, t = 0; i < numRows; i++) {
			PairNode cur = org[i].next;
			while (cur != null) {
				res[t] = cur.v;
				cur = cur.next;
				t++;
			}
		}
		return new String(res);
	}

	private class PairNode {
		int col;
		char v;
		PairNode next;

		public PairNode(int col, char v, PairNode next) {
			this.col = col;
			this.v = v;
			this.next = next;
		}
	}
}
