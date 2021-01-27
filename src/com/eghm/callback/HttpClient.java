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
            "    \"msg\": \"SUCCx\",\n" +
            "    \"code\": \"100\",\n" +
            "    \"data\": {\n" +
            "        \"schoolId\": \"1196713856013959211\",\n" +
            "        \"schoolName\": \"四川大学\"\n" +
            "    }\n" +
            "}";

    /**
     * 调用成功的code码
     */
    private static final int SUCCESS = 200;

    public void register() {
        this.register(null, null, null);
    }

    public <T> void register(Callback<T> callback) {
        this.register(callback, null, null);
    }

    public <T> void register(Callback<T> callback, Integer code, ErrorCallback errorCallback) {
        if (callback == null) {
            Wrapper wrapper = gson.fromJson(response, Wrapper.class);
            this.doParseError(wrapper, code, errorCallback);
        } else {
            RespWrapper<T> wrapper = gson.fromJson(response, new ParameterizedTypeImpl(RespWrapper.class, new Type[]{this.getGenericType(callback.getClass())}));
            if (wrapper.getCode() == SUCCESS) {
                callback.onData(wrapper.getData());
            } else {
                this.doParseError(wrapper, code, errorCallback);
            }
        }
    }

    private void doParseError(Wrapper wrapper, Integer code, ErrorCallback errorCallback) {
        // 自定义错误码处理逻辑
        if (wrapper.getCode().equals(code) && errorCallback != null) {
            errorCallback.accept(wrapper);
            return;
        }
        if (wrapper.getCode() != SUCCESS) {
            // TODO 通用错误码提示
            System.out.println(wrapper.getMsg());
        }
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
        }, 100, data -> System.out.println(data.getMsg()));
    }
}
