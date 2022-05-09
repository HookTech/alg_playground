package ps.philo.playground;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * CommonStructure contains all kinds of class
 *
 * @author philo
 * @create 2018-03-27 9:31 AM
 **/
public class CommonStructure {
	public static class ListNode<T> {
		public T value;
		public ListNode<T> next;

		public ListNode(T data) {
			value = data;
		}
	}

	public static class TreeNode<T> {
		public T value;
		public TreeNode<T> left;
		public TreeNode<T> right;
		public int depth = 0;

		public TreeNode(T v, TreeNode l, TreeNode r) {
			this.value = v;
			this.left = l;
			this.right = r;
		}

		public TreeNode(T v, TreeNode l, TreeNode r, int d) {
			this(v, l, r);
			this.depth = d;
		}

		private String preOrderTraversal(TreeNode<T> root) {
			StringBuilder builder = new StringBuilder();
			if (root != null) {
				builder.append(root.value);
				builder.append(preOrderTraversal(root.left));
				builder.append(preOrderTraversal(root.right));
			}
			return builder.toString();
		}

		private String deepFirst() {
			StringBuilder builder = new StringBuilder();
			if (left == null && right == null) {
				builder.append(value);
			} else if (left != null && right == null) {
				builder.append("  ").append(value).append("  ")
					.append("\n")
					.append(" ").append("/").append(" ").append(" ").append(" ")
					.append("\n")
					.append(left.toString()).append("   ").append(" ");
			} else if (left == null && right != null) {
				builder.append("  ").append(value).append("  ")
					.append("\n")
					.append(" ").append(" ").append(" ").append("\\").append(" ")
					.append("\n")
					.append(" ").append("   ").append(right.toString());
			} else {
				builder.append("  ").append(value).append("  ")
					.append("\n")
					.append(" ").append("/").append(" ").append("\\").append(" ")
					.append("\n")
					.append(left.toString()).append("   ").append(right.toString());
			}
			return builder.toString();
		}
	}

	public static class Tree<T> {
		TreeNode<T> root;
		int maxDepth;

		public Tree(TreeNode root, int maxDepth) {
			this.root = root;
			this.maxDepth = maxDepth;
		}

		@Override
		public String toString() {
			Queue<TreeNode<T>> nodes = new ArrayDeque<>();
			StringBuilder builder = new StringBuilder();
			nodes.add(root);
			while (!nodes.isEmpty()) {
				TreeNode<T> curNode = nodes.poll();
				builder.append("  ").append(curNode.value).append("  -- ").append(curNode.depth)
					.append("\n");
				addToQueue(nodes, curNode.left);
				addToQueue(nodes, curNode.right);
			}
			return builder.toString();
		}

		private void printWhiteSpace(StringBuilder builder, int count) {
			for (int i = 0; i < count; i++) {
				builder.append(" ");
			}
		}

		private <T> void addToQueue(Queue<TreeNode<T>> queue, TreeNode<T> item) {
			if (item != null) {
				queue.add(item);
			}
		}
	}

	public static Object[] tree2Array(TreeNode<Object> root) {
		List<Object> res = new LinkedList<>();
		ArrayDeque<TreeNode<Object>> bfs = new ArrayDeque<>();
		bfs.push(root);
		fetchNode(bfs, res);
		return res.toArray();
	}

	private static void fetchNode(ArrayDeque<TreeNode<Object>> q, List<Object> collector) {
		if (!q.isEmpty()) {
			TreeNode<Object> node = q.poll();
			collector.add(node.value);
			if (node.left != null)
				q.push(node.left);
			if (node.right != null)
				q.push(node.right);
			fetchNode(q, collector);
		}
	}

	public static TreeNode<Object> array2Tree(Object[] items) {
		int real = 1, last = items.length;
		TreeNode<Object>[] index = new TreeNode[items.length];
		TreeNode<Object> root = new TreeNode<>(items[tran(real)], null, null);
		index[tran(real)] = root;
		while (++real <= last) {
			TreeNode<Object> nNode = new TreeNode<>(items[tran(real)], null, null);
			if (real % 2 == 0) {
				index[tran(real / 2)].left = nNode;
			} else {
				index[tran(real / 2)].right = nNode;
			}
			index[tran(real)] = nNode;
		}
		return root;
	}

	private static int tran(int realIndex) {
		return realIndex - 1;
	}
}
