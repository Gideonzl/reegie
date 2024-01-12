package com.gideon.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * ClassName: MyMetaObjecthandler
 * Package: com.gideon.reggie.common
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/9 9:44
 * @Version 1.0
 */

//自定义元数据对象处理器
@Component  // * Spring的组件注解，用于将被注解的类视为一个组件，可以被Spring管理并自动注入到其他组件中
@Slf4j
public class MyMetaObjecthandler implements MetaObjectHandler {
    //@Autowired
    //HttpServletRequest request;
    //获取ID：用注解@Autowired获取session中的employee对象也可以获取ID
    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("公共字段自动填充[insert]...");
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        //metaObject.setValue("createUser", request.getSession().getAttribute("employee"));
        //metaObject.setValue("updateUser", request.getSession().getAttribute("employee"));
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
        log.info(metaObject.toString());
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        log.info("公共字段自动填充[update]...");
        metaObject.setValue("updateTime", LocalDateTime.now());
        //metaObject.setValue("updateUser", request.getSession().getAttribute("employee"));
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
        log.info(metaObject.toString());
    }
}






















