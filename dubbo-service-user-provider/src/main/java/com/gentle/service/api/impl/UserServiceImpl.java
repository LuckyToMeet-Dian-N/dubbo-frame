package com.gentle.service.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gentle.exception.CheckException;
import com.gentle.service.UserService;

/**
 * @author Gentle
 * @date 2019/03/26 : 17/28
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String hello() {
//        return "hello dubbo";
    throw new CheckException("123123");

    }
}
