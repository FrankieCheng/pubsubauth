package com.weswu.pubsub;

/**
 * @author weswu
 * @date 2021/5/25
 */
public class HttpRes {
    private int code;
    private String data;

    public HttpRes(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
