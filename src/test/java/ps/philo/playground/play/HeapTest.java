package ps.philo.playground.play;

import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;

/**
 * philo
 * # 4/13/19
 */
public class HeapTest {

	@Test
	public void test(){
		int[] a = new int[]{14, -1, 3, 4, 9, 7, 3, 2};
		int i = 100;
		while (i-- > 0) {
			TestUtil.assertEqualArray(new int[]{-1, 2, 3, 3, 4}, miniK(a, 5));
		}
	}

	public int[] miniK(int[] input, int k){
		int N = input.length;
		int[] mini = new int[k];
		//copy to standard expression
		int[] minHeap = new int[N + 1];
		for(int i = 0; i < N; i++){
			minHeap[i+1] = input[i];
		}
		//construct minHeap
		for(int r = N >> 1; r > 0 ; r--){
			sink(minHeap,r, N);
		}
		//fetch k value and put into mini array
		int j = 0;
		while (k-- > 0){
			exch(minHeap, 1, N);
			mini[j++] = minHeap[N--];
			sink(minHeap, 1, N);
		}
		return mini;
	}

	private void sink(int[] minHeap, int k, int N){
		while (k << 1 <= N) {
			int j = k << 1;
			if (j < N && minHeap[j + 1] < minHeap[j]) j++;
			if(minHeap[k] <= minHeap[j]) break;
			exch(minHeap, k, j);
			k = j;
		}
	}

	private void exch(int[] minHeap, int i, int j){
		int tmp = minHeap[i];
		minHeap[i] = minHeap[j];
		minHeap[j] = tmp;
	}
}
