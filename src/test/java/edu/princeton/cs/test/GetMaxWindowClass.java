package edu.princeton.cs.test;

import org.testng.annotations.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 生成窗口最大值数组问题
 * */
public class GetMaxWindowClass implements StackAndQueueCallMethod {
    /*** self practice function **/
    //get max value collection steam from big array with window move
    private int[] getMaxWindow(int[] arr, int w){
        assert arr.length >= w && w > 0;
        int arrLength= arr.length;
        int[] res = new int[arrLength - w + 1];
        Deque<Integer> qMax = new LinkedList<Integer>();//Double ended queue
        int index = 0;
        do{
            getMaxWindow_dealQMax(qMax,arr,w,index++);
        }
        while(index < w - 1);
        for (; index < arrLength; index++) {
            getMaxWindow_dealQMax(qMax,arr,w,index);
            res[index - w + 1] = arr[qMax.getFirst()];//query first element,max value
        }
        return res;
    }
    //deal qMax structure with element
    private void getMaxWindow_dealQMax(Deque<Integer> qMax, int[] arr, int w, int index){
        int element = arr[index];
        do{
            if(qMax.isEmpty()){
                qMax.addLast(index);
                break;
            }
            else{
                if(element < arr[qMax.getLast()]){
                    qMax.addLast(index);
                    if(qMax.getFirst() == index - w) qMax.pollFirst();
                    break;
                }
                else {
                    qMax.pollLast();
                }
            }
        }

        while (true);
    }

    /***standard resolve***/
    private int[] getMaxWindows(int[] arr, int w){
        if(arr == null || w < 1 || arr.length < w) return null;
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for(int i = 0; i < arr.length; i++){
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
                qmax.pollLast();
            }
            qmax.addLast(i);
            if(qmax.peekFirst() == i - w){ qmax.pollFirst(); }
            if(i >= w - 1){ res[index++] = arr[qmax.peekFirst()]; }
        }
        return res;
    }

    @Override
    public void callStackAndQueueAlgoMethod() {
        TestUtil.printString("++++++++++++++++ getMaxWindowTest ++++++++++++++++");
        getMaxWindowTest();
    }

    @Test(groups = StackAndQueueCallMethod.testBaseName)
    public void getMaxWindowTest() {
        TestUtil.printString("array is:");
        TestUtil.printIntArray(new int[]{4,3,5,4,6,3,6,7});
        TestUtil.printString("window length is 3:");
        TestUtil.printString("test get result:");
//        Thread.sleep(TestUtil.generateRandomInteger() * 1000);
        TestUtil.printIntArray(getMaxWindows(new int[]{4,3,5,4,6,3,6,7},3));
    }
}
