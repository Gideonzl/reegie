package com.gideon.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gideon.reggie.dto.DishDto;
import com.gideon.reggie.entity.DishFlavor;
import com.gideon.reggie.mapper.DishFlavorMapper;
import com.gideon.reggie.service.DishFlavorService;
import com.gideon.reggie.service.DishService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: DishFlavorServiceImpl
 * Package: com.gideon.reggie.service.impl
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/9 15:33
 * @Version 1.0
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {

}
