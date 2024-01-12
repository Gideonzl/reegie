package com.gideon.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gideon.reggie.dto.SetmealDto;
import com.gideon.reggie.entity.Setmeal;

import java.util.List;

/**
 * ClassName: SetmealService
 * Package: com.gideon.reggie.service
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/9 11:36
 * @Version 1.0
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     *  新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);
    public void removeWithDish(List<Long> ids);
}
