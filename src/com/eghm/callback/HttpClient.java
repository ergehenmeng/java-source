package com.eghm.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author 殿小二
 * @date 2021/1/26
 */
public class HttpClient {

    private Gson gson = new Gson();

    /**
     * 后台返回的数据
     */
    private String response = "{\n" +
            "    \"msg\": \"success\",\n" +
            "    \"code\": \"200\",\n" +
            "    \"data\": null\n" +
            "}";

    /**
     * 调用成功的code码
     */
    private static final int SUCCESS = 200;

    public void register() {
        this.register(Callback.identity(), ErrorCallback.identity());
    }

    public void register(ErrorCallback error) {
        this.register(Callback.identity(), error);
    }

    public <T> void register(Callback<T> callback) {
        this.register(callback, ErrorCallback.identity());
    }


    public <T> void register(Callback<T> success, ErrorCallback error) {
        RespWrapper<T> wrapper = gson.fromJson(response, new ParameterizedTypeImpl(RespWrapper.class, new Type[]{this.getGenericType(success.getClass())}));
        // 成功
        if (wrapper.getCode() == SUCCESS) {
            success.onData(wrapper.getData());
            return;
        }
        error.accept(wrapper);
    }

    /**
     * 解析泛型参数
     */
    private Type getGenericType(Class<?> cls) {
        Type type = cls.getGenericInterfaces()[0];
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return parameterizedType.getActualTypeArguments()[0];
        }
        return null;
    }


    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        client.register(new Callback<School>() {
            @Override
            public void onData(School data) {
                System.out.println(data);
            }
        }, data -> System.out.println(data.getMsg()));
    }
}
