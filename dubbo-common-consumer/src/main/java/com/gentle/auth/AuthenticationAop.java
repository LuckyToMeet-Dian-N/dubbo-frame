package com.gentle.auth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gentle.RedisService;
import com.gentle.result.ResultBean;
import com.gentle.utils.RequestAndResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;

/**
 * @author Gentle
 * @date 2019/04/11 : 12:52
 * 权限拦截，可修改拦截的类或方法
 * 如要使用时，开启注释
 */
//@Aspect
//@Component
//@Slf4j
public class AuthenticationAop {

    @Reference
    private RedisService redisService;

//    @Pointcut("execution(public  * com.gentle.controller.*.*(..))")
    public void authority(){
    }

//    @Before("authority()")
    public void authenticationMethod(){

        HttpServletRequest request = RequestAndResponseUtils.getRequest();

        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            responseErrorInfo("亲，请你登录后操作");
        }
        String s = redisService.hashGet("", "");
        if (s==null){
            responseErrorInfo("亲，请你登录后操作");
        }
    }

    /**
     * 返回错误信息格式
     * @param errorInfo 错误信息
     * @return
     */
    private ResultBean responseErrorInfo(String errorInfo){
        ResultBean resultBean = new ResultBean();
        resultBean.setMsg(errorInfo);
        resultBean.setCode(ResultBean.NO_LOGIN);
        return resultBean;
    }




}
