package com.gentle.result.dto;

import java.io.Serializable;

/**
 * 返回
 * @author Gentle
 * @date 2019/04/11 : 12:45
 */
public class ResultBeanDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    private static final int SUCCESS = 0;

    public static final int CHECK_FAIL = 1;

    public static final int NO_PERMISSION = 2;

    public static final int UNKNOWN_EXCEPTION = -99;

    /**
     * 返回的信息(主要出错的时候使用)
     */
    private String msg = "success";

    /**
     * 接口返回码, 0表示成功, 其他看对应的定义
     * 0   : 成功
     * >0 : 表示已知的异常(例如提示错误等, 需要调用地方单独处理)
     * <0 : 表示未知的异常(不需要单独处理, 调用方统一处理)
     */
    private Integer code = SUCCESS;

    /**
     * 返回的数据
     */
    private T data;

    public ResultBeanDTO() {
        super();
    }

    public ResultBeanDTO(T data) {
        super();
        this.data = data;
    }

    public ResultBeanDTO(String msg, T data){
        super();
        this.data = data;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBeanDTO(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = UNKNOWN_EXCEPTION;
    }

    @Override
    public String toString() {
        return "ResultBeanDTO{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
