package edu.princeton.cs.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListPTest extends BaseObjectTest<ListPCallMethod> {

    @BeforeMethod(groups = "ListPTest")
    public void initInstance() {
        super.initTestTasks(ListPCallMethod.class);
    }

    @Test(groups = "ListPTest")
    @Override
    protected void groupTest() {
        for (ListPCallMethod task : testTasks){
            task.callListPCallAlgoMethod();
        }
    }

}
