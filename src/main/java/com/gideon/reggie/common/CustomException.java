package com.gideon.reggie.common;

/**
 * 自定义业务异常类
 */
public class CustomException extends RuntimeException {
    public CustomException(String message){//message异常时的提示信息,自己写的
        super(message);
    }
}
