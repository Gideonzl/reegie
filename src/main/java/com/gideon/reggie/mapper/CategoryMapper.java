package com.gideon.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gideon.reggie.entity.Category;
import com.gideon.reggie.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: CategoryMapper
 * Package: com.gideon.reggie.mapper
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/9 10:46
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
