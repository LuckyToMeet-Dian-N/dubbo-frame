package com.gentle;

import com.gentle.result.dto.ResultBeanDTO;

/**
 * @author Gentle
 * @date 2019/03/26 : 17/23
 */
public interface UserService {

    ResultBeanDTO<String > hellos();

    ResultBeanDTO<String> Hi();

}
