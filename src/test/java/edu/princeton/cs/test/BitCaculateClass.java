package edu.princeton.cs.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author philo
 * @create 4/12/18 07:01
 **/
public class BitCaculateClass implements BitOperationCallMethod{

    int testA = 45;

    int testB = 12;

    public boolean isNeg(int n){return n < 0;}

    private int div(int a, int b){// a/b
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 31; i > -1 ; i = minus(i, 1)) {
            if((x >> i) >= y){
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    private int divide(int a, int b){
        if(b == 0) throw new RuntimeException("divisor is 0");
        if(a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) return 1;
        else if(b == Integer.MIN_VALUE) return 0;
        else if(a == Integer.MIN_VALUE){
            int res = div(add(a,1),b);
            return add(res, div(minus(a, multi(res,b)),b));
        }
        else
            return div(a,b);
    }

    private int multi(int a, int b){//a*b
        int res = 0;
        while (b != 0){
            if((b & 1) != 0){
                res = res + a;
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    private int add(int a, int b){//a+b
        int sum = a;
        while (b != 0){
            sum = a^b;
            b = (a&b)<<1;
            a = sum;
        }
        return sum;
    }

    private int minus(int a, int b) {//a-b
        return add(a,negNum(b));
    }

    private int negNum(int a){
        return add(~a,1);
    }

    @Override
    public void callBitOperationAlgoMethod() {
        TestUtil.printString("++++++++++++++++ bitCalculateTest ++++++++++++++++");
        prepareTestData();
        bitCalculateTest();
    }

    @BeforeMethod(groups = BitOperationCallMethod.testBaseName)
    public void prepareTestData(){}

    @Test(groups = BitOperationCallMethod.testBaseName)
    public void bitCalculateTest(){
        TestUtil.printString(testA + "+" + testB + "=" + add(testA,testB));
        TestUtil.printString(testA + "-" + testB + "=" + minus(testA,testB));
        TestUtil.printString(testA + "x" + testB + "=" + multi(testA,testB));
        TestUtil.printString(testA + "/" + testB + "=" + divide(testA,testB));
    }
}
