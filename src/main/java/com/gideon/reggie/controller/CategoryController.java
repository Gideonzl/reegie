package com.gideon.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gideon.reggie.common.R;
import com.gideon.reggie.entity.Category;
import com.gideon.reggie.entity.Setmeal;
import com.gideon.reggie.service.CategoryService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CategoryController
 * Package: com.gideon.reggie.controller
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/9 10:53
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    /**
     * * @Description: 新增分类
     * @param request
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Category category) {
        //直接用写好的类MyMetaObjecthandler，在这里进行填充，进行添加或删除操作
        log.info("save category:{}", category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        //分页构造器
        Page<Category> pageInfo = new Page<Category>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<Category>();

        //添加排序条件，根据sort字段进行排序
        queryWrapper.orderByAsc(Category::getSort);

        //进行分页
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     *  * @Description: 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id){
        log.info("delete category:{}",id);
        //调用自己写的删除类
        categoryService.remove(id);
        return R.success("删除成功");
    }


    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Category category){
        log.info("update category:{}",category,"update category:{}",category);
        categoryService.updateById(category);
        return R.success("修改成功");
    }


    /**
     *
     * @Description: 根据条件查询分类数据
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category) {
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        //查询
        List<Category> list = categoryService.list(queryWrapper);

        return R.success(list);
    }












































}
