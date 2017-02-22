package com.jackycser.springmvc.classloader;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author Jacky Zhang
 * @since 13/01/2017
 */
public class MainTest {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
    }

    @Test
    public void testClassIdentity() {
        String classDataRootPath = "/Users/Jacky/Projects/GitHub/spring-mvc-study/target/classes";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printClassLoader() {
        ClassLoader loader = MainTest.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }

}
