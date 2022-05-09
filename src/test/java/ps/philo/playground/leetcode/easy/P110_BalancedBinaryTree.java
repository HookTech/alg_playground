package ps.philo.playground.leetcode.easy;

import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;

import static ps.philo.playground.CommonStructure.*;

/**
 * philo
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary tree is defined as:
 * <p>
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Example 1:
 * <p>
 * Given the following tree [3,9,20,null,null,15,7]:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * Return true.
 * <p>
 * Example 2:
 * <p>
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * Return false.
 * # 3/24/19
 */
public class P110_BalancedBinaryTree {

	@Test
	public void test() {
		Object[] items = new Integer[]{1, 2, 2, 3};
		TestUtil.assertEqualAndPrintToInfo(tree2Array(array2Tree(items)), items);
//		items = new String[]{"3","9","20","null","null","15","7"};
//		TestUtil.assertEqualAndPrintToInfo(tree2Array(array2Tree(items)), items);
	}

	public boolean isBalanced(TreeNode root) {
		if (root == null) return true;
		int sub = deep(root.left) - deep(root.right);
		if (sub > 1 || sub < -1) return false;
		return isBalanced(root.left) && isBalanced(root.right);
	}

	public int deep(TreeNode node) {
		if (node == null) return -1;
		return Math.max(deep(node.left), deep(node.right)) + 1;
	}
}
