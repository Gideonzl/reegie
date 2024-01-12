package com.gideon.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工实体
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;//身份证号码

    private Integer status;



    /**
     * 注解，用于在MyBatis Plus中对数据库表字段进行操作
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;// 创建时间，仅在插入时填充

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime; // 更新时间，既在插入时填充，也在更新时填充

    @TableField(fill = FieldFill.INSERT)
    private Long createUser; // 创建用户，仅在插入时填充

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser; // 更新用户，既在插入时填充，也在更新时填充



















}
