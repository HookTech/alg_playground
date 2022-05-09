package ps.philo.playground.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Big Data sort Algorithm Problem Model
 * Using multiple thread & merge sort to deal with it
 * */
public class JavaForkJoin {
	@Test
	public void test() {
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		int[] testCase = generateRandomIntArray(1000 * 1000 * 100);//One Hundred Million
		Assert.assertFalse(isSorted(testCase));
		forkJoinPool.invoke(new SortTask(testCase, new int[testCase.length], 0, testCase.length - 1));
		Assert.assertTrue(isSorted(testCase));
	}

	class SortTask extends RecursiveAction {
		final int[] array;
		final int lo;
		final int hi;
		final int[] copy;
		final int scale;

		SortTask(int[] array, int[] copy, int lo, int hi) {
			assert array.length == copy.length;
			this.array = array;
			this.lo = lo;
			this.hi = hi;
			this.copy = copy;
			this.scale = array.length;
		}

		protected void compute() {
			if (hi - lo < 1000 * 10)
				Arrays.sort(array, lo, hi + 1);
			else {
				int mid = (lo + hi) >>> 1;
				invokeAll(new SortTask(array, copy, lo, mid),
					new SortTask(array, copy, mid + 1, hi));
				merge(array, copy, lo, mid, hi);
			}
		}

		private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
			// precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
			assert isSorted(a, lo, mid);
			assert isSorted(a, mid + 1, hi);

			// copy to aux[]
			for (int k = lo; k <= hi; k++) {
				aux[k] = a[k];
			}

			// merge back to a[]
			int i = lo, j = mid + 1;
			for (int k = lo; k <= hi; k++) {
				if (i > mid) a[k] = aux[j++];
				else if (j > hi) a[k] = aux[i++];
				else if ((aux[j] < (aux[i]))) a[k] = aux[j++];
				else a[k] = aux[i++];
			}

			// postcondition: a[lo .. hi] is sorted
			assert isSorted(a, lo, hi);
		}
	}

	private static int[] generateRandomIntArray(int n) {
		int[] x = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = i;
		}
		Random r = new Random();
		for (int i = 0; i < n; i++) {
			int in = r.nextInt(n - i) + i;
			int t = x[in];
			x[in] = x[i];
			x[i] = t;
		}
		return x;
	}

	private static boolean isSorted(int[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(int[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if ((a[i] < (a[i - 1]))) return false;
		return true;
	}
}
