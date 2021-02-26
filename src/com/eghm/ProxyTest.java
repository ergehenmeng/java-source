package com.eghm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 殿小二
 * @date 2021/2/19
 */
public class ProxyTest implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method);
        return null;
    }
}
