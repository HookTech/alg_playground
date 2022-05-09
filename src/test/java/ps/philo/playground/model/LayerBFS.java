package ps.philo.playground.model;


import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.System.out;
import static ps.philo.playground.CommonStructure.TreeNode;

/**
 * philo
 * # 2020/10/8
 */
public class LayerBFS {
	public static void main(String[] args) {
		TreeNode<Integer> root = new TreeNode<>(1, null, null);
		root.left = new TreeNode<>(2, null, new TreeNode(4, null, null));
		root.right = new TreeNode<>(3, new TreeNode(5, null, null), new TreeNode(6, new TreeNode(7, null, null), null));
		printLayer(root);
	}

	public static void printLayer(TreeNode<Integer> root) {
		Queue<TreeNode<Integer>> deal = new ArrayDeque<>();
		deal.offer(root);
		while (!deal.isEmpty()) {
			int size = deal.size();
			while (size-- > 0) {
				TreeNode<Integer> value = deal.poll();
				out.print(value.value > 0 ? value.value : "null");
				out.print(" ");
				if(isLeaf(value)) continue;
				if (value.left != null) {
					deal.offer(value.left);
				} else {
					deal.offer(new TreeNode(-1, null, null));
				}
				if (value.right != null) {
					deal.offer(value.right);
				} else {
					deal.offer(new TreeNode(-1, null, null));
				}
			}
			out.print('\n');
		}
	}

	private static boolean isLeaf(TreeNode<Integer> node){
		return node.left == null && node.right == null;
	}
}
