package edu.princeton.cs.test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Stack;

/**
 * 一个栈依次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1。
 * 在不借助其他数据结构条件下实现栈的转置，即从栈顶到栈底变成1、2、3、4、5
 * */
public class GetAndRemoveLastElementClass implements StackAndQueueCallMethod {
    /*** self practice function **/
    private void selfReverseStack(Stack<Integer> sk){
    }

    private Integer lastElement(Stack<Integer> sk){return 0;}

    /***standard resolve***/
    private Integer getAndRemoveLastElement(Stack<Integer> sk){
        Integer result = sk.pop();
        if(sk.empty()){return result;}
        else{
            Integer last = getAndRemoveLastElement(sk);
            sk.push(result);
            return last;
        }
    }

    private void officialReverse(Stack<Integer> sk){
        if(sk.empty()) {return;}
        else {
            Integer last = getAndRemoveLastElement(sk);
            officialReverse(sk);
            sk.push(last);
        }
    }
    @Override
    public void callStackAndQueueAlgoMethod() {
        TestUtil.printString("++++++++++++++++ getAndRemoveLastElementTest ++++++++++++++++");
        prepareTestData();
        getAndRemoveLastElementTest();
    }

    @Test(groups = StackAndQueueCallMethod.testBaseName)
    public void getAndRemoveLastElementTest(){
        TestUtil.printString("Before reverse, stack is:");
        TestUtil.printStackReversly(sk);
        TestUtil.printString("Reverse stack ... ");
        officialReverse(sk);
        TestUtil.printString("After reverse, stack is");
        TestUtil.printStackReversly(sk);
    }

    @BeforeMethod(groups = StackAndQueueCallMethod.testBaseName)
    public void prepareTestData(){
        sk = new Stack<>();
        for(int i = 0; i < 9; i++) {
          sk.push(i);
        }
    }

    private Stack<Integer> sk;
}
