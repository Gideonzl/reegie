package com.gideon.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gideon.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: DishMapper
 * Package: com.gideon.reggie.mapper
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/9 11:40
 * @Version 1.0
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
