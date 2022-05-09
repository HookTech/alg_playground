package edu.princeton.cs.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author philo
 * @create 4/2/18 20:54
 **/
public class BitOperationTest extends BaseObjectTest<BitOperationCallMethod>{

    @BeforeMethod(groups = BitOperationCallMethod.testBaseName)
    public void initInstance(){super.initTestTasks(BitOperationCallMethod.class);}

    @Test(groups = BitOperationCallMethod.testBaseName)
    @Override
    protected void groupTest() {
        for(BitOperationCallMethod bit : testTasks){
            bit.callBitOperationAlgoMethod();
        }
    }
}
