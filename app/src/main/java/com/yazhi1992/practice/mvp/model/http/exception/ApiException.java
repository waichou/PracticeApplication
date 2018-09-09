package com.yazhi1992.practice.mvp.model.http.exception;

/**
 * Created by 12262 on 2016/5/31.
 */
public class ApiException extends Exception {


    private int code;
    private String displayMessage;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;

    }

    public ApiException(String e){
        super(e);
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
