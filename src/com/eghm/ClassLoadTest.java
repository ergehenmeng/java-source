package com.eghm;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassLoadTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = ClassLoadTest.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(Thread.currentThread().getContextClassLoader());
        ClassLoader loader1 = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    if ("ClassLoaderTest".equals(name)){
                        byte[] bytes = Files.readAllBytes(Paths.get("/Users/wyb/ClassLoaderTest.class"));
                        return super.defineClass(name,bytes,0, bytes.length);
                    }else{
                        return super.loadClass(name);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        Object classLoaderTest = loader1.loadClass("ClassLoaderTest").newInstance();
        ClassLoader loader2 = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    if ("ClassLoaderTest".equals(name)){
                        byte[] bytes = Files.readAllBytes(Paths.get("/Users/wyb/ClassLoaderTest_v1.class"));
                        return super.defineClass(name,bytes,0, bytes.length);
                    }else{
                        return super.loadClass(name);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        Object classLoaderTest2 = loader2.loadClass("ClassLoaderTest").newInstance();
        System.out.println(classLoaderTest == classLoaderTest2 );
        System.out.println(classLoaderTest.getClass().getClassLoader());
        System.out.println(classLoaderTest2.getClass().getClassLoader());
    }
}
