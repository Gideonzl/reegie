package com.gideon.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gideon.reggie.entity.Category;

/**
 * ClassName: CategoryService
 * Package: com.gideon.reggie.service
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/9 10:46
 * @Version 1.0
 */
public interface CategoryService extends IService<Category> {


    //根据Id删除分类，删除之前需要进行判断
    public  void remove(Long id);
}
