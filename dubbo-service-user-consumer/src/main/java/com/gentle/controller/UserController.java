package com.gentle.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gentle.UserService;
import com.gentle.result.ResultBean;
import com.gentle.result.dto.ResultBeanDTO;
import com.gentle.vo.UsersVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gentle
 * @date 2019/03/26 : 17/22
 */
@RestController
public class UserController {

    @Reference
    private UserService userService;

    @GetMapping(value = "hello")
    public ResultBean<UsersVo> returns(){

        ResultBeanDTO<String> hello = userService.hellos();
        System.out.println(hello);
        ResultBean resultBean =new ResultBean();
        BeanUtils.copyProperties(hello,resultBean);
        return resultBean;
    }

}
