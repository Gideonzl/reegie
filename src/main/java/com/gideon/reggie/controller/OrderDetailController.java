package com.gideon.reggie.controller;

import com.gideon.reggie.service.OrderDetailService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: OrderDetailController
 * Package: com.gideon.reggie.controller
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/11 11:13
 * @Version 1.0
 */
/**
 * 订单明细
 */
@Slf4j
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Resource
    private OrderDetailService orderDetailService;

}
