package com.gideon.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.gideon.reggie.common
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/8 11:33
 * @Version 1.0
 */

//异常处理
@ControllerAdvice(annotations = {Controller.class, RestController.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    //异常处理方法
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)//数据库
    private R<String> exceptionHandler(SQLIntegrityConstraintViolationException e){
        log.info(e.getMessage());
        if (e.getMessage().contains("Duplicate entry")){
            String[] split = e.getMessage().split("");
            String msg = split[2];
            return R.error(msg + "已存在");
        }
        return R.error("已存在该数据");
    }


    /**
     *  异常处理方法
     * @param customException
     * @return
     * 前端会根据返回的信息显示到页面上
     */
    @ExceptionHandler(CustomException.class)
    public  R<String> exceptionHandler(CustomException customException){
        log.info(customException.getMessage());
        return R.error(customException.getMessage());
    }
}



















