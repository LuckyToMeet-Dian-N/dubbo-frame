package com.gentle.exception;

/**
 *
 *自定义异常类
 *@author CCJ
 *@date 2019/3/31 18:09
**/
public class CheckException extends RuntimeException {
    private Integer code;
    private String msg;

    public CheckException(Integer code, String message){
        this.code = code;
        this.msg = message;
    }

    public CheckException(String message){
        super(message);
    }

}