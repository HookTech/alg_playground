package ps.philo.playground.play;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;
import ps.philo.playground.CommonStructure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * philo
 * # 3/12/19
 */
public class QuickTest {

	@Test
	public void testtt(){
		System.out.println("Length of the longest substring: " + findLength("araaci", 2));
		System.out.println("Length of the longest substring: " + findLength("araaci", 1));
		System.out.println("Length of the longest substring: " + findLength("cbbebi", 3));
	}

	public static int findLength(String str, int k) {
		// TODO: Write your code here
		int startIndex=0;
		int maxLength = 0;
		Set<Character> distinct = new HashSet<>();
		for(int endIndex=0;endIndex < str.length();endIndex++){
			Character cc = str.charAt(endIndex);
			if(!distinct.contains(cc)) {
				distinct.add(cc);
			}
			while (distinct.size() > k){
				cc = str.charAt(startIndex++);
				if(distinct.contains(cc)){
					distinct.remove(cc);
				}
			}
			maxLength = Math.max(endIndex - startIndex + 1, maxLength);
		}
		return maxLength;
	}

	public boolean isPalindrome(CommonStructure.ListNode head) {
		if(head == null || head.next == null) return true;
		CommonStructure.ListNode slow = head, fast = head.next, pre = null, prepre = null;
		while(fast != null && fast.next != null) {
			//反转前半段链表
			pre = slow;
			slow = slow.next;
			fast = fast.next.next;
			//先移动指针再来反转
			pre.next = prepre;
			prepre = pre;
		}
		CommonStructure.ListNode p2 = slow.next;
		slow.next = pre;
		CommonStructure.ListNode p1 = fast == null? slow.next : slow;
		while(p1 != null) {
			if(p1.value != p2.value)
				return false;
			p1 = p1.next;
			p2 = p2.next;
		}
		return true;
	}

	@Test
	public void test() {
		int[] a = new int[]{14, -1, 3, 4, 9, 7, 3, 2};
		int i = 100;
		while (i-- > 0) {
			TestUtil.assertEqualArray(new int[]{-1, 2, 3, 3, 4}, miniK(a, 5));
		}
		TestUtil.assertEqualArray(new int[]{-1, 2, 3, 3}, miniK2(a, 4));
	}

	public int[] miniK(int[] input, int k) {
		StdRandom.shuffle(input);
//		quickSort(input, 0, input.length - 1);
		simpleDualPivotQuickSort(input, 0, input.length - 1);
		int[] mini = new int[k];
		for (int i = 0; i < k; i++) {
			mini[i] = input[i];
		}
		return mini;
	}

	public int[] miniK2(int[] input, int k) {
		Queue<Integer> pquene = new PriorityQueue<>();
		for (int item : input) {
			pquene.offer(item);
		}
		int[] mini = new int[k];
		for (int i = 0; i < k; i++) {
			mini[i] = pquene.poll();
		}
		return mini;
	}

	public void simpleDualPivotQuickSort(int[] input, int lo, int hi) {
		if (lo >= hi) return;
		int lt = lo, i = lo + 1, gt = hi;
		int v = input[lo];
		while (i <= gt) {
			if (input[i] > v) {
				swap(input, i, gt--);
			} else if (input[i] < v) {
				swap(input, i++, lt++);
			} else {
				i++;
			}
		}
		simpleDualPivotQuickSort(input, lo, lt - 1);
		simpleDualPivotQuickSort(input, gt + 1, hi);
	}

	public void quickSort(int[] a, int low, int high) {
		if (low >= high) return;
		int splitPoint = partition(a, low, high);
		quickSort(a, low, splitPoint - 1);
		quickSort(a, splitPoint + 1, high);
	}

	public int partition(int[] a, int low, int high) {
		int split = a[low];
		int i = low, j = high + 1;
		while (true) {
			while (a[++i] < split) if (i == high) break;
			while (a[--j] > split) if (j == low) break;
			if (i >= j) break;
			swap(a, i, j);
		}
		swap(a, j, low);
		return j;
	}

	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
