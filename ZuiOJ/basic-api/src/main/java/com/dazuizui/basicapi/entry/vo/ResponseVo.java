package com.dazuizui.basicapi.entry.vo;

/**
 * 后端返回给前端的数据封装题
 */
public class ResponseVo<T> {

    private String message; //返回信息
    private T data;    //返回数据
    private String code;    //响应状态码

    @Override
    public String toString() {
        return "ResponseVo{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", code='" + code + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public ResponseVo setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseVo setData(T data) {
        this.data = data;
        return this ;
    }

    public String getCode() {
        return code;
    }

    public ResponseVo setCode(String code) {
        this.code = code;
        return this;
    }

    public ResponseVo(String message, T data, String code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public ResponseVo() {
    }
}