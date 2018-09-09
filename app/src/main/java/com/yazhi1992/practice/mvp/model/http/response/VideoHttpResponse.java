package com.yazhi1992.practice.mvp.model.http.response;

/**
 * Created by codeest on 16/8/28.
 */

public class VideoHttpResponse<T> {

    private int code;
    private String msg;
    T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
