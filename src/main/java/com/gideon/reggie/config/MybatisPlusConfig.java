package com.gideon.reggie.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * ClassName: MybatisPlusConfig
 * Package: com.gideon.reggie.config
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/8 17:07
 * @Version 1.0
 */
//配置MP的分页插件
public class MybatisPlusConfig {
//新的分页插件,一缓和二缓遵循mybatis的规则,
//需要设置 MybatisConfiguration#useDeprecatedExecutor = false
//避免缓存出现问题(该属性会在旧插件移除后一同移除)
    @Bean
   public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();//mybatis-plus拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());//分页插件
        return interceptor;
    }
}






























