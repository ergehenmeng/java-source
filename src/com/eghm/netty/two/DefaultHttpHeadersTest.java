package com.eghm.netty.two;

import io.netty.handler.codec.http.DefaultHttpHeaders;

/**
 * @author 殿小二
 * @date 2021/3/9
 */
public class DefaultHttpHeadersTest {

    public static void main(String[] args) {
        DefaultHttpHeaders headers = new DefaultHttpHeaders();
        headers.add("a", "b");
        headers.add("c", "d");
        String c = headers.get("c");
        System.out.println(Integer.numberOfLeadingZeros(1));
    }
}
