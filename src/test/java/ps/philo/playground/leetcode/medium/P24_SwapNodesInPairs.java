package ps.philo.playground.leetcode.medium;

import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;

import static ps.philo.playground.CommonStructure.ListNode;
/**
 * philo
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 *
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * # 3/20/19
 */
public class P24_SwapNodesInPairs {
	@Test
	public void test() throws Exception {
		ListNode<Integer> head = TestUtil.generateListFromSeries(1,2,3,4);
		TestUtil.assertList(
			swapPairsNew(head),
			2,1,4,3
		);

	}
	public ListNode swapPairs(ListNode head) {
		ListNode nHead = swap(head);
		head = nHead;
		while(head != null && head.next != null){
			head.next.next = swap(head.next.next);
			head = head.next.next;
		}
		return nHead;
	}

	ListNode swap(ListNode p){
		if(p == null || p.next == null) return p;
		ListNode gradeChild = p.next.next;
		ListNode child = p.next;
		p.next = gradeChild;
		child.next = p;
		return child;
	}

	public ListNode swapPairsNew(ListNode head) {
		ListNode dump = new ListNode(0);
		dump.next = head;
		head = dump;
		while (head.next != null
			&& head.next.next != null) {
			ListNode n1 = head.next;
			ListNode n2 = head.next.next;
			head.next = n2;
			n1.next = n2.next;
			n2.next = n1;
			head = n1;

		}
		return dump.next;
	}
}
