package com.eghm;

import java.lang.reflect.Proxy;

/**
 * @author 殿小二
 * @date 2021/2/19
 */
public class ProxyMain {

    public static void main(String[] args) {
        ProxyInter instance = (ProxyInter)Proxy.newProxyInstance(ProxyInter.class.getClassLoader(), new Class[]{ProxyInter.class}, new ProxyTest());
        ProxyInter instance2 = (ProxyInter)Proxy.newProxyInstance(ProxyInter.class.getClassLoader(), new Class[]{ProxyInter.class}, new ProxyTest());
        instance.say();
        instance2.say();
    }
}
