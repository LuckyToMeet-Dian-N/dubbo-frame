package com.gentle.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gentle.exception.CheckException;
import com.gentle.UserService;
import com.gentle.result.dto.ResultBeanDTO;

/**
 * @author Gentle
 * @date 2019/03/26 : 17/28
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    public ResultBeanDTO<String> hellos() {

        ResultBeanDTO<String> hi = hi();
        String data = hi.getData();

        return new ResultBeanDTO<>( data+",我来了");
    }

    @Override
    public ResultBeanDTO<String> hi() {
        return new ResultBeanDTO<>("你好啊，Dubbo");
    }
}
