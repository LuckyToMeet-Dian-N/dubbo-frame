package com.gentle.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gentle.result.ResultBean;
import com.gentle.UserService;
import com.gentle.vo.UsersVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gentle
 * @date 2019/03/26 : 17/22
 */
@RestController
public class UserController {

    @Reference
    UserService userService;

    @GetMapping(value = "hello")
    public ResultBean<UsersVo> returns(){
        System.out.println(userService);
        String hello = userService.hello();
        UsersVo usersVo = new UsersVo();
        usersVo.setUserName("123");
        return new ResultBean<>(usersVo);
    }

}
