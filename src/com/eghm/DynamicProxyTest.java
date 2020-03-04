package com.eghm;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DynamicProxyTest {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{User.class});
        try(FileOutputStream stream = new FileOutputStream("/Users/wyb/UserProxy.class")){
            stream.write(bytes);
            stream.flush();
        }
    }
}
