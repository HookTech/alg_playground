package edu.princeton.cs.test;

import org.testng.annotations.Test;

/**
 * 数组的值记录了从下标位置向右跳跃到另一个位置的范围
 * 给定数组，返回从第0个位置跳到最后一位需要的最小步数
 *
 * @author philo
 * @create 2018-03-30 2:35 PM
 **/
public class JumpGameClass implements RecursionAndDinamicCallMethod{

    /*** self practice function **/
    private int selfJump(int[] arr){
        return minStep(arr,0,arr.length - 1);
    }

    private int minStep(int[] arr,int s,int e){
        if(s >= e) return 0;
        int maxNum = Integer.MAX_VALUE;
        int range = arr[s] <= 0 ? -1 : arr[s];
        if(-1 == range) return maxNum;
        int min = maxNum;int tmp = 0;
        for(int i = 1; i <= range; i++) {
          tmp = minStep(arr,s+i,e);
          min = tmp < min ? tmp : min;
        }
        return min == maxNum ? maxNum : min + 1;
    }

    /***standard resolve***/
    public int officialJump(int[] arr){
        if (arr == null || arr.length == 0){return 0;}
        int jump = 0;
        int cur = 0;
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if(cur < i){
                jump++;
                cur = next;
            }
            next = Math.max(next,i + arr[i]);
        }
        return jump;
    }

    @Override
    public void callRecursionAndDinamicAlgoMethod() {
        TestUtil.printString("++++++++++++++++ jumpGameTest ++++++++++++++++");
        jumpGameTest();
    }

    @Test(groups = RecursionAndDinamicCallMethod.testBaseName)
    public void jumpGameTest() {
        int[] jumpArray = new int[]{3,-1,5,3,0,2,1,1};
        TestUtil.printString("jump arr is:");
        TestUtil.printIntArray(jumpArray);
        TestUtil.printString("jump min step is:" + officialJump(jumpArray));
    }
}
