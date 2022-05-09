package ps.philo.playground.leetcode.medium;

import org.testng.annotations.Test;

import static java.lang.System.out;

/**
 * Created by hjhe on 2019/7/16
 */
public class P62_Paths {
    @Test
    public void test(){
        out.println(uniquePaths(7,3));
    }

    public int uniquePaths(int m, int n) {
        if(m == 1 || n == 1) return 1;
        return path(m,n);
    }

    //TODO:切换为动态规划
    public int path(int m, int n){
        if(m == 1 || n == 1) return 1;
        return path(--m, n) + path(m, --n);
    }
}
