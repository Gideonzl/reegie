package com.gideon.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gideon.reggie.common.R;
import com.gideon.reggie.dto.SetmealDto;
import com.gideon.reggie.entity.Category;
import com.gideon.reggie.entity.Setmeal;
import com.gideon.reggie.service.CategoryService;
import com.gideon.reggie.service.SetmealDishService;
import com.gideon.reggie.service.SetmealService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: StemealDishController
 * Package: com.gideon.reggie.controller
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/10 11:19
 * @Version 1.0
 */

/**
 *  套餐菜品关系
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class StemealDishController {
    @Resource
    private SetmealDishService setmealDishService;
    @Resource
    private SetmealService setmealService;
    @Resource
    private CategoryService categoryService;
    /**
     * 新增套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        log.info("套餐信息：{}",setmealDto);
        setmealService.saveWithDish(setmealDto);
        return R.success("新增套餐成功");
    }

    /**
     *  套餐分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        //构造分页构造器对象
        Page <Setmeal> pageInfo = new Page<>(page,pageSize);
        //构造条件构造器对象
        Page<SetmealDto> dtoPage = new Page<>();
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据name进行like模糊查询
        queryWrapper.like(name != null,Setmeal::getName,name);
        //添加排序条件，根据更新时间进行降序排列
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        //进行分页查询
        setmealService.page(pageInfo,queryWrapper);
        //对象拷贝
        BeanUtils.copyProperties(pageInfo,dtoPage,"records");
        //获取分页查询结果中的records属性值
        List<Setmeal> records = pageInfo.getRecords();
        //对records属性值进行遍历，对每个对象进行拷贝
        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            //对象拷贝
            BeanUtils.copyProperties(item,setmealDto);
            //分类id
            Long categoryId = item.getCategoryId();
            //根据分类id查询分类对象
            Category category = categoryService.getById(categoryId);
            //如果查询到分类对象，则将分类对象的name属性值赋值给item对象的categoryName属性
            if (category != null){
                //分类名称
                String categoryName = category.getName();
                //将分类名称赋值给item对象的categoryName属性
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());
        //设置拷贝后的list属性值到dtoPage对象中
        dtoPage.setRecords(list);
        //返回结果
        return  R.success(dtoPage);
    }

    /**
     *  新增套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("ids:{}",ids);
        setmealService.removeWithDish(ids);
        return  R.success("删除套餐成功");
    }


    /**
     * 根据条件查询套餐数据
     * @param setmeal
     * @return
     */

    public R<List<Setmeal>> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(setmeal.getCategoryId() != null,Setmeal::getCategoryId,setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getStatus() != null,Setmeal::getStatus,setmeal.getStatus());
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> list = setmealService.list(queryWrapper);

        return R.success(list);
    }















}
