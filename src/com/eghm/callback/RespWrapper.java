package com.eghm.callback;

/**
 * @author 殿小二
 * @date 2021/1/26
 */
public class RespWrapper<T> extends Wrapper {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
