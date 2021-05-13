package com.eghm.callback;

/**
 * @author 殿小二
 * @date 2021/1/26
 */
public interface ErrorCallback {

    /**
     * 回调处理响应信息
     * @param data 响应信息的外壳
     */
    void accept(Wrapper data);

    /**
     * 默认方式错误信息
     * @return 默认回调
     */
    static ErrorCallback identity() {
        return data -> System.out.println(data.getCode() + ":" + data.getMsg());
    }
}
