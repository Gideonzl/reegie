package com.gideon.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gideon.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserMapper
 * Package: com.gideon.reggie.mapper
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/10 14:15
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
