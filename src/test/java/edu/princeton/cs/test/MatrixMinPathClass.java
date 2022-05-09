package edu.princeton.cs.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 二维矩阵中最小的路径值，经典动态规划
 */
public class MatrixMinPathClass implements RecursionAndDinamicCallMethod {

    private Map<Coordinate, Integer> cachedMap = new HashMap<>();

    @Override
    public void callRecursionAndDinamicAlgoMethod() {
        TestUtil.printString("++++++++++++++++ getMatrixMinPathTest ++++++++++++++++");
        getMatrixMinPathTest();
    }

    @Test
    private void getMatrixMinPathTest() {
        Matrix testCase = Matrix.getInitMatrix();
        Assert.assertEquals(calulateMin(testCase, 0, 0), 1);
        Assert.assertEquals(calulateMin(testCase, 1, 0), 4);
        Assert.assertEquals(calulateMin(testCase, 0, 1), 9);
        Assert.assertEquals(calulateMin(testCase, 1, 1), 5);
        Assert.assertEquals(calulateMin(testCase, 2, 2), 11);
        Assert.assertEquals(calulateMin(testCase, 3, 3), 12);
        Assert.assertEquals(calulateMin(testCase, 1, 2), 5);
        Assert.assertEquals(minPathSum2(testCase.getDp()), 12);
        Assert.assertEquals(minPathSum1(testCase.getDp()), 12);
    }

    public int minPathSum1(int[][] m){
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){return 0;}
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public int minPathSum2(int[][] m) {
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){return 0;}
        int more = Math.max(m.length, m[0].length);
        int less = Math.min(m.length, m[0].length);
        boolean rowmore = more == m.length;//行数是否大于列数
        int[] arr = new int[less];//辅助数组
        arr[0] = m[0][0];
        for (int i = 1; i < less; i++) {
            arr[i] = arr[i - 1] + (rowmore ? m[0][i] : m[i][0]);//init arr
        }
        for (int i = 1; i < more; i++) {
            arr[0] = arr[0] + (rowmore ? m[i][0] : m[0][i]);
            for (int j = 1; j < less; j++) {
                arr[j] = Math.min(arr[j - 1], arr[j]) + (rowmore ? m[i][j] : m[j][i]);
            }
        }
        return arr[less - 1];
    }

    /**
     * self implements
     * */
    private int calulateMin(Matrix mux, int i, int j) {
        if (i < 0 || i > mux.cols() || j < 0 || j > mux.rows()) {
            return 0;
        }
        Coordinate cod = new Coordinate(i, j);
        if (cachedMap.containsKey(cod)) {
            return cachedMap.get(cod);
        }
        int choice1 = calulateMin(mux, i - 1, j );
        int choice2 = calulateMin(mux, i,  j - 1);
        int value = choose(choice1,choice2) + mux.getValue(i, j);
        cachedMap.put(cod, value);
        return value;
    }

    private int choose(int choice1, int choice2){
        if(choice1 == 0 && choice2 != 0){ return choice2;}
        else if(choice1 != 0 && choice2 == 0){return choice1;}
        else if(choice1 == 0 && choice2 == 0) {return 0;}
        else {
            return (choice1 < choice2 ? choice1 : choice2);
        }
    }

    private class Coordinate {
        int indexI;
        int indexJ;

        public Coordinate(int indexI, int indexJ) {
            this.indexI = indexI;
            this.indexJ = indexJ;
        }

        @Override
        public int hashCode() {
            return indexI * 100 + indexJ;
        }

        @Override
        public boolean equals(Object obj) {
            return this.indexI == ((Coordinate) obj).indexI
                    &&
                    this.indexJ == ((Coordinate) obj).indexJ;
        }
    }

    private static class Matrix {
        int[][] dp;

        public Matrix(int[][] init, int rows, int cols) {
            dp = init;
            rowNum = rows;
            colNum = cols;
        }

        int rowNum;
        int colNum;

        public int rows() {
            return this.rowNum;
        }

        public int cols() {
            return this.colNum;
        }

        public int[][] getDp(){
            return dp;
        }

        public int getValue(int i, int j) {
            if (i < 0 || i > cols() || j < 0 || j > rows()) {
                return 0;
            }
            return dp[j][i];
        }

        public static Matrix getInitMatrix() {
            int[][] raw =
                    {
                            {1, 3, 5, 9},
                            {8, 1, 3, 4},
                            {5, 0, 6, 1},
                            {8, 8, 4, 0}
                    };
            return new Matrix(raw, 4, 4);
        }
    }
}
