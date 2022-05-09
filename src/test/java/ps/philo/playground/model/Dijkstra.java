package ps.philo.playground.model;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * philo
 * # 2020/9/30
 */
public class Dijkstra {
	static class node {
		int num; //节点编号
		int length;//长度

		public node(int num, int length) {
			this.num = num;
			this.length = length;
		}
	}

	public static void main(String[] args) {

		int[][] map = new int[4][4];//记录权值，顺便记录链接情况，可以考虑附加邻接表
		initmap(map);//初始化
		boolean bool[] = new boolean[4];//判断是否已经确定
		int len[] = new int[4];//每个节点到源点的最短距离
		for (int i = 0; i < 4; i++) {
			len[i] = Integer.MAX_VALUE;
		}
		Queue<node> deal = new PriorityQueue<>(Comparator.comparingInt(o -> o.length));
		len[0] = 0;//从0这个点开始
		deal.add(new node(0, 0));
		while (!deal.isEmpty()) {
			node lowestStart = deal.poll();
			int index = lowestStart.num;//节点编号
			int length = lowestStart.length;//节点当前点距离
			bool[index] = true;//抛出的点确定
			for (int i = 0; i < map[index].length; i++) {
				if (map[index][i] > 0 && !bool[i]) {
					node node = new node(i, length + map[index][i]);
					if (len[i] > node.length)//需要更新节点的时候更新节点并加入队列
					{
						len[i] = node.length;
						deal.add(node);
					}
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			System.out.println(len[i]);
		}
	}

	private static void initmap(int[][] map) {
		map[0][1] = 2;map[1][0] = 2;
		map[0][2] = 4;map[2][0] = 4;
		map[1][2] = 3;map[2][1] = 3;
		map[1][3] = 1;map[3][1] = 1;
		map[2][3] = 2;map[3][2] = 2;
	}
}
