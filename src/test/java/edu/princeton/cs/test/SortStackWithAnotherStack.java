package edu.princeton.cs.test;

import org.testng.annotations.Test;

import java.util.Stack;

public class SortStackWithAnotherStack implements StackAndQueueCallMethod {
    @Override
    public void callStackAndQueueAlgoMethod() {
        TestUtil.printString("++++++++++++++++ sortStackWithAnotherStackTest ++++++++++++++++");
        sortStackWithAnotherStackTest();
    }

    @Test
    public void sortStackWithAnotherStackTest(){
        Stack<Integer> stack = new Stack<>(), help = new Stack<>();
        for (int i = 9; i > 6; i--) {
            stack.push(i);
        }
        stack.push(4);stack.push(2);stack.push(3);stack.push(5);stack.push(6);
        TestUtil.printString("Before sorting, stack is:");
        TestUtil.printStackReversly(stack);
        TestUtil.printString("sorting stack ... ");
        while (!stack.isEmpty()){
            pushCurIntoHelp(stack.pop(), help, stack);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
        TestUtil.printString("After sorting, stack is:");
        TestUtil.printStackReversly(stack);
    }

    public void pushCurIntoHelp(Integer item, Stack<Integer> help, Stack<Integer> oldStack){
        while (!help.isEmpty() && help.peek() < item){
            oldStack.push(help.pop());
        }
        help.push(item);
    }
}
