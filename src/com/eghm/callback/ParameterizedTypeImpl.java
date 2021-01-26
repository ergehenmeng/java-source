package com.eghm.callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author 殿小二
 * @date 2021/1/26
 */
public class ParameterizedTypeImpl implements ParameterizedType {

    private Class<?> rawTypes;

    private Type[] type;

    public ParameterizedTypeImpl(Class<?> rawTypes, Type[] type) {
        this.rawTypes = rawTypes;
        this.type = type != null ? type : new Type[0];
    }

    @Override
    public Type[] getActualTypeArguments() {
        return type;
    }

    @Override
    public Type getRawType() {
        return rawTypes;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
