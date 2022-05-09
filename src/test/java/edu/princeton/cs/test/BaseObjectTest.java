package edu.princeton.cs.test;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public abstract class BaseObjectTest<T extends BaseTestInterface> {

    protected static final String packageName = BaseObjectTest.class.getPackage().getName();

    protected List<T> testTasks = new ArrayList<>();

    protected void initTestTasks(Class<T> type) {
        try {
            if(!type.isInterface()) throw new InstantiationException();
            String packageDirName = packageName.replace('.', '/');
            ClassLoader appClassLoader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> dirs = appClassLoader.getResources(packageDirName);
            URL packageDirPathURL = null;
            if (dirs.hasMoreElements()) packageDirPathURL = dirs.nextElement();
            else System.exit(-1);
            if (!"file".equals(packageDirPathURL.getProtocol())) System.exit(-1);
            File[] classForLoad = new File(packageDirPathURL.getPath()).listFiles();
            for (File file : classForLoad) {
                StringBuffer classNameToBeLoadedBuff = new StringBuffer();
                classNameToBeLoadedBuff.append(packageName).append(".")
                        .append(file.getName().substring(0, file.getName().indexOf(".")));
                Class currentClass = appClassLoader.loadClass(classNameToBeLoadedBuff.toString());
                if (!currentClass.equals(BaseTestInterface.class) && BaseTestInterface.class.isAssignableFrom(currentClass) && !currentClass.isInterface()) {
                    if (type.isAssignableFrom(currentClass)) {
                        //currentclass is xxxClass implements 'class<T> type'
//                        testTasks.add((T) currentClass.newInstance());
                        testTasks.add((T) Proxy.newProxyInstance(type.getClassLoader(),
                                new Class[]{type},
                                new BaseTestInterfaceProxyHandler<T>((T)currentClass.newInstance())));
                    }
                }
            }
        }
        catch (IOException e){System.exit(-1);}
        catch (ClassNotFoundException e){System.exit(-1);}
        catch (IllegalAccessException e){System.exit(-1);}
        catch (InstantiationException e){System.exit(-1);}
        catch (Exception e){System.exit(-1);}
    }

    @Test(groups = "AllTest")
    protected abstract void groupTest();

}
