package com.gideon.reggie.common;

/**
 * ClassName: BaseContext
 * Package: com.gideon.reggie.common
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/9 10:27
 * @Version 1.0
 */
//基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static Long getCurrentId(){
        return threadLocal.get();
    }


}






















