package com.eghm.callback;

/**
 * @author 殿小二
 * @date 2021/1/26
 */
public class Wrapper {

    /**
     * 响应code码
     */
    private Integer code;

    /**
     * 详细信息
     */
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
