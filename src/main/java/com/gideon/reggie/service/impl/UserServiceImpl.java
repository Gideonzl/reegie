package com.gideon.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gideon.reggie.entity.User;
import com.gideon.reggie.mapper.UserMapper;
import com.gideon.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Package: com.gideon.reggie.service.impl
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/10 14:16
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



}
