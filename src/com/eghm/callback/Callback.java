package com.eghm.callback;

/**
 * @author 殿小二
 * @date 2021/1/26
 */
public interface Callback<T> {

    /**
     * 请求成功
     * @param data 响应信息
     */
    default void onData(T data) {
    }

}
