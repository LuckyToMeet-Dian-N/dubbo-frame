package com.gentle.exceptionhandler;

import com.gentle.dto.ResultBeanDTO;
import com.gentle.exception.CheckException;
import com.gentle.po.Users;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Gentle
 * @date 2019/04/11 : 12:52
 */

@Slf4j
@Aspect
@Component
public class ExceptionHandlerAop {

    @Pointcut(value = "execution(public * com.gentle.api.impl.*.*(..))")
    public void handlerException(){
        Users users = new Users();
    }

    @Around("handlerException()")
    public Object handlerMethod(ProceedingJoinPoint proceedingJoinPoint){
        ResultBeanDTO resultBeanDTO =null;
        try {
            long startTime = System.currentTimeMillis();
            Object proceed = proceedingJoinPoint.proceed();
            resultBeanDTO = new ResultBeanDTO(proceed);

            long endTime = System.currentTimeMillis()-startTime;

            log.info("最后花费的时间为："+ endTime);
        }catch (Throwable e){
            resultBeanDTO= handlerException(e);
        }
        return resultBeanDTO;
    }

    private ResultBeanDTO<?> handlerException(Throwable throwable){
        ResultBeanDTO<?> resultBean=new ResultBeanDTO();
        if (throwable instanceof CheckException || throwable instanceof IllegalArgumentException){
            resultBean.setMsg(throwable.getLocalizedMessage());
            resultBean.setCode(ResultBeanDTO.CHECK_FAIL);
        }else {
            log.error("未知异常：",throwable);
            resultBean.setMsg("未知异常，请联系管理员");
            resultBean.setCode(ResultBeanDTO.UNKNOWN_EXCEPTION);
        }
        return resultBean;
    }

}
