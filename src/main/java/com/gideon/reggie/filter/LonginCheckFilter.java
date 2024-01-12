package com.gideon.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.gideon.reggie.common.BaseContext;
import com.gideon.reggie.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

/**
 * ClassName: LonginCheckFilter
 * Package: com.gideon.reggie.filter
 * Description:
 *
 * @Author 吉迪恩
 * @Create 2024/1/8 9:25
 * @Version 1.0
 */


/*
    拦截器，登录是否成功才可以进入主页
*/
@WebFilter(filterName = "loginCheckFilter" , urlPatterns = "/*")
@Slf4j
public class LonginCheckFilter implements Filter {
    public static final AntPathMatcher   //用于路径比较的String包下的
            PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;//向下转型,用子类的方法
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取本次请求的URL
        String requestURI = request.getRequestURI();

        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",   //只显示页面，不显示内容即可
                "/front/**",      //只显示页面，不显示内容即可
                "/user/sendMsg",
                "/user/login"
        };
        //2.判断本次请求的URL是否需要处理
        boolean check = check(urls, requestURI);
        //3.如果不需要处理，则直接放行
        if (check){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        //4-1.判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("employee") != null){//拿到Session对象看看里面是否有employee对象数据
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("employee"));

            //利用多线程来获取用户的ID
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);//自己编写的BaseContext类来实现

            filterChain.doFilter(request, response);
            return;
        }

        //4-2.判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("user") != null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("user"));

            //利用多线程来获取用户的ID
            Long userid = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userid);//自己编写的BaseContext类来实现

            filterChain.doFilter(request, response);
            return;
        }
        //5.如果未登录，则跳转到登录页面
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        //response.getWriter().write()方法会直接将参数写入到response的body中
        //浏览器发现response的body被写入内容，则浏览器不再显示其默认的登录提示信息
        //如果不想被浏览器拦截，则可以不使用response.getWriter().write()方法
        return;

    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    private boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}














