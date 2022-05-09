package ps.philo.playground.play;

import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;
import ps.philo.playground.OptimalBinarySearchTree;

import static ps.philo.playground.CommonStructure.*;

/**
 * philo
 * # 12/8/18
 */
public class OptimalBinarySearchTreeTest {
    @Test
    public void ptimalBinarySearchTreeTest(){
        OptimalBinarySearchTree testTree = new OptimalBinarySearchTree(5);
        float[] p = new float[]{Float.MAX_VALUE,0.15F,0.10F,0.05F,0.10F,0.20F};
        float[] q = new float[]{0.05F,0.10F,0.05F,0.05F,0.05F,0.10F};
        OptimalBinarySearchTree searchTree = testTree.optimalBST(p,q,5);
        TestUtil.printTree(new Tree<String>(searchTree.constructOptimalBST(),Integer.MAX_VALUE));
        TestUtil.printT(searchTree.getOptimalEstimation());
    }
}
