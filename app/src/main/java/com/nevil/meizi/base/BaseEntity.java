package com.nevil.meizi.base;

import java.io.Serializable;

/**
 * Created by Tangkun on 2017/5/4.
 */

public class BaseEntity<E> implements Serializable {
    private int code;
    private String message;
    private E data;

    public int getCode() {
        return code;
    }

    public BaseEntity setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public E getData() {
        return data;
    }

    public BaseEntity setData(E data) {
        this.data = data;
        return this;
    }
}
