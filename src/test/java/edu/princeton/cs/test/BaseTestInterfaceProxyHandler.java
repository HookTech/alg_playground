package edu.princeton.cs.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Dinamic Proxy handler class
 *
 * @author philo
 * @create 2018-03-30 5:36 PM
 **/
public class BaseTestInterfaceProxyHandler<T extends BaseTestInterface> implements InvocationHandler {
    private final T proxyedInterface;

    private long testCaseStartTime = 0L;
    private long testCaseEndTime = 0L;

    public BaseTestInterfaceProxyHandler(T proxyedInterface){
        this.proxyedInterface = proxyedInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeProxyInterfaceInvoke(method,args);
        Object res = method.invoke(proxyedInterface,args);
        afterProxyInterfaceInvoke(method,args);
        TestUtil.printString("============ It costs " + String.valueOf(testCaseEndTime - testCaseStartTime) + " ms ============\n");
        testCaseStartTime = 0L;testCaseEndTime = 0L;
        return res;
    }

    protected void beforeProxyInterfaceInvoke(Method method, Object[] args){
        TestUtil.printString("<<<<<<<<<<<< start " + method.getName() + " Test >>>>>>>>>>>>");
        testCaseStartTime = System.currentTimeMillis();
    }

    protected void afterProxyInterfaceInvoke(Method method, Object[] args){
        testCaseEndTime = System.currentTimeMillis();
        TestUtil.printString(">>>>>>>>>>>> end " + method.getName() + " Test <<<<<<<<<<<<");
    }
}
