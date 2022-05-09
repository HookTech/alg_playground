package ps.philo.playground.leetcode.hard;

import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;

import static ps.philo.playground.CommonStructure.ListNode;

/**
 * philo
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * N stand for length of list array, M stand for average node length of each list
 * the algorithm is N*lg(M)
 * # 3/21/19
 */
public class P23_MergeKSortedLists {

	@Test
	public void test() throws Exception {
		ListNode[] heads = new ListNode[]{
			TestUtil.generateListFromSeries(1, 4, 5),
			TestUtil.generateListFromSeries(1, 3, 4),
			TestUtil.generateListFromSeries(2, 6)
		};
		TestUtil.assertList(
			mergeKSortedList(heads),
			1, 1, 2, 3, 4, 4, 5, 6
		);
	}

	public ListNode<Integer> mergeKSortedList(ListNode<Integer>[] heads) {
		return mergeRange(heads, 0, heads.length - 1);
	}

	public ListNode<Integer> mergeRange(ListNode<Integer>[] heads, int lo, int hi) {
		if (lo >= hi) return heads[lo];
		int mid = (lo + hi) / 2;
		ListNode<Integer> left = mergeRange(heads, lo, mid);
		ListNode<Integer> right = mergeRange(heads, mid + 1, hi);
		return mergeTwoList(left, right);
	}

	public ListNode<Integer> mergeTwoList(ListNode<Integer> left, ListNode<Integer> right) {
		if (left == null) return right;
		if (right == null) return left;
		if (left.value > right.value) {
			right.next = mergeTwoList(left, right.next);
			return right;
		} else {
			left.next = mergeTwoList(left.next, right);
			return left;
		}
	}
}
