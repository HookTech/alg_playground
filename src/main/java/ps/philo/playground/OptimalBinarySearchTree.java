package ps.philo.playground;

import static ps.philo.playground.CommonStructure.TreeNode;

/**
 * playground for optimal binary search tree problem
 */
public class OptimalBinarySearchTree {
    int n;//input's range
    float[][] e;//estimate
    int[][] root;//tree's root

    public OptimalBinarySearchTree(int n) {
        this.n = n;
        reInit();
    }

    /**
     * generate optimal BST
     *
     * @param p key node's cost array
     * @param q dummy key node's cost array
     * @param n input's range
     */
    public OptimalBinarySearchTree optimalBST(float[] p, float[] q, int n) throws ExceptionInInitializerError {
        if (n != this.n || p.length != n + 1 || q.length != n + 1) throw new ExceptionInInitializerError();
        float[][] w = new float[n + 2][n + 1];//w[1..n+1,0..n]
        for (int i = 1; i <= n + 1; i++) {
            e[i][i - 1] = q[i - 1];
            w[i][i - 1] = q[i - 1];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                e[i][j] = Float.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j] + q[j];
                for (int r = i; r <= j; r++) {
                    float t = e[i][r - 1] + e[r + 1][j] + w[i][j];
                    if (t < e[i][j]) {
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }
        return this;
    }

    /**
     * clear BST
     */
    public void reInit() {
        e = new float[n + 2][n + 1];//e[1..n+1,0..n]
        root = new int[n + 1][n + 1];//root[1..n,1..n]
    }

    /**
     * construct BST
     */
    public TreeNode<String> constructOptimalBST() {
        return constructBST(root, 1, n, -1);
    }
    /**
     * get optimal estimation
     * */
    public float getOptimalEstimation(){
        return e[1][n];
    }

    private TreeNode<String> constructBST(int[][] rootArray, int firstIndex, int lastIndex, int parentDepth) {
        if (firstIndex <= lastIndex) {
            int rootIndex = rootArray[firstIndex][lastIndex];
            return new TreeNode<>(
                    "K" + String.valueOf(rootIndex),
                    constructBST(rootArray,firstIndex,rootIndex - 1, parentDepth + 1),
                    constructBST(rootArray,rootIndex + 1,lastIndex, parentDepth + 1),
                    parentDepth + 1
            );
        } else{
            return new TreeNode<>("d" + String.valueOf(lastIndex),null,null, ++parentDepth);
        }
    }
}
