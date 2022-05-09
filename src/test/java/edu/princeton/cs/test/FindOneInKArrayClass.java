package edu.princeton.cs.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author philo
 * @create 4/2/18 21:08
 **/
public class FindOneInKArrayClass implements BitOperationCallMethod {

    private int[] arr;

    private int k;

    private int selfOnceNum(int[] arr,int k){
        return 1;
    }

    private int officialOnceNum(int[] arr, int k){
        int[] e0 = new int[32];
        for (int i = 0; i != arr.length; i++) {
            setExclusiveOr(e0,arr[i],k);
        }
        int res = getNumFromKSysNum(e0, k);
        return res;
    }

    private void setExclusiveOr(int[] e0, int value, int k){
        int[] curKSysNum = getKSysNumFromNum(value,k);
        for (int i = 0; i != e0.length; i++) {
            e0[i] = (e0[i] + curKSysNum[i]) % k;
        }
    }

    private int[] getKSysNumFromNum(int value, int k){
        int[] res = new int[32];
        int index = 0;
        while (value != 0){
            res[index++] = value % k;
            value = value / k;
        }
        return res;
    }

    private int getNumFromKSysNum(int[] e0, int k){
        int res = 0;
        for (int i = e0.length - 1; i != -1; i--) {
            res = res * k + e0[i];
        }
        return res;
    }

    @Override
    public void callBitOperationAlgoMethod() {
        TestUtil.printString("++++++++++++++++ findOneInKArrayTest ++++++++++++++++");
        prepareTestData();
        findOneInKArrayTest();
    }

    @Test(groups = BitOperationCallMethod.testBaseName)
    public void findOneInKArrayTest(){
        TestUtil.printString("The kth num is "+ k +" and the arr is:");
        TestUtil.printIntArray(arr);
        TestUtil.assertEqualAndPrintToInfo(officialOnceNum(arr,k),12);
    }

    @BeforeMethod(groups = BitOperationCallMethod.testBaseName)
    public void prepareTestData(){
        arr = new int[]{4,4,12,4,4,2,3,2,3,2,3,2,3};
        k = 4;
    }
}
