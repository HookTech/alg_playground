package ps.philo.playground.leetcode.easy;

import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;

import static ps.philo.playground.CommonStructure.ListNode;

/**
 * philo
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class P21_MergeTwoSortedLists {

	@Test
	public void test() throws Exception {
		TestUtil.assertList(
			mergeTwoLists(
				TestUtil.generateListFromSeries(1,2,4),
				TestUtil.generateListFromSeries(1,3,4)
			),
			1,1,2,3,4,4
		);
	}

	public ListNode<Integer> mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
		if(l1 != null && l2 == null) return l1;
		if(l1 == null && l2 != null) return l2;
		if(l1 == null && l2 == null) return null;
		if(l1.value > l2.value){
			l2.next = mergeTwoLists(l1,l2.next);
			return l2;
		}else{
			l1.next = mergeTwoLists(l1.next,l2);
			return l1;
		}
	}
}
