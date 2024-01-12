package com.gideon.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gideon.reggie.common.BaseContext;
import com.gideon.reggie.common.R;
import com.gideon.reggie.entity.Setmeal;
import com.gideon.reggie.entity.ShoppingCart;
import com.gideon.reggie.service.ShoppingCartService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: ShoppingCartController
 * Package: com.gideon.reggie.controller
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/11 11:14
 * @Version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){

        //设置用户id，指定当前是那个用户的购物车数据
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        //查询当前菜品或者是套餐是否在购物车中
        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,currentId);
        if(dishId != null){
            //添加到购物车的是菜品
            queryWrapper.eq(ShoppingCart::getDishId,dishId);
        }else{
            //添加到购物车的是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);
        if(cartServiceOne != null){
            //如果已经存在，就在原来的数量上加一
            Integer integer = cartServiceOne.getNumber();
            cartServiceOne.setNumber(integer+1);
            shoppingCartService.updateById(cartServiceOne);
        }else {
            //如果不存在则添加到购物车中，数量默认为一
            shoppingCartService.save(cartServiceOne);
            cartServiceOne.setNumber(1);
            cartServiceOne = shoppingCart;
        }
        log.info("购物车数据:{}",shoppingCart);
        return R.success(cartServiceOne);
    }


    /**
     * 查看
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
         LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
         queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
         queryWrapper.orderByDesc(ShoppingCart::getCreateTime);

         List<ShoppingCart> list = shoppingCartService.list(queryWrapper);
         return R.success(list);
    }

    @DeleteMapping("/clean")
    public R<String> clean(){

        LambdaQueryWrapper <ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        shoppingCartService.remove(queryWrapper);

        return R.success("清空购物车成功");

    }























}
