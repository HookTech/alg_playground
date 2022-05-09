package edu.princeton.cs.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class StackAndQueueTest extends BaseObjectTest<StackAndQueueCallMethod>{

    @BeforeMethod(groups = StackAndQueueCallMethod.testBaseName)
    public void initInstance() {
        super.initTestTasks(StackAndQueueCallMethod.class);
    }

    @Test(groups = StackAndQueueCallMethod.testBaseName)
    @Override
    protected void groupTest() {
        for(StackAndQueueCallMethod task : testTasks){
            task.callStackAndQueueAlgoMethod();
        }
    }
}
