package ps.philo.playground.model;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * philo
 * # 2/7/19
 */
public class KMPS {
	@Test
	public void test() {
		final String pattern = "ABABAC";
		final String text = "BCBAABACAABABACAA";

		KMP kmp = new KMPWithNextArray(pattern);
		Assert.assertEquals(kmp.search(text), 9);
		Assert.assertEquals(kmp.search("BCBAABACAADABACAA"), 17);

		kmp = new KMPWithDFA(pattern);
		Assert.assertEquals(kmp.search(text), 9);
		Assert.assertEquals(kmp.search("BCBAABACAADABACAA"), 17);
	}

	private static abstract class KMP {
		protected abstract void parsePattern(String pattern);

		protected abstract int search(String txt);

		public KMP(String pattern) {
			parsePattern(pattern);
		}
	}

	/**
	 * pattern using next array
	 * */
	private static class KMPWithNextArray extends KMP {
		private int[] next;
		private String pattern;

		public KMPWithNextArray(String pattern) {
			super(pattern);
		}

		@Override
		protected void parsePattern(String pattern) {
			this.pattern = pattern;
			next = new int[pattern.length() + 1];
			next[0] = -1;
			int i = 0, j = -1;
			while (i < pattern.length()) {
				if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
					i++;
					j++;
					next[i] = j;
				} else {
					j = next[j];
				}
			}
		}

		@Override
		public int search(String txt) {
			int i = 0, j = 0;
			while (i < txt.length() && j < pattern.length()) {
				if (j == -1 || txt.charAt(i) == pattern.charAt(j)) {
					i++;
					j++;
				} else {
					j = next[j];
				}
			}
			if (j == pattern.length()) {
				return i - j;
			} else {
				return i;
			}
		}
	}

	/**
	 * advanced pattern using DFA
	 * */
	private static class KMPWithDFA extends KMP {
		private final int R = 256;
		private int[][] dfa;       // the KMP automoton
		private String pat;

		public KMPWithDFA(String pattern) {
			super(pattern);
		}

		@Override
		protected void parsePattern(String pattern) {
			this.pat = pattern;

			// build DFA from pattern
			int m = pat.length();
			dfa = new int[R][m];
			dfa[pat.charAt(0)][0] = 1;
			for (int x = 0, j = 1; j < m; j++) {
				for (int c = 0; c < R; c++)
					dfa[c][j] = dfa[c][x];     // Copy mismatch cases.
				dfa[pat.charAt(j)][j] = j + 1;   // Set match case.
				x = dfa[pat.charAt(j)][x];     // Update restart state.
			}
		}

		@Override
		protected int search(String txt) {
			// simulate operation of DFA on text
			int n = txt.length();
			int m = pat.length();
			int i, j;
			for (i = 0, j = 0; i < n && j < m; i++) {
				j = dfa[txt.charAt(i)][j];
			}
			if (j == m) { // found
				return i - m;
			}
			return n;                    // not found
		}
	}
}
