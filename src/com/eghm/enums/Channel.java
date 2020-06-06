package com.eghm.enums;

/**
 * 客户端类型
 * @author 二哥很猛
 * @date 2018/8/14 13:51
 */
public enum Channel {

    /**
     * pc客户端
     */
    PC,

    /**
     * 安卓客户端
     */
    ANDROID,

    /**
     * ios客户端
     */
    IOS,

    /**
     * H5客户端
     */
    H5,

    /**
     * 微信小程序
     */
    WECHAT,

    /**
     * 支付宝小程序
     */
    ALIPAY;


    /**
     * 根据下标获取响应渠道信息
     */
    public static Channel getChannel(byte index){
        for (Channel channel : Channel.values()){
            if(channel.ordinal() == index){
                return channel;
            }
        }
        return Channel.PC;
    }
}

