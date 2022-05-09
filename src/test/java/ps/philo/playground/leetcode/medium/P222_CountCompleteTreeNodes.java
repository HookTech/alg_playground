package ps.philo.playground.leetcode.medium;

import java.util.ArrayDeque;

import static ps.philo.playground.CommonStructure.*;

/**
 * philo
 * Given a complete binary tree, count the number of nodes.
 *
 * Note:
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Example:
 *
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * Output: 6
 * # 3/25/19
 */
public class P222_CountCompleteTreeNodes {

	int counter = 0;
	public int countNodes_v1(TreeNode root) {
		if(root == null) return 0;
		ArrayDeque<TreeNode> q = new ArrayDeque<TreeNode>();
		q.push(root);
		counter++;
		countNodes_v1(q);
		return counter;
	}

	public void countNodes_v1(ArrayDeque<TreeNode> q){
		if(!q.isEmpty()){
			TreeNode node = q.poll();
			if(node.left != null){
				q.push(node.left);
				counter++;
			}
			if(node.right != null){
				q.push(node.right);
				counter++;
			}
			countNodes_v1(q);
		}
	}

	public int countNodes_v2(TreeNode root) {
		travel(root);
		return counter;
	}

	public void travel(TreeNode node){
		if(node == null) return;
		counter++;
		travel(node.left);
		travel(node.right);
	}
}
