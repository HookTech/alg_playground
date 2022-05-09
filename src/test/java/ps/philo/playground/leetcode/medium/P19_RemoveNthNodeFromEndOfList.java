package ps.philo.playground.leetcode.medium;

import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;

import static ps.philo.playground.CommonStructure.ListNode;

/**
 * philo
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * <p>
 * Example:
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * <p>
 * Given n will always be valid.
 * <p>
 * Follow up:
 * <p>
 * Could you do this in one pass?
 * # 3/18/19
 */
public class P19_RemoveNthNodeFromEndOfList {
	@Test
	public void test() throws Exception {
		ListNode<Integer> head = TestUtil.generateListFromSeries(1, 2, 3, 4, 5);
		TestUtil.assertList(
			removeNthFromEnd(head, 2),
			1, 2, 3, 5
		);
		head = TestUtil.generateListFromSeries(1, 2, 3, 4, 5);
		TestUtil.assertList(
			removeNthFromEnd(head, 5),
			2, 3, 4, 5
		);
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode firstNode = head;
		int gap = n;
		while (gap-- > 0) {
			firstNode = firstNode.next;
		}
		if (firstNode == null) {
			ListNode nHead = head.next;
			head.next = null;
			return nHead;
		}
		ListNode secondNode = new ListNode(0);
		secondNode.next = head;
		for (; firstNode != null; firstNode = firstNode.next) {
			secondNode = secondNode.next;
		}
		ListNode del = secondNode.next;
		secondNode.next = del.next;
		return head;
	}
}
